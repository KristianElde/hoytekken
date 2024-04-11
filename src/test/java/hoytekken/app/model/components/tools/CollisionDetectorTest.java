package hoytekken.app.model.components.tools;

import org.mockito.Mock;
import org.mockito.Mockito;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class CollisionDetectorTest {
    private CollisionDetector collisionDetector;
    private HandleCollisions model;

    @Mock
    private Contact mockContact = Mockito.mock(Contact.class);

    @Mock
    private Fixture mockFixtureA = Mockito.mock(Fixture.class), mockFixtureB = Mockito.mock(Fixture.class);

    @Mock
    private Manifold mockMan = Mockito.mock(Manifold.class);

    @Mock
    private ContactImpulse mockImpulse = Mockito.mock(ContactImpulse.class);
}
