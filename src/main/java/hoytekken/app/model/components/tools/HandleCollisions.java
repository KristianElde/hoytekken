package hoytekken.app.model.components.tools;

import hoytekken.app.model.components.player.PlayerType;

public interface HandleCollisions {

    /**
     * Method to handle the collision between the player and the ground
     * 
     * @return true if the collision was handled, false otherwise
     */
    public boolean resetDoubleJump(PlayerType player);

    /**
     * Method to generate a new powerup
     */
    public void generatePowerUp();

}
