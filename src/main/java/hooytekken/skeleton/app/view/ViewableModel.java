package hooytekken.skeleton.app.view;

import com.badlogic.gdx.physics.box2d.World;

import hooytekken.skeleton.app.model.components.PlayerEntity.IPlayer;

public interface ViewableModel {
    
    /**
     * Updates the model
     * @param dt time slice float
     */
    public void updateModel(float dt);

    /**
     * Getter for the game world
     * @return the game world
     */
    public World getGameWorld();

    /**
     * Getter for the player
     * @param playerNumber the player number
     * @return the player
     */
    public IPlayer getPlayer(int playerNumber);
}
