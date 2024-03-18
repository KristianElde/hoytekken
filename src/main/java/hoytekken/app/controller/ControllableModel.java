package hoytekken.app.controller;

import java.util.HashMap;

import hoytekken.app.model.components.ForceDirection;
import hoytekken.app.model.components.GameState;
import hoytekken.app.model.components.player.PlayerType;

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
     * @return the force direction of the given player
     * @throws IllegalArgumentException if the player is not found
     */
    public ForceDirection getDirection(PlayerType player) throws IllegalArgumentException;

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
    public boolean performAttackAction(PlayerType player, ActionType action);

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

    /**
     * Method to get the maps for the game
     * 
     * @return the maps for the game
     */
    public HashMap<String, String> getGameMaps();

    /**
     * Method to set the map for the game
     * 
     * @param mapName the name of the map
     */
    public void setGameMap(String mapName);

    void activateBlock(PlayerType player);
}
