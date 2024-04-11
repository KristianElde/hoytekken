package hoytekken.app.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    
}
