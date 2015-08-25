package com.ltj.java.view;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.jogamp.opengl.awt.GLCanvas;
import com.ltj.shared.engine.Area;
import com.ltj.shared.engine.Camera;
import com.ltj.shared.engine.Engine;
import com.ltj.shared.engine.RenderObject;
import com.ltj.shared.engine.Sprite;

@SuppressWarnings("serial")
public class AreaList extends JPanel {

	private JTextField x,y;
	private Area currentArea;
	private DefaultListModel<RenderObject> areaListModel;
	private GLCanvas canvas;
	private JList<RenderObject> areaList;

	public AreaList(GLCanvas canvas){
		this.canvas = canvas;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		Box box = new Box(BoxLayout.X_AXIS);
		box.setMaximumSize(new Dimension(200, 30));
		x = new JTextField("0",4);
		y = new JTextField("0",4);
		box.add(new JLabel("x "));
		box.add(x);
		box.add(new JLabel(" y "));
		box.add(y);
		add(box);
		areaListModel = new DefaultListModel<RenderObject>();
		areaList = new JList<RenderObject>(areaListModel);
		JScrollPane scroller = new JScrollPane(areaList);
		add(scroller);
		AreaKeyListener listener = new AreaKeyListener();
		x.addKeyListener(listener);
		y.addKeyListener(listener);
		if (Engine.getCurrentArea() == null){
			Engine.addArea(0, 0);
			Engine.setCurrentArea(0, 0);
		}
	}
	
	public JList<RenderObject> getList(){
		return areaList;
	}
	
	private void openAreaList(){
		areaListModel.clear();
		Camera.setLookAt(currentArea.getX(),currentArea.getY());
		if (currentArea.getObjectList()!= null){
			for (Sprite o : currentArea.getObjectList()){
				areaListModel.addElement((RenderObject) o);
			}
		}
		canvas.display();
	}
	
private class AreaKeyListener implements KeyListener {
		
		@Override
		public void keyTyped(KeyEvent e) {
			
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			JTextField field = (JTextField)e.getSource();
			if (field.getText().isEmpty() || field.getText().equals("-")){
				return;
			}
			int value = Integer.parseInt(field.getText());
			if (field == x){
				currentArea = Engine.getArea(value,Engine.getCurrentArea().getY());
			} else if (field == y){
				currentArea = Engine.getArea(Engine.getCurrentArea().getX(),value);
			}
			openAreaList();
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			
		}
	}
}
