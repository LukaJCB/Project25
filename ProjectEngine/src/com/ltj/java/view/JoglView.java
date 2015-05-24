package com.ltj.java.view;

import javax.swing.JFrame;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import com.ltj.java.engine.JoglRenderer;

public class JoglView{
	public static void main(String[] args){
		GLProfile glp = GLProfile.getDefault();
		GLCapabilities caps = new GLCapabilities(glp);
		GLCanvas canvas = new GLCanvas(caps);

		JFrame frame = new JFrame("AWT Window Test");
		frame.setSize(1280,720);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FPSAnimator animator = new FPSAnimator(canvas, 60);
		JoglRenderer r = new JoglRenderer();
		canvas.addGLEventListener(r);
		frame.add(canvas);
		frame.addKeyListener(r);
		animator.start();
		
	}
	
}
