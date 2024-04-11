package hoytekken.app;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HoytekkenTest {
    private Hoytekken game;

    @BeforeEach
    void setUp() {
        game = new Hoytekken();
        game.batch = Mockito.mock(SpriteBatch.class);
    }
}
