package hoytekken.app.model.components.player;

import com.badlogic.gdx.graphics.g2d.Batch;

public interface ViewablePlayer {
    
    /**
     * Retrieves the health of the player.
     * 
     * @return the health of the player
     */
    int getHealth();

    /**
     * Retrieves the number of lives the player has.
     * 
     * @return the number of lives the player has
     */
    int getLives();

    /**
     * Draws the player on the screen.
     * 
     * @param batch the batch to draw the player on
     */
    void draw(Batch batch);
}
