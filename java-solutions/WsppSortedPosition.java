import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class WsppSortedPosition {

    public static void main(String[] args) throws IOException {
        int currLine = 0;
        Map<String, Map<Integer, ArrayList<Integer>>> wordsOccur = new LinkedHashMap<>();
        Map<Integer, Integer> amWordsInLine = new LinkedHashMap<>();
        FastScanner input = new FastScanner(new FileReader(args[0], StandardCharsets.UTF_8));
        while (input.hasNextLine()) {
            String line = input.nextLine(); 
            FastScanner sc = new FastScanner(line);
            currLine++;
            int currIndex = 0;
            while (sc.hasNext()) {
                String currWord = sc.nextWord();
                if (currWord.length() != 0) {
                    currIndex++;
                    if (wordsOccur.containsKey(currWord.toLowerCase())) {
                        if (wordsOccur.get(currWord.toLowerCase()).containsKey(currLine)) {
                        } else {
                            wordsOccur.get(currWord.toLowerCase()).put(currLine, new ArrayList<>());
                        }
                    } else {
                        wordsOccur.put(currWord.toLowerCase(), new HashMap<>());
                        wordsOccur.get(currWord.toLowerCase()).put(currLine, new ArrayList<>());
                    }
                    wordsOccur.get(currWord.toLowerCase()).get(currLine).add(currIndex);
                }
            }
            amWordsInLine.put(currLine, currIndex);
        }
        input.close();
        Map<String, Map<Integer, ArrayList<Integer>>> sortedWordsOccur = new TreeMap<>(wordsOccur);
        try (BufferedWriter output = new BufferedWriter(new FileWriter(args[1], StandardCharsets.UTF_8))) {
            for (Map.Entry<String, Map<Integer, ArrayList<Integer>>> pair : sortedWordsOccur.entrySet()) {
                output.write(pair.getKey() + " ");
                Iterator<Integer> iterator = pair.getValue().keySet().iterator();
                int totalCurrWordFreq = 0;
                while (iterator.hasNext()) {
                    totalCurrWordFreq += pair.getValue().get(iterator.next()).size();
                }
                output.write(String.valueOf(totalCurrWordFreq));
                for (Map.Entry<Integer, ArrayList<Integer>> ent : pair.getValue().entrySet()) {
                    for (int i = 0; i < ent.getValue().size(); i++) {
                        int d = amWordsInLine.get(ent.getKey()) - ent.getValue().get(i) + 1;
                        output.write(" " + ent.getKey() + ":" + d);
                    }
                }
                output.write(System.lineSeparator());
            }
        } catch (IOException ex) {
            System.err.println("Output file does not exist: " + ex.getMessage());
        }
    }
}
