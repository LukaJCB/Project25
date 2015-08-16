package com.ltj.java.view;


import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;

import com.ltj.shared.engine.RenderObject;

@SuppressWarnings("serial")
public class RenameDialog extends JDialog {

	
	public RenameDialog(JFrame mainFrame, RenderObject renderObject) {
		super(mainFrame);
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		prepareTextField(renderObject);
        pack();
        setLocationRelativeTo(mainFrame);
	}

	private void prepareTextField(final RenderObject r) {
		final JTextField text = new JTextField(r.toString());
		this.add(text);
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				r.setName(text.getText());
				dispose();
			}
		});
		add(submit);
		
	}

}
