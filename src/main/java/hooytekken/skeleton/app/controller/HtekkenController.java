package hooytekken.skeleton.app.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

import hooytekken.skeleton.app.model.components.ForceDirection;
import hooytekken.skeleton.app.model.components.GameState;

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

        // Player1, LEFT, RIGHT, UP, PUNCH, BLOCK, KICK keys
        if (keycode == Input.Keys.LEFT)
            model.setDirection(1, ForceDirection.LEFT);
        if (keycode == Input.Keys.RIGHT)
            model.setDirection(1, ForceDirection.RIGHT);
        if (keycode == Input.Keys.UP)
            model.jump(1);
        if (keycode == Input.Keys.P)
            model.performAction(1, ActionType.PUNCH);
        if (keycode == Input.Keys.K)
            model.performAction(1, ActionType.KICK);
        if (keycode == Input.Keys.B)
            model.performAction(1, ActionType.BLOCK);

        // Player2, A, D, W, PUNCH, BLOCK, KICK keys
        if (keycode == Input.Keys.A)
            model.setDirection(2, ForceDirection.LEFT);
        if (keycode == Input.Keys.D)
            model.setDirection(2, ForceDirection.RIGHT);
        if (keycode == Input.Keys.W)
            model.jump(2);
        if (keycode == Input.Keys.Q)
            model.performAction(2, ActionType.PUNCH);
        if (keycode == Input.Keys.E)
            model.performAction(2, ActionType.KICK);
        if (keycode == Input.Keys.S) {
            model.performAction(2, ActionType.BLOCK);
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {

        // Stop applying force to the player when the key is released
        if (keycode == Input.Keys.LEFT || keycode == Input.Keys.RIGHT)
            model.setDirection(1, ForceDirection.STATIC);
        if (keycode == Input.Keys.A || keycode == Input.Keys.D)
            model.setDirection(2, ForceDirection.STATIC);

        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (model.getGameState() == GameState.INSTRUCTIONS) {
            model.setGameState(GameState.MAIN_MENU);
            return true;
        }
        else if (model.getGameState() == GameState.MAIN_MENU) {
            model.setGameState(GameState.ACTIVE_GAME);
            return true;
        }
        else if (model.getGameState() == GameState.GAME_OVER) {
            model.setGameState(GameState.MAIN_MENU);
            return true;
        }
        return false;
    }
}
