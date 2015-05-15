package com.ltj.engine.android;

import static android.opengl.GLES20.*;




import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


import com.ltj.engine.primitives.Camera;
import com.ltj.engine.primitives.ModeSevenObject;
import com.ltj.engine.primitives.RenderObject;
import com.ltj.engine.utils.ShaderHelper;
import com.ltj.engine.utils.TextResourceReader;
import com.ltj.projectengine.R;


import android.content.Context;
import android.hardware.SensorEvent;
import android.opengl.GLSurfaceView.Renderer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


public abstract class AndroidRenderer implements Renderer{

	
	public static int programId;
	
	public static int uMatrixLocation;

	public static Context context;

	


	private boolean modeSeven,changeMode;
	
	private long renderTime = 1000 / 30;

	private AndroidUpdater updater;

	

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
		String vertexShaderSrc = TextResourceReader.readTextFileFromResource(context, R.raw.vertex_shader);
		String fragmentShaderSrc = TextResourceReader.readTextFileFromResource(context, R.raw.fragment_shader);
		//compile shaders
		int vertexShader = ShaderHelper.compileVertexShader(vertexShaderSrc);
		int fragmentShader = ShaderHelper.compileFragmentShader(fragmentShaderSrc);
		
		//link
		programId = ShaderHelper.linkProgram(vertexShader, fragmentShader);
		
		
		//use program
		glUseProgram(programId);
		//get locations
		uMatrixLocation = glGetUniformLocation(programId, "uMatrix");
		
		
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		glViewport(0,0,width,height);
		
		// Projection matrix : 60° Field of View, ratio, display range : 0.1 unit <-> 100 units
		Camera.surfaceChanged(height, width);
		
	}
	public void start(){
		updater.start();
	}
	
	@Override
	public void onDrawFrame(GL10 gl) {
		long beginTime = System.currentTimeMillis();
		
		//clear framebuffer
		if (modeSeven){
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		} else {
			glClear(GL_COLOR_BUFFER_BIT);
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
		updater.addRenderable(r);
	}

	public void addMSRenderable(ModeSevenObject r) {
		updater.addMSRenderable(r);
	}

	public void changeMode() {
		changeMode = true;
	}




	private void setNormal(){
		glDisable(GL_DEPTH_TEST);
		Log.w("depth: ", ""+glIsEnabled(GL_DEPTH_TEST));
		Camera.setNormalMode();
		for (ModeSevenObject s : updater.getAllMSObjects()){
			s.setNormalMode();
		}
		modeSeven = false;
	}


	
	
	private void setModeSeven(){
		glEnable(GL_DEPTH_TEST);
		glDepthFunc(GL_LEQUAL);
		glDepthMask(true);
		Log.w("depth: ", ""+glIsEnabled(GL_DEPTH_TEST));
		Camera.setModeSeven();
		for (ModeSevenObject s : updater.getAllMSObjects()){
			s.setModeSeven();
		}
		modeSeven = true;
	}
}
