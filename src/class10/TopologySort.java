package class10;

import java.util.*;

public class TopologySort {

    /**
     * 调节 有向图并且无环
     * @param graph 图
     * @return 拓扑顺序
     */
    public static List<Node> sortedTopology (Graph graph) {
        //key:某一个node
        //value:剩余的入度
        HashMap<Node,Integer> inMap = new HashMap<Node,Integer>();
        Queue<Node> zeroInQueue = new LinkedList<>();
        for (Node node: graph.nodes.values()) {
            inMap.put(node,node.in);
            if (node.in == 0) {
                zeroInQueue.add(node);
            }
        }

        //将拓扑的结果依次加入result
        List<Node> result = new ArrayList<>();
        while (!zeroInQueue.isEmpty()) {
            Node cur = zeroInQueue.poll();
            result.add(cur);
            for (Node next : cur.nexts) {
                inMap.put(next, inMap.get(next) -1);
                if (inMap.get(next) == 0) {
                    zeroInQueue.add(next);
                }
            }
        }
        return result;
    }
}
