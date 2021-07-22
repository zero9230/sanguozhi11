package nianyang.mny.demo;

import nianyang.mny.leetcode.dto.ListNode;

/**
 * @author sikou
 * @date 2021/07/21
 */
public class Test {

    //1 2 3 4 5 6 7
    //
    //
    //7 5 6 3 4 1 2

    
    public ListNode skipReverse(ListNode head){
        if(head==null || head.next==null){
            return head;
        }
        //ListNode node1,node2,node3,node4,node5,node6,node7;
        //node3.next=node4; //自带
        //node4.next=node1;
        //node5.next=node6; //自带
        //node6.next=node3;  //循环重复点
        //return node5;
        ListNode newHead=head;
        ListNode p=head;

        while(newHead.next!=null){
            ListNode p3=p;
            ListNode p4=p3.next;
            p4.next=newHead;
            newHead=p3;
        }
        return newHead;
    }

}
