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
		Engine.setAreaMode(a);
	}
	
	private void createAreaSizeDialog(){
		JTextField width = new JTextField(5);
		width.setText(""+Engine.getAreaWidth());
		JTextField height = new JTextField(5);
		height.setText("" +  Engine.getAreaHeight());

		JPanel myPanel = new JPanel();
		myPanel.add(new JLabel("Width:"));
		myPanel.add(width);
		myPanel.add(Box.createHorizontalStrut(15)); 
		myPanel.add(new JLabel("Height:"));
		myPanel.add(height);

		int result = JOptionPane.showConfirmDialog(null, myPanel, 
				"Area size", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			Engine.setAreaSize(Float.parseFloat(width.getText()), Float.parseFloat(height.getText()));
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
