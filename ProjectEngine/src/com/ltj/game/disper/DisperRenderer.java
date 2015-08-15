package com.ltj.game.disper;


import com.jogamp.opengl.GLAutoDrawable;
import com.ltj.java.engine.JoglParticleEmitter;
import com.ltj.java.engine.JoglRenderer;
import com.ltj.java.engine.JoglSprite;
import com.ltj.shared.engine.Area;
import com.ltj.shared.engine.AreaMode;
import com.ltj.shared.engine.BehaviourManipulator;
import com.ltj.shared.engine.EmptyObject;
import com.ltj.shared.engine.Engine;
import com.ltj.shared.engine.OrthoRenderObject;
import com.ltj.shared.engine.SoundManager;
import com.ltj.shared.engine.primitives.BoxCollider;
import com.ltj.shared.engine.primitives.RunTimeGlobals;

public class DisperRenderer extends JoglRenderer {



	@Override
	public void init(GLAutoDrawable drawable) {
		super.init(drawable);
		Engine.setAreaSize(25, 15);
		Engine.setAreaMode(AreaMode.HIDE);
		RunTimeGlobals.add("gravity", 0.016f);
		SoundManager.initSoundManager(false);
		SoundManager.addSoundClip("assets/song.wav");
		//SoundManager.setLooping(true);
		//SoundManager.playSoundClip();
		
		Engine.addOrthoRenderObject(new OrthoRenderObject("assets/pic/layout_light.png", Engine.DESKTOP));

		JoglSprite playerWorm = new JoglSprite( "assets/pic/player_worm_run.png",5,1);
		
		playerWorm.setTexture(0, 0);
		PlayerWormController b = new PlayerWormController();
		playerWorm.addCollider(new BoxCollider());
		playerWorm.translate(42,-30);
		playerWorm.addBehaviour(b);
		b.allocateObject(playerWorm);
		JoglParticleEmitter jpe = new JoglParticleEmitter(100, 1000, 0.1f, 0.9f, 0.1f);
		Engine.addParticleEmitter(jpe);
		playerWorm.addParticleEmitter(jpe);

		playerWorm.scale(0.3f, 0.3f);
		playerWorm.setTag("player");
		playerWorm.addAnimation("walk", 5, 0, true, 5);
		Engine.addRenderable(playerWorm);
		playerWorm.setInactive(true);
		
		
		JoglSprite player = new JoglSprite("assets/pic/player_run.png",4,1);
		player.setTexture(0, 0);
		PlayerController b2 = new PlayerController();
		player.addCollider(new BoxCollider());
		player.addBehaviour(b2);
		b2.allocateObject(player);
		JoglParticleEmitter jpe2 = new JoglParticleEmitter(100, 1000, 0.1f, 0.9f, 0.1f);
		Engine.addParticleEmitter(jpe2);
		player.addParticleEmitter(jpe2);

		player.setTag("player");
		player.addAnimation("walk", 4, 0, true, 4);
		player.scale(0.6f, 0.6f);
		player.translate(42, -32.125f);
		//player.setInactive(true);
		player.setPosition(75, -11);
		Engine.addRenderable(player);
		
		
		EmptyObject trigger = new EmptyObject();
		trigger.setTag("trigger");
		trigger.scale(1, 1);
		trigger.addCollider(new BoxCollider());
		trigger.translate(42, -32.125f);
		//Engine.addRenderable(trigger);
		
		trigger.addChild(player);
		TriggerBehaviour tb = new TriggerBehaviour();
		tb.allocateObject(trigger);
		trigger.addBehaviour(tb);
		
		
		init00();
		init0_1();
		init10();
		init1_1();
		init0_2();
		init1_2();
		init2_2();
		init3_2();
		init3_1();
		init30();
		init2_1();

		Engine.setCurrentArea(2, -2);
		
		Engine.start();

		
	}

	private void init00() {
		Area a = Engine.addArea(0, 0);
		
		JoglSprite floor = new JoglSprite("assets/pic/layout_dark.png", 1, 1);
		floor.scale(25, 5);
		floor.translate(0, -5);
		floor.setTag("ground");
		floor.addCollider(new BoxCollider());
		a.addObject(floor);
		Engine.addRenderable(floor);
		
		JoglSprite deco = new JoglSprite("assets/pic/flora_master.png",1,1);
		deco.scale(24, 2);
		deco.translate(0, -1.5f);
		a.addObject(deco);
		Engine.addRenderable(deco);
	}
	
	private void init10(){
		Area a = Engine.addArea(1,0);
		
		JoglSprite platform = new JoglSprite("assets/pic/layout_dark.png", 1, 1);
		platform.scale(17.5f, 2);
		platform.translate(-3.75f, -6.5f);
		platform.addCollider(new BoxCollider());
		platform.setTag("ground");
		a.addObject(platform);
		Engine.addRenderable(platform);
		
		JoglSprite water = new JoglSprite("assets/pic/water_stream.png", 1, 1);
		water.scale(1, 5);
		water.setRepeat(1, 5);
		water.translate(-6.5f, -2);
		a.addObject(water);
		Engine.addRenderable(water);
		
		JoglSprite waterfall = new JoglSprite("assets/pic/water_crushing.png", 3,1);
		waterfall.translate(-6.5f, -5);
		waterfall.addAnimation("fall", 6, 0, true, 3);
		waterfall.startAnimation("fall");
		a.addObject(waterfall);
		Engine.addRenderable(waterfall);
		
		JoglSprite platform2 = new JoglSprite("assets/pic/layout_dark.png", 1, 1);
		platform2.scale(4, 2);
		platform2.translate(10.5f, -6.5f);
		platform2.addCollider(new BoxCollider());
		platform2.setTag("ground");
		a.addObject(platform2);
		Engine.addRenderable(platform2);
		
		JoglSprite spikes = new JoglSprite("assets/pic/spikes.png",1,1);
		spikes.scale(1, 0.25f);
		spikes.translate(8f, -6.375f);
		spikes.addCollider(new BoxCollider());
		spikes.setTag("hazard");
		a.addObject(spikes);
		Engine.addRenderable(spikes);
		
	}
	
	private void init1_1(){
		Area a = Engine.addArea(1,-1);
		
		JoglSprite platform = new JoglSprite("assets/pic/layout_dark.png", 1, 1);
		platform.scale(4, 5);
		platform.translate(3, 5);
		platform.addCollider(new BoxCollider());
		platform.setTag("ground");
		a.addObject(platform);
		Engine.addRenderable(platform);
		
		JoglSprite platform2 = new JoglSprite("assets/pic/layout_dark.png", 1, 1);
		platform2.scale(5, 11);
		platform2.translate(10f, 3f);
		platform2.addCollider(new BoxCollider());
		platform2.setTag("ground");
		a.addObject(platform2);
		Engine.addRenderable(platform2);
		
		JoglSprite ground = new JoglSprite("assets/pic/layout_dark.png",1,1);
		ground.scale(25, 2);
		ground.translate(0, -3.5f);
		ground.addCollider(new BoxCollider());
		ground.setTag("ground");
		a.addObject(ground);
		Engine.addRenderable(ground);
		
		JoglSprite enemy = new JoglSprite("assets/pic/mutant.png",4,2);
		enemy.translate(3, -2);
		enemy.addCollider(new BoxCollider());
		MutantBehaviour mb = new MutantBehaviour();
		mb.allocateObject(enemy);
		enemy.addBehaviour(mb);
		a.addObject(enemy);
		enemy.addAnimation("walk", 5, 0, true, 4);
		enemy.addAnimation("idle", 20, 1, true, 4);
		Engine.addRenderable(enemy);
		
		
		EmptyObject cd = new EmptyObject();
		cd.setTag("changeDirection");
		cd.addCollider(new BoxCollider());
		cd.translate(4.5f, -2f);
		a.addObject(cd);
		Engine.addRenderable(cd);
		
		EmptyObject cd2 = new EmptyObject();
		cd2.setTag("changeDirection");
		cd2.addCollider(new BoxCollider());
		cd2.translate(-6.5f, -2f);
		a.addObject(cd2);
		Engine.addRenderable(cd2);
		
		JoglSprite water = new JoglSprite("assets/pic/water_stream.png", 1, 1);
		water.scale(1, 12);
		water.setRepeat(1, 12);
		water.translate(-6.5f, 4.5f);
		a.addObject(water);
		Engine.addRenderable(water);
		
		JoglSprite waterfall = new JoglSprite("assets/pic/water_crushing.png", 3,1);
		waterfall.translate(-6.5f, -2);
		waterfall.addAnimation("fall", 6, 0, true, 3);
		waterfall.startAnimation("fall");
		a.addObject(waterfall);
		Engine.addRenderable(waterfall);
	}
	
	private void init0_1(){
		Area a = Engine.addArea(0, -1);
		
		JoglSprite moving = new JoglSprite("assets/pic/jelly_big_idle.png",4,1);
		moving.setTag("moving");
		moving.addAnimation("idle", 15, 0, true, 4);
		moving.addCollider(new BoxCollider());
		moving.scale(2.5f,2);
		MovingPlatform mp = new MovingPlatform();
		mp.allocateObject(moving);
		moving.addBehaviour(mp);
		moving.translate(2, -3.5f);
		a.addObject(moving);
		Engine.addRenderable(moving);
		BehaviourManipulator.manipulateField(mp, "movingRight", true);
		

		EmptyObject cd = new EmptyObject();
		cd.setTag("changeDirection");
		cd.addCollider(new BoxCollider());
		cd.translate(13f,  -3.5f);
		a.addObject(cd);
		Engine.addRenderable(cd);
		
		EmptyObject cd2 = new EmptyObject();
		cd2.setTag("changeDirection");
		cd2.addCollider(new BoxCollider());
		cd2.translate(0,  -2.25f);
		a.addObject(cd2);
		Engine.addRenderable(cd2);
		
		
		JoglSprite raising = new JoglSprite("assets/pic/jelly_big_idle.png",4,1);
		raising.setTag("raising");
		raising.addAnimation("idle", 15, 0, true, 4);
		raising.addCollider(new BoxCollider());
		raising.scale(2.5f,2);
		AnimatedRaisingPlatform rp = new AnimatedRaisingPlatform();
		rp.allocateObject(raising);
		raising.addBehaviour(rp);
		raising.translate(-1, -4.5f);
		BehaviourManipulator.manipulateField(rp, "delay", 60);
		a.addObject(raising);
		Engine.addRenderable(raising);
		

		EmptyObject cd3 = new EmptyObject();
		cd3.setTag("changeDirection");
		cd3.addCollider(new BoxCollider());
		cd3.translate(-1,  -14.75f);
		a.addObject(cd3);
		Engine.addRenderable(cd3);
		
		
		
		
	}
	
	private void init0_2(){
		Area a = Engine.addArea(0, -2);
		
		JoglSprite platform = new JoglSprite("assets/pic/layout_dark.png",1,1);
		platform.setTag("ground");
		platform.addCollider(new BoxCollider());
		platform.scale(5, 2);
		platform.translate(2.75f, 1.75f);
		a.addObject(platform);
		Engine.addRenderable(platform);
		
		JoglSprite platform2 = new JoglSprite("assets/pic/layout_dark.png",1,1);
		platform2.setTag("ground");
		platform2.addCollider(new BoxCollider());
		platform2.scale(5, 2);
		platform2.translate(-4.75f, 1.75f);
		a.addObject(platform2);
		Engine.addRenderable(platform2);
		
		JoglSprite ground = new JoglSprite("assets/pic/layout_dark.png",1,1);
		ground.setTag("ground");
		ground.addCollider(new BoxCollider());
		ground.scale(22, 2);
		ground.translate(0, 0.75f);
		a.addObject(ground);
		Engine.addRenderable(ground);
		

		JoglSprite moving = new JoglSprite("assets/pic/jelly_big_idle.png",4,1);
		moving.setTag("moving");
		moving.addAnimation("idle", 12, 0, true, 4);
		moving.addCollider(new BoxCollider());
		moving.scale(1.25f,1);
		MovingPlatform mp = new MovingPlatform();
		mp.allocateObject(moving);
		moving.addBehaviour(mp);
		moving.translate(-12.85f, -1.5f);
		a.addObject(moving);
		Engine.addRenderable(moving);
		BehaviourManipulator.manipulateField(mp, "movingRight", true);
		BehaviourManipulator.manipulateField(mp, "speed", 0.08f);

		EmptyObject cd = new EmptyObject();
		cd.setTag("changeDirection");
		cd.addCollider(new BoxCollider());
		cd.translate(-13.5f,  -1.5f);
		cd.scale(0.1f, 1);
		a.addObject(cd);
		Engine.addRenderable(cd);
		
		EmptyObject cd2 = new EmptyObject();
		cd2.setTag("changeDirection");
		cd2.addCollider(new BoxCollider());
		cd2.translate(13.5f,  -1.5f);
		cd2.scale(0.1f, 1);
		a.addObject(cd2);
		Engine.addRenderable(cd2);
		
		JoglSprite spikes = new JoglSprite("assets/pic/spikes.png",1,1);
		spikes.scale(25, 0.25f);
		spikes.setRepeat(25, 1);
		spikes.translate(0, -2.5f);
		spikes.addCollider(new BoxCollider());
		spikes.setTag("hazard");
		a.addObject(spikes);
		Engine.addRenderable(spikes);
		
		JoglSprite ground2 = new JoglSprite("assets/pic/layout_dark.png",1,1);
		ground2.setTag("ground");
		ground2.scale(25, 5);
		ground2.translate(0, -5.125f);
		a.addObject(ground2);
		Engine.addRenderable(ground2);
	}
	
	private void init1_2(){
		Area a = Engine.addArea(1, -2);
		
		JoglSprite spikes = new JoglSprite("assets/pic/spikes.png",1,1);
		spikes.scale(21, 0.25f);
		spikes.setRepeat(21, 1);
		spikes.translate(-2, -2.5f);
		spikes.addCollider(new BoxCollider());
		spikes.setTag("hazard");
		a.addObject(spikes);
		Engine.addRenderable(spikes);
		
		JoglSprite ground2 = new JoglSprite("assets/pic/layout_dark.png",1,1);
		ground2.setTag("ground");
		ground2.scale(21, 5);
		ground2.translate(-2, -5.125f);
		a.addObject(ground2);
		Engine.addRenderable(ground2);
		
		JoglSprite moving = new JoglSprite("assets/pic/jelly_big_idle.png",4,1);
		moving.setTag("moving");
		moving.addAnimation("idle", 12, 0, true, 4);
		moving.addCollider(new BoxCollider());
		moving.scale(1.25f,1);
		MovingPlatform mp = new MovingPlatform();
		mp.allocateObject(moving);
		moving.addBehaviour(mp);
		moving.translate(14.75f, -1.5f);
		a.addObject(moving);
		Engine.addRenderable(moving);
		BehaviourManipulator.manipulateField(mp, "speed", 0.08f);
		
		

		EmptyObject cd = new EmptyObject();
		cd.setTag("changeDirection");
		cd.addCollider(new BoxCollider());
		cd.scale(0.2f, 1);
		cd.translate(15.5f, -1.5f);
		a.addObject(cd);
		Engine.addRenderable(cd);
		
	}
	
	private void init2_2(){
		Area a = Engine.addArea(2, -2);
		
		JoglSprite ground = new JoglSprite("assets/pic/layout_dark.png",1,1);
		ground.setTag("ground");
		ground.scale(8, 1);
		ground.translate(-8, -6.125f);
		ground.addCollider(new BoxCollider());
		a.addObject(ground);
		Engine.addRenderable(ground);
		
		JoglSprite spikes2 = new JoglSprite("assets/pic/spikes.png",1,1);
		spikes2.scale(8, 0.25f);
		spikes2.setRepeat(8, 1);
		spikes2.translate(-8, -5.5f);
		spikes2.addCollider(new BoxCollider());
		spikes2.setTag("hazard");
		a.addObject(spikes2);
		Engine.addRenderable(spikes2);
		
		JoglSprite spikes = new JoglSprite("assets/pic/spikes.png",1,1);
		spikes.scale(4.5f, 0.25f);
		spikes.setRepeat(4.5f, 1);
		spikes.translate(-14.25f, -6.5f);
		spikes.addCollider(new BoxCollider());
		spikes.setTag("hazard");
		a.addObject(spikes);
		Engine.addRenderable(spikes);
		
		JoglSprite ground2 = new JoglSprite("assets/pic/layout_dark.png",1,1);
		ground2.setTag("ground");
		ground2.scale(29, 1);
		ground2.addCollider(new BoxCollider());
		ground2.translate(-2, -7.125f);
		a.addObject(ground2);
		Engine.addRenderable(ground2);
		
		JoglSprite ground3 = new JoglSprite("assets/pic/layout_dark.png",1,1);
		ground3.setTag("ground");
		ground3.scale(6, 1);
		ground3.translate(-8, -5.125f);
		a.addObject(ground3);
		Engine.addRenderable(ground3);
		
		JoglSprite spikes3 = new JoglSprite("assets/pic/spikes.png",1,1);
		spikes3.scale(6, 0.25f);
		spikes3.setRepeat(6, 1);
		spikes3.translate(-8, -4.5f);
		spikes3.setTag("hazard");
		a.addObject(spikes3);
		Engine.addRenderable(spikes3);
		
		JoglSprite ground4 = new JoglSprite("assets/pic/layout_dark.png",1,1);
		ground4.setTag("ground");
		ground4.scale(4, 1);
		ground4.translate(-8, -4.125f);
		a.addObject(ground4);
		Engine.addRenderable(ground4);
		
		JoglSprite spikes4 = new JoglSprite("assets/pic/spikes.png",1,1);
		spikes4.scale(4, 0.25f);
		spikes4.setRepeat(4, 1);
		spikes4.translate(-8, -3.5f);
		spikes4.addCollider(new BoxCollider());
		spikes4.setTag("hazard");
		a.addObject(spikes4);
		Engine.addRenderable(spikes4);
		
		JoglSprite ground5 = new JoglSprite("assets/pic/layout_dark.png",1,1);
		ground5.setTag("ground");
		ground5.scale(2, 1);
		ground5.addCollider(new BoxCollider());
		ground5.translate(-8, -3.125f);
		a.addObject(ground5);
		Engine.addRenderable(ground5);
	
		
		
	}

	private void init3_2(){
		Area a = Engine.addArea(3, -2);
		
		JoglSprite ground = new JoglSprite("assets/pic/layout_dark.png", 1, 1);
		ground.setTag("ground");
		ground.scale(25, 1);
		ground.addCollider(new BoxCollider());
		ground.translate(0, -7.125f);
		a.addObject(ground);
		Engine.addRenderable(ground);
	
		JoglSprite wall = new JoglSprite("assets/pic/layout_dark.png", 1, 1);
		wall.setTag("ground");
		wall.scale(1, 14.125f);
		wall.addCollider(new BoxCollider());
		wall.translate(12, 0.4375f);
		a.addObject(wall);
		Engine.addRenderable(wall);
		

		JoglSprite wall2 = new JoglSprite("assets/pic/layout_dark.png", 1, 1);
		wall2.setTag("ground");
		wall2.scale(0.5f, 8);
		wall2.addCollider(new BoxCollider());
		wall2.translate(-12.25f, 3.5f);
		a.addObject(wall2);
		Engine.addRenderable(wall2);
	
		JoglSprite platform1 = new JoglSprite("assets/pic/layout_dark.png", 1, 1);
		platform1.setTag("ground");
		platform1.scale(2, 0.2f);
		platform1.addCollider(new BoxCollider());
		platform1.translate(-11, -5.75f);
		a.addObject(platform1);
		Engine.addRenderable(platform1);
		
		JoglSprite platform2 = new JoglSprite("assets/pic/layout_dark.png", 1, 1);
		platform2.setTag("ground");
		platform2.scale(2, 0.2f);
		platform2.addCollider(new BoxCollider());
		platform2.translate(-8, -4f);
		a.addObject(platform2);
		Engine.addRenderable(platform2);
		
		JoglSprite platform3 = new JoglSprite("assets/pic/layout_dark.png", 1, 1);
		platform3.setTag("ground");
		platform3.scale(2, 0.2f);
		platform3.addCollider(new BoxCollider());
		platform3.translate(-11, -2.25f);
		a.addObject(platform3);
		Engine.addRenderable(platform3);
		
		JoglSprite platform4 = new JoglSprite("assets/pic/layout_dark.png", 1, 1);
		platform4.setTag("ground");
		platform4.scale(2, 0.2f);
		platform4.addCollider(new BoxCollider());
		platform4.translate(-8, -0.5f);
		a.addObject(platform4);
		Engine.addRenderable(platform4);
		
		JoglSprite platform5 = new JoglSprite("assets/pic/layout_dark.png", 1, 1);
		platform5.setTag("ground");
		platform5.scale(2, 0.2f);
		platform5.addCollider(new BoxCollider());
		platform5.translate(-11, 1.25f);
		a.addObject(platform5);
		Engine.addRenderable(platform5);
		
		JoglSprite platform6 = new JoglSprite("assets/pic/layout_dark.png", 1, 1);
		platform6.setTag("ground");
		platform6.scale(2, 0.2f);
		platform6.addCollider(new BoxCollider());
		platform6.translate(-8, 3f);
		a.addObject(platform6);
		Engine.addRenderable(platform6);
		
		JoglSprite platform7 = new JoglSprite("assets/pic/layout_dark.png", 1, 1);
		platform7.setTag("ground");
		platform7.scale(2, 0.2f);
		platform7.addCollider(new BoxCollider());
		platform7.translate(-11, 4.75f);
		a.addObject(platform7);
		Engine.addRenderable(platform7);
		
		JoglSprite platform8 = new JoglSprite("assets/pic/layout_dark.png", 1, 1);
		platform8.setTag("ground");
		platform8.scale(1, 0.2f);
		platform8.addCollider(new BoxCollider());
		platform8.translate(-11.5f, 6.5f);
		a.addObject(platform8);
		Engine.addRenderable(platform8);
		
	}
	
	private void init3_1(){
		Area a = Engine.addArea(3, -1);
		
		JoglSprite activatedPlatform = new JoglSprite("assets/pic/layout_dark.png", 1, 1);
		activatedPlatform.setTag("raising");
		activatedPlatform.scale(2.8f, 1);
		activatedPlatform.addCollider(new BoxCollider());
		activatedPlatform.translate(10, -7);
		ActivatedRaisingPlatform ar = new ActivatedRaisingPlatform();
		ar.allocateObject(activatedPlatform);
		activatedPlatform.addBehaviour(ar);
		BehaviourManipulator.manipulateField(ar, "delay", 30);
		BehaviourManipulator.manipulateField(ar, "identifier", "Platform3_1");
		a.addObject(activatedPlatform);
		Engine.addRenderable(activatedPlatform);
		
		EmptyObject activator = new EmptyObject();
		activator.translate(10, -6f);
		activator.addCollider(new BoxCollider());
		activator.setParent(activatedPlatform);
		a.addObject(activator);
		Engine.addRenderable(activator);
		
		EmptyObject cd = new EmptyObject();
		cd.addCollider(new BoxCollider());
		cd.translate(10	, -22);
		cd.setTag("changeDirection");
		a.addObject(cd);
		Engine.addRenderable(cd);
		
		EmptyObject cd2 = new EmptyObject();
		cd2.addCollider(new BoxCollider());
		cd2.translate(10 , 9);
		cd2.setTag("changeDirection");
		a.addObject(cd2);
		Engine.addRenderable(cd2);
		
		
		
		JoglSprite ground = new JoglSprite("assets/pic/layout_dark.png", 1, 1);
		ground.setTag("ground");
		ground.scale(19, 1);
		ground.addCollider(new BoxCollider());
		ground.translate(-1, -7);
		a.addObject(ground);
		Engine.addRenderable(ground);
		
		JoglSprite wall = new JoglSprite("assets/pic/layout_dark.png", 1, 1);
		wall.setTag("ground");
		wall.scale(1, 15);
		wall.addCollider(new BoxCollider());
		wall.translate(12, 0);
		a.addObject(wall);
		Engine.addRenderable(wall);
		

		JoglSprite wall2 = new JoglSprite("assets/pic/layout_dark.png", 1, 1);
		wall2.setTag("ground");
		wall2.scale(0.5f, 5);
		wall2.addCollider(new BoxCollider());
		wall2.translate(-12.25f, -5f);
		a.addObject(wall2);
		Engine.addRenderable(wall2);
		
		JoglSprite wall9 = new JoglSprite("assets/pic/layout_dark.png", 1, 1);
		wall9.setTag("ground");
		wall9.scale(0.5f, 5);
		wall9.addCollider(new BoxCollider());
		wall9.translate(-12.25f, 3);
		a.addObject(wall9);
		Engine.addRenderable(wall9);
		
		
		JoglSprite spikes = new JoglSprite("assets/pic/spikes.png",1,1);
		spikes.setTag("hazard");
		spikes.scale(5, 0.25f);
		spikes.setRepeat(5,1);
		spikes.addCollider(new BoxCollider());
		spikes.rotate(-90);
		spikes.translate(-3, -1);
		a.addObject(spikes);
		Engine.addRenderable(spikes);
		
		JoglSprite spikes2 = new JoglSprite("assets/pic/spikes.png",1,1);
		spikes2.setTag("hazard");
		spikes2.scale(5, 0.25f);
		spikes2.setRepeat(5,1);
		spikes2.addCollider(new BoxCollider());
		spikes2.rotate(90);
		spikes2.translate(-1, -4);
		a.addObject(spikes2);
		Engine.addRenderable(spikes2);
		
		
		JoglSprite wall3 = new JoglSprite("assets/pic/layout_dark.png", 1, 1);
		wall3.scale(0.75f, 5);
		wall3.translate(-0.5f, -4);
		a.addObject(wall3);
		Engine.addRenderable(wall3);
		
		JoglSprite spikes6 = new JoglSprite("assets/pic/spikes.png",1,1);
		spikes6.setTag("hazard");
		spikes6.scale(0.75f, 0.25f);
		spikes6.setRepeat(0.75f,1);
		spikes6.addCollider(new BoxCollider());
		spikes6.translate(-0.5f, -1.375f);
		a.addObject(spikes6);
		Engine.addRenderable(spikes6);
		
		JoglSprite spikes3 = new JoglSprite("assets/pic/spikes.png",1,1);
		spikes3.setTag("hazard");
		spikes3.scale(5, 0.25f);
		spikes3.setRepeat(5,1);
		spikes3.addCollider(new BoxCollider());
		spikes3.rotate(-90);
		spikes3.translate(0, -4);
		a.addObject(spikes3);
		Engine.addRenderable(spikes3);
		
		JoglSprite wall5 = new JoglSprite("assets/pic/layout_dark.png", 1, 1);
		wall5.scale(0.5f, 5.625f);
		wall5.setTag("ground");
		wall5.addCollider(new BoxCollider());
		wall5.translate(2.375f,-0.725f);
		a.addObject(wall5);
		Engine.addRenderable(wall5);
		
		JoglSprite spikes4 = new JoglSprite("assets/pic/spikes.png",1,1);
		spikes4.setTag("hazard");
		spikes4.scale(5, 0.25f);
		spikes4.setRepeat(5,1);
		spikes4.addCollider(new BoxCollider());
		spikes4.rotate(90);
		spikes4.translate(2, -1);
		a.addObject(spikes4);
		Engine.addRenderable(spikes4);
		
		JoglSprite wall4 = new JoglSprite("assets/pic/layout_dark.png", 1, 1);
		wall4.scale(5.5f, 0.5f);
		wall4.setTag("ground");
		wall4.addCollider(new BoxCollider());
		wall4.translate(-0.5f, 1.825f);
		a.addObject(wall4);
		Engine.addRenderable(wall4);
		
		JoglSprite spikes5 = new JoglSprite("assets/pic/spikes.png",1,1);
		spikes5.setTag("hazard");
		spikes5.scale(4.5f, 0.25f);
		spikes5.setRepeat(4.5f,1);
		spikes5.addCollider(new BoxCollider());
		spikes5.rotate(180);
		spikes5.translate(-0.5f, 1.5f);
		a.addObject(spikes5);
		Engine.addRenderable(spikes5);
		
	
		JoglSprite wall6 = new JoglSprite("assets/pic/layout_dark.png", 1, 1);
		wall6.scale(0.5f, 5.625f);
		wall6.setTag("ground");
		wall6.addCollider(new BoxCollider());
		wall6.translate(-3.375f,-0.725f);
		a.addObject(wall6);
		Engine.addRenderable(wall6);
		
		JoglSprite platform = new JoglSprite("assets/pic/layout_dark.png", 1, 1);
		platform.scale(1, 0.2f);
		platform.translate(-2f, -3);
		platform.addCollider(new BoxCollider());
		platform.setTag("raising");
		RaisingPlatform b = new RaisingPlatform();
		b.allocateObject(platform);
		platform.addBehaviour(b);
		a.addObject(platform);
		Engine.addRenderable(platform);
		
		EmptyObject directionChanger1 = new EmptyObject();
		directionChanger1.translate(-2, -6.9f);
		directionChanger1.addCollider(new BoxCollider());
		directionChanger1.setTag("changeDirection");
		a.addObject(directionChanger1);
		Engine.addRenderable(directionChanger1);
		
		EmptyObject directionChanger2 = new EmptyObject();
		directionChanger2.translate(-2, 2);
		directionChanger2.addCollider(new BoxCollider());
		directionChanger2.setTag("changeDirection");
		a.addObject(directionChanger2);
		Engine.addRenderable(directionChanger2);
		
		JoglSprite platform2 = new JoglSprite("assets/pic/layout_dark.png", 1, 1);
		platform2.scale(1, 0.2f);
		platform2.translate(5f, 3);
		platform2.addCollider(new BoxCollider());
		platform2.setTag("ground");
		a.addObject(platform2);
		Engine.addRenderable(platform2);

		JoglSprite platform3 = new JoglSprite("assets/pic/layout_dark.png", 1, 1);
		platform3.scale(1, 0.2f);
		platform3.translate(-8f, -1);
		platform3.addCollider(new BoxCollider());
		platform3.setTag("ground");
		a.addObject(platform3);
		Engine.addRenderable(platform3);
	
		
	
	}
	
	private void init2_1(){
		Area a = Engine.addArea(2, -1);
	}
	
	private void init30(){
		Area a = Engine.addArea(3, 0);
	}
}

