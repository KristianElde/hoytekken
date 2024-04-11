package hoytekken.app.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import hoytekken.app.model.components.eventBus.EventBus;

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

    
}
