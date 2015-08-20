package com.ltj.java.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.JTextComponent;

import com.jogamp.opengl.awt.GLCanvas;
import com.ltj.java.engine.JoglRenderer;
import com.ltj.shared.engine.EmptyObject;
import com.ltj.shared.engine.Engine;
import com.ltj.shared.engine.RenderObject;

@SuppressWarnings("serial")
public class InspectorPanel extends JPanel {
	
	private JPanel mainPanel;
	private JTextField x,y,width,height, rotation,tag;
	private JLabel labelX, labelY, labelWidth, labelHeight,labelModeS;
	private JLabel labelRotation,labelTag,labelMirrorX,labelMirrorY;
	private JCheckBox modeS,mirrorX,mirrorY;
	private int currentId;
	private GLCanvas canvas;


	public InspectorPanel(final GLCanvas canvas, final JoglRenderer renderer){
		this.canvas = canvas;
		setPreferredSize(new Dimension(200,500));
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(8, 1));
		mainPanel.setVisible(false);
		
		KeyChangeListener listener = new KeyChangeListener();
		labelX = new JLabel("x");
		mainPanel.add(labelX);
		x = new JTextField();
		x.addKeyListener(listener);
		mainPanel.add(x);
		
		labelY = new JLabel("y");
		mainPanel.add(labelY);
		y = new JTextField();
		y.addKeyListener(listener);
		mainPanel.add(y);
		
		labelWidth = new JLabel("width");
		mainPanel.add(labelWidth);
		width = new JTextField();
		width.addKeyListener(listener);
		mainPanel.add(width);
		
		labelHeight = new JLabel("Height");
		mainPanel.add(labelHeight);
		height = new JTextField();
		height.addKeyListener(listener);
		mainPanel.add(height);
		
		labelRotation = new JLabel("Rotation");
		mainPanel.add(labelRotation);
		rotation = new JTextField();
		rotation.addKeyListener(listener);
		mainPanel.add(rotation);
		
		labelTag = new JLabel("Tag");
		mainPanel.add(labelTag);
		tag = new JTextField();
		tag.addKeyListener(listener);
		mainPanel.add(tag);
		
		labelMirrorX = new JLabel("Mirror X");
		mainPanel.add(labelMirrorX);
		mirrorX = new JCheckBox();
		mainPanel.add(mirrorX);
		mirrorX.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				JCheckBox cb =(JCheckBox) e.getSource();
				RenderObject o = Engine.getAllObjects().get(currentId);
				o.setMirroring(cb.isSelected(), o.isMirroredY());
				canvas.display();
			}
		});
		
		labelMirrorY = new JLabel("Mirror Y");
		mainPanel.add(labelMirrorY);
		mirrorY = new JCheckBox();
		mainPanel.add(mirrorY);
		mirrorY.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				JCheckBox cb =(JCheckBox) e.getSource();
				RenderObject o = Engine.getAllObjects().get(currentId);
				o.setMirroring(o.isMirroredX(),cb.isSelected());
				canvas.display();
			}
		});
		
		labelModeS = new JLabel("2.5D");
		mainPanel.add(labelModeS);
		modeS = new JCheckBox();
		mainPanel.add(modeS);
		modeS.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent e) {
				JCheckBox cb =(JCheckBox) e.getSource();
				RenderObject o = Engine.getAllObjects().get(currentId);
				o.setModeSevenEnabled(cb.isSelected());
				canvas.display();
			}
		});
		
		
		
		
		add(mainPanel);
	}

	public void openInspector(int selectedIndex) {
		mainPanel.setVisible(true);
		currentId = selectedIndex;
		RenderObject o = Engine.getAllObjects().get(currentId);
		x.setText(""+o.getX());
		y.setText(""+o.getY());
		width.setText(""+o.getWidth());
		height.setText(""+o.getHeight());
		rotation.setText("" + o.getRotation());
		tag.setText(o.getTag());
		if (o.getClass() == EmptyObject.class){
			labelModeS.setVisible(false);
			modeS.setVisible(false);
			labelMirrorX.setVisible(false);
			labelMirrorY.setVisible(false);
			mirrorX.setVisible(false);
			mirrorY.setVisible(false);
		} else {
			labelModeS.setVisible(true);
			modeS.setVisible(true);
			modeS.setSelected(o.isModeSevenEnabled());
			labelMirrorX.setVisible(true);
			labelMirrorY.setVisible(true);
			mirrorX.setVisible(true);
			mirrorX.setSelected(o.isMirroredX());
			mirrorY.setVisible(true);
			mirrorY.setSelected(o.isMirroredY());
		}
	}
	
	private class KeyChangeListener implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) {
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if (!((JTextComponent) e.getSource()).getText().isEmpty()){
				if (e.getSource() == x){
					RenderObject o = Engine.getAllObjects().get(currentId);
					o.setPosition(Float.parseFloat(x.getText()), o.getY());
				} else if (e.getSource() == y){
					RenderObject o = Engine.getAllObjects().get(currentId);
					o.setPosition(o.getX(),Float.parseFloat(y.getText()));
				} else if (e.getSource() == width){
					RenderObject o = Engine.getAllObjects().get(currentId);
					o.setScale(Float.parseFloat(width.getText()), o.getHeight());
				} else if (e.getSource() == height){
					RenderObject o = Engine.getAllObjects().get(currentId);
					o.setScale(o.getWidth(),Float.parseFloat(height.getText()));
				} else if (e.getSource() == rotation){
					RenderObject o = Engine.getAllObjects().get(currentId);
					o.setRotation(Float.parseFloat(rotation.getText()));
				}
				
				canvas.display();
			}
		}
		
	}
}
