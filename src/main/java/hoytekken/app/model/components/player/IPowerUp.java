package hoytekken.app.model.components.player;

public interface IPowerUp {
    /**
     * A player gains an extra life.
     * 
     */
    void gainExtraLife();

    /**
     * A player gets increased damage.
     * 
     * @param increaseAmount the amount of damage that is being increased
     */
    void increaseDamage(int increaseAmount);

}
