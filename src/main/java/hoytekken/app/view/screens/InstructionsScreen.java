package hoytekken.app.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;

import hoytekken.app.Hoytekken;
import hoytekken.app.model.components.GameState;
import hoytekken.app.view.ViewableModel;

/**
 * Class representing the instructions screen.
 */
public class InstructionsScreen extends BaseScreen {
    
    private Label.LabelStyle font;
    private Table table;

    private Stage stage;

    /**
     * Constructor for the instructions screen.
     * 
     * @param game the game object
     * @param mode the viewable model
     */
    public InstructionsScreen(Hoytekken game, ViewableModel mode) {
        super(game, mode);

        stage = new Stage(gamePort, game.batch);

        font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        table = new Table();
        table.center();
        table.setFillParent(true);

        Label actionLabel = new Label("Actions", font);
        Label player1 = new Label("Player1", font);
        Label player2 = new Label("Player2", font);

        Label movement = new Label("Movement", font);
        Label keysAWD = new Label("Keys: A, W, D", font);
        Label keysArrows = new Label("Keys: Left, Up, Right", font);

        Label punch = new Label("Punch", font);
        Label keyQ = new Label("Key: Q", font);
        Label KeyP = new Label("Key: P", font);

        Label kick = new Label("Kick", font);
        Label keyE = new Label("Key: E", font);
        Label keyK = new Label("Key: K", font);

        Label block = new Label("Block", font);
        Label keyS = new Label("Key: S", font);
        Label keyB = new Label("Key: B", font);

        Label cont = new Label("Click to continue", font);

        table.add(actionLabel).expandX();
        table.add(player1).expandX();
        table.add(player2).expandX();
        table.row();
        table.add(movement).expandX();
        table.add(keysAWD).expandX();
        table.add(keysArrows).expandX();
        table.row();
        table.add(punch).expandX();
        table.add(keyQ).expandX();
        table.add(KeyP).expandX();
        table.row();
        table.add(kick).expandX();
        table.add(keyE).expandX();
        table.add(keyK).expandX();
        table.row();
        table.add(block).expandX();
        table.add(keyS).expandX();
        table.add(keyB).expandX();
        table.row();
        table.add(cont).expandX().padTop(100);

        stage.addActor(table);
    }

    private void handleStateSwitch() {
        if (model.getGameState() == GameState.MAIN_MENU) {
            game.setScreen(new MenuScreen(game, model));
        }
    }

    private void update(float delta) {
        this.gameCam.update();
        handleStateSwitch();
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

}
