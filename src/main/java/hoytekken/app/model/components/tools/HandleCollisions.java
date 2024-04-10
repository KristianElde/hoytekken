package hoytekken.app.model.components.tools;

import hoytekken.app.model.components.player.PlayerType;
import hoytekken.app.model.components.powerup.ActivePowerUp;
import net.bytebuddy.dynamic.TypeResolutionStrategy.Active;

public interface HandleCollisions {

    /**
     * Method to handle the collision between the player and the ground
     * 
     * @return true if the collision was handled, false otherwise
     */
    public boolean resetDoubleJump(PlayerType player);

    /**
     * Method to get the active powerUp
     * 
     * @return the active powerUp
     */
    public ActivePowerUp getActivePowerUp();

    /**
     * Method to apply the powerUp to the player
     * 
     * @param player the player to apply the powerUp to
     * @param powerUp the powerUp to apply
     */
    public void applyPowerUp(PlayerType player, ActivePowerUp powerUp);

    /**
     * Method to destroy the powerUp list
     */
    public void destroyPowerUpList();
}
