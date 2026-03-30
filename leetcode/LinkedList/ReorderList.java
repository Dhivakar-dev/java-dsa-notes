package LinkedList;

import implementation.ListNode;

public class ReorderList {

    public void reorderList(ListNode head) {

        if(head ==null || head.next == null) {
            return;
        }

        ListNode m = mid(head);
        ListNode first =  head;
        ListNode second =  rev(m);
        ListNode temp;

        while(second.next != null) {
            temp = first.next;
            first.next = second;
            first = temp;

            temp = second.next;
            second.next = first;
            second = temp;
        }

    }

    public ListNode mid(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while(fast!=null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;

    }
    public ListNode rev(ListNode head) {

        ListNode prev = null;
        ListNode curr = head;

        while(curr != null) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }

        return prev;

    }
}
