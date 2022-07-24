package class20;

public class AppleMinBags {

    public static int minBags(int apple) {
        if (apple < 0) {
            return -1;
        }
        int bag6 = -1;
        int bag8 = apple /8;
        int res = apple - bag8 * 8;
        while (bag8 >= 0 && res < 24) {
            int restUse6 = minBagBase6(res);
            if (restUse6 != -1) {
                bag6 = restUse6;
                break;
            }
            res = apple - 8 * (--bag8);
        }
        return bag6 == -1 ? -1 : bag6 + bag8;
    }

    // 如果剩余苹果rest可以被装6个苹果的袋子搞定，返回袋子数量
    // 不能搞定返回-1
    private static int minBagBase6(int res) {
        return res % 6 == 0 ? (res /6) : -1;
    }

    public static int minBags2(int n) {
        if ( (n & 1) != 0) {
            return -1;
        }
        if (n < 18) {
            return n == 0 ? 0 : (n == 6 || n == 8) ? 1 : (n == 12 || n == 14 || n == 16) ? 2 : -1;
        }
        return (n - 18) / 8 + 3;
    }

    public static void main(String[] args) {
        for(int apple = 1; apple < 100;apple++) {
            if (minBags(apple) != minBags2(apple)) {
                System.out.println("Oops");
            }
//            System.out.println(apple + " : "+ minBags(apple));
        }
    }
}
