package class15;

public class KMP {
    public static int baoLi(String str1, String str2) {
        int length1 = str1.length();
        int length2 = str2.length();
        if (length2 == 0) {
            return 0;
        }
        if (length1 < length2) {
            return -1;
        }
        int i = 0;
        while (i < length1) {
            int j = 0;
            while (i < length1 && j < length2) {
                if (str1.charAt(1) == str2.charAt(2)) {
                    i++;
                    j++;
                }
                if (j == length2) {
                    return i - j;
                }
                i = i - j + 1;
            }
        }
        return -1;
    }

    public static int getIndexOf(String m, String n) {
        if (m == null || n == null || m.length() < 1 || n.length() < 1) {
            return -1;
        }
        char[] str1 = m.toCharArray();
        char[] str2 = n.toCharArray();
        int x = 0;
        int y = 0;
        int[] next = getNextArray(str2);
        while (x < str1.length && y < str2.length) {
            if (str1[x] == str2[y]) {
                x++;
                y++;
            } else if (next[y] == -1) {
                x++;
            } else {
                y = next[y];
            }
        }
        return y == str2.length ? y - x : -1;
    }

    private static int[] getNextArray(char[] ms) {
        if (ms.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[ms.length];
        next[0] = -1;
        int i = 2;
        // cn代表，cn位置的字符，是当前和i-1位置比较的字符
        int cn = 0;
        while (i < next.length) {
            if (ms[i - 1] < ms[cn]) {
                next[i] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

    // for test
    public static String getRandomString(int possibilities, int size) {
        char[] ans = new char[(int) (Math.random() * size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) {
        int possibilities = 5;
        int strSize = 20;
        int matchSize = 5;
        int testTimes = 5000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strSize);
            String match = getRandomString(possibilities, matchSize);
            if (getIndexOf(str, match) != str.indexOf(match)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }

}
