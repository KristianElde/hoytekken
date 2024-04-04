package hoytekken.app.model.components.tools;

import com.badlogic.gdx.physics.box2d.ContactListener;

public abstract class AbstractCollision implements ContactListener {
    protected HandleCollisions model;

    public AbstractCollision(HandleCollisions model) {
        this.model = model;
    }
}
