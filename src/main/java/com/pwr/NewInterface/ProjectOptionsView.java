package com.pwr.NewInterface;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.EtchedBorder;

import com.pwr.Graph.GraphFacade;
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
	private static GraphFacade graphFacade;
	private JScrollPane graphScrollPane;
	private JScrollPane outroScroll;
	private JScrollPane introScroll;
	private JScrollPane listScroll;
	protected JList introPics;
	protected JList outroPics;

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
		introScroll = new JScrollPane(introPics);
		introScroll.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		introScroll.setBounds(10, 233, 290, 69);
		add(introScroll);

		outroPics = new JList(outroPicsListModel);
		outroScroll = new JScrollPane(outroPics);
		outroScroll.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		outroScroll.setBounds(10, 342, 290, 69);
		add(outroScroll);

		listOfQuizes = new JList(quizListModel);
		listScroll = new JScrollPane(listOfQuizes);
		listScroll.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		listScroll.setBounds(10, 465, 290, 253);
		
		for (String s : campaign.getQuizesNames())
			quizListModel.addElement(s);
		add(listScroll);
		addIntroOutroButtons();
	}

	public void initiateGameFields() {
		this.tfGameTitle.setText(campaign.getGameTitle());
		this.tfStartDate.setText(campaign.getGameDate());
		for (String q : campaign.getIntroPics()) {
			this.introPicsListModel.addElement(q);			
		}
		for (String q : campaign.getOutroPics()) {
			this.outroPicsListModel.addElement(q);			
		}
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
		graphFacade.getGraphPanel().setBounds(310, 53, 900, 900);
		
		graphScrollPane = new JScrollPane();
		graphScrollPane.setLayout(new ScrollPaneLayout());
		graphScrollPane.setBounds(310, 53, 600, 600);
		graphScrollPane.setViewportView(graphFacade.getGraphPanel());
		
		add(graphScrollPane);
		graphFacade.getGraphPanel().setPreferredSize(new Dimension(900, 900));
		
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
				if (ind >= 0) {
					NewQuizView quizEditView = new NewQuizView(campaign, ind);
				}
			}
		});
		
		btnDelIntro.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				int ind = introPics.getSelectedIndex();
				if (ind >= 0) {
					introPicsListModel.remove(ind);
				}
			}
		});
		
		btnDelOutro.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				int ind = outroPics.getSelectedIndex();
				if (ind >= 0) {
					outroPicsListModel.remove(ind);
				}
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
	
	public static void updateView() {
		graphFacade.getGraphPanel().repaint();
	}
	
	public DefaultListModel getQuizListModel() {
		return quizListModel;
	}
	
	public void updateGraph()
	{
		graphFacade.getGraphPanel().setQuizListFromArrayList(campaign.convertQuiz());
		graphScrollPane.repaint();
		//graphFacade.getGraphPanel().repaint();
	}

	@Override
	public void update(Observable o, Object arg) {
		graphFacade.getGraphPanel().setQuizListFromArrayList(campaign.convertQuiz());
		graphFacade.getGraphPanel().repaint();
		if (campaign.getCreated() == true) {
			quizListModel.addElement(campaign.getLastQuiz().ToString());
			campaign.createdFalse();
		} else if (campaign.getEdited() == true) {
			campaign.editedFalse();
		}
	}
	public String getGameTitle() {
		return tfGameTitle.getText();
	}
	
	public String getGameDate() {
		return tfStartDate.getText();
	}
}
