package hoytekken.app.model;

import hoytekken.app.model.components.eventBus.EventBus;

import java.util.HashMap;

public interface IViewAndControl {

    /**
     * Gets the event bus, stored in model
     * @return the event bus
     */
    public EventBus getEventBus();

    /**
     * Method to get the maps for the game
     * 
     * @return the maps for the game
     */
    public HashMap<String, String> getGameMaps();
}
