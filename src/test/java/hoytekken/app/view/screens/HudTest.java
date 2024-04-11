package hoytekken.app.view.screens;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import hoytekken.app.Hoytekken;

public class HudTest {
    private static HeadlessApplication application;
    private static Hoytekken gameMock;
    private Hud hud;

    @BeforeAll
    static void setUpBeforeAll() {
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        application = new HeadlessApplication(new Hoytekken(), config);
        Gdx.gl = mock(GL20.class);
        gameMock = mock(Hoytekken.class);
        gameMock.batch = mock(SpriteBatch.class);
    }

    @BeforeEach
    void setUpBeforeEach() {
        hud = new Hud(gameMock.batch);
    }

    @AfterEach
    void disposeHud() {
        hud.getStage().dispose();
    }

    @AfterAll
    static void cleanUp() {
        application.exit();
        application = null;
    }

    
}
