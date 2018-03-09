/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode p = new ListNode(-1);
        p.next = head;

        ListNode q = head;
        head = p;

        for (int i = 0; i < n; i++)
            q = q.next;

        while (q != null) {
            q = q.next;
            p = p.next;
        }
        p.next = p.next.next;
        return head.next;
    }
}