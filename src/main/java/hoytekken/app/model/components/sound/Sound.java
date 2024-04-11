public class Sound {
    private String soundName;
    private String soundPath;

    public Sound(String soundName, String soundPath) {
        this.soundName = soundName;
        this.soundPath = soundPath;
    }

    public String getSoundName() {
        return soundName;
    }

    public String getSoundPath() {
        return soundPath;
    }
}