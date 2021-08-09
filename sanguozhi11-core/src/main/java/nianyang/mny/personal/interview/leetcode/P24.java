package nianyang.mny.personal.interview.leetcode;

import nianyang.mny.personal.interview.leetcode.dto.ListNode;
import nianyang.mny.personal.interview.leetcode.util.ListNodeBuilder;

public class P24 {

    public static void main(String[] args) {
        P24 p=new P24();

        int[] arr=new int[]{1,2};
        ListNode node = ListNodeBuilder.build(arr);

        ListNode pt=node;
//        while(pt!=null){
//            System.out.print(pt.val+" ");
//            pt=pt.next;
//        }

        ListNode listNode = p.swapPairs(node);
        while(listNode!=null){
            System.out.print(listNode.val  +" ");
            listNode=listNode.next;
        }

    }

    public ListNode swapPairs(ListNode head) {
        if(head==null || head.next ==null ){
            return head;
        }
        ListNode newHead=head.next;
        ListNode p=new ListNode(0);
        p.next=head;
        while(p!=null && p.next!=null){
            exchange(p);
            if(p.next!=null){
                p=p.next;
            }
            if(p.next!=null) {
                p = p.next;
            }
        }
//        ListNode test=p.next;
//        while(test.next!=null){
//            System.out.print(test.val+" ");
//        }
        return newHead;

    }

    //交换目标节点后的两个节点顺序
    public void exchange(ListNode node){
        if(node== null || node.next==null || node.next.next==null) {
            return;
        }
        ListNode n=node.next;
        ListNode nn=node.next.next;

        //交换
        node.next=nn;
        n.next=nn.next;

        nn.next=n;


    }
}
