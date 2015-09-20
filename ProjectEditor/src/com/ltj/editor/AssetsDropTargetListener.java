package com.ltj.editor;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.io.IOException;


import com.ltj.shared.utils.BasicIO;

public class AssetsDropTargetListener extends DropTargetAdapter {

	private AssetConsole console;

	public AssetsDropTargetListener(AssetConsole console) {
		this.console = console;
		new DropTarget(console.list, DnDConstants.ACTION_COPY, this, true);
	}
	
	@Override
	public void drop(DropTargetDropEvent event) {
		
			String jsonString;
			try {
				jsonString = (String) event.getTransferable().getTransferData(DataFlavor.stringFlavor);
				if (event.isDataFlavorSupported(DataFlavor.stringFlavor)) {
					event.acceptDrop(DnDConstants.ACTION_COPY);
					
					ImageEntry entry = console.addEntryFromJson(jsonString);
					copyDMOtoProjectFolder(jsonString, entry.toString());
					
					
					event.dropComplete(true);
					return;
				}
				event.rejectDrop();
			} catch (UnsupportedFlavorException | IOException e) {
				e.printStackTrace();
			}
			
		
	}

	private void copyDMOtoProjectFolder(String jsonString, String name) {
		String path = console.getProjectPath() + File.separatorChar + "assets" + File.separator +  name + ".dmo";
		File dst = new File(path);

		if (!dst.exists()){
			new Thread(() ->BasicIO.writeToPath(jsonString, path)).start();
		}
		
		
	}

}
