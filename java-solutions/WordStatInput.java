import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.NoSuchFileException;
import java.util.LinkedHashMap;
import java.util.Map;

public class WordStatInput {
    
    public static boolean isLegalCharacter(char ch) {
        return (Character.getType(ch) == Character.DASH_PUNCTUATION || Character.isLetter(ch) || ch == '\'');
    }

    public static void main(String[] args) {
        Map<String, Integer> wordsOccur = new LinkedHashMap<>();
        StringBuilder currWord = new StringBuilder();
        try (BufferedReader input = new BufferedReader(new FileReader(args[0], StandardCharsets.UTF_8))) {
            while (input.ready()) {
                char currSymbol = (char) input.read();
                if (isLegalCharacter(currSymbol)) {
                    currWord.append(currSymbol);
                } else {
                    if (currWord.length() != 0) {
                        if (wordsOccur.containsKey(currWord.toString().toLowerCase())) {
                            wordsOccur.put(currWord.toString().toLowerCase(), wordsOccur.get(currWord.toString().toLowerCase()) + 1);
                        } else {
                            wordsOccur.put(currWord.toString().toLowerCase(), 1);
                        }
                    }
                    currWord.setLength(0);
                }
            }
        } catch (IOException ex) {
            System.err.println("Input file does not exist: " + ex.getMessage());
        }

        try (BufferedWriter output = new BufferedWriter(new FileWriter(args[1], StandardCharsets.UTF_8))) {
            for (Map.Entry<String, Integer> pair : wordsOccur.entrySet()) {
                output.write(pair.getKey() + " " + pair.getValue() + System.lineSeparator());
            }
        } catch (IOException ex) {
            System.err.println("Output file does not exist: " + ex.getMessage());
        }
    }
}
