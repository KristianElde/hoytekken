package hoytekken.app.model.components.powerup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import hoytekken.app.model.components.player.IPlayer;
import hoytekken.app.model.components.player.Player;

public class DoubleSpeed extends PowerUp {

    private static final Integer SPEED = 1;

    DoubleSpeed() {
        super(new Texture(Gdx.files.internal("double_speed.png")));
    }

    @Override
    public void applyPowerUp(IPlayer player) {
        player.increaseSpeed(SPEED);
    }

}
