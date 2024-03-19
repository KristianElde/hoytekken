package hoytekken.app.model.components.powerup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import hoytekken.app.model.components.player.Player;

public class ExtraDamage extends PowerUp {

    private ExtraDamage() {
        super(new Texture(Gdx.files.internal("extra_damage.png")));
    }

    static PowerUp createPowerUp() {
        return new ExtraDamage();
    }

    @Override
    public void applyPowerUp(Player player) {
        // setDamage in Player?
        // player.setDamage(player.getDamage() + fixedAmount)
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyPowerUp'");
    }

}
