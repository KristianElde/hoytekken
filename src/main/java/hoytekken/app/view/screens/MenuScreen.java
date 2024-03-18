package hoytekken.app.view.screens;

import org.lwjgl.opengl.GL20;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import hoytekken.app.Hoytekken;
import hoytekken.app.model.components.GameState;
import hoytekken.app.view.ViewableModel;

/**
 * class represents a menu screen
 */
public class MenuScreen implements Screen {
    private Hoytekken game;
    private ViewableModel model;

    private OrthographicCamera gameCam;
    private Viewport gamePort;

    /**
     * Constructor for the menu screen
     * 
     * @param game  the game object
     * @param model the viewable model
     */
    public MenuScreen(Hoytekken game, ViewableModel model) {
        this.game = game;
        this.model = model;

        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(Hoytekken.V_WIDTH / Hoytekken.PPM, Hoytekken.V_HEIGHT / Hoytekken.PPM, gameCam);

    }

    private void handleStateSwitch() {
        if (model.getGameState() != GameState.MAIN_MENU) {
            game.setScreen(new GameScreen(game, model));
        }
    }

    private void update(float delta) {
        gameCam.update();
        handleStateSwitch();
    }

    @Override
    public void show() {
        // ignore implementation
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        game.batch.setProjectionMatrix(gameCam.combined);

        game.batch.begin();

        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void pause() {
        // ignore implementation
    }

    @Override
    public void resume() {
        // ignore implementation
    }

    @Override
    public void hide() {
        // ignore implementation
    }

    @Override
    public void dispose() {
        // ignore implementation
    }

}
