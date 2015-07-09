package com.ltj.java.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static com.ltj.java.engine.StaticGL.*;
import com.jogamp.opengl.GL3;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.ltj.java.utils.JoglShaderHelper;
import com.ltj.java.utils.JoglTextResourceReader;
import com.ltj.shared.engine.Camera;
import com.ltj.shared.engine.HeadsUpDisplay;
import com.ltj.shared.engine.HudElement;
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

	private long renderTime = 1000 / 60;
	
	private int alphaProgramId, normalProgramId;
	private HeadsUpDisplay hud;

	private float pointSize;


	
	
	public void init(GLAutoDrawable drawable) {
		if (drawable.getGLProfile().isGL4()){
			gl = drawable.getGL().getGL4();
		} else {
			gl = drawable.getGL().getGL3();
		}
		StaticGL.insertGL(gl);

		updater = new JoglUpdater(true);
		
		// tell opengl to clear the colorbuffer
		glClearColor(0, 0, 0, 0);

		glEnable(GL_BLEND);
		glBlendFunc(GL_ONE, GL_ONE_MINUS_SRC_ALPHA);
		
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
		glUseProgram(programId);

		// get locations
		uMatrixLocation = gl.glGetUniformLocation(programId, "uMatrix");
		
		
		Camera.setLookAt(0, 0);
		hud = new HeadsUpDisplay();
	
		pointSize = 5;
		glPointSize(pointSize);
		
		Camera.setDistance(2);
	}

	public void addRenderable(RenderObject r) {
		Updater.addRenderable(r);
	}
	
	public void addHudElement(String key,HudElement e){
		hud.addHudElement(key,e);
	}


	public void changeMode() {
		changeMode = true;
	}

	private void setNormal() {
		programId = normalProgramId;
		glDisable(GL_DEPTH_TEST);
		Camera.setNormalMode();
		for (RenderObject g : Updater.getAllObjects()) {
			g.setNormalMode();
		}
		modeSeven = false;
	}

	private void setModeSeven() {
		programId = alphaProgramId;
		glEnable(GL_DEPTH_TEST);
		glDepthFunc(GL_LEQUAL);
		glDepthMask(true);

		Camera.setModeSeven();
		for (RenderObject g : Updater.getAllObjects()) {
			g.setModeSeven();
		}
		modeSeven = true;
	}

	public void display(GLAutoDrawable drawable) {
		
		long time = System.currentTimeMillis();
		if (changeMode){
			if (modeSeven){
				setNormal();
			} else {
				setModeSeven();
			}
			changeMode = false;
		}
		
		Updater.update();
		
		glUseProgram(programId);
		
		//clear framebuffer
		if (modeSeven){
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			if (Camera.activeSkybox()){
				Camera.renderSkybox();
				glClear(GL_DEPTH_BUFFER_BIT);
			}
		} else {
			glClear(GL_COLOR_BUFFER_BIT);
			for (OrthoRenderObject r : Updater.getAllOrthoRenderObjects()){
				r.render();
			}
		}

		for(RenderObject r : Updater.getAllObjects()){
			r.render();
		}
		
		glUseProgram(particleProgramId);
		
		for (ParticleEmitter pe : Updater.getAllParticleEmitters()){
			pe.render();
		}
		glUseProgram(programId);
		
		glClear(GL_DEPTH_BUFFER_BIT);

		hud.render();
		long timeDiff = System.currentTimeMillis() - time;
		if (timeDiff < renderTime){
			try {
				Thread.sleep(renderTime - timeDiff);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
		glViewport(x, y, width, height);

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
