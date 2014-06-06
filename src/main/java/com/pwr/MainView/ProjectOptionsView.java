package com.pwr.MainView;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.EtchedBorder;


import com.pwr.Graph.GraphFacade;
import com.pwr.Other.DateTimePicker;
import com.pwr.Other.NoDataInFieldException;
import com.pwr.Quest.Campaign;
import com.pwr.Quest.QuestPoint;
import com.pwr.QuestView.NewQuizView;

public class ProjectOptionsView extends JPanel implements Observer {

	private static final int panelWidth = 1000;
	private static final int panelHeight = 750;
	private JTextField tfGameTitle;
	private JTextField tfStartDate;
	
	private DateTimePicker dateTimePicker;
	private Date datePickerDate;

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
	protected ArrayList<String> introTextList;
	protected ArrayList<String> outroTextList;

	protected DefaultListModel<String> introPicsListModel;
	protected DefaultListModel<String> outroPicsListModel;
	protected DefaultListModel<String> quizListModel;
	
	private JButton btnAddIntro;
	private JButton btnDelIntro;
	private JButton btnEditIntro;
	private JButton btnAddOutro;
	private JButton btnDelOutro;
	private JButton btnEditOutro;
	private JButton btnEdit;
	private JButton btnDel;
	private JList listOfQuizes;
	
	private boolean introClicked=true;
	
	private Campaign campaign;

	public ProjectOptionsView(Campaign camp) {
		introTextList = new ArrayList();
		outroTextList = new ArrayList();
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

		lblIntroModulePics = new JLabel("Intro");
		lblIntroModulePics.setBounds(10, 210, 81, 14);
		add(lblIntroModulePics);

		lblOutroModulePics = new JLabel("Outro");
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
		//this.tfStartDate.setText(campaign.getGameDate());
		
		SimpleDateFormat simple = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		
		try {
			datePickerDate=simple.parse(campaign.getGameDate());
			dateTimePicker.setDate(simple.parse(campaign.getGameDate()));
			dateTimePicker.updateUI();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (String q : campaign.getIntroPics()) {
			this.introPicsListModel.addElement(q);			
		}
		for (String q : campaign.getOutroPics()) {
			this.outroPicsListModel.addElement(q);			
		}
		introTextList = campaign.getIntroText();
		outroTextList = campaign.getOutroText();
	}
	
	public void addIntroOutroButtons() {
		btnAddIntro = new JButton("Dodaj");
		btnAddIntro.setBounds(81, 206, 69, 23);
		add(btnAddIntro);

		btnDelIntro = new JButton("Usun");
		btnDelIntro.setBounds(219, 206, 69, 23);
		add(btnDelIntro);
		
		btnEditIntro = new JButton("Tekst");
		btnEditIntro.setBounds(150,206,69,23);
		add(btnEditIntro);
		
		btnAddOutro = new JButton("Dodaj");
		btnAddOutro.setBounds(81, 313, 69, 23);
		add(btnAddOutro);
		
		btnEditOutro = new JButton("Tekst");
		btnEditOutro.setBounds(150,313,69,23);
		add(btnEditOutro);

		btnDelOutro = new JButton("Usun");
		btnDelOutro.setBounds(219, 313, 69, 23);
		add(btnDelOutro);

		lblStartDate = new JLabel("Data startu");
		lblStartDate.setBounds(10, 93, 81, 14);
		add(lblStartDate);

		/*tfStartDate = new JTextField();
		tfStartDate.setBounds(10, 125, 290, 28);
		add(tfStartDate);
		tfStartDate.setColumns(10);*/
		
		datePickerDate = new Date();
		dateTimePicker = new DateTimePicker(datePickerDate);
		dateTimePicker.setBounds(10, 125, 290, 28);
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		dateTimePicker.setFormats( dateFormat );
		dateTimePicker.setTimeFormat( DateFormat.getTimeInstance( DateFormat.MEDIUM ) );
		dateTimePicker.setDate(datePickerDate);
		add(dateTimePicker);
		
		graphFacade = new GraphFacade();
		graphFacade.getGraphPanel().setQuizListFromArrayList(campaign.convertQuiz());
		graphFacade.getGraphPanel().setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		graphFacade.getGraphPanel().setBounds(310, 53, 900, 900);
		
		graphScrollPane = new JScrollPane();
		graphScrollPane.setLayout(new ScrollPaneLayout());
		graphScrollPane.setBounds(310, 79, 600, 600);
		graphScrollPane.setViewportView(graphFacade.getGraphPanel());

		add(graphScrollPane);
		graphFacade.getGraphPanel().setPreferredSize(new Dimension(900, 900));

		lblLista = new JLabel("Lista");
		lblLista.setBounds(10, 439, 85, 14);
		add(lblLista);

		btnEdit = new JButton("Edytuj");
		btnEdit.setBounds(101, 435, 89, 23);
		add(btnEdit);
		
		btnDel = new JButton("Usuń");
		btnDel.setBounds(211, 435, 89, 23);
		add(btnDel);
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
		
		btnEditOutro.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				introClicked=false;
				if(outroPics.getSelectedIndex()>=0)
				{
					if(outroTextList.size()==outroPics.getSelectedIndex())
					{
						outroTextList.add("");
					}
					createDialog();
				}	
			}
			
		});
		
		btnEditIntro.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				introClicked=true;
				if(introPics.getSelectedIndex()>=0)
				{
					if(introTextList.size()==introPics.getSelectedIndex())
					{
						introTextList.add("");
					}
					createDialog();
				}	
			}
			
		});

		btnEdit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				for (QuestPoint quest : campaign.getQuizes()) {
					if (listOfQuizes.getSelectedValue().toString().equals(quest.getQuestName())) {
						NewQuizView quizEditView = new NewQuizView(campaign, quest.getId());
					}
				}
			}
		});
		
		btnDel.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				int ind = listOfQuizes.getSelectedIndex();
				if (quizListModel.size() != 0) {
					for (QuestPoint q : campaign.getQuizes()) {
						if (q.getQuestName() == quizListModel.get(ind)) {
							campaign.getQuizes().remove(q);
							updateGraph();
							quizListModel.remove(ind);
							break;
						}
					}
				}
			}

		});

		btnDelIntro.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int ind = introPics.getSelectedIndex();
				if (ind >= 0) {
					introPicsListModel.remove(ind);
					introTextList.remove(ind);
				}
			}
		});

		btnDelOutro.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int ind = outroPics.getSelectedIndex();
				if (ind >= 0) {
					outroPicsListModel.remove(ind);
					outroTextList.remove(ind);
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
			String str = chooser.getSelectedFile().getPath();
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
		graphFacade.getGraphPanel().setQuizListFromArrayList(
				campaign.convertQuiz());
		graphFacade.getGraphPanel().repaint();
		if (campaign.getCreated() == true) {
			quizListModel.addElement(campaign.getLastQuiz().toString());
			campaign.createdFalse();
		} else if (campaign.getEdited() == true) {
			updateQuestList();
			campaign.editedFalse();
		} else if (campaign.getDeleted()) {
			updateQuestList();
			campaign.deleteFalse();
		}
	}

	private void updateQuestList() {
		listOfQuizes.removeAll();
		quizListModel.removeAllElements();
		for (String questName : campaign.getQuizesNames()) {
			quizListModel.addElement(questName);
		}
	}

	public String getGameTitle() {
		if (tfGameTitle.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Podaj tytul");
			throw new NoDataInFieldException();
		} else {
			return tfGameTitle.getText();
		}
	}

	public String getGameDate() {
		/*if (!tfStartDate
				.getText()
				.matches(
						"[0-3][0-9]-[0-1][0-9]-[0-9]{4} [0-2][0-9]:[0-6][0-9]:[0-6][0-9]")) {
			JOptionPane.showMessageDialog(null,
					"Podaj date w formacie dd-MM-rrrr hh:mm:ss");
			throw new NoDataInFieldException();
		} else {
			return tfStartDate.getText();
		}*/
		Date dsa=dateTimePicker.getDate();
		SimpleDateFormat simpleFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		return simpleFormat.format(dsa);
	}
	
	private void createDialog() 
	{

		final JTextArea textArea;
		JScrollPane scroll;
		
		JButton okBtn;
		final JDialog dialog = new JDialog();

		dialog.setSize(new Dimension(400,400));
		dialog.setLayout(new BorderLayout());
		
		textArea = new JTextArea();
		textArea.setPreferredSize(new Dimension(360,300));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		if(introClicked)
			textArea.setText(introTextList.get(introPics.getSelectedIndex()));
		if(!introClicked)
			textArea.setText(outroTextList.get(outroPics.getSelectedIndex()));
		scroll = new JScrollPane(textArea);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		dialog.add(scroll,BorderLayout.NORTH);
		
		okBtn = new JButton("Ok");
		okBtn.setPreferredSize(new Dimension(60,30));
		okBtn.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(introClicked)
					introTextList.set(introPics.getSelectedIndex(), textArea.getText());
				if(!introClicked)
					outroTextList.set(outroPics.getSelectedIndex(), textArea.getText());
				dialog.dispose();
			}
			
		});
		dialog.add(okBtn, BorderLayout.SOUTH);

		dialog.setVisible(true);
	}
}
