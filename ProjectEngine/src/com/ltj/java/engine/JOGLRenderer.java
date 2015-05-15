package com.ltj.java.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.ltj.java.utils.JoglShaderHelper;
import com.ltj.shared.engine.Behaviour;
import com.ltj.shared.engine.Camera;
import com.ltj.shared.engine.ModeSevenObject;
import com.ltj.shared.engine.RenderObject;

import static com.jogamp.opengl.GL.*;

public class JOGLRenderer implements GLEventListener, KeyListener {

	public static int programId;

	public static int uMatrixLocation;

	private static HashMap<String, RenderObject> tags = new HashMap<String, RenderObject>();

	
	public static void addID(String tag, RenderObject r) {
		tags.put(tag, r);
	}

	public static RenderObject getObjectByID(String tag) {
		return tags.get(tag);
	}

	private boolean modeSeven, changeMode;



	private GL4 gl;

	private JoglUpdater updater;

	
	
	public void init(GLAutoDrawable drawable) {
		gl = drawable.getGL().getGL4();



		updater = new JoglUpdater(true);
		
		// tell opengl to clear the colorbuffer
		gl.glClearColor(0, 0, 0, 0);

		gl.glEnable(GL_BLEND);
		gl.glBlendFunc(GL_ONE, GL_ONE_MINUS_SRC_ALPHA);

		// get shader src
		String vertexShaderSrc = "uniform mat4 uMatrix; "
				+ "attribute vec4 aPosition;" +
				"attribute vec2 aTexCoordinates;"+
				"varying vec2 vTexCoordinate; "+
				"void main(){ " +
				"vTexCoordinate = aTexCoordinates;"+
				"gl_Position =  " +
				"uMatrix * " +
				"aPosition;" + "}";
		String fragmentShaderSrc = "precision mediump float;" +
				"uniform sampler2D uTexture;"+
				"varying vec2 vTexCoordinate;"+
				"void main(){" + "gl_FragColor =" +
				"texture2D(uTexture, vTexCoordinate);"+
				"}";
		// compile shaders
		int vertexShader = JoglShaderHelper.compileVertexShader(gl,
				vertexShaderSrc);
		int fragmentShader = JoglShaderHelper.compileFragmentShader(gl,
				fragmentShaderSrc);

		// link
		programId = JoglShaderHelper.linkProgram(gl, vertexShader,
				fragmentShader);

		// use program
		gl.glUseProgram(programId);

		// get locations
		uMatrixLocation = gl.glGetUniformLocation(programId, "uMatrix");
		
		Camera.setLookAt(0, 0);
		

		SimpleSpriteJogl sp = new SimpleSpriteJogl(gl, "img/background.png");
		sp.scale(10, 24);
		updater.addRenderable(sp);
		SheetSpriteModeSJogl hero = new SheetSpriteModeSJogl(gl, "img/spritesheet_hero.png",3,4);
		Behaviour<SheetSpriteModeSJogl> b = new Behaviour<SheetSpriteModeSJogl>(){

			@Override
			public void start() {
				
			}

			@Override
			public void update() {
				gameObject.setTexture(2, 1);
				
				
				Camera.setLookAt(gameObject.getX(), gameObject.getY());
			}
			
		};
		
		hero.addBehaviour(b);
		b.allocateObject(hero);
		hero.scale(0.5f, 0.5f);
		updater.addRenderable(hero);
		SimpleSpriteJogl sp3 = new SimpleSpriteJogl(gl, "img/enemy.png");
		sp3.translate(1, 1);
		updater.addRenderable(sp3);
		
		updater.start();
	}

	public void addRenderable(RenderObject r) {
		updater.addRenderable(r);
	}

	public void addMSRenderable(ModeSevenObject r) {
		updater.addMSRenderable(r);
	}

	public void changeMode() {
		changeMode = true;
	}

	private void setNormal() {
		gl.glDisable(GL_DEPTH_TEST);

		Camera.setNormalMode();
		for (ModeSevenObject s : updater.getAllMSObjects()) {
			s.setNormalMode();
		}
		modeSeven = false;
	}

	private void setModeSeven() {
		gl.glEnable(GL_DEPTH_TEST);
		gl.glDepthFunc(GL_LEQUAL);
		gl.glDepthMask(true);

		Camera.setModeSeven();
		for (ModeSevenObject s : updater.getAllMSObjects()) {
			s.setModeSeven();
		}
		modeSeven = true;
	}

	public void dispose(GLAutoDrawable drawable) {

	}

	public void display(GLAutoDrawable drawable) {
		gl.glClear(GL_COLOR_BUFFER_BIT);
	
		

		//clear framebuffer
		if (modeSeven){
			gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		} else {
			gl.glClear(GL_COLOR_BUFFER_BIT);
		}
		if (changeMode){
			if (modeSeven){
				setNormal();
			} else {
				setModeSeven();
			}
			changeMode = false;
		}

		updater.update();

		for(RenderObject r : updater.getAllObjects()){
			r.render();
		}
	

	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
		gl.glViewport(x, y, width, height);

		
		Camera.surfaceChanged(height, width);
	}

	public void keyTyped(KeyEvent e) {
		updater.keyTyped(e);
	}

	public void keyReleased(KeyEvent e) {
		updater.keyReleased(e);
	}

	public void keyPressed(KeyEvent e) {
		updater.keyPressed(e);
	}
	
}
