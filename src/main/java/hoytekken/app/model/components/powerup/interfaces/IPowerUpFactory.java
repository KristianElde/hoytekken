package hoytekken.app.model.components.powerup.interfaces;

import hoytekken.app.model.components.powerup.PowerUp;

/**
 * Interface for power up factory
 */
public interface IPowerUpFactory {
    /**
     * Gets the next power up
     * 
     * @return the power up
     */
    PowerUp getNext();
}
