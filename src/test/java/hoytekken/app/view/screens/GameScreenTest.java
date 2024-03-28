package hoytekken.app.view.screens;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockingDetails;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;

import hoytekken.app.Hoytekken;
import hoytekken.app.model.HTekkenModel;
import hoytekken.app.model.components.GameState;

public class GameScreenTest {

    Hoytekken game;
    HTekkenModel model;
    GameScreen gameScreen;

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
        game = new Hoytekken();
        game.create();
        model = new HTekkenModel();
        game.setScreen(new GameScreen(game, model));
    }

    @Test
    void sanityTest() {
        assertNotNull(game.getScreen());
        assertNotNull(gameScreen.gameCam);
        assertNotNull(gameScreen.gamePort);
    }

    @Test
    void handleStateSwitchTest() {
        assertEquals(GameState.ACTIVE_GAME, model.getGameState());
        gameScreen.handleStateSwitch();
        assertEquals(GameState.GAME_OVER, model.getGameState());
    }
}
