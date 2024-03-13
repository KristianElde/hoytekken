package hooytekken.skeleton.app.model.components.collisionComponents;

public interface HandleCollisions {
    
    /**
     * Method to handle the collision between the player and the ground
     * 
     * @return true if the collision was handled, false otherwise
     */
    public boolean resetDoubleJump();
}
