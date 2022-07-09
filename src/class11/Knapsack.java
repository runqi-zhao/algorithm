package class11;

public class Knapsack {
    public static int getMaxValue(int[] w, int [] v, int bag) {
        return process(w, v, 0 , 0, bag);
    }

    /**
     *
     * @param w weight
     * @param v value
     * @param index 当前进行到的序号
     * @param alreadyW 判断剩余重量 是否超过背包
     * @param bag 背包剩余的容量
     * @return 最大价值
     */
    private static int process(int[] w, int[] v, int index, int alreadyW, int bag) {
        //超过重量
        if (alreadyW > bag) {
            return -1;
        }
        //重量没超
        if (index == w.length) {
            return 0;
        }
        // 有货也有空间
        //不选择index
        int p1 = process(w, v, index + 1, alreadyW, bag);
        //选择index
        int p2Next = process(w, v, index + 1, alreadyW + w[index], bag);
        int p2 = -1;
        //当前重量没超
        if (p2Next != -1) {
            p2 = v[index] +p2Next;
        }
        return Math.max(p1, p2);
    }

    public static int dpWay(int[] w, int[] v, int bag) {
        int N = w.length;
        int[][] dp = new int[N + 1][bag +1];
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 1; rest <= bag; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (rest >= w[index]) {
                    dp[index][rest] = Math.max(dp[index][rest],v[index] + dp[index + 1][rest - w[index]]);
                }
            }
        }
        return dp[0][bag];
    }

    public static void main(String[] args) {
        int[] weights = { 3, 2, 4, 7 };
        int[] values = { 5, 6, 3, 19 };
        int bag = 11;
        System.out.println(getMaxValue(weights, values, bag));
        System.out.println(dpWay(weights, values, bag));
    }
}
