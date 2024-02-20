package hooytekken.skeleton.app.board;

/**
 * Represents a tile on the board.
 * 
 * @param pos position of the tile.
 * @param value value of the tile.
 */
public record Tile<T>(TilePosition pos, T value) {}
