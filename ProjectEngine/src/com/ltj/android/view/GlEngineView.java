package com.ltj.android.view;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.ltj.game.GameRenderer;

public class GlEngineView extends GLSurfaceView implements OnTouchListener {

	private GameRenderer renderer;
	public GlEngineView(Context context) {
		super(context);
		setEGLContextClientVersion(2);
		setPreserveEGLContextOnPause (true);
		renderer = new GameRenderer(context, true, true);
		
		setRenderer(renderer);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		return renderer.onTouch(v,event);
	}

	public void onBackPressed() {
		renderer.onBackPressed();
		
	}

}
