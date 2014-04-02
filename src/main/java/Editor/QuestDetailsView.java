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

public class QuestDetailsView extends JFrame implements KeyListener {

	private Set<String> setOfpath;
	private MapPoint mapPoint;
	
	private JPanel panel;
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


	public QuestDetailsView(MapPoint mP, QuestType type)
	{
		super("Details view");
		mapPoint = mP;
		this.pointName = mapPoint.toString();

		chooseSounds = new JButton("Choose sounds");
		chooseSounds.setBounds(377, 143, 112, 23);

		btnApplySettings = new JButton("Apply settings");
		btnApplySettings.setBounds(10, 375, 119, 23);

		choosePics = new JButton("Choose files");
		choosePics.setBounds(377, 32, 112, 25);

		panel = new JPanel();
		panel.setLayout(null);

		setOfpath = new HashSet<>();
		picsListModel = new DefaultListModel<String>();
		soundsListModel = new DefaultListModel<String>();
		
		pics = new JList(picsListModel);
		pics.setFocusable(true);
		lblListOfPics = new JLabel("List of pic's");

		pics.setBounds(248, 62, 241, 56);
		pics.setPreferredSize(new Dimension(70, 100));
		lblListOfPics.setBounds(248, 11, 102, 15);

		sounds = new JList(soundsListModel);
		lblListOfSounds = new JLabel("List of sounds");

		sounds.setBounds(248, 172, 241, 56);
		lblListOfSounds.setBounds(246, 129, 71, 14);
		
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
		
		btnApplySettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mapPoint.getQuest().setQuestDescription(questContent.getText());
				mapPoint.getQuest().setQuestAnswer(textFieldAnswer.getText());
				mapPoint.getQuest().setQuestTimeout(Double.parseDouble(textFieldTimeout.getText()));
			}
		});
				
		//Wyswietl dane dla wybranego punktu
		for (String s : mapPoint.getQuest().getPicturePaths())
			picsListModel.addElement(s);
		for (String s : mapPoint.getQuest().getSoundPaths())
			soundsListModel.addElement(s);		
		
		panel.add(choosePics);
		panel.add(chooseSounds);
		panel.add(pics);
		panel.add(sounds);
		panel.add(pointNameLbl);
		panel.add(lblListOfSounds);
		panel.add(lblListOfPics);
		panel.add(btnApplySettings);
		
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
		
		btnDelete.setBounds(248, 33, 112, 23);
		panel.add(btnDelete);
		
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
		btnDelete2.setBounds(248, 143, 112, 23);
		panel.add(btnDelete2);
		
		////////////////////////////////////////////
		/*
		JTextArea questContent = new JTextArea(mapPoint.getQuest().getQuestDescription());
		questContent.setBounds(12, 254, 477, 107);
		panel.add(questContent);
		
		JLabel lblQuestAnswer = new JLabel("Quest Answer");
		lblQuestAnswer.setBounds(10, 104, 117, 14);
		panel.add(lblQuestAnswer);
		
		textFieldAnswer = new JTextField(mapPoint.getQuest().getQuestAnswer());
		textFieldAnswer.setBounds(121, 101, 117, 20);
		panel.add(textFieldAnswer);
		textFieldAnswer.setColumns(10);*/
		///////////////////////////////////////////
		textFieldTimeout = new JTextField(Double.toString(mapPoint.getQuest().getQuestTimeout()));
		textFieldTimeout.setBounds(121, 60, 117, 20);
		panel.add(textFieldTimeout);
		textFieldTimeout.setColumns(10);
		
		JLabel lblQuestTimeout = new JLabel("Quest Timeout");
		lblQuestTimeout.setBounds(10, 63, 94, 14);
		panel.add(lblQuestTimeout);
		
		listTreasures = new JList();
		listTreasures.setBounds(12, 172, 223, 56);
		panel.add(listTreasures);
		
		lblTreasures = new JLabel("Treasures");
		lblTreasures.setBounds(10, 147, 84, 14);
		panel.add(lblTreasures);		
		
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
		getContentPane().add(panel);
		

		setSize(new Dimension(515, 448));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	//Tworz pola zagadki dotyczacej zasiegu
	private void constructRangeQuestFields() {
		// TODO Auto-generated method stub
		getContentPane().add(panel);
	}

	//Tworz pola zagadki tekstowej
	private void constructTextQuestFields()
	{

		questContent = new JTextArea(mapPoint.getQuest().getQuestDescription());
		questContent.setBounds(12, 254, 477, 107);
		panel.add(questContent);
		
		lblQuestAnswer = new JLabel("Quest Answer");
		lblQuestAnswer.setBounds(10, 104, 117, 14);
		panel.add(lblQuestAnswer);
		
		textFieldAnswer = new JTextField(mapPoint.getQuest().getQuestAnswer());
		textFieldAnswer.setBounds(121, 101, 117, 20);
		panel.add(textFieldAnswer);
		textFieldAnswer.setColumns(10);
		//getContentPane().add(panel);
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
