package class02;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 使用队列实现栈
 * 使用两个队列 一个全部放入 另一个全部放出 来实现
 */
public class TwoQueueImplementStack {
   public static class TwoQueueStack<T> {
       public Queue<T> queue;
       public Queue<T> help;

       public TwoQueueStack() {
           queue = new LinkedList<>();
           help = new LinkedList<>();
       }

       public void push(T value) {
           queue.offer(value);
       }
   }
}
