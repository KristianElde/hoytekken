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
     * Method to handle the collision between the player and the powerUp
     * 
     * @return true if the collision was handled, false otherwise
     */
    public void destroyPowerUp();

    /**
     * Method to get the active powerUp
     * 
     * @return the active powerUp
     */
    public ActivePowerUp getActivePowerUp();

    public void applyPowerUp(PlayerType player, ActivePowerUp powerUp);

    public void destroyPowerUpList();
}
