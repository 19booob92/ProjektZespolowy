package com.pwr.NewInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import com.pwr.Graph.*;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;

import com.pwr.Quest.Campaign;

public class ProjectOptionsView extends JPanel implements Observer {

	private static final int panelWidth = 1000;
	private static final int panelHeight = 750;
	private JTextField tfGameTitle;
	private JTextField tfStartDate;

	private JLabel lblGameTitle;
	private JLabel lblIntroModulePics;
	private JLabel lblOutroModulePics;
	private JLabel lblStartDate;
	private JLabel lblLista;
	private GraphFacade graphFacade;
	
	
	private JList introPics;
	private JList outroPics;

	protected DefaultListModel<String> introPicsListModel;
	protected DefaultListModel<String> outroPicsListModel;
	protected DefaultListModel<String> quizListModel;
	
	private JButton btnAddIntro;
	private JButton btnDelIntro;
	private JButton btnAddOutro;
	private JButton btnDelOutro;
	private JButton btnEdit;
	private JList listOfQuizes;
	
	private Campaign campaign;

	public ProjectOptionsView(Campaign camp) {
		this.setSize(panelWidth, panelHeight);
		setLayout(null);
		
		campaign = camp;
		camp.addObserver(this);
		introPicsListModel = new DefaultListModel<String>();
		outroPicsListModel = new DefaultListModel<String>();
		quizListModel = new DefaultListModel<String>();
		
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

		listOfQuizes = new JList(quizListModel);
		listOfQuizes.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		listOfQuizes.setBounds(10, 465, 290, 253);
		
		for (String s : campaign.getQuizesNames())
			quizListModel.addElement(s);
		add(listOfQuizes);
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
		
		graphFacade = new GraphFacade();
		graphFacade.getGraphPanel().setQuizListFromArrayList(campaign.convertQuiz());
		graphFacade.getGraphPanel().setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		graphFacade.getGraphPanel().setBounds(310, 53, 676, 326);
		add(graphFacade.getGraphPanel());
		
		lblLista = new JLabel("Lista");
		lblLista.setBounds(10, 439, 85, 14);
		add(lblLista);
		
		btnEdit = new JButton("Edytuj");
		btnEdit.setBounds(211, 435, 89, 23);
		add(btnEdit);
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
		
		btnEdit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				int ind = listOfQuizes.getSelectedIndex();
				NewQuizView quizEditView = new NewQuizView(campaign, ind);
			}
		});
		
		btnDelIntro.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				int ind = introPics.getSelectedIndex();
				introPicsListModel.remove(ind);
			}
		});
		
		btnDelOutro.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				int ind = outroPics.getSelectedIndex();
				outroPicsListModel.remove(ind);
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
	
	public DefaultListModel getQuizListModel() {
		return quizListModel;
	}

	@Override
	public void update(Observable o, Object arg) {
		graphFacade.getGraphPanel().setQuizListFromArrayList(campaign.convertQuiz());
		graphFacade.getGraphPanel().repaint();
		if (campaign.getCreated() == true)
		{
			quizListModel.addElement(campaign.getLastQuiz().ToString());
			campaign.createdFalse();
		} else if (campaign.getEdited() == true) {
			campaign.editedFalse();
		}
	}
}
