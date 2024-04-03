package hoytekken.app.view.screens;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import hoytekken.app.Hoytekken;
import hoytekken.app.view.ViewableModel;

public class BaseScreenTest {
    private static HeadlessApplication application;
    private static Hoytekken gameMock;
    private static ViewableModel modelMock;

    private MenuScreen menuScreen;

    @BeforeAll
    public static void setUpBeforeAll() {
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        application = new HeadlessApplication(new Hoytekken(), config);

        // Mock dependencies
        Gdx.gl = mock(GL20.class);
        gameMock = mock(Hoytekken.class);
        modelMock = mock(ViewableModel.class);
        gameMock.batch = mock(SpriteBatch.class);
    }

    @BeforeEach
    public void setUp() {
        menuScreen = new MenuScreen(gameMock, modelMock);
    }

    @AfterAll
    public static void cleanUp() {
        application.exit();
        application = null;
    }

    @Test
    public void sanityTest() {
        assertEquals(1, 1, "Sanity check to verify that tests are working.");
        assertNotNull(application, "Headless application should be initialized.");
        assertNotNull(Gdx.gl, "Mock GL20 object should be initialized.");
        assertNotNull(gameMock, "Mock game object should be initialized.");
        assertNotNull(modelMock, "Mock model object should be initialized.");
        assertNotNull(gameMock.batch, "Mock SpriteBatch object should be initialized.");
    }

}
