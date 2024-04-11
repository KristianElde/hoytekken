package hoytekken.app.model.components.powerup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import hoytekken.app.model.components.player.IPlayer;
import hoytekken.app.model.components.player.Player;

public class ExtraDamage extends PowerUp {
    private static Integer DAMAGE = 20;

    ExtraDamage() {
        super(new Texture(Gdx.files.internal("extra_damage.png")));
    }

    @Override
    public void applyPowerUp(IPlayer player) {
        player.increaseDamage(DAMAGE);
    }

}
