package com.ltj.editor;

import java.awt.Cursor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;

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
	
	@Override
	public void dragGestureRecognized(DragGestureEvent event) {
		Cursor cursor = null;
		
		  if (event.getDragAction() == DnDConstants.ACTION_COPY) {
		      cursor = DragSource.DefaultCopyDrop;
		    }
	    event.startDrag(cursor, list.getSelectedValue().getPath());
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
