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

    // 从右往左的动态规划
    // 就是上面方法的动态规划版本
    // dp[i]表示：str[i...]有多少种转化方式
    public static int dp1(String s) {
        //base case
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = s.length();
        int[] dp = new int[N + 1];
        dp[N] = 1;
        for (int i = N - 1; i >= 0; i--) {
            //如果此此时str[i] == '0', 那么他是一定要拉前一个字符（i-1的字符）进行拼接的
            // 那么就要求前面一个字符，不能是'0'，否则不能拼接
            // 前一个字符不是‘0’就够了嘛？不够，还得要求拼完了要么是10，要么是20，如果更大的话，拼不了。
            // 这就够了嘛？还不够，你们拼完了，还得要求str[0...i-2]真的可以被分解！
            // 如果str[0...i-2]都不存在分解方案，那i和i-1拼成了也不行，因为之前的搞定不了。
            if (str[i] != '0') {
                dp[i] = dp[i + 1];
                if (i + 1 < str.length && (str[i] - '0') * 10 + (str[i + 1] - '0') < 27) {
                    dp [i] += dp[i + 2];
                }

            }
        }
//            if (str[i] == '0') {
//                dp[i] = 0;
//            } else if (str[i] == '1') {
//                dp[i] = dp[i + 1];
//                if (i + 1 < N) {
//                    dp[i] += dp[i + 2];
//                }
//            } else if (str[i] == '2') {
//                dp[i] = dp[i + 1];
//                if (i + 1 < N && str[i + 1] >= '0' && str[i + 1] <= '6'){
//                    dp[i] += dp[i + 2];
//                }
//            } else {
//                dp[i] = dp[i + 1];
//            }
//        }
        return dp[0];
    }

    public static void main(String[] args) {
        String str = "111";
        int ans = number(str);
        System.out.println(ans);
        int ans1 = dp1(str);
        System.out.println(ans1);
    }
}
