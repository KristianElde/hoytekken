package hoytekken.app.view.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;

import hoytekken.app.Hoytekken;
import hoytekken.app.model.components.GameState;
import hoytekken.app.model.components.player.PlayerType;
import hoytekken.app.view.ViewableModel;

/**
 * Base class for all screens.
 */
public abstract class BaseScreen implements Screen {
    protected Hoytekken game;
    protected ViewableModel model;

    protected OrthographicCamera gameCam;
    protected FitViewport gamePort;

    /**
     * Constructor for the base screen.
     * @param game the game object
     * @param model the viewable model
     * @param scaling whether the viewport should scale
     */
    public BaseScreen(Hoytekken game, ViewableModel model, boolean scaling) {
        this.game = game;
        this.model = model;

        initializeCameraAndViewport(scaling);

    }

    /**
     * Constructor for the base screen, ViewPort not Scaled.
     * @param game the game object
     * @param model the viewable model
     */
    public BaseScreen(Hoytekken game, ViewableModel model) {
        this(game, model, false);
    }

    private int getWinningPlayer() throws IllegalStateException{
        boolean playerOneWon = model.getPlayer(PlayerType.PLAYER_ONE).isAlive();
        boolean playerTwoWon = model.getPlayer(PlayerType.PLAYER_TWO).isAlive();

        if (playerOneWon && playerTwoWon) throw new IllegalStateException("Both players cannot win at the same time");
        if (!playerOneWon && !playerTwoWon) throw new IllegalStateException("No player has won");

        return playerOneWon && !playerTwoWon ? 1 : 0;
    }

    /**
     * Initializes the camera and viewport.
     * @param scaling whether the viewport should scale
     */
    protected void initializeCameraAndViewport(boolean scaling) {
        gameCam = new OrthographicCamera();
        if (scaling) gamePort = new FitViewport(Hoytekken.V_WIDTH/Hoytekken.PPM, Hoytekken.V_HEIGHT/Hoytekken.PPM, gameCam);
        else gamePort = new FitViewport(Hoytekken.V_WIDTH, Hoytekken.V_HEIGHT, gameCam);
    }

    /**
     * Handles the state switch.
     * If the game state has changed, switches to the appropriate screen.
     */
    protected void handleStateSwitch() {
        if (model.getGameState() == GameState.MAIN_MENU 
            && !(this instanceof MenuScreen)) {
            game.setScreen(new MenuScreen(game, model));
        } else if (model.getGameState() == GameState.INSTRUCTIONS 
            && !(this instanceof InstructionsScreen)) {
            game.setScreen(new InstructionsScreen(game, model));
        } else if (model.getGameState() == GameState.ACTIVE_GAME 
            /*&& !(this instanceof GameScreen)*/) {
            game.setScreen(new GameScreen(game, model));
        } else if (model.getGameState() == GameState.GAME_OVER 
            && !(this instanceof GameOverScreen)) {
            int winningPlayer = getWinningPlayer();
            game.setScreen(new GameOverScreen(game, model, winningPlayer));
        }
    }

    /**
     * Updates the screen. 
     * Class GameScreen should OVERRIDE this method.
     * @param delta the time since the last update
     */
    protected void update(float delta) {
        this.gameCam.update();
        handleStateSwitch();
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {}

    @Override
    public void resize(int width, int height) {
        this.gamePort.update(width, height);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}
    
}
