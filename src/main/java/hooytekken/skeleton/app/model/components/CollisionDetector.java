package hooytekken.skeleton.app.model.components;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class CollisionDetector implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();
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
