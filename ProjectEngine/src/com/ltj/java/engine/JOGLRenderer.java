package com.ltj.java.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.ltj.java.utils.JoglShaderHelper;
import com.ltj.java.utils.JoglTextResourceReader;
import com.ltj.shared.engine.Behaviour;
import com.ltj.shared.engine.Camera;
import com.ltj.shared.engine.EmptyObject;
import com.ltj.shared.engine.GameObject;
import com.ltj.shared.engine.ModeSevenObject;
import com.ltj.shared.engine.RenderObject;
import com.ltj.shared.engine.SheetSpriteModeS;
import com.ltj.shared.engine.SimpleSprite;
import com.ltj.shared.engine.SimpleSpriteModeS;
import com.ltj.shared.engine.SoundManager;
import com.ltj.shared.engine.primitives.BoxCollider;

import static com.jogamp.opengl.GL.*;

public class JoglRenderer implements GLEventListener, KeyListener {

	public static int programId;

	public static int uMatrixLocation;



	private boolean modeSeven, changeMode;



	private GL4 gl;

	private JoglUpdater updater;

	private int alphaProgramId;

	private int normalProgramId;

	
	
	public void init(GLAutoDrawable drawable) {
		gl = drawable.getGL().getGL4();



		updater = new JoglUpdater(true);
		
		// tell opengl to clear the colorbuffer
		gl.glClearColor(0, 0, 0, 0);

		gl.glEnable(GL_BLEND);
		gl.glBlendFunc(GL_ONE, GL_ONE_MINUS_SRC_ALPHA);

		// get shader src
		String vertexShaderSrc = JoglTextResourceReader.readTextFileFromResource("res/raw/vertex_shader.glsl");
				
		String fragmentShaderSrc = JoglTextResourceReader.readTextFileFromResource("res/raw/fragment_shader.glsl");
		String alphaFragmentShaderSrc = JoglTextResourceReader.readTextFileFromResource("res/raw/alpha_fragment_shader.glsl");
		// compile shaders
		int vertexShader = JoglShaderHelper.compileVertexShader(gl,vertexShaderSrc);
		int fragmentShader = JoglShaderHelper.compileFragmentShader(gl,fragmentShaderSrc);
		int alphaFragmentShader = JoglShaderHelper.compileFragmentShader(gl,alphaFragmentShaderSrc);

		// link
		normalProgramId = JoglShaderHelper.linkProgram(gl, vertexShader,
				fragmentShader);
		alphaProgramId = JoglShaderHelper.linkProgram(gl, vertexShader, alphaFragmentShader);

		programId = normalProgramId;
		// use program
		gl.glUseProgram(programId);

		// get locations
		uMatrixLocation = gl.glGetUniformLocation(programId, "uMatrix");
		
		Camera.setLookAt(0, 0);
		
		SoundManager.initSoundManager(false);

		
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
		programId = normalProgramId;
		gl.glUseProgram(normalProgramId);
		gl.glDisable(GL_DEPTH_TEST);
		gl.glUseProgram(programId);
		Camera.setNormalMode();
		for (ModeSevenObject s : updater.getAllMSObjects()) {
			s.setNormalMode();
		}
		modeSeven = false;
	}

	private void setModeSeven() {
		programId = alphaProgramId;
		gl.glUseProgram(alphaProgramId);
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
	
		if (changeMode){
			if (modeSeven){
				setNormal();
			} else {
				setModeSeven();
			}
			changeMode = false;
		}
		
		//clear framebuffer
		if (modeSeven){
			gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		} else {
			gl.glClear(GL_COLOR_BUFFER_BIT);
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
