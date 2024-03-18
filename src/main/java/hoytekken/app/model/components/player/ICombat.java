package hoytekken.app.model.components.player;

/**
 * Interface for the combat actions that a player can perform
 */
public interface ICombat {
    /**
     * A player will punch another player and
     * inflict damage on that player.
     * Will only hit if other player is in range.
     * 
     * @param that the player to punch
     * @return bool on if player was hit
     */
    boolean punch(Player that);

    /**
     * A player will kick another player and
     * inflilct damage on that player.
     * Will only hit if other player is in range.
     * 
     * @param that the player to kick
     * @return bool on if player was hit
     */
    boolean kick(Player that);

    boolean activateBlock();

    boolean deactivateBlock();

    boolean getIsBlocking();
}
