package com.ltj.editor;

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
import com.ltj.shared.engine.EmptyObject;
import com.ltj.shared.engine.Engine;
import com.ltj.shared.engine.RenderObject;

@SuppressWarnings("serial")
public class MainInspector extends JPanel {
	private JTextField x,y,z,width,height, rotation,tag,textureRow,textureCol,repeatX,repeatY;
	private JLabel labelZ, labelModeS,labelTextureRow,labelTextureCol;
	private JLabel labelMirrorX,labelMirrorY,labelRepeatX,labelRepeatY;
	private JCheckBox modeS,mirrorX,mirrorY;
	private int currentId;
	private GLCanvas canvas;
	private JCheckBox inactiveOnLoad;
	
	public MainInspector(GLCanvas cnv){
		canvas = cnv;
		setLayout(new GridLayout(18, 2));
		setVisible(false);
		setPreferredSize(new Dimension(180,500));

		KeyChangeListener listener = new KeyChangeListener();
		JLabel	labelX = new JLabel("X");
		add(labelX);
		x = new JTextField();
		x.addKeyListener(listener);
		add(x);

		JLabel labelY = new JLabel("Y");
		add(labelY);
		y = new JTextField();
		y.addKeyListener(listener);
		add(y);

		labelZ= new JLabel("Z");
		add(labelZ);
		z = new JTextField();
		z.addKeyListener(listener);
		add(z);

		JLabel labelWidth = new JLabel("width");
		add(labelWidth);
		width = new JTextField();
		width.addKeyListener(listener);
		add(width);

		JLabel labelHeight = new JLabel("Height");
		add(labelHeight);
		height = new JTextField();
		height.addKeyListener(listener);
		add(height);

		JLabel labelRotation = new JLabel("Rotation");
		add(labelRotation);
		rotation = new JTextField();
		rotation.addKeyListener(listener);
		add(rotation);

		JLabel labelTag = new JLabel("Tag");
		add(labelTag);
		tag = new JTextField();
		tag.addKeyListener(listener);
		add(tag);

		labelMirrorX = new JLabel("Mirror X");
		add(labelMirrorX);
		mirrorX = new JCheckBox();
		add(mirrorX);
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
		add(labelMirrorY);
		mirrorY = new JCheckBox();
		add(mirrorY);
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
		add(labelModeS);
		modeS = new JCheckBox();
		add(modeS);
		modeS.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent e) {
				RenderObject o = Engine.getAllObjects().get(currentId);
				o.setModeSevenEnabled(modeS.isSelected());
				canvas.display();
			}
		});


		labelRepeatX = new JLabel("Repeat X");
		add(labelRepeatX);
		repeatX = new JTextField();
		add(repeatX);
		repeatX.addKeyListener(listener);

		labelRepeatY = new JLabel("Repeat Y");
		add(labelRepeatY);
		repeatY = new JTextField();
		add(repeatY);
		repeatY.addKeyListener(listener);



		labelTextureCol = new JLabel("Texture Column");
		add(labelTextureCol);
		textureCol = new JTextField();
		add(textureCol);
		textureCol.addKeyListener(listener);

		labelTextureRow = new JLabel("Texture Row");
		add(labelTextureRow);
		textureRow = new JTextField();
		add(textureRow);
		textureRow.addKeyListener(listener);


		add(new JLabel("Inactive on Load?"));
		inactiveOnLoad = new JCheckBox();
		inactiveOnLoad.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				RenderObject o = Engine.getAllObjects().get(currentId);
				o.setInactiveOnLoad(inactiveOnLoad.isSelected());
			}
		});
		add(inactiveOnLoad);

	}
	
	public void openInspector(int selectedIndex) {
		currentId = selectedIndex;
		RenderObject o = Engine.getAllObjects().get(currentId);
		x.setText(""+o.getX());
		y.setText(""+o.getY());
		width.setText(""+o.getWidth());
		height.setText(""+o.getHeight());
		rotation.setText("" + o.getRotation());
		tag.setText(o.getTag());
		inactiveOnLoad.setSelected(o.isInactiveOnLoad());
		
		boolean full = (o.getClass() != EmptyObject.class);
		setupEmptyInspector(full);
		if (full){

			if (o.getNumCols() > 1 || o.getNumRows() > 1){
				labelRepeatX.setVisible(false);
				labelRepeatY.setVisible(false);
				repeatX.setVisible(false);
				repeatY.setVisible(false);

				textureCol.setText("" + o.getTextureColumn());
				textureRow.setText("" + o.getTextureRow());
			} else {
				labelTextureCol.setVisible(false);
				labelTextureRow.setVisible(false);
				textureCol.setVisible(false);
				textureRow.setVisible(false);

				repeatX.setText(""+o.getRepeatX());
				repeatY.setText(""+ o.getRepeatY());
			}


			modeS.setSelected(o.isModeSevenEnabled());
			mirrorX.setSelected(o.isMirroredX());
			mirrorY.setSelected(o.isMirroredY());
			z.setText("" + o.getZ());
		}
	}

	private void setupEmptyInspector(boolean full) {
		labelModeS.setVisible(full);
		modeS.setVisible(full);
		labelMirrorX.setVisible(full);
		labelMirrorY.setVisible(full);
		mirrorX.setVisible(full);
		mirrorY.setVisible(full);
		labelZ.setVisible(full);
		z.setVisible(full);

		labelRepeatX.setVisible(full);
		labelRepeatY.setVisible(full);
		repeatX.setVisible(full);
		repeatY.setVisible(full);

		labelTextureCol.setVisible(full);
		labelTextureRow.setVisible(full);
		textureCol.setVisible(full);
		textureRow.setVisible(full);

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

				RenderObject o = Engine.getAllObjects().get(currentId);

				if (e.getSource() == x){
					o.setPosition(Float.parseFloat(x.getText()), o.getY());
				} else if (e.getSource() == y){
					o.setPosition(o.getX(),Float.parseFloat(y.getText()));
				} else if (e.getSource() == width){
					o.setScale(Float.parseFloat(width.getText()), o.getHeight());
				} else if (e.getSource() == height){
					o.setScale(o.getWidth(),Float.parseFloat(height.getText()));
				} else if (e.getSource() == rotation){
					o.setRotation(Float.parseFloat(rotation.getText()));
				} else if (e.getSource() == z){
					o.setZ(Float.parseFloat(z.getText()));
				} else if (e.getSource() == textureCol){
					o.setTexture(Integer.parseInt(textureCol.getText()), o.getTextureRow());
				} else if (e.getSource() == textureRow){
					o.setTexture(o.getTextureColumn(), Integer.parseInt(textureRow.getText()));
				} else if (e.getSource() == repeatX){
					o.setRepeat(Float.parseFloat(repeatX.getText()), o.getRepeatY());
				} else if (e.getSource() == repeatY){
					o.setRepeat(o.getRepeatX(), Float.parseFloat(repeatY.getText()));
				}

				canvas.display();
			}
		}

	}

}
