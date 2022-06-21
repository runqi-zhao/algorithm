package class09;

import java.util.HashSet;

public class Light {
    public static int minLight(String road) {
        if (road == null || road.length() == 0) {
            return 0;
        }
        return process(road.toCharArray(),0,new HashSet<>());
    }

    private static int process(char[] str, int index, HashSet<Integer> light) {
        if (index == str.length) {
            for (int i = 0; i < str.length; i++) {
                if (str[i] != 'X') {
                    //全部没有照明
                    if (!light.contains(i -1) && !light.contains(i) && !light.contains(i + 1)) {
                        return Integer.MAX_VALUE;
                    }
                }
            }
            return light.size();
        } else {
            int no = process(str, index + 1, light);
            int yes = Integer.MAX_VALUE;
            if (str[index] == '.') {
                light.add(index);
                yes = process (str, index + 1, light);
                light.remove(index);
            }
            return Math.min(yes,no);
        }
    }

    public static int minLight2(String road) {
        char[] str = road.toCharArray();
        int index = 0;
        int light = 0;
        while (index < str.length) {
            if (str[index] == 'X') {
                index++;
            } else {
                light++;
                if (index + 1 == str.length) {
                    break;
                } else {
                    if (str[index + 1] == 'X') {
                        index = index + 2;
                    } else {
                        index = index + 3;
                    }
                }
            }
        }
        return light;
    }



    // for test
    public static String randomString(int len) {
        char[] res = new char[(int) (Math.random() * len) + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = Math.random() < 0.5 ? 'X' : '.';
        }
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        int len = 20;
        int testTime = 100000;
        for (int i = 0; i < testTime; i++) {
            String test = randomString(len);
            int ans1 = minLight(test);
            int ans2 = minLight2(test);
            if (ans1 != ans2) {
                System.out.println("oops!");
            }
        }
        System.out.println("finish!");
    }
}
