package hooytekken.skeleton.app.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import hooytekken.skeleton.app.Hoytekken;
import hooytekken.skeleton.app.model.components.GameState;
import hooytekken.skeleton.app.view.ViewableModel;

/**
 * class represents a game over screen
 */
public class GameOverScreen implements Screen {
    private Hoytekken game;
    private ViewableModel model;

    private OrthographicCamera gameCam;
    private Viewport gamePort;

    private Stage stage;

    /**
     * Constructor for the game over screen
     * 
     * @param game  the game object
     * @param model the viewable model
     */
    public GameOverScreen(Hoytekken game, ViewableModel model) {
        this.game = game;
        this.model = model;

        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(Hoytekken.V_WIDTH, Hoytekken.V_HEIGHT, gameCam);

        stage = new Stage(gamePort, game.batch);
        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        Table table = new Table();
        table.center();
        table.setFillParent(true);
        Label gameOverLabel = new Label("Game Over", font);
        Label restart = new Label("Click to restart", font);

        table.add(gameOverLabel).expandX();
        table.row();
        table.add(restart).expandX().padTop(10f);
        stage.addActor(table);
    }

    private void handleStateSwitch() {
        if (model.getGameState() != GameState.GAME_OVER) {
            game.setScreen(new MenuScreen(game, model));
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

        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        // gamePort.update(width, height);
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
