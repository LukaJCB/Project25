package com.ltj.editor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.Animator;
import com.ltj.java.engine.JoglRenderer;
import com.ltj.java.engine.JoglSprite;
import com.ltj.shared.engine.AreaMode;
import com.ltj.shared.engine.Camera;
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
	private SettingsMenu options;
	private DefaultListModel<OrthoRenderObject> orthoListModel;
	private Animator animator;
	private CanvasMouseListener cmListener;
	private JScrollPane listScroller;
	private String currentScene;
	private AreaList areaList;
	private boolean areaListActive;
	private ConsolePanel console;

	public EditorView(){

		prepareGUI();

		prepareRenderList();

		prepareMenuBar();

		prepareGLView();
		prepareInspector();

		prepareAreaList();
		prepareOrthoList();
		prepareConsole();
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
		mainFrame.setSize(1280,720);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLayout(new BorderLayout(5, 5));

		tabbedPane = new JTabbedPane();
		
	}

	private void prepareMenuBar(){
		JMenuBar menuBar = new JMenuBar();

		addFileMenu(menuBar);


		addObjectMenu(menuBar);


		addSettingsMenu(menuBar);


		JCheckBox modeS = new JCheckBox("ModeSeven Preview");
		modeS.addActionListener(ae ->{
			renderer.changeMode();
			canvas.display();
		});
		menuBar.add(modeS);

		final JButton play = new JButton("Play");
		menuBar.add(play);
		play.addActionListener(ae -> {
			if (Engine.isStarted()){
				//animator.stop();
				loadScene();
				play.setText("Play");
				
			} else {
				saveScene();
				Engine.start();
				animator.start();
				play.setText("Stop");
				canvas.requestFocusInWindow();
			}

		});

		mainFrame.setJMenuBar(menuBar);

	}

	private void addSettingsMenu(JMenuBar menuBar) {
		options = new SettingsMenu();
		menuBar.add(options);
		options.setAreaMode(AreaMode.NONE);
		//options.setVisible(false);
	}

	private void addObjectMenu(JMenuBar menuBar) {
		JMenu object = new JMenu("GameObject");

		JMenuItem addSingle = new JMenuItem("Add SingleSprite");
		object.add(addSingle);
		addSingle.addActionListener(ae -> {
			final JFileChooser chooser = new JFileChooser();
			chooser.setFileFilter(new FileNameExtensionFilter("PNG", "png"));

			int result = chooser.showOpenDialog(mainFrame);
			if (result == JFileChooser.APPROVE_OPTION){
				
				String path = copyToProjectFolder(chooser);
				JoglSprite o = new JoglSprite(path, 1, 1);
				addObject(o,"SingleSprite");

				
			}
		});

		JMenuItem addObject = new JMenuItem("Add SpriteSheet");
		object.add(addObject);
		addObject.addActionListener(ae -> {
			CreateSpriteDialog dialog = new CreateSpriteDialog(this);
			dialog.setVisible(true);
		});

		JMenuItem addEmpty = new JMenuItem("Add EmptyObject");
		addEmpty.addActionListener(ae -> {
			EmptyObject eo = new EmptyObject();
			addObject(eo,"Empty Object");
		});
		object.add(addEmpty);


		JMenuItem addOrtho = new JMenuItem("Add OrthoObject");
		addOrtho.addActionListener(ae -> {
			final JFileChooser chooser = new JFileChooser();
			chooser.setFileFilter(new FileNameExtensionFilter("PNG", "png"));

			int result = chooser.showOpenDialog(mainFrame);
			if (result == JFileChooser.APPROVE_OPTION){
				String path = copyToProjectFolder(chooser);
				OrthoRenderObject oro = new OrthoRenderObject(path, Engine.getPlatform());
				Engine.addOrthoRenderObject(oro);
				orthoListModel.addElement(oro);
				canvas.reshape(canvas.getX(), canvas.getY(), canvas.getWidth(), canvas.getHeight());
				canvas.display();
			}

		});

		object.add(addOrtho);

		menuBar.add(object);
	}

	public String copyToProjectFolder(final JFileChooser chooser) {
		File dst = new File(projectPath + File.separator + "images" + File.separator +  chooser.getSelectedFile().getName());
		if (!dst.exists()){
			try {
				BasicIO.copy(chooser.getSelectedFile(), dst);
				console.updateList();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		return dst.getPath();
	}

	private void addFileMenu(JMenuBar menuBar) {
		JMenu file = new JMenu("File");


		JMenuItem newFile = new JMenuItem("New...");
		file.add(newFile);

		JMenuItem fileSave = new JMenuItem("Save");
		file.add(fileSave);

		JMenuItem fileLoad = new JMenuItem("Load");
		file.add(fileLoad);


		menuBar.add(file);



		newFile.addActionListener(ae -> {
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

				File imageFolder = new File(projectPath + File.separatorChar + "images");
				imageFolder.mkdir();

				File assetFolder = new File(projectPath + File.separatorChar + "assets");
				assetFolder.mkdir();

				File scriptFolder = new File(projectPath + File.separatorChar + "scripts");
				scriptFolder.mkdir();

				mainFrame.setTitle(mainFrame.getTitle() +  " - "  + name);

				console.setPath(projectPath);
			}
		});


		fileSave.addActionListener(ae -> saveScene());
		fileSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));

		fileLoad.addActionListener(ae -> {
			JFileChooser chooser = new JFileChooser();
			chooser.setFileFilter(new FileNameExtensionFilter("DME", "dme"));
			int result = chooser.showOpenDialog(mainFrame);


			if (result == JFileChooser.APPROVE_OPTION){
				projectPath = chooser.getCurrentDirectory().getPath();
				currentScene = chooser.getSelectedFile().getName().split(".dme")[0];
				loadScene();
			}

		});
	}

	private void prepareInspector() {

		inspector = new InspectorPanel(canvas,renderer);
		JScrollPane jsp = new JScrollPane(inspector);
		jsp.setPreferredSize(new Dimension(250, 500));
		mainFrame.add(jsp, BorderLayout.LINE_END);


	}

	private void prepareRenderList(){


		listModel = new DefaultListModel<RenderObject>();
		list = new JList<RenderObject>(listModel);

		final JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem rename = new JMenuItem("Rename");
		rename.setActionCommand("Rename");
		rename.addActionListener(ae -> {
			renameObject();
		});
		popupMenu.add(rename);
		popupMenu.add(new JPopupMenu.Separator());
		
		
		JMenuItem delete = new JMenuItem("Delete");
		delete.setActionCommand("Delete");
		delete.addActionListener(ae -> {
			removeObject();
		});

		delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE,0));
		
		popupMenu.add(delete);

		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {

				list.setSelectedIndex(list.locationToIndex(me.getPoint()));

				if (SwingUtilities.isRightMouseButton(me)) {
					popupMenu.show(list, me.getX(), me.getY());
				} 

			}
		});
		
		list.addKeyListener(new KeyListListener());

		selectionListener = new ObjectListListener(list);
		list.addListSelectionListener(selectionListener);



		listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(200, 500));

		tabbedPane.addTab("GameObjects",listScroller);
		mainFrame.add(tabbedPane,BorderLayout.LINE_START);

	}

	public void renameObject() {
		JList<RenderObject> jlist;
		if (areaListActive){
			jlist = areaList.getList();
		} else {
			jlist = list;
		}
		String newName = JOptionPane.showInputDialog(mainFrame, "Rename", jlist.getSelectedValue());
		if (newName != null){
			jlist.getSelectedValue().setName(newName);
			jlist.repaint();
		}
	}

	public void addObject(RenderObject o,String name) {
		addObject(o, name, Camera.getLookAt()[0], Camera.getLookAt()[1]);
	}
	
	public void addObject(RenderObject o,String name,float x, float y){
		o.setName(name);
		o.setPosition(x,y);
		Engine.addRenderable(o);
		if (areaListActive){
			areaList.addElement(o);
			areaList.setSelectedIndex(areaList.getLength()-1);
		} else {
			listModel.addElement(o);
			list.setSelectedIndex(listModel.getSize()-1);
		}
		console.updateList();
		canvas.display();
	}

	public void removeObject() {
		JList<RenderObject> jlist;
		DefaultListModel<RenderObject> jlistModel;
		if (areaListActive){
			jlist = areaList.getList();
			jlistModel = areaList.getModel();
		} else {
			jlist = list;
			jlistModel = listModel;
		}
		if (jlist.getSelectedValue()!= null){
			Engine.getAllObjects().remove(jlist.getSelectedValue().getId());
			int index = jlist.getSelectedIndex();
			jlistModel.remove(index);
			jlist.clearSelection();
			canvas.display();
		}
	}

	private void prepareAreaList(){
		areaList = new AreaList(canvas,this);

		tabbedPane.addTab("Areas", areaList);
		tabbedPane.addChangeListener(ce -> {
			if (tabbedPane.getSelectedComponent() == areaList){
				cmListener.setList(areaList.getList());
				areaListActive = true;
			} else if (tabbedPane.getSelectedComponent() == listScroller){
				cmListener.setList(list);
				areaListActive = false;
			}

		});
		areaList.addKeyListener(new KeyListListener());
		areaList.getList().addListSelectionListener(new ObjectListListener(areaList.getList()));
	}

	private void prepareOrthoList(){
		orthoListModel = new DefaultListModel<OrthoRenderObject>();
		JList<OrthoRenderObject> orthoList = new JList<OrthoRenderObject>(orthoListModel);
		JScrollPane listScroller = new JScrollPane(orthoList);
		tabbedPane.addTab("Ortho", listScroller);
	}

	private void prepareConsole(){
		console = new ConsolePanel(projectPath,canvas,this);
		mainFrame.add(console,BorderLayout.PAGE_END);
		console.setPreferredSize(new Dimension(700,180));
	}

	private void prepareGLView(){

		GLProfile glp = GLProfile.getDefault();
		GLCapabilities caps = new GLCapabilities(glp);
		canvas = new GLCanvas(caps);

		renderer = new JoglRenderer();
		canvas.addGLEventListener(renderer);
		canvas.reshape(canvas.getX(), canvas.getY(), canvas.getWidth(), canvas.getHeight());
		canvas.addKeyListener(renderer);
		canvas.addKeyListener(new KeyListListener());
		
		
		mainFrame.add(canvas,BorderLayout.CENTER);
		animator = new Animator(canvas);
		animator.setRunAsFastAsPossible(true);


		cmListener = new CanvasMouseListener(canvas,list,selectionListener);
		cmListener.registerListener();
		cmListener.setAreaChangedListener(newArea ->{

			cmListener.setList(areaList.getList());
			areaList.displayAreaList(newArea);
			tabbedPane.setSelectedIndex(1);

		});

		Engine.setCollisionZone(new Rectangle(0, 0, 30, 25));

	}

	private void loadScene() {
		try {
			Engine.flush();
			listModel.clear();
			BasicIO.loadFromDME(projectPath,currentScene + ".dme");
			for (RenderObject o : Engine.getAllObjects().values()){
				o.setInactive(false);
				if (o.toString().isEmpty()){
					o.setName("Object");
				}
				if (!o.isPartOfArea()){
					listModel.addElement(o);
				}

			}
			orthoListModel.clear();
			for (OrthoRenderObject o : Engine.getAllOrthoRenderObjects()){
				orthoListModel.addElement(o);
			}
			options.setAreaMode(Engine.getAreaMode());
			canvas.reshape(canvas.getX(), canvas.getY(), canvas.getWidth(), canvas.getHeight());
			canvas.display();
			console.setPath(projectPath);
			console.updateList();
		} catch (Exception ex){
			ex.printStackTrace();
		}
	}

	private void saveScene() {
		BasicIO.parseToDME(projectPath,currentScene);
	}

	private class ObjectListListener implements ListSelectionListener {
	
		private JList<RenderObject> list;
	
		public ObjectListListener(JList<RenderObject> list){
			this.list = list;
		}
	
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (list.getSelectedIndex() < 0){
				return;
			}
			RenderObject o = list.getSelectedValue();
			inspector.openInspector(o.getId());
			if (selection == null){
				selection = new JoglSprite("selection.png", 1, 1);
				JoglRenderer.setSelectionSprite(selection);
			}
			selection.setPosition(o.getX(),o.getY());
			selection.setScale(o.getWidth(), o.getHeight());
			selection.setRotation(o.getRotation());
			canvas.display();
		}
	}
	
	private class KeyListListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_DELETE){
				removeObject();
			} else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_R){
				renameObject();
			}
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
		
	}

	public JFrame getMainFrame() {
		return mainFrame;
	}




}
