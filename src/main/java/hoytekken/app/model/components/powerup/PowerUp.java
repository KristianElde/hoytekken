package hoytekken.app.model.components.powerup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

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
                new PowerUp(PowerUpType.EXTRA_DAMAGE, new Texture(Gdx.files.internal(null)));
            case 'H' ->
                new PowerUp(PowerUpType.EXTRA_HEALTH, new Texture(Gdx.files.internal(null)));
            case 'S' ->
                new PowerUp(PowerUpType.DOUBLE_SPEED, new Texture(Gdx.files.internal(null)));
            default ->
                throw new IllegalArgumentException("Undefined type for PowerUp");
        };
        return pUp;
    }

}
