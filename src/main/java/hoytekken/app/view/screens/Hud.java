package hoytekken.app.view.screens;

import java.util.ArrayList;
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

import hoytekken.app.Hoytekken;

/**
 * class represents the heads-up display for the game
 */
public class Hud {
    private Stage stage;
    private Viewport port;

    private static final Integer INIT_HEALTH = 100;
    private static final Integer INIT_TIME = 0;

    private Integer playerHealth;
    private Integer battleTimer;
    private Integer enemyHealth;

    private List<Label> labelList;
    private final LabelStyle defaultLabelStyle = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

    private Table upperTable;

    /**
     * Constructor for the heads-up display
     * 
     * @param sb
     */
    public Hud(SpriteBatch sb) {
        playerHealth = INIT_HEALTH;
        enemyHealth = INIT_HEALTH;
        battleTimer = INIT_TIME;

        port = new FitViewport(Hoytekken.V_WIDTH, Hoytekken.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(port, sb);
        upperTable = new Table();
        upperTable.top();
        upperTable.setFillParent(true);

        labelList = initLabels();
        addLabelsToTable(labelList, upperTable);

        stage.addActor(upperTable);
    }

    private Label createLabel(String text) {
        return new Label(text, defaultLabelStyle);
    }

    // better to save labels as field variables?
    private List<Label> initLabels() {
        List<Label> list = new ArrayList<>();
        list.add(createLabel("PLAYER"));
        list.add(createLabel("TIME"));
        list.add(createLabel("ENEMY"));
        // next lines might have to change due to updating the variable
        list.add(createLabel(String.format("%02d", playerHealth)));
        list.add(createLabel(String.format("%03d", battleTimer)));
        list.add(createLabel(String.format("%02d", enemyHealth)));
        return list;
    }

    private void addLabelsToTable(List<Label> labels, Table table) {
        Integer counter = 0;
        for (Label label : labels) {
            counter++;
            table.add(label).expandX().padTop(5);
            if (counter == 3) {
                table.row();
            }
        }
    }

    /**
     * Getter for the stage
     * 
     * @return the stage
     */
    public Stage getStage() {
        return stage;
    }

    // getters and setters might be removed for better alternative later

    /**
     * Getter for the player's health
     * 
     * @return Integer representing the player's health
     */
    public Integer getPlayerHealth() {
        return playerHealth;
    }

    /**
     * Setter for the player's health
     * 
     * @param health
     */
    public void setPlayerHealth(Integer health) {
        labelList.get(3).setText(String.format("%02d", health));
    }

    /**
     * Getter for other enemy's health
     * 
     * @return Integer representing the enemy's health
     */
    public Integer getEnemyHealth() {
        return enemyHealth;
    }

    /**
     * Setter for the enemy's health
     * 
     * @param health
     */
    public void setEnemyHealth(Integer health) {
        labelList.get(5).setText(String.format("%02d", health));
    }
}
