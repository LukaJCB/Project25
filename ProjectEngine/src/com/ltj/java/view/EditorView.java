package com.ltj.java.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.ltj.java.engine.JoglRenderer;
import com.ltj.java.engine.JoglSprite;
import com.ltj.shared.engine.EmptyObject;
import com.ltj.shared.engine.Engine;
import com.ltj.shared.engine.OrthoRenderObject;
import com.ltj.shared.engine.RenderObject;
import com.ltj.shared.engine.primitives.Rectangle;
import com.ltj.shared.utils.BasicIO;

public class EditorView {

	private JFrame mainFrame;
	private DefaultListModel<RenderObject> listModel;
	private GLCanvas canvas;
	private JList<RenderObject> list;
	private InspectorPanel inspector;
	private JoglRenderer renderer;
	private JoglSprite selection;
	private ListSelectionListener selectionListener;
	private String projectPath;
	private JTabbedPane tabbedPane;
	
	public EditorView(){
		prepareGUI();
		prepareRenderList();
		prepareMenuBar();
		prepareGLView();
		prepareInspector();
		prepareOrthoList();
	}
	
	public void start(){

		mainFrame.setVisible(true);  

	}


	public static void main(String[] args){
		EditorView eView = new EditorView();  
		eView.start();
		
	}

	private void prepareGUI(){
		mainFrame = new JFrame("Project Engine");
		mainFrame.setSize(900,600);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLayout(new BorderLayout(5, 5));

		tabbedPane = new JTabbedPane();
	
	}

	private void prepareMenuBar(){
		JMenuBar menuBar = new JMenuBar();
		
		JMenu file = new JMenu("File");
		

		JMenuItem newFile = new JMenuItem("New...");
		file.add(newFile);
		
		JMenuItem fileSave = new JMenuItem("Save");
		file.add(fileSave);
		
		JMenuItem fileLoad = new JMenuItem("Load");
		file.add(fileLoad);
		
		
		menuBar.add(file);
		
		
		JMenu object = new JMenu("GameObject");
		
		JMenuItem addObject = new JMenuItem("Add GameObject");
		object.add(addObject);
		addObject.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CreateSpriteDialog dialog = new CreateSpriteDialog(mainFrame,canvas,listModel,list,inspector);
				dialog.setVisible(true);
				
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
		

		JMenuItem addOrtho = new JMenuItem("Add OrthoObject");
		addOrtho.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				final JFileChooser chooser = new JFileChooser("assets");
				chooser.setFileFilter(new FileNameExtensionFilter("PNG", "png"));
				
				int result = chooser.showOpenDialog(mainFrame);
				if (result == JFileChooser.APPROVE_OPTION){
					String path =("assets" +chooser.getSelectedFile().getPath().split("assets")[1]);
					OrthoRenderObject oro = new OrthoRenderObject(path, Engine.getPlatform());
					Engine.addOrthoRenderObject(oro);
					canvas.display();
				}
				
			}
		});
		
		object.add(addOrtho);
		
		menuBar.add(object);
		
		
		
		
		
		newFile.addActionListener(new ActionListener() {
			

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog(mainFrame, "Choose a name for your Project.");
				
				JFileChooser chooser = new JFileChooser();
			    chooser.setDialogTitle("Choose Directory");
			    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			    chooser.setAcceptAllFileFilterUsed(false);
			    
			    int state = chooser.showOpenDialog(mainFrame);
			    if (state == JFileChooser.APPROVE_OPTION){
			    	File projectFolder = new File(chooser.getSelectedFile().toString()+ File.separatorChar + name);
			    	projectFolder.mkdir();
			    	projectPath = projectFolder.toString();
			    	mainFrame.setTitle(mainFrame.getTitle() +  " - "  + name);
			    }
				
			}
		});
		
		fileSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				BasicIO.parseToDME(projectPath, "Scene");
				
			}
		});
		
		fileLoad.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileFilter(new FileNameExtensionFilter("DME", "dme"));
				int result = chooser.showOpenDialog(mainFrame);
				
				if (result == JFileChooser.APPROVE_OPTION){
					try {
						BasicIO.loadFromDME(chooser.getCurrentDirectory().getPath(), chooser.getSelectedFile().getName());
						projectPath = chooser.getCurrentDirectory().getPath();
						for (RenderObject o : Engine.getAllObjects().values()){
							if (o.toString().isEmpty()){
								o.setName("Object");
							}
							listModel.addElement(o);
						}
						canvas.display();
					} catch (Exception ex){
						ex.printStackTrace();
					}
				
				}
			}
		});
		
		
		
		JMenu options = new JMenu("Settings");
		menuBar.add(options);
		
		
		JMenu areaSettings = new JMenu("Area Settings");
		options.add(areaSettings);
		
		ButtonGroup areaModes = new ButtonGroup();
		JRadioButtonMenuItem none = new JRadioButtonMenuItem("None");
		JRadioButtonMenuItem hide = new JRadioButtonMenuItem("Hide");
		JRadioButtonMenuItem dynamicLoad = new JRadioButtonMenuItem("Dynamic");
		areaSettings.add(none);
		areaSettings.add(hide);
		areaSettings.add(dynamicLoad);

		areaModes.add(none);
		areaModes.add(hide);
		areaModes.add(dynamicLoad);
		
		JMenuItem areaSize = new JMenuItem("Area Size");
		areaSettings.add(areaSize);
		
		
		JMenu collisions = new JMenu("Collision Detection");
		options.add(collisions);
		
		ButtonGroup collisionModes = new ButtonGroup();
		JRadioButtonMenuItem collideAll = new JRadioButtonMenuItem("Collide All");
		JRadioButtonMenuItem useShMap = new JRadioButtonMenuItem("Spatial Hashing");
		collisionModes.add(collideAll);
		collisionModes.add(useShMap);
		collisions.add(collideAll);
		collisions.add(useShMap);
		
		
		JMenuItem collisionArea = new JMenuItem("Collision Area");
		
		collisions.add(collisionArea);
		
		
		JCheckBox modeS = new JCheckBox("ModeSeven Preview");
		modeS.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				renderer.changeMode();
				canvas.display();
			}
		});
		menuBar.add(modeS);
		
		JButton play = new JButton("Play");
		menuBar.add(play);
		play.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new JoglView().start();
			}
		});
		
		mainFrame.setJMenuBar(menuBar);
		
	}
	
	private void prepareInspector() {
		inspector = new InspectorPanel(canvas,renderer);
		mainFrame.add(inspector, BorderLayout.LINE_END);
		
		
	}

	private void prepareRenderList(){
		
		
		listModel = new DefaultListModel<RenderObject>();
		list = new JList<RenderObject>(listModel);
		
		final JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem rename = new JMenuItem("Rename");
		rename.setActionCommand("Rename");
		rename.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog jd = new RenameDialog(mainFrame,list.getSelectedValue());
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
				Engine.getAllObjects().remove(list.getSelectedValue().getId());
				int index = list.getSelectedIndex();
				listModel.remove(index);
				list.clearSelection();
				
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
				if (list.getSelectedIndex() < 0){
					return;
				}
				RenderObject o = list.getSelectedValue();
				inspector.openInspector(o.getId());
				if (selection == null){
					selection = new JoglSprite("selection.png", 1, 1);
					renderer.setSelectionSprite(selection);
				}
				selection.setPosition(o.getX(),o.getY());
				selection.setScale(o.getWidth(), o.getHeight());
				selection.setRotation(o.getRotation());
				canvas.display();
			}
		};
		list.addListSelectionListener(selectionListener);
				
	
	
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(200, 500));

		tabbedPane.add(listScroller,"GameObjects");
		mainFrame.add(tabbedPane,BorderLayout.LINE_START);
		
	}

	private void prepareOrthoList(){
		JList<OrthoRenderObject> orthoList = new JList<OrthoRenderObject>();
		JScrollPane listScroller = new JScrollPane(orthoList);
		tabbedPane.addTab("Ortho", listScroller);
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
		
		Engine.setCollisionZone(new Rectangle(0, 0, 30, 25));
		
	}
}
