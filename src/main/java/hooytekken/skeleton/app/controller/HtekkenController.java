package hooytekken.skeleton.app.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;

/**
 * Controller for the game
 * handles input from the user
 */
public class HtekkenController extends InputAdapter {
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

     
}
