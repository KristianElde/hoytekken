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

/**
 * class represents an active game screen
 */
public class GameScreen implements Screen {
    private Hoytekken game;

    private Texture img;

    private OrthographicCamera gameCam;
    private Viewport gamePort;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthoCachedTiledMapRenderer renderer;

    private Hud hud;

    /**
     * Constructor for the game screen
     * @param game the game object
     */
    public GameScreen(Hoytekken game) {
        this.game = game;

        img = new Texture("obligator.png"); 

        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(Hoytekken.V_WIDTH / Hoytekken.PPM, Hoytekken.V_HEIGHT / Hoytekken.PPM, gameCam);

        hud = new Hud(game.batch);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("defaultMap.tmx");
        renderer = new OrthoCachedTiledMapRenderer(map, 1 / Hoytekken.PPM);

        gameCam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2, 0);
    }

    private void update(float delta) {
        gameCam.update();
        renderer.setView(gameCam);
    }

    @Override
    public void show() {
        //ignore implementation
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);

        renderer.render();

        game.batch.setProjectionMatrix(gameCam.combined);
        hud.getStage().draw();

        game.batch.begin();
        game.batch.draw(img, 0, 0);
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
