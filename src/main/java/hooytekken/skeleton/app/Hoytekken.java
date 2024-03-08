package hooytekken.skeleton.app;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import hooytekken.skeleton.app.controller.HtekkenController;
import hooytekken.skeleton.app.model.HTekkenModel;
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
        HTekkenModel model = new HTekkenModel();
        new HtekkenController(model);

        // setScreen(new MenuScreen(this));
        setScreen(new GameScreen(this, model));
    }

    /**
     * Sets the screens content to a games screen.
     */
    public void startGame() {
        // setScreen(new GameScreen(this));
    }

}
