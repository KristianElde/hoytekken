package hoytekken.app.model.components.powerup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import hoytekken.app.model.components.player.Player;

public abstract class PowerUp {
    protected final PowerUpType pUpType;
    protected final Texture pUpTexture;

    protected PowerUp(PowerUpType pUpType, Texture pUpTexture) {
        this.pUpType = pUpType;
        this.pUpTexture = pUpTexture;
    }

    static PowerUp newPowerUp(Character type) {
        PowerUp pUp = switch (type) {
            case 'D' ->
                new ExtraDamage(null, null);
            case 'H' ->
                new ExtraHealth(null, null);
            case 'S' ->
                new DoubleSpeed(null, null);
            default ->
                throw new IllegalArgumentException("Undefined type for PowerUp");
        };
        return pUp;
    }

    public abstract void applyPowerUp(Player player);

}
