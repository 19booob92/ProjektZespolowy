package NewInterface;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class QuestView extends JPanel {
	private JTextField questName;
	
	private JList<String> pics;
	private JList<String> sounds;
	private DefaultListModel<String> picsListModel;
	private DefaultListModel<String> soundsListModel;
	private JTextField timeoutField;
	
	public QuestView() {
		setLayout(null);

		JLabel lblNazwaQuizu = new JLabel("Nazwa quizu");
		lblNazwaQuizu.setBounds(24, 31, 76, 14);
		add(lblNazwaQuizu);

		picsListModel = new DefaultListModel<String>();
		soundsListModel = new DefaultListModel<String>();
		
		questName = new JTextField();
		questName.setBounds(187, 28, 253, 20);
		add(questName);
		questName.setColumns(10);
		
		JList sounds = new JList(soundsListModel);
		sounds.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		sounds.setBounds(187, 93, 253, 60);
		add(sounds);
		
		JLabel lblPics = new JLabel("Obrazy");
		lblPics.setBounds(24, 96, 76, 14);
		add(lblPics);
		
		JList pics = new JList(picsListModel);
		pics.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pics.setBounds(187, 164, 253, 60);
		add(pics);
		
		JLabel lblSounds = new JLabel("D\u017Awi\u0119ki");
		lblSounds.setBounds(24, 167, 76, 14);
		add(lblSounds);
		
		JLabel lblTimeout = new JLabel("Timeout");
		lblTimeout.setBounds(24, 62, 46, 14);
		add(lblTimeout);
		
		timeoutField = new JTextField();
		timeoutField.setBounds(187, 59, 253, 20);
		add(timeoutField);
		timeoutField.setColumns(10);

	}
}
