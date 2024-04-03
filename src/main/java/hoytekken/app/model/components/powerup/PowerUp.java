package hoytekken.app.model.components.powerup;

import com.badlogic.gdx.graphics.Texture;

import hoytekken.app.model.components.player.Player;

public abstract class PowerUp {
    private final Texture pUpTexture;

    // constructor
    PowerUp(Texture pUpTexture) {
        this.pUpTexture = pUpTexture;
    }

    /**
     * Creates a new power-up, without having to create an object.
     * 
     * @param type char to represent the type of power-up.
     * @throws IllegalArgumentException for undefined types.
     * @return A power-up.
     */
    static PowerUp newPowerUp(PowerUpType type) {
        PowerUp pUp = switch (type) {
            case EXTRA_DAMAGE ->
                new ExtraDamage();
            case EXTRA_LIFE ->
                new ExtraLife();
            case DOUBLE_SPEED ->
                new DoubleSpeed();
            case EXTRA_HEALTH ->
                new ExtraHealth();
            default ->
                throw new IllegalArgumentException("Undefined type for PowerUp");
        };
        return pUp;
    }

    /**
     * applies the power-up, affecting the player.
     * 
     * @param player Player to affect.
     */
    public abstract void applyPowerUp(Player player);

}
