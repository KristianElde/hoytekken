package hooytekken.skeleton.app.view;

import com.badlogic.gdx.Screen;

import hooytekken.skeleton.app.Hoytekken;

/**
 * Class representing the instructions screen.
 */
public class InstructionsScreen implements Screen {
    private Hoytekken game;
    private ViewableModel model;

    /**
     * Constructor for the instructions screen.
     * 
     * @param game the game object
     * @param mode the viewable model
     */
    public InstructionsScreen(Hoytekken game, ViewableModel mode) {
        this.game = game;
        this.model = mode; 
        
    }

    @Override
    public void show() {
        //ingore implementation
    }

    @Override
    public void render(float delta) {
        //ingore implementation
    }

    @Override
    public void resize(int width, int height) {
        //ingore implementation
    }

    @Override
    public void pause() {
        //ingore implementation
    }

    @Override
    public void resume() {
        //ingore implementation
    }

    @Override
    public void hide() {
        //ingore implementation
    }

    @Override
    public void dispose() {
        //ingore implementation
    }
    
}
