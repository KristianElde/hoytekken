package hoytekken.app;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;

import hoytekken.app.model.HTekkenModel;
import hoytekken.app.model.components.player.PlayerType;

public class HoytekkenTest {

    Hoytekken game;

    @BeforeAll
    static void setUpBeforeAll() {

    }

    @BeforeEach
    void setUpBeforeEach() {
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        game = new Hoytekken();
        new HeadlessApplication(game, config);
        Gdx.gl = mock(GL20.class);
    }

    @Test
    void sanityTest() {
        assertNotNull(this);
        assertNotNull(game.batch);
    }
}
