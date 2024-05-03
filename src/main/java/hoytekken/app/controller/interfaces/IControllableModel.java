package hoytekken.app.controller.interfaces;

import hoytekken.app.controller.enums.ActionType;
import hoytekken.app.model.IViewAndControl;
import hoytekken.app.model.components.ForceDirection;
import hoytekken.app.model.components.player.enums.PlayerType;
import hoytekken.app.model.components.player.interfaces.IPlayer;

/**
 * Interface for the model that can be controlled
 */
public interface IControllableModel extends IViewAndControl {

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
     * @param attacker the player to perform the action
     * @param action   the action to perform
     * 
     * @return true if the action was performed, false otherwise
     */
    boolean performAttackAction(PlayerType attacker, ActionType action);

    /**
     * Get the player of the given type
     * 
     * @param player the player type to get
     * @return the player of the given type
     */
    IPlayer getPlayer(PlayerType player);
}
