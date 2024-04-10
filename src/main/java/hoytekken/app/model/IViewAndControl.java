package hoytekken.app.model;

import hoytekken.app.model.components.eventBus.EventBus;

public interface IViewAndControl {

    /**
     * Gets the event bus, stored in model
     * @return the event bus
     */
    public EventBus getEventBus();
}
