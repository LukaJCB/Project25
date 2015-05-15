package com.ltj.android.engine;


import com.ltj.shared.engine.AbstractSprite;



public class SimpleSpriteAndroid extends AbstractSprite {


	public SimpleSpriteAndroid (int resId){
		
		renderer = new AndroidSpriteRenderer(resId);
		
	}
}
