package hooytekken.skeleton.app;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import hooytekken.skeleton.app.view.GameScreen;

public class Hoytekken extends Game {
    public static final int TILE_SIZE = 32;
    public static final int V_WIDTH = TILE_SIZE * 30;
    public static final int V_HEIGHT = TILE_SIZE * 15;
    public static final float PPM = 100;

    public SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        setScreen(new GameScreen(this));

    }

}
