package hoytekken.app.controller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;

import hoytekken.app.model.HTekkenModel;
import hoytekken.app.model.components.GameState;
import hoytekken.app.model.components.eventBus.EventBus;
import hoytekken.app.model.components.player.IPlayer;
import hoytekken.app.model.components.player.PlayerType;

public class HTekkenControllerTest {
    HTekkenModel model;
    HtekkenController controller;
    IPlayer player1;
    IPlayer player2;

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
        model = new HTekkenModel(Mockito.mock(EventBus.class));
        controller = new HtekkenController(model);
        player1 = model.getPlayer(PlayerType.PLAYER_ONE);
        model.setNumberOfPlayers(false);
        player2 = model.getPlayer(PlayerType.PLAYER_TWO);
    }

    @Test
    void keyDownTestMainMenuI() {
        model.setGameState(GameState.MAIN_MENU);
        assertTrue(controller.keyDown(Input.Keys.I));
    }

    @Test
    void keyDownTestMainMenuESCAPE() {
        model.setGameState(GameState.MAIN_MENU);
        assertTrue(controller.keyDown(Input.Keys.ESCAPE));
    }

    @Test
    void keyDownTestMainMenuUP() {
        model.setGameState(GameState.MAIN_MENU);
        assertFalse(controller.keyDown(Input.Keys.UP));
    }

}
