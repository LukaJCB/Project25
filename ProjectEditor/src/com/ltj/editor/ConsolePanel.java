package com.ltj.editor;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.datatransfer.StringSelection;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

import com.jogamp.opengl.awt.GLCanvas;

@SuppressWarnings("serial")
public class ConsolePanel extends JPanel implements DragGestureListener{

	private DefaultListModel<ImageEntry> model;
	private JList<ImageEntry> list;
	private String projectPath;

	public ConsolePanel(String projectPath, GLCanvas canvas, EditorView editor) {
		this.projectPath = projectPath;
		model = new DefaultListModel<>();
		list = new JList<ImageEntry>(model);
		list.setCellRenderer(new ImageCellRenderer());
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.setVisibleRowCount(-1);
		list.setPreferredSize(new Dimension(1280,170));
		list.setMaximumSize(new Dimension(3820,400));
		JScrollPane pane = new JScrollPane(list);
		pane.createVerticalScrollBar();
		pane.createHorizontalScrollBar();
		pane.setWheelScrollingEnabled(true);
		add(pane);


		new CanvasDropTargetListener(this, canvas,editor);

		DragSource ds = new DragSource();
		ds.createDefaultDragGestureRecognizer(list, DnDConstants.ACTION_COPY, this);

	}

	public void updateList(){
		model.clear();
		File imageFolder = new File(projectPath + File.separatorChar + "images");
		File[] images = imageFolder.listFiles(pathname -> {
			if (pathname.getName().toLowerCase().endsWith("png")){
				return true;
			}
			return false;
		});
		for (File f : images){
			model.addElement(new ImageEntry(f.getName(), f.getPath()));
		}
		repaint();
	}
	
	
	public void setPath(String projectPath) {
		this.projectPath = projectPath;
		
	}

	@Override
	public void dragGestureRecognized(DragGestureEvent event) {
		Cursor cursor = null;
		
		  if (event.getDragAction() == DnDConstants.ACTION_COPY) {
		      cursor = DragSource.DefaultCopyDrop;
		    }
	    event.startDrag(cursor, list.getSelectedValue().getPath());
	}

	private class ImageEntry  {
			private String title, path;
			private ImageIcon icon;
			
			//protected DataFlavor[] imageFlavor = {new DataFlavor(ImageEntry.class, "ImageEntry")};
			
			public ImageEntry(String title, String path){
				this.title = title;
				this.path = path;
				BufferedImage image;
				try {
					image = ImageIO.read(new File(path));
					BufferedImage ret = new BufferedImage(64,64,BufferedImage.TYPE_INT_ARGB);
					ret.getGraphics().drawImage(image,0,0,64,64,null);
					this.icon = new ImageIcon(ret);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
	
			public String toString() {
				return title;
			}
	
	
			
			public StringSelection getPath() {
				return new StringSelection(path);
			}
			
			public ImageIcon getThumbnail(){
				return icon;
			}
	
			
		}


	private class ImageCellRenderer extends JLabel implements ListCellRenderer<ImageEntry> {
	  private final Color HIGHLIGHT_COLOR = new Color(0, 0, 128);
	
	  public ImageCellRenderer() {
	    setOpaque(true);
	    setIconTextGap(12);
	  }
	
	
	  public Component getListCellRendererComponent(JList<? extends ImageEntry> list,
			  ImageEntry value,int index, boolean isSelected, boolean cellHasFocus) {
	    ImageEntry entry = (ImageEntry) value;
	    setText(entry.toString());
	    setIcon(entry.getThumbnail());
	    if (isSelected) {
	      setBackground(HIGHLIGHT_COLOR);
	      setForeground(Color.white);
	    } else {
	      setBackground(Color.white);
	      setForeground(Color.black);
	    }
	    return this;
	  }
	}
	
	
	
	


}
