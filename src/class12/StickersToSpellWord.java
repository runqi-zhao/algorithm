package class12;

import java.util.HashMap;

public class StickersToSpellWord {
    public static int minStickers1(String[] stickers, String target) {
        int n = stickers.length;
        //stickers ->[26][26][26]...
        int[][] map = new int[n][26];
        for (int i = 0; i < n; i++) {
            char[] str = stickers[i].toCharArray();
            for (char ch : str) {
                map[i][ch - 'a']++;
            }
        }
        HashMap<String, Integer> dp = new HashMap<>();
        dp.put("", 0);
        return process(dp, map, target);
    }

    /**
     * 0..N每一个字符串所包含字符的词频统计
     * @param dp 傻缓存，如果rest已经算过了，直接返回dp中的值
     * @param map 贴纸
     * @param rest 剩余的目标
     * @return 0..N每一个字符串所包含字符的词频统计 返回值如果是-1，map中的贴纸 怎么都无法rest
     */
    private static int process(HashMap<String, Integer> dp, int[][] map, String rest) {
        if (dp.containsKey(rest)){
            return dp.get(rest);
        }
        //以下就是正式的递归调用过程
        int ans = Integer.MAX_VALUE;
        int n = map.length; //N种贴纸
        int[] tmap = new int[26]; //tmap 替换rest
        char[] target = rest.toCharArray();
        for (char c: target) {
            tmap[c - 'a']++;
        }
        //map -> tmap
        for (int i = 0; i < n; i++) {
            //枚举当前的第一张贴纸是谁
            //避免死循环
            if (map[i][target[0] - 'a'] == 0) {
                continue;
            }
            StringBuilder sb = new StringBuilder();
            //i贴纸，j枚举a~z字符
            for (int j = 0; j < 26; j++) {
                // j这个字符是target所需要的
                if (tmap[j] > 0) {
                    for (int k = 0; k < Math.max(0, tmap[j] - map[i][j]); k++) {
                        sb.append((char) ('a' + j));
                    }
                }
            }
            //sb -> i
            String s = sb.toString();
            int tmp = process(dp, map, s);
            if (tmp != -1) {
                ans = Math.min(ans, 1 + tmp);
            }
        }
        //ans 系统最大 rest -1
        dp.put(rest, ans == Integer.MAX_VALUE ? -1 : ans);
        return dp.get(rest);
    }

    public static void main(String[] args) {
        String target = "aabbc";
        String[] stickers = {"ab","c","abcd"};
        System.out.println(minStickers1(stickers, target));
    }
}
