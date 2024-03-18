package hoytekken.app.model.components.player;

import static org.mockito.ArgumentMatchers.floatThat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import hoytekken.app.Hoytekken;

/**
 * The player class
 */
public class Player extends Sprite implements IPlayer {
    private static final String DEFAULT_SKIN = "obligator.png";
    private static final float MAX_VELOCITY = 2;
    private static final float PLAYER_WIDTH = 45 / Hoytekken.PPM;
    private static final float PLAYER_HEIGHT = 60 / Hoytekken.PPM;
    private static final int JUMPING_HEIGHT = 5;

    private World world;
    private Body body;
    private Texture playerTexture;

    // The type of player (player one or player two)
    private PlayerType type;

    // Check if the player is alive
    private boolean isAlive = true;

    // Health, if health is 0, player is dead
    private int health;

    // if attack is over limit, block is unsuccessful
    private int blockLimit = 30;

    // if block is unsuccessful, divide attack by this
    private int blockSupresser = 2;

    // max health
    private int maxHealth;

    private int punchDmg = 10;
    private int kickDmg = 7;
    private float punchRange = 1.8f;
    private float kickRange = 2.2f;
    private boolean isBlocking = false;

    /**
     * Constructor for the player
     * 
     * @param world  the world
     * @param type   the type of player
     * @param health the health of the player
     */
    public Player(World world, PlayerType type, int health) {
        this.world = world;
        this.type = type;
        this.health = health;
        this.maxHealth = health;

        this.playerTexture = new Texture(Gdx.files.internal(DEFAULT_SKIN));

        definePlayer();
        setBounds(0, 0, PLAYER_WIDTH, PLAYER_HEIGHT);
        setRegion(playerTexture);
    }

    private void definePlayer() {
        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.DynamicBody;

        bdef.position.set((32 * (type == PlayerType.PLAYER_ONE ? 10 : 20)) / Hoytekken.PPM,
                (32 * 14) / Hoytekken.PPM);

        body = world.createBody(bdef);

        // Set the user data to this object
        body.setUserData(this);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(PLAYER_WIDTH / 2, PLAYER_HEIGHT / 2);
        fdef.shape = shape;
        body.createFixture(fdef).setUserData(this.type + "body");

        // foot sensor
        EdgeShape feet = new EdgeShape();
        feet.set(new Vector2(-PLAYER_WIDTH / 2.1f, -PLAYER_HEIGHT / 2),
                new Vector2(PLAYER_WIDTH / 2.1f, -PLAYER_HEIGHT / 2));
        fdef.shape = feet;
        fdef.isSensor = true;
        body.createFixture(fdef).setUserData(this.type + "feet");
    }

    @Override
    public void update() {
        if (fallenOffTheMap()) {
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
        if (deltaY != 0) {
            body.setLinearVelocity(body.getLinearVelocity().x, 0);
            body.applyLinearImpulse(new Vector2(deltaX, deltaY), body.getWorldCenter(), true);
        } else if (Math.abs(body.getLinearVelocity().x) < MAX_VELOCITY)
            body.applyLinearImpulse(new Vector2(deltaX, deltaY), body.getWorldCenter(), true);
    }

    @Override
    public void takeDamage(int damage) {
        if (this.health - damage <= 0) {
            this.isAlive = false;
            this.health = 0;
        } else {
            this.health -= damage;
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

    private boolean isWithinRange(Player that, float rangeFactor) {
        Vector2 thisPos = new Vector2(getBody().getPosition().x, getBody().getPosition().y);
        Vector2 thatPos = new Vector2(that.getBody().getPosition().x, that.getBody().getPosition().y);

        float distance = thisPos.dst(thatPos);
        float range = PLAYER_WIDTH * rangeFactor;
        return distance <= range;
    }

    @Override
    public boolean punch(Player that) {
        int dmg = punchDmg;
        float rangeFactor = punchRange;

        if (!isWithinRange(that, rangeFactor)) {
            return false;
        }
        if (that.getIsBlocking()) {
            return false;
        }
        if (this.isAlive() && that.isAlive()) {
            that.takeDamage(dmg);
            return true;
        }
        return false;
    }

    @Override
    public boolean kick(Player that) {
        int dmg = kickDmg;
        float rangeFactor = kickRange;
        if (!isWithinRange(that, rangeFactor)) {
            return false;
        }
        if (that.getIsBlocking()) {
            return false;
        }
        if (this.isAlive() && that.isAlive()) {
            that.takeDamage(dmg);
            return true;
        }
        return false;
    }

    @Override
    public void activateBlock() {
        isBlocking = true;
    }

    @Override
    public void deactivateBlock() {
        isBlocking = false;
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
}
