package class10;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Prim {
    public static class EdgeComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public static Set<Edge> primMST(Graph graph) {
        //解锁边进入小根堆
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        HashSet<Node> set = new HashSet<>();
        //将结果依次放入
        Set<Edge> result = new HashSet<>();
        //随便挑选一个点
        for (Node node : graph.nodes.values()) {
            if (!set.contains(node)) {
                set.add(node);
                //由一个点，解锁相连的边
                for (Edge edge : node.edges) {
                    priorityQueue.add(edge);
                }
                while (!priorityQueue.isEmpty()) {
                    //弹出解锁的的边中，最小的边
                    Edge edge = priorityQueue.poll();
                    // 可能的一个新的点
                    Node toNode = edge.to;
                    // 不含有的时候，就是新的点
                    if (!set.contains(toNode)) {
                        set.add(toNode);
                        result.add(edge);
                    }
                    for (Edge nextEdge : toNode.edges) {
                        priorityQueue.add(nextEdge);
                    }
                }
            }
        }
        return result;
    }

    /**
     * 保证graph是连通图
     * @param graph 表示点i到点j的距离，如果是系统最大值代表无路
     * @return 返回值是最小连通图的路径之和
     */
    public static int prim(int[][] graph) {
        int size = graph.length;
        int[] distances = new int[size];
        boolean[] visit = new boolean[size];
        visit[0] = true;
        int sum = 0;
        for (int i = 0; i < size; i++) {
            int minPath = Integer.MAX_VALUE;
            int minIndex = -1;
            for (int j = 0; j < size; j++) {
                if (!visit[j] && distances[j] < minPath) {
                    minPath = distances[j];
                    minIndex = j;
                }
            }
            if (minIndex == -1) {
                return sum;
            }
            visit[minIndex] = true;
            sum += minPath;
            for (int j = 0; j < size; j++) {
                if (!visit[j] && distances[j] > graph[minIndex][j]) {
                    distances[j] = graph[minIndex][j];
                }
            }
        }
        return sum;
    }
}
