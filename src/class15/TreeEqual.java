package class15;

import java.util.ArrayList;

public class TreeEqual {

    public static class TreeNode {
        public int value;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int v) {
            value = v;
        }
    }

    public static boolean containsTree1(TreeNode big, TreeNode small) {
        if (small == null) {
            return true;
        }
        if (big == null) {
            return false;
        }
        if (isSameValueStructure(big, small)) {
            return true;
        }
        return containsTree1(big.left, small) || containsTree1(big.right, small);
    }

    public static boolean isSameValueStructure(TreeNode head1, TreeNode head2) {
        if (head1 == null && head2 != null) {
            return false;
        }
        if (head1 != null && head2 == null) {
            return false;
        }
        if (head1 == null && head2 == null) {
            return true;
        }
        if (head1.value != head2.value) {
            return false;
        }
        return isSameValueStructure(head1.left, head2.left) && isSameValueStructure(head1.right, head2.right);
    }

    public static boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (subRoot == null) {
            return true;
        }
        if (root == null) {
            return false;
        }
        ArrayList<String> rootStr = preSerial(root);
        ArrayList<String> subRootStr = preSerial(subRoot);
        String[] str = new String[rootStr.size()];
        for (int i = 0; i < str.length; i++) {
            str[i] = rootStr.get(i);
        }

        String[] match = new String[subRootStr.size()];
        for (int i = 0; i < match.length; i++) {
            match[i] = subRootStr.get(i);
        }

        return getIndexOf(str, match) != -1;

    }

    private static int getIndexOf(String[] str, String[] match) {
        if (str == null || match == null || str.length < 1 || str.length < match.length) {
            return -1;
        }
        int x = 0;
        int y = 0;
        int[] next = getNextArray(match);
        while (x < str.length && y < match.length) {
            if (isEqual(str[x], match[y])) {
                x++;
                y++;
            } else if (next[y] == -1) {
                x++;
            } else {
                y = next[y];
            }
        }
        return y == match.length ? x - y : -1;
    }

    private static int[] getNextArray(String[] match) {
        if (match.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[match.length];
        int i = 2;
        int cn = 0;
        next[0] = -1;
        next[1] = 0;
        while (i < match.length) {
            if (isEqual(match[i - 1], match[cn])) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

    private static boolean isEqual(String a, String b) {
        if (a == null && b == null) {
            return true;
        } else {
            if (a== null || b == null) {
                return false;
            } else {
                return a.equals(b);
            }
        }
    }

    private static ArrayList<String> preSerial(TreeNode root) {
        ArrayList<String> result = new ArrayList<>();
        pre(result, root);
        return result;
    }

    private static void pre(ArrayList<String> result, TreeNode root) {
        if (root == null) {
            result.add(null);
        } else {
            result.add(String.valueOf(root.value));
            pre(result, root.left);
            pre(result, root.right);
        }
    }

    // for test
    public static TreeNode generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static TreeNode generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        TreeNode head = new TreeNode((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int bigTreeLevel = 7;
        int smallTreeLevel = 4;
        int nodeMaxValue = 5;
        int testTimes = 100000;
        System.out.println("test begin");
        TreeNode test1 = new TreeNode(3);
        test1.left = new TreeNode(4);
        test1.right = new TreeNode(5);
        test1.left.left = new TreeNode(1);
        test1.left.right = new TreeNode(2);
        TreeNode test2 = new TreeNode(4);
        test2.left = new TreeNode(1);
        test2.right = new TreeNode(2);
        boolean a =  containsTree1(test1, test2);
        boolean b= isSubtree(test1, test2);
        if (a != b) {
            System.out.println("Oops!");
        }
        for (int i = 0; i < testTimes; i++) {
            TreeNode big = generateRandomBST(bigTreeLevel, nodeMaxValue);
            TreeNode small = generateRandomBST(smallTreeLevel, nodeMaxValue);
            boolean ans1 = containsTree1(big, small);
            boolean ans2 = isSubtree(big, small);
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish!");

    }
}
