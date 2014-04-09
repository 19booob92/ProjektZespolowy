package NewInterface;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;

public class QuestView extends JPanel {
	private JTextField questName;
	
	private JList<String> pics;
	private JList<String> sounds;
	private DefaultListModel<String> picsListModel;
	private DefaultListModel<String> soundsListModel;
	private JTextField timeoutField;
	protected final static int panelWidth=800;
	protected final static int panelHeight=800;
	
	public QuestView() {
		setLayout(null);
		setSize(panelWidth, panelHeight);
		JLabel lblNazwaQuizu = new JLabel("Nazwa quizu");
		lblNazwaQuizu.setBounds(24, 48, 76, 14);
		add(lblNazwaQuizu);

		picsListModel = new DefaultListModel<String>();
		soundsListModel = new DefaultListModel<String>();
		
		questName = new JTextField();
		questName.setBounds(24, 73, 253, 29);
		add(questName);
		questName.setColumns(10);
		
		JList sounds = new JList(soundsListModel);
		sounds.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		sounds.setBounds(341, 73, 253, 60);
		add(sounds);
		
		JLabel lblPics = new JLabel("Obrazy");
		lblPics.setBounds(341, 48, 76, 14);
		add(lblPics);
		
		JList pics = new JList(picsListModel);
		pics.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pics.setBounds(341, 169, 253, 60);
		add(pics);
		
		JLabel lblSounds = new JLabel("D\u017Awi\u0119ki");
		lblSounds.setBounds(341, 144, 76, 14);
		add(lblSounds);
		
		JLabel lblTimeout = new JLabel("Timeout");
		lblTimeout.setBounds(24, 144, 46, 14);
		add(lblTimeout);
		
		timeoutField = new JTextField();
		timeoutField.setBounds(24, 169, 253, 29);
		add(timeoutField);
		timeoutField.setColumns(10);
		
		JButton btnDodaj = new JButton("Dodaj");
		btnDodaj.setBounds(607, 72, 89, 23);
		add(btnDodaj);
		
		JButton btnUsun = new JButton("Usun");
		btnUsun.setBounds(607, 112, 89, 23);
		add(btnUsun);
		
		JButton btnDodaj_1 = new JButton("Dodaj");
		btnDodaj_1.setBounds(607, 168, 89, 23);
		add(btnDodaj_1);
		
		JButton btnUsun_1 = new JButton("Usun");
		btnUsun_1.setBounds(607, 206, 89, 23);
		add(btnUsun_1);

	}
}
