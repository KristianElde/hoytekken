package hoytekken.app.model.components.ai;

import com.badlogic.gdx.physics.box2d.World;

import hoytekken.app.model.components.player.Player;
import hoytekken.app.model.components.player.PlayerType;

public class AI extends Player {

    private Player target;

    public AI(World world, PlayerType type, int health, Player target) {
        super(world, type, health);
        this.target = target;
    }

}
