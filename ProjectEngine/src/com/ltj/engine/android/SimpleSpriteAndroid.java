package com.ltj.engine.android;


import com.ltj.engine.primitives.AbstractSprite;



public class SimpleSpriteAndroid extends AbstractSprite {


	public SimpleSpriteAndroid (int resId){
		
		renderer = new AndroidSpriteRenderer(resId);
		
	}
}
