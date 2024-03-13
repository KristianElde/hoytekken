package hooytekken.skeleton.app.model.components.collisionComponents;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

/**
 * Class to detect collisions between objects
 */
public class CollisionDetector implements ContactListener {
    private HandleCollisions model;

    /**
     * Constructor for the collision detector
     * 
     * @param model the model to handle the collisions
     */
    public CollisionDetector(HandleCollisions model) {
        this.model = model;
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();
        System.out.println("Collision between " + fixtureA.getBody().getUserData() + " and " + fixtureB.getBody().getUserData());

        //TODO: handle collision
        // check which player is involved in the collision
        // if player feet collides with ground, set player to grounded, reset double jump
    }

    @Override
    public void endContact(Contact contact) {
        //ignore end contact
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        //ignore pre solve
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        //ignore post solve
    }
    
}
