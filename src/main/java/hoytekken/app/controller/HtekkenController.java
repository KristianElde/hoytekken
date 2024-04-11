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

    // Player types
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

    /**
     * Handles key down events
     * 
     * @param keycode the key code of the key that was pressed
     * @return true if the input was processed
     */
    @Override
    public boolean keyDown(int keycode) {
        switch (model.getGameState()) {
            case ACTIVE_GAME -> handleActiveGameInput(keycode);
            default -> {
                return false;
            }
        }
        return false;
    }

    /**
     * Handles key code events for the main menu state
     * 
     * @param keycode
     */
    private void handleMainMenuInput(int keycode) {
        switch (keycode) {
            case Input.Keys.I -> model.setGameState(GameState.INSTRUCTIONS);
            case Input.Keys.ESCAPE -> Gdx.app.exit();
            case Input.Keys.NUM_1, Input.Keys.NUM_2, Input.Keys.NUM_3, Input.Keys.NUM_4 -> {
                model.setGameMap("map" + (keycode - Input.Keys.NUM_0));
                model.setGameState(GameState.ACTIVE_GAME);
            }
        }
    }

    /**
     * Handles key code events for the active game state
     * 
     * @param keycode
     */
    private void handleActiveGameInput(int keycode) {
        switch (keycode) {
            case Input.Keys.LEFT -> model.setDirection(playerOne, ForceDirection.LEFT);
            case Input.Keys.RIGHT -> model.setDirection(playerOne, ForceDirection.RIGHT);
            case Input.Keys.UP -> model.jump(playerOne);
            case Input.Keys.P -> model.performAttackAction(playerOne, ActionType.PUNCH);
            case Input.Keys.K -> model.performAttackAction(playerOne, ActionType.KICK);
            case Input.Keys.DOWN -> model.getPlayer(playerOne).changeBlockingState();
            case Input.Keys.A -> model.setDirection(playerTwo, ForceDirection.LEFT);
            case Input.Keys.D -> model.setDirection(playerTwo, ForceDirection.RIGHT);
            case Input.Keys.W -> model.jump(playerTwo);
            case Input.Keys.Q -> model.performAttackAction(playerTwo, ActionType.PUNCH);
            case Input.Keys.E -> model.performAttackAction(playerTwo, ActionType.KICK);
            case Input.Keys.S -> model.getPlayer(playerTwo).changeBlockingState();
        }
    }

}
