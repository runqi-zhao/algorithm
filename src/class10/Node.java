package class10;

import java.util.ArrayList;

/**
 * 点结构的描述
 */
public class Node {
    //值
    public int value;
    //入度
    public int in;
    //出度
    public int out;
    //指向的结点
    public ArrayList<Node> nexts;
    //边得存储
    public ArrayList<Edge> edges;

    public Node (int value) {
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
