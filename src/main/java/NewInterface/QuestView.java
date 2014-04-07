package NewInterface;

import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class QuestView extends JPanel {
	private JTextField questName;
	
	private JList<String> pics;
	private JList<String> sounds;
	private DefaultListModel<String> picsListModel;
	private DefaultListModel<String> soundsListModel;

	public QuestView() {
		setLayout(null);

		JLabel lblNazwaQuizu = new JLabel("Nazwa quizu");
		lblNazwaQuizu.setBounds(27, 31, 76, 14);
		add(lblNazwaQuizu);

		picsListModel = new DefaultListModel<String>();
		soundsListModel = new DefaultListModel<String>();
		
		questName = new JTextField();
		questName.setBounds(187, 28, 253, 20);
		add(questName);
		questName.setColumns(10);
		
		JList sounds = new JList(soundsListModel);
		sounds.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		sounds.setBounds(187, 59, 253, 60);
		add(sounds);
		
		JLabel lblPics = new JLabel("Obrazy");
		lblPics.setBounds(27, 62, 76, 14);
		add(lblPics);
		
		JList pics = new JList(picsListModel);
		pics.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pics.setBounds(187, 130, 253, 60);
		add(pics);
		
		JLabel lblSounds = new JLabel("D\u017Awi\u0119ki");
		lblSounds.setBounds(27, 133, 76, 14);
		add(lblSounds);

	}
}
