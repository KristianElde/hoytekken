package hoytekken.app.model.components.powerup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import hoytekken.app.model.components.player.Player;

public class DoubleSpeed extends PowerUp {

    private static Integer SPEED = 1;

    DoubleSpeed() {
        super(new Texture(Gdx.files.internal("double_speed.png")));
    }

    @Override
    public void applyPowerUp(Player player) {
        player.increaseSpeed(SPEED);
    }

}
