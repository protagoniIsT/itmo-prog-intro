import java.util.Scanner;
import java.util.Arrays;

public class ReverseMinC {

    public static int result(int[][] nums, int[] lengths, int row, int column) {
        int minValue = Integer.MAX_VALUE;
        for (int i = 0; i <= row; i++) {
            if (column < lengths[i]) {
                minValue = Math.min(minValue, nums[i][column]);
            }
        }
        return minValue;
    }
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

    public static void main(String[] args) {
        int[][] nums = new int[1][1];
        int[] lengths = new int[1];
        Scanner input = new Scanner(System.in);
        int j = 0;
        while (input.hasNextLine()) {
            String str = input.nextLine();
            Scanner scanner = new Scanner(str);
            while (scanner.hasNextInt()) {
                nums[j] = arraySizeIncrease(nums[j], lengths[j]);
                nums[j][lengths[j]] = scanner.nextInt();
                lengths[j]++;
            }
            j++;
            nums = matrixSizeIncrease(nums, j);
            lengths = arraySizeIncrease(lengths, j);
        }

        for (int k = 0; k < j; k++) {
            for (int i = 0; i < lengths[k]; i++) {
                System.out.print(result(nums, lengths, k, i) + " ");
            }
            System.out.println();
        }
    }
}