package hoytekken.app.model.components.sound;

/**
 * Interface for sound effects.
 */
public interface ISound {

    /**
     * Play the sound.
     */
    public void play();

    /**
     * Stop playing the sound.
     */
    public void stop();

    /**
     * Loop the sound.
     */
    public void loop();

    /**
     * Stop the sound loop.
     */
    public void stopLoop();
}
