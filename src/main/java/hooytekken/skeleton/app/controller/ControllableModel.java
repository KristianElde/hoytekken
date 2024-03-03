package hooytekken.skeleton.app.controller;

/**
 * Interface for the model that can be controlled
 */
public interface ControllableModel {
    
    /**
     * Set the direction of the given player
     * @param player the player to set the direction for
     * @param direction the direction to apply force
     * @return true if the direction was set, false otherwise
     */
    public boolean setDirection(int player, int direction);

    /**
     * Make the given player jump
     * @param player the player to make jump
     * @return true if the player jumped, false otherwise
     */
    public boolean jump(int player);
}
