package com.ltj.engine.view;



import android.app.Activity;
import android.os.Bundle;

public class GlActivity extends Activity {

	
	private GlEngineView mainView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mainView = new GlEngineView(this);
		mainView.setOnTouchListener(mainView);
		
		setContentView(mainView);
		
	}
	@Override
	protected void onResume() {
		super.onResume();
		mainView.onResume();
	}
	@Override
	public void onBackPressed() {
		mainView.onBackPressed();
	}
	@Override
	protected void onPause() {
		super.onPause();
		mainView.onPause();
	}

}
