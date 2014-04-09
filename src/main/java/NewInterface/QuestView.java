package NewInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
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
	private Set<String> setOfpath;
	
	private DefaultListModel<String> picsListModel;
	private DefaultListModel<String> soundsListModel;
	
	private JTextField timeoutField;

	private JButton btnAddPics;
	private JButton btnAddSounds;
	private JButton btnDelPics;
	private JButton btnDelSounds;

	private JLabel lblPics;
	private JLabel lblSounds;
	private JLabel lblTimeout;

	protected final static int panelWidth = 800;
	protected final static int panelHeight = 800;

	public QuestView() {
		setLayout(null);
		setSize(panelWidth, panelHeight);
		
		setOfpath = new HashSet<>();
		
		JLabel lblNazwaQuizu = new JLabel("Nazwa quizu");
		lblNazwaQuizu.setBounds(24, 48, 76, 14);
		add(lblNazwaQuizu);

		picsListModel = new DefaultListModel<String>();
		soundsListModel = new DefaultListModel<String>();

		questName = new JTextField();
		questName.setBounds(24, 73, 253, 29);
		add(questName);
		questName.setColumns(10);

		sounds = new JList(soundsListModel);
		sounds.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		sounds.setBounds(341, 73, 253, 60);
		add(sounds);

		lblPics = new JLabel("Obrazy");
		lblPics.setBounds(341, 48, 76, 14);
		add(lblPics);

		pics = new JList(picsListModel);
		pics.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pics.setBounds(341, 169, 253, 60);
		add(pics);

		lblSounds = new JLabel("D\u017Awi\u0119ki");
		lblSounds.setBounds(341, 144, 76, 14);
		add(lblSounds);

		lblTimeout = new JLabel("Timeout");
		lblTimeout.setBounds(24, 144, 46, 14);
		add(lblTimeout);

		timeoutField = new JTextField();
		timeoutField.setBounds(24, 169, 253, 29);
		add(timeoutField);
		timeoutField.setColumns(10);

		btnAddPics = new JButton("Dodaj");
		btnAddPics.setBounds(607, 72, 89, 23);
		add(btnAddPics);

		btnDelPics = new JButton("Usun");
		btnDelPics.setBounds(607, 112, 89, 23);
		add(btnDelPics);

		btnAddSounds = new JButton("Dodaj");
		btnAddSounds.setBounds(607, 168, 89, 23);
		add(btnAddSounds);

		btnDelSounds = new JButton("Usun");
		btnDelSounds.setBounds(607, 206, 89, 23);
		add(btnDelSounds);

		addButtonsListeners();
	}

	private void addButtonsListeners() {
		btnAddPics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getSoundsPath(soundsListModel);
			}
		});

		btnAddSounds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getPicturesPath(picsListModel);
			}
		});

		btnDelPics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectedIndex = pics.getSelectedIndex();
				/*for (String s : mapPoint.getQuest().getPicturePaths())
					if (s == pics.getSelectedValue()) {
						mapPoint.getQuest().getPicturePaths()
								.remove(selectedIndex);
						picsListModel.remove(selectedIndex);
					}
					*/
			}
		});

		btnDelSounds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = sounds.getSelectedIndex();
				/*for (String s : mapPoint.getQuest().getSoundPaths())
					if (s == sounds.getSelectedValue()) {
						mapPoint.getQuest().getSoundPaths()
								.remove(selectedIndex);
						soundsListModel.remove(selectedIndex);
					}*/
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
			setOfpath.add(chooser.getCurrentDirectory().toString()
					+ chooser.getSelectedFile());
			System.out.println(setOfpath);
		} else {
			System.out.println("No Selection ");
		}
	}
}
