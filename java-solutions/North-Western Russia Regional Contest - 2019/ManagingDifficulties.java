import java.util.HashMap;
import java.util.Scanner;

public class ManagingDifficulties {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int t = Integer.parseInt(input.nextLine());
        for (int k = 0; k < t; k++) {
            int n = Integer.parseInt(input.nextLine());
            String[] d = input.nextLine().split(" ");
            int[] data = new int[d.length];
            for (int l = 0; l < data.length; l++) {
                data[l] = Integer.parseInt(d[l]);
            }

            HashMap<Integer, Integer> c = new HashMap<>();

            int res = 0;
            for (int j = n - 2; j > 0; j--) {
                c.put(data[j + 1], c.getOrDefault(data[j + 1], 0) + 1);
                for (int i = 0; i <= j - 1; i++) {
                    if (c.containsKey(2 * data[j] - data[i])) {
                        res += c.get(2 * data[j] - data[i]);
                    }
                }
            }
            System.out.println(res);
        }
    }
}