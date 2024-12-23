package cn.xxywithpq.infrastructure.cache.lc.add.two.numbers;


import cn.xxywithpq.infrastructure.cache.lc.ListNode;

class Solution {
    public static void main(String[] args) {
        ListNode listNode = new ListNode();
        listNode.val = 2;
        ListNode listNode2 = new ListNode();
        listNode2.val = 4;
        ListNode listNode3 = new ListNode();
        listNode3.val = 3;
        listNode.next = listNode2;
        listNode2.next = listNode3;

        ListNode listNode4 = new ListNode();
        listNode4.val = 5;
        ListNode listNode5 = new ListNode();
        listNode5.val = 6;
        ListNode listNode6 = new ListNode();
        listNode6.val = 4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;

        ListNode listNode1 = new Solution().addTwoNumbers(listNode, listNode4);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1);
        ListNode cur = head;
        int nextAdd = 0;
        while (l1 != null || l2 != null || nextAdd != 0) {
            int l1v = l1 != null ? l1.val : 0;
            int l2v = l2 != null ? l2.val : 0;
            int value = (l1v + l2v + nextAdd) % 10;
            cur.next = new ListNode(value);
            nextAdd = (l1v + l2v + nextAdd) / 10;
            cur = cur.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        return head.next;
    }
}