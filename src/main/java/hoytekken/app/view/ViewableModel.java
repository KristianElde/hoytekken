package hoytekken.app.view;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;

import hoytekken.app.model.components.GameState;
import hoytekken.app.model.components.player.IPlayer;
import hoytekken.app.model.components.player.PlayerType;
import hoytekken.app.model.components.player.ViewablePlayer;
import hoytekken.app.model.components.powerup.PowerUp;

/**
 * Interface for the viewable model
 */
public interface ViewableModel {

    /**
     * Updates the model
     * 
     * @param dt time slice float
     */
    public void updateModel(float dt);

    /**
     * Getter for the game world
     * 
     * @return the game world
     */
    public World getGameWorld();

    /**
     * Getter for the player
     * 
     * @param player the player number
     * @return the player
     */
    public ViewablePlayer getPlayer(PlayerType player);

    /**
     * Getter for the map
     * 
     * @return the map path string
     */
    public String getMap();

    /**
     * Getter for the tiled map
     * 
     * @return the tiled map
     */
    public TiledMap getTiledMap();

    /**
     * Gets the gamestate that the game is currently in
     * 
     * @return a GameState-object that represents the current gamestate
     */
    public GameState getGameState();

}
