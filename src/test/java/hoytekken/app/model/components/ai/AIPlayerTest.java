package hoytekken.app.model.components.ai;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.Random;

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

import hoytekken.app.model.components.player.IPlayer;
import hoytekken.app.model.components.player.Player;
import hoytekken.app.model.components.player.PlayerType;

public class AIPlayerTest {
    private World world;
    private Player opposition;
    private AIPlayer AIPlayer;
    private Random rand = new Random();

    private static final int MAX_HEALTH = 99;

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

        // create interface for world which gives better abstraction?
        world = new World(new Vector2(0, -14), true); // no gravity right now
        opposition = new Player(world, PlayerType.PLAYER_ONE, 99);
        AIPlayer = new AIPlayer(world, PlayerType.PLAYER_TWO, 99, opposition);
    }

    @Test
    void sanityTest() {
        assertNotNull(world);
        assertNotNull(opposition);
        assertNotNull(AIPlayer);
        assertNotNull(AIPlayer.getBody());
        assertTrue(AIPlayer.isAlive());
        assertEquals(opposition.getHealth(), MAX_HEALTH);
    }

    @Test
    void moveTowardsTargetTest() {
        float distance = Float.compare(AIPlayer.getBody().getPosition().x, opposition.getBody().getPosition().x);
        for (int i = 0; i < 10; i++) {
            AIPlayer.move(-5, 0);
            world.step(1 / 60f, 6, 2);
        }
        float distanceAfterUpdate = Float.compare(AIPlayer.getBody().getPosition().x,
                opposition.getBody().getPosition().x);

        float deltaX = distance - distanceAfterUpdate;

        System.out.println(distance);
        System.out.println(distanceAfterUpdate);
        assertTrue(deltaX > 0);
    }
}
