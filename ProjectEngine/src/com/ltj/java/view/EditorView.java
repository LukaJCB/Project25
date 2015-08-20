package com.ltj.java.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.ltj.java.engine.JoglRenderer;
import com.ltj.java.engine.JoglSprite;
import com.ltj.shared.engine.EmptyObject;
import com.ltj.shared.engine.Engine;
import com.ltj.shared.engine.RenderObject;

public class EditorView {

	private JFrame mainFrame;
	private DefaultListModel<RenderObject> listModel;
	private GLCanvas canvas;
	private JList<RenderObject> list;
	private InspectorPanel inspector;
	private JoglRenderer renderer;
	private JoglSprite selection;
	private ListSelectionListener selectionListener;
	
	public EditorView(){
		prepareGUI();
		prepareList();
		prepareMenuBar();
		prepareGLView();
		prepareInspector();

	}
	
	public void start(){

		mainFrame.setVisible(true);  

	}


	public static void main(String[] args){
		EditorView eView = new EditorView();  
		eView.start();
		
	}

	private void prepareGUI(){
		mainFrame = new JFrame("Java SWING Examples");
		mainFrame.setSize(900,600);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLayout(new BorderLayout(5, 5));

	
	}

	private void prepareMenuBar(){
		JMenuBar menuBar = new JMenuBar();
		
		JMenu file = new JMenu("File");
		
		JMenuItem fileSave = new JMenuItem("Save");
		file.add(fileSave);
		
		menuBar.add(file);
		
		
		JMenu object = new JMenu("GameObject");
		
		JMenuItem addObject = new JMenuItem("Add GameObject");
		object.add(addObject);
		addObject.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CreateSpriteDialog dialog = new CreateSpriteDialog(mainFrame,canvas,listModel,list,inspector);
				dialog.setVisible(true);
				if (selection == null){
					selection = new JoglSprite("selection.png", 1, 1);
				}
			}
		});
		JMenuItem addEmpty = new JMenuItem("Add EmptyObject");
		addEmpty.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				EmptyObject eo = new EmptyObject();
				eo.setName("EmptyObject");
				Engine.addRenderable(eo);
				listModel.addElement(eo);
				list.setSelectedIndex(listModel.getSize()-1);
				
			}
		});
		object.add(addEmpty);
		
		menuBar.add(object);
		
		JCheckBox modeS = new JCheckBox("ModeSeven");
		modeS.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				renderer.changeMode();
				canvas.display();
			}
		});
		menuBar.add(modeS);
		
		mainFrame.setJMenuBar(menuBar);
		
	}
	
	private void prepareInspector() {
		inspector = new InspectorPanel(canvas,renderer);
		mainFrame.add(inspector, BorderLayout.LINE_END);
		
		
	}

	private void prepareList(){
		
		listModel = new DefaultListModel<RenderObject>();
		listModel.addElement(new EmptyObject());
		list = new JList<RenderObject>(listModel);
		
		final JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem rename = new JMenuItem("Rename");
		rename.setActionCommand("Rename");
		rename.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog jd = new RenameDialog(mainFrame,listModel.get(list.getSelectedIndex()));
				jd.setVisible(true);
			}
		});
		popupMenu.add(rename);
		
		popupMenu.add(new JPopupMenu.Separator());
		
		JMenuItem delete = new JMenuItem("Delete");
		delete.setActionCommand("Delete");
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = list.getSelectedIndex();
				listModel.remove(index);
				Engine.getAllObjects().remove(index);
				list.clearSelection();
				
				canvas.display();
			}
		});
		popupMenu.add(delete);
		
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				
				list.setSelectedIndex(list.locationToIndex(me.getPoint()));
	
				if (SwingUtilities.isRightMouseButton(me)) {
					popupMenu.show(list, me.getX(), me.getY());
				} 
			
			}
		});
		
		selectionListener = new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				
				inspector.openInspector(list.getSelectedIndex());
				RenderObject o = list.getSelectedValue();
				selection.setPosition(o.getX(),o.getY());
				selection.setScale(o.getWidth(), o.getHeight());
				selection.setRotation(o.getRotation());
				selection.render();
			}
		};
		list.addListSelectionListener(selectionListener);
				
	
	
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(200, 500));
		mainFrame.add(listScroller,BorderLayout.LINE_START);
		
	}

	

	private void prepareGLView(){
		
		GLProfile glp = GLProfile.getDefault();
		GLCapabilities caps = new GLCapabilities(glp);
		canvas = new GLCanvas(caps);

		renderer = new JoglRenderer();
		canvas.addGLEventListener(renderer);
		canvas.reshape(canvas.getX(), canvas.getY(), canvas.getWidth(), canvas.getHeight());
		mainFrame.add(canvas,BorderLayout.CENTER);
		
		CanvasMouseListener cmListener = new CanvasMouseListener(canvas,list,selectionListener);
		cmListener.registerListener();
		
		
	}
}
