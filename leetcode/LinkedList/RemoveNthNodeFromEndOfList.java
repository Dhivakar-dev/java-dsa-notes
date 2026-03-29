package LinkedList;

import implementation.ListNode;

public class RemoveNthNodeFromEndOfList {

    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode dummy = new ListNode();
        dummy.next = head;

        ListNode front = dummy;
        ListNode back = dummy;

        for(int i=0; i<=n; i++) {
            front = front.next;
        }

        while(front != null) {
            front = front.next;
            back = back.next;
        }

        back.next = back.next.next;

        return dummy.next;

    }
}
