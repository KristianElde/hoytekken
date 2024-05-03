package hoytekken.app.view;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;

import hoytekken.app.model.IViewAndControl;
import hoytekken.app.model.components.player.enums.PlayerType;
import hoytekken.app.model.components.player.interfaces.IViewablePlayer;
import hoytekken.app.model.components.powerup.ActivePowerUp;

/**
 * Interface for the viewable model
 */
public interface ViewableModel extends IViewAndControl {

    /**
     * Updates the model. Takes care of players movements and position, powerups and
     * gamestate
     * 
     * @param dt time slice float
     */
    void updateModel(float dt);

    /**
     * Getter for the game world
     * 
     * @return the game world
     */
    World getGameWorld();

    /**
     * Getter for the player of the given type
     * 
     * @param player the player number
     * @return the player
     */
    IViewablePlayer getPlayer(PlayerType player);

    /**
     * Getter for the tiled map
     * 
     * @return the tiled map
     */
    TiledMap getTiledMap();

    /**
     * Gets the powerup that is currently active
     * 
     * @return the active powerup
     */
    ActivePowerUp getActivePowerUp();

}
