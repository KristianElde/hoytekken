package hoytekken.app.model.components.sound;

import com.badlogic.gdx.Gdx;

public class Sound implements ISound {
    private com.badlogic.gdx.audio.Sound sound;
    private long id;

    public Sound(String path) {
        this.sound = Gdx.audio.newSound(Gdx.files.internal(path));
    }

    @Override
    public void play() {
        this.id = this.sound.play();
    }

    @Override
    public void stop() {
        sound.stop();
    }

}