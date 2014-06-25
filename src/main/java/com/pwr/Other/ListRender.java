package com.pwr.Other;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class ListRender extends JLabel implements ListCellRenderer {

	public ListRender() {
		setOpaque(true);
	}

	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		setText(value.toString());
		if (index % 2 == 0) {
			setBackground(new Color(155,215,231));
		} else
			setBackground(new Color(162,232,217));
		if (isSelected) {
			setBackground(new Color(166,166,166));
		}
		return this;
	}
}