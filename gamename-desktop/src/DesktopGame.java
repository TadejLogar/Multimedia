import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class DesktopGame {
    public static void main (String[] args) throws InterruptedException {
    	//OrthographicCameraController game = new OrthographicCameraController();
		Game game = new Game();
        new LwjglApplication(game, "Game", 480, 320, false);
    }
}