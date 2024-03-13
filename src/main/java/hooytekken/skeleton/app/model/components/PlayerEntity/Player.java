package hooytekken.skeleton.app.model.components.PlayerEntity;

import hooytekken.skeleton.app.Hoytekken;

import org.w3c.dom.css.Rect;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * The player class
 */
public class Player extends Sprite implements IPlayer {
    private static final String DEFAULT_SKIN = "obligator.png";
    private static final float MAX_VELOCITY = 2;

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

    private static final float PLAYER_RADIUS = 20 / Hoytekken.PPM;

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

        this.playerTexture = new Texture(DEFAULT_SKIN);

        definePlayer();
        setBounds(0, 0, 45 / Hoytekken.PPM, 45 / Hoytekken.PPM);
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
        //CircleShape shape = new CircleShape();
        //shape.setRadius(PLAYER_RADIUS);
        //change shape to a rectangle instead of a circle
        
        PolygonShape shape = new PolygonShape();

        // Set the shape to be a box of the desired width and height
        float width = 45 / Hoytekken.PPM; // replace with desired width
        float height = 45 / Hoytekken.PPM; // replace with desired height
        shape.setAsBox(width / 2, height / 2); // setAsBox takes half-width and half-height as arguments


        fdef.shape = shape;
        body.createFixture(fdef);
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
        if (deltaY != 0)
            body.applyLinearImpulse(new Vector2(deltaX, deltaY), body.getWorldCenter(), true);
        else if (Math.abs(body.getLinearVelocity().x) < MAX_VELOCITY)
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

    private boolean isWithinRange(Player that) {
        Vector2 thisPos = new Vector2(getBody().getPosition().x, getBody().getPosition().y);
        Vector2 thatPos = new Vector2(that.getBody().getPosition().x, that.getBody().getPosition().y);

        float distance = thisPos.dst(thatPos);
        float range = PLAYER_RADIUS * 2; // attack-length is the same as length of body
        return distance <= range;
    }

    @Override
    public boolean punch(Player that, int dmg) {
        if (!isWithinRange(that)) {
            return false;
        }
        if (this.isAlive() && that.isAlive()) {
            that.takeDamage(dmg);
            return true;
        }
        return false;
    }

    @Override
    public boolean kick(Player that, int dmg) {
        if (!isWithinRange(that)) {
            return false;
        }
        if (this.isAlive() && that.isAlive()) {
            that.takeDamage(dmg);
            return true;
        }
        return false;
    }

    @Override
    public boolean block(Player that, int incomingAttack) {
        if (this.isAlive() && incomingAttack > blockLimit && isWithinRange(that)) {
            this.takeDamage(incomingAttack / blockSupresser);
            return false;
        }
        return true;
    }

    @Override
    public boolean fallenOffTheMap() {
        return getBody().getPosition().y < 0;
    }
}
