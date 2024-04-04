package hoytekken.app.model.components.powerup;

import org.lwjgl.system.MathUtil;

import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer.Random;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class PowerUpSpawn {
    private TiledMap map;
    private RandomPowerUpFactory powerUpFactory;

    public PowerUpSpawn(TiledMap map) {
        this.map = map;
        this.powerUpFactory = new RandomPowerUpFactory();
    }

    public Vector2 getRandomPowerUpSpawn() {
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(0);
        int width = layer.getWidth();
        int height = layer.getHeight();

        int x, y;
        boolean isTileOccupied = true;

        do {
            x = MathUtils.random(width - 1);
            y = MathUtils.random(height - 1);

            //isTileOccupied = layer.getCell(x, y) != null;
            isTileOccupied = checkIfTileIsOccupied(x, y);
            
        } while (isTileOccupied);
        
        Vector2 spawnPosition = new Vector2(x, y);
        return spawnPosition;
    }

    private boolean checkIfTileIsOccupied(int x, int y) {
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(0);
        return layer.getCell(x, y) != null;
        //Possible fail
    }

    // public PowerUp spawnPowerUp() {
    //     Vector2 spawnPosition = getRandomPowerUpSpawn();
    //     return powerUpFactory.createPowerUp(spawnPosition);
    // }


}
