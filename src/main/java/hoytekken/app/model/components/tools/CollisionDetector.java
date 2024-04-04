package hoytekken.app.model.components.tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

import hoytekken.app.model.components.player.PlayerBody;
import hoytekken.app.model.components.player.PlayerType;

/**
 * Class to detect collisions between objects
 */
public class CollisionDetector extends AbstractCollision {
    private final PlayerType playerOne = PlayerType.PLAYER_ONE;
    private final PlayerType playerTwo = PlayerType.PLAYER_TWO;

    /**
     * Constructor for the collision detector
     * 
     * @param model the model to handle the collisions
     */
    public CollisionDetector(HandleCollisions model) {
        super(model);
    }

    @Override
    public void beginContact(Contact contact) {
        Object userDataA = contact.getFixtureA().getUserData();
        Object userDataB = contact.getFixtureB().getUserData();

        if (userDataA != null && userDataB != null) {
            handlePlayerCollisions(userDataA, userDataB);
            handlePowerUpCollision(userDataA, userDataB);
        }
    }

    private void handlePlayerCollisions(Object userDataA, Object userDataB) {
        if (userDataA instanceof PlayerType && userDataB instanceof PlayerBody) {
            handlePlayerCollision((PlayerType) userDataA, (PlayerBody) userDataB);
        } else if (userDataB instanceof PlayerType && userDataA instanceof PlayerBody) {
            handlePlayerCollision((PlayerType) userDataB, (PlayerBody) userDataA);
        }
    }

    private void handlePlayerCollision(PlayerType playerType, PlayerBody playerBody) {
        switch (playerBody) {
            case FEET:
                feetTouched(playerType);
                break;
            case BODY:
                break;
            default:
                break;
        }
    }

    private void feetTouched(PlayerType playerType) {
        model.resetDoubleJump(playerType);
    }

    private void handlePowerUpCollision(Object userDataA, Object userDataB) {
        // Implement power-up collision handling if needed
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
