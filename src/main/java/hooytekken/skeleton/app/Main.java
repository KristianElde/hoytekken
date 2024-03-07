package hooytekken.skeleton.app;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import hooytekken.skeleton.app.model.HTekkenModel;

public class Main {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("hoytekken");
        cfg.setWindowedMode(480, 320);
        HTekkenModel model = new HTekkenModel();
        new Lwjgl3Application(new Hoytekken(), cfg);
    }
}
