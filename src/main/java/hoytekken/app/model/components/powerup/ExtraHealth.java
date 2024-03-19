package hoytekken.app.model.components.powerup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import hoytekken.app.model.components.player.Player;

public class ExtraHealth extends PowerUp {

    protected ExtraHealth() {
        super(PowerUpType.EXTRA_HEALTH, new Texture(Gdx.files.internal(null)), 'H');
    }

    @Override
    public void applyPowerUp(Player player) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyPowerUp'");
    }

}
