package com.ltj.editor;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

@SuppressWarnings("serial")
public class ImageCellRenderer extends JLabel implements ListCellRenderer<ImageEntry> {
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