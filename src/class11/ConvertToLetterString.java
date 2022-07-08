package class11;

public class ConvertToLetterString {
    public static int number(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        return process(str.toCharArray(), 0);
    }

    private static int process(char[] str, int i) {
        // base case
        if (i == str.length) {
            return 1;
        }
        //i没有到终止条件
        if (str[i] == '0') {
            return 0;
        } else if (str[i] == '1') {
            //i 作为单独的一部分，有多少种走法
            int res = process(str, i + 1);
            //i和i+1作为单独的部分，后续有多少种走法
            if (i + 1 < str.length) {
                res += process(str, i + 2);
            }
            return res;
        } else if (str[i] == '2') {
            int res = process(str, i + 1);
            if (i + 1 < str.length && str[i + 1] >= '0' && str[i + 1] <= '6') {
                res += process(str, i + 2);
            }
            return res;
        } else {
            return process(str, i + 1);
        }
    }

    public static void main(String[] args) {
        String str = "111";
        int ans = number(str);
        System.out.println(ans);
    }
}
