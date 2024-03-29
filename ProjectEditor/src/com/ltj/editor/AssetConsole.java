package com.ltj.editor;

import java.awt.Cursor;
import java.awt.datatransfer.StringSelection;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragSource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


import com.ltj.shared.engine.json.JSONObject;

public class AssetConsole extends AbstractConsolePanel {


	public AssetConsole(String projectPath) {
		super(projectPath);
		
		new AssetsDropTargetListener(this);
		
		DragSource ds = new DragSource();
		ds.createDefaultDragGestureRecognizer(list, DnDConstants.ACTION_COPY, this);
	}
	
	@Override
	public void dragGestureRecognized(DragGestureEvent event) {
		Cursor cursor = null;
		
		if (event.getDragAction() == DnDConstants.ACTION_COPY) {
			cursor = DragSource.DefaultCopyDrop;
		}
		//TODO
		event.startDrag(cursor, new StringSelection(list.getSelectedValue().toString() + ".dmo"));
	}

	@Override
	public void updateList() {
		model.clear();
		File imageFolder = new File(getProjectPath() + File.separatorChar + "assets");
		File[] images = imageFolder.listFiles(pathname -> {
			if (pathname.getName().toLowerCase().endsWith("dmo")){
				return true;
			}
			return false;
		});
		for (File f : images){
			
			try {
				String json = new String(Files.readAllBytes(f.toPath()));
				addEntryFromJson(json);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		pane.repaint();
		
	}
	
	public ImageEntry addEntryFromJson(String json){
		JSONObject jsonObject = new JSONObject(json);
		
		String name = jsonObject.getString("name");
		String imagePath = getProjectPath() + File.separatorChar + jsonObject.getString("path");
		ImageEntry entry = new ImageEntry(name, imagePath);
		model.addElement(entry);
		return entry;
	}

	
}
