import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Scanner;
import java.util.Arrays;

public class Reverse {
    
    public static int[] arraySizeIncrease(int[] nums, int size) {
        if (size == nums.length) {
            int[] newNums = new int[size * 3 / 2 + 1];
            System.arraycopy(nums, 0, newNums, 0, nums.length);
            nums = newNums;
        }
        return nums;
    }

    public static int[][] matrixSizeIncrease(int[][] nums, int size) {
        if (size == nums.length) {
            int[][] newNums = new int[nums.length * 3 / 2 + 1][1];
            for (int i = 0; i < nums.length; i++) {
                newNums[i] = new int[nums[i].length];
                System.arraycopy(nums[i], 0, newNums[i], 0, nums[i].length);
            }
            nums = newNums;
        }
        return nums;
    }

    public static void main(String[] args) throws IOException {
        int[][] nums = new int[1][1];
        int[] lengths = new int[1];
        FastScanner input = new FastScanner(System.in);
        int j = 0;
        while (input.hasNextLine()) {
            String str = input.nextLine();
            FastScanner scanner = new FastScanner(str);
            while (scanner.hasNext()) {
                nums[j] = arraySizeIncrease(nums[j], lengths[j]);
                nums[j][lengths[j]] = scanner.nextInt();
                lengths[j]++;
            }
            j++;
            nums = matrixSizeIncrease(nums, j);
            lengths = arraySizeIncrease(lengths, j);
        }
        for (int k = j - 1; k >= 0; k--) {
            for (int i = lengths[k] - 1; i >= 0; i--) {
                System.out.print(nums[k][i] + " ");
            }
            System.out.println();
        }
    }
}
