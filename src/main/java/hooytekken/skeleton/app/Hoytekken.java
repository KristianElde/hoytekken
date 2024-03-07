package hooytekken.skeleton.app;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import hooytekken.skeleton.app.controller.HtekkenController;
import hooytekken.skeleton.app.model.HTekkenModel;
import hooytekken.skeleton.app.model.components.GameState;
import hooytekken.skeleton.app.view.GameScreen;
import hooytekken.skeleton.app.view.MenuScreen;

public class Hoytekken extends Game {
    public static final int TILE_SIZE = 32;
    public static final int V_WIDTH = TILE_SIZE * 30;
    public static final int V_HEIGHT = TILE_SIZE * 15;
    public static final float PPM = 100;

    public SpriteBatch batch;
    public HTekkenModel model;
    public OrthographicCamera gameCam;

    @Override
    public void create() {
        model = new HTekkenModel();
        batch = new SpriteBatch();
        gameCam = new OrthographicCamera(Hoytekken.V_WIDTH / Hoytekken.PPM, Hoytekken.V_HEIGHT / Hoytekken.PPM);
        gameCam.position.set(gameCam.viewportWidth / 2, gameCam.viewportHeight / 2, 0);
        gameCam.update();
        HTekkenModel model = new HTekkenModel();
        new HtekkenController(model);

        setScreen(new MenuScreen(this, model));
    }

    @Override
    public void render() {
        if (model.getGameState() == GameState.MAIN_MENU) {
            setScreen(new MenuScreen(this, model));
        } else if (model.getGameState() == GameState.ACTIVE_GAME) {
            setScreen(new GameScreen(this, model));
        }
    }
}
