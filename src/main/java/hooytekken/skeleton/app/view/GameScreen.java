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

    private Hud hud;

    public GameScreen(Hoytekken game) {
        this.game = game;

        img = new Texture("obligator.png"); 
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(Hoytekken.V_WIDTH / Hoytekken.PPM, Hoytekken.V_HEIGHT / Hoytekken.PPM, gameCam);

        gameCam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2, 0);

        hud = new Hud(game.batch);
    }

    @Override
    public void show() {
        //ignore implementation
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(hud.getStage().getCamera().combined);
        hud.getStage().draw();

        game.batch.begin();
        game.batch.draw(img, 0, 0);
        game.batch.end();
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
