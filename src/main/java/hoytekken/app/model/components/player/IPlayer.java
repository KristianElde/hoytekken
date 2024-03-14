package hoytekken.app.model.components.player;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Interface defining common behavior for a player in the game.
 */
public interface IPlayer extends ICombat {

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
     * Retrieves the health of the player.
     * 
     * @return the health of the player
     */
    int getHealth();

    /**
     * Checks if the player is alive.
     * 
     * @return true if the player is alive, false otherwise
     */
    boolean isAlive();

    boolean fallenOffTheMap();

    /**
     * Draws the player on the screen.
     * 
     * @param batch the batch to draw the player on
     */
    void draw(Batch batch);

}
