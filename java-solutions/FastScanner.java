import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.InputMismatchException;

public class FastScanner {
    private final Reader input;
    private final int BUFFER_CAPACITY = 1024;
    private char[] charBuffer = new char[BUFFER_CAPACITY];
    private int index = 0;
    private int indicator;

    public FastScanner(Reader reader) throws IOException {
        this.input = reader;
        fillBuffer();
    }

    public FastScanner(File fileStream) throws FileNotFoundException, IOException {
        this(new InputStreamReader(new FileInputStream(fileStream), StandardCharsets.UTF_8));
    }

    public FastScanner(InputStream stream) throws IOException {
        this(new InputStreamReader(stream));
    }

    public FastScanner(String s) throws IOException {
        this(new StringReader(s));
    }

    public static boolean isLegalCharacter(char c) {
        return (Character.getType(c) == Character.DASH_PUNCTUATION || Character.isLetter(c) || c == '\'' || Character.isDigit(c));
    }

    public static boolean isLegalCharacterForWord(char c) {
        return (Character.getType(c) == Character.DASH_PUNCTUATION || Character.isLetter(c) || c == '\'');
    }

    private void fillBuffer() throws IOException {
        indicator = input.read(charBuffer);
        index = 0;
        if (indicator != -1) {
            charBuffer = Arrays.copyOf(charBuffer, indicator);
        }
    }

    private void reformatBuffer() throws IOException {
        if (index == charBuffer.length) {
            fillBuffer();
        }
    }

    private static boolean isLineSeparator(char c) {
        return (c == '\n') || (c == '\u2028') || (c == '\u2029') ||
                (c == '\u0085') || (c == '\r');
    }

    public boolean hasNext() throws IOException {
        while ((indicator != -1) && !isLegalCharacter(charBuffer[index])) {
            index++;
            reformatBuffer();
        }
        return indicator != -1;
    }

    public boolean hasNextLine() throws IOException {
        return indicator != -1;
    }

    public String next() throws IOException {
        StringBuilder expression = new StringBuilder();
        while (!isLegalCharacter(charBuffer[index]) && indicator != -1) {
            index++;
            reformatBuffer();
        }
        while (isLegalCharacter(charBuffer[index]) && indicator != -1) {
            expression.append(charBuffer[index]);
            index++;
            reformatBuffer();
        }
        return expression.toString();
    }

    public int nextInt() throws IOException {
        try {
            return Integer.parseInt(next());
        } catch (NumberFormatException ex) {
            throw new InputMismatchException();
        }
    }

    public String nextLine() throws IOException {
        StringBuilder line = new StringBuilder();
        while (!isLineSeparator(charBuffer[index]) && indicator != -1) {
            line.append(charBuffer[index]);
            index++;
            reformatBuffer();
        }
        if (charBuffer[index] == '\r') {
            index++;
            reformatBuffer();
            if (charBuffer[index] == '\n') {
                index++;
                reformatBuffer();
            }
        } else {
            index++;
            reformatBuffer();
        }
        return line.toString();
    }

    public String nextWord() throws IOException {
        StringBuilder word = new StringBuilder();
        while (!isLegalCharacterForWord(charBuffer[index]) && indicator != -1) {
            index++;
            reformatBuffer();
        }
        while (isLegalCharacterForWord(charBuffer[index]) && indicator != -1) {
            word.append(charBuffer[index]);
            index++;
            reformatBuffer();
        }
        return word.toString();
    }

    public void close() throws IOException {
        input.close();
    }
}
