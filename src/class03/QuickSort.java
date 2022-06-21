package class03;

import java.util.Random;

public class QuickSort {
    /**
     * 荷兰国旗问题
     * 给定一个数组arr，和一个整数num。请把小于num的数放在数组的左边，等于num的数放在中间，大于num的数放在数组的右边。
     * 解题思路：使用”划分的方法“，将比num小的都放在左边，大的都放在右边
     */
    public static int partition(int[] arr, int L, int R) {
        if (L > R) {
            return -1;
        }
        if (L == R) {
            return L;
        }
        //规定左边界与右边界
        //左边界刚开始确定为L -1， 右边界刚开始确定为 R + 1，然后将arr[R]作为num
        int lessEqual = L - 1;
        int index = L;
        while (index < R) {
            if (arr[index] <= arr[R]) {
                //这是第一种情况：此值小于等于要求值，将此值放于左区间内，左区间向前移动
                swap(arr,index,++lessEqual);
            }
            index++;
        }
        //将此值放于左区间边界
        swap(arr,++lessEqual,R);
        return lessEqual;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 荷兰国旗求解方法
     * @param arr 数组
     * @param L 左边界
     * @param R 右边界
     * @return 左区间边界值 与 右区间边界值
     */
    public  static int[] netherlandsFlag(int[] arr, int L, int R) {
        if (L > R) {
            return new int[] {-1, -1};
        }
        if (L == R) {
            return new int[] {L, R};
        }
        //规定最开始的左边界 与 右边界 将arr[R]作为num
        int less = L - 1;
        int more = R;
        int index = L;
        //当最开始值小于边界值时
        while (index < more) {
            if (arr[index] < arr[R]) {
                //此值与num交换 同时index向前移动 左区间向前移动
                swap(arr,index++,++less);
            } else if (arr[index] == arr[R]) {
                //相等时 index向前移动
                index++;
            } else {
                //大于时 此值交换，index保持不变
                swap(arr,index,--more);
            }
        }
        //最后， 将num放入右区间 划分结束
        //0..less less+1..more-1 more..R-1 R
        //o..less less+1..more more+1..R
        swap(arr,more,R);
        return new int[] {less + 1, more};
    }

    //由荷兰国旗问题 引出快排
    public static void quickSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process1(arr,0,arr.length -1);
    }

    private static void process1(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int M = partition(arr,L,R);
        process1(arr,L,M -1);
        process1(arr, M + 1, R);
    }

    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process2(arr,0,arr.length - 1);
    }

    private static void process2(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        //根据荷兰国旗问题 将相等值直接进行提取
        int[] equalArea = netherlandsFlag(arr,L,R);
        process2(arr,L,equalArea[0] -1);
        process2(arr,equalArea[1] + 1, R);
    }

    public static void quickSort3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process3(arr,0,arr.length - 1);
    }

    private static void process3(int[] arr, int L, int R) {
        if (L>= R) {
            return;
        }
        //随机选择数 将其先放在最右边
        swap(arr,L+ (int)(Math.random()*(R - L + 1)),R);
        int[] equalArea = netherlandsFlag(arr,L,R);
        process3(arr,L,equalArea[0] -1);
        process3(arr,equalArea[1] + 1, R);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            int[] arr3 = copyArray(arr1);
            quickSort1(arr1);
            quickSort2(arr2);
            quickSort3(arr3);
            if (!isEqual(arr1, arr2) || !isEqual(arr2, arr3)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Oops!");

    }

}
