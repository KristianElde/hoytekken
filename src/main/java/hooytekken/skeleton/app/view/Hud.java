package hooytekken.skeleton.app.view;

import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import hooytekken.skeleton.app.Hoytekken;

public class Hud {
    private Stage stage;
    private Viewport port;

    private static final Integer INIT_HEALTH = 99;
    private static final Integer INIT_TIME = 0;

    private Integer playerHealth;
    private Integer battleTimer;
    private Integer enemyHealth;

    private List<Label> labelList;
    private final LabelStyle defaultLabelStyle = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

    private Table upperTable;

    // hvilke interface kan være smarte?
    // for label kanskje
    public Hud(SpriteBatch sb) {
        playerHealth = INIT_HEALTH;
        enemyHealth = INIT_HEALTH;
        battleTimer = INIT_TIME;

        port = new FitViewport(Hoytekken.V_WIDTH, Hoytekken.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(port, sb);
        upperTable = new Table();
        upperTable.top();
        upperTable.setFillParent(true);

        stage.addActor(upperTable);
    }

    private Label createLabel(String text) {
        return new Label(text, defaultLabelStyle);
    }

    public Stage getStage() {
        return stage;
    }

    // getters and setters might be removed for better alternative later
    public Integer getPlayerHealth() {
        return playerHealth;
    }

    public void setPlayerHealth(Integer health) {
        playerHealth = health;
    }

    public Integer getEnemyHealth() {
        return enemyHealth;
    }

    public void setEnemyHealth(Integer health) {
        enemyHealth = health;
    }

}
