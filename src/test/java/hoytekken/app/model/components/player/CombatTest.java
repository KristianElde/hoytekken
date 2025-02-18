package hoytekken.app.model.components.player;

import static org.junit.jupiter.api.Assertions.*;
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

import hoytekken.app.Hoytekken;
import hoytekken.app.model.components.player.enums.PlayerType;

/**
 * Unit tests for the Player class
 */
public class CombatTest {
    private static final float PUNCH_RANGE = 1.2f;
    private World world;
    private Player playerOne;
    private Player playerTwo;

    @BeforeAll
    static void setUpBeforeAll() {
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        ApplicationListener listener = new ApplicationAdapter() {
        };
        new HeadlessApplication(listener, config);
    }

    @BeforeEach
    void setUpBeforeEach() {
        Gdx.gl = mock(GL20.class);

        world = new World(new Vector2(0, 0), true);
        playerOne = new Player(world, PlayerType.PLAYER_ONE, 99);
        playerTwo = new Player(world, PlayerType.PLAYER_TWO, 99);

        while (!playerOne.isWithinRange(playerTwo, PUNCH_RANGE)) {
            playerOne.move(1f * Hoytekken.PPM, 0);
            playerTwo.move(-1f * Hoytekken.PPM, 0);
            world.step(1 / 60f, 6, 2);
        }
    }

    @Test
    void kick() {
        // Test that the player can kick
        assertTrue(playerOne.kick(playerTwo));
        assertTrue(playerTwo.getHealth() == 99 - 7, "Player two should have 92 health after being kicked");
    }

    @Test
    void punch() {
        // Test that the player can punch
        assertTrue(playerOne.punch(playerTwo));
        assertTrue(playerTwo.getHealth() == 99 - 10, "Player two should have 89 health after being punched");

    }

    @Test
    void block() {
        // Activate block
        playerTwo.changeBlockingState();
        assertTrue(playerTwo.getIsBlocking(), "Player two should be blocking");

        // Test that the player doesnt take damage while blocking
        playerOne.punch(playerTwo);
        assertEquals(99, playerTwo.getHealth(), "Player two should have 99 health after being punched while blocking");
        playerOne.kick(playerTwo);
        assertEquals(99, playerTwo.getHealth(), "Player two should have 99 health after being kicked while blocking");
    }
}