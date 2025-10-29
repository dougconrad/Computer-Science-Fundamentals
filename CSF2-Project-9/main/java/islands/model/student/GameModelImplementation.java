package islands.model.student;

import islands.model.GameModel;
import islands.model.Graph;
import islands.model.TileColor;

/**
 * A concrete representation of the state of a game of Islands of Hex.
 */
public class GameModelImplementation implements GameModel {
    private final int size;
    private final TileColor[][] board;

    /**
     * Constructs a model with an empty game board with the specified
     * number of rows and columns.
     *
     * @param size the number of rows (and the number of columns) on the board
     */
    public GameModelImplementation(int size) {
        this.size = size;
        this.board = new TileColor[size][size];
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public TileColor getTileColor(int row, int col) {
        return board[row][col];
    }

    @Override
    public boolean canPlay(int row, int col) throws IllegalArgumentException {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new IllegalArgumentException("Row or column is out of bounds.");
        }
        return board[row][col] == null;
    }

    @Override
    public void makePlay(int row, int col, TileColor tileColor) {
        if (canPlay(row, col)) {
            board[row][col] = tileColor;
        }
    }

    @Override
    public int getScore(TileColor tileColor) {
        return 0;
    }

    @Override
    public boolean isGameOver() {
        return false;
    }

    @Override
    public String getBoardString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                sb.append(getTileColor(row, col).getAbbreviation());
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
