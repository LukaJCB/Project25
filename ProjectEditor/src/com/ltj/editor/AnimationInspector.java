package com.ltj.editor;

import java.awt.GridLayout;

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

	public AnimationInspector(){
		
		setLayout(new GridLayout(4, 2));
		
		JLabel at = new JLabel("Animation Time");
		add(at);
		animationTime = new JTextField();
		add(animationTime);
		
		JLabel tx = new JLabel("Texture Row");
		add(tx);
		texRow = new JTextField();
		add(texRow);
		
		JLabel lo = new JLabel("Looping");
		add(lo);
		looping = new JCheckBox();
		add(looping);
		
		JLabel nc = new JLabel("Number of Steps");
		add(nc);
		numCols = new JTextField();
		add(numCols);
	}
	
	public void openInspector(Animation a){
		animationTime.setText("" + a.getAnimationTime());
		texRow.setText("" + a.getTexRow());
		looping.setSelected(a.isLooping());
		numCols.setText(""+a.getNumCols());
	}
}
