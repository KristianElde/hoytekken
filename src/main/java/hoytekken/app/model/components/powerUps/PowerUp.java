package hoytekken.app.model.components.powerUps;

public final class PowerUp {
    private final PowerUpType pUpType;

    private PowerUp(PowerUpType pUpType) {
        this.pUpType = pUpType;
    }

    static PowerUp newPowerUp(PowerUpType type) {
        PowerUp pUp = switch (type) {
            case EXTRA_DAMAGE -> new PowerUp(type);
            case EXTRA_HEALTH -> new PowerUp(type);
            case DOUBLE_SPEED -> new PowerUp(type);
        }
        return pUp;
    }

}
