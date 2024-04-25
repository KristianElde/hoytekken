package hoytekken.app.model.components.eventBus;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    @Test
    public void sanityTest(){
        assertTrue(true);
        assertNotNull(eventBus);
        assertNotNull(mockListener);
        assertNotNull(mockEvent);
    }


}