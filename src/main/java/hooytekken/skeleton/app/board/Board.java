package hooytekken.skeleton.app.board;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a board of tiles.
 * @param <T> The type of the tile values.
 */
public class Board<T> implements BoardInterface<T>{
    private int rows;
    private int cols;
    private T defaultValue;
    private List<List<T>> board;

    /**
     * Creates a new board with the given dimensions and default value.
     * @param rows
     * @param cols
     * @param defaultValue
     */
    public Board(int rows, int cols, T defaultValue) {
        this.rows = rows;
        this.cols = cols;
        this.defaultValue = defaultValue;

        board = new ArrayList<>();

        for (int i = 0; i < rows; i ++) {
            board.add(i, new ArrayList<>());
            for (int j = 0; j < cols; j++){
                board.get(i).add(j, defaultValue);
            }
        }
    }

    /**
     * Creates a new board with the given dimensions and no default value.
     * @param rows
     * @param cols
     */
    public Board(int rows, int cols) {
        this(rows, cols, null);
    }

    @Override
    public int rows() {
        return rows;
    }

    @Override
    public int cols() {
        return cols;
    }

    @Override
    public Iterator<Tile<T>> iterator() {
        List<Tile<T>> tiles = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                tiles.add(new Tile<>(new TilePosition(i, j), board.get(i).get(j)));
            }
        }
        return tiles.iterator();
    }

    @Override
    public T getTile(TilePosition pos) {
        return board.get(pos.row()).get(pos.col());
    }

    @Override
    public void setTile(TilePosition pos, T value) {
        board.get(pos.row()).set(pos.col(), value);
    }

    @Override
    public Boolean positionIsInBounds(TilePosition pos) {
        return pos.row() >= 0 && pos.row() < rows && pos.col() >= 0 && pos.col() < cols;
    }

    @Override
    public Boolean isTileEmpty(TilePosition pos) {
        return getTile(pos) == defaultValue;
    }
    
}
