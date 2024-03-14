package hooytekken.skeleton.app.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import hooytekken.skeleton.app.Model.PlayerEntity.PlayerTest;
import hooytekken.skeleton.app.model.HTekkenModel;
import hooytekken.skeleton.app.model.components.GameState;
import hooytekken.skeleton.app.model.components.PlayerEntity.Player;
import hooytekken.skeleton.app.model.components.PlayerEntity.PlayerType;

public class ModelTest {
    private HTekkenModel model;
    private Player player1;
    private Player player2;

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
        player1 = model.getPlayer(PlayerType.PLAYER_ONE);
        player2 = model.getPlayer(PlayerType.PLAYER_TWO);

    }

    @Test
    void sanityTest() {
        assertNotNull(model.getGameState());
        assertEquals(GameState.INSTRUCTIONS, model.getGameState());
        assertNotNull(model.getGameWorld());
        assertNotNull(model.getMap());
        assertNotNull(model.getTiledMap());
        assertNotNull(player1);
        assertNotNull(player1);
        assertNotEquals(player1, player2);
    }
}
