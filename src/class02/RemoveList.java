package class02;

public class RemoveList {
    public static class Node {
        int value;
        Node next;
        public Node (int data) {
            value = data;
        }
    }

    public static class DoubleNode {
        int value;
        DoubleNode next;
        DoubleNode last;
        public DoubleNode(int data) {
            value = data;
        }
    }
    public static Node removeNode(Node head, int num) {
        while(head != null) {
            if (head.value != num) {
                break;
            }
            head = head.next;
        }
        Node pre = head;
        Node cur = head;
        while (cur != null) {
            if (cur.value == num) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    public static DoubleNode removeDouble(DoubleNode head, int num) {
        while (head != null) {
            if (head.value != num) {
                break;
            }
            head = head.next;
        }
        DoubleNode pre = head;
        DoubleNode cur = head;

        while (cur != null) {
            if (cur.value == num) {
                pre.next = cur.next;
                cur.next.last = cur.last;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    public static Node generateRandomLinkedList(int len, int value) {
        int size = (int) (Math.random() * (len + 1));
        if (size == 0) {
            return null;
        }
        size--;
        Node head = new Node((int) (Math.random() * (value + 1)));
        Node pre = head;
        while (size != 0) {
            Node cur = new Node((int) (Math.random() * (value + 1)));
            pre.next = cur;
            pre = cur;
            size--;
        }
        return head;
    }
    // 要求无环，有环别用这个函数
    public static boolean checkLinkedListEqual(Node head1, Node head2) {
        while (head1 != null && head2 != null) {
            if (head1.value != head2.value) {
                return false;
            }
            head1 = head1.next;
            head2 = head2.next;
        }
        return head1 == null && head2 == null;
    }

    public static void main(String[] args) {
        int len = 50;
        int value = 100;
        int testTime = 100000;
        for (int i = 0; i < testTime; i++) {
            Node node1 = generateRandomLinkedList(len, value);
            Node reverse1 = removeNode(node1,4);
//            Node back1 = testReverseLinkedList(reverse1);
//            if (!checkLinkedListEqual(node1, back1)) {
//                System.out.println("oops!");
//                break;
//            }
        }
        System.out.println("finish!");

    }
}
