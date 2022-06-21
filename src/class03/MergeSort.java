package class03;

public class MergeSort {

    /**
     * 归并排序递归版 核心思想实现
     * @param arr 数组
     * @param L 左区间
     * @param M 中间区分的地方
     * @param R 右区间
     */
    public static void merge(int[] arr, int L, int M, int R) {
        //开辟一个长度一样的辅助空间
        int[] help = new int [R - L + 1];
        int p1 = L;
        int p2 = M + 1;
        int i = 0;
        while(p1 <= M && p2 <= R) {
            //拷贝小的
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++]: arr[p2++];
        }

        while (p1 <= M) {
            help[i++] = arr[p1++];
        }

        while(p2 <= R) {
            help[i++] = arr[p2++];
        }
        //此时 help就是有序的 将其拷贝回原数组

        for (int j = 0; j < help.length; j++) {
            arr[L + j] = help[j];
        }
    }

    /**
     * 递归方式实现
     * @param arr 数组
     */
    public static void mergeSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr,0,arr.length -1);
    }

    public static void process(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int mid = L + ((R - L) >> 1);
        process(arr,L, mid);
        process(arr, mid + 1, R);
        merge(arr,L, mid, R);
    }

    /**
     * 非递归方式实现
     * @param arr 数组
     */
    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        //此时时代表几个有序
        int mergeSize = 1;
        //开始归并排序
        while (mergeSize < N) {
            int L = 0;
            //当左区间无序时
            while (L < N) {
                //规定右边界
                int M = L  + mergeSize -1;
                //如果说此时右边界已经大于最大值 那么此时一定是已经有序的 可以直接退出
                if (M >= N) {
                    break;
                }
                //接下来 就需要将此区间上进行递归排序
                //确定出右边界，是否到达顶端
                int R = Math.min(N -1, M + mergeSize);
                //进行排序
                merge(arr, L, M, R);
                //将左边界移动到有边界下一个位置
                L = R + 1;
            }
            //避免越界的发生
            if (mergeSize > N / 2) {
                break;
            }
            //最大范围进行进行移动
            mergeSize <<=  1;
        }
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
            mergeSort1(arr1);
            mergeSort2(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Oops!");
    }
}
