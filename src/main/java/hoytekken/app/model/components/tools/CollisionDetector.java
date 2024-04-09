package hoytekken.app.model.components.tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Manifold;

import hoytekken.app.model.components.player.Player;
import hoytekken.app.model.components.player.PlayerFixtures;
import hoytekken.app.model.components.player.PlayerType;
import hoytekken.app.model.components.powerup.ActivePowerUp;
import hoytekken.app.model.components.powerup.PowerUp;

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
        Object userDataA = contact.getFixtureA().getUserData();
        Object userDataB = contact.getFixtureB().getUserData();
        System.out.println("Collision detected between " + userDataA + " and " + userDataB);

        if (userDataA != null && userDataB != null) {
            handlePlayerCollisions(userDataA, userDataB);
            handlePowerUpCollision(userDataA, userDataB);
        }
    }

    /**
     * Determines the type of player based on the user data.
     * 
     * @param userData the user data of the fixture
     * @return the type of player
     */
    private PlayerType getPlayerType(Object userData) {
        return userData.equals(PlayerFixtures.PLAYER_ONE_FEET) ? PlayerType.PLAYER_ONE : PlayerType.PLAYER_TWO;
    }

    /**
     * Determines the player fixture based on the user data.
     * 
     * @param userData
     */
    private PlayerFixtures getPlayerFixture(Object userData) {
        return userData.equals(PlayerFixtures.PLAYER_ONE_FEET) ? PlayerFixtures.PLAYER_ONE_FEET
                : PlayerFixtures.PLAYER_TWO_FEET;
    }

    /**
     * Handles collisions involving player bodies.
     * 
     * @param userDataA the user data of fixture A
     * @param userDataB the user data of fixture B
     */
    private void handlePlayerCollisions(Object userDataA, Object userDataB) {
        PlayerType playerTypeA = this.getPlayerType(userDataA);
        PlayerFixtures playerFixtureA = this.getPlayerFixture(userDataA);

        PlayerType playerTypeB = this.getPlayerType(userDataB);
        PlayerFixtures playerFixtureB = this.getPlayerFixture(userDataB);

        if (userDataA.equals(playerFixtureA) || userDataB.equals(playerFixtureA)) {
            handlePlayerCollision(playerTypeA, playerFixtureA);
        }

        if (userDataA.equals(playerFixtureB) || userDataB.equals(playerFixtureB)) {
            handlePlayerCollision(playerTypeB, playerFixtureB);
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

    /**
     * Resets double jump if player's feet touch something.
     * 
     * @param playerType the type of player
     */
    private void feetTouched(PlayerType playerType) {
        model.resetDoubleJump(playerType);
    }

    /**
     * Handles collisions involving power-ups.
     * 
     * @param userDataA the user data of fixture A
     * @param userDataB the user data of fixture B
     */
    private void handlePowerUpCollision(Object userDataA, Object userDataB) {
        // If the player body is colliding with a power-up. The powerUp should disappear and the player should get the power-up
        if (userDataA instanceof Player && userDataB instanceof ActivePowerUp) {
            
            PowerUp powerUp = (PowerUp) userDataB;
            powerUp.applyPowerUp((Player) userDataA);
            model.destroyPowerUp();
        }
        else if (userDataB instanceof Player && userDataA instanceof ActivePowerUp) {
            
            PowerUp powerUp = (PowerUp) userDataA;
            powerUp.applyPowerUp((Player) userDataB);
            model.destroyPowerUp();
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
