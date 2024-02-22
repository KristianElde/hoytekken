package hooytekken.skeleton.app.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

/**
 * Controller for the game
 * handles input from the user
 */
public class HtekkenController implements InputProcessor {
    //ControllableModel model;

    public HtekkenController(/*ControllableModel model*/) {
//        this.model = model;
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public boolean keyDown(int keycode) {
        System.out.println("Key Down: " + keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        System.out.println("Key Up: " + keycode);
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        //ignore implementation key typed
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        //ignore implementation mouse click
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        ///ignore implementation mouse click up
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        //ignore implementation mouse click cancelled
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        //ignore implementation mouse dragged
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        //ignore implementation mouse moved
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        //ignore implementation mouse scrolled
        return false;
    }
    
}
