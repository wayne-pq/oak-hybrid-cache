package cn.xxywithpq.infrastructure.cache.lc.agroupofkflippedlists;

import cn.xxywithpq.infrastructure.cache.lc.ListNode;

class AGroupOfKFlippedLists {
    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode tail = head;
        ListNode end = head;
        // for循环是判断当前链表是否够K个节点，如果不够的话，直接返回头节点，够的话，则end会到达第k+1个节点
        for (int i = 0; i < k; i++) {
            if (end == null) {
                return head; // 不足 k 个节点，返回原链表
            }
            end = end.next;
        }
        ListNode newHead = reverseListNode(head, k);
        tail.next = reverseKGroup(end, k);

        return newHead;
    }


    public static ListNode reverseListNode(ListNode head, int k) {
        if (k == 1)
            return head;
        ListNode pre = null;
        ListNode cur = head;
        while (k > 0) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
            k--;
        }

        return pre;

    }


    public static void main(String[] args) {
        ListNode listNode = new ListNode();
        listNode.val = 1;
        ListNode listNode2 = new ListNode();
        listNode2.val = 2;
        ListNode listNode3 = new ListNode();
        listNode3.val = 3;
        ListNode listNode4 = new ListNode();
        listNode4.val = 4;
        ListNode listNode5 = new ListNode();
        listNode5.val = 5;
        listNode.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        ListNode listNode1 = reverseKGroup(listNode, 3);
        while (listNode1 != null) {
            System.out.println(listNode1.val);
            listNode1 = listNode1.next;
        }

    }
}