package hoytekken.app.model.components.player;

import java.util.Random;

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

        // reduce AI speed to make it less aggressive
        increaseSpeed(-1);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        makeDecision();
    }

    //Randomly choose between 0 and 1
    private int randomChoice() {
        return new Random().nextInt(2);
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

    private void idleMovement() {
        int choice = randomChoice();
        if (choice == 0) {
            move(0.5f, 0);
        } else {
            move(-0.5f, 0);
        }
    }

    private void moveTowardsTarget() {
        float dirX = Float.compare(target.getBody().getPosition().x, getBody().getPosition().x);
        move(dirX * 0.5f, 0);
    }
}
