package com.pwr.NewInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;


public class ParagraphInputDialog extends JDialog{
	
	private JButton btnOk;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	
	public ParagraphInputDialog(String paragraph)
	{
		super();
		setTitle("Podaj opis");
		setSize(800,600);
		setLayout(null);
		
		textArea = new JTextArea();
		textArea.setText(paragraph);
		textArea.setBounds(20,20,740,500);
		
		scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(20,20,740,500);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		add(scrollPane);
		
		btnOk = new JButton("Ok");
		btnOk.setBounds(370,520,60,60);
		
		btnOk.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		}
		);
		add(btnOk);
		
		setVisible(true);
	}
	
	public String getParagraph()
	{
		return textArea.getText();
	}
}
