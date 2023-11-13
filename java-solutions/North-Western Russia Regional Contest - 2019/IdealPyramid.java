import java.util.Scanner;

public class IdealPyramid {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = Integer.parseInt(input.nextLine());
        int x_l = Integer.MAX_VALUE;
        int x_r = Integer.MIN_VALUE;
        int y_l = Integer.MAX_VALUE;
        int y_r = Integer.MIN_VALUE;
        int h;
        int x;
        int y;

        for (int i = 0; i < n; i++) {
            String[] data = input.nextLine().split(" ");
            int x_i = Integer.parseInt(data[0]);
            int y_i = Integer.parseInt(data[1]);
            int h_i = Integer.parseInt(data[2]);

            x_l = Math.min(x_i - h_i, x_l);
            y_l = Math.min(y_i - h_i, y_l);

            x_r = Math.max(x_i + h_i, x_r);
            y_r = Math.max(y_i + h_i, y_r);
        }

        h = ((Math.max(x_r - x_l, y_r - y_l) + 1) / 2);
        x = (x_l + x_r) / 2;
        y = (y_l + y_r) / 2;
        System.out.println(x + " " + y + " " + h);
    }
}