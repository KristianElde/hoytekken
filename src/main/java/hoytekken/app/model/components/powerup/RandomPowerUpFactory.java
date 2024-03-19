package hoytekken.app.model.components.powerup;

import java.util.Random;

public class RandomPowerUpFactory implements PowerUpFactory {

    @Override
    public PowerUp getNext() {
        String pUpString = "SDH";
        Random rand = new Random();
        int index = rand.nextInt(pUpString.length());
        Character c = pUpString.charAt(index);
        return PowerUp.newPowerUp(c);
    }
}
