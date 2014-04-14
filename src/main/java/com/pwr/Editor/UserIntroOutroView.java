package com.pwr.Editor;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;

public class UserIntroOutroView extends JFrame{
	
	private final int Width = 300;
	private final int Height = 500;
	private JPanel panel;

	private JTextField introModuleName;
	private JTextField outroModuleName;
	
	private JLabel lblIntroModuleText;
	private JLabel lblOutroModuleText;
	private JLabel lblIntroPictures;
	private JLabel lblOutroPictures;
	
	private JList introPicsList;
	private JList outroPicsList;
	
	public UserIntroOutroView()
	{
		super("Intro and Outro edition view");
		Toolkit toolkt = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkt.getScreenSize();
		
		this.setSize(374,500);
		this.setLocation(screenSize.width/4, screenSize.height/4);
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 11, 338, 439);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		lblIntroModuleText = new JLabel("Intro module text");
		lblIntroModuleText.setBounds(10, 11, 103, 14);
		
		introModuleName = new JTextField();
		introModuleName.setBounds(126, 8, 202, 20);
		introModuleName.setColumns(10);
		
		lblIntroPictures = new JLabel("Intro pictures");
		lblIntroPictures.setBounds(10, 48, 86, 14);
		
		introPicsList = new JList();
		introPicsList.setBounds(126, 48, 202, 56);
		
		lblOutroModuleText = new JLabel("Outro module text");
		lblOutroModuleText.setBounds(10, 132, 103, 14);
		
		outroModuleName = new JTextField();
		outroModuleName.setBounds(126, 129, 202, 20);
		outroModuleName.setColumns(10);
		
		lblOutroPictures = new JLabel("Outro pictures");
		lblOutroPictures.setBounds(10, 172, 103, 14);
		
		outroPicsList = new JList();
		outroPicsList.setBounds(126, 172, 202, 56);

		panel.add(outroPicsList);
		panel.add(lblOutroPictures);
		panel.add(outroModuleName);
		panel.add(lblOutroModuleText);
		panel.add(introPicsList);
		panel.add(lblIntroModuleText);
		panel.add(introModuleName);
		panel.add(lblIntroPictures);
		
		setVisible(true);
	}
}
