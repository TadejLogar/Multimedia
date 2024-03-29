import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.tests.utils.GdxTest;
import com.badlogic.gdx.tests.utils.PerspectiveCamController;

public class ShapeRendererTest extends GdxTest {

        @Override
        public boolean needsGL20 () {
                return false;
        }

        ShapeRenderer renderer;
        PerspectiveCamera cam;
        PerspectiveCamController controller;
        SpriteBatch batch;
        BitmapFont font;

        public void create () {
                renderer = new ShapeRenderer();
                cam = new PerspectiveCamera(47, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                cam.position.set(0, 0, 2);
                cam.near = 0.1f;
                controller = new PerspectiveCamController(cam);
                Gdx.input.setInputProcessor(controller);
                batch = new SpriteBatch();
                font = new BitmapFont();
        }

        public void render () {
                Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
                //Gdx.gl.glEnable(GL10.GL_DEPTH_TEST);
                cam.update();
                renderer.setProjectionMatrix(cam.combined);
                renderer.identity();
                renderer.rotate(0, 1, 0, 20);
                renderer.translate(-0.5f, -0.5f, 0);

                MathUtils.random.setSeed(0);

                /*renderer.begin(ShapeType.Line);
                renderer.setColor(Color.GREEN);
                for (int i = 0; i < 1000; i++) {
                        renderer.line(MathUtils.random(), MathUtils.random(), MathUtils.random(), MathUtils.random());
                }
                renderer.end();*/

                /*renderer.begin(ShapeType.Point);
                renderer.setColor(Color.BLUE);
                for (int i = 0; i < 1000; i++) {
                        renderer.point(MathUtils.random(), MathUtils.random(), MathUtils.random());
                }
                renderer.end();

                renderer.begin(ShapeType.Rectangle);
                renderer.setColor(Color.RED);
                for (int i = 0; i < 20; i++) {
                        float x = MathUtils.random();
                        float y = MathUtils.random();
                        float width = MathUtils.random();
                        float height = MathUtils.random();
                        renderer.identity();
                        renderer.translate(-0.5f + x, -0.5f + y, MathUtils.random());
                        renderer.translate(width / 2, height / 2, 0);
                        renderer.rotate(0, 1, 0, MathUtils.random() * 360);
                        renderer.translate(-width / 2, -height / 2, 0);
                        renderer.rect(0, 0, width, height);
                }
                renderer.end();

                renderer.begin(ShapeType.FilledRectangle);
                renderer.setColor(Color.WHITE);
                for (int i = 0; i < 20; i++) {
                        float x = MathUtils.random();
                        float y = MathUtils.random();
                        float width = MathUtils.random();
                        float height = MathUtils.random();
                        renderer.identity();
                        renderer.translate(-0.5f + x, -0.5f + y, -MathUtils.random());
                        renderer.translate(width / 2, height / 2, 0);
                        renderer.rotate(0, 1, 0, MathUtils.random() * 360);
                        renderer.translate(-width / 2, -height / 2, 0);
                        renderer.filledRect(0, 0, width, height);
                }
                renderer.end();*/

                /*renderer.begin(ShapeType.Box);
                renderer.setColor(1, 1, 0, 1);
                for (int i = 0; i < 1; i++) {
                        float x = MathUtils.random();
                        float y = MathUtils.random();
                        float width = MathUtils.random();
                        float height = MathUtils.random();
                        float depth = MathUtils.random();
                        renderer.identity();
                        renderer.translate(-1.5f + x, -0.5f + y, MathUtils.random());
                        renderer.translate(width / 2, height / 2, depth / 2);
                        renderer.rotate(0, 1, 0, MathUtils.random() * 360);
                        renderer.translate(-width / 2, -height / 2, depth / 2);
                        renderer.box(0, 0, 0, width, height, depth);
                }
                renderer.end();*/

                renderer.begin(ShapeType.Box);
                renderer.setColor(1, 0, 1, 1);
                renderer.identity();
                for (int i = 0; i < 20; i++) {
                        float x = MathUtils.random();
                        float y = MathUtils.random();
                        float radius = MathUtils.random();
                        renderer.box(-10, -10, -10, 10, 10, 10);//(x, y, radius, 40);
                }
                renderer.end();

                /*renderer.begin(ShapeType.Box);
                renderer.setColor(0, 1, 1, 1);
                renderer.identity();
                renderer.rotate(0, 1, 0, 45);
                for (int i = 0; i < 5; i++) {
                        float x = MathUtils.random();
                        float y = MathUtils.random();
                        float width = MathUtils.random();
                        float height = MathUtils.random();
                        renderer.identity();
                        renderer.translate(0.5f + x, -0.5f + y, -MathUtils.random());
                        renderer.translate(width / 2, height / 2, 0);
                        renderer.rotate(0, 1, 0, MathUtils.random() * 360);
                        renderer.translate(-width / 2, -height / 2, 0);
                        //renderer.filledCircle(0, 0, width, 40);
                }
                renderer.end();*/

                //Gdx.gl.glDisable(GL10.GL_DEPTH_TEST);
                /*batch.begin();
                font.draw(batch, "fps: " + Gdx.graphics.getFramesPerSecond(), 0, 20);
                batch.end();*/
        }
}
