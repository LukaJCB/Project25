package com.ltj.editor;

import java.awt.Cursor;
import java.awt.datatransfer.StringSelection;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;

import javax.swing.JList;

import com.ltj.shared.engine.RenderObject;

public class RenderableDragGestureListener implements DragGestureListener {

	private JList<RenderObject> list;

	public RenderableDragGestureListener(JList<RenderObject> list) {
		this.list = list;
		DragSource ds = new DragSource();
		ds.createDefaultDragGestureRecognizer(list, DnDConstants.ACTION_COPY, this);

	}

	@Override
	public void dragGestureRecognized(DragGestureEvent event) {
		Cursor cursor = null;
		
		  if (event.getDragAction() == DnDConstants.ACTION_COPY) {
		      cursor = DragSource.DefaultCopyDrop;
		    }
	    event.startDrag(cursor, new StringSelection(list.getSelectedValue().toJSON()));
	}

}
