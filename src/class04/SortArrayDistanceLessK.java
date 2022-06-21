package class04;

import java.util.PriorityQueue;

/**
 * 已知一个几乎有序的数组。几乎有序是指，如果把数组排好顺序的话，每个元素移动的距离一定不超过k，并且k相对于数组长度来说是比较小的。
 * 请选择一个合适的排序策略，对这个数组进行排序。
 */
public class SortArrayDistanceLessK {
    public void sortedArrDistanceLessK (int[] arr, int k) {
        //系统提供的堆 默认小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int index = 0;
        //这里是将前k数进行加入
        for(; index <= Math.min(arr.length, k); index++) {
            heap.add(arr[index]);
        }
        int i = 0;
        for(; index < arr.length; i++, index++) {
            heap.add(arr[index]);
            arr[i] = heap.poll();
        }
        while (!heap.isEmpty()) {
            arr[i++] = heap.poll();
        }
    }
    public static void main(String[] args) {

        PriorityQueue<Integer> heap = new PriorityQueue<>();

        heap.add(8);
        heap.add(4);
        heap.add(4);
        heap.add(9);
        heap.add(10);
        heap.add(3);

        while (!heap.isEmpty()) {
            System.out.println(heap.poll());
        }

    }
}
