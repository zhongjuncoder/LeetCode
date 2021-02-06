package LinkedList.easy;

import tree.ListNode;

import java.util.ArrayList;
import java.util.List;

public class Order {

    /**
     * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
     *
     * 示例 1：
     *
     * 输入：head = [1,3,2]
     * 输出：[2,3,1]
     * https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/
     */
    public int[] reversePrint(ListNode head) {
        if (head == null) {
            return new int[0];
        }
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }

        int[] result = new int[list.size()];
        for (int i = list.size() - 1; i >= 0; i--) {
            result[list.size() - 1 - i] = list.get(i);
        }
        return result;
    }

}
