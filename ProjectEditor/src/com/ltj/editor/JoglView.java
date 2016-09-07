package com.ltj.editor;

import javax.swing.JFrame;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.Animator;
import com.ltj.game.disper.DisperRenderer;
import com.ltj.java.engine.JoglRenderer;

public class JoglView{
	private JFrame frame;
	private Animator anim;

	public JoglView(){
		GLProfile glp = GLProfile.getDefault();
		GLCapabilities caps = new GLCapabilities(glp);
		GLCanvas canvas = new GLCanvas(caps);

		frame = new JFrame("Dimension Engine");
		frame.setSize(1280,720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		anim = new Animator(canvas);
		anim.setRunAsFastAsPossible(true);
		JoglRenderer r = new DisperRenderer();
		canvas.addGLEventListener(r);
		frame.add(canvas);
		frame.addKeyListener(r);
	}
	
	public void start(){
		frame.setVisible(true);
		anim.start();
	}
	
	public static void main(String[] args){
		new JoglView().start();
		
		
	}
	
}
