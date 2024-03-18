package hoytekken.app.view.screens;

import org.lwjgl.opengl.GL20;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import hoytekken.app.Hoytekken;
import hoytekken.app.model.components.GameState;
import hoytekken.app.view.ViewableModel;

/**
 * class represents a menu screen
 */
public class MenuScreen implements Screen {
    private static final String BG_PATH = "background.png";
    private static final String PLAY = "CLICK TO PLAY";
    private static final String INSTRUCTIONS = "PRESS \'I\' FOR INSTRUCTIONS";
    private static final String EXIT = "PRESS \'ESC\' TO EXIT";

    private Hoytekken game;
    private ViewableModel model;

    private OrthographicCamera gameCam;
    private Viewport gamePort;

    private Texture background;

    private Stage stage;

    private Label.LabelStyle font;
   //private BitmapFont font;

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
        gamePort = new FitViewport(Hoytekken.V_WIDTH, Hoytekken.V_HEIGHT, gameCam);

        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        background = new Texture(Gdx.files.internal(BG_PATH));

        //font = new BitmapFont();
        stage = new Stage(gamePort, game.batch);

        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        Table table = new Table();
        table.bottom();
        table.setFillParent(true);
        Label instructionsLabel = new Label(INSTRUCTIONS, font);
        Label exitLabel = new Label(EXIT, font);
        Label playLabel = new Label(PLAY, font);
        table.add(exitLabel).expandX();
        table.add(playLabel).expandX();
        table.add(instructionsLabel).expandX();

        stage.addActor(table);
    }

    /*private void drawShadedText(String text, float x, float y) {
        font.setColor(Color.BLACK);
        font.draw(game.batch, text, x + 10/Hoytekken.PPM, y + 10 / Hoytekken.PPM);
        font.setColor(Color.WHITE);
        font.draw(game.batch, text, x/Hoytekken.PPM, y/ Hoytekken.PPM);
    }*/

    private void handleStateSwitch() {
        if (model.getGameState() != GameState.MAIN_MENU) {
            game.setScreen(new GameScreen(game, model));
        } else if (model.getGameState() == GameState.INSTRUCTIONS) {
            game.setScreen(new InstructionsScreen(game, model));
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
        game.batch.draw(background, 0, 0, gamePort.getWorldWidth(), gamePort.getWorldHeight());
        //drawShadedText(PLAY, gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2);
        game.batch.end();

        stage.draw();
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
