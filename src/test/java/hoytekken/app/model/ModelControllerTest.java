package hoytekken.app.model;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeAll;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;

import hoytekken.app.controller.ControllableModel;

public class ModelControllerTest {
    private ControllableModel model;

    @BeforeAll
    static void setUpBeforeAll() {
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        ApplicationListener listener = new ApplicationAdapter() {};
            new HeadlessApplication(listener, config);
            Gdx.gl = mock(GL20.class);
    }

    
}
