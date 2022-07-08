package class11;

public class CardsInLine {
    // 根据规则，返回获胜者的分数
    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return Math.max(f(arr, 0 , arr.length - 1), s(arr, 0, arr.length - 1));
    }

    /**
     * arr[L..R]，后手获得的最好分数返回
     * @param arr array
     * @param i 左边界
     * @param j 右边界
     * @return 后手获得的最好分数
     */
    private static int s(int[] arr, int i, int j) {
        if (i == j) {
            return 0;
        }
        //对手拿走i位置与j位置进行比较
        return Math.min(arr[i] + f(arr, i + 1, j), arr[j] + f(arr, i, j -1));
    }

    /**
     * arr[L..R]，先手获得的最好分数返回
     * @param arr array
     * @param i 左边界
     * @param j 右边界
     * @return 先手获得的最好分数
     */
    private static int f(int[] arr, int i, int j) {
        if (i == j) {
            return 0;
        }
        return Math.max(arr[i] + s(arr, i + 1, j), arr[j] + s(arr, i , j -1));

    }

    public static void main(String[] args) {
        int[] arr = { 1, 9, 1 };
        System.out.println(win1(arr));
        //System.out.println(win2(arr));

    }
}
