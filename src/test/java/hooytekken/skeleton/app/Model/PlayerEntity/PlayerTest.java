package hooytekken.skeleton.app.Model.PlayerEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.physics.box2d.World;

import hooytekken.skeleton.app.model.components.PlayerEntity.Player;

/**
 * Unit tests for the Player class
 */
public class PlayerTest {
    private World world;
    private Player player;
    private Random rand = new Random();

    @BeforeEach
    void setUpBeforeEach() {
        // create interface for world which gives better abstraction?
        world = new World(null, false); // which values here?
        player = new Player(world, null); // dont need playertype?
    }

    @Test
    void sanityTest() {
        assertNotNull(player);
        assertTrue(player.isAlive());
        assertEquals(100, player.getHealth());
    }

    @Test
    void testMove() {
        float initX = player.getBody().getPosition().x;
        float initY = player.getBody().getPosition().y;
        float randX = rand.nextFloat(0.1f, 1.0f);
        float randY = rand.nextFloat(0.1f, 1.0f);

        // move forward
        player.move(randX, randY);
        assertTrue(player.getBody().getPosition().x > initX);
        assertTrue(player.getBody().getPosition().y > initY);
        assertEquals(initX + randX, player.getBody().getPosition().x);
        assertEquals(initY + randY, player.getBody().getPosition().y);

        // move back to origin
        player.move(-randX, -randY);
        assertEquals(player.getBody().getPosition().x, initX);
        assertEquals(player.getBody().getPosition().y, initY);

        // move backwards

    }

}