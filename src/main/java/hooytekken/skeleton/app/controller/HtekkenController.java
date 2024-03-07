package hooytekken.skeleton.app.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import hooytekken.skeleton.app.model.components.ForceDirection;

/**
 * Controller for the game
 * handles input from the user
 */
public class HtekkenController extends InputAdapter {
    ControllableModel model;

    public HtekkenController(ControllableModel model) {
        this.model = model;
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public boolean keyDown(int keycode) {

        //Player1, LEFT, RIGHT, UP keys
        if (keycode == Input.Keys.LEFT) 
            model.setDirection(1, ForceDirection.LEFT);
        if (keycode == Input.Keys.RIGHT) 
            model.setDirection(1, ForceDirection.RIGHT);
        if (keycode == Input.Keys.UP) 
            model.jump(1);
        if (keycode == Input.Keys.P)
            //model.punch(1);
        if (keycode == Input.Keys.K)
            //model.kick(1);
        if (keycode == Input.Keys.B)
            //model.block(1);

        //Player2, A, D, W keys
        if (keycode == Input.Keys.A)
            model.setDirection(2, ForceDirection.LEFT);
        if (keycode == Input.Keys.D) 
            model.setDirection(2, ForceDirection.RIGHT);
        if (keycode == Input.Keys.W) 
            model.jump(2);
        if (keycode == Input.Keys.Q)
            //model.punch(2);
        if (keycode == Input.Keys.E)
            //model.kick(2);
        if (keycode == Input.Keys.S)
            //model.block(2);

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        
        //Stop applying force to the player when the key is released
        if (keycode == Input.Keys.LEFT || keycode == Input.Keys.RIGHT) model.setDirection(1, ForceDirection.STATIC);
        if (keycode == Input.Keys.A || keycode == Input.Keys.D) model.setDirection(2, ForceDirection.STATIC);

        return false;
    }

     
}
