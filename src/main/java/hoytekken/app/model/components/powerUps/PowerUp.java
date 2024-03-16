package hoytekken.app.model.components.powerUps;

public final class PowerUp {
    private final PowerUpType pUpType;

    private PowerUp(PowerUpType pUpType) {
        this.pUpType = pUpType;
    }

    static PowerUp newPowerUp(Character type) {
        PowerUp pUp = switch (type) {
            case 'D' -> new PowerUp(PowerUpType.EXTRA_DAMAGE);
            case 'H' -> new PowerUp(PowerUpType.EXTRA_HEALTH);
            case 'S' -> new PowerUp(PowerUpType.DOUBLE_SPEED);
            default -> throw new IllegalArgumentException("Undefined type for PowerUp");
        };
        return pUp;
    }

}
