package com.ltj.editor;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import com.jogamp.opengl.awt.GLCanvas;
import com.ltj.java.engine.JoglRenderer;
import com.ltj.java.engine.JoglSprite;
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

	public AreaList(GLCanvas canvas, EditorView editorView){
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
		
		prepareList(editorView);
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

	private void prepareList(EditorView editorView) {
		areaListModel = new DefaultListModel<RenderObject>();
		areaList = new JList<RenderObject>(areaListModel);
		
		final JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem rename = new JMenuItem("Rename");
		rename.setActionCommand("Rename");
		rename.addActionListener(ae -> {
			editorView.renameObject();
		});
		popupMenu.add(rename);
		popupMenu.add(new JPopupMenu.Separator());
		
		
		JMenuItem delete = new JMenuItem("Delete");
		delete.setActionCommand("Delete");
		delete.addActionListener(ae -> {
			editorView.removeObject();
		});

		delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE,0));
		
		popupMenu.add(delete);

		areaList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {

				areaList.setSelectedIndex(areaList.locationToIndex(me.getPoint()));

				if (SwingUtilities.isRightMouseButton(me)) {
					popupMenu.show(areaList, me.getX(), me.getY());
				} 

			}
		});
	}
	
	public JList<RenderObject> getList(){
		return areaList;
	}
	
	public void addElement(RenderObject obj){
		currentArea.addObjectWorldSpace(obj);
		areaListModel.addElement(obj);
	}
	
	public void setSelectedIndex(int index){
		areaList.setSelectedIndex(index);
	}
	
	public int getLength(){
		return areaListModel.getSize();
	}
	
	public void displayAreaList(Area area){
		currentArea = area;
		x.setText("" + (int)(currentArea.getX() / Engine.getAreaWidth()));
		y.setText("" + (int)(currentArea.getY() / Engine.getAreaHeight()));
		openAreaList(area);
	}
	
	
	private void openAreaList(Area area) {
		areaListModel.clear();
		Camera.setLookAt(area.getX(),area.getY());
		if (area.getObjectList()!= null){
			for (Sprite o : area.getObjectList()){
				areaListModel.addElement((RenderObject) o);
			}
		}
		
		JoglSprite selection = JoglRenderer.getSelectionSprite();
		selection.setScale(Engine.getAreaWidth(),Engine.getAreaHeight());
		selection.setPosition(area.getX(), area.getY());
		
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
				currentArea = Engine.getArea(value, (int)(currentArea.getY() / Engine.getAreaHeight()));
			} else if (field == y){
				currentArea = Engine.getArea((int)(currentArea.getX() / Engine.getAreaWidth()),value);
			}
			
			openAreaList(currentArea);
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			
		}
	}

	public DefaultListModel<RenderObject> getModel() {
		return areaListModel;
	}

	@Override
	public synchronized void addKeyListener(KeyListener l) {
		areaList.addKeyListener(l);
	}
}
