package class18;

public class MinLength {

    public static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node(int x) {
            val = x;
        }
    }

    public static int minHeight1(Node head) {
        if (head == null) {
            return 0;
        }
        return process(head);
    }

    private static int process(Node head) {
        if (head.left == null && head.right == null) {
            return 1;
        }
        int leftHeight = Integer.MAX_VALUE;
        if (head.left != null) {
            leftHeight = process(head.left);
        }
        int rightHeight = Integer.MAX_VALUE;
        if (head.right != null) {
            rightHeight = process(head.right);
        }
        return 1 + Math.min(leftHeight, rightHeight);
    }


    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static int minHeight2(Node head) {
        if (head == null) {
            return 0;
        }
        Node cur = head;
        Node mostRight = null;
        int curLevel = 0;
        int minHeight = Integer.MAX_VALUE;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                int leftHeight = 1;
                while (mostRight.right != null && mostRight.right != cur) {
                    leftHeight++;
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    curLevel += 1;
                    continue;
                } else {
                    if (mostRight.left == null) {
                        minHeight = Math.min(minHeight, curLevel);
                    }
                    curLevel -= leftHeight;
                    mostRight.right = null;
                }
            } else {
                curLevel++;
            }
            cur = cur.right;
        }
        //最后遍历对应的最优子树
        int finalRight = 1;
        cur = head;
        while (cur.right != null) {
            finalRight++;
            cur = cur.right;
        }
        if (cur.left == null && cur.right == null) {
            minHeight = Math.min(finalRight, minHeight);
        }
        return minHeight;
    }

    public static void main(String[] args) {
        int treeLevel = 7;
        int nodeMaxValue = 5;
        int testTimes = 100000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(treeLevel, nodeMaxValue);
            int ans1 = minHeight1(head);
            int ans2 = minHeight2(head);
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish!");

    }
}
