package LinkedList;

import implementation.ListNode;

public class MergeTwoSortedLists {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        ListNode dummy = new ListNode(1);
        ListNode merge = dummy;

        while(list1 != null && list2 !=null) {
            if(list1.val <= list2.val) {
                merge.next = list1;
                list1 = list1.next;
            } else {
                merge.next = list2;
                list2 = list2.next;
            }

            merge = merge.next;
        }

        if(list1 != null) {
            merge.next = list1;
        }else {
            merge.next = list2;

        }

        return dummy.next;

    }
}
