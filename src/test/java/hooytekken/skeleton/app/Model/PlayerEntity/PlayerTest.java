package hooytekken.skeleton.app.Model.PlayerEntity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import com.badlogic.gdx.physics.box2d.World;

import hooytekken.skeleton.app.model.components.PlayerEntity.Player;

/**
 * Unit tests for the Player class
 */
public class PlayerTest {
    private World world;
    private Player player;

    @BeforeEach
    void setUpBeforeEach() {
        // create interface for world which gives better abstraction?
        world = new World(null, false); // which values here?
        player = new Player(world, null); // dont need playertype?
    }
}