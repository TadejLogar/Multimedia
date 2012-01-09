import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Component;

import javax.media.opengl.GL;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.loaders.obj.ObjLoader;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;


/*
 * 

Vaja 1  - 3D vsebine (Pametni telefon)

Uporabite knjižnico libgdx (http://code.google.com/p/libgdx/) za prikaz objektov v 3D.
Ustvarite enostaven primer (npr. prikaz trikotnika).

Implementirajte različne poglede (ortogonalni, perspektivni) ter omogočite nadzor nad kamero.
Omogočite interaktivno premikanje objekta po zaslonu in rotacijo objekta okoli svojih osi.

Poleg prikazovanja objekta naj aplikacija omogoča tudi nalaganje in predvajanje zvočnih posnetkov.

Zahteve:

    Vzpostavitev 3D pogona in prikaz poljubnega objekta (3T)
    Rotacija in translacija (3T)
    Vključitev nalaganja in predvajanja zvoka(3T)
    Rotacija poljubno prestavljenega objekta okoli lastnih osi (1T)

 * 
 */
public class Game implements ApplicationListener {
		public class Type {
			public static final int BOX = 1;
			public static final int OBJECT1 = 2;
			public static final int OBJECT2 = 3;
		}
		
		private int type;
	
		private Mesh[] mesh;
        private Camera camera;
        
        private float rotX;
        private float rotY;
        private float rotZ = 2.0f;
        
        private float zoom = 2;
        
        private float width;
        private float height;
        private boolean isPerspective = true;
		//private boolean first = true;
		
		private Music music;
		private String note;
		private BitmapFont font;
		private SpriteBatch spriteBatch;
		
		private Mesh object;
		private Texture texture;
		
		public Game(int type) {
			this.type = type;
		}
        
        public void setRotX(float rotX) {
        	this.rotX = rotX;
        }
        
        public void setRotY(float rotY) {
        	this.rotY = rotY;
        }
        
        public void setRotZ(float rotZ) {
        	this.rotZ = rotZ;
        }
        
        private void play(String file) {
        	if (music != null) music.stop();
			music = Gdx.audio.newMusic(Gdx.files.getFileHandle("data/" + file, FileType.Internal));
			music.play();
			music.setLooping(true);
        }

        @Override
        public void create() {
        	
            /*if (mesh == null) {
                mesh = new Mesh(true, 3, 3, new VertexAttribute(Usage.Position, 3, "a_position"));
                mesh.setVertices(new float[] { -0.5f, -0.5f, 0, 0.5f, -0.5f, 0, 0, 0.5f, 0 });
                mesh.setIndices(new short[] { 0, 1, 2 });
            }*/
        	
    		if (mesh == null) {
    			spriteBatch = new SpriteBatch();
    			FileHandle handle = Gdx.files.getFileHandle("data/note.txt", FileType.Internal);
                note = handle.readString();
                font = new BitmapFont();
                font.setColor(Color.RED);
    			
                play("Sidney Samson - 'Fill U Up' (Club Mix).mp3");
    			
    			mesh = new Mesh[6]; // ploskve

    			for (int i = 0; i < 6; i++) {
    				mesh[i] = new Mesh(true, 4, 4,
    						new VertexAttribute(Usage.Position, 3, "a_position"), // namesto a_position je lahko karkoli
    						new VertexAttribute(Usage.ColorPacked, 4, "a_color"));
    				// statično, maksimalno vertikal, maksimalno indeksov, atributi
    				// 4 vertikale kvadrata
    				
    				/*
    				 *     usage - the usage, used for the fixed function pipeline.
    				 *     Generic attributes are not supported in the fixed function pipeline.
    				 *     numComponents - the number of components of this attribute, must be between 1 and 4.
    				 *     alias - the alias used in a shader for this attribute. Can be changed after construction.
    				 */

    				mesh[i].setIndices(new short[] { 0, 1, 2, 3 }); // povezava oglišč
    			

    				if (i == 0) {
		    			mesh[0].setVertices(new float[] {
		    					0.5f, 0.5f, 0.5f, Color.toFloatBits(0, 10, 255, 255), // x, y, z, barva: rgba
		    					-0.5f, 0.5f, 0.5f, Color.toFloatBits(0, 10, 255, 255),
		    					0.5f, -0.5f, 0.5f, Color.toFloatBits(0, 10, 255, 255),
		    					-0.5f, -0.5f, 0.5f, Color.toFloatBits(0, 10, 255, 255) });
		    			
    				} else if (i == 1) {
	
		    			mesh[1].setVertices(new float[] {
		    					0.5f, 0.5f, -0.5f, Color.toFloatBits(255, 0, 0, 255),
		    					-0.5f, 0.5f, -0.5f, Color.toFloatBits(255, 0, 0, 255),
		    					0.5f, -0.5f, -0.5f,  Color.toFloatBits(255, 0, 0, 255),
		    					-0.5f, -0.5f, -0.5f, Color.toFloatBits(255, 0, 0, 255) });
	    			
    				} else if (i == 2) {
	
		    			mesh[2].setVertices(new float[] {
		    					0.5f, 0.5f, -0.5f, Color.toFloatBits(0, 255, 0, 255),
		    					-0.5f, 0.5f, -0.5f, Color.toFloatBits(0, 255, 0, 255),
		    					0.5f, 0.5f, 0.5f, Color.toFloatBits(0, 255, 0, 255),
		    					-0.5f, 0.5f, 0.5f, Color.toFloatBits(0, 255, 0, 255) });
	    			
    				} else if (i == 3) {
	
		    			mesh[3].setVertices(new float[] {
		    					0.5f, -0.5f, -0.5f, Color.toFloatBits(0, 96, 0, 255),
		    					-0.5f, -0.5f, -0.5f, Color.toFloatBits(0, 96, 0, 255),
		    					0.5f, -0.5f, 0.5f, Color.toFloatBits(0, 96, 0, 255),
		    					-0.5f, -0.5f, 0.5f,  Color.toFloatBits(0, 96, 0, 255) });
	    			
    				} else if (i == 4) {
	
		    			mesh[4].setVertices(new float[] {
		    					0.5f, 0.5f, 0.5f, Color.toFloatBits(50, 50, 50, 255),
		    					0.5f, -0.5f, 0.5f, Color.toFloatBits(50, 50, 50, 255),
		    					0.5f, 0.5f, -0.5f, Color.toFloatBits(50, 50, 50, 255),
		    					0.5f, -0.5f, -0.5f, Color.toFloatBits(50, 50, 50, 255) });
	    			
    				} else if (i == 5) {
	
		    			mesh[5].setVertices(new float[] {
		    					-0.5f, 0.5f, 0.5f, Color.toFloatBits(0, 0, 96, 255),
		    					-0.5f, -0.5f, 0.5f, Color.toFloatBits(0, 0, 96, 255),
		    					-0.5f, 0.5f, -0.5f, Color.toFloatBits(0, 0, 96, 255),
		    					-0.5f, -0.5f, -0.5f, Color.toFloatBits(0, 0, 96, 255) });
	    			
    				}
    				
    				if (type == Type.OBJECT1) {
	    				object = ObjLoader.loadObj(Gdx.files.internal("data/xerai.obj").read()); // butterfly2.obj
	    				texture = new Texture(Gdx.files.internal("data/x.jpg")); // badlogic.jpg
    				} else if (type == Type.OBJECT2) {
	    				object = ObjLoader.loadObj(Gdx.files.internal("data/butterfly2.obj").read()); // butterfly2.obj
	    				texture = new Texture(Gdx.files.internal("data/badlogic.jpg")); // badlogic.jpg
    				}
    				
    				
                    //cam.update();
                    //renderer.setProjectionMatrix(cam.combined);
                    //renderer.identity();
	    			
    			}
    		}

    		Gdx.gl.glEnable(GL10.GL_DEPTH_TEST);
        }

        @Override
        public void dispose() { }

        @Override
        public void pause() { }

        /*@Override
        public void render() {
        	camera.translate(rotX, rotY, rotZ);
            camera.update();
            camera.apply(Gdx.gl10);
        	
            Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
            mesh.render(GL10.GL_TRIANGLES, 0, 3);
        }*/
        
        /*private int total = 0;
        private float movementIncrement = 0.01f;
        private boolean move;*/
        
    	protected int lastTouchX;
    	protected int lastTouchY;
    	int deltaX;
    	int deltaY;

    	float i = 0;
		private int angleX;
		private int angleY;
		private float camY;
		private float camX;

		private float light = 0.8f;
    	
        @Override
        public void render() {
        	camera.position.set(rotX, rotY, rotZ);
        	camera.update();
        	
        	boolean cameraPosition = true;
        	
    		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
    			rotY += 0.03f;
    		} else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
    			rotY -= 0.03f;
    		} else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
    			rotX += 0.03f;
    			//camera.translate(0, 0, 0);
    			
    			camera.rotate(0, -0.03f, 0, 0);
    			camera.translate(0.03f, 0, 0);
    			
    			//camera.translate(-0.03f, 0, 0);
    			//camera.rotate(0.5f, -3f, 0, 0);
    			//camera.position.set(rotX, rotY, rotZ);
    		} else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
    			rotX -= 0.03f;
    			
    			//camera.translate(0, 0, 0);
    			//camera.translate(0, 0.03f, 0);
    			//camera.rotate(0, 0.03f, 0, 0);
    			//camera.rotate(1, 0, 0, 0);
    			//camera.position.set(rotX, rotY, rotZ);
    			
    		} else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
    			rotZ += 0.03f;
    			//camera.translate(0, 0, 1);
    		} else if (Gdx.input.isKeyPressed(Input.Keys.W)) {
    			rotZ -= 0.03f;
    			//camera.translate(0, 0, -1);
    		} else if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
    			camera.rotate(-0.5f, 0, 0, 1);
    		} else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
    			camera.rotate(0.5f, 0, 0, 1);
    		} else if (Gdx.input.isKeyPressed(Input.Keys.T)) {
    			//camera.rotate(0.5f, 1, 0, 0);
    			
    			/*camera.position.x = 0;
    			camera.position.y = 0;
    			camera.position.z = 10;*/
    			
    			rotZ += 0.03f;
    			camera.translate(0, 0, rotZ);
    			cameraPosition = false;
    			
    			//camera.lookAt(0, 0, 0);
    		} else if (Gdx.input.isKeyPressed(Input.Keys.Z)) {
    			//camera.rotate(0.5f, 1, 0, 0);
    			camera.translate(500, 500, 570);
    		} else if (Gdx.input.isKeyPressed(Input.Keys.NUM_1)) {
    			play("Sidney Samson - 'Fill U Up' (Club Mix).mp3");
    		} else if (Gdx.input.isKeyPressed(Input.Keys.NUM_2)) {
    			play("Roll Deep Ft. Jodie Connor Good Times With Lyrics.mp3");
    		} else if (Gdx.input.isKeyPressed(Input.Keys.NUM_3)) {
    			play("David Guetta & Chris Willis ft Fergie & LMFAO - Gettin' Over You.mp3");
    		} else if (Gdx.input.isKeyPressed(Input.Keys.P)) {
    			music.play();
    		} else if (Gdx.input.isKeyPressed(Input.Keys.O)) {
    			music.pause();
    		} else if (Gdx.input.isKeyPressed(Input.Keys.N)) {
    			angleX -= 3;
    			updateCamera();
    		} else if (Gdx.input.isKeyPressed(Input.Keys.M)) {
    			angleX += 3;
    			updateCamera();
    		}
    		
    		
    		
			/*if (Gdx.input.isKeyPressed(Keys.PLUS)) {
    			zoom -= 0.1;
    		}
    		if (Gdx.input.isKeyPressed(Keys.MINUS)) {
    			zoom += 0.1;
    		}*/
    		
    		//if (first) {
    		//	camera.translate(rotX, rotY, rotZ);
    		//	first = false;
    		//}/* else {
    			//camera.translate(0.01f, 0.01f, 0.01f);
    		//}*/

    		if (Gdx.input.isKeyPressed(Input.Keys.Y)) {
    			camera.lookAt(0, 0, 0);
    			cameraPosition = false;
    			//rotZ -= 0.03f;
    			//camera.translate(0, 0, rotZ);
    		}
    		
    		if (cameraPosition) {
    			camera.position.set(rotX, rotY, rotZ);
    		}
    		
    		camera.update();
    		camera.apply(Gdx.gl10);

    		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT); // počistimo sceno
    		
    		//Gdx.gl.glEnable(GL10.GL_DEPTH_TEST);
    		
    		//Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
    		//mesh.render(GL10.GL_TRIANGLES, 0, 3);

    		if (type == Type.BOX) {
	    		for (Mesh face : mesh) {
	    			face.render(GL10.GL_TRIANGLE_STRIP, 0, 4);
	    		}
    		} else if (type == Type.OBJECT1 || type == Type.OBJECT2) {
           	 	GL10 gl = Gdx.gl10;
           	 	gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
                gl.glEnable(GL10.GL_TEXTURE_2D);
                gl.glEnable(GL10.GL_DEPTH_TEST);
                gl.glColor4f(light , light, light, light);
                
                // http://code.google.com/p/libgdx-users/wiki/MeshLighting
                
				gl.glEnable(GL10.GL_LIGHTING);   
				
				// Enable up to n=8 light sources: GL_LIGHTn
				gl.glEnable(GL10.GL_LIGHT0);
				gl.glEnable(GL10.GL_LIGHT1);
				gl.glEnable(GL10.GL_LIGHT2);
				gl.glEnable(GL10.GL_LIGHT3);
				gl.glEnable(GL10.GL_LIGHT4);

				// light position
				gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, new float[]{0, 0, 1, 1}, 0);

				// Light that has been reflected by other objects and hits the mesh in small amounts
				gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT, new float[]{0.005f, 0.005f, 0.005f, 1f}, 0);

				// setting diffuse light color like a bulb or neon tube
				gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_DIFFUSE, new float[]{0.9f, 0.9f, 0.7f, 1f}, 0);

				// setting specular light color        like a halogen spot
				gl.glLightfv(GL10.GL_LIGHT2, GL10.GL_SPECULAR, new float[]{0.9f, 0.9f, 0.7f, 1f}, 0);

                
                camera.update();
                camera.apply(gl);
    			
    			
    			texture.bind();
    			object.render(GL10.GL_TRIANGLES);
    		}
        	
            /*total = rotX;
            if (total > 5000) {
                movementIncrement = -movementIncrement;
                total = -2000;
            }*/
        	
        	//if (movementIncrement != 0) {
        		/*camera.translate(rotX, rotY, rotZ);
        		camera.rotate(rotX * 20, rotY * 20, rotZ * 20, 0);
        		rotX = 0;
        		rotY = 0;
        		rotZ = 0;*/
        	//}
            
            /*camera.update();
            camera.apply(Gdx.gl10);
            Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
            mesh.render(GL10.GL_TRIANGLES, 0, 3);*/
    		
            spriteBatch.begin();         
            font.drawMultiLine(spriteBatch, note, 20, Gdx.graphics.getHeight() - 20);
            spriteBatch.end();
        }

        /*@Override
        public void resize(int width, int height) {
            float aspectRatio = (float) width / (float) height;
            camera = new OrthographicCamera(2f * aspectRatio, 2f);
        }*/
        
    	public void resize(int width, int height) {
			this.width = width;
			this.height = height;
			setCamera();
    		
    		/*float aspectRatio = (float) width / (float) height;
    		// camera = new OrthographicCamera(2f * aspectRatio, 2f); // ortografska kamera: 2d prostor
    		camera = new PerspectiveCamera(67, 2f * aspectRatio, 2f); // perspektivna kamera: 3d prostor: globina, dimenzije, oddaljenost
    		camera.near = 0.2f;
    		camera.translate(rotX, rotY, rotZ);*/
    		//setCamera();
    	}
    	
    	private void updateCamera()
    	{
    		double x = angleX * 2 * Math.PI/360;
    		double y = angleY * 2 * Math.PI/360;
    		camera.position.x = (float)Math.sin(x) * zoom * (float)Math.cos(y) + camX;
    		camera.position.z = (float)Math.cos(x) * zoom * (float)Math.cos(y) ;
    		camera.position.y = (float)Math.sin(y) * zoom + camY;
    		camera.lookAt(0, 0, 0);
    	}
    	
    	private void setCamera()
    	{
    		float aspectRatio = (float) width / (float) height;
    		camera = isPerspective
    				? new PerspectiveCamera(67, 2f * aspectRatio, 2f)
    				: new OrthographicCamera(2f * aspectRatio, 2f);
    		
    		updateCamera();
    	}
    	
    	private void setCameraOld() {
    		float aspectRatio = (float) width / (float) height;
    		if (isPerspective) {
    			camera = new PerspectiveCamera(67, 2f * aspectRatio, 2f);
    		} else {
    			camera = new OrthographicCamera(2f * aspectRatio, 2f);
    		}
    		
    		camera.near = 0.1f;
    		camera.translate(0, 0, 0);
    		
    		updateCamera();
    	}
    	
    	private void updateCameraOld() {
    		/*double x = rotX * 2 * Math.PI / 360;
    		double y = rotY * 2 * Math.PI / 360;
    		camera.position.x = (float) Math.sin(x) * zoom * (float) Math.cos(y);
    		camera.position.z = (float) Math.cos(x) * zoom * (float) Math.cos(y);
    		camera.position.y = (float) Math.sin(y) * zoom;
    		camera.lookAt(0, 0, 0);
    		camera.update();*/
    		
    		//camera.lookAt(0, 0, 0);
    		//camera.update();
    	}

        @Override
        public void resume() { }
}