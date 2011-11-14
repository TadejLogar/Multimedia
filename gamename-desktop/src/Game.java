import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Component;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;


public class Game implements ApplicationListener {
        //private Mesh mesh;
		private Mesh[] mesh;
        private Camera camera;
        
        private float rotX;
        private float rotY;
        private float rotZ = 2.0f;
        
        private float zoom = 2;
        
        private float width;
        private float height;
        private boolean isPerspective = true;
		private boolean first = true;
		
		private Sound sound;
        
        public void setRotX(float rotX) {
        	this.rotX = rotX;
        }
        
        public void setRotY(float rotY) {
        	this.rotY = rotY;
        }
        
        public void setRotZ(float rotZ) {
        	this.rotZ = rotZ;
        }

        @Override
        public void create() {
        	
            /*if (mesh == null) {
                mesh = new Mesh(true, 3, 3, new VertexAttribute(Usage.Position, 3, "a_position"));
                mesh.setVertices(new float[] { -0.5f, -0.5f, 0, 0.5f, -0.5f, 0, 0, 0.5f, 0 });
                mesh.setIndices(new short[] { 0, 1, 2 });
            }*/
        	
    		if (mesh == null) {
    			sound = Gdx.audio.newSound(Gdx.files.internal("data/fuu.mp3"));
    			sound.play();
    			
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

        @Override
        public void render() {
    		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
    			rotY += 0.03f;
    		} else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
    			rotY -= 0.03f;
    		} else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
    			rotX += 0.03f;
    			//camera.translate(0, 0, 0);
    			//camera.rotate(0, -0.03f, 0, 0);
    			//camera.translate(0.03f, 0, 0);
    			
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
    		} else if (Gdx.input.isKeyPressed(Input.Keys.W)) {
    			rotZ -= 0.03f;
    		} else if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
    			camera.rotate(-0.5f, 0, 0, 1);
    		} else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
    			camera.rotate(0.5f, 0, 0, 1);
    		}
			/*if (Gdx.input.isKeyPressed(Keys.PLUS)) {
    			zoom -= 0.1;
    		}
    		if (Gdx.input.isKeyPressed(Keys.MINUS)) {
    			zoom += 0.1;
    		}*/
    		
    		camera.position.set(rotX, rotY, rotZ);
    		
    		if (first) {
    			camera.translate(rotX, rotY, rotZ);
    			first = false;
    		}/* else {
    			//camera.translate(0.01f, 0.01f, 0.01f);
    		}*/

    		camera.update();
    		camera.apply(Gdx.gl10);

    		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT); // počistimo sceno
    		
    		//Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
    		//mesh.render(GL10.GL_TRIANGLES, 0, 3);

    		for (Mesh face : mesh) {
    			face.render(GL10.GL_TRIANGLE_STRIP, 0, 4);
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
    	
    	private void setCamera() {
    		float aspectRatio = (float) width / (float) height;
    		camera = isPerspective ? new PerspectiveCamera(67, 2f * aspectRatio, 2f) : new OrthographicCamera(2f * aspectRatio, 2f);
    		updateCamera();
    	}
    	
    	private void updateCamera() {
    		/*double x = rotX * 2 * Math.PI / 360;
    		double y = rotY * 2 * Math.PI / 360;
    		camera.position.x = (float) Math.sin(x) * zoom * (float) Math.cos(y);
    		camera.position.z = (float) Math.cos(x) * zoom * (float) Math.cos(y);
    		camera.position.y = (float) Math.sin(y) * zoom;
    		camera.lookAt(0, 0, 0);
    		camera.update();*/
    	}

        @Override
        public void resume() { }
}