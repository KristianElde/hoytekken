package hoytekken.app.model;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import hoytekken.app.controller.ActionType;
import hoytekken.app.controller.ControllableModel;
import hoytekken.app.model.components.ForceDirection;
import hoytekken.app.model.components.GameState;
import hoytekken.app.model.components.player.IPlayer;
import hoytekken.app.model.components.player.Player;
import hoytekken.app.model.components.player.PlayerType;
import hoytekken.app.model.components.tools.Box2DWorldGenerator;
import hoytekken.app.model.components.tools.CollisionDetector;
import hoytekken.app.model.components.tools.HandleCollisions;
import hoytekken.app.view.ViewableModel;

/**
 * The model for the game
 */
public class HTekkenModel implements ViewableModel, ControllableModel, HandleCollisions {
    private static final String DEFAULT_MAP = "defaultMap.tmx";
    private World gameWorld;
    private GameState gameState;

    private Player playerOne;
    private Player playerTwo;

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
        this.gameState = GameState.INSTRUCTIONS;

        this.playerOne = new Player(gameWorld, PlayerType.PLAYER_ONE, 99);
        this.playerTwo = new Player(gameWorld, PlayerType.PLAYER_TWO, 99);

        mapLoader = new TmxMapLoader();
        tiledmap = mapLoader.load(map);

        new Box2DWorldGenerator(gameWorld, tiledmap);

        this.gameWorld.setContactListener(new CollisionDetector(this));
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
        playerOne.update();
        playerTwo.update();
        if (isGameOver()) {
            setGameState(GameState.GAME_OVER);
        }
    }

    @Override
    public World getGameWorld() {
        return this.gameWorld;
    }

    @Override
    public Player getPlayer(PlayerType player) {
        return player == PlayerType.PLAYER_ONE ? playerOne : playerTwo;
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
    public boolean setDirection(PlayerType player, ForceDirection direction) {
        if (player == PlayerType.PLAYER_ONE) {
            p1Direction = direction;
            return true;
        } else if (player == PlayerType.PLAYER_TWO) {
            p2Direction = direction;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ForceDirection getDirection(PlayerType player) {
        if (player == PlayerType.PLAYER_ONE) {
            return p1Direction;
        } else if (player == PlayerType.PLAYER_TWO) {
            return p2Direction;
        } else {
            throw new IllegalArgumentException("Player: " + player + " not found");
        }
    }

    @Override
    public boolean jump(PlayerType player) {
        IPlayer p = getPlayer(player);
        p.move(0, 5);
        return true;
    }

    private boolean movePlayers() {
        directionToSpeed(PlayerType.PLAYER_ONE, p1Direction);
        directionToSpeed(PlayerType.PLAYER_TWO, p2Direction);
        return true;
    }

    private void directionToSpeed(PlayerType player, ForceDirection direction) {
        Player p = getPlayer(player);
        if (direction == ForceDirection.LEFT) {
            p.move(-0.5f, 0);
        } else if (direction == ForceDirection.RIGHT) {
            p.move(0.5f, 0);
        } else if (direction == ForceDirection.STATIC) {
            p.move(0, 0);
        }
    }

    public boolean performAction(PlayerType player, ActionType actionType) {
        Player attacker = getPlayer(player);
        Player victim = player == PlayerType.PLAYER_ONE ? playerTwo : playerOne;

        int damage = 0;
        switch (actionType) {
            case KICK:
                damage = 10;
                if (attacker.kick(victim, damage)) {
                    System.out.println(victim.getHealth() + " health left");
                    return true;
                }
                break;
            case PUNCH:
                damage = 10;
                if (attacker.punch(victim, damage)) {
                    System.out.println(victim.getHealth() + " health left");
                    return true;
                }
                break;
            case BLOCK:
                damage = 10;
                if (attacker.block(victim, damage)) {
                    System.out.println(victim.getHealth() + " health left");
                    return true;
                }
                break;
        }
        return false;
    }

    @Override
    public GameState getGameState() {
        return gameState;
    }

    @Override
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    private boolean isGameOver() {
        if (playerOne.isAlive() && playerTwo.isAlive())
            return false;
        return true;
    }

    @Override
    public boolean resetDoubleJump(PlayerType player) {
        // TODO: implement method
        return true;
    }

}
