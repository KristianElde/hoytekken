package hoytekken.app.model.components.powerup;

import com.badlogic.gdx.graphics.Texture;

import hoytekken.app.model.components.player.Player;

public abstract class PowerUp {
    protected final PowerUpType pUpType;
    protected final Texture pUpTexture;
    protected final char symbol;

    protected PowerUp(PowerUpType pUpType, Texture pUpTexture, char symbol) {
        this.symbol = symbol;
        this.pUpType = pUpType;
        this.pUpTexture = pUpTexture;
    }

    /**
     * Creates a new power-up, without having to create an object.
     * 
     * @param type char to represent the type of power-up.
     * @throws IllegalArgumentException for undefined types.
     * @return A power-up.
     */
    static PowerUp newPowerUp(Character type) {
        PowerUp pUp = switch (type) {
            case 'D' ->
                new ExtraDamage();
            case 'H' ->
                new ExtraHealth();
            case 'S' ->
                new DoubleSpeed();
            default ->
                throw new IllegalArgumentException("Undefined type for PowerUp");
        };
        return pUp;
    }

    public abstract void applyPowerUp(Player player);

}
