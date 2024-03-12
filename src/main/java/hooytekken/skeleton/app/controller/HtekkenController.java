package hooytekken.skeleton.app.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;


import hooytekken.skeleton.app.model.components.ForceDirection;
import hooytekken.skeleton.app.model.components.GameState;
import hooytekken.skeleton.app.model.components.PlayerEntity.PlayerType;

//Import Hoytekken class from the view package
import hooytekken.skeleton.app.Hoytekken;

/**
 * Controller for the game
 * handles input from the user
 */
public class HtekkenController extends InputAdapter {
    ControllableModel model;

    private PlayerType playerOne = PlayerType.PLAYER_ONE;
    private PlayerType playerTwo = PlayerType.PLAYER_TWO;

    /**
     * Constructor for the controller
     * keeps model and sets input processor for the game
     * 
     * @param model the model to control
     */
    public HtekkenController(ControllableModel model) {
        this.model = model;
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public boolean keyDown(int keycode) {

        // Player1, LEFT, RIGHT, UP, PUNCH, BLOCK, KICK keys
        if (keycode == Input.Keys.LEFT)
            model.setDirection(playerOne, ForceDirection.LEFT);
        if (keycode == Input.Keys.RIGHT)
            model.setDirection(playerOne, ForceDirection.RIGHT);
        if (keycode == Input.Keys.UP)
            model.jump(playerOne);
        if (keycode == Input.Keys.P)
            model.performAction(playerOne, ActionType.PUNCH);
        if (keycode == Input.Keys.K)
            model.performAction(playerOne, ActionType.KICK);
        if (keycode == Input.Keys.B)
            model.performAction(playerOne, ActionType.BLOCK);

        // Player2, A, D, W, PUNCH, BLOCK, KICK keys
        if (keycode == Input.Keys.A)
            model.setDirection(playerTwo, ForceDirection.LEFT);
        if (keycode == Input.Keys.D)
            model.setDirection(playerTwo, ForceDirection.RIGHT);
        if (keycode == Input.Keys.W)
            model.jump(playerTwo);
        if (keycode == Input.Keys.Q)
            model.performAction(playerTwo, ActionType.PUNCH);
        if (keycode == Input.Keys.E)
            model.performAction(playerTwo, ActionType.KICK);
        if (keycode == Input.Keys.S) {
            model.performAction(playerTwo, ActionType.BLOCK);
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {

        // Stop applying force to the player when the key is released
        if (keycode == Input.Keys.LEFT || keycode == Input.Keys.RIGHT)
            model.setDirection(playerOne, ForceDirection.STATIC);
        if (keycode == Input.Keys.A || keycode == Input.Keys.D)
            model.setDirection(playerTwo, ForceDirection.STATIC);

        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (model.getGameState() == GameState.INSTRUCTIONS) {
            model.setGameState(GameState.MAIN_MENU);
            return true;
        } else if (model.getGameState() == GameState.MAIN_MENU) {
            model.setGameState(GameState.ACTIVE_GAME);
            return true;
        } else if (model.getGameState() == GameState.GAME_OVER) {
            ((Hoytekken)Gdx.app.getApplicationListener()).create();
            return true;
        }
        return false;
    }
}
