package com.pwr.DetailsView;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.pwr.Quest.Campaign;

public class GameSettingsDialog extends JDialog{
	private Campaign campaignRef;
	
	private JLabel background1Label;
	private JLabel background2Label;
	private JLabel background3Label;
	
	private JLabel logoLabel;
	private JLabel buttonLabel;
	
	private JTextField background1Field;
	private JTextField background2Field;
	private JTextField background3Field;
	
	private JTextField logoField;
	private JTextField buttonField;
	
	private JButton addBackground1btn;
	private JButton addBackground2btn;
	private JButton addBackground3btn;
	private JButton addLogoBtn;
	private JButton addButtonBtn;
	
	private JButton delBackground1btn;
	private JButton delBackground2btn;
	private JButton delBackground3btn;
	private JButton delLogoBtn;
	private JButton delButtonBtn;
	
	private JButton okBtn;
	
	public GameSettingsDialog(Campaign campaign)
	{
		super();
		this.setSize(new Dimension(600,400));
		this.campaignRef=campaign;
		this.setVisible(true);
		this.setResizable(false);
		this.setLayout(new FlowLayout());
		
		background1Label=new JLabel("Tło 1");
		background1Label.setPreferredSize(new Dimension(80,30));
		this.add(background1Label);
		
		background1Field = new JTextField();
		background1Field.setPreferredSize(new Dimension(300,30));
		background1Field.setEditable(false);
		this.add(background1Field);
		
		addBackground1btn = new JButton("Dodaj");
		addBackground1btn.setPreferredSize(new Dimension(80,30));
		this.add(addBackground1btn);
		
		delBackground1btn = new JButton("Usuń");
		delBackground1btn.setPreferredSize(new Dimension(80,30));
		this.add(delBackground1btn);
		
		background2Label=new JLabel("Tło 2");
		background2Label.setPreferredSize(new Dimension(80,30));
		this.add(background2Label);
		
		background2Field = new JTextField();
		background2Field.setPreferredSize(new Dimension(300,30));
		background2Field.setEditable(false);
		this.add(background2Field);
		
		addBackground2btn = new JButton("Dodaj");
		addBackground2btn.setPreferredSize(new Dimension(80,30));
		this.add(addBackground2btn);
		
		delBackground2btn = new JButton("Usuń");
		delBackground2btn.setPreferredSize(new Dimension(80,30));
		this.add(delBackground2btn);
		
		background3Label=new JLabel("Tło 3");
		background3Label.setPreferredSize(new Dimension(80,30));
		this.add(background3Label);
		
		background3Field = new JTextField();
		background3Field.setPreferredSize(new Dimension(300,30));
		background3Field.setEditable(false);
		this.add(background3Field);
		
		addBackground3btn = new JButton("Dodaj");
		addBackground3btn.setPreferredSize(new Dimension(80,30));
		this.add(addBackground3btn);
		
		delBackground3btn = new JButton("Usuń");
		delBackground3btn.setPreferredSize(new Dimension(80,30));
		this.add(delBackground3btn);

		
		
		
		logoLabel=new JLabel("Logo");
		logoLabel.setPreferredSize(new Dimension(80,30));
		this.add(logoLabel);
		
		logoField = new JTextField();
		logoField.setPreferredSize(new Dimension(300,30));
		logoField.setEditable(false);
		this.add(logoField);
		
		addLogoBtn = new JButton("Dodaj");
		addLogoBtn.setPreferredSize(new Dimension(80,30));
		this.add(addLogoBtn);
		
		delLogoBtn = new JButton("Usuń");
		delLogoBtn.setPreferredSize(new Dimension(80,30));
		this.add(delLogoBtn);
		
		
		
		buttonLabel=new JLabel("Przycisk");
		buttonLabel.setPreferredSize(new Dimension(80,30));
		this.add(buttonLabel);
		
		buttonField = new JTextField();
		buttonField.setPreferredSize(new Dimension(300,30));
		buttonField.setEditable(false);
		this.add(buttonField);
		
		addButtonBtn = new JButton("Dodaj");
		addButtonBtn.setPreferredSize(new Dimension(80,30));
		this.add(addButtonBtn);
		
		delButtonBtn = new JButton("Usuń");
		delButtonBtn.setPreferredSize(new Dimension(80,30));
		this.add(delButtonBtn);
		
		okBtn = new JButton("Ok");
		okBtn.setPreferredSize(new Dimension(60,30));
		this.add(okBtn);
		
		addActionListeners();
		setData();
}
	
	
	private void getPicturesPath(JTextField field) {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle("Chose JPEG file");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);

		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			String str = chooser.getSelectedFile().getPath();
			field.setText(str);
		} else {
			System.out.println("No Selection ");
		}
	}
	
	private void addActionListeners()
	{
		addBackground1btn.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				getPicturesPath(background1Field);
			}
			
		});
		addBackground2btn.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				getPicturesPath(background2Field);
			}
			
		});
		addBackground3btn.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				getPicturesPath(background3Field);
			}
			
		});
		addLogoBtn.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				getPicturesPath(logoField);
			}
			
		});
		addButtonBtn.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				getPicturesPath(buttonField);
			}		
		});
		
		
		delBackground1btn.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				background1Field.setText("");
			}
			
		});
		
		delBackground2btn.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				background2Field.setText("");
			}
			
		});
		
		delBackground3btn.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				background3Field.setText("");
			}
			
		});
		
		delLogoBtn.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				logoField.setText("");
			}
			
		});
		delButtonBtn.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				buttonField.setText("");
			}
		});
		
		okBtn.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				setSettings();
				dispose();
			}
			
		});
	}
	
	private void setSettings()
	{
		campaignRef.setSettings(background1Field.getText(), background2Field.getText(), background3Field.getText(), logoField.getText(),
				buttonField.getText());
	}
	
	private void setData()
	{
		ArrayList<String> list = campaignRef.getSettings();
		background1Field.setText(list.get(0));
		background2Field.setText(list.get(1));
		background3Field.setText(list.get(2));
		logoField.setText(list.get(3));
		buttonField.setText(list.get(4));
	}

}
