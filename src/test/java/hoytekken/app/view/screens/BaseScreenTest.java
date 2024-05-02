package hoytekken.app.view.screens;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;

import hoytekken.app.Hoytekken;
import hoytekken.app.model.components.GameState;
import hoytekken.app.model.components.eventBus.EventBus;
import hoytekken.app.model.components.eventBus.GameStateEvent;
import hoytekken.app.model.components.eventBus.IEvent;
import hoytekken.app.view.ViewableModel;

public class BaseScreenTest {
    private static HeadlessApplication application;
    private static Hoytekken gameMock;
    private static ViewableModel modelMock;
    private final EventBus eventbus = new EventBus();

    @BeforeAll
    static void setUpBeforeAll() {
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        application = new HeadlessApplication(new Hoytekken(), config);

        // Mock dependencies
        Gdx.gl = mock(GL20.class);
        gameMock = mock(Hoytekken.class);
        modelMock = mock(ViewableModel.class);
        gameMock.batch = mock(SpriteBatch.class);
    }

    @BeforeEach
    void setUpBeforeEach() {
        when(modelMock.getEventBus()).thenReturn(eventbus);
    }

    @AfterAll
    static void cleanUp() {
        application.exit();
        application = null;
    }

    @Test
    void sanityTest() {
        assertNotNull(application, "Headless application should be initialized.");
        assertNotNull(Gdx.gl, "Mock GL20 object should be initialized.");
        assertNotNull(gameMock, "Mock game object should be initialized.");
        assertNotNull(modelMock, "Mock model object should be initialized.");
        assertNotNull(gameMock.batch, "Mock SpriteBatch object should be initialized.");
    }

    @Test
    void testBaseScreens() {
        testBaseScreen(new MenuScreen(gameMock, modelMock));
        testBaseScreen(new InstructionsScreen(gameMock, modelMock));
        testBaseScreen(new GameOverScreen(gameMock, modelMock, 1));
        testBaseScreen(new SelectionScreen(gameMock, modelMock));
    }

    private void testBaseScreen(BaseScreen screen) {
        assertNotNull(screen);
        testSuperImplementations(screen);
        assertScreenInitialization(screen);
        testResizeScreen(screen);
        testInitCameraAndViewPort(screen);
        handleEventTest(screen);
        assertDoesNotThrow(() -> screen.update(1f));
    }

    private void assertScreenInitialization(BaseScreen screen) {
        assertNotNull(screen.game, "Game object should be initialized.");
        assertNotNull(screen.model, "Model object should be initialized.");
        assertNotNull(screen.gameCam, "Camera object should be initialized.");
        assertNotNull(screen.gamePort, "Viewport object should be initialized.");
        assertInstanceOf(OrthographicCamera.class, screen.gameCam);
        assertInstanceOf(FitViewport.class, screen.gamePort);
        assertEquals(Hoytekken.V_WIDTH / 2f, screen.gameCam.position.x, "Camera X position should be centered.");
        assertEquals(Hoytekken.V_HEIGHT / 2f, screen.gameCam.position.y, "Camera Y position should be centered.");
    }

    private void testSuperImplementations(BaseScreen screen) {
        // assert super implementations does not throw exceptions
        assertDoesNotThrow(screen::show, "Show should not throw an exception.");
        assertDoesNotThrow(screen::hide, "Hide should not throw an exception.");
        assertDoesNotThrow(screen::pause, "Pause should not throw an exception.");
        assertDoesNotThrow(screen::resume, "Resume should not throw an exception.");
        assertDoesNotThrow(screen::dispose, "Dispose should not throw an exception.");
    }

    private void testResizeScreen(BaseScreen screen) {
        // assert aspect ratio is maintained
        float originalAspectRatio = screen.gamePort.getWorldWidth() / screen.gamePort.getWorldHeight();
        screen.resize(800, 600);

        float newWidth = screen.gamePort.getScreenWidth();
        float newHeight = screen.gamePort.getScreenHeight();
        float newAspectRatio = newWidth / newHeight;

        assertEquals(originalAspectRatio, newAspectRatio, 0.01, "Aspect ratio should be maintained after resize.");
        assertEquals(800, newWidth, "Screen width should be 800.");
        assertNotEquals(600, newHeight, "Height should not be 600.");
        assertEquals(newWidth / newAspectRatio, newHeight, "Height should be adjusted to maintain aspect ratio.");

        // assert screens are resized for different parameters
        screen.resize(1000, 800);
        assertEquals(1000, screen.gamePort.getScreenWidth(), "Screen width should be 1000.");
        assertEquals(1000 / newAspectRatio, screen.gamePort.getScreenHeight(), "Screen height should be 800.");

        screen.resize(1200, 900);
        assertEquals(1200, screen.gamePort.getScreenWidth(), "Screen width should be 1200.");
        assertEquals(1200 / newAspectRatio, screen.gamePort.getScreenHeight(), "Screen height should be 900.");

        // assert does not throw exception
        assertDoesNotThrow(() -> screen.resize(0, 0), "Resize should not throw an exception.");
        assertDoesNotThrow(() -> screen.resize(100000, 100000), "Resize should not throw an exception.");
    }

    private void testInitCameraAndViewPort(BaseScreen screen) {
        Viewport current = screen.gamePort;
        screen.initializeCameraAndViewport(true);
        assertNotNull(screen.gameCam);
        assertNotNull((screen.gamePort));
        // make sure viewport is updated
        assertNotEquals(current, screen.gamePort);
    }

    private void handleEventTest(BaseScreen screen) {
        IEvent event = null;
        assertThrows(IllegalArgumentException.class, () -> screen.handleEvent(event));
        GameStateEvent gameStateEvent = new GameStateEvent(GameState.MAIN_MENU, GameState.INSTRUCTIONS);
        assertDoesNotThrow(() -> screen.handleEvent(gameStateEvent));
    }

}
