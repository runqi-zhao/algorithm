package class10;

import java.util.*;

import static class10.GraphGenerator.createGraph;

/**
 * 此算法知识和于无向图
 */
public class Kruskal {

    public static class MySets {
        public HashMap<Node, List<Node>> setMap;
        public MySets(List<Node> nodes) {
            for (Node cur : nodes) {
                List<Node> set = new ArrayList<Node>();
                set.add(cur);
                setMap.put(cur, set);
            }
        }

        public boolean isSameSet(Node from, Node to) {
            List<Node> fromSet = setMap.get(from);
            List<Node> toSet = setMap.get(to);
            return fromSet == toSet;
        }

        public void union (Node from, Node to) {
            List<Node> fromSet = setMap.get(from);
            List<Node> toSet = setMap.get(to);
            for (Node toNode : toSet) {
                fromSet.add(toNode);
                setMap.put(toNode,fromSet);
            }
        }
    }

    public static class UnionFind {
        // key 某一个节点， value key节点往上的节点
        private HashMap<Node, Node > fatherMap;
        //key 某一个集合的代表节点，value key所在集合的节点个数
        private HashMap<Node, Integer> sizeMap;

        public UnionFind () {
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
        }

        public void makeSets(Collection<Node> nodes) {
            fatherMap.clear();
            sizeMap.clear();
            for (Node node : nodes) {
                fatherMap.put(node, node);
                sizeMap.put(node,1);
            }
        }

        public Node findFather (Node n) {
            Stack<Node> path = new Stack<>();
            while (n != fatherMap.get(n)) {
                path.add(n);
                n = fatherMap.get(n);
            }
            while (!path.isEmpty()) {
                fatherMap.put(path.pop(), n);
            }
            return n;
        }

        public boolean isSameSet (Node a, Node b) {
            return findFather(a) == findFather(b);
        }

        public void union (Node a, Node b) {
            if (a == null || b == null) {
                return;
            }
            Node aDai = findFather(a);
            Node bDai = findFather(b);
            if (aDai != bDai) {
                int aSetSize = sizeMap.get(aDai);
                int bSetSize = sizeMap.get(bDai);
                Node big = aSetSize >= bSetSize ? aDai :bDai;
                Node small = big == bDai ? aDai : bDai;
                fatherMap.put(small, big);
                sizeMap.put(big, aSetSize + bSetSize);
                sizeMap.remove(small);
            }
        }
    }

    public static class EdgeComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public static ArrayList<Edge> KruskalMST(Graph graph) {
        if (graph == null) return null;
        UnionFind unionFind = new UnionFind();
        unionFind.makeSets(graph.nodes.values());
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        //M条边
        //O(logM)
        priorityQueue.addAll(graph.edges);
        ArrayList<Edge> result = new ArrayList<>();
        while (!priorityQueue.isEmpty()) { // M 条边
            Edge edge = priorityQueue.poll(); // O(logM)
            if (!unionFind.isSameSet(edge.from, edge.to)) { // O(1)
                result.add(edge);
                unionFind.union (edge.from, edge.to);
            }
        }
        return result;
    }

    public static void test(){
        //造一个图

        Integer[][] matrix = {
                {1,2,1},
                {2,3,2},
                {3,4,3},
                {4,5,100},
                {2,5,5},
                {2,6,100},
                {5,6,7}
        };

        Graph graph = createGraph(matrix);
        ArrayList<Edge> edges = KruskalMST(graph);
        for (Edge edge:edges){
            System.out.println(edge.weight);
        }
    }

    public static void main(String[] args) {
        test();
    }
}
