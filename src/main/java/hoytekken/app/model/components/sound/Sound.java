package hoytekken.app.model.components.sound;

import com.badlogic.gdx.Gdx;

public class Sound {
    private com.badlogic.gdx.audio.Sound sound;
    private long id;

    public Sound(String path) {
        this.sound = Gdx.audio.newSound(Gdx.files.internal(path));
    }

}