package hoytekken.app.model.components.eventBus;

public interface IEventListener {
    
    /**
     * Handles an event
     * @param event the event to handle
     */
    void handleEvent (IEvent event);
}
