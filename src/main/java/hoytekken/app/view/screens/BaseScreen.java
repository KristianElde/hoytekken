package hoytekken.app.view.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;

import hoytekken.app.Hoytekken;
import hoytekken.app.view.ViewableModel;

public abstract class BaseScreen implements Screen {
    protected Hoytekken game;
    protected ViewableModel model;

    protected OrthographicCamera gameCam;
    protected FitViewport gamePort;


    public BaseScreen(Hoytekken game, ViewableModel model, boolean scaling) {
        this.game = game;
        this.model = model;

        initializeCameraAndViewport(scaling);

    }

    public BaseScreen(Hoytekken game, ViewableModel model) {
        this(game, model, false);
    }

    protected void initializeCameraAndViewport(boolean scaling) {
        gameCam = new OrthographicCamera();
        if (scaling) gamePort = new FitViewport(Hoytekken.V_WIDTH/Hoytekken.PPM, Hoytekken.V_HEIGHT/Hoytekken.PPM, gameCam);
        else gamePort = new FitViewport(Hoytekken.V_WIDTH, Hoytekken.V_HEIGHT, gameCam);
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
