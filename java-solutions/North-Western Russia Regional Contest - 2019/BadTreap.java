import java.util.Scanner;

public class BadTreap {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        double minMod = Float.MAX_VALUE;
        int number = 0;

        for (int num = 1; num < (Math.pow(2, 31) - 1)/50000; num++) {
            if (minMod > num % (2 * Math.PI)) {
                minMod = num % (2 * Math.PI);
                number = num;
            }
        }

        for (int a = number * (-25000); n > 0; a += number) {
            n--;
            System.out.println(a);
        }
    }
}