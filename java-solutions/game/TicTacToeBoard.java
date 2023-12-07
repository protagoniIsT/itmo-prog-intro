package game;

import java.util.Arrays;
import java.util.Map;

public class TicTacToeBoard implements Board, Position {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.EMPTY, '.'
    );

    private final Cell[][] cells;
    private final int m;
    private final int n;
    private final int k;
    private int empty;
    private Cell turn;

    public TicTacToeBoard(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;
        this.empty = m * n;
        this.cells = new Cell[m][n];
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.EMPTY);
        }
        turn = Cell.X;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }
        cells[move.getRow()][move.getColumn()] = move.getValue();
        empty--;
        if (checkWin(move, k)) {
            return Result.WIN;
        }
        if (empty == 0) {
            return Result.DRAW;
        }
        turn = turn == Cell.X ? Cell.O : Cell.X;
        return Result.UNDEFINED;
    }

    private int countValues(Move move, int rowInc, int colInc) {
        int row = move.getRow();
        int col = move.getColumn();
        Cell value = move.getValue();
        int count = 0;
        for (int r = row + rowInc, c = col + colInc; isValidPosition(r, c); r += rowInc, c += colInc) {
            if (cells[r][c] != value) {
                break;
            }
            count++;
        }
        return count;
    }

    private boolean checkWin(Move move, int k) {
        return  countValues(move, 0, -1) +
                countValues(move, 0, 1) >= k - 1 ||
                countValues(move, -1, 0) +
                        countValues(move, 1, 0) >= k - 1 ||
                countValues(move, -1, -1) +
                        countValues(move, 1, 1) >= k - 1 ||
                countValues(move, -1, 1) +
                        countValues(move, 1, -1) >= k - 1;
    }

    protected boolean isValidPosition(int row, int col) {
        return row >= 0 && row < m && col >= 0 && col < n;
    }


    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < m
                && 0 <= move.getColumn() && move.getColumn() < n
                && cells[move.getRow()][move.getColumn()] == Cell.EMPTY
                && turn == getCell();
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(" ");
        for (int i = 0; i < n; i++) {
            sb.append(i + 1);
        }
        for (int r = 0; r < m; r++) {
            sb.append("\n");
            sb.append(r + 1);
            for (int c = 0; c < n; c++) {
                sb.append(SYMBOLS.get(cells[r][c]));
            }
        }
        return sb.toString();
    }

    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }
}
