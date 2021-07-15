package nianyang.mny.leetcode.util;

import nianyang.mny.leetcode.dto.ListNode;

import java.util.List;

public class ListNodeBuilder {

    public static ListNode build(List<Integer> list){

        return null;
    }

    public static ListNode build(int[] arr){
        if(arr==null || arr.length<=0){
            return null;
        }
        ListNode head=new ListNode(arr[0]);
        ListNode p=head;
        for (int i = 1; i < arr.length; i++) {
            ListNode node=new ListNode(arr[i]);
            p.next=node;
            p=p.next;
        }
        return head;
    }

}
