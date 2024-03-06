package hooytekken.skeleton.app.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import hooytekken.skeleton.app.model.components.PlayerEntity.IPlayer;
import hooytekken.skeleton.app.model.components.PlayerEntity.Player;
import hooytekken.skeleton.app.model.components.PlayerEntity.PlayerType;
import hooytekken.skeleton.app.view.ViewableModel;

public class HTekkenModel implements ViewableModel {
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
        this.gameWorld = new World(new Vector2(0, -20), true);

        this.player1 = new Player(gameWorld, PlayerType.PLAYER_ONE);
        this.player2 = new Player(gameWorld, PlayerType.PLAYER_TWO);
    }

    /**
     * Constructor for the model, uses default map
     */
    public HTekkenModel() {
        this(DEFAULT_MAP);
    }

    @Override
    public void updateModel(float dt) {
        gameWorld.step(1/60f, 6, 2);
        player1.update(dt);
        player2.update(dt);
    }

    @Override
    public World getGameWorld() {
        return this.gameWorld;
    }

    @Override
    public IPlayer getPlayer(int playerNumber) {
        return playerNumber == 1 ? player1 : player2;
    }

    @Override
    public String getMap() {
        return this.map;
    }
}
