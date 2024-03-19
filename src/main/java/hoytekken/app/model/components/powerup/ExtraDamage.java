package hoytekken.app.model.components.powerup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import hoytekken.app.model.components.player.Player;

public class ExtraDamage extends PowerUp {

    protected ExtraDamage() {
        super(PowerUpType.EXTRA_DAMAGE, new Texture(Gdx.files.internal(null)), 'D');
    }

    @Override
    public void applyPowerUp(Player player) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyPowerUp'");
    }

}
