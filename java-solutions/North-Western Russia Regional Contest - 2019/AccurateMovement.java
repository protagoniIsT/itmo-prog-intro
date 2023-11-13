import java.util.Scanner;

public class AccurateMovement {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] abn = input.nextLine().split(" ");
        double a = Double.parseDouble(abn[0]);
        double b = Double.parseDouble(abn[1]);
        double n = Double.parseDouble(abn[2]);
        System.out.println(Math.round(2 * Math.ceil((n - b)/(b - a)) + 1));
    }
}