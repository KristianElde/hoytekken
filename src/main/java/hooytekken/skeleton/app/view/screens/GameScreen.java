package hooytekken.skeleton.app.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import hooytekken.skeleton.app.Hoytekken;
import hooytekken.skeleton.app.model.components.GameState;
import hooytekken.skeleton.app.model.components.PlayerEntity.PlayerType;
import hooytekken.skeleton.app.view.ViewableModel;

/**
 * class represents an active game screen
 */
public class GameScreen implements Screen {
    private Hoytekken game;
    private ViewableModel model;

    private OrthographicCamera gameCam;
    private Viewport gamePort;

    private TiledMap map;
    private OrthoCachedTiledMapRenderer renderer;

    private Hud hud;

    private World world;
    private Box2DDebugRenderer b2dr;

    /**
     * Constructor for the game screen
     * 
     * @param game  the game object
     * @param model the viewable model
     */
    public GameScreen(Hoytekken game, ViewableModel model) {
        this.game = game;
        this.model = model;

        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(Hoytekken.V_WIDTH / Hoytekken.PPM, Hoytekken.V_HEIGHT / Hoytekken.PPM, gameCam);

        hud = new Hud(game.batch);

        map = model.getTiledMap();
        renderer = new OrthoCachedTiledMapRenderer(map, 1 / Hoytekken.PPM);

        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        b2dr = new Box2DDebugRenderer();

    }

    private void handleStateSwitch() {
        if (model.getGameState() != GameState.ACTIVE_GAME) {
            game.setScreen(new GameOverScreen(game, model));
        }
    }

    private int getWinningPlayer() {
        boolean playerOneWon = model.getPlayer(PlayerType.PLAYER_ONE).isAlive();
        boolean playerTwoWon = model.getPlayer(PlayerType.PLAYER_TWO).isAlive();

        if (playerOneWon && !playerTwoWon) {
            return 1;
        }
        else {
            return 2;
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

        b2dr.render(this.model.getGameWorld(), gameCam.combined);

        game.batch.setProjectionMatrix(gameCam.combined);

        // Update health
        hud.setPlayerHealth(this.model.getPlayer(PlayerType.PLAYER_ONE).getHealth());
        hud.setEnemyHealth(this.model.getPlayer(PlayerType.PLAYER_TWO).getHealth());

        game.batch.begin();
        // game.batch.draw(img, 0, 0);
        this.model.getPlayer(PlayerType.PLAYER_ONE).draw(game.batch);
        this.model.getPlayer(PlayerType.PLAYER_TWO).draw(game.batch);
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
