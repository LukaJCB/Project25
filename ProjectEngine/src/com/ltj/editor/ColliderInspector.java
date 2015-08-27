package com.ltj.editor;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.ltj.shared.engine.Collider;

@SuppressWarnings("serial")
public class ColliderInspector extends JPanel {

	private JTextField xOffset, yOffset, xScale, yScale;
	
	public ColliderInspector(){
		
		setLayout(new GridLayout(4, 2));
		JLabel xo = new JLabel("X Offset");
		add(xo);
		xOffset = new JTextField();
		add(xOffset);
		
		JLabel yo = new JLabel("Y Offset");
		add(yo);
		yOffset = new JTextField();
		add(yOffset);
		
		JLabel xs = new JLabel("X Scale");
		add(xs);
		xScale = new JTextField();
		add(xScale);
		
		JLabel ys = new JLabel("Y Scale");
		add(ys);
		yScale = new JTextField();
		add(yScale);
	}
	
	public void openInspector(Collider c){
		xOffset.setText(""+ c.getxOffset());
		yOffset.setText(""+c.getyOffset());
		xScale.setText("" + c.getxScaling());
		yScale.setText("" + c.getyScaling());
		
	}
}
