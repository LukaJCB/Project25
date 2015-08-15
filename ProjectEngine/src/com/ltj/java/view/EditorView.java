package com.ltj.java.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
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
	
	public EditorView(){
		prepareGUI();
		prepareList();
		//prepareEventDemo();
		prepareMenuBar();
		prepareGLView();
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
		mainFrame.setSize(700,400);
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
				CreateSpriteDialog dialog = new CreateSpriteDialog(mainFrame,canvas,listModel);
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
			}
		});
		object.add(addEmpty);
		
		menuBar.add(object);
		
		
		mainFrame.setJMenuBar(menuBar);
		
	}
	
	private void prepareGLView(){
		
		GLProfile glp = GLProfile.getDefault();
		GLCapabilities caps = new GLCapabilities(glp);
		canvas = new GLCanvas(caps);

		JoglRenderer r = new JoglRenderer();
		canvas.addGLEventListener(r);
		canvas.reshape(canvas.getX(), canvas.getY(), canvas.getWidth(), canvas.getHeight());
		mainFrame.add(canvas,BorderLayout.CENTER);
		
	}
	

	private void prepareList(){
		
		listModel = new DefaultListModel<RenderObject>();
		listModel.addElement(new EmptyObject());
		final JList<RenderObject> list = new JList<RenderObject>(listModel);
		
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

				if (SwingUtilities.isRightMouseButton(me)) {
					list.setSelectedIndex(list.locationToIndex(me.getPoint()));
					popupMenu.show(list, me.getX(), me.getY());
				}
			}
		});
    

		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(200, 500));
		mainFrame.add(listScroller,BorderLayout.LINE_START);
		
	}
	
	@SuppressWarnings("unused")
	private void prepareEventDemo(){
	     
		JLabel headerLabel = new JLabel("",JLabel.CENTER );
		JLabel statusLabel = new JLabel("",JLabel.CENTER);        

		statusLabel.setSize(350,100);
		
		
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());

		mainFrame.add(headerLabel);
		mainFrame.add(controlPanel);
		mainFrame.add(statusLabel);
		
		headerLabel.setText("Control in action: Button"); 

		JButton okButton = new JButton("OK");
		JButton submitButton = new JButton("Submit");
		JButton cancelButton = new JButton("Cancel");

		okButton.setActionCommand("OK");
		submitButton.setActionCommand("Submit");
		cancelButton.setActionCommand("Cancel");

		okButton.addActionListener(new ButtonClickListener()); 
		submitButton.addActionListener(new ButtonClickListener()); 
		cancelButton.addActionListener(new ButtonClickListener()); 

		controlPanel.add(okButton);
		controlPanel.add(submitButton);
		controlPanel.add(cancelButton);  
	}

	private class ButtonClickListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();  
			System.out.println(command);
		}		
	}

	
}
