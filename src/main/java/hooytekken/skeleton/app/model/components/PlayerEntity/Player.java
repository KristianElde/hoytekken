package hooytekken.skeleton.app.model.components.PlayerEntity;

import hooytekken.skeleton.app.Hoytekken;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * The player class
 */
public class Player extends Sprite implements IPlayer {
    private World world;
    private Body body;
    private TextureRegion playerTexture;

    // The type of player (player one or player two)
    private PlayerType type;

    // Check if the player is alive
    private boolean isAlive = true;

    // Health, if health is 0, player is dead
    private int health = 100;

    private static final float PLAYER_RADIUS = 20 / Hoytekken.PPM;

    public Player(World world, PlayerType type) {
        this.world = world;
        this.type = type;

        definePlayer();
        setBounds(0, 0, 45 / Hoytekken.PPM, 45 / Hoytekken.PPM);
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
        CircleShape shape = new CircleShape();
        shape.setRadius(PLAYER_RADIUS);

        fdef.shape = shape;
        body.createFixture(fdef);
    }

    public void update(float dt) {
        setPosition(body.getPosition().x - playerTexture.getRegionWidth() / 2,
                body.getPosition().y - playerTexture.getRegionHeight() / 2);
    }

    public Body getBody() {
        return body;
    }

    public void move(float deltaX, float deltaY) {
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
}
