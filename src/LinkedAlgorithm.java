import java.util.LinkedList;

public class LinkedAlgorithm {

    public static void main(String[] args) {
        SingleLinked linked1 = new SingleLinked(1);
        SingleLinked linked2 = new SingleLinked(2);
        SingleLinked linked3 = new SingleLinked(3);
        SingleLinked linked4 = new SingleLinked(4);
        SingleLinked linked5 = new SingleLinked(5);
        SingleLinked linked6 = new SingleLinked(6);
        SingleLinked linked7 = new SingleLinked(7);

        linked1.next = linked2;
        linked2.next = linked3;
        linked3.next = linked4;
        linked4.next = linked5;
        linked5.next = linked6;
        linked6.next = linked7;
        //linked7.next = linked5;

        SingleLinked singleLinked=reverseLinked(linked1);
        while (singleLinked != null) {
            System.out.print(singleLinked.value + " ");
            singleLinked = singleLinked.next;
        }
    }

    /**
     * 判断链表是否有环，使用快慢指针
     */
    private static boolean isCycle(SingleLinked linked) {
        if (linked == null) {
            return false;
        }

        SingleLinked slow = linked.next;
        SingleLinked fast = linked.next.next;

        while (fast != null) {
            if (slow == fast) {
                return true;
            }

            slow = slow.next;

            if (fast.next == null) {
                return false;
            }
            fast = fast.next.next;
        }

        return false;
    }

    private static SingleLinked reverseLinked(SingleLinked linked) {
        if (linked == null) {
            return null;
        }
        SingleLinked root = linked;
        SingleLinked prev = linked;
        while (root != null) {
            SingleLinked next = root.next;
            root = root.next;
            next.next = prev;
            prev = next;
        }
        return prev;
    }


    private static class SingleLinked {

        int value;

        SingleLinked next;

        public SingleLinked(int value) {
            this.value = value;
        }

    }

    private static class DoublyLinked {

        int value;

        SingleLinked prev;

        SingleLinked next;

    }

}

