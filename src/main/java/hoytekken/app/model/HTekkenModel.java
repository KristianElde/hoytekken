package hoytekken.app.model;

import java.util.HashMap;

import javax.swing.Box;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import hoytekken.app.Hoytekken;
import hoytekken.app.controller.ActionType;
import hoytekken.app.controller.ControllableModel;
import hoytekken.app.model.components.ForceDirection;
import hoytekken.app.model.components.GameState;
import hoytekken.app.model.components.player.IPlayer;
import hoytekken.app.model.components.player.Player;
import hoytekken.app.model.components.player.PlayerType;
import hoytekken.app.model.components.player.ViewablePlayer;
import hoytekken.app.model.components.powerup.ActivePowerUp;
import hoytekken.app.model.components.powerup.PowerUp;
import hoytekken.app.model.components.powerup.RandomPowerUpFactory;
import hoytekken.app.model.components.tools.Box2DWorldGenerator;
import hoytekken.app.model.components.tools.CollisionDetector;
import hoytekken.app.model.components.tools.HandleCollisions;
import hoytekken.app.view.ViewableModel;
import net.bytebuddy.dynamic.TypeResolutionStrategy.Active;

/**
 * The model for the game
 */
public class HTekkenModel implements ViewableModel, ControllableModel, HandleCollisions {
    private static final String DEFAULT_MAP = "defaultMap.tmx";
    private static final Vector2 GRAVITY_VECTOR = new Vector2(0, -14);
    private static final int MAX_JUMPS = 2;
    private int playerOneJumpCounter = 0;
    private int playerTwoJumpCounter = 0;
    private HashMap<String, String> gameMaps = new HashMap<String, String>() {
        {
            put("map1", "defaultMap.tmx");
            put("map2", "secondKMVmap.tmx");
            put("map3", "thirdKMVmap.tmx");
            put("map4", "fourthKMVmap1.tmx");
        }
    };

    private World gameWorld;
    private GameState gameState;

    private IPlayer playerOne;
    private IPlayer playerTwo;

    private String map;

    private TmxMapLoader mapLoader;
    private TiledMap tiledmap;

    private ForceDirection p1Direction = ForceDirection.STATIC;
    private ForceDirection p2Direction = ForceDirection.STATIC;

    private ActivePowerUp activePowerUp;

    /**
     * Constructor for the model
     * 
     * @param map string for chosen map
     */
    public HTekkenModel(String map) {
        this.map = map;
        this.gameWorld = new World(GRAVITY_VECTOR, true);
        this.gameState = GameState.MAIN_MENU;

        this.playerOne = new Player(gameWorld, PlayerType.PLAYER_ONE, 99);
        this.playerTwo = new Player(gameWorld, PlayerType.PLAYER_TWO, 99);

        mapLoader = new TmxMapLoader();
        // tiledmap = mapLoader.load(map);

        // new Box2DWorldGenerator(gameWorld, tiledmap);

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
    public IPlayer getPlayer(PlayerType player) {
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
        if (getPlayer(player).getIsBlocking()) {
            return false;
        }
        if (playerOneJumpCounter < MAX_JUMPS && player == PlayerType.PLAYER_ONE) {
            playerOneJumpCounter++;
            IPlayer p1 = getPlayer(player);
            p1.move(0, p1.getJumpingHeight());
            return true;
        } else if (playerTwoJumpCounter < MAX_JUMPS && player == PlayerType.PLAYER_TWO) {
            playerTwoJumpCounter++;
            IPlayer p2 = getPlayer(player);
            p2.move(0, p2.getJumpingHeight());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean resetDoubleJump(PlayerType player) {
        if (player == PlayerType.PLAYER_ONE) {
            playerOneJumpCounter = 0;
        } else {
            playerTwoJumpCounter = 0;
        }
        return true;
    }

    private boolean movePlayers() {
        directionToSpeed(PlayerType.PLAYER_ONE, p1Direction);
        directionToSpeed(PlayerType.PLAYER_TWO, p2Direction);
        return true;
    }

    private void directionToSpeed(PlayerType player, ForceDirection direction) {
        IPlayer p = getPlayer(player);
        if (direction == ForceDirection.LEFT) {
            p.move(-0.5f, 0);
        } else if (direction == ForceDirection.RIGHT) {
            p.move(0.5f, 0);
        } else if (direction == ForceDirection.STATIC) {
            p.move(0, 0);
        }
    }

    @Override
    public boolean performAttackAction(PlayerType attacker, ActionType actionType) {
        IPlayer attackingPlayer = getPlayer(attacker);
        IPlayer victimPlayer = attacker == PlayerType.PLAYER_ONE ? playerTwo : playerOne;

        switch (actionType) {
            case KICK:
                if (attackingPlayer.kick(victimPlayer)) {
                    System.out.println(victimPlayer.getHealth() + " health left");
                    return true;
                }
                break;
            case PUNCH:
                if (attackingPlayer.punch(victimPlayer)) {
                    System.out.println(victimPlayer.getHealth() + " health left");
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

    /**
     * Retrieves the jump count for player one. Only used for test purposes.
     * 
     * @return the current jump count for player one
     */
    int getJumpCounter(PlayerType player) {
        if (player == PlayerType.PLAYER_ONE) {
            return playerOneJumpCounter;
        } else if (player == PlayerType.PLAYER_TWO) {
            return playerTwoJumpCounter;
        } else {
            throw new IllegalArgumentException("Player: " + player + " not found");
        }
    }

    @Override
    public HashMap<String, String> getGameMaps() {
        return this.gameMaps;
    }

    @Override
    public void setGameMap(String mapName) {
        String gameMap = gameMaps.get(mapName);
        if (gameMap != null) {
            this.map = gameMap;
            this.tiledmap = mapLoader.load(gameMap);
            new Box2DWorldGenerator(gameWorld, tiledmap);
        } else {
            throw new IllegalArgumentException("Map: " + mapName + " not found");
        }
    }
}
