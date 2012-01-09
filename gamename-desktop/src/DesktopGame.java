import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class DesktopGame {
    public static void main (String[] args) throws InterruptedException {
    	//OrthographicCameraController game = new OrthographicCameraController();
    	//G3 game = new G3();
    	Game game = new Game(Game.Type.OBJECT1);
		//TestClass game = new TestClass();
    	//Kocka game = new Kocka();
    	//YouSpinMeRound game = new YouSpinMeRound();
        //new LwjglApplication(game, "Game", 480, 320, false);
		new LwjglApplication(game, "Game", 720, 480, false);
    }
}