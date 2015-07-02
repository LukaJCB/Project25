package com.ltj.java.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.jogamp.opengl.GL3;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.ltj.java.utils.JoglShaderHelper;
import com.ltj.java.utils.JoglTextResourceReader;
import com.ltj.shared.engine.Camera;
import com.ltj.shared.engine.HeadsUpDisplay;
import com.ltj.shared.engine.HudElement;
import com.ltj.shared.engine.ModeSevenObject;
import com.ltj.shared.engine.OrthoRenderObject;
import com.ltj.shared.engine.ParticleEmitter;
import com.ltj.shared.engine.RenderObject;
import com.ltj.shared.engine.Updater;

import static com.jogamp.opengl.GL.*;

public abstract class JoglRenderer implements GLEventListener, KeyListener {

	public static int programId;

	public static int particleProgramId;

	public static int uMatrixLocation;



	private boolean modeSeven, changeMode;



	protected GL3 gl;

	private JoglUpdater updater;

	private int alphaProgramId, normalProgramId;
	private HeadsUpDisplay hud;

	private float pointSize;


	
	
	public void init(GLAutoDrawable drawable) {
		if (drawable.getGLProfile().isGL4()){
			gl = drawable.getGL().getGL4();
		} else {
			gl = drawable.getGL().getGL3();
		}


		updater = new JoglUpdater(true);
		
		// tell opengl to clear the colorbuffer
		gl.glClearColor(0, 0, 0, 0);

		gl.glEnable(GL_BLEND);
		gl.glBlendFunc(GL_ONE, GL_ONE_MINUS_SRC_ALPHA);
		
		// get shader src
		String vertexShaderSrc = JoglTextResourceReader.readTextFileFromResource("res/raw/vertex_shader.glsl");		
		String fragmentShaderSrc = JoglTextResourceReader.readTextFileFromResource("res/raw/fragment_shader.glsl");
		String alphaFragmentShaderSrc = JoglTextResourceReader.readTextFileFromResource("res/raw/alpha_fragment_shader.glsl");
		String particleVertexShaderSrc = JoglTextResourceReader.readTextFileFromResource("res/raw/particle_vertex_shader.glsl");
		String particleFragmentShaderSrc = JoglTextResourceReader.readTextFileFromResource("res/raw/particle_fragment_shader.glsl");
		// compile shaders
		int vertexShader = JoglShaderHelper.compileVertexShader(gl,vertexShaderSrc);
		int fragmentShader = JoglShaderHelper.compileFragmentShader(gl,fragmentShaderSrc);
		int alphaFragmentShader = JoglShaderHelper.compileFragmentShader(gl,alphaFragmentShaderSrc);
		int particleVertexShader = JoglShaderHelper.compileVertexShader(gl, particleVertexShaderSrc);
		int particleFragmentShader = JoglShaderHelper.compileFragmentShader(gl, particleFragmentShaderSrc);
		
		// link
		normalProgramId = JoglShaderHelper.linkProgram(gl, vertexShader,fragmentShader);
		alphaProgramId = JoglShaderHelper.linkProgram(gl, vertexShader, alphaFragmentShader);
		particleProgramId = JoglShaderHelper.linkProgram(gl, particleVertexShader, particleFragmentShader);
		
		programId = normalProgramId;
		// use program
		gl.glUseProgram(programId);

		// get locations
		uMatrixLocation = gl.glGetUniformLocation(programId, "uMatrix");
		
		
		Camera.setLookAt(0, 0);
		hud = new HeadsUpDisplay();
	
		pointSize = 5;
		gl.glPointSize(pointSize);
		
		Camera.setDistance(2);
	}

	public void addRenderable(RenderObject r) {
		Updater.addRenderable(r);
	}
	
	public void addHudElement(String key,HudElement e){
		hud.addHudElement(key,e);
	}

	public void addMSRenderable(ModeSevenObject r) {
		Updater.addMSRenderable(r);
	}

	public void changeMode() {
		changeMode = true;
	}

	private void setNormal() {
		programId = normalProgramId;
		gl.glDisable(GL_DEPTH_TEST);
		Camera.setNormalMode();
		for (ModeSevenObject s : Updater.getAllMSObjects()) {
			s.setNormalMode();
		}
		modeSeven = false;
	}

	private void setModeSeven() {
		programId = alphaProgramId;
		gl.glEnable(GL_DEPTH_TEST);
		gl.glDepthFunc(GL_LEQUAL);
		gl.glDepthMask(true);

		Camera.setModeSeven();
		for (ModeSevenObject s : Updater.getAllMSObjects()) {
			s.setModeSeven();
		}
		modeSeven = true;
	}

	public void display(GLAutoDrawable drawable) {
		
		//long time = System.currentTimeMillis();
		if (changeMode){
			if (modeSeven){
				setNormal();
			} else {
				setModeSeven();
			}
			changeMode = false;
		}
		
		Updater.update();
		
		gl.glUseProgram(programId);
		
		//clear framebuffer
		if (modeSeven){
			gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			if (Camera.activeSkybox()){
				Camera.renderSkybox();
				gl.glClear(GL_DEPTH_BUFFER_BIT);
			}
		} else {
			gl.glClear(GL_COLOR_BUFFER_BIT);
			for (OrthoRenderObject r : Updater.getAllOrthoRenderObjects()){
				r.render();
			}
		}

		for(RenderObject r : Updater.getAllObjects()){
			r.render();
		}
		
		gl.glUseProgram(particleProgramId);
		
		for (ParticleEmitter pe : Updater.getAllParticleEmitters()){
			pe.render();
		}
		gl.glUseProgram(programId);
		
		gl.glClear(GL_DEPTH_BUFFER_BIT);

		hud.render();
		//System.out.println(System.currentTimeMillis() - time);
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
		gl.glViewport(x, y, width, height);

		hud.setDimensions(width,height);
		Updater.setDimensions(width, height);
		
		Camera.createPerspective(height, width);
		Camera.createOrthographic(height, width);
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

	public void dispose(GLAutoDrawable drawable) {
	
	}
	
}
