package hoytekken.app.model.components.powerup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import hoytekken.app.model.components.player.Player;

public class DoubleSpeed extends PowerUp {

    protected DoubleSpeed() {
        super(PowerUpType.DOUBLE_SPEED, new Texture(Gdx.files.internal(null)), 'S');
    }

    @Override
    public void applyPowerUp(Player player) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyPowerUp'");
    }

}
