package class11;

public class NQueens {
    public static int num1(int n) {
        if (n < 1) {
            return 0;
        }
        //record[i] -> i行的皇后，放在了第几列
        int[] record = new int[n];
        return process1(0, record, n);
    }

    /**
     * @param i      行数
     * @param record 放置皇后的物质
     * @param n      一共的行数
     * @return 摆完所有的皇后，合理的摆法有多少种
     */
    private static int process1(int i, int[] record, int n) {
        // base case
        if (i == n) {
            return 1;
        }
        int res = 0;
        // 当前行在i行，尝试i行所有的列  -> j
        for (int j = 0; j < n; j++) {
            // 当前i行的皇后，放在j列，会不会和之前(0..i-1)的皇后，不共行共列或者共斜线，
            // 如果是，认为有效
            // 如果不是，认为无效
            if (isValid(record, i, j)) {
                record[i] = j;
                res += process1(i + 1, record, n);
            }
        }
        return res;
    }

    /**
     * record[0..i-1]你需要看，record[i...]不需要看
     * 返回i行皇后，放在了j列，是否有效
     *
     * @param record 位置
     * @param i      行数
     * @param j      列数
     * @return 是否有效
     */
    private static boolean isValid(int[] record, int i, int j) {
        // 之前的某个k行的皇后
        for (int k = 0; k < i; k++) {
            if (j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k)) {
                return false;
            }
        }
        return true;
    }

    public static int num2(int n) {
        if (n < 1 || n > 32) {
            return 0;
        }
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process2(limit, 0, 0, 0);
    }

    /**
     * @param limit       总共的棋子
     * @param colLim      列的限制，1的位置不能放皇后，0的位置可以
     * @param leftDiaLim  左斜线的限制，1的位置不能放皇后，0的位置可以
     * @param rightDiaLim 右斜线的限制，1的位置不能放皇后，0的位置可以
     * @return 最多放几个结果
     */
    private static int process2(int limit, int colLim, int leftDiaLim, int rightDiaLim) {
        if(colLim==limit){
            return 1;
        }
        //~表示位取反，先将所有限制合并,然后位取反，再和limit与，结果就是，pos上1位置表示可以放皇后，0位置不行
        int pos=limit&(~(colLim|leftDiaLim|rightDiaLim));
        int res=0;
        int mostRightOne=0;
        //pos上1代表可以放皇后，每试一个皇后，就把1变成0，全0表示这一行全试完了
        while(pos!=0){
            //取反加1后与自己，能够取出最右边的二进制位上的1
            mostRightOne=pos & (~pos+1);
            //pos去掉最右边的1
            pos=pos-mostRightOne;
            //确定这一行的一个皇后位置后，向下递归
            //colLim|mostRightOne:列限制加上这一行新添加的
            //(leftDiaLim|mostRightOne)<<1:左边45°线上加上新添加的后，再全体左移一位，表示下一行45°线的限制
            //(rightDiaLim|mostRightOne)>>>1:右边45°线上加上新添加的后，再全体右移一位，表示下一行45°线的限制
            res+=process2(limit,colLim|mostRightOne,
                    (leftDiaLim|mostRightOne)<<1,
                    (rightDiaLim|mostRightOne)>>>1);
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 14;

        long start = System.currentTimeMillis();
        System.out.println(num2(n));
        long end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

        start = System.currentTimeMillis();
        System.out.println(num1(n));
        end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

    }
}
