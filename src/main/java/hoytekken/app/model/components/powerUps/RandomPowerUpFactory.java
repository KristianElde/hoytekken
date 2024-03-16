package hoytekken.app.model.components.powerUps;

import java.util.Random;

public class RandomPowerUpFactory implements PowerUpFactory {

    @Override
    public PowerUp getNext() {
        String pUpString = "SDH";
        Random rand = new Random();
        Character c = pUpString.charAt(rand.nextInt(pUpString.length()));
        return PowerUp.newPowerUp(c);

    }

}
