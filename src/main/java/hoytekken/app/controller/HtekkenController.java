package hoytekken.app.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

import hoytekken.app.Hoytekken;
import hoytekken.app.model.components.ForceDirection;
import hoytekken.app.model.components.GameState;
import hoytekken.app.model.components.player.PlayerType;

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
            model.performAttackAction(playerOne, ActionType.PUNCH);
        if (keycode == Input.Keys.K)
            model.performAttackAction(playerOne, ActionType.KICK);
        if (keycode == Input.Keys.B)
            // implement this

            // Player2, A, D, W, PUNCH, BLOCK, KICK keys
            if (keycode == Input.Keys.A)
                model.setDirection(playerTwo, ForceDirection.LEFT);
        if (keycode == Input.Keys.D)
            model.setDirection(playerTwo, ForceDirection.RIGHT);
        if (keycode == Input.Keys.W)
            model.jump(playerTwo);
        if (keycode == Input.Keys.Q)
            model.performAttackAction(playerTwo, ActionType.PUNCH);
        if (keycode == Input.Keys.E)
            model.performAttackAction(playerTwo, ActionType.KICK);
        if (keycode == Input.Keys.S) {
            // implement this
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {

        // Stop applying force to the player when the key is released
        if (keycode == Input.Keys.LEFT && model.getDirection(playerOne) == ForceDirection.LEFT
                || keycode == Input.Keys.RIGHT && model.getDirection(playerOne) == ForceDirection.RIGHT) {
            model.setDirection(playerOne, ForceDirection.STATIC);
            return true;
        } else if (keycode == Input.Keys.A && model.getDirection(playerTwo) == ForceDirection.LEFT
                || keycode == Input.Keys.D && model.getDirection(playerTwo) == ForceDirection.RIGHT) {
            model.setDirection(playerTwo, ForceDirection.STATIC);
            return true;
        } else
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
            ((Hoytekken) Gdx.app.getApplicationListener()).create();
            return true;
        }
        return false;
    }
}
