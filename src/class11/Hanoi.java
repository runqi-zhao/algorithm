package class11;

import org.omg.CORBA.PUBLIC_MEMBER;

public class Hanoi {
    public static void hanoi1(int n) {
        leftToRight(n);
    }

    private static void leftToRight(int n) {
        if (n == 1) {
            System.out.println("Move 1 from left to right");
            return;
        }
        leftToMid( n - 1);
        System.out.println("Move " + n + " from left to right");
        midToRight(n - 1);
    }

    private static void midToRight(int n) {
        if (n == 1) {
            System.out.println("Move 1 form mid to right");
            return;
        }
        midToLeft(n - 1);
        System.out.println("Move " + n + " from mid to right");
        leftToRight(n - 1);
    }

    public static void leftToMid(int n) {
        if (n == 1) {
            System.out.println("Move 1 from left to mid");
            return;
        }
        leftToRight(n - 1);
        System.out.println("Move " + n + " from left to mid");
        rightToMid(n - 1);
    }

    private static void rightToMid(int n) {
        if (n == 1) {
            System.out.println("Move " + n + " from right to mid");
            return;
        }
        rightToLeft(n - 1);
        System.out.println("Move " + n + " from right to mid");
        leftToRight(n - 1);
    }

    private static void rightToLeft(int n) {
        if (n == 1) {
            System.out.println("Move 1 right from left");
            return;
        }
        rightToMid(n - 1);
        System.out.println("Move " + n  + " from right to left");
        midToLeft(n - 1);
    }

    private static void midToLeft(int n) {
        if (n == 1) {
            System.out.println("Move 1 from Mid to left");
            return;
        }
        midToRight(n - 1);
        System.out.println("Move " + n + " from Mid to left");
        rightToLeft(n - 1);
    }

    public static void hanoi2(int n) {
        if (n > 0) {
            func(n, "left", "mid", "right");
        }
    }

    private static void func(int n, String from, String other, String to) {
        if (n == 1) {
            System.out.println("Move 1 from " + from + " to " + to);
        } else {
            func(n -1, from, to, other);
            System.out.println("Move " + n + " from " + from + " to " + to);
            func(n - 1, other, from, to);
        }
    }


    public static void main(String[] args) {
        int n = 100;
        hanoi1(n);
        System.out.println("============");
        hanoi2(n);
        System.out.println("============");
    }
}
