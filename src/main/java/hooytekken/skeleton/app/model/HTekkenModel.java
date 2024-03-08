package hooytekken.skeleton.app.model;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import hooytekken.skeleton.app.controller.ActionType;
import hooytekken.skeleton.app.controller.ControllableModel;
import hooytekken.skeleton.app.model.components.Box2DWorldGenerator;
import hooytekken.skeleton.app.model.components.ForceDirection;
import hooytekken.skeleton.app.model.components.PlayerEntity.IPlayer;
import hooytekken.skeleton.app.model.components.PlayerEntity.Player;
import hooytekken.skeleton.app.model.components.PlayerEntity.PlayerType;
import hooytekken.skeleton.app.view.ViewableModel;

public class HTekkenModel implements ViewableModel, ControllableModel {
    private static final String DEFAULT_MAP = "defaultMap.tmx";
    private World gameWorld;

    private IPlayer player1;
    private IPlayer player2;

    private String map;

    private TmxMapLoader mapLoader;
    private TiledMap tiledmap;

    private ForceDirection p1Direction = ForceDirection.STATIC;
    private ForceDirection p2Direction = ForceDirection.STATIC;

    /**
     * Constructor for the model
     * 
     * @param map string for chosen map
     */
    public HTekkenModel(String map) {
        this.map = map;
        this.gameWorld = new World(new Vector2(0, -20), true);

        this.player1 = new Player(gameWorld, PlayerType.PLAYER_ONE, 99);
        this.player2 = new Player(gameWorld, PlayerType.PLAYER_TWO, 99);

        mapLoader = new TmxMapLoader();
        tiledmap = mapLoader.load(map);

        new Box2DWorldGenerator(gameWorld, tiledmap);
    }

    /**
     * Constructor for the model, uses default map
     */
    public HTekkenModel() {
        this(DEFAULT_MAP);
    }

    @Override
    public void updateModel(float dt) {
        gameWorld.step(1 / 60f, 6, 2);
        movePlayers();
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

    @Override
    public TiledMap getTiledMap() {
        return tiledmap;
    }

    @Override
    public boolean setDirection(int player, ForceDirection direction) {
        if (player == 1) {
            p1Direction = direction;
            return true;
        } else if (player == 2) {
            p2Direction = direction;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean jump(int player) {
        IPlayer p = getPlayer(player);
        p.move(0, 5);
        return true;
    }

    private boolean movePlayers() {
        directionToSpeed(1, p1Direction);
        directionToSpeed(2, p2Direction);
        return true;
    }

    private void directionToSpeed(int player, ForceDirection direction) {
        IPlayer p = getPlayer(player);
        if (direction == ForceDirection.LEFT) {
            p.move(-0.1f, 0);
        } else if (direction == ForceDirection.RIGHT) {
            p.move(0.1f, 0);
        } else if (direction == ForceDirection.STATIC) {
            p.move(0, 0);
        }
    }

    public boolean performAction(int player, ActionType actionType) {
        IPlayer attacker = getPlayer(player);
        IPlayer victim = getPlayer(player == 1 ? 2 : 1);

        // Player class, TODO: Fix this
        Player att = (Player) attacker;
        Player vic = (Player) victim;

        int damage = 0;
        switch (actionType) {
            case KICK:
                damage = 10;
                if (att.kick(vic, damage)) {
                    System.out.println(vic.getHealth() + " health left");
                    return true;
                }
                break;
            case PUNCH:
                damage = 10;
                if (att.punch(vic, damage)) {
                    System.out.println(vic.getHealth() + " health left");
                    return true;
                }
                break;
            case BLOCK:
                damage = 10;
                if (att.block(vic, damage)) {
                    System.out.println(vic.getHealth() + " health left");
                    return true;
                }
                break;
        }
        return false;
    }

}
