package class12;

// 题目
// 数组arr代表每一个咖啡机冲一杯咖啡的时间，每个咖啡机只能串行的制造咖啡。
// 现在有n个人需要喝咖啡，只能用咖啡机来制造咖啡。
// 认为每个人喝咖啡的时间非常短，冲好的时间即是喝完的时间。
// 每个人喝完之后咖啡杯可以选择洗或者自然挥发干净，只有一台洗咖啡杯的机器，只能串行的洗咖啡杯。
// 洗杯子的机器洗完一个杯子时间为a，任何一个杯子自然挥发干净的时间为b。
// 四个参数：arr, n, a, b
// 假设时间点从0开始，返回所有人喝完咖啡并洗完咖啡杯的全部过程结束后，至少来到什么时间点。
public class Coffee {
    /**
     *
     * @param drinks 每一个员工喝完的时间 固定值
     * @param a 洗一杯的时间 固定变量
     * @param b 自己挥发干净的时间 固定变量
     * @param index 开始的编号
     * @param washLine 洗的机器何时可用
     * @return drinks[0..index - 1] 都已经变干净 不用操心 drinks[index..]都想变干净 这是我操心的 washLine表示洗的机器何时可用 返回最少时间
     */
    public static int process(int[] drinks, int a, int b, int index, int washLine) {
        if (index == drinks.length - 1) {
            return Math.min(Math.max(washLine, drinks[index]) + a, drinks[index] + b);
        }
        //wash是当前的咖啡杯洗碗的时间
        int wash = Math.max(washLine, drinks[index]) + a;
        int next1 = process(drinks, a, b, index + 1, wash);
        int p1 = Math.max(wash, next1);
        //dry是当前杯自然挥发的时间
        int dry = drinks[index] + b;
        int next2 = process(drinks, a, b, index + 1, washLine);
        int p2 = Math.max(dry, next2);
        return Math.min(p1, p2);

    }
}
