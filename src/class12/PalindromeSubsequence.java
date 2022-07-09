package class12;

public class PalindromeSubsequence {
    public static int lcse(String text1, String text2) {
        char[] str1 = text1.toCharArray();
        char[] str2 = text2.toCharArray();
        int[][] dp = new int[str1.length][str2.length];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        //第0列的所有值
        for (int i = 1; i < str1.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], str1[i] == str2[0] ? 1 : 0);
        }
        //第0行的所有值
        for (int i = 1; i < str2.length; i++) {
            dp[0][i] = Math.max(dp[0][i - 1], str1[0] == str2[i] ? 1 : 0);
        }
        for (int i = 1; i < str1.length; i++) {
            for (int j = 1; j < str2.length; j++) {
                //情况2与情况3
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                //情况4
                if (str1[i] == str2[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
                //为什么可以忽略情况1，4）=1+1），情况2与情况3已经都决策一遍可能性1了，因此可以忽略可能性1
            }
        }
        return dp[str1.length - 1][str2.length - 1];
    }
}
