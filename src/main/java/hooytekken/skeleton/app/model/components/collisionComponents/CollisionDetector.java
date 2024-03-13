package hooytekken.skeleton.app.model.components.collisionComponents;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

<<<<<<< HEAD
import hooytekken.skeleton.app.model.components.PlayerEntity.PlayerType;

=======
>>>>>>> 4fe36dc (added folder for collisionComponents, moved CollisionDetector class)
/**
 * Class to detect collisions between objects
 */
public class CollisionDetector implements ContactListener {
<<<<<<< HEAD
    private HandleCollisions model;

    private final PlayerType playerOne = PlayerType.PLAYER_ONE;
    private final PlayerType playerTwo = PlayerType.PLAYER_TWO;

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
        Object userDataA = contact.getFixtureA().getBody().getUserData();
        Object userDataB = contact.getFixtureB().getBody().getUserData();
        System.out.println("Collision between " + userDataA + " and " + userDataB);

        if (userDataA.equals(playerOne + "feet")
            || userDataB.equals(playerOne + "feet")) 
            model.resetDoubleJump(playerOne);

        if (userDataA.equals(playerTwo + "feet")
            || userDataB.equals(playerTwo + "feet")) 
            model.resetDoubleJump(playerTwo);
=======

    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();
        System.out.println("Collision between " + fixtureA.getBody().getUserData() + " and " + fixtureB.getBody().getUserData());

        //TODO: handle collision
        // check which player is involved in the collision
        // if player feet collides with ground, set player to grounded, reset double jump
>>>>>>> 4fe36dc (added folder for collisionComponents, moved CollisionDetector class)
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
