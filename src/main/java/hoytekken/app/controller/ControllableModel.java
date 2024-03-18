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
    boolean setDirection(PlayerType player, ForceDirection direction);

    /**
     * Get the force direction of the given player
     * 
     * @param player the player to get the direction for
     * @return the force direction of the given player
     * @throws IllegalArgumentException if the player is not found
     */
    ForceDirection getDirection(PlayerType player) throws IllegalArgumentException;

    /**
     * Make the given player jump
     * 
     * @param player the player to make jump
     * @return true if the player jumped, false otherwise
     */
    boolean jump(PlayerType player);

    /**
     * Perform actions against the other player
     * 
     * @param player the player to perform the action on
     * @param action the action to perform
     * 
     * @return true if the action was performed, false otherwise
     */
    boolean performAttackAction(PlayerType player, ActionType action);

    /**
     * Gets the gamestate that the game is currently in
     * 
     * @return a GameState-object that represents the current gamestate
     */
    GameState getGameState();

    /**
     * Updates the current gamestate
     * 
     * @param gameState is the gamestate that the game gets set to
     */
    void setGameState(GameState gameState);

    /**
     * Method to get the maps for the game
     * 
     * @return the maps for the game
     */
    HashMap<String, String> getGameMaps();

    /**
     * Method to set the map for the game
     * 
     * @param mapName the name of the map
     */
    void setGameMap(String mapName);

    /**
     * Sets the isBlocking variable for specified player to true.
     * 
     * @param player specifies wheter player1 or player2 gets isBlocking-field
     *               variable set to true
     */
    void activateBlock(PlayerType player);

    /**
     * Sets the isBlocking variable for specified player to false.
     * 
     * @param player specifies wheter player1 or player2 gets isBlocking-field
     *               variable set to false
     */
    void deactivateBlock(PlayerType player);
}
