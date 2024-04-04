package hoytekken.app.model.components.tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

import hoytekken.app.model.components.player.PlayerType;

/**
 * Class to detect collisions between objects
 */
public class CollisionDetector implements ContactListener {
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
        // user data for the two fixtures that collided
        Object userDataA = contact.getFixtureA().getUserData();
        Object userDataB = contact.getFixtureB().getUserData();

        // if a players feet collide with another object, reset their double jump
        if (userDataA.equals(playerOne + "feet")
                || userDataB.equals(playerOne + "feet"))
            model.resetDoubleJump(playerOne);

        if (userDataA.equals(playerTwo + "feet")
                || userDataB.equals(playerTwo + "feet"))
            model.resetDoubleJump(playerTwo);
        
        //if a player collides with a powerup, a new powerup is generated
        
        if (userDataA.equals(playerOne) && userDataB.equals("powerup")) {
            System.out.println("Player 1 collided with powerup");
            //model.generatePowerUp();
        }
    }

    @Override
    public void endContact(Contact contact) {
        // ignore end contact
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        // ignore pre solve
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        // ignore post solve
    }

}
