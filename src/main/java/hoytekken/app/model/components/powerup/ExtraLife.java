package hoytekken.app.model.components.powerup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import hoytekken.app.model.components.player.IPlayer;
import hoytekken.app.model.components.player.Player;

public class ExtraLife extends PowerUp {

    ExtraLife() {
        super(new Texture(Gdx.files.internal("extra_life.png")));
    }

    @Override
    public void applyPowerUp(IPlayer player) {
        player.gainExtraLife();
    }

}
