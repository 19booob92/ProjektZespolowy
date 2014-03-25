package Editor;

import java.awt.Dimension;
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

	private JPanel panel;
	private JTextArea questContent = new JTextArea("Insert quest content");
	private JButton choosePics = new JButton("Chose files");
	private JList<String> paths;
	private DefaultListModel<String> listModel;
	private JLabel pointNameLbl;
	private String pointName = "default";
	private JLabel lblNewLabel;
	private JTextField textField;
	private JLabel lblListOfSounds;
	private JList list;

	public DetailsView(String pointName, int QuestType) {
		super("Details view");
		
		panel = new JPanel();
		//TYMCZASOWE KONTROLKI DO ZMIANY WIDOKU W WB
		
		//KONIEC
		
		
		//Typy zagadek
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
		
		
		setSize(new Dimension(515, 351));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);

	}
	//Tworz pola zagadki dotyczacej zasiegu
	private void constructRangeQuestFields() {
		// TODO Auto-generated method stub
		
	}

	//Tworz pola zagadki tekstowej
	private void constructTextQuestFields()
	{
		this.pointName = pointName;
		setOfpath = new HashSet<>();
		listModel = new DefaultListModel<String>();
		paths = new JList<String>(listModel);
		
		paths.setBounds(248, 62, 241, 107);
		paths.setPreferredSize(new Dimension(70, 100));
		setLocationRelativeTo(null);
		questContent.setBounds(12, 62, 226, 107);
		pointNameLbl = new JLabel(this.pointName);
		pointNameLbl.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC,
				15));
		pointNameLbl.setBounds(12, 11, 117, 15);
		panel.setLayout(null);

		panel.add(questContent);
		choosePics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getPath();
			}

			private void getPath() {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Chose JPEG file");
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);

				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					// short path looks better in GUI
					listModel.addElement(chooser.getSelectedFile().getName());
					// full path for creating package
					setOfpath.add(chooser.getCurrentDirectory().toString()
							+ chooser.getSelectedFile());
					System.out.println(setOfpath);
				} else {
					System.out.println("No Selection ");
				}
			}
		});

		choosePics.setBounds(377, 32, 112, 25);
		panel.add(choosePics);
		panel.add(paths);
		panel.add(pointNameLbl);

		getContentPane().add(panel);

		lblNewLabel = new JLabel("List of pic's");
		lblNewLabel.setBounds(255, 37, 102, 15);
		panel.add(lblNewLabel);
		
		JLabel lblQuestContent = new JLabel("Quest Content");
		lblQuestContent.setBounds(12, 37, 71, 14);
		panel.add(lblQuestContent);
		
		JLabel lblQuestAnswer = new JLabel("Quest Answer");
		lblQuestAnswer.setBounds(12, 180, 73, 14);
		panel.add(lblQuestAnswer);
		
		textField = new JTextField();
		textField.setBounds(95, 177, 143, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		lblListOfSounds = new JLabel("List of sounds");
		lblListOfSounds.setBounds(258, 180, 71, 14);
		panel.add(lblListOfSounds);
		
		list = new JList();
		list.setBounds(248, 205, 241, 96);
		panel.add(list);
	}
	
	public String getPointName() {
		return pointName;
	}

	public void setPointName(String pointName) {
		this.pointName = pointName;
	}
}
