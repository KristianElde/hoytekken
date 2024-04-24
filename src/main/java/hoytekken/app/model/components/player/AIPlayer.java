package hoytekken.app.model.components.player;

import com.badlogic.gdx.physics.box2d.World;

/**
 * AIPlayer class that represents an AI player
 * Extends {@code Player.Class} and introduces automated decision making
 */
public class AIPlayer extends Player {
    private static final float PUNCH_RANGE = 1.8f;
    private static final float KICK_RANGE = 2.2f;
    private final IPlayer target;

    /**
     * Constructor for AIPlayer
     * @param world the game world
     * @param type the player type
     * @param health the player health
     * @param target the target player
     */
    public AIPlayer(World world, PlayerType type, int health, IPlayer target) {
        super(world, type, health);
        this.target = target;
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        makeDecision();
    }

    private void makeDecision() {
        if (isWithinRange(target, PUNCH_RANGE)) {
            punch(target);
        } else if (isWithinRange(target, KICK_RANGE)) {
            kick(target);
        } else {
            moveTowardsTarget();
        }
    }

    private void moveTowardsTarget() {
        float dirX = Float.compare(target.getBody().getPosition().x, getBody().getPosition().x);
        move(dirX * 0.5f, 0);
    }
}
