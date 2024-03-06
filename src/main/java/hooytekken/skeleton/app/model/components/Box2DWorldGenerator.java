package hooytekken.skeleton.app.model.components;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;


public class Box2DWorldGenerator {
    private World world;
    private TiledMap map;
    
    public Box2DWorldGenerator(World world, TiledMap map) {
        this.world = world;
        this.map = map;
        generate();
    }

    /**
     * Generate box2d bodies from the map
     */
    private void generate() {
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body = null;
    }



    
}

