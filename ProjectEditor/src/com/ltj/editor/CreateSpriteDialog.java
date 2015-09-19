package com.ltj.editor;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.ltj.java.engine.JoglSprite;



@SuppressWarnings("serial")
public class CreateSpriteDialog extends JDialog {


	public CreateSpriteDialog(EditorView editorView) {
		super(editorView.getMainFrame());
		
		setPreferredSize(new Dimension(270,140));
		setLayout(new GridLayout(4,2));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(editorView.getMainFrame());
        pack();
		
        prepareDialog(editorView);
	}
	
	private void prepareDialog(EditorView editorView) {
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
		openChooser.addActionListener(ae -> {
			int state = chooser.showOpenDialog(CreateSpriteDialog.this);
			if (state == JFileChooser.APPROVE_OPTION){
				pathText.setText(chooser.getSelectedFile().getName());
			}
		});
		
		
		
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(ae -> {
			int c = Integer.parseInt(colText.getText());
			int r = Integer.parseInt(rowText.getText());
			String path = editorView.copyToProjectFolder(chooser);
			JoglSprite sp = new JoglSprite(path, c,r);
			if (c > 1 || r > 1){
				sp.setName("SpriteSheet");
				sp.setTexture(0, 0);
			} else {
				sp.setName("Sprite");
			}

			editorView.addObject(sp, sp.toString());


			dispose();

		});
		add(submit);
		
	}
}
