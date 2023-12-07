package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int m, n, k, t, am, playerType;
        playerType = -1;
        am = 0;
        ArrayList<Player> players = new ArrayList<>(am);
        boolean needAddMove;
        do {
            try {
                System.out.print("Type m: ");
                String mS = input.next();
                System.out.print("Type n: ");
                String nS = input.next();
                System.out.print("Type k: ");
                String kS = input.next();
                //String needAddMoveS = input.next();
                System.out.print("Do you want to play olympic tournament? [1 = yes/0 = no]: ");
                String wantTournament = input.next();

                m = Integer.parseInt(mS);
                n = Integer.parseInt(nS);
                k = Integer.parseInt(kS);
                if (m <= 0 || n <= 0 || k <= 0) {
                    throw new NumberFormatException();
                }
                t = Integer.parseInt(wantTournament);
                //needAddMove = Integer.parseInt(needAddMoveS) == 1;
                if (t == 1) {
                    System.out.print("Type amount of players: ");
                    am = input.nextInt();
                    for (int i = 0; i < am; i++) {
                        System.out.println("Random player: Type 0\n" +
                                "Human player: Type 1\n" +
                                "Sequential player: Type 2");
                        playerType = input.nextInt();
                        switch (playerType) {
                            case 0: players.add(new RandomPlayer());
                            case 1: players.add(new HumanPlayer());
                            case 2: players.add(new SequentialPlayer());
                        }
                    }
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Input format error.\nTry again:");
                input.nextLine();
            }
        } while (true);

        if (t == 0) {
            final Game game = new Game(false, new HumanPlayer(), new SequentialPlayer());
            int result;
            do {
                result = game.play(new TicTacToeBoard(m, n, k));
                System.out.println("Game result: " + result);
            } while (result == 0);
        } else {
            final OlympicTournament game = new OlympicTournament(players, new TicTacToeBoard(m, n, k));
            game.getResult();
        }
    }
}
