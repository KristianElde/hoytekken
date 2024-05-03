package hoytekken.app.model.components.powerup;

import java.util.Random;

import hoytekken.app.model.components.powerup.enums.PowerUpType;
import hoytekken.app.model.components.powerup.interfaces.IPowerUpFactory;

/**
 * Random power up factory
 */
public class RandomPowerUpFactory implements IPowerUpFactory {

    @Override
    public PowerUp getNext() {
        Random rand = new Random();
        int index = rand.nextInt(PowerUpType.values().length);
        PowerUpType type = PowerUpType.values()[index];
        return PowerUp.newPowerUp(type);
    }
}
