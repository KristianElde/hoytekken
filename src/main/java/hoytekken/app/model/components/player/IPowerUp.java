package hoytekken.app.model.components.player;

public interface IPowerUp {
    /**
     * A player gains an extra life.
     * 
     * @param the player to receive an extra life.
     * @return bool on if player received an extra life.
     */
    void gainExtraLife(IPlayer that);

}
