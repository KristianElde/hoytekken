package hoytekken.app.model.components.powerup;

import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.Sprite;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import hoytekken.app.Hoytekken;

public class ActivePowerUp extends Sprite {

    private static final float POWERUP_SIZE = 30 / Hoytekken.PPM;

    private String type;
    private PowerUpFactory factory;
    private World world;
    private Body body;
    private Texture texture;
    private PowerUp powerUp;

    public ActivePowerUp(PowerUpFactory factory, World world) {

        this.factory = factory;
        this.world = world;
        this.powerUp = factory.getNext();
        this.type = powerUp.getClass().getSimpleName();
        this.texture = powerUp.getTexture();
        
        setRegion(texture);
        defineBody();
        setBounds(0, 0, POWERUP_SIZE, POWERUP_SIZE);
        positionBody();
        positionTexture();
    }

    private void defineBody() {
        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.StaticBody;
        body = world.createBody(bdef);

        // Set the user data to this object
        body.setUserData(this);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(POWERUP_SIZE / 2, POWERUP_SIZE / 2);
        fdef.shape = shape;
        fdef.isSensor = true;
        body.createFixture(fdef).setUserData(this.type + "powerUp");
    }

    

}
