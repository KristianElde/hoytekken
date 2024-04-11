package hoytekken.app.model.components.sound;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;

/*
 * Unit tests for the Sound class
 */
public class SoundTest {
    private ISound sound;
    private String path = "sounds/punch-3-166696.mp3";

    @BeforeAll
    static void setUpBeforeAll() {
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        ApplicationListener listener = new ApplicationAdapter() {
        };

        new HeadlessApplication(listener, config);
    }

    @BeforeEach
    void setUpBeforeEach() {
        sound = new Sound(path);
    }

    @Test
    void sanityTest() {
        assertNotNull(sound);
    }

    @Test
    void checkIdNotNull() {
        assertNotNull(sound.getId());
    }
}
