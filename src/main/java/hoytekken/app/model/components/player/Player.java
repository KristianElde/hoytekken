package hoytekken.app.model.components.player;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import hoytekken.app.Hoytekken;

/**
 * The player class
 */
public class Player extends Sprite implements IPlayer {
    private float MAX_VELOCITY = 2;
    private static final float PLAYER_WIDTH = 45 / Hoytekken.PPM;
    private static final float PLAYER_HEIGHT = 60 / Hoytekken.PPM;
    private static final int JUMPING_HEIGHT = 5;
    private static final float PLAYER_FRICTION_CONSTANT = 3;

    // Constants for player position
    private static final int PLAYER_ONE_START_X = 10;
    private static final int PLAYER_TWO_START_X = 20;
    private static final int PLAYER_START_Y = 14;

    // Constants for player shape
    private static final Vector2[] feetVerts = new Vector2[] {
            new Vector2(-PLAYER_WIDTH / 2, -PLAYER_HEIGHT / 2),
            new Vector2(PLAYER_WIDTH / 2, -PLAYER_HEIGHT / 2),
    };

    // Constants for attack and defense
    private int PUNCH_DAMAGE = 10;
    private int KICK_DAMAGE = 7;
    private static final float PUNCH_RANGE = 1.8f;
    private static final float KICK_RANGE = 2.2f;

    // Player Texture & World
    private World world;
    private Body body;
    private TextureRegion playerStand;
    private static TextureAtlas atlas = new TextureAtlas("Figur1.txt");
    private static TextureAtlas atlas2 = new TextureAtlas("Figur2.txt");

    // Constants for health management
    private static final int MAX_LIVES = 3;

    // Player State
    private PlayerType type;
    private boolean isAlive = true;
    private boolean isBlocking = false;
    private boolean runningRight;
    private int maxHealth;
    private int health;
    private int lives;
    private PlayerState current;
    private PlayerState previous;
    private float stateTimer;

    // Animation
    private Animation<TextureRegion> punchAnimation;
    private Animation<TextureRegion> kickAnimation;


    /**
     * Constructor for the player
     * 
     * @param world  the world
     * @param type   the type of player
     * @param health the health of the player
     */
    public Player(World world, PlayerType type, int health) {
        super(type == PlayerType.PLAYER_ONE 
        ? atlas.findRegion("Character_1_normalStand(60x27)") 
        : atlas2.findRegion("Character_2_normalStand(60x27)"));
        this.world = world;
        this.type = type;
        this.health = health;
        this.maxHealth = health;
        this.lives = MAX_LIVES;
        this.current = PlayerState.STANDING;
        this.previous = PlayerState.STANDING;
        this.stateTimer = 0;
        this.runningRight = true;

        // Punching animation
        Array<TextureRegion> frames = new Array<TextureRegion>();
        frames.add(new TextureRegion(getTexture(), 2178, 0, 666, 1080));
        punchAnimation = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();

        this.playerStand = new TextureRegion(getTexture(), 1026, 0, 486, 1080);

        definePlayer();
        setBounds(0, 0, PLAYER_WIDTH, PLAYER_HEIGHT);
        setRegion(playerStand);
        body.getFixtureList().get(0).setFriction(PLAYER_FRICTION_CONSTANT);
    }

    private void definePlayer() {
        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bdef);
        resetPosistion();

        // Set the user data to this object
        body.setUserData(this);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(PLAYER_WIDTH / 2, PLAYER_HEIGHT / 2);
        fdef.shape = shape;

        body.createFixture(fdef).setUserData(
                type == PlayerType.PLAYER_ONE ? PlayerFixtures.PLAYER_ONE_BODY : PlayerFixtures.PLAYER_TWO_BODY);

        // Foot sensor
        EdgeShape feet = new EdgeShape();
        feet.set(feetVerts[0], feetVerts[1]);
        fdef.shape = feet;
        fdef.isSensor = true;
        body.createFixture(fdef).setUserData(
                type == PlayerType.PLAYER_ONE ? PlayerFixtures.PLAYER_ONE_FEET : PlayerFixtures.PLAYER_TWO_FEET);
    }

    /**
     * Set the position of the player
     */
    private void resetPosistion() {
        // Reset velocity
        body.setLinearVelocity(0, 0);
        // Set the position of the player
        body.setTransform(
                (32 * (type == PlayerType.PLAYER_ONE ? PLAYER_ONE_START_X : PLAYER_TWO_START_X)) / Hoytekken.PPM,
                (32 * PLAYER_START_Y) / Hoytekken.PPM, 0);
    }

    @Override
    public void update(float dt) {
        if (fallenOffTheMap() && this.lives > 0) {
            takeDamage(maxHealth);
            resetPosistion();
        } else if (fallenOffTheMap() && this.lives == 0) {
            takeDamage(maxHealth);
        }
        setPosition(body.getPosition().x - getWidth() / 2,
                body.getPosition().y - getHeight() / 2);
    }

    @Override
    public Body getBody() {
        return body;
    }

    @Override
    public void move(float deltaX, float deltaY) {
        if (deltaX < 0) {
            flipLeft();
        } else if (deltaX > 0) {
            flipRight();
        }
        if (deltaY != 0) {
            body.setLinearVelocity(body.getLinearVelocity().x, 0);
            body.applyLinearImpulse(new Vector2(deltaX, deltaY), body.getWorldCenter(), true);
        } else if (Math.abs(body.getLinearVelocity().x) < MAX_VELOCITY)
            body.applyLinearImpulse(new Vector2(deltaX, deltaY), body.getWorldCenter(), true);
    }

    @Override
    public void takeDamage(int damage) {
        if (this.isAlive) {
            this.health -= damage;

            if (this.health <= 0) {
                if (this.lives > 1) {
                    this.lives--;
                    this.health = maxHealth;
                    resetPosistion();
                } else {
                    this.isAlive = false;
                    this.health = 0;
                    this.lives = 0;
                }
            }
        }
    }

    @Override
    public boolean isAlive() {
        return isAlive;
    }

    @Override
    public int getHealth() {
        return health;
    }

    protected boolean isWithinRange(IPlayer that, float rangeFactor) {
        Vector2 thisPos = new Vector2(getBody().getPosition().x, getBody().getPosition().y);
        Vector2 thatPos = new Vector2(that.getBody().getPosition().x, that.getBody().getPosition().y);

        float distance = thisPos.dst(thatPos);
        float range = PLAYER_WIDTH * rangeFactor;
        return distance <= range;
    }

    /**
     * Perform an attack on another player
     * 
     * @param that   the player that is being attacked
     * @param damage the damage that is being dealt
     * @param range  the range of the attack
     * @return
     */
    private boolean performAttack(IPlayer that, int damage, float range) {
        if (!isWithinRange(that, range) || that.getIsBlocking() || this.getIsBlocking()) {
            return false;
        }
        if (this.isAlive() && that.isAlive()) {
            that.takeDamage(damage);
            return true;
        }
        return false;
    }

    @Override
    public boolean punch(IPlayer that) {
        return performAttack(that, PUNCH_DAMAGE, PUNCH_RANGE);
    }

    @Override
    public boolean kick(IPlayer that) {
        return performAttack(that, KICK_DAMAGE, KICK_RANGE);
    }

    @Override
    public void changeBlockingState() {
        isBlocking = !isBlocking;
    }

    @Override
    public boolean getIsBlocking() {
        return isBlocking;
    }

    @Override
    public boolean fallenOffTheMap() {
        return getBody().getPosition().y < 0;
    }

    @Override
    public int getJumpingHeight() {
        return JUMPING_HEIGHT;
    }

    @Override
    public int getLives() {
        return lives;
    }

    @Override
    public void gainExtraLife() {
        this.lives++;
    }

    @Override
    public void increaseDamage(int increaseAmount) {
        this.PUNCH_DAMAGE += increaseAmount;
        this.KICK_DAMAGE += increaseAmount;
    }

    @Override
    public void increaseSpeed(int increaseAmount) {
        this.MAX_VELOCITY += increaseAmount;
    }

    @Override
    public void increaseHealth(int increaseAmount) {
        this.maxHealth += increaseAmount;
        this.health += increaseAmount;
    }

    @Override
    public void flipLeft() {
        this.setFlip(true, false);
    }

    @Override
    public void flipRight() {
        this.setFlip(false, false);
    }
}
