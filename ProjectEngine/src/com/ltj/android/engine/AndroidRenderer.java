package com.ltj.android.engine;

import static android.opengl.GLES20.GL_BLEND;
import static android.opengl.GLES20.GL_COLOR_BUFFER_BIT;
import static android.opengl.GLES20.GL_DEPTH_BUFFER_BIT;
import static android.opengl.GLES20.GL_DEPTH_TEST;
import static android.opengl.GLES20.GL_LEQUAL;
import static android.opengl.GLES20.GL_ONE;
import static android.opengl.GLES20.GL_ONE_MINUS_SRC_ALPHA;
import static android.opengl.GLES20.glBlendFunc;
import static android.opengl.GLES20.glClear;
import static android.opengl.GLES20.glClearColor;
import static android.opengl.GLES20.glDepthFunc;
import static android.opengl.GLES20.glDepthMask;
import static android.opengl.GLES20.glDisable;
import static android.opengl.GLES20.glEnable;
import static android.opengl.GLES20.glGetUniformLocation;
import static android.opengl.GLES20.glIsEnabled;
import static android.opengl.GLES20.glUseProgram;
import static android.opengl.GLES20.glViewport;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.hardware.SensorEvent;
import android.opengl.GLSurfaceView.Renderer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.ltj.android.utils.AndroidShaderHelper;
import com.ltj.android.utils.AndroidTextResourceReader;
import com.ltj.projectengine.R;
import com.ltj.shared.engine.Camera;
import com.ltj.shared.engine.Engine;
import com.ltj.shared.engine.HeadsUpDisplay;
import com.ltj.shared.engine.HudElement;
import com.ltj.shared.engine.OrthoRenderObject;
import com.ltj.shared.engine.ParticleEmitter;
import com.ltj.shared.engine.RenderObject;


public abstract class AndroidRenderer implements Renderer{

	
	public static int programId,particleProgramId;

	public static int uMatrixLocation;

	public static Context context;

	private boolean modeSeven,changeMode;
	
	private long renderTime = 1000 / 30;

	private AndroidUpdater updater;

	private int alphaProgramId, normalProgramId;

	private HeadsUpDisplay hud;

	private float pointSize;
	

	public boolean onTouch(View v, MotionEvent event) {
		return updater.onTouch(v, event);
	}

	public void onSensorChanged(SensorEvent event) {
		updater.onSensorChanged(event);
	}

	public AndroidRenderer (Context c, boolean motion, boolean tilt){
		context = c;

		updater = new AndroidUpdater(c,motion,tilt);

	}
	
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		//tell opengl to clear the colorbuffer
		glClearColor(0,0,0,0);
		
		glEnable(GL_BLEND);
		glBlendFunc(GL_ONE, GL_ONE_MINUS_SRC_ALPHA);
	
		
		//get shader src
		String vertexShaderSrc = AndroidTextResourceReader.readTextFileFromResource(context, R.raw.vertex_shader);
		String fragmentShaderSrc = AndroidTextResourceReader.readTextFileFromResource(context, R.raw.fragment_shader);
		String alphaFragmentShaderSrc = AndroidTextResourceReader.readTextFileFromResource(context,R.raw.alpha_fragment_shader);
		String particleVertexShaderSrc = AndroidTextResourceReader.readTextFileFromResource(context, R.raw.particle_vertex_shader);
		String particleFragmentShaderSrc = AndroidTextResourceReader.readTextFileFromResource(context, R.raw.particle_fragment_shader);
		
		// compile shaders
		int vertexShader = AndroidShaderHelper.compileVertexShader(vertexShaderSrc);
		int fragmentShader = AndroidShaderHelper.compileFragmentShader(fragmentShaderSrc);
		int alphaFragmentShader = AndroidShaderHelper.compileFragmentShader(alphaFragmentShaderSrc);
		int particleVertexShader = AndroidShaderHelper.compileVertexShader(particleVertexShaderSrc);
		int particleFragmentShader = AndroidShaderHelper.compileFragmentShader(particleFragmentShaderSrc);
		
		//link programs
		normalProgramId = AndroidShaderHelper.linkProgram(vertexShader, fragmentShader);
		alphaProgramId =AndroidShaderHelper.linkProgram(vertexShader, alphaFragmentShader);
		particleProgramId = AndroidShaderHelper.linkProgram(particleVertexShader, particleFragmentShader);
		
		programId = normalProgramId;
		//use program
		glUseProgram(programId);
		//get locations
		uMatrixLocation = glGetUniformLocation(programId, "uMatrix");
		
		hud = new HeadsUpDisplay();
		
		pointSize = 5;
		
		Camera.setDistance(2);
	}

	public void addHudElement(String key,HudElement element) {
		hud.addHudElement(key,element);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		glViewport(0,0,width,height);
		
		hud.setDimensions(width,height);
		Engine.setDimensions(width, height);
		
		Camera.createPerspective(height, width);
		Camera.createOrthographic(height, width);
		
	}
	public void start(){
		Engine.start();
	}
	
	@Override
	public void onDrawFrame(GL10 gl) {
		long beginTime = System.currentTimeMillis();
		
		if (changeMode){
			if (modeSeven){
				setNormal();
			} else {
				setModeSeven();
			}
			changeMode = false;
		}
		glUseProgram(programId);

		Engine.update();
		
		//clear framebuffer
		if (modeSeven){
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			if (Camera.activeSkybox()){
				Camera.renderSkybox();
				glClear(GL_DEPTH_BUFFER_BIT);
			}
		} else {
			glClear(GL_COLOR_BUFFER_BIT);
			for (OrthoRenderObject r : Engine.getAllOrthoRenderObjects()){
				r.render();
			}
		}
		
		for(RenderObject r : Engine.getAllObjects().values()){
			r.render();
		}
		
		glUseProgram(particleProgramId);
		
		for (ParticleEmitter pe : Engine.getAllParticleEmitters().values()){
			pe.render();
		}
		
		glUseProgram(programId);
		
		//clear framebuffer and draw hud
		glClear(GL_DEPTH_BUFFER_BIT);
		hud.render();
		
	
		
		long timeDiff = System.currentTimeMillis() - beginTime;
		if (timeDiff < renderTime){
			try {
				Thread.sleep(renderTime - timeDiff);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
	}

	
	
	public void addRenderable(RenderObject r) {
		Engine.addRenderable(r);
	}


	public void changeMode() {
		changeMode = true;
	}




	private void setNormal(){
		programId = normalProgramId;
		glDisable(GL_DEPTH_TEST);
		Log.w("depth: ", ""+glIsEnabled(GL_DEPTH_TEST));
		Camera.setNormalMode();
		for (RenderObject s : Engine.getAllObjects().values()){
			s.setNormalMode();
		}
		modeSeven = false;
		Engine.setModeSeven(false);
	}


	
	
	private void setModeSeven(){
		programId = alphaProgramId;
		glEnable(GL_DEPTH_TEST);
		glDepthFunc(GL_LEQUAL);
		glDepthMask(true);
		Log.w("depth: ", ""+glIsEnabled(GL_DEPTH_TEST));
		Camera.setModeSeven();
		for (RenderObject s : Engine.getAllObjects().values()){
			s.setModeSeven();
		}
		modeSeven = true;
		Engine.setModeSeven(true);
	}
}
