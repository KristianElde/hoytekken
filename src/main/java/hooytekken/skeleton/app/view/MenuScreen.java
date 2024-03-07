package hooytekken.skeleton.app.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import hooytekken.skeleton.app.Hoytekken;
import hooytekken.skeleton.app.controller.HtekkenController;
import hooytekken.skeleton.app.model.HTekkenModel;

/**
 * class represents an active game screen
 */
public class MenuScreen implements Screen {
    private Hoytekken game;
    private HTekkenModel model;

    private Viewport gamePort;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthoCachedTiledMapRenderer renderer;
    private Texture welcome;

    private static final float WELCOME_TEXT_WIDTH = 300;
    private static final float WELCOME_TEXT_HEIGHT = 300;

    /**
     * Constructor for the game screen
     * 
     * @param game the game object
     */
    public MenuScreen(Hoytekken game, HTekkenModel model) {
        this.game = game;

        gamePort = new FitViewport(Hoytekken.V_WIDTH / Hoytekken.PPM,
                Hoytekken.V_HEIGHT / Hoytekken.PPM, game.gameCam);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("defaultMap.tmx");
        renderer = new OrthoCachedTiledMapRenderer(map, 1 / Hoytekken.PPM);
        welcome = new Texture("Welcome.png");

        game.gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
    }

    private void update(float delta) {
        game.gameCam.update();
        renderer.setView(game.gameCam);
    }

    @Override
    public void show() {
        render(0);
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);

        renderer.render();

        game.batch.setProjectionMatrix(game.gameCam.combined);

        game.batch.begin();

        game.batch.draw(welcome, (Hoytekken.V_WIDTH / 2 - WELCOME_TEXT_WIDTH / 2) / Hoytekken.PPM,
                (Hoytekken.V_HEIGHT / 2 - WELCOME_TEXT_HEIGHT / 2) / Hoytekken.PPM, WELCOME_TEXT_WIDTH / Hoytekken.PPM,
                WELCOME_TEXT_HEIGHT / Hoytekken.PPM);

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
