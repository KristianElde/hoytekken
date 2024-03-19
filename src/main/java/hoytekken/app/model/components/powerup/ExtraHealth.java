package hoytekken.app.model.components.powerup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import hoytekken.app.model.components.player.Player;

public class ExtraHealth extends PowerUp {

    private ExtraHealth() {
        super(new Texture(Gdx.files.internal("extra_health.png")));
    }

    @Override
    public void applyPowerUp(Player player) {
        // make method for setting health in player (maybe make takedamage use this)
        // use it here to set a fixed amount of health
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyPowerUp'");
    }

}
