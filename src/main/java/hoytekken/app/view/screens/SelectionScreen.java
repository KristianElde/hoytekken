package hoytekken.app.view.screens;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

import org.lwjgl.opengl.GL20;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import hoytekken.app.Hoytekken;
import hoytekken.app.view.ViewableModel;

public class SelectionScreen extends BaseScreen {
    private List<Texture> mapTextures = new ArrayList<Texture>();
    
    public SelectionScreen(Hoytekken game, ViewableModel model) {
        super(game, model);
        loadMapTextures();
        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
        
    }

    private void loadMapTextures() {
        /*mapTextures.add(new Texture(Gdx.files.internal("map1.png")));
        mapTextures.add(new Texture(Gdx.files.internal("map2.png")));
        mapTextures.add(new Texture(Gdx.files.internal("map3.png")));
        mapTextures.add(new Texture(Gdx.files.internal("map4.png")));*/
        int count = 0;
        while(count < 4) {
            mapTextures.add(new Texture(Gdx.files.internal("background.png")));
            count++;
        }
    }

    private void drawMapSelections() {

        // Calculate cell width and height based on the screen size and desired grid layout
        float cellWidth = gamePort.getWorldWidth() / 2; // 2 columns
        float cellHeight = gamePort.getWorldHeight() / 3; // 3 rows

        // Starting positions for the bottom 2 rows
        float startX = 0;
        float startY = 0;

        int mapIndex = 0;

        while(mapIndex < 4) {
            Texture mapTexture = mapTextures.get(mapIndex);
            float x = startX + (mapIndex % 2) * cellWidth;
            float y = startY + (mapIndex / 2) * cellHeight;

            game.batch.draw(mapTexture, x, y, cellWidth, cellHeight);

            mapIndex++;
        }
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(gameCam.combined);

        game.batch.begin();
        drawMapSelections();
        game.batch.end();
    }
}
