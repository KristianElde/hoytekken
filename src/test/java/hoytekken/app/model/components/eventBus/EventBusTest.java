package hoytekken.app.model.components.eventBus;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;

public class EventBusTest {
    private EventBus eventBus;
    private IEventListener mockListener;
    private IEvent mockEvent;
    
    @BeforeEach
    public void setUp() {
        eventBus = new EventBus();
        mockListener = mock(IEventListener.class); 
        mockEvent = mock(IEvent.class);
    }

    

}