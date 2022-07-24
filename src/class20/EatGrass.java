package class20;

public class EatGrass {
    /**
     *
     * @param n 青草数量
     * @return "先手" ”后手“
     */
    public static String winner1(int n) {
        if (n < 5) {
            return (n == 0 || n == 2) ? "后手" : "先手";
        }
        //先手吃草的数量
        int base = 1;
        while (base <= n) {
            if (winner1(n - base).equals("后手")) {
                return "先手";
            }
            if (base > n /4) {
                break;
            }
            base = base * 4;
        }
        return "后手";
    }

    public static String winner2(int n ) {
        if (n % 5 == 0 || n % 5 == 2) {
            return "后手";
        }
        return "先手";
    }

    public static void main(String[] args) {
        for (int i = 0; i <= 50; i++) {
            if (winner1(i) != winner2(i)) {
                System.out.println("Oops");
            }
            //System.out.println(i + " : " + winner1(i));
        }
    }
}
