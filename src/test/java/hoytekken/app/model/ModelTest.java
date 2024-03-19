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
import com.badlogic.gdx.math.Vector2;

import hoytekken.app.Hoytekken;
import hoytekken.app.controller.ActionType;
import hoytekken.app.model.components.ForceDirection;
import hoytekken.app.model.components.GameState;
import hoytekken.app.model.components.player.Player;
import hoytekken.app.model.components.player.PlayerType;

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

    private boolean isWithinRange(Player p1, Player p2) {
        float playerWidth = 45 / Hoytekken.PPM;

        Vector2 p1Pos = new Vector2(p1.getBody().getPosition().x, p1.getBody().getPosition().y);
        Vector2 p2Pos = new Vector2(p2.getBody().getPosition().x, p2.getBody().getPosition().y);

        float distance = p1Pos.dst(p2Pos);
        float range = playerWidth * 1.8f;
        return distance <= range;
    }

    private void movePlayersBeside() {
        while (!isWithinRange(player1, player2)) {
            player1.move(1, 0);
            model.getGameWorld().step(1 / 60f, 6, 2);
        }
    }

    @Test
    void sanityTest() {
        assertNotNull(model.getGameState());
        assertNotNull(model.getGameWorld());
        assertNotNull(model.getMap());
        // assertNotNull(model.getTiledMap());
        assertNotNull(player1);
        assertNotNull(player1);
        assertNotEquals(player1, player2);
    }

    @Test
    void gameStateTest() {
        assertEquals(GameState.MAIN_MENU, model.getGameState());
        model.setGameState(GameState.INSTRUCTIONS);
        assertEquals(GameState.INSTRUCTIONS, model.getGameState());
        model.setGameState(GameState.ACTIVE_GAME);
        assertEquals(GameState.ACTIVE_GAME, model.getGameState());

        movePlayersBeside();
        while (player2.isAlive()) {
            model.performAttackAction(PlayerType.PLAYER_ONE, ActionType.PUNCH);
            model.updateModel(0);
        }
        assertEquals(GameState.GAME_OVER, model.getGameState());
    }

    @Test
    void ForceDirectionTest() {
        assertEquals(ForceDirection.STATIC, model.getDirection(PlayerType.PLAYER_ONE));
        assertEquals(ForceDirection.STATIC, model.getDirection(PlayerType.PLAYER_TWO));

        model.setDirection(PlayerType.PLAYER_ONE, ForceDirection.LEFT);
        assertEquals(ForceDirection.LEFT, model.getDirection(PlayerType.PLAYER_ONE));
        assertEquals(ForceDirection.STATIC, model.getDirection(PlayerType.PLAYER_TWO));

        model.setDirection(PlayerType.PLAYER_TWO, ForceDirection.RIGHT);
        assertEquals(ForceDirection.LEFT, model.getDirection(PlayerType.PLAYER_ONE));
        assertEquals(ForceDirection.RIGHT, model.getDirection(PlayerType.PLAYER_TWO));
    }

    @Test
    void performAttackActionPunchTest() {
        model.performAttackAction(PlayerType.PLAYER_ONE, ActionType.PUNCH);

        // Check that opponents health is not reduced by punch when opponent is out of
        // range
        assertEquals(99, player1.getHealth());
        assertEquals(99, player2.getHealth());

        movePlayersBeside();
        model.performAttackAction(PlayerType.PLAYER_ONE, ActionType.PUNCH);

        // Check that opponents health is reduced by punch when opponent is inside range
        assertEquals(99, player1.getHealth());
        assertEquals(89, player2.getHealth());
    }

    @Test
    void performAttackActionKickTest() {
        model.performAttackAction(PlayerType.PLAYER_ONE, ActionType.KICK);

        // Check that opponents health is not reduced by kick when opponent is out of
        // range
        assertEquals(99, player1.getHealth());
        assertEquals(99, player2.getHealth());

        movePlayersBeside();
        model.performAttackAction(PlayerType.PLAYER_ONE, ActionType.KICK);

        // Check that opponents health is reduced by kick when opponent is inside range
        assertEquals(99, player1.getHealth());
        assertEquals(92, player2.getHealth());
    }

    @Test
    void jumpCounterTest() {
        assertEquals(0, model.getJumpCounter(PlayerType.PLAYER_ONE));

        model.jump(PlayerType.PLAYER_ONE);
        assertEquals(1, model.getJumpCounter(PlayerType.PLAYER_ONE));

        model.jump(PlayerType.PLAYER_ONE);
        assertEquals(2, model.getJumpCounter(PlayerType.PLAYER_ONE));

        model.jump(PlayerType.PLAYER_ONE);
        assertEquals(2, model.getJumpCounter(PlayerType.PLAYER_ONE));

        model.resetDoubleJump(PlayerType.PLAYER_ONE);
        assertEquals(0, model.getJumpCounter(PlayerType.PLAYER_ONE));
    }

    @Test
    void blockingPreventsDamageTest() {
        movePlayersBeside();

        player1.activateBlock();
        model.performAttackAction(PlayerType.PLAYER_TWO, ActionType.PUNCH);
        // Check that attack does not inflict damage when victim is blocking
        assertEquals(99, player1.getHealth());
    }

    @Test
    void blockingPreventsJumpTest() {
        // Check that jump() returns true
        assertTrue(model.jump(PlayerType.PLAYER_ONE));

        player1.activateBlock();
        // Check that jump() returns false when isBlocking is set to true
        assertFalse(model.jump(PlayerType.PLAYER_ONE));
    }

    @Test
    void blockingPreventsAttackingActions() {
        player1.activateBlock();
        assertFalse(model.performAttackAction(PlayerType.PLAYER_ONE, ActionType.PUNCH));
        assertEquals(99, player2.getHealth());

        player1.deactivateBlock();
        assertFalse(model.performAttackAction(PlayerType.PLAYER_ONE, ActionType.PUNCH));
        assertEquals(89, player2.getHealth());
    }

}
