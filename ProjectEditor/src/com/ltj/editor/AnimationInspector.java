package com.ltj.editor;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.ltj.shared.engine.Animation;


@SuppressWarnings("serial")
public class AnimationInspector extends JPanel {
	private JTextField animationTime;
	private JTextField texRow;
	private JCheckBox looping;
	private JTextField numCols;
	private Animation currentAnimation;

	public AnimationInspector(){
		
		setLayout(new GridLayout(4, 2));
		
		KeyAnimationListener listener = new KeyAnimationListener();
		JLabel at = new JLabel("Animation Time");
		add(at);
		animationTime = new JTextField();
		animationTime.addKeyListener(listener);
		add(animationTime);
		
		JLabel tx = new JLabel("Texture Row");
		add(tx);
		texRow = new JTextField();
		texRow.addKeyListener(listener);
		add(texRow);
		
		JLabel lo = new JLabel("Looping");
		add(lo);
		looping = new JCheckBox();
		looping.addChangeListener(ce -> currentAnimation.setLooping(looping.isSelected()));
		add(looping);
		
		JLabel nc = new JLabel("Number of Steps");
		add(nc);
		numCols = new JTextField();
		numCols.addKeyListener(listener);
		add(numCols);
	}
	
	public void openInspector(Animation a){
		
		currentAnimation = a;
		animationTime.setText("" + a.getAnimationTime());
		texRow.setText("" + a.getTexRow());
		looping.setSelected(a.isLooping());
		numCols.setText(""+a.getNumCols());
	}
	
	private class KeyAnimationListener implements KeyListener {

		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getSource() == animationTime){
				currentAnimation.setAnimationTime(Integer.parseInt(animationTime.getText()));
			} else if (e.getSource() == texRow){
				currentAnimation.setTexRow(Integer.parseInt(texRow.getText()));
			} else if (e.getSource() == numCols ){
				currentAnimation.setNumCols(Integer.parseInt(numCols.getText()));
			} 

		}

		@Override
		public void keyTyped(KeyEvent e) {

		}

		@Override
		public void keyPressed(KeyEvent e) {

		}
	}
}
