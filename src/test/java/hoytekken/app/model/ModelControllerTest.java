package hoytekken.app.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

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
    void testPerformAttackAction() { 
        //testet i ModelTest.java, legge til flere caser i ModelTest.java for spillere blokker/ikke i range osv...
        //assert player does not damage when not within range
        //move players within range
        //assert player cannot inflict damage when blocking
        //assert player can inflict damage when not blocking
        //assert player does not damage victim when victim is blocking
        //assert player damages victim when victim is not blocking
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

    
}
