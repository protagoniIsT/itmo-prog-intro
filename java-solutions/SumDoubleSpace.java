import java.lang.StringBuilder;
public class SumDoubleSpace {
    public static void main(String[] args) {
        Double totalSum = 0.0;
        for (String arg : args) {
            StringBuilder curr = new StringBuilder();
            for (int j = 0; j < arg.length(); j++) {
                if (Character.getType(arg.charAt(j)) == Character.SPACE_SEPARATOR) {
                    if (!curr.isEmpty()) {
                        totalSum += Double.parseDouble(curr.toString());
                        curr.delete(0, curr.length());
                    }
                } else {
                    curr.append(arg.charAt(j));
                }
            }
            if (!curr.isEmpty()) {
                totalSum += Double.parseDouble(curr.toString());
                curr.delete(0, curr.length());
            }
        }
        System.out.println(totalSum);
    }
}