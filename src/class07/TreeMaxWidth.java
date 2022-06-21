package class07;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class TreeMaxWidth {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

        public static int maxWidthUseMap(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        HashMap<Node, Integer> levelMap = new HashMap<>();
        levelMap.put(head, 1);
        int currentLevel = 1;
        int currentLevelNodes = 0;
        int max = 0;
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            int currentNodeLevel = levelMap.get(current);
            if (current.left != null) {
                levelMap.put(current.left, currentNodeLevel + 1);
                queue.add(current.left);
            }
            if (current.right != null) {
                levelMap.put(current.right, currentNodeLevel + 1);
                queue.add(current.right);
            }
            if (currentNodeLevel == currentLevel) {
                currentLevelNodes++;
            } else {
                max = Math.max(max, currentLevelNodes);
                currentLevel++;
                currentLevelNodes = 1;
            }
        }
        max = Math.max(max, currentLevelNodes);
        return max;
    }

    public static int maxWidthNoMap(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Node currentEnd = head;
        Node nextEnd = null;
        int max = 0;
        int currentLevelNodes = 0;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.left != null) {
                queue.add(cur.left);
                nextEnd = cur.left;
            }
            if (cur.right != null) {
                queue.add(cur.right);
                nextEnd = cur.right;
            }
            currentLevelNodes++;
            if (cur == currentEnd) {
                max = Math.max(max, currentLevelNodes);
                currentLevelNodes = 0;
                currentEnd = nextEnd;
            }
        }
        return max;
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

    public static void main(String[] args) {
        int maxLevel = 10;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxWidthUseMap(head) != maxWidthNoMap(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");

    }
}
