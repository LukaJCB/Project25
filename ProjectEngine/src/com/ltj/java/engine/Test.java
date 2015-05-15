package com.ltj.java.engine;

import javax.swing.JFrame;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

public class Test{
	public static void main(String[] args){
		GLProfile glp = GLProfile.getDefault();
		GLCapabilities caps = new GLCapabilities(glp);
		GLCanvas canvas = new GLCanvas(caps);

		JFrame frame = new JFrame("AWT Window Test");
		frame.setSize(1280,720);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FPSAnimator animator = new FPSAnimator(canvas, 30);
		JOGLRenderer r = new JOGLRenderer();
		canvas.addGLEventListener(r);
		frame.add(canvas);
		frame.addKeyListener(r);
		animator.start();
		
	}
	
}
