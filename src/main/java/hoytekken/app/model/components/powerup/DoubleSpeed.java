package hoytekken.app.model.components.powerup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import hoytekken.app.model.components.player.Player;

public class DoubleSpeed extends PowerUp {

    DoubleSpeed() {
        super(new Texture(Gdx.files.internal("double_speed.png")));
    }

    @Override
    public void applyPowerUp(Player player) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyPowerUp'");
    }

}
