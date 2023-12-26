package game;

import java.util.*;

public class OlympicTournament {
    ArrayList<Player> players;
    Board board;
    public Random random;
    int numberOfPlayers;
    public OlympicTournament(ArrayList<Player> players, Board board) {
        this.players = players;
        this.board = board;
        numberOfPlayers = players.size();
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }
    int am = getNumberOfPlayers();

    private static int replay(Player player1, Player player2, Board board) {
        int res = new Game(true, player1, player2).play(board);
        while (res <= 0) {
            res = new Game(true, player1, player2).play(board);
        }
        return res;
    }

    private HashMap<Integer, ArrayList<String>> play() {
        HashMap<Integer, ArrayList<String>> stats = new HashMap<>();
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= numberOfPlayers; i++) {
            numbers.add(i);
        }
        int currPlace = (int) (Math.log(numberOfPlayers) / Math.log(2)) + 1;
        while (players.size() > 1) {
            ArrayList<Player> winners = new ArrayList<>();
            ArrayList<String> playersPlace = new ArrayList<>();
            for (int i = 0; i < players.size() - players.size() % 2; i += 2) {
                Collections.shuffle(players);
                Player player1 = players.get(i);
                Player player2 = players.get(i + 1);
                int currResult = replay(player1, player2, board);
                if (currResult == 1) {
                    winners.add(player1);
                    playersPlace.add("Player" + numbers.get(i));
                    numbers.remove(i + 1);
                } else {
                    winners.add(player2);
                    playersPlace.add("Player" + numbers.get(i + 1));
                    numbers.remove(i);
                }
            }
            if (players.size() % 2 != 0) {
                winners.add(players.get(players.size() - 1));
            }
            stats.put(currPlace, playersPlace);
            players = winners;
            currPlace--;
        }
        return stats;
    }

    public void getResult() {
        HashMap<Integer, ArrayList<String>> leaderBoard = play();
        for (Map.Entry<Integer, ArrayList<String>> place : leaderBoard.entrySet()) {
            String p = place.getValue().toString();
            p = p.substring(1, p.length() - 1);
            System.out.println("Place " + place.getKey() + " : " + p);
        }
    }
}
