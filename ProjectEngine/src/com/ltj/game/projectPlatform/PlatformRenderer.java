package com.ltj.game.projectPlatform;


import com.jogamp.opengl.GLAutoDrawable;
import com.ltj.java.engine.JoglParticleEmitter;
import com.ltj.java.engine.JoglRenderer;
import com.ltj.java.engine.JoglSprite;
import com.ltj.shared.engine.Behaviour;
import com.ltj.shared.engine.EmptyObject;
import com.ltj.shared.engine.HudElement;
import com.ltj.shared.engine.OrthoRenderObject;
import com.ltj.shared.engine.Sprite;
import com.ltj.shared.engine.Updater;
import com.ltj.shared.engine.primitives.BoxCollider;
import com.ltj.shared.engine.primitives.Globals;
import com.ltj.shared.engine.primitives.Rectangle;

public class PlatformRenderer extends JoglRenderer {

	@Override
	public void init(GLAutoDrawable drawable) {
		super.init(drawable);

		EmptyObject background = new EmptyObject();
		background.scale(10, 10);
		
		
		Updater.setCollisionZone(new Rectangle(background.getX(), background.getY(), background.getWidth(), background.getHeight()));
		//background
		OrthoRenderObject o = new HudElement(gl, "assets/img/skyboxleft.png");
		Updater.addOrthoRenderObject(o);
		
		JoglSprite star1 = new JoglSprite(gl, "assets/img/star.png", 1, 1);
		star1.translate(10, 16);
		star1.scale(10, 10);
		star1.setZ(-68);
		Updater.addRenderable(star1);
		
		JoglSprite star2 = new JoglSprite(gl, "assets/img/star.png", 1, 1);
		star2.translate(-4, 19);
		star2.scale(10, 10);
		star2.setZ(-88);
		Updater.addRenderable(star2);
		
		JoglSprite star3 = new JoglSprite(gl, "assets/img/star.png", 1, 1);
		star3.translate(30, 27);
		star3.scale(10, 10);
		star3.setZ(-99);
		Updater.addRenderable(star3);
		
		JoglSprite moon1 = new JoglSprite(gl, "assets/img/moon.png", 1, 1);
		moon1.translate(0, 14);
		moon1.scale(4, 4);
		moon1.setZ(-28);
		Updater.addRenderable(moon1);
		
		JoglSprite moon2 = new JoglSprite(gl, "assets/img/moon.png", 1, 1);
		moon2.translate(0, 8);
		moon2.scale(4, 4);
		moon2.setZ(-18);
		Updater.addRenderable(moon2);
		
		JoglSprite moon3 = new JoglSprite(gl, "assets/img/moon.png", 1, 1);
		moon3.translate(0, 9);
		moon3.scale(4, 4);
		moon3.setZ(-58);
		Updater.addRenderable(moon3);
		
		Globals.add("gravity", 0.013f);
		
		//hero 
		JoglSprite hero = new JoglSprite(gl, "assets/img/player_sheet.png",4,3);
		JoglSprite bullet = new JoglSprite(gl, "assets/img/ship.png",1,1);
		bullet.setTag("bullet");
		Behaviour<Sprite> bulletScript = new BulletController();
		bullet.addBehaviour(bulletScript);
		bulletScript.allocateObject(bullet);
		bullet.addCollider(new BoxCollider());
		bullet.getCollider(0).setScaling(0.6f, 0.3f);
		
		hero.setTexture(0, 0);
		CharacterController b = new CharacterController();
		hero.addCollider(new BoxCollider());
		hero.addBehaviour(b);
		b.allocateObject(hero);
		Globals.add("bullet", bullet);
		bullet.scale(0.3f, 0.6f);
		hero.scale(0.5f, 0.5f);
		hero.setTag("hero");
		//hero.setPosition(20, 0);
		hero.addAnimation("walk_right", 10, 1, true, 4);
		hero.addAnimation("walk_left", 10, 2, true, 4);
		hero.addAnimation("idle", 10, 0, true, 4);

		Updater.addRenderable(hero);
		
		JoglParticleEmitter jpe = new JoglParticleEmitter(gl,100, 1000, 1, 0.2f, 0.1f);
		b.emitter = jpe;
		Updater.addParticleEmitter(jpe);
		
		
		setupMiddle();
		
		setupRight();
		
		setupBottomRight();
		
		Updater.start();
		

	}
	
	private void setupMiddle(){
		
		EmptyObject e = new EmptyObject();
		e.scale(30, 15);
		
		
		//platforms
		JoglSprite platform = new JoglSprite(gl,"assets/img/tile_t.png", 1, 1);
		platform.setTag("ground");
		platform.setRepeat(6, 1);
		platform.scale(2f, 0.3f);
		platform.translate(15, 2.2f);
		platform.addCollider(new BoxCollider());
		Updater.addRenderable(platform);
		platform.setParent(e);
		

		JoglSprite platform2 = new JoglSprite(gl,"assets/img/tile_t.png", 1, 1);
		platform2.setTag("ground");
		platform2.setRepeat(30, 1);
		platform2.scale(10f, 0.3f);
		platform2.translate(10, -1);
		platform2.addCollider(new BoxCollider());
		Updater.addRenderable(platform2);
		platform2.setParent(e);
		
		JoglSprite ground = new JoglSprite(gl, "assets/img/tile.png", 1, 1);
		ground.setRepeat(10,2);
		ground.scale(25, 5);
		ground.translate(0,-5);
		ground.setTag("ground");
		ground.addCollider(new BoxCollider());
		Updater.addRenderable(ground);
		ground.setParent(e);
		
		//enemy
		JoglSprite enemy = new JoglSprite(gl, "assets/img/spritesheet_hero.png",3,4);
		enemy.setTag("enemy");
		enemy.scale(0.6f, 0.9f);
		enemy.setPosition(platform2.getX() , platform2.getY()+platform2.getHeight()/2 + enemy.getHeight()/2);
		enemy.addCollider(new BoxCollider());
		EnemyBehaviour eb = new EnemyBehaviour();
		eb.allocateObject(enemy);
		enemy.addBehaviour(eb);
		JoglParticleEmitter pe1 = new JoglParticleEmitter(gl, 100, 1000, 1, 0, 0);
		Updater.addParticleEmitter(pe1);
		eb.pe = pe1;
		enemy.setParent(e);
		
		EmptyObject awareness = new EmptyObject();
		awareness.setParent(enemy);
		awareness.addCollider(new BoxCollider());
		awareness.scale(2, 1);
		awareness.setPosition(enemy.getX()-awareness.getWidth()/2, enemy.getY());
		Updater.addRenderable(awareness);
		Updater.addRenderable(enemy);
		
		//spikes
		JoglSprite spikes = new JoglSprite(gl, "assets/img/spike.png", 1, 1);
		spikes.setRepeat(1, 4);
		spikes.scale(1, 4);
		spikes.addCollider(new BoxCollider());
		spikes.setTag("hazard");
		spikes.translate(15.7f, 0);
		Updater.addRenderable(spikes);
		spikes.setParent(e);
		
	}
	
	private void setupRight(){

		JoglSprite wall = new JoglSprite(gl, "assets/img/tile.png", 1, 1);
		wall.setRepeat(2, 15);
		wall.scale(2, 15);
		wall.setTag("ground");
		wall.addCollider(new BoxCollider());
		wall.translate(17.2f, -2.5f);
		Updater.addRenderable(wall);
		
		
		JoglSprite wall2 = new JoglSprite(gl, "assets/img/tile.png", 1, 1);
		wall2.setRepeat(20, 2);
		wall2.scale(20, 2);
		wall2.setTag("ground");
		wall2.addCollider(new BoxCollider());
		wall2.translate(28.2f, 4);
		Updater.addRenderable(wall2);
		
		JoglSprite wall4 = new JoglSprite(gl, "assets/img/tile.png", 1, 1);
		wall4.setRepeat(0.5f, 10);
		wall4.scale(0.5f, 10);
		wall4.setTag("ground");
		wall4.addCollider(new BoxCollider());
		wall4.translate(38.45f, 0);
		Updater.addRenderable(wall4);
		
		JoglSprite wall5 = new JoglSprite(gl, "assets/img/tile.png", 1, 1);
		wall5.setRepeat(0.5f, 14);
		wall5.scale(0.5f, 14);
		wall5.setTag("ground");
		wall5.addCollider(new BoxCollider());
		wall5.translate(42.45f, -2f);
		Updater.addRenderable(wall5);
		
		JoglSprite wall6 = new JoglSprite(gl, "assets/img/tile.png", 1, 1);
		wall6.setRepeat(4, 0.5f);
		wall6.scale(4, 0.5f);
		wall6.translate(40.7f, -9.25f);
		Updater.addRenderable(wall6);
		
		JoglSprite wall7 = new JoglSprite(gl, "assets/img/tile.png", 1, 1);
		wall7.setRepeat(8, 1.5f);
		wall7.scale(8, 1.5f);
		wall7.setTag("ground");
		wall7.addCollider(new BoxCollider());
		wall7.translate(34.7f, -8.75f);
		Updater.addRenderable(wall7);
		
		JoglSprite wall8 = new JoglSprite(gl, "assets/img/tile_t.png", 1, 1);
		wall8.setRepeat(3, 0.5f);
		wall8.scale(3, 0.5f);
		wall8.setTag("ground");
		wall8.addCollider(new BoxCollider());
		wall8.translate(19.7f, -1f);
		Updater.addRenderable(wall8);
		
		JoglSprite wall9 = new JoglSprite(gl, "assets/img/tile_t.png", 1, 1);
		wall9.setRepeat(2f, 0.5f);
		wall9.scale(2f, 0.5f);
		wall9.setTag("ground");
		wall9.addCollider(new BoxCollider());
		wall9.translate(20.2f, -5f);
		Updater.addRenderable(wall9);
		
		JoglSprite wall10 = new JoglSprite(gl, "assets/img/tile.png", 1, 1);
		wall10.setRepeat(12f, 0.5f);
		wall10.scale(12f, 0.5f);
		wall10.translate(25.2f,-9.25f);
		Updater.addRenderable(wall10);
		
		JoglSprite wall11 = new JoglSprite(gl, "assets/img/tile.png", 1, 1);
		wall11.setRepeat(2f, 0.5f);
		wall11.scale(2f, 0.5f);
		wall11.translate(26f,-1.75f);
		Updater.addRenderable(wall11);
		
		JoglSprite platform = new JoglSprite(gl,"assets/img/tile_t.png", 1, 1);
		platform.setRepeat(2, 0.5f);
		platform.scale(2, 0.5f);
		platform.setPosition(36.5f, -5.5f);
		platform.setTag("ground");
		platform.addCollider(new BoxCollider());
		RaisingPlatform s = new RaisingPlatform();
		s.allocateObject(platform);
		platform.addBehaviour(s);
		Updater.addRenderable(platform);
		
		
		JoglSprite platform2 = new JoglSprite(gl,"assets/img/tile_t.png", 1, 1);
		platform2.setRepeat(2, 0.5f);
		platform2.scale(2, 0.5f);
		platform2.setPosition(33.5f, -2.5f);
		platform2.setTag("ground");
		platform2.addCollider(new BoxCollider());
		RaisingPlatform s2 = new RaisingPlatform();
		s2.allocateObject(platform2);
		platform2.addBehaviour(s2);
		Updater.addRenderable(platform2);
		
		JoglSprite movingPlatform = new JoglSprite(gl,"assets/img/tile_t.png", 1, 1);
		movingPlatform.setRepeat(2, 0.5f);
		movingPlatform.scale(2, 0.5f);
		movingPlatform.setPosition(31f, -1f);
		movingPlatform.setTag("moving");
		movingPlatform.addCollider(new BoxCollider());
		MovingPlatform m = new MovingPlatform();
		m.allocateObject(movingPlatform);
		movingPlatform.addBehaviour(m);
		Updater.addRenderable(movingPlatform);
		
	
		//hazards
		JoglSprite spikes1 = new JoglSprite(gl, "assets/img/spike.png", 1, 1);
		spikes1.setRepeat(1, 15);
		spikes1.scale(1, 15);
		spikes1.addCollider(new BoxCollider());
		spikes1.rotate(90);
		spikes1.setTag("hazard");
		spikes1.translate(30f, 2.5f);
		Updater.addRenderable(spikes1);
		
		JoglSprite spikes2 = new JoglSprite(gl, "assets/img/spike.png", 1, 1);
		spikes2.setRepeat(1, 10);
		spikes2.scale(1, 10);
		spikes2.addCollider(new BoxCollider());
		spikes2.rotate(180);
		spikes2.setTag("hazard");
		spikes2.translate(39.2f, 0f);
		Updater.addRenderable(spikes2);
		
		JoglSprite spikes3 = new JoglSprite(gl, "assets/img/spike.png", 1, 1);
		spikes3.setRepeat(1, 13);
		spikes3.scale(1, 13);
		spikes3.addCollider(new BoxCollider());
		spikes3.setTag("hazard");
		spikes3.translate(41.7f, -1.5f);
		Updater.addRenderable(spikes3);
		
		JoglSprite spikes4 = new JoglSprite(gl, "assets/img/spike.png", 1, 1);
		spikes4.setRepeat(1, 3);
		spikes4.scale(1, 3);
		spikes4.addCollider(new BoxCollider());
		spikes4.rotate(-90);
		spikes4.setTag("hazard");
		spikes4.translate(40.45f, -8.5f);
		Updater.addRenderable(spikes4);
		
		JoglSprite spikes5 = new JoglSprite(gl, "assets/img/spike.png", 1, 1);
		spikes5.setRepeat(1, 2);
		spikes5.scale(1, 2);
		spikes5.addCollider(new BoxCollider());
		spikes5.rotate(-90);
		spikes5.setTag("hazard");
		spikes5.translate(26f, -1);
		Updater.addRenderable(spikes5);
		
		JoglSprite spikes6 = new JoglSprite(gl, "assets/img/spike.png", 1, 1);
		spikes6.addCollider(new BoxCollider());
		spikes6.rotate(-90);
		spikes6.setTag("hazard");
		spikes6.translate(20.7f, -4.25f);
		Updater.addRenderable(spikes6);
		
		JoglSprite spikes7 = new JoglSprite(gl, "assets/img/spike.png", 1, 1);
		spikes7.setRepeat(1, 11);
		spikes7.scale(1, 11);
		spikes7.addCollider(new BoxCollider());
		spikes7.rotate(-90);
		spikes7.setTag("hazard");
		spikes7.translate(24.7f, -8.5f);
		Updater.addRenderable(spikes7);
		
		//enemy
		JoglSprite enemy2 = new JoglSprite(gl, "assets/img/spritesheet_hero.png",3,4);
		enemy2.setTag("enemy");
		enemy2.scale(0.6f, 0.9f);
		enemy2.setPosition(wall2.getX() + wall2.getWidth()/4 , wall2.getY()+wall2.getHeight()/2 + enemy2.getHeight()/2);
		enemy2.addCollider(new BoxCollider());
		EnemyBehaviour eb2 = new EnemyBehaviour();
		eb2.allocateObject(enemy2);
		enemy2.addBehaviour(eb2);
		JoglParticleEmitter pe2 = new JoglParticleEmitter(gl, 100, 1000, 1, 0, 0);
		Updater.addParticleEmitter(pe2);
		eb2.pe = pe2;
		
		EmptyObject awareness2 = new EmptyObject();
		awareness2.setParent(enemy2);
		awareness2.addCollider(new BoxCollider());
		awareness2.scale(3, 1f);
		awareness2.setPosition(enemy2.getX()-awareness2.getWidth()/2, enemy2.getY());
		Updater.addRenderable(enemy2);
		Updater.addRenderable(awareness2);
		
		JoglSprite enemy3 = new JoglSprite(gl, "assets/img/spritesheet_hero.png",3,4);
		enemy3.setTag("enemy");
		enemy3.scale(0.6f, 0.9f);
		enemy3.setPosition(wall2.getX() - wall2.getWidth()/4 , wall2.getY()+wall2.getHeight()/2 + enemy2.getHeight()/2);
		enemy3.addCollider(new BoxCollider());
		EnemyBehaviour eb3 = new EnemyBehaviour();
		eb3.allocateObject(enemy3);
		enemy3.addBehaviour(eb3);
		JoglParticleEmitter pe3 = new JoglParticleEmitter(gl, 100, 1000, 1, 0, 0);
		Updater.addParticleEmitter(pe3);
		eb3.pe = pe3;
		
		EmptyObject awareness3 = new EmptyObject();
		awareness3.setParent(enemy3);
		awareness3.addCollider(new BoxCollider());
		awareness3.scale(3, 1f);
		awareness3.setPosition(enemy3.getX()-awareness3.getWidth()/2, enemy3.getY());
		Updater.addRenderable(enemy3);
		Updater.addRenderable(awareness3);
	}
	
	private void setupBottomRight(){
		EmptyObject e = new EmptyObject();
		e.scale(25, 15);
		

		JoglSprite wall = new JoglSprite(gl, "assets/img/tile.png", 1, 1);
		wall.setRepeat(1, 15);
		wall.scale(1, 15);
		wall.setTag("ground");
		wall.addCollider(new BoxCollider());
		wall.translate(-12, 0);
		Updater.addRenderable(wall);
		wall.setParent(e);
		
		
		JoglSprite wall2 = new JoglSprite(gl, "assets/img/tile.png", 1, 1);
		wall2.setRepeat(1, 15);
		wall2.scale(1, 15);
		wall2.setTag("ground");
		wall2.addCollider(new BoxCollider());
		wall2.translate(12f, 0);
		Updater.addRenderable(wall2);
		wall2.setParent(e);
		
		JoglSprite wall4 = new JoglSprite(gl, "assets/img/tile.png", 1, 1);
		wall4.setRepeat(25, 2);
		wall4.scale(25, 2);
		wall4.setTag("ground");
		wall4.addCollider(new BoxCollider());
		wall4.translate(0, -6.5f);
		Updater.addRenderable(wall4);
		wall4.setParent(e);
		
		JoglSprite boss = new JoglSprite(gl, "assets/img/boss.png", 1, 1);
		EnemyBehaviour bb = new EnemyBehaviour();
		bb.allocateObject(boss);
		boss.addBehaviour(bb);
		boss.scale(5, 5);
		Updater.addRenderable(boss);
		boss.setParent(e);
		boss.addCollider(new BoxCollider());
		JoglParticleEmitter pe2 = new JoglParticleEmitter(gl, 100, 1000, 1, 0, 0);
		Updater.addParticleEmitter(pe2);
		bb.pe = pe2;
		
		EmptyObject awareness = new EmptyObject();
		awareness.setParent(boss);
		awareness.addCollider(new BoxCollider());
		awareness.scale(boss.getWidth(),boss.getHeight());
		awareness.setPosition(boss.getX()-awareness.getWidth()/2, boss.getY());
		Updater.addRenderable(boss);
		Updater.addRenderable(awareness);
		
		e.translate(29, -17);
	}
	
}
