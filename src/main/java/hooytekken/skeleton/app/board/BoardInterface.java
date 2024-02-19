package hooytekken.skeleton.app.board;

public interface BoardInterface<T> extends BoardDimensions, Iterable<Tile<T>> {

    /**
     * Returns the tile value at the given position.
     * @param pos The position of the tile.
     * @return The tile at the given position.
     * @throws IndexOutOfBoundsException if the position is out of bounds.
     */
    T getTile(TilePosition pos);

    /**
     * Sets the value of the tile at the given position.
     * @param pos The position of the tile.
     * @param value The value to set.
     * @throws IndexOutOfBoundsException if the position is out of bounds.
     */
    void setTile(TilePosition pos, T value);

    /**
     * Returns whether the given position is in bounds.
     * @param pos The position to check.
     * @return Whether the position is in bounds.
     */
    Boolean positionIsInBounds(TilePosition pos);
    
    /**
     * Returns whether the given position is empty.
     * @param pos The position to check.
     * @return Whether the position is empty.
     * @throws IndexOutOfBoundsException if the position is out of bounds.
     */
    Boolean isTileEmpty(TilePosition pos);
}
