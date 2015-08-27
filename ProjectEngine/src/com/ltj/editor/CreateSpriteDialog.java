package com.ltj.editor;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.jogamp.opengl.awt.GLCanvas;
import com.ltj.java.engine.JoglSprite;
import com.ltj.shared.engine.Engine;
import com.ltj.shared.engine.RenderObject;



@SuppressWarnings("serial")
public class CreateSpriteDialog extends JDialog {

	private GLCanvas canvas;
	private DefaultListModel<RenderObject> listModel;

	public CreateSpriteDialog(JFrame mainFrame, GLCanvas canvas, DefaultListModel<RenderObject> listModel,
			JList<RenderObject> list, InspectorPanel inspector) {
		super(mainFrame);
		
		this.canvas = canvas;
		this.listModel = listModel;
		setPreferredSize(new Dimension(270,140));
		setLayout(new GridLayout(4,2));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(mainFrame);
        pack();
		
        prepareDialog(list, inspector);
	}
	
	private void prepareDialog(final JList<RenderObject> list, final InspectorPanel inspector) {
		JLabel cols = new JLabel("Columns");
		add(cols);
		final JTextField colText = new JTextField();
		colText.setPreferredSize(new Dimension(70, 10));
		add(colText);
		
		JLabel rows = new JLabel("Rows");
		add(rows);
		final JTextField rowText = new JTextField();
		rowText.setPreferredSize(new Dimension(70, 10));
		add(rowText);
		
		final JTextField pathText = new JTextField("Image");
		pathText.setPreferredSize(new Dimension(70, 10));
		add(pathText);
		
		final JFileChooser chooser = new JFileChooser("assets");
		chooser.setFileFilter(new FileNameExtensionFilter("PNG", "png"));
		
		

		JButton openChooser = new JButton("Browse...");
		add(openChooser);
		openChooser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int state = chooser.showOpenDialog(CreateSpriteDialog.this);
				if (state == JFileChooser.APPROVE_OPTION){
					pathText.setText("assets" +chooser.getSelectedFile().getPath().split("assets")[1]);
				}
			}
		});
		
		
		
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
				
			@Override
			public void actionPerformed(ActionEvent e) {
				int cols = Integer.parseInt(colText.getText());
				int rows = Integer.parseInt(rowText.getText());
				JoglSprite sp = new JoglSprite(pathText.getText(), cols,rows);
				if (cols > 1 || rows > 1){
					sp.setName("SpriteSheet");
					sp.setTexture(0, 0);
				} else {
					sp.setName("Sprite");
				}
				
				Engine.addRenderable(sp);
				listModel.addElement(sp);
			
				canvas.display();

				list.setSelectedIndex(listModel.getSize()-1);

				dispose();
			}
		});
		add(submit);
		
	}
}
