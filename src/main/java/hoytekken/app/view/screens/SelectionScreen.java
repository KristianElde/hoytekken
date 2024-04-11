package hoytekken.app.view.screens;

import java.util.HashMap;

import org.lwjgl.opengl.GL20;

import com.badlogic.gdx.Gdx;

import hoytekken.app.Hoytekken;
import hoytekken.app.view.ViewableModel;

public class SelectionScreen extends BaseScreen {
    private HashMap<String, String> gameMaps;
    
    public SelectionScreen(Hoytekken game, ViewableModel model) {
        super(game, model);
        gameMaps = model.getGameMaps();
        
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(gameCam.combined);

        game.batch.begin();
        //game.batch.draw(background, 0, 0, gamePort.getWorldWidth(), gamePort.getWorldHeight());
        game.batch.end();
    }
}
