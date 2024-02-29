package hooytekken.skeleton.app.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import hooytekken.skeleton.app.Hoytekken;

public class GameScreen implements Screen {
    private Hoytekken game;

    private Texture img;
    private OrthographicCamera gameCam;
    private Viewport gamePort;

    public GameScreen(Hoytekken game) {
        this.game = game;

        img = new Texture("obligator.png"); 
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(Hoytekken.V_WIDTH / Hoytekken.PPM, Hoytekken.V_HEIGHT / Hoytekken.PPM, gameCam);


    }

    @Override
    public void show() {
        //ignore implementation
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void pause() {
        // ignore implementation
    }

    @Override
    public void resume() {
        // ignore implementation
    }

    @Override
    public void hide() {
        // ignore implementation
    }

    @Override
    public void dispose() {
        // ignore implementation
    }
    
}
