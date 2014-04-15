package com.pwr.NewInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;

public class ProjectOptionsView extends JPanel {

	private static final int panelWidth = 1000;
	private static final int panelHeight = 800;
	private JTextField tfGameTitle;
	private JTextField tfStartDate;

	private JLabel lblGameTitle;
	private JLabel lblIntroModulePics;
	private JLabel lblOutroModulePics;
	private JLabel lblStartDate;

	private JList introPics;
	private JList outroPics;

	protected DefaultListModel<String> introPicsListModel;
	protected DefaultListModel<String> outroPicsListModel;
	
	private JButton btnAddIntro;
	private JButton btnDelIntro;
	private JButton btnAddOutro;
	private JButton btnDelOutro;
	private JList listOfQuizes;

	public ProjectOptionsView() {
		this.setSize(panelWidth, panelHeight);
		setLayout(null);

		introPicsListModel = new DefaultListModel<String>();
		outroPicsListModel = new DefaultListModel<String>();
		lblGameTitle = new JLabel("Tytuł gry");
		lblGameTitle.setBounds(10, 28, 81, 14);
		add(lblGameTitle);

		tfGameTitle = new JTextField();
		tfGameTitle.setBounds(10, 53, 290, 28);
		add(tfGameTitle);
		tfGameTitle.setColumns(10);

		lblIntroModulePics = new JLabel("Obrazy Intra");
		lblIntroModulePics.setBounds(10, 210, 81, 14);
		add(lblIntroModulePics);

		lblOutroModulePics = new JLabel("Obrazy Outra");
		lblOutroModulePics.setBounds(10, 317, 81, 14);
		add(lblOutroModulePics);

		introPics = new JList(introPicsListModel);
		introPics.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		introPics.setBounds(10, 233, 290, 69);
		add(introPics);

		outroPics = new JList(outroPicsListModel);
		outroPics.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		outroPics.setBounds(10, 342, 290, 69);
		add(outroPics);

		JLabel lblMiejsceNaGraf = new JLabel(
				"Miejsce na graf kampanii, t.j listę quizów + graficzną reprezentację gry. Tu można wstawić też wiele innych rzeczy");
		lblMiejsceNaGraf.setBounds(314, 60, 565, 14);
		add(lblMiejsceNaGraf);

		addIntroOutroButtons();
	}

	public void addIntroOutroButtons() {
		btnAddIntro = new JButton("Dodaj");
		btnAddIntro.setBounds(101, 206, 89, 23);
		add(btnAddIntro);

		btnDelIntro = new JButton("Usun");
		btnDelIntro.setBounds(211, 206, 89, 23);
		add(btnDelIntro);

		btnAddOutro = new JButton("Dodaj");
		btnAddOutro.setBounds(101, 313, 89, 23);
		add(btnAddOutro);

		btnDelOutro = new JButton("Usun");
		btnDelOutro.setBounds(211, 313, 89, 23);
		add(btnDelOutro);

		lblStartDate = new JLabel("Data startu");
		lblStartDate.setBounds(10, 93, 81, 14);
		add(lblStartDate);

		tfStartDate = new JTextField();
		tfStartDate.setBounds(10, 125, 290, 28);
		add(tfStartDate);
		tfStartDate.setColumns(10);

		listOfQuizes = new JList();
		listOfQuizes.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
				null));
		listOfQuizes.setBounds(326, 125, 406, 286);
		add(listOfQuizes);
		addButtonsListeners();
	}
	private void addButtonsListeners() {
		btnAddOutro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getPicturesPath(outroPicsListModel);
			}
		});

		btnAddIntro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getPicturesPath(introPicsListModel);
			}
		});
	}
	
	private void getPicturesPath(DefaultListModel<String> list) {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle("Chose JPEG file");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);

		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			String str = chooser.getSelectedFile().getName();
			list.addElement(str);
		} else {
			System.out.println("No Selection ");
		}
	}
}
