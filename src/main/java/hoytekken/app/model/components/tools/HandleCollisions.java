package hoytekken.app.model.components.tools;

import hoytekken.app.model.components.player.PlayerType;
import hoytekken.app.model.components.powerup.ActivePowerUp;

public interface HandleCollisions {

    /**
     * Method to handle the collision between the player and the ground
     * 
     * @return true if the collision was handled, false otherwise
     */
    public boolean resetDoubleJump(PlayerType player);


    public void destroyPowerUpList();

    public void applyPowerUp(PlayerType player, ActivePowerUp powerUp);

    public ActivePowerUp getActivePowerUp();
}
