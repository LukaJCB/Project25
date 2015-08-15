package com.ltj.java.view;

import javax.swing.JFrame;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.Animator;
import com.ltj.game.disper.DisperRenderer;
import com.ltj.java.engine.JoglRenderer;

public class JoglView{
	public static void main(String[] args){
		GLProfile glp = GLProfile.getDefault();
		GLCapabilities caps = new GLCapabilities(glp);
		GLCanvas canvas = new GLCanvas(caps);

		JFrame frame = new JFrame("AWT Window Test");
		frame.setSize(720,480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Animator anim = new Animator(canvas);
		anim.setRunAsFastAsPossible(true);
		JoglRenderer r = new DisperRenderer();
		canvas.addGLEventListener(r);
		frame.add(canvas);
		frame.addKeyListener(r);
		frame.setVisible(true);
		anim.start();
		
	}
	
}
