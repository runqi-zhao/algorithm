package class08;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class lowestAncestor {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static class Info {
        //a和b的最初交汇点，如果不是在当前这颗X节点的树上，返回空
        public Node ans;
        //当前子树上，是否发现a和b
        public boolean findA;
        public boolean findB;

        public Info(Node ans, boolean findA, boolean findB) {
            this.ans = ans;
            this.findA = findA;
            this.findB = findB;
        }
    }

    public static Info process(Node head, Node a, Node b) {
        if (head == null) {
            return new Info(null, false, false);
        }
        Info leftInfo = process(head.left, a, b);
        Info rightInfo = process(head.right, a, b);
        //构建head自身需要返回的Info
        //head为头树上是否发现a
        boolean findA = head == a || leftInfo.findA || rightInfo.findA;
        //head为头的树上是否发现b
        boolean findB = head == b || leftInfo.findB || rightInfo.findB;
        Node ans = null;
        // 1) 在左树上已经提前交汇了,最初交汇点保留左树的
        if (leftInfo.ans != null) {
            ans = leftInfo.ans;
        }
        //2)在右树上已经提前交汇了，最初交汇点保留右树的
        if (rightInfo.ans != null) {
            ans = rightInfo.ans;
        }
        // 3) 没有在左树或者右树上提前交汇
        if (ans == null) {
            // 但是找到了a和b，那么交汇点就是X自身
            if (findA && findB) {
                ans = head;
            }
        }
        return new Info(ans, findA, findB);
    }

    public static Node lowestAncestor(Node head, Node a, Node b) {
        if (head == null) {
            return null;
        }
        return process(head, a, b).ans;
    }

    // 解法1，借助辅助Map和Set
    public static Node lowestAncestor1(Node head, Node o1, Node o2) {
        if (head == null) {
            return null;
        }
        // key的父节点是value
        HashMap<Node, Node> parentMap = new HashMap<>();
        parentMap.put(head, null);
        // 递归填充map
        fillParentMap(head, parentMap);
        // 辅助set
        HashSet<Node> o1Set = new HashSet<>();
        Node cur = o1;
        o1Set.add(cur);
        // o1Set存入的是沿途所有的父节点
        while (parentMap.get(cur) != null) {
            cur = parentMap.get(cur);
            o1Set.add(cur);
        }
        cur = o2;
        // o2的某个父节点在o1Set中，就是我们要找的节点
        while (!o1Set.contains(cur)) {
            cur = parentMap.get(cur);
        }
        return cur;
    }

    public static void fillParentMap(Node head, HashMap<Node, Node> parentMap) {
        if (head.left != null) {
            parentMap.put(head.left, head);
            fillParentMap(head.left, parentMap);
        }
        if (head.right != null) {
            parentMap.put(head.right, head);
            fillParentMap(head.right, parentMap);
        }
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

    // for test
    public static Node pickRandomOne(Node head) {
        if (head == null) {
            return null;
        }
        ArrayList<Node> arr = new ArrayList<>();
        fillPrelist(head, arr);
        int randomIndex = (int) (Math.random() * arr.size());
        return arr.get(randomIndex);
    }

    // for test
    public static void fillPrelist(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        arr.add(head);
        fillPrelist(head.left, arr);
        fillPrelist(head.right, arr);
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            Node o1 = pickRandomOne(head);
            Node o2 = pickRandomOne(head);
            if (lowestAncestor1(head, o1, o2) != lowestAncestor(head, o1, o2)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
