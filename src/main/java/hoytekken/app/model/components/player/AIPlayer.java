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

    //AI Actions
    private boolean chase = false;
    private boolean idleMovement = false;
    private boolean block = false;

    // Action timers
    private float movementTimer = 0;
    private float blockTimer = 0;
    private float lastBlockTimer = 0;

    // improve fluidity of idle movement
    private int moveTicks = 0;
    private int lastDir = 0;

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
        int decide = randomChoice();

        if (isWithinRange(target, PUNCH_RANGE) && !block) {
            punch(target);
        } else if (isWithinRange(target, KICK_RANGE) && !block) {
            kick(target);
        } else if (!(idleMovement || chase)) {
            chooseMovement();
        }
    }

    private void attackOrDefend(boolean punchRange, int decide) {
        if (decide == 0) {
            if (punchRange) punch(target);
            else kick(target);
        } else if (lastBlockTimer > 2) startBlock();
    }

    private void checkTimers() {
        if (block && blockTimer > 2) stopBlock();
        if (idleMovement && movementTimer > 2) idleMovement = false;
        if (chase && movementTimer > 3) chase = false;
    }

    private void stopBlock() {
        block = false;
        lastBlockTimer = 0;
        changeBlockingState();
    }

    private void startBlock() {
        block = true;
        blockTimer = 0;
        changeBlockingState();
    }

    private void chooseMovement() {
        movementTimer = 0;
        if (randomChoice() == 0) idleMovement = true;
        else chase = true;
    }

    private void idleMovement() {
        int choice = randomChoice();
        if (!(moveTicks % 30 == 0)) choice = lastDir;

        if (choice == 0) {
            lastDir = 0;
            move(0.15f, 0);
        } else {
            lastDir = 1;
            move(-0.15f, 0);
        }
    }

    private void moveTowardsTarget() {
        float dirX = Float.compare(target.getBody().getPosition().x, getBody().getPosition().x);
        move(dirX * 0.5f, 0);
    }
}
