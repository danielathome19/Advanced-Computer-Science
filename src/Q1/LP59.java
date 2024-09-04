package Q1;

public class LP59 {
    public static void main(String[] args) {
        System.out.println("x^1\t\tx^2\t\tx^3\t\tx^4\t\tx^5");
        for (int x = 1; x < 7; x++) {
            int x2 = (int)Math.pow(x, 2);
            int x3 = (int)Math.pow(x, 3);
            int x4 = (int)Math.pow(x, 4);
            int x5 = (int)Math.pow(x, 5);
            System.out.printf("%d\t\t%d\t\t%d\t\t%d\t\t%d\n", x, x2, x3, x4, x5);
        }
    }
}
/*
x^1		x^2		x^3		x^4		x^5
1		1		1		1		1
2		4		8		16		32
3		9		27		81		243
4		16		64		256		1024
5		25		125		625		3125
6		36		216		1296		7776
*/