package hooytekken.skeleton.app.Model.PlayerEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.math.Vector2;
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
        world = new World(new Vector2(0, 0), true); // no gravity right now
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
        float randX = rand.nextFloat(1.0f, 5.0f);
        float randY = rand.nextFloat(1.0f, 5.0f);

        // move forward
        player.move(randX, randY);
        world.step(1 / 60f, 6, 2); // updates the world objects
        assertTrue(player.getBody().getPosition().x > initX);
        assertTrue(player.getBody().getPosition().y > initY);

        // move back to origin
        player.move(-randX, -randY);
        world.step(1 / 60f, 6, 2);
        float margin = 0.1f; // margin for error due to physics
        assertEquals(initX, player.getBody().getPosition().x, margin);
        assertEquals(initY, player.getBody().getPosition().y, margin);
    }

    @Test
    void testUpdate() {
        // not sure yet how to implement this
    }

    @Test
    void testDamageTaken() {
        int initHealth = player.getHealth();

        // make sure player stays alive
        int dmg1 = rand.nextInt(1, initHealth);
        player.takeDamage(dmg1);
        assertEquals(initHealth - dmg1, player.getHealth());
        assertTrue(player.isAlive());

        // make sure player cant survive
        int dmg2 = rand.nextInt(100, 500);
        player.takeDamage(dmg2);
        assertEquals(0, player.getHealth());
        assertFalse(player.isAlive());

    }

}