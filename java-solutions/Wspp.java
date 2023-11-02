import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Wspp {
    public static void main(String[] args) throws IOException {
        int currIndex = 0;
        Map<String, ArrayList<Integer>> wordsOccur = new LinkedHashMap<>();
        FastScanner input = new FastScanner(new FileReader(args[0], StandardCharsets.UTF_8));
        while (input.hasNext()) {
            String currWord = input.nextWord();
            if (currWord.length() != 0) {
                currIndex++;
                if (wordsOccur.containsKey(currWord.toLowerCase())) {
                    wordsOccur.get(currWord.toLowerCase()).add(currIndex);
                } else {
                    wordsOccur.put(currWord.toLowerCase(), new ArrayList<>());
                    wordsOccur.get(currWord.toLowerCase()).add(currIndex);
                }
            }
        }
        input.close();
        try (BufferedWriter output = new BufferedWriter(new FileWriter(args[1], StandardCharsets.UTF_8))) {
            for (Map.Entry<String, ArrayList<Integer>> pair : wordsOccur.entrySet()) {
                output.write(pair.getKey() + " " + pair.getValue().size());
                for (int i = 0; i < pair.getValue().size(); i++) {
                    output.write(" " + pair.getValue().get(i));
                }
                output.write(System.lineSeparator());
            }
        } catch (IOException ex) {
            System.err.println("Output file does not exist: " + ex.getMessage());
        }
    }
}