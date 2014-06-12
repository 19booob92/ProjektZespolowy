package com.pwr.DetailsView;

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
		setSize(794,458);
		getContentPane().setLayout(null);
		
		textArea = new JTextArea();
		textArea.setText(paragraph);
		textArea.setBounds(20,20,740,500);
		
		scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(20,20,740,346);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		getContentPane().add(scrollPane);
		
		btnOk = new JButton("Ok");
		btnOk.setBounds(596,377,164,30);
		
		btnOk.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		}
		);
		getContentPane().add(btnOk);
		
		setVisible(true);
	}
	
	public String getParagraph()
	{
		return textArea.getText();
	}
}
