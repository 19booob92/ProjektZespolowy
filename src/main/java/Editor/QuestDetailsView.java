package Editor;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Quest.MapPoint;
import Quest.QuestFactory;
import Quest.QuestType;
import javax.swing.border.EtchedBorder;

public class QuestDetailsView extends JFrame implements KeyListener {

	private Set<String> setOfpath;
	private MapPoint mapPoint;
	
	private JPanel generalPanel;
	private JPanel optionsPanel;
	
	private JTextField textFieldAnswer;
	private JTextArea questContent;
	private JLabel lblQuestAnswer;
	
	private JButton choosePics;
	private JButton chooseSounds;
	private JButton btnApplySettings;
	
	private JList<String> pics;
	private JList<String> sounds;
	private DefaultListModel<String> picsListModel;
	private DefaultListModel<String> soundsListModel;
	
	private String pointName;
	
	private JLabel pointNameLbl;
	private JLabel lblListOfPics;
	private JLabel lblListOfSounds;
	private JButton btnDelete2;
	private JTextField textFieldTimeout;
	private JList listTreasures;
	private JLabel lblTreasures;
	private JTextField textField;


	public QuestDetailsView(MapPoint mP, QuestType type)
	{
		super("Details view");
		mapPoint = mP;
		this.pointName = mapPoint.toString();

		chooseSounds = new JButton("Choose sounds");
		chooseSounds.setBounds(400, 63, 112, 23);

		choosePics = new JButton("Choose files");
		choosePics.setBounds(141, 62, 112, 25);

		generalPanel = new JPanel();
		generalPanel.setLayout(null);

		setOfpath = new HashSet<>();
		picsListModel = new DefaultListModel<String>();
		soundsListModel = new DefaultListModel<String>();
		
		pics = new JList(picsListModel);
		pics.setFocusable(true);
		lblListOfPics = new JLabel("List of pic's");

		pics.setBounds(12, 98, 241, 56);
		pics.setPreferredSize(new Dimension(70, 100));
		lblListOfPics.setBounds(12, 37, 102, 15);

		sounds = new JList(soundsListModel);
		lblListOfSounds = new JLabel("List of sounds");

		sounds.setBounds(271, 97, 241, 56);
		lblListOfSounds.setBounds(271, 37, 71, 14);
		
		setLocationRelativeTo(null);
		
		
		
		pointNameLbl = new JLabel(this.pointName);
		pointNameLbl.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC,15));
		pointNameLbl.setBounds(12, 11, 117, 15);

				
		//block of listeners

		chooseSounds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getSoundsPath(soundsListModel);
			}
		});
		
		
		choosePics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getPicturesPath(picsListModel);
			}
		});
				
		//Wyswietl dane dla wybranego punktu
		for (String s : mapPoint.getQuest().getPicturePaths())
			picsListModel.addElement(s);
		for (String s : mapPoint.getQuest().getSoundPaths())
			soundsListModel.addElement(s);		
		
		generalPanel.add(choosePics);
		generalPanel.add(chooseSounds);
		generalPanel.add(pics);
		generalPanel.add(sounds);
		generalPanel.add(pointNameLbl);
		generalPanel.add(lblListOfSounds);
		generalPanel.add(lblListOfPics);
		
		//Listenery usuwania
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectedIndex = pics.getSelectedIndex();
				for (String s : mapPoint.getQuest().getPicturePaths())
					if ( s == pics.getSelectedValue())
					{
						mapPoint.getQuest().getPicturePaths().remove(selectedIndex);
						picsListModel.remove(selectedIndex);
					}
			}
		});
		
		btnDelete.setBounds(12, 63, 112, 23);
		generalPanel.add(btnDelete);
		
		btnDelete2 = new JButton("Delete");
		btnDelete2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = sounds.getSelectedIndex();
				for (String s : mapPoint.getQuest().getSoundPaths())
					if ( s == sounds.getSelectedValue())
					{
						mapPoint.getQuest().getSoundPaths().remove(selectedIndex);
						soundsListModel.remove(selectedIndex);
					}
			}
		});
		btnDelete2.setBounds(270, 63, 112, 23);
		generalPanel.add(btnDelete2);
		
		////////////////////////////////////////////
		/*
		JTextArea questContent = new JTextArea(mapPoint.getQuest().getQuestDescription());
		questContent.setBounds(12, 190, 500, 107);
		generalPanel.add(questContent);
		
		JLabel lblQuestAnswer = new JLabel("Quest Answer");
		lblQuestAnswer.setBounds(12, 324, 117, 14);
		generalPanel.add(lblQuestAnswer);
		
		textFieldAnswer = new JTextField(mapPoint.getQuest().getQuestAnswer());
		textFieldAnswer.setBounds(141, 321, 371, 20);
		generalPanel.add(textFieldAnswer);
		textFieldAnswer.setColumns(10);
		
		JLabel lblQuestContent = new JLabel("Quest Content");
		lblQuestContent.setBounds(12, 165, 102, 14);
		generalPanel.add(lblQuestContent);
		*/
		listTreasures = new JList();
		listTreasures.setBounds(12, 393, 241, 56);
		generalPanel.add(listTreasures);
		
		lblTreasures = new JLabel("Treasures");
		lblTreasures.setBounds(12, 368, 84, 14);
		generalPanel.add(lblTreasures);		
		
		switch(type){
			case TEXTQUEST:
				constructTextQuestFields();
				break;
			case CHOICEQUEST:
				constructRangeQuestFields();
				break;
			//Inne typy zagadek
			default:
				constructTextQuestFields();
				break;
		}
		getContentPane().add(generalPanel);
		
		optionsPanel = new JPanel();
		optionsPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		optionsPanel.setBounds(529, 62, 213, 374);
		generalPanel.add(optionsPanel);
		optionsPanel.setLayout(null);
		
		JLabel lblQuestTimeout = new JLabel("Quest Timeout");
		lblQuestTimeout.setBounds(10, 44, 70, 14);
		optionsPanel.add(lblQuestTimeout);
		///////////////////////////////////////////
		textFieldTimeout = new JTextField(Double.toString(mapPoint.getQuest().getQuestTimeout()));
		textFieldTimeout.setBounds(117, 41, 86, 20);
		optionsPanel.add(textFieldTimeout);
		textFieldTimeout.setColumns(10);

		btnApplySettings = new JButton("Apply settings");
		btnApplySettings.setBounds(57, 340, 119, 23);
		optionsPanel.add(btnApplySettings);
		
		textField = new JTextField();
		textField.setBounds(117, 11, 86, 20);
		optionsPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblQuestName = new JLabel("Quest Name");
		lblQuestName.setBounds(10, 14, 70, 14);
		optionsPanel.add(lblQuestName);
		
		btnApplySettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//mapPoint.getQuest().setQuestDescription(questContent.getText());
				mapPoint.getQuest().setQuestAnswer(textFieldAnswer.getText());
				mapPoint.getQuest().setQuestTimeout(Double.parseDouble(textFieldTimeout.getText()));
			}
		});


		setSize(new Dimension(768, 499));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	//Tworz pola zagadki dotyczacej zasiegu
	private void constructRangeQuestFields() {
		// TODO Auto-generated method stub
		getContentPane().add(generalPanel);
	}

	//Tworz pola zagadki tekstowej
	private void constructTextQuestFields()
	{
		JTextArea questContent = new JTextArea(mapPoint.getQuest().getQuestDescription());
		questContent.setBounds(12, 190, 500, 107);
		generalPanel.add(questContent);
		
		JLabel lblQuestAnswer = new JLabel("Quest Answer");
		lblQuestAnswer.setBounds(12, 324, 117, 14);
		generalPanel.add(lblQuestAnswer);
		
		textFieldAnswer = new JTextField(mapPoint.getQuest().getQuestAnswer());
		textFieldAnswer.setBounds(141, 321, 371, 20);
		generalPanel.add(textFieldAnswer);
		textFieldAnswer.setColumns(10);
		
		JLabel lblQuestContent = new JLabel("Quest Content");
		lblQuestContent.setBounds(12, 165, 102, 14);
		generalPanel.add(lblQuestContent);
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
			mapPoint.getQuest().getPicturePaths().add(str);
			setOfpath.add(chooser.getCurrentDirectory().toString()
					+ chooser.getSelectedFile());
			System.out.println(setOfpath);
		} else {
			System.out.println("No Selection ");
		}
	}

	private void getSoundsPath(DefaultListModel<String> list) {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle("Chose MP3 file");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);

		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			String str = chooser.getSelectedFile().getName();
			list.addElement(str);
			mapPoint.getQuest().getSoundPaths().add(str);
			setOfpath.add(chooser.getCurrentDirectory().toString()
					+ chooser.getSelectedFile());
			System.out.println(setOfpath);
		} else {
			System.out.println("No Selection ");
		}
	}
		
	public String getPointName() {
		return pointName;
	}

	public void setPointName(String pointName) {
		this.pointName = pointName;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode())
	     {
	         case KeyEvent.VK_DELETE: 
	        	 picsListModel.remove(pics.getSelectedIndex());
	             repaint();
	             break;
	     } 
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
