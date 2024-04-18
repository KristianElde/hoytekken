package hoytekken.app.model.components.eventBus;

import java.util.concurrent.CopyOnWriteArrayList;

public class EventBus {
    private CopyOnWriteArrayList<IEventListener> listeners = new CopyOnWriteArrayList<>();

    public void addListener(IEventListener listener) {
        listeners.add(listener);
    }

    public void removeListener(IEventListener listener) {
        listeners.remove(listener);
    }

    public void emitEvent(IEvent event) {
        for (IEventListener listener : listeners) {
            listener.handleEvent(event);
        }
    }

}
