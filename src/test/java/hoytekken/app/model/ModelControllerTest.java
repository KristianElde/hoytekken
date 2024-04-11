package hoytekken.app.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;

import hoytekken.app.controller.ControllableModel;
import hoytekken.app.model.components.ForceDirection;
import hoytekken.app.model.components.GameState;
import hoytekken.app.model.components.eventBus.EventBus;
import hoytekken.app.model.components.player.IPlayer;
import hoytekken.app.model.components.player.PlayerType;

public class ModelControllerTest {
    private ControllableModel model;

    @BeforeAll
    static void setUpBeforeAll() {
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        ApplicationListener listener = new ApplicationAdapter() {};
            new HeadlessApplication(listener, config);
            Gdx.gl = mock(GL20.class);
    }

    @BeforeEach
    void setUpBeforeEach() {
        model = new HTekkenModel(new EventBus());
    }

    @Test
    void SanityTest() {
        assertEquals(1, 1, "Sanity check to verify that tests are working.");
        assertNotNull(model, "Model should be initialized.");
        assertNotNull(Gdx.gl, "Mock GL20 object should be initialized.");
    }

    @Test
    void testForceDirection() {
        ForceDirection initialP1 = model.getDirection(PlayerType.PLAYER_ONE);
        ForceDirection initialP2 = model.getDirection(PlayerType.PLAYER_TWO);
        ForceDirection initialExpected = ForceDirection.STATIC;

        //assert initial force direction is STATIC
        assertEquals(initialExpected, initialP1, "Initial force direction should be STATIC.");
        assertEquals(initialExpected, initialP2, "Initial force direction should be STATIC.");

        //assert method sets direction correctly
        model.setDirection(PlayerType.PLAYER_ONE, ForceDirection.LEFT);
        model.setDirection(PlayerType.PLAYER_TWO, ForceDirection.RIGHT);

        ForceDirection p1 = model.getDirection(PlayerType.PLAYER_ONE);
        ForceDirection p2 = model.getDirection(PlayerType.PLAYER_TWO);

        assertEquals(ForceDirection.LEFT, p1, "Player one force direction should be LEFT.");
        assertEquals(ForceDirection.RIGHT, p2, "Player two force direction should be RIGHT.");

        model.setDirection(PlayerType.PLAYER_ONE, ForceDirection.RIGHT);
        model.setDirection(PlayerType.PLAYER_TWO, ForceDirection.LEFT);

        ForceDirection p1Updated = model.getDirection(PlayerType.PLAYER_ONE);
        ForceDirection p2Updated = model.getDirection(PlayerType.PLAYER_TWO);

        assertEquals(ForceDirection.RIGHT, p1Updated, "Player one force direction should be RIGHT.");
        assertEquals(ForceDirection.LEFT, p2Updated, "Player two force direction should be LEFT.");

        //assert method returns true when setting direction
        assertTrue(model.setDirection(PlayerType.PLAYER_ONE, ForceDirection.STATIC));
        assertTrue(model.setDirection(PlayerType.PLAYER_TWO, ForceDirection.STATIC));
    }

    @Test
    void testJump() {
        //assert players can jump
        assertTrue(model.jump(PlayerType.PLAYER_ONE), "Player one should be able to jump.");
        assertTrue(model.jump(PlayerType.PLAYER_TWO), "Player two should be able to jump.");

        //assert players can't jump when blocking
        model.getPlayer(PlayerType.PLAYER_ONE).changeBlockingState();
        model.getPlayer(PlayerType.PLAYER_TWO).changeBlockingState();

        assertFalse(model.jump(PlayerType.PLAYER_ONE), "Player one should not be able to jump while blocking.");
        assertFalse(model.jump(PlayerType.PLAYER_TWO), "Player two should not be able to jump while blocking.");

        model.getPlayer(PlayerType.PLAYER_ONE).changeBlockingState();
        model.getPlayer(PlayerType.PLAYER_TWO).changeBlockingState();

        //assert players can jump a second time
        assertTrue(model.jump(PlayerType.PLAYER_ONE), "Player one should be able to jump a second time.");
        assertTrue(model.jump(PlayerType.PLAYER_TWO), "Player two should be able to jump a second time.");


        //assert players can't jump a third time
        assertFalse(model.jump(PlayerType.PLAYER_ONE), "Player one should not be able to jump a third time.");
        assertFalse(model.jump(PlayerType.PLAYER_TWO), "Player two should not be able to jump a third time.");
    }

    @Test
    void testGameState() {
        //assert gamestate is not null
        assertNotNull(model.getGameState(), "Gamestate should not be null.");
        GameState initialGameState = model.getGameState();
        GameState expectedGameState = GameState.MAIN_MENU;

        //assert initial gamestate is MAIN_MENU
        assertEquals(expectedGameState, initialGameState, "Initial gamestate should be MAIN_MENU.");

        //assert gamestate can be changed
        assertDoesNotThrow(() -> model.setGameState(GameState.ACTIVE_GAME));
        assertEquals(GameState.ACTIVE_GAME, model.getGameState(), "Gamestate should be ACTIVE_GAME.");
    }

    @Test
    void testGetGameMaps() {
        HashMap<String, String> maps = model.getGameMaps();
        assertNotNull(maps, "Game maps should not be null.");
        assertEquals(4, maps.size(), "Game maps should have 4 maps.");
        assertEquals("defaultMap.tmx", maps.get("map1"));
        assertEquals("secondKMVmap.tmx", maps.get("map2"));
        assertEquals("thirdKMVmap.tmx", maps.get("map3"));
        assertEquals("fourthKMVmap1.tmx", maps.get("map4"));
        assertDoesNotThrow(() -> model.getGameMaps());
    }
    
    @Test
    void testSetGameMap() {
        //assert method throws when map is not found
        assertThrows(IllegalArgumentException.class, () -> model.setGameMap("map5"));
        assertDoesNotThrow(() -> model.setGameMap("map1"));
        assertDoesNotThrow(() -> model.setGameMap("map2"));
        assertDoesNotThrow(() -> model.setGameMap("map3"));
        assertDoesNotThrow(() -> model.setGameMap("map4"));
    }

    @Test
    void testGetPlayers() {
        IPlayer p1 = model.getPlayer(PlayerType.PLAYER_ONE);
        IPlayer p2 = model.getPlayer(PlayerType.PLAYER_TWO);
        
        assertNotNull(p1, "Player one should not be null.");
        assertNotNull(p2, "Player two should not be null.");

        assertEquals(99, p1.getHealth(), "Player one should have 99 health.");
        assertEquals(99, p2.getHealth(), "Player two should have 99 health.");

        //skrive tester for metoder IPlayer metoder som skal være tilgengelige
    }

    @Test
    void testChangeBlockingState() {
        IPlayer p1 = model.getPlayer(PlayerType.PLAYER_ONE);
        IPlayer p2 = model.getPlayer(PlayerType.PLAYER_TWO);

        //assert players can change blocking state
        p1.changeBlockingState();
        p2.changeBlockingState();

        assertTrue(p1.getIsBlocking(), "Player one should be blocking.");
        assertTrue(p2.getIsBlocking(), "Player two should be blocking.");

        //assert players can change blocking state back
        p1.changeBlockingState();
        p2.changeBlockingState();

        assertFalse(p1.getIsBlocking(), "Player one should not be blocking.");
        assertFalse(p2.getIsBlocking(), "Player two should not be blocking.");

    }

    @Test 
    void testFallenOffTheMap() {
        IPlayer p1 = model.getPlayer(PlayerType.PLAYER_ONE);
        IPlayer p2 = model.getPlayer(PlayerType.PLAYER_TWO);

        assertDoesNotThrow(() -> p1.fallenOffTheMap());
        assertDoesNotThrow(() -> p2.fallenOffTheMap());
    }

    @Test
    void testFlip() {
        IPlayer p1 = model.getPlayer(PlayerType.PLAYER_ONE);
        IPlayer p2 = model.getPlayer(PlayerType.PLAYER_TWO);

        //assert players can flip
        p1.flipLeft();
        p2.flipRight();

       

        //assert players can flip back
        p1.flipRight();
        p2.flipLeft();

        //assertions???

    }
        // p1.flipLeft();
        // p1.flipRight();
    
    @Test
    void testKick() {
        IPlayer p1 = model.getPlayer(PlayerType.PLAYER_ONE);
        IPlayer p2 = model.getPlayer(PlayerType.PLAYER_TWO);

        assertDoesNotThrow(() -> p1.kick(p2));
        assertDoesNotThrow(() -> p2.kick(p1));
    }

    @Test
    void testPunch() {
        IPlayer p1 = model.getPlayer(PlayerType.PLAYER_ONE);
        IPlayer p2 = model.getPlayer(PlayerType.PLAYER_TWO);

        assertDoesNotThrow(() -> p1.punch(p2));
        assertDoesNotThrow(() -> p2.punch(p1));
    }
    
    @Test
    void testGetBody() {
        IPlayer p1 = model.getPlayer(PlayerType.PLAYER_ONE);
        IPlayer p2 = model.getPlayer(PlayerType.PLAYER_TWO);

        assertNotNull(p1.getBody(), "Player one body should not be null.");
        assertNotNull(p2.getBody(), "Player two body should not be null.");
    }
        // p1.getBody();
        // p1.increaseDamage(0);
        // p1.increaseHealth(0);
        // p1.gainExtraLife();
        // p1.isAlive();
        // p1.getLives();
        // p1.getJumpingHeight();
    
}
