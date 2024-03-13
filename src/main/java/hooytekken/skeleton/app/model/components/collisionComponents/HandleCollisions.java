package hooytekken.skeleton.app.model.components.collisionComponents;

import hooytekken.skeleton.app.model.components.PlayerEntity.PlayerType;

import hooytekken.skeleton.app.model.components.PlayerEntity.PlayerType;
import hooytekken.skeleton.app.model.components.PlayerEntity.PlayerType;


public interface HandleCollisions {
    
    /**
     * Method to handle the collision between the player and the ground
     * 
     * @return true if the collision was handled, false otherwise
     */
    public boolean resetDoubleJump(PlayerType player);
}
