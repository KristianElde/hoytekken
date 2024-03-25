package hoytekken.app.model.components.player;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Interface defining common behavior for a player in the game.
 */
public interface IPlayer extends ICombat, ViewablePlayer {

    /**
     * Updates the player.
     * 
     */
    void update();

    /**
     * Retrieves the physical body representing the player.
     * 
     * @return the Box2D body of the player
     */
    Body getBody();

    /**
     * Moves the player by applying a force.
     * 
     * @param deltaX the change in the x direction
     * @param deltaY the change in the y direction
     */
    void move(float deltaX, float deltaY);

    /**
     * Inflicts damage on the player.
     * 
     * @param damage the amount of damage to inflict
     */
    void takeDamage(int damage);

    /**
     * Checks if the player is alive.
     * 
     * @return true if the player is alive, false otherwise
     */
    boolean isAlive();

    boolean fallenOffTheMap();

    /**
     * Retrieves the jumping height of the player.
     * 
     * @return an int representing the deltaY of a jump
     */
    int getJumpingHeight();

}
