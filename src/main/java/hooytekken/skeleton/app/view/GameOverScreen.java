package hooytekken.skeleton.app.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import hooytekken.skeleton.app.Hoytekken;
import hooytekken.skeleton.app.model.components.GameState;

/**
 * class represents an active game screen
 */
public class GameOverScreen implements Screen {
    private Hoytekken game;
    private ViewableModel model;

    private OrthographicCamera gameCam;
    private Viewport gamePort;

    private TiledMap map;
    private OrthoCachedTiledMapRenderer renderer;

    private Texture welcomeImg;

    private Box2DDebugRenderer b2dr;

    private static final float WELCOME_TEXT_WIDTH = 300;
    private static final float WELCOME_TEXT_HEIGHT = 300;

    /**
     * Constructor for the game screen
     * 
     * @param game the game object
     */
    public GameOverScreen(Hoytekken game, ViewableModel model) {
        this.game = game;
        this.model = model;

        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(Hoytekken.V_WIDTH / Hoytekken.PPM, Hoytekken.V_HEIGHT / Hoytekken.PPM, gameCam);

        map = model.getTiledMap();
        renderer = new OrthoCachedTiledMapRenderer(map, 1 / Hoytekken.PPM);

        welcomeImg = new Texture("game-over.jpg");

        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        b2dr = new Box2DDebugRenderer();

    }

    private void handleStateSwitch() {
        if (model.getGameState() != GameState.GAME_OVER) {
            game.setScreen(new MenuScreen(game, model));
        }
    }

    private void update(float delta) {
        model.updateModel(delta);
        gameCam.update();
        renderer.setView(gameCam);
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

        renderer.render();

        game.batch.setProjectionMatrix(gameCam.combined);

        game.batch.begin();

        game.batch.draw(welcomeImg, (Hoytekken.V_WIDTH / 2 - WELCOME_TEXT_WIDTH / 2) / Hoytekken.PPM,
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
