package hooytekken.skeleton.app.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import hooytekken.skeleton.app.model.components.PlayerEntity.IPlayer;
import hooytekken.skeleton.app.model.components.PlayerEntity.Player;
import hooytekken.skeleton.app.model.components.PlayerEntity.PlayerType;

public class HTekkenModel {
    private static final String DEFAULT_MAP = "defaultMap.tmx";
    private World gameWorld;

    private IPlayer player1;
    private IPlayer player2;

    private String map;

    /**
     * Constructor for the model
     * @param map string for chosen map
     */
    public HTekkenModel(String map) {
        this.map = map;
        gameWorld = new World(new Vector2(0, -20), true);

        player1 = new Player(gameWorld, PlayerType.PLAYER_ONE);
        player2 = new Player(gameWorld, PlayerType.PLAYER_TWO);
    }

    /**
     * Constructor for the model
     */
    public HTekkenModel() {
        this(DEFAULT_MAP);
    }
}
