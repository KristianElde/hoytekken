package hoytekken.app.model.components.powerup;

import hoytekken.app.model.components.player.Player;

public class ExtraHealth extends PowerUp {

    private static Integer HEALTH = 50;

    ExtraHealth() {
        super(null);
    }

    @Override
    public void applyPowerUp(Player player) {
        player.increaseHealth(HEALTH);
    }

}
