package hoytekken.app.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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

import hoytekken.app.model.components.player.PlayerType;
import hoytekken.app.view.ViewableModel;


public class ModelViewTests {
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
    }

    @Test
    void testGetPlayer() {
        assertNotNull(model.getPlayer(PlayerType.PLAYER_ONE), "Player object should be initialized.");
        assertNotNull(model.getPlayer(PlayerType.PLAYER_TWO), "Player object should be initialized.");
    }

    @Test
    void testGetMap() {
        assertNotNull(model.getMap(), "Map object should be initialized.");
    }
}