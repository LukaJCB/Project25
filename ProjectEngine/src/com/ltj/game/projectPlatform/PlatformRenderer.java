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

		EmptyObject background = new EmptyObject();
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
		bullet.getCollider(0).setScaling(0.6f, 0.3f);
		
		hero.setTexture(0, 0);
		CharacterController b = new CharacterController();
		hero.addCollider(new BoxCollider());
		hero.addBehaviour(b);
		b.allocateObject(hero);
		b.rocket = bullet;
		bullet.scale(0.3f, 0.6f);
		hero.scale(0.5f, 0.5f);
		hero.setTag("hero");

		Updater.addRenderable(hero);
		
		
		
		
		
		//platforms
		SimpleSprite platform = new SimpleSprite(gl,"assets/img/tile_t.png");
		platform.setTag("ground");
		platform.setRepeat(2, 1);
		platform.scale(2f, 1f);
		platform.translate(0, -2);
		platform.addCollider(new BoxCollider());
		Updater.addRenderable(platform);
		

		SimpleSprite platform2 = new SimpleSprite(gl,"assets/img/tile_t.png");
		platform2.setTag("ground");
		platform2.setRepeat(2, 1);
		platform2.scale(2f, 1f);
		platform2.translate(3, -1);
		platform2.addCollider(new BoxCollider());
		Updater.addRenderable(platform2);
		
		SimpleSprite ground = new SimpleSprite(gl, "assets/img/tile.png");
		ground.setRepeat(15,4);
		ground.scale(15, 4);
		ground.translate(3,-5);
		ground.setTag("ground");
		ground.addCollider(new BoxCollider());
		Updater.addRenderable(ground);
		
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
