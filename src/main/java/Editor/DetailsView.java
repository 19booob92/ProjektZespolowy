package Editor;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class DetailsView extends JFrame {

	private Set<String> setOfpath;
	private MapPoint mapPoint;
	
	private JPanel panel;
	private JTextField textField;
	
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


	public DetailsView(MapPoint mP, int QuestType)
	{
		super("Details view");
		mapPoint = mP;
		this.pointName = mapPoint.toString();

		chooseSounds = new JButton("Choose sounds");
		chooseSounds.setBounds(377, 143, 112, 23);

		btnApplySettings = new JButton("Apply settings");
		btnApplySettings.setBounds(10, 375, 119, 23);

		choosePics = new JButton("Chose files");
		choosePics.setBounds(377, 32, 112, 25);

		panel = new JPanel();
		panel.setLayout(null);

		setOfpath = new HashSet<>();
		picsListModel = new DefaultListModel<String>();
		soundsListModel = new DefaultListModel<String>();
		
		pics = new JList<String>(picsListModel);
		lblListOfPics = new JLabel("List of pic's");

		pics.setBounds(248, 62, 241, 56);
		pics.setPreferredSize(new Dimension(70, 100));
		lblListOfPics.setBounds(255, 37, 102, 15);

		sounds = new JList<String>(soundsListModel);
		lblListOfSounds = new JLabel("List of sounds");

		sounds.setBounds(248, 172, 241, 56);
		lblListOfSounds.setBounds(248, 147, 71, 14);
		
		setLocationRelativeTo(null);
		
		
		
		pointNameLbl = new JLabel(this.pointName);
		pointNameLbl.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC,15));
		pointNameLbl.setBounds(12, 11, 117, 15);

				
		//block of listeners

		chooseSounds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getPath(soundsListModel);
			}
		});
		
		
		choosePics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getPath(picsListModel);
			}
		});
		
		btnApplySettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TO DO
				//Wstawienie obrazkow i dzwiekow do ArrayList w obiekcie mapPoint 
				//Tu trzeba wywolac funkcje w zaleznosci od typu zagadki - na razie prowizorycznie wpisano Stringi
				mapPoint.getQuest().setQuestDescription("Description");
				mapPoint.getQuest().setQuestAnswer("Answer");
			}
		});
		
		picsListModel.addElement((mapPoint.getQuest().getPicturePaths().get(0)));
		soundsListModel.addElement(mapPoint.getQuest().getSoundPaths().get(0));
		
		panel.add(choosePics);
		panel.add(chooseSounds);
		panel.add(pics);
		panel.add(sounds);
		panel.add(pointNameLbl);
		panel.add(lblListOfSounds);
		panel.add(lblListOfPics);
		panel.add(btnApplySettings);
		
		switch(QuestType){
			case 0:
				constructTextQuestFields();
				break;
			case 1:
				constructRangeQuestFields();
				break;
			//Inne typy zagadek
			default:
				break;
		}
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

		JTextArea questContent = new JTextArea(mapPoint.getQuest().getQuestDescription());
		questContent.setBounds(12, 254, 477, 107);
		panel.add(questContent);
		
		JLabel lblQuestContent = new JLabel("Quest Content");
		lblQuestContent.setBounds(12, 37, 71, 14);
		panel.add(lblQuestContent);
		
		JLabel lblQuestAnswer = new JLabel("Quest Answer");
		lblQuestAnswer.setBounds(12, 180, 73, 14);
		panel.add(lblQuestAnswer);
		
		textField = new JTextField(mapPoint.getQuest().getQuestAnswer());
		textField.setBounds(95, 177, 143, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		getContentPane().add(panel);
	}
	
	private void getPath(DefaultListModel<String> list) {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle("Chose JPEG file");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);

		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			// short path looks better in GUI
			list.addElement(chooser.getSelectedFile().getName());
			// full path for creating package
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
}
