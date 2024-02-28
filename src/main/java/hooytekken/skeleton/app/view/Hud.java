package hooytekken.skeleton.app.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Hud {
    private Stage stage;
    private Viewport port;

    public Hud(SpriteBatch sb) {
        port = new FitViewport(0, 0, new OrthographicCamera());
        stage = new Stage(port, sb);
    }

}
