package com.ltj.java.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;

import com.ltj.shared.engine.AreaMode;
import com.ltj.shared.engine.Engine;
import com.ltj.shared.engine.primitives.Rectangle;

@SuppressWarnings("serial")
public class SettingsMenu extends JMenu {
	
	private JRadioButtonMenuItem none;
	private JRadioButtonMenuItem hide;
	private JRadioButtonMenuItem dynamicLoad;

	public SettingsMenu(){
		super("Settings");
		
		
		setupAreaSettings();
		
		
		setupCollisionSettings();
	}

	private void setupCollisionSettings() {
		JMenu collisions = new JMenu("Collision Detection");
		add(collisions);
		
		ButtonGroup collisionModes = new ButtonGroup();
		JRadioButtonMenuItem collideAll = new JRadioButtonMenuItem("Collide All");
		JRadioButtonMenuItem useShMap = new JRadioButtonMenuItem("Spatial Hashing");
		useShMap.setSelected(true);
		collisionModes.add(collideAll);
		collisionModes.add(useShMap);
		collisions.add(collideAll);
		collisions.add(useShMap);
		
		
		JMenuItem collisionArea = new JMenuItem("Collision Area");
		
		collisions.add(collisionArea);
		collisionArea.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				createCollisionZoneDialog();
				
			}
		});
	}

	private void setupAreaSettings() {
		JMenu areaSettings = new JMenu("Area Settings");
		add(areaSettings);
		
		ButtonGroup areaModes = new ButtonGroup();
		AreaSettingsListener listener = new AreaSettingsListener();
		none = new JRadioButtonMenuItem("None");
		none.addActionListener(listener);
		hide = new JRadioButtonMenuItem("Hide");
		hide.addActionListener(listener);
		dynamicLoad = new JRadioButtonMenuItem("Dynamic");
		dynamicLoad.addActionListener(listener);
		areaSettings.add(none);
		areaSettings.add(hide);
		areaSettings.add(dynamicLoad);

		areaModes.add(none);
		areaModes.add(hide);
		areaModes.add(dynamicLoad);
		
		JMenuItem areaSize = new JMenuItem("Area Size");
		areaSize.addActionListener(listener);
		areaSettings.add(areaSize);
	}
	
	public void setAreaMode(AreaMode a){
		if (a == AreaMode.HIDE){
			hide.setSelected(true);
		} else if (a == AreaMode.NONE){
			none.setSelected(true);
		} else {
			dynamicLoad.setSelected(true);
		}
	}
	
	private void createAreaSizeDialog(){
		JTextField width = new JTextField(5);
		width.setText(""+Engine.getAreaWidth());
		JTextField height = new JTextField(5);
		height.setText("" +  Engine.getAreaHeight());

		JPanel dialog = new JPanel();
		dialog.add(new JLabel("Width:"));
		dialog.add(width);
		dialog.add(Box.createHorizontalStrut(12)); 
		dialog.add(new JLabel("Height:"));
		dialog.add(height);

		int result = JOptionPane.showConfirmDialog(null, dialog, 
				"Area size", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			Engine.setAreaSize(Float.parseFloat(width.getText()), Float.parseFloat(height.getText()));
		}
	}
	

	private void createCollisionZoneDialog(){
		JTextField x = new JTextField(5);
		JTextField y = new JTextField(5);
		JTextField width = new JTextField(5);
		JTextField height = new JTextField(5);
		

		Rectangle r = Engine.getCollisionZone();
		if (r != null){
			x.setText(""+r.getX());
			y.setText("" + r.getY());

			width.setText(""+r.getWidth());
			height.setText("" + r.getHeight());
		}
		
		JPanel dialog = new JPanel();
		dialog.add(new JLabel("X: "));
		dialog.add(x);
		dialog.add(new JLabel("Y:"));
		dialog.add(y);
		dialog.add(new JLabel("Width:"));
		dialog.add(width);
		dialog.add(new JLabel("Height:"));
		dialog.add(height);

		int result = JOptionPane.showConfirmDialog(null, dialog, 
				"Collision zone", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			Rectangle newRect = new Rectangle(Float.parseFloat(x.getText()),Float.parseFloat(y.getText()),
					Float.parseFloat(width.getText()), Float.parseFloat(height.getText()));
			Engine.setCollisionZone(newRect);
		}
	}
	
	
	private class AreaSettingsListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == hide){
				Engine.setAreaMode(AreaMode.HIDE);
			} else if (e.getSource() == none){
				Engine.setAreaMode(AreaMode.NONE);
			} else if (e.getSource() == dynamicLoad){
				Engine.setAreaMode(AreaMode.DYNAMIC_LOAD);
			} else {
				createAreaSizeDialog();
			}
			
		}
		
	}
}
