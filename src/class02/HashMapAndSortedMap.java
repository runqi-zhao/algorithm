package class02;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

public class HashMapAndSortedMap {
    public static void main(String[] args) {
        HashMap<Integer,String> map = new HashMap<>();
        //HashMap按值传递
        //Hash表在非基础类型时 不是按值传递
        map.put(1,"我是1");
        map.put(2,"我是2");
        map.put(3,"我是3");
        map.put(4,"我是4");
        map.put(5,"我是5");
        map.put(6,"我是6");

        System.out.println(map.containsKey(1));
        System.out.println(map.get(4));

        map.put(4, "他是4");
        System.out.println(map.get(4));

        map.remove(4);
        System.out.println(map.get(4));

        //key
        HashSet<String> set = new HashSet<>();
        set.add("abc");
        set.contains("abc");
        set.remove("abc");

        //哈希表 增删改查 使用时 O(1)

        System.out.println("===========");
        int a = 10000;
        int b = 10000;
        System.out.println(a == b);

        Integer c = 10000;
        Integer d = 10000;
        System.out.println(c == d);
        System.out.println(c.equals(d));

        //-128 ~ 127 按值传递 否则按引用
        Integer e = 127;
        Integer f = 127;
        System.out.println(e == f);

        System.out.println("==========");

        TreeMap<Integer, String> treeMap = new TreeMap<>();

        treeMap.put(1,"我是1");
        treeMap.put(3,"我是3");
        treeMap.put(2,"我是2");
        treeMap.put(4,"我是4");
        treeMap.put(5,"我是5");
        treeMap.put(7,"我是7");
        treeMap.put(8,"我是8");

        System.out.println(treeMap.containsKey(1));
        System.out.println(treeMap.get(4));

        treeMap.put(4, "他是4");
        System.out.println(treeMap.get(4));

        treeMap.remove(4);
        System.out.println(treeMap.get(4));

        System.out.println(treeMap.firstKey());
        System.out.println(treeMap.lastKey());

        //<=5
        System.out.println(treeMap.floorKey(5));
        //>=5
        System.out.println(treeMap.ceilingKey(5));

        //有序表 增删改查 使用时 O(logN)
        //有序表 非基础类型 需要自己写比较器才能实现
    }
}
