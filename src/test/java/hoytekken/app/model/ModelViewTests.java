package hoytekken.app.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
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
import com.badlogic.gdx.math.Vector2;

import hoytekken.app.model.components.GameState;
import hoytekken.app.model.components.player.PlayerType;
import hoytekken.app.view.ViewableModel;


public class ModelViewTests {
    private static final Vector2 GRAVITY_VECTOR = new Vector2(0, -14);
    private static final int INITIAL_BODY_COUNT = 2;

    private ViewableModel model;

    @BeforeAll
    static void setUpBeforeAll() {
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        ApplicationListener listener = new ApplicationAdapter() {
        };
        new HeadlessApplication(listener, config);
        Gdx.gl = mock(GL20.class);
    }

    @BeforeEach
    void setUpBeforeEach() {
        model = new HTekkenModel();

    }

    @Test
    void sanityTest() {
        assertEquals(1, 1, "Sanity check to verify that tests are working.");
        assertNotNull(Gdx.gl, "Mock GL20 object should be initialized.");
        assertNotNull(model, "Model object should be initialized.");
    }

    @Test
    void testUpdateModel() {
        model.updateModel(0.1f);
        assertNotNull(model, "Model object should be initialized.");
        assertDoesNotThrow(() -> model.updateModel(0.1f), "Update should not throw.");
    }

    @Test
    void testGetPlayer() {
        assertNotNull(model.getPlayer(PlayerType.PLAYER_ONE), "Player object should be initialized.");
        assertNotNull(model.getPlayer(PlayerType.PLAYER_TWO), "Player object should be initialized.");

        //assert View has access to correct player attributes
        assertDoesNotThrow(() -> model.getPlayer(PlayerType.PLAYER_ONE).getHealth());
        assertDoesNotThrow(() -> model.getPlayer(PlayerType.PLAYER_TWO).getHealth());
        assertDoesNotThrow(() -> model.getPlayer(PlayerType.PLAYER_ONE).getLives());
        assertDoesNotThrow(() -> model.getPlayer(PlayerType.PLAYER_TWO).getLives());
        assertDoesNotThrow(() -> model.getPlayer(PlayerType.PLAYER_ONE).isAlive());
        assertDoesNotThrow(() -> model.getPlayer(PlayerType.PLAYER_TWO).isAlive());
    }

    @Test
    void testGetMap() {
        assertNotNull(model.getMap(), "Map object should be initialized.");
    }

    @Test
    void testGetTiledMap() {
        assertNull(model.getTiledMap(), "Tiledmap should not be loaded before game starts.");
    }

    @Test
    void testGetGameState() {
        assertNotNull(model.getGameState(), "Gamestate should be initialized.");
        assertEquals(model.getGameState(), GameState.MAIN_MENU, "Intial game stata should me main menu.");
    }

    @Test
    void testGetGameWorld() {
        assertNotNull(model.getGameWorld(), "Game world should be initialized.");

        Vector2 gravity = model.getGameWorld().getGravity();
        int bodyCount = model.getGameWorld().getBodyCount();

        assertEquals(gravity, GRAVITY_VECTOR, "Game world should have gravity vector set.");
        assertEquals(bodyCount, INITIAL_BODY_COUNT, "Game world should have to player bodies");
    }
}