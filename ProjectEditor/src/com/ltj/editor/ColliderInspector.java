package com.ltj.editor;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.ltj.shared.engine.Collider;

@SuppressWarnings("serial")
public class ColliderInspector extends JPanel {

	private JTextField xOffset, yOffset, xScale, yScale;
	private Collider currentCollider;
	
	public ColliderInspector(){
		
		KeyColliderListener listener = new KeyColliderListener();
		
		setLayout(new GridLayout(4, 2));
		JLabel xo = new JLabel("X Offset");
		add(xo);
		xOffset = new JTextField();
		xOffset.addKeyListener(listener);
		add(xOffset);
		
		JLabel yo = new JLabel("Y Offset");
		add(yo);
		yOffset = new JTextField();
		yOffset.addKeyListener(listener);
		add(yOffset);
		
		JLabel xs = new JLabel("X Scale");
		add(xs);
		xScale = new JTextField();
		xScale.addKeyListener(listener);
		add(xScale);
		
		JLabel ys = new JLabel("Y Scale");
		add(ys);
		yScale = new JTextField();
		yScale.addKeyListener(listener);
		add(yScale);
	}
	
	public void openInspector(Collider c){
		currentCollider = c;
		
		xOffset.setText(""+ c.getxOffset());
		yOffset.setText(""+c.getyOffset());
		xScale.setText("" + c.getxScaling());
		yScale.setText("" + c.getyScaling());
		
	}
	
	private class KeyColliderListener implements KeyListener {

		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getSource() == xOffset){
				currentCollider.setOffet(Float.parseFloat(xOffset.getText()),
						currentCollider.getyOffset());
			} else if (e.getSource() == yOffset){
				currentCollider.setOffet(currentCollider.getxOffset(),
						Float.parseFloat(yOffset.getText()));
			} else if (e.getSource() == xScale ){
				currentCollider.setScaling(Float.parseFloat(xScale.getText()), 
						currentCollider.getyScaling());
			} else {
				currentCollider.setScaling(currentCollider.getxScaling(),
						Float.parseFloat(yScale.getText()));
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
