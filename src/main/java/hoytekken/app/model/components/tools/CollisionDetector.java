package hoytekken.app.model.components.tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Manifold;

import hoytekken.app.model.components.player.PlayerFixtures;
import hoytekken.app.model.components.player.PlayerType;

/**
 * Class to detect collisions between objects
 */
public class CollisionDetector extends AbstractCollision {
    /**
     * Constructor for the collision detector
     * 
     * @param model the model to handle the collisions
     */
    public CollisionDetector(HandleCollisions model) {
        super(model);
    }

    /**
     * Handles the beginning of contact between two fixtures.
     * 
     * @param contact the contact information
     */
    @Override
    public void beginContact(Contact contact) {
        PlayerFixtures userDataA = (PlayerFixtures) contact.getFixtureA().getUserData();
        PlayerFixtures userDataB = (PlayerFixtures) contact.getFixtureB().getUserData();

        if (userDataA != null && userDataB != null) {
            handlePlayerCollisions(userDataA, userDataB);
            handlePowerUpCollision(userDataA, userDataB);
        }
    }

    private void handlePlayerCollisions(Object userDataA, Object userDataB) {
        PlayerType playerType = userDataA.equals(PlayerFixtures.PLAYER_ONE_FEET) ? PlayerType.PLAYER_ONE
                : PlayerType.PLAYER_TWO;
        PlayerFixtures playerBody = userDataB.equals(PlayerFixtures.PLAYER_ONE_FEET) ? PlayerFixtures.PLAYER_ONE_FEET
                : PlayerFixtures.PLAYER_TWO_FEET;

        if (userDataA.equals(PlayerFixtures.PLAYER_ONE_FEET) || userDataA.equals(PlayerFixtures.PLAYER_TWO_FEET)) {
            handlePlayerCollision(playerType, playerBody);
        }

        if (userDataB.equals(PlayerFixtures.PLAYER_ONE_FEET) || userDataB.equals(PlayerFixtures.PLAYER_TWO_FEET)) {
            handlePlayerCollision(playerType, playerBody);
        }
    }

    /**
     * Handles collision events involving player bodies.
     * 
     * @param playerType the type of player
     * @param playerBody the body part of the player involved in collision
     */
    private void handlePlayerCollision(PlayerType playerType, PlayerFixtures playerBody) {
        switch (playerBody) {
            case PLAYER_ONE_FEET:
            case PLAYER_TWO_FEET:
                feetTouched(playerType);
                break;
            case PLAYER_ONE_BODY:
            case PLAYER_TWO_BODY:
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
