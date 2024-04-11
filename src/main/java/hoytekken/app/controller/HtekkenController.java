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

}
