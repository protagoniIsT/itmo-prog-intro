package game;

import java.io.PrintStream;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private final PrintStream out;
    private final Scanner input;

    public HumanPlayer(final PrintStream out, final Scanner in) {
        this.out = out;
        this.input = in;
    }

    public HumanPlayer() {
        this(System.out, new Scanner(System.in));
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            out.println("Position");
            out.println(position);
            out.println(cell + "'s move");
            out.println("Enter row and column");
            String row = input.next();
            String col = input.next();
            try {
                final Move move = new Move(Integer.parseInt(row) - 1, Integer.parseInt(col) - 1, cell);
                if (position.isValid(move)) {
                    return move;
                }
                out.println("Move " + move + " is invalid");
            } catch (NumberFormatException e) {
                System.out.println("Got something non-numeric. \nTry again");
            }
        }
    }
}
