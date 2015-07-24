package com.ltj.game.projectPlatform;


import com.jogamp.opengl.GLAutoDrawable;
import com.ltj.java.engine.JoglParticleEmitter;
import com.ltj.java.engine.JoglRenderer;
import com.ltj.java.engine.JoglSprite;
import com.ltj.shared.engine.Area;
import com.ltj.shared.engine.Behaviour;
import com.ltj.shared.engine.EmptyObject;
import com.ltj.shared.engine.Engine;
import com.ltj.shared.engine.Sprite;
import com.ltj.shared.engine.primitives.BoxCollider;
import com.ltj.shared.engine.primitives.RunTimeGlobals;

public class MainRenderer extends JoglRenderer {

	@Override
	public void init(GLAutoDrawable drawable) {
		super.init(drawable);
		Engine.setAreaSize(25, 15);
		RunTimeGlobals.add("gravity", 0.013f);

		JoglSprite hero = new JoglSprite( "assets/img/player_run.png",4,1);
		JoglSprite bullet = new JoglSprite( "assets/img/ship.png",1,1);
		bullet.setTag("bullet");
		Behaviour<Sprite> bulletScript = new BulletController();
		bullet.addBehaviour(bulletScript);
		bulletScript.allocateObject(bullet);
		bullet.addCollider(new BoxCollider());
		bullet.getCollider(0).setScaling(0.6f, 0.3f);
		Engine.addRenderable(bullet);
		hero.setTexture(0, 0);
		CharacterController b = new CharacterController();
		hero.addCollider(new BoxCollider());
		hero.addBehaviour(b);
		b.allocateObject(hero);
		JoglParticleEmitter jpe = new JoglParticleEmitter(100, 1000, 0.1f, 0.9f, 0.1f);
		Engine.addParticleEmitter(jpe);
		hero.addParticleEmitter(jpe);
		RunTimeGlobals.add("bullet", bullet);

		bullet.scale(0.3f, 0.6f);
		hero.scale(0.5f, 0.5f);
		hero.setTag("hero");
		//hero.setPosition(20, -12);
		hero.addAnimation("walk_right", 10, 1, true, 4);
		hero.addAnimation("walk_left", 10, 2, true, 4);
		hero.addAnimation("walk", 10, 0, true, 4);

		RunTimeGlobals.createPool("bullets", "bullet", 20);
		Engine.addRenderable(hero);
		
		initMid();
		initBelowLeft();
		initLeft();
		initFarLeft();
		initRight();
		initFarRight();
		initBelowMid();
		initBelowRight();
		initBelowFarLeft();
		initBelowFarRight();
		initTopLeft();
		initTopMid();
		initTopRight();
		initTopFarLeft();
		initTopFarRight();
		

		Engine.setAreaMode(Engine.AREA_MODE_HIDE);
	
	}
	
	private void initBelowLeft(){
		Engine.addArea(-1, -1);
		Area below = Engine.getArea(-1,-1);
		//platforms
		JoglSprite platform = new JoglSprite("assets/img/tile_t.png", 1, 1);
		platform.setTag("ground");
		platform.setRepeat(6, 1);
		platform.scale(2f, 0.3f);
		platform.translate(15, 2.2f);
		platform.addCollider(new BoxCollider());
		Engine.addRenderable(platform);
		below.addObject(platform);

		JoglSprite platform2 = new JoglSprite("assets/img/tile_t.png", 1, 1);
		platform2.setTag("ground");
		platform2.setRepeat(30, 1);
		platform2.scale(10f, 0.3f);
		platform2.translate(10, -1);
		platform2.addCollider(new BoxCollider());
		Engine.addRenderable(platform2);
		below.addObject(platform2);
		
		JoglSprite ground = new JoglSprite( "assets/img/tile.png", 1, 1);
		ground.setRepeat(10,2);
		ground.scale(25, 5);
		ground.translate(0,-5);
		ground.setTag("ground");
		ground.addCollider(new BoxCollider());
		Engine.addRenderable(ground);
		below.addObject(ground);
		
		//enemy
		JoglSprite enemy = new JoglSprite( "assets/img/spritesheet_hero.png",3,4);
		enemy.setTag("enemy");
		enemy.scale(0.6f, 0.9f);
		enemy.translate(10,0);
		enemy.addCollider(new BoxCollider());
		EnemyBehaviour eb = new EnemyBehaviour();
		eb.allocateObject(enemy);
		enemy.addBehaviour(eb);
		
		
		EmptyObject awareness = new EmptyObject();
		awareness.setParent(enemy);
		awareness.addCollider(new BoxCollider());
		awareness.scale(2, 1);
		awareness.setPosition(enemy.getX()-awareness.getWidth()/2, enemy.getY());
		Engine.addRenderable(awareness);
		Engine.addRenderable(enemy);
		below.addObject(awareness);
		below.addObject(enemy);
		
		//spikes
		JoglSprite spikes = new JoglSprite( "assets/img/spike.png", 1, 1);
		spikes.setRepeat(1, 4);
		spikes.scale(1, 4);
		spikes.addCollider(new BoxCollider());
		spikes.setTag("hazard");
		spikes.translate(15.7f, 0);
		Engine.addRenderable(spikes);
		below.addObject(spikes);
		
		
	}
	private void initBelowMid(){
		Engine.addArea(0, -1);
		Area below = Engine.getArea(0,-1);
		//platforms
		JoglSprite platform = new JoglSprite("assets/img/tile_t.png", 1, 1);
		platform.setTag("ground");
		platform.setRepeat(6, 1);
		platform.scale(2f, 0.3f);
		platform.translate(15, 2.2f);
		platform.addCollider(new BoxCollider());
		Engine.addRenderable(platform);
		below.addObject(platform);

		JoglSprite platform2 = new JoglSprite("assets/img/tile_t.png", 1, 1);
		platform2.setTag("ground");
		platform2.setRepeat(30, 1);
		platform2.scale(10f, 0.3f);
		platform2.translate(10, -1);
		platform2.addCollider(new BoxCollider());
		Engine.addRenderable(platform2);
		below.addObject(platform2);
		
		JoglSprite ground = new JoglSprite( "assets/img/tile.png", 1, 1);
		ground.setRepeat(10,2);
		ground.scale(25, 5);
		ground.translate(0,-5);
		ground.setTag("ground");
		ground.addCollider(new BoxCollider());
		Engine.addRenderable(ground);
		below.addObject(ground);
		
		//enemy
		JoglSprite enemy = new JoglSprite( "assets/img/spritesheet_hero.png",3,4);
		enemy.setTag("enemy");
		enemy.scale(0.6f, 0.9f);
		enemy.translate(10,0);
		enemy.addCollider(new BoxCollider());
		EnemyBehaviour eb = new EnemyBehaviour();
		eb.allocateObject(enemy);
		enemy.addBehaviour(eb);
		
		
		EmptyObject awareness = new EmptyObject();
		awareness.setParent(enemy);
		awareness.addCollider(new BoxCollider());
		awareness.scale(2, 1);
		awareness.setPosition(enemy.getX()-awareness.getWidth()/2, enemy.getY());
		Engine.addRenderable(awareness);
		Engine.addRenderable(enemy);
		below.addObject(enemy);
		
		//spikes
		JoglSprite spikes = new JoglSprite( "assets/img/spike.png", 1, 1);
		spikes.setRepeat(1, 4);
		spikes.scale(1, 4);
		spikes.addCollider(new BoxCollider());
		spikes.setTag("hazard");
		spikes.translate(15.7f, 0);
		Engine.addRenderable(spikes);
		below.addObject(spikes);
		
		
	}
	private void initBelowRight(){
		Engine.addArea(1, -1);
		Area below = Engine.getArea(1,-1);
		//platforms
		JoglSprite platform = new JoglSprite("assets/img/tile_t.png", 1, 1);
		platform.setTag("ground");
		platform.setRepeat(6, 1);
		platform.scale(2f, 0.3f);
		platform.translate(15, 2.2f);
		platform.addCollider(new BoxCollider());
		Engine.addRenderable(platform);
		below.addObject(platform);

		JoglSprite platform2 = new JoglSprite("assets/img/tile_t.png", 1, 1);
		platform2.setTag("ground");
		platform2.setRepeat(30, 1);
		platform2.scale(10f, 0.3f);
		platform2.translate(10, -1);
		platform2.addCollider(new BoxCollider());
		Engine.addRenderable(platform2);
		below.addObject(platform2);
		
		JoglSprite ground = new JoglSprite( "assets/img/tile.png", 1, 1);
		ground.setRepeat(10,2);
		ground.scale(25, 5);
		ground.translate(0,-5);
		ground.setTag("ground");
		ground.addCollider(new BoxCollider());
		Engine.addRenderable(ground);
		below.addObject(ground);
		
		//enemy
		JoglSprite enemy = new JoglSprite( "assets/img/spritesheet_hero.png",3,4);
		enemy.setTag("enemy");
		enemy.scale(0.6f, 0.9f);
		enemy.translate(10,0);
		enemy.addCollider(new BoxCollider());
		EnemyBehaviour eb = new EnemyBehaviour();
		eb.allocateObject(enemy);
		enemy.addBehaviour(eb);
		
		
		EmptyObject awareness = new EmptyObject();
		awareness.setParent(enemy);
		awareness.addCollider(new BoxCollider());
		awareness.scale(2, 1);
		awareness.setPosition(enemy.getX()-awareness.getWidth()/2, enemy.getY());
		Engine.addRenderable(awareness);
		Engine.addRenderable(enemy);
		below.addObject(enemy);
		
		//spikes
		JoglSprite spikes = new JoglSprite( "assets/img/spike.png", 1, 1);
		spikes.setRepeat(1, 4);
		spikes.scale(1, 4);
		spikes.addCollider(new BoxCollider());
		spikes.setTag("hazard");
		spikes.translate(15.7f, 0);
		Engine.addRenderable(spikes);
		below.addObject(spikes);
		
		
	}
	private void initBelowFarLeft(){
		Engine.addArea(-2, -1);
		Area below = Engine.getArea(-2,-1);
		//platforms
		JoglSprite platform = new JoglSprite("assets/img/tile_t.png", 1, 1);
		platform.setTag("ground");
		platform.setRepeat(6, 1);
		platform.scale(2f, 0.3f);
		platform.translate(15, 2.2f);
		platform.addCollider(new BoxCollider());
		Engine.addRenderable(platform);
		below.addObject(platform);

		JoglSprite platform2 = new JoglSprite("assets/img/tile_t.png", 1, 1);
		platform2.setTag("ground");
		platform2.setRepeat(30, 1);
		platform2.scale(10f, 0.3f);
		platform2.translate(10, -1);
		platform2.addCollider(new BoxCollider());
		Engine.addRenderable(platform2);
		below.addObject(platform2);
		
		JoglSprite ground = new JoglSprite( "assets/img/tile.png", 1, 1);
		ground.setRepeat(10,2);
		ground.scale(25, 5);
		ground.translate(0,-5);
		ground.setTag("ground");
		ground.addCollider(new BoxCollider());
		Engine.addRenderable(ground);
		below.addObject(ground);
		
		//enemy
		JoglSprite enemy = new JoglSprite( "assets/img/spritesheet_hero.png",3,4);
		enemy.setTag("enemy");
		enemy.scale(0.6f, 0.9f);
		enemy.translate(10,0);
		enemy.addCollider(new BoxCollider());
		EnemyBehaviour eb = new EnemyBehaviour();
		eb.allocateObject(enemy);
		enemy.addBehaviour(eb);
		
		
		EmptyObject awareness = new EmptyObject();
		awareness.setParent(enemy);
		awareness.addCollider(new BoxCollider());
		awareness.scale(2, 1);
		awareness.setPosition(enemy.getX()-awareness.getWidth()/2, enemy.getY());
		Engine.addRenderable(awareness);
		Engine.addRenderable(enemy);
		below.addObject(enemy);
		
		//spikes
		JoglSprite spikes = new JoglSprite( "assets/img/spike.png", 1, 1);
		spikes.setRepeat(1, 4);
		spikes.scale(1, 4);
		spikes.addCollider(new BoxCollider());
		spikes.setTag("hazard");
		spikes.translate(15.7f, 0);
		Engine.addRenderable(spikes);
		below.addObject(spikes);
		
		
	}
	private void initBelowFarRight(){
		Engine.addArea(2, -1);
		Area below = Engine.getArea(2,-1);
		//platforms
		JoglSprite platform = new JoglSprite("assets/img/tile_t.png", 1, 1);
		platform.setTag("ground");
		platform.setRepeat(6, 1);
		platform.scale(2f, 0.3f);
		platform.translate(15, 2.2f);
		platform.addCollider(new BoxCollider());
		Engine.addRenderable(platform);
		below.addObject(platform);

		JoglSprite platform2 = new JoglSprite("assets/img/tile_t.png", 1, 1);
		platform2.setTag("ground");
		platform2.setRepeat(30, 1);
		platform2.scale(10f, 0.3f);
		platform2.translate(10, -1);
		platform2.addCollider(new BoxCollider());
		Engine.addRenderable(platform2);
		below.addObject(platform2);
		
		JoglSprite ground = new JoglSprite( "assets/img/tile.png", 1, 1);
		ground.setRepeat(10,2);
		ground.scale(25, 5);
		ground.translate(0,-5);
		ground.setTag("ground");
		ground.addCollider(new BoxCollider());
		Engine.addRenderable(ground);
		below.addObject(ground);
		
		//enemy
		JoglSprite enemy = new JoglSprite( "assets/img/spritesheet_hero.png",3,4);
		enemy.setTag("enemy");
		enemy.scale(0.6f, 0.9f);
		enemy.translate(10,0);
		enemy.addCollider(new BoxCollider());
		EnemyBehaviour eb = new EnemyBehaviour();
		eb.allocateObject(enemy);
		enemy.addBehaviour(eb);
		
		
		EmptyObject awareness = new EmptyObject();
		awareness.setParent(enemy);
		awareness.addCollider(new BoxCollider());
		awareness.scale(2, 1);
		awareness.setPosition(enemy.getX()-awareness.getWidth()/2, enemy.getY());
		Engine.addRenderable(awareness);
		Engine.addRenderable(enemy);
		below.addObject(enemy);
		
		//spikes
		JoglSprite spikes = new JoglSprite( "assets/img/spike.png", 1, 1);
		spikes.setRepeat(1, 4);
		spikes.scale(1, 4);
		spikes.addCollider(new BoxCollider());
		spikes.setTag("hazard");
		spikes.translate(15.7f, 0);
		Engine.addRenderable(spikes);
		below.addObject(spikes);
		
		
	}
	
	
	private void initMid(){
		Engine.addArea(0, 0);
		Area mid = Engine.getArea(0,0);
		Engine.setCurrentArea(0, 0);
		//platforms
		JoglSprite platform = new JoglSprite("assets/img/tile_t.png", 1, 1);
		platform.setTag("ground");
		platform.setRepeat(6, 1);
		platform.scale(2f, 0.3f);
		platform.translate(15, 2.2f);
		platform.addCollider(new BoxCollider());
		Engine.addRenderable(platform);
		mid.addObject(platform);
	
		JoglSprite platform2 = new JoglSprite("assets/img/tile_t.png", 1, 1);
		platform2.setTag("ground");
		platform2.setRepeat(30, 1);
		platform2.scale(10f, 0.3f);
		platform2.translate(10, -1);
		platform2.addCollider(new BoxCollider());
		Engine.addRenderable(platform2);
		mid.addObject(platform2);
		
		JoglSprite ground = new JoglSprite( "assets/img/tile.png", 1, 1);
		ground.setRepeat(10,2);
		ground.scale(25, 5);
		ground.translate(0,-5);
		ground.setTag("ground");
		ground.addCollider(new BoxCollider());
		Engine.addRenderable(ground);
		mid.addObject(ground);
		
		//enemy
		JoglSprite enemy = new JoglSprite( "assets/img/spritesheet_hero.png",3,4);
		enemy.setTag("enemy");
		enemy.scale(0.6f, 0.9f);
		enemy.translate(10,0);
		enemy.addCollider(new BoxCollider());
		EnemyBehaviour eb = new EnemyBehaviour();
		eb.allocateObject(enemy);
		enemy.addBehaviour(eb);
		
		
		EmptyObject awareness = new EmptyObject();
		awareness.setParent(enemy);
		awareness.addCollider(new BoxCollider());
		awareness.scale(2, 1);
		awareness.setPosition(enemy.getX()-awareness.getWidth()/2, enemy.getY());
		Engine.addRenderable(awareness);
		Engine.addRenderable(enemy);
		mid.addObject(enemy);
		
		
		
		//spikes
		JoglSprite spikes = new JoglSprite( "assets/img/spike.png", 1, 1);
		spikes.setRepeat(1, 4);
		spikes.scale(1, 4);
		spikes.addCollider(new BoxCollider());
		spikes.setTag("hazard");
		spikes.translate(15.7f, 0);
		Engine.addRenderable(spikes);
		mid.addObject(spikes);
		
		
	}

	private void initLeft(){
		Engine.addArea(-1, 0);
		Area below = Engine.getArea(-1,0);
		//platforms
		JoglSprite platform = new JoglSprite("assets/img/tile_t.png", 1, 1);
		platform.setTag("ground");
		platform.setRepeat(6, 1);
		platform.scale(2f, 0.3f);
		platform.translate(15, 2.2f);
		platform.addCollider(new BoxCollider());
		Engine.addRenderable(platform);
		below.addObject(platform);

		JoglSprite platform2 = new JoglSprite("assets/img/tile_t.png", 1, 1);
		platform2.setTag("ground");
		platform2.setRepeat(30, 1);
		platform2.scale(10f, 0.3f);
		platform2.translate(10, -1);
		platform2.addCollider(new BoxCollider());
		Engine.addRenderable(platform2);
		below.addObject(platform2);
		
		JoglSprite ground = new JoglSprite( "assets/img/tile.png", 1, 1);
		ground.setRepeat(10,2);
		ground.scale(25, 5);
		ground.translate(0,-5);
		ground.setTag("ground");
		ground.addCollider(new BoxCollider());
		Engine.addRenderable(ground);
		below.addObject(ground);
		
		//enemy
		JoglSprite enemy = new JoglSprite( "assets/img/spritesheet_hero.png",3,4);
		enemy.setTag("enemy");
		enemy.scale(0.6f, 0.9f);
		enemy.translate(10,0);
		enemy.addCollider(new BoxCollider());
		EnemyBehaviour eb = new EnemyBehaviour();
		eb.allocateObject(enemy);
		enemy.addBehaviour(eb);
		
		
		EmptyObject awareness = new EmptyObject();
		awareness.setParent(enemy);
		awareness.addCollider(new BoxCollider());
		awareness.scale(2, 1);
		awareness.setPosition(enemy.getX()-awareness.getWidth()/2, enemy.getY());
		Engine.addRenderable(awareness);
		Engine.addRenderable(enemy);
		below.addObject(enemy);
		
		//spikes
		JoglSprite spikes = new JoglSprite( "assets/img/spike.png", 1, 1);
		spikes.setRepeat(1, 4);
		spikes.scale(1, 4);
		spikes.addCollider(new BoxCollider());
		spikes.setTag("hazard");
		spikes.translate(15.7f, 0);
		Engine.addRenderable(spikes);
		below.addObject(spikes);
		
		
	}
	private void initFarLeft(){
		Engine.addArea(-2, 0);
		Area below = Engine.getArea(-2,0);
		//platforms
		JoglSprite platform = new JoglSprite("assets/img/tile_t.png", 1, 1);
		platform.setTag("ground");
		platform.setRepeat(6, 1);
		platform.scale(2f, 0.3f);
		platform.translate(15, 2.2f);
		platform.addCollider(new BoxCollider());
		Engine.addRenderable(platform);
		below.addObject(platform);

		JoglSprite platform2 = new JoglSprite("assets/img/tile_t.png", 1, 1);
		platform2.setTag("ground");
		platform2.setRepeat(30, 1);
		platform2.scale(10f, 0.3f);
		platform2.translate(10, -1);
		platform2.addCollider(new BoxCollider());
		Engine.addRenderable(platform2);
		below.addObject(platform2);
		
		JoglSprite ground = new JoglSprite( "assets/img/tile.png", 1, 1);
		ground.setRepeat(10,2);
		ground.scale(25, 5);
		ground.translate(0,-5);
		ground.setTag("ground");
		ground.addCollider(new BoxCollider());
		Engine.addRenderable(ground);
		below.addObject(ground);
		
		//enemy
		JoglSprite enemy = new JoglSprite( "assets/img/spritesheet_hero.png",3,4);
		enemy.setTag("enemy");
		enemy.scale(0.6f, 0.9f);
		enemy.translate(10,0);
		enemy.addCollider(new BoxCollider());
		EnemyBehaviour eb = new EnemyBehaviour();
		eb.allocateObject(enemy);
		enemy.addBehaviour(eb);
		
		
		EmptyObject awareness = new EmptyObject();
		awareness.setParent(enemy);
		awareness.addCollider(new BoxCollider());
		awareness.scale(2, 1);
		awareness.setPosition(enemy.getX()-awareness.getWidth()/2, enemy.getY());
		Engine.addRenderable(awareness);
		Engine.addRenderable(enemy);
		below.addObject(enemy);
		
		//spikes
		JoglSprite spikes = new JoglSprite( "assets/img/spike.png", 1, 1);
		spikes.setRepeat(1, 4);
		spikes.scale(1, 4);
		spikes.addCollider(new BoxCollider());
		spikes.setTag("hazard");
		spikes.translate(15.7f, 0);
		Engine.addRenderable(spikes);
		below.addObject(spikes);
		
		
	}
	
	private void initRight(){
		Engine.addArea(1, 0);
		Area below = Engine.getArea(1,0);
		//platforms
		JoglSprite platform = new JoglSprite("assets/img/tile_t.png", 1, 1);
		platform.setTag("ground");
		platform.setRepeat(6, 1);
		platform.scale(2f, 0.3f);
		platform.translate(15, 2.2f);
		platform.addCollider(new BoxCollider());
		Engine.addRenderable(platform);
		below.addObject(platform);

		JoglSprite platform2 = new JoglSprite("assets/img/tile_t.png", 1, 1);
		platform2.setTag("ground");
		platform2.setRepeat(30, 1);
		platform2.scale(10f, 0.3f);
		platform2.translate(10, -1);
		platform2.addCollider(new BoxCollider());
		Engine.addRenderable(platform2);
		below.addObject(platform2);
		
		JoglSprite ground = new JoglSprite( "assets/img/tile.png", 1, 1);
		ground.setRepeat(10,2);
		ground.scale(25, 5);
		ground.translate(0,-5);
		ground.setTag("ground");
		ground.addCollider(new BoxCollider());
		Engine.addRenderable(ground);
		below.addObject(ground);
		
		//enemy
		JoglSprite enemy = new JoglSprite( "assets/img/spritesheet_hero.png",3,4);
		enemy.setTag("enemy");
		enemy.scale(0.6f, 0.9f);
		enemy.translate(10,0);
		enemy.addCollider(new BoxCollider());
		EnemyBehaviour eb = new EnemyBehaviour();
		eb.allocateObject(enemy);
		enemy.addBehaviour(eb);
		
		
		EmptyObject awareness = new EmptyObject();
		awareness.setParent(enemy);
		awareness.addCollider(new BoxCollider());
		awareness.scale(2, 1);
		awareness.setPosition(enemy.getX()-awareness.getWidth()/2, enemy.getY());
		Engine.addRenderable(awareness);
		Engine.addRenderable(enemy);
		below.addObject(enemy);
		
		//spikes
		JoglSprite spikes = new JoglSprite( "assets/img/spike.png", 1, 1);
		spikes.setRepeat(1, 4);
		spikes.scale(1, 4);
		spikes.addCollider(new BoxCollider());
		spikes.setTag("hazard");
		spikes.translate(15.7f, 0);
		Engine.addRenderable(spikes);
		below.addObject(spikes);
		
		
	}
	
	private void initFarRight(){
		Engine.addArea(2, 0);
		Area below = Engine.getArea(2,0);
		//platforms
		JoglSprite platform = new JoglSprite("assets/img/tile_t.png", 1, 1);
		platform.setTag("ground");
		platform.setRepeat(6, 1);
		platform.scale(2f, 0.3f);
		platform.translate(15, 2.2f);
		platform.addCollider(new BoxCollider());
		Engine.addRenderable(platform);
		below.addObject(platform);

		JoglSprite platform2 = new JoglSprite("assets/img/tile_t.png", 1, 1);
		platform2.setTag("ground");
		platform2.setRepeat(30, 1);
		platform2.scale(10f, 0.3f);
		platform2.translate(10, -1);
		platform2.addCollider(new BoxCollider());
		Engine.addRenderable(platform2);
		below.addObject(platform2);
		
		JoglSprite ground = new JoglSprite( "assets/img/tile.png", 1, 1);
		ground.setRepeat(10,2);
		ground.scale(25, 5);
		ground.translate(0,-5);
		ground.setTag("ground");
		ground.addCollider(new BoxCollider());
		Engine.addRenderable(ground);
		below.addObject(ground);
		
		//enemy
		JoglSprite enemy = new JoglSprite( "assets/img/spritesheet_hero.png",3,4);
		enemy.setTag("enemy");
		enemy.scale(0.6f, 0.9f);
		enemy.translate(10,0);
		enemy.addCollider(new BoxCollider());
		EnemyBehaviour eb = new EnemyBehaviour();
		eb.allocateObject(enemy);
		enemy.addBehaviour(eb);
		
		
		EmptyObject awareness = new EmptyObject();
		awareness.setParent(enemy);
		awareness.addCollider(new BoxCollider());
		awareness.scale(2, 1);
		awareness.setPosition(enemy.getX()-awareness.getWidth()/2, enemy.getY());
		Engine.addRenderable(awareness);
		Engine.addRenderable(enemy);
		below.addObject(enemy);
		
		//spikes
		JoglSprite spikes = new JoglSprite( "assets/img/spike.png", 1, 1);
		spikes.setRepeat(1, 4);
		spikes.scale(1, 4);
		spikes.addCollider(new BoxCollider());
		spikes.setTag("hazard");
		spikes.translate(15.7f, 0);
		Engine.addRenderable(spikes);
		below.addObject(spikes);
		
		
	}
	private void initTopLeft(){
		Engine.addArea(-1, 1);
		Area below = Engine.getArea(-1,1);
		//platforms
		JoglSprite platform = new JoglSprite("assets/img/tile_t.png", 1, 1);
		platform.setTag("ground");
		platform.setRepeat(6, 1);
		platform.scale(2f, 0.3f);
		platform.translate(15, 2.2f);
		platform.addCollider(new BoxCollider());
		Engine.addRenderable(platform);
		below.addObject(platform);

		JoglSprite platform2 = new JoglSprite("assets/img/tile_t.png", 1, 1);
		platform2.setTag("ground");
		platform2.setRepeat(30, 1);
		platform2.scale(10f, 0.3f);
		platform2.translate(10, -1);
		platform2.addCollider(new BoxCollider());
		Engine.addRenderable(platform2);
		below.addObject(platform2);
		
		JoglSprite ground = new JoglSprite( "assets/img/tile.png", 1, 1);
		ground.setRepeat(10,2);
		ground.scale(25, 5);
		ground.translate(0,-5);
		ground.setTag("ground");
		ground.addCollider(new BoxCollider());
		Engine.addRenderable(ground);
		below.addObject(ground);
		
		//enemy
		JoglSprite enemy = new JoglSprite( "assets/img/spritesheet_hero.png",3,4);
		enemy.setTag("enemy");
		enemy.scale(0.6f, 0.9f);
		enemy.translate(10,0);
		enemy.addCollider(new BoxCollider());
		EnemyBehaviour eb = new EnemyBehaviour();
		eb.allocateObject(enemy);
		enemy.addBehaviour(eb);
		
		
		EmptyObject awareness = new EmptyObject();
		awareness.setParent(enemy);
		awareness.addCollider(new BoxCollider());
		awareness.scale(2, 1);
		awareness.setPosition(enemy.getX()-awareness.getWidth()/2, enemy.getY());
		Engine.addRenderable(awareness);
		Engine.addRenderable(enemy);
		below.addObject(enemy);
		
		//spikes
		JoglSprite spikes = new JoglSprite( "assets/img/spike.png", 1, 1);
		spikes.setRepeat(1, 4);
		spikes.scale(1, 4);
		spikes.addCollider(new BoxCollider());
		spikes.setTag("hazard");
		spikes.translate(15.7f, 0);
		Engine.addRenderable(spikes);
		below.addObject(spikes);
		
		
	}

	private void initTopMid(){
		Engine.addArea(0, 1);
		Area below = Engine.getArea(0,1);
		//platforms
		JoglSprite platform = new JoglSprite("assets/img/tile_t.png", 1, 1);
		platform.setTag("ground");
		platform.setRepeat(6, 1);
		platform.scale(2f, 0.3f);
		platform.translate(15, 2.2f);
		platform.addCollider(new BoxCollider());
		Engine.addRenderable(platform);
		below.addObject(platform);

		JoglSprite platform2 = new JoglSprite("assets/img/tile_t.png", 1, 1);
		platform2.setTag("ground");
		platform2.setRepeat(30, 1);
		platform2.scale(10f, 0.3f);
		platform2.translate(10, -1);
		platform2.addCollider(new BoxCollider());
		Engine.addRenderable(platform2);
		below.addObject(platform2);
		
		JoglSprite ground = new JoglSprite( "assets/img/tile.png", 1, 1);
		ground.setRepeat(10,2);
		ground.scale(25, 5);
		ground.translate(0,-5);
		ground.setTag("ground");
		ground.addCollider(new BoxCollider());
		Engine.addRenderable(ground);
		below.addObject(ground);
		
		//enemy
		JoglSprite enemy = new JoglSprite( "assets/img/spritesheet_hero.png",3,4);
		enemy.setTag("enemy");
		enemy.scale(0.6f, 0.9f);
		enemy.translate(10,0);
		enemy.addCollider(new BoxCollider());
		EnemyBehaviour eb = new EnemyBehaviour();
		eb.allocateObject(enemy);
		enemy.addBehaviour(eb);
		
		
		EmptyObject awareness = new EmptyObject();
		awareness.setParent(enemy);
		awareness.addCollider(new BoxCollider());
		awareness.scale(2, 1);
		awareness.setPosition(enemy.getX()-awareness.getWidth()/2, enemy.getY());
		Engine.addRenderable(awareness);
		Engine.addRenderable(enemy);
		below.addObject(enemy);
		
		//spikes
		JoglSprite spikes = new JoglSprite( "assets/img/spike.png", 1, 1);
		spikes.setRepeat(1, 4);
		spikes.scale(1, 4);
		spikes.addCollider(new BoxCollider());
		spikes.setTag("hazard");
		spikes.translate(15.7f, 0);
		Engine.addRenderable(spikes);
		below.addObject(spikes);
		
		
	}
	private void initTopRight(){
		Engine.addArea(1, 1);
		Area below = Engine.getArea(1,1);
		//platforms
		JoglSprite platform = new JoglSprite("assets/img/tile_t.png", 1, 1);
		platform.setTag("ground");
		platform.setRepeat(6, 1);
		platform.scale(2f, 0.3f);
		platform.translate(15, 2.2f);
		platform.addCollider(new BoxCollider());
		Engine.addRenderable(platform);
		below.addObject(platform);

		JoglSprite platform2 = new JoglSprite("assets/img/tile_t.png", 1, 1);
		platform2.setTag("ground");
		platform2.setRepeat(30, 1);
		platform2.scale(10f, 0.3f);
		platform2.translate(10, -1);
		platform2.addCollider(new BoxCollider());
		Engine.addRenderable(platform2);
		below.addObject(platform2);
		
		JoglSprite ground = new JoglSprite( "assets/img/tile.png", 1, 1);
		ground.setRepeat(10,2);
		ground.scale(25, 5);
		ground.translate(0,-5);
		ground.setTag("ground");
		ground.addCollider(new BoxCollider());
		Engine.addRenderable(ground);
		below.addObject(ground);
		
		//enemy
		JoglSprite enemy = new JoglSprite( "assets/img/spritesheet_hero.png",3,4);
		enemy.setTag("enemy");
		enemy.scale(0.6f, 0.9f);
		enemy.translate(10,0);
		enemy.addCollider(new BoxCollider());
		EnemyBehaviour eb = new EnemyBehaviour();
		eb.allocateObject(enemy);
		enemy.addBehaviour(eb);
		
		
		EmptyObject awareness = new EmptyObject();
		awareness.setParent(enemy);
		awareness.addCollider(new BoxCollider());
		awareness.scale(2, 1);
		awareness.setPosition(enemy.getX()-awareness.getWidth()/2, enemy.getY());
		Engine.addRenderable(awareness);
		Engine.addRenderable(enemy);
		below.addObject(enemy);
		
		//spikes
		JoglSprite spikes = new JoglSprite( "assets/img/spike.png", 1, 1);
		spikes.setRepeat(1, 4);
		spikes.scale(1, 4);
		spikes.addCollider(new BoxCollider());
		spikes.setTag("hazard");
		spikes.translate(15.7f, 0);
		Engine.addRenderable(spikes);
		below.addObject(spikes);
		
		
	}
	private void initTopFarLeft(){
		Engine.addArea(-2, 1);
		Area below = Engine.getArea(-2,1);
		//platforms
		JoglSprite platform = new JoglSprite("assets/img/tile_t.png", 1, 1);
		platform.setTag("ground");
		platform.setRepeat(6, 1);
		platform.scale(2f, 0.3f);
		platform.translate(15, 2.2f);
		platform.addCollider(new BoxCollider());
		Engine.addRenderable(platform);
		below.addObject(platform);

		JoglSprite platform2 = new JoglSprite("assets/img/tile_t.png", 1, 1);
		platform2.setTag("ground");
		platform2.setRepeat(30, 1);
		platform2.scale(10f, 0.3f);
		platform2.translate(10, -1);
		platform2.addCollider(new BoxCollider());
		Engine.addRenderable(platform2);
		below.addObject(platform2);
		
		JoglSprite ground = new JoglSprite( "assets/img/tile.png", 1, 1);
		ground.setRepeat(10,2);
		ground.scale(25, 5);
		ground.translate(0,-5);
		ground.setTag("ground");
		ground.addCollider(new BoxCollider());
		Engine.addRenderable(ground);
		below.addObject(ground);
		
		//enemy
		JoglSprite enemy = new JoglSprite( "assets/img/spritesheet_hero.png",3,4);
		enemy.setTag("enemy");
		enemy.scale(0.6f, 0.9f);
		enemy.translate(10,0);
		enemy.addCollider(new BoxCollider());
		EnemyBehaviour eb = new EnemyBehaviour();
		eb.allocateObject(enemy);
		enemy.addBehaviour(eb);
		
		
		EmptyObject awareness = new EmptyObject();
		awareness.setParent(enemy);
		awareness.addCollider(new BoxCollider());
		awareness.scale(2, 1);
		awareness.setPosition(enemy.getX()-awareness.getWidth()/2, enemy.getY());
		Engine.addRenderable(awareness);
		Engine.addRenderable(enemy);
		below.addObject(enemy);
		
		//spikes
		JoglSprite spikes = new JoglSprite( "assets/img/spike.png", 1, 1);
		spikes.setRepeat(1, 4);
		spikes.scale(1, 4);
		spikes.addCollider(new BoxCollider());
		spikes.setTag("hazard");
		spikes.translate(15.7f, 0);
		Engine.addRenderable(spikes);
		below.addObject(spikes);
		
		
	}
	private void initTopFarRight(){
		Engine.addArea(2, 1);
		Area below = Engine.getArea(2,1);
		//platforms
		JoglSprite platform = new JoglSprite("assets/img/tile_t.png", 1, 1);
		platform.setTag("ground");
		platform.setRepeat(6, 1);
		platform.scale(2f, 0.3f);
		platform.translate(15, 2.2f);
		platform.addCollider(new BoxCollider());
		Engine.addRenderable(platform);
		below.addObject(platform);

		JoglSprite platform2 = new JoglSprite("assets/img/tile_t.png", 1, 1);
		platform2.setTag("ground");
		platform2.setRepeat(30, 1);
		platform2.scale(10f, 0.3f);
		platform2.translate(10, -1);
		platform2.addCollider(new BoxCollider());
		Engine.addRenderable(platform2);
		below.addObject(platform2);
		
		JoglSprite ground = new JoglSprite( "assets/img/tile.png", 1, 1);
		ground.setRepeat(10,2);
		ground.scale(25, 5);
		ground.translate(0,-5);
		ground.setTag("ground");
		ground.addCollider(new BoxCollider());
		Engine.addRenderable(ground);
		below.addObject(ground);
		
		//enemy
		JoglSprite enemy = new JoglSprite( "assets/img/spritesheet_hero.png",3,4);
		enemy.setTag("enemy");
		enemy.scale(0.6f, 0.9f);
		enemy.translate(10,0);
		enemy.addCollider(new BoxCollider());
		EnemyBehaviour eb = new EnemyBehaviour();
		eb.allocateObject(enemy);
		enemy.addBehaviour(eb);
		
		
		EmptyObject awareness = new EmptyObject();
		awareness.setParent(enemy);
		awareness.addCollider(new BoxCollider());
		awareness.scale(2, 1);
		awareness.setPosition(enemy.getX()-awareness.getWidth()/2, enemy.getY());
		Engine.addRenderable(awareness);
		Engine.addRenderable(enemy);
		below.addObject(enemy);
		
		//spikes
		JoglSprite spikes = new JoglSprite( "assets/img/spike.png", 1, 1);
		spikes.setRepeat(1, 4);
		spikes.scale(1, 4);
		spikes.addCollider(new BoxCollider());
		spikes.setTag("hazard");
		spikes.translate(15.7f, 0);
		Engine.addRenderable(spikes);
		below.addObject(spikes);
		
		
	}
	
	
	
	
}
