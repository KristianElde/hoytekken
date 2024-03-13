package hooytekken.skeleton.app.controller;

import hooytekken.skeleton.app.model.components.ForceDirection;
import hooytekken.skeleton.app.model.components.GameState;
import hooytekken.skeleton.app.model.components.PlayerEntity.PlayerType;

/**
 * Interface for the model that can be controlled
 */
public interface ControllableModel {

    /**
     * Set the direction of the given player
     * 
     * @param player    the player to set the direction for
     * @param direction the direction to apply force
     * @return true if the direction was set, false otherwise
     */
    public boolean setDirection(PlayerType player, ForceDirection direction);

    /**
     * Get the force direction of the given player
     * 
     * @param player the player to get the direction for
     * @return the ForceDirection of the player
     */
    public ForceDirection getDirection(PlayerType player);

    /**
     * Make the given player jump
     * 
     * @param player the player to make jump
     * @return true if the player jumped, false otherwise
     */
    public boolean jump(PlayerType player);

    /**
     * Perform actions against the other player
     * 
     * @param player the player to perform the action on
     * @param action the action to perform
     * 
     * @return true if the action was performed, false otherwise
     */
    public boolean performAction(PlayerType player, ActionType action);

    /**
     * Gets the gamestate that the game is currently in
     * 
     * @return a GameState-object that represents the current gamestate
     */
    public GameState getGameState();

    /**
     * Updates the current gamestate
     * 
     * @param gameState is the gamestate that the game gets set to
     */
    public void setGameState(GameState gameState);
}
