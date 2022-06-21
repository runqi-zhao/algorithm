package class10;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class UnionFind {
    public static class Node<V> {
        V value;

        public Node (V value) {
            this.value = value;
        }
    }

    public static class UnionSet<V> {
        //记录下来当前结点位置与值
        public HashMap<V,Node<V>> nodes;
        //记录当前结点与其“帮主结点”
        public HashMap<Node<V>, Node<V>> parents;
        //记录下来这个帮派有多大
        public HashMap<Node<V>, Integer> sizeMap;

        public UnionSet (List<V> values) {
            for (V value : values) {
                Node<V> node = new Node<>(value);
                nodes.put(value, node);
                parents.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        /**
         * 找到父节点
         * @param cur 当前结点
         * @return 找到代表结点进行返回
         */
        public Node<V> findFather (Node<V> cur) {
            Stack<Node<V>> path = new Stack<>();
            //向上找到父节点
            while (cur != parents.get(cur)) {
                path.push(cur);
                cur = parents.get(cur);
            }
            //记录下来当前结点
            while (!path.isEmpty()) {
                parents.put(path.pop(),cur);
            }
            return cur;
        }

        public boolean isSameSet (V a, V b) {
            if (!nodes.containsKey(a) || !nodes.containsKey(b)) {
                return false;
            }
            //看是否是同一个代表结点结点
            return findFather(nodes.get(a)) == findFather(nodes.get(b));
        }

        public void union(V a, V b) {
            if (!nodes.containsKey(a) || !nodes.containsKey(b)) {
                return;
            }
            Node<V> aHead = findFather(nodes.get(a));
            Node<V> bHead = findFather(nodes.get(b));
            if (aHead != bHead) {
                int aSetSize = sizeMap.get(aHead);
                int bSetSize = sizeMap.get(bHead);
                Node<V> big = aSetSize >= bSetSize ? aHead : bHead;
                Node<V> small = big == aHead ? bHead : aHead;
                parents.put(small, big);
                sizeMap.put(big, aSetSize + bSetSize);
                sizeMap.remove(small);
            }
        }


    }
}
