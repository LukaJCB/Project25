package com.ltj.editor;

import java.awt.dnd.DragGestureListener;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;

public abstract class AbstractConsolePanel implements DragGestureListener {

	protected DefaultListModel<ImageEntry> model;
	protected JList<ImageEntry> list;
	private String projectPath;
	protected JScrollPane pane;
	
	public AbstractConsolePanel(String projectPath) {
		this.projectPath = projectPath;
		model = new DefaultListModel<>();
		list = new JList<ImageEntry>(model);
		list.setCellRenderer(new ImageCellRenderer());
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.setVisibleRowCount(-1);
		pane = new JScrollPane(list);
	}
	
	

	public JScrollPane getPane() {
		return pane;
	}



	public void setPath(String projectPath) {
		this.projectPath = projectPath;
		
	}

	public String getProjectPath() {
		return projectPath;
	}

	public abstract void updateList();
	
}
