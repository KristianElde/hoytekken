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
import hooytekken.skeleton.app.model.components.Box2DWorldGenerator;
import hooytekken.skeleton.app.model.components.PlayerEntity.IPlayer;

/**
 * class represents an active game screen
 */
public class GameScreen implements Screen {
    private Hoytekken game;
    private ViewableModel model;

    private Texture img;

    private OrthographicCamera gameCam;
    private Viewport gamePort;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthoCachedTiledMapRenderer renderer;

    private Hud hud;

    private World world;
    private Box2DDebugRenderer b2dr;

    /**
     * Constructor for the game screen
     * 
     * @param game the game object
     */
    public GameScreen(Hoytekken game, ViewableModel model) {
        this.game = game;
        this.model = model;

        img = new Texture("obligator.png");

        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(Hoytekken.V_WIDTH / Hoytekken.PPM, Hoytekken.V_HEIGHT / Hoytekken.PPM, gameCam);

        hud = new Hud(game.batch);

        map = model.getTiledMap();
        renderer = new OrthoCachedTiledMapRenderer(map, 1 / Hoytekken.PPM);

        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        b2dr = new Box2DDebugRenderer();

    }

    private void update(float delta) {
        model.updateModel(delta);
        gameCam.update();
        renderer.setView(gameCam);
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

        b2dr.render(this.model.getGameWorld(), gameCam.combined);

        game.batch.setProjectionMatrix(gameCam.combined);

        // Update health
        hud.setPlayerHealth(this.model.getPlayer(1).getHealth());
        hud.setEnemyHealth(this.model.getPlayer(2).getHealth());

        game.batch.begin();
        // game.batch.draw(img, 0, 0);
        this.model.getPlayer(1).draw(game.batch);
        this.model.getPlayer(2).draw(game.batch);
        game.batch.end();
        hud.getStage().draw();
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
