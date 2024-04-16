package hoytekken.app.view.screens;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

import org.lwjgl.opengl.GL20;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import hoytekken.app.Hoytekken;
import hoytekken.app.model.components.GameState;
import hoytekken.app.model.components.eventBus.ClickedScreenEvent;
import hoytekken.app.model.components.eventBus.GameStateEvent;
import hoytekken.app.model.components.eventBus.IEvent;
import hoytekken.app.view.ViewableModel;

public class SelectionScreen extends BaseScreen {
    private List<Texture> mapTextures = new ArrayList<Texture>();
    private boolean isOnePlayerSelected = true;
    
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

    private void drawPlayerSelection() {
        BitmapFont font = new BitmapFont();
        font.setColor(Color.WHITE);
        GlyphLayout layout = new GlyphLayout();
        String text = isOnePlayerSelected ? "1 player selected, click here to change to 2 players" : "2 players selected, click here to change to 1 player";
        layout.setText(font, text);
        float x = (gamePort.getWorldWidth() - layout.width) / 2;
        float y = gamePort.getWorldHeight()- (gamePort.getWorldHeight()/10) - layout.height; 
        font.draw(game.batch, layout, x, y);
    }

    @Override
    public void handleEvent(IEvent event) {
        /*if (event instanceof GameStateEvent) {
            handleStateSwitch((GameStateEvent) event);
        } else*/
        super.handleEvent(event);
        if (event instanceof ClickedScreenEvent) {
            handleSelection((ClickedScreenEvent) event);
        }
    }

    private void handleSelection(ClickedScreenEvent event) {
        int x = event.x();
        int y = event.y();

        //float cellWidth = gamePort.getWorldWidth() / 2; // 2 columns
        //float cellHeight = gamePort.getWorldHeight() / 3; // 3 rows
        float cellWidth = gamePort.getScreenWidth() / 2; // 2 columns
        float cellHeight = gamePort.getScreenHeight() / 3; // 3 rows

        /*System.out.println("World height " + gamePort.getWorldHeight());
        System.out.println("World height - 1/3 " + (gamePort.getWorldHeight() - (gamePort.getWorldHeight() / 3)));
        System.out.println("World height - 2/3 " + (gamePort.getWorldHeight() - (gamePort.getWorldHeight() / 3 * 2)));
        System.out.println("middle world x " + gamePort.getWorldWidth() / 2);*/
        System.out.println("3Cell height " + 3*cellHeight);
        System.out.println("2Cell heihgt " + 2*cellHeight);
        System.out.println("1Cell Heigh " + 1*cellHeight);
        System.out.println("Cell width " + cellWidth);


        System.out.println("x: " + x + " y: " + y);
        if (y < cellHeight) {
            isOnePlayerSelected = !isOnePlayerSelected;
        } else if (y < 2*cellHeight) {
            if (x < cellWidth) model.setGameMap("map1");
            else model.setGameMap("map2");
            model.setGameState(GameState.ACTIVE_GAME);
        } else if (y < 3*cellHeight){
            if (x < cellWidth) model.setGameMap("map3");
            else model.setGameMap("map4");
            model.setGameState(GameState.ACTIVE_GAME);
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
        drawPlayerSelection();
        game.batch.end();
    }
}
