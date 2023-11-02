import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class WordStatCount {
    public static boolean isLegalCharacter(char ch) {
        return (Character.getType(ch) == Character.DASH_PUNCTUATION || Character.isLetter(ch) || ch == '\'');
    }
    public static <K, V extends Comparable<V>> Map<K, V> sortByValues(final Map<K, V> map) {
        Comparator<K> valueComparator = new Comparator<K>() {
            @Override
            public int compare(K k1, K k2) {
                int comp = map.get(k1).compareTo(map.get(k2));
                if (comp >= 0) {
                    return 1;
                } else {
                    return comp;
                }
            }
        };
        Map<K, V> sorted = new TreeMap<K, V>(valueComparator);
        sorted.putAll(map);
        return sorted;
    }

    public static void main(String[] args) {
        LinkedHashMap<String, Integer> wordsOccur = new LinkedHashMap<>();
        StringBuilder currWord = new StringBuilder();
        try (BufferedReader input = new BufferedReader(new FileReader(args[0], StandardCharsets.UTF_8))) {
            while (input.ready()) {
                char currSymbol = (char) input.read();
                if (isLegalCharacter(currSymbol)) {
                    currWord.append(currSymbol);
                } else {
                    if (currWord.length() != 0) {
                        wordsOccur.put(currWord.toString().toLowerCase(),
                                wordsOccur.getOrDefault(currWord.toString().toLowerCase(), 0) + 1);
                    }
                    currWord.setLength(0);
                }
            }
        } catch (IOException ex) {
            System.err.println("Input file does not exist: " + ex.getMessage());
        }

        Map<String, Integer> wordsOccurSorted = sortByValues(wordsOccur);

        try (BufferedWriter output = new BufferedWriter(new FileWriter(args[1], StandardCharsets.UTF_8))) {
            for (Map.Entry<String, Integer> pair : wordsOccurSorted.entrySet()) {
                output.write(pair.getKey() + " " + pair.getValue() + System.lineSeparator());
            }
        } catch (IOException ex) {
            System.err.println("Output file does not exist: " + ex.getMessage());
        }
    }
}