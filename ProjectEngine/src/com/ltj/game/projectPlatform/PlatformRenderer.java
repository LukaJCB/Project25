package com.ltj.game.projectPlatform;


import com.jogamp.opengl.GLAutoDrawable;
import com.ltj.java.engine.JoglRenderer;
import com.ltj.shared.engine.Behaviour;
import com.ltj.shared.engine.EmptyObject;
import com.ltj.shared.engine.GameObject;
import com.ltj.shared.engine.HudElement;
import com.ltj.shared.engine.OrthoRenderObject;
import com.ltj.shared.engine.RenderObject;
import com.ltj.shared.engine.SheetSprite;
import com.ltj.shared.engine.SimpleSprite;
import com.ltj.shared.engine.Updater;
import com.ltj.shared.engine.primitives.BoxCollider;
import com.ltj.shared.engine.primitives.Rectangle;

public class PlatformRenderer extends JoglRenderer {

	@Override
	public void init(GLAutoDrawable drawable) {
		super.init(drawable);

		RenderObject background = new SimpleSprite(gl,"assets/img/sky.png"); 
		background.scale(10, 10);
		
		
		Updater.setCollisionZone(new Rectangle(background.getX(), background.getY(), background.getWidth(), background.getHeight()));
		//background
		OrthoRenderObject o = new HudElement(gl, "assets/img/sky.png");
		Updater.addOrthoRenderObject(o);
		
		//hero 
		SheetSprite hero = new SheetSprite(gl, "assets/img/blue.png",1,1);
		SimpleSprite bullet = new SimpleSprite(gl, "assets/img/ship.png");
		Behaviour<GameObject> bulletScript = new BulletController();
		bullet.addBehaviour(bulletScript);
		bulletScript.allocateObject(bullet);
		bullet.addCollider(new BoxCollider());
		EmptyObject colliderZone = new EmptyObject();
		
		hero.scale(0.4f, 0.45f);
		hero.setTexture(0, 0);
		colliderZone.translate(0, -hero.getHeight()*0.2f);
		colliderZone.setTag("bottomCollider");
		colliderZone.addCollider(new BoxCollider());
		colliderZone.setParent(hero);
		CharacterController b = new CharacterController();
		hero.addCollider(new BoxCollider());
		hero.addBehaviour(b);
		b.allocateObject(hero);
		b.rocket = bullet;
		bullet.scale(0.1f, 0.2f);
		bullet.setRendererDisabled(true);
		hero.scale(0.5f, 0.5f);
		hero.setTag("hero");

		Updater.addRenderable(hero);
		Updater.addRenderable(colliderZone);
		Updater.addRenderable(bullet);
		
		
		
		
		
		//platforms
		SimpleSprite platform = new SimpleSprite(gl,"assets/img/car.png");
		platform.setTag("ground");
		platform.scale(1.5f, 1f);
		platform.translate(0, -1);
		platform.addCollider(new BoxCollider());
		Updater.addRenderable(platform);
		

		SimpleSprite platform2 = new SimpleSprite(gl,"assets/img/car.png");
		platform2.setTag("ground");
		platform2.scale(1.5f, 1f);
		platform2.translate(3, -1);
		platform2.addCollider(new BoxCollider());
		Updater.addRenderable(platform2);
		
		
		//enemy
		SimpleSprite enemy = new SimpleSprite(gl, "assets/img/enemy.png");
		enemy.setTag("enemy");
		enemy.scale(0.7f, 0.7f);
		enemy.translate(3, 0.35f);
		enemy.addCollider(new BoxCollider());
		Updater.addRenderable(enemy);
		
		
		Updater.start();
		

	}
	
}
