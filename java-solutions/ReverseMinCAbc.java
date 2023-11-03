import java.io.IOException;
public class ReverseMinCAbc {

    public static String parseAbcToInt(String arg) {
        StringBuilder number = new StringBuilder();
        int k = 0;
        if (arg.charAt(0) == '-') {
            number.append(arg.charAt(0));
            k = 1;
        }
        for (int i = k; i < arg.length(); i++) {
            number.append((char) (((int) arg.charAt(i)) - 49));
        }
        return number.toString();
    }

    public static String parseIntToAbc(String arg) {
        StringBuilder number = new StringBuilder();
        int k = 0;
        if (arg.charAt(0) == '-') {
            number.append(arg.charAt(0));
            k = 1;
        }
        for (int i = k; i < arg.length(); i++) {
            number.append((char) (((int) arg.charAt(i)) + 49));
        }
        return number.toString();
    }

    public static String[] arraySizeIncrease(String[] nums, int size) {
        if (size == nums.length) {
            String[] newNums = new String[size * 3 / 2 + 1];
            System.arraycopy(nums, 0, newNums, 0, nums.length);
            nums = newNums;
        }
        return nums;
    }

    public static void main(String[] args) throws IOException {
        String[] nums = new String[1];
        FastScanner input = new FastScanner(System.in);
        int lineCounter = 0;
        while (input.hasNextLine()) {
            FastScanner scanner = new FastScanner(input.nextLine());
            lineCounter++;
            int j = 0;
            while (scanner.hasNext()) {
                if (lineCounter == 1) {
                    nums = arraySizeIncrease(nums, j + 1);
                    nums[j] = scanner.next();
                    j++;
                } else {
                    nums = arraySizeIncrease(nums, j + 1);
                    if (nums[j] == null) {
                        nums[j] = scanner.next();
                    } else {
                        nums[j] = parseIntToAbc(String.valueOf(Math.min(Integer.parseInt(parseAbcToInt(scanner.next())),
                                Integer.parseInt(parseAbcToInt(nums[j])))));
                    }
                    j++;
                }
            }
            scanner.close();
            for (int i = 0; i < j ; i++) {
                System.out.print(nums[i] + " ");
            }
            System.out.println();
        }
        input.close();
    }
}
