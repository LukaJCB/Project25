package com.ltj.editor;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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

@SuppressWarnings("serial")
public class ConsolePanel extends JPanel {

	private DefaultListModel<ImageEntry> model;
	private JList<ImageEntry> list;
	private String projectPath;

	public ConsolePanel(String projectPath) {
		this.projectPath = projectPath;
		model = new DefaultListModel<>();
		list = new JList<ImageEntry>(model);
		list.setCellRenderer(new ImageCellRenderer());
		list.setLayoutOrientation(JList.VERTICAL_WRAP);
		list.setVisibleRowCount(-1);
		list.setPreferredSize(new Dimension(1000,170));
		list.setDragEnabled(true);
		JScrollPane pane = new JScrollPane(list);
		add(pane);
	}
	
	public void updateList(){
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
	
	
	private class ImageEntry {
		private String title, path;
		private ImageIcon icon;
		
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


		@SuppressWarnings("unused")
		public String getPath() {
			return path;
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

	public void setPath(String projectPath) {
		this.projectPath = projectPath;
		
	}

}
