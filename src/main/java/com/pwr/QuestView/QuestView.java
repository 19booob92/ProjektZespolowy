package com.pwr.QuestView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import com.pwr.DetailsView.DescribeView;
import com.pwr.DetailsView.ParagraphInputDialog;

public class QuestView extends JPanel implements DescribeView {
	protected JList<String> pics;
	protected JList<String> sounds;
	protected Set<String> setOfpath;
	protected String paragraph="";
	protected ArrayList<Boolean> imageInventoryList;

	protected DefaultListModel<String> picsListModel;
	protected DefaultListModel<String> soundsListModel;

	private ParagraphInputDialog paragraphInputDialog;
	
	private JButton btnAddPics;
	private JButton btnAddSounds;
	private JButton btnDelPics;
	private JButton btnDelSounds;
	private JButton btnAddParagraph;

	private JScrollPane introScrollPane;
	private JScrollPane outroScrollPane;
	private JScrollPane picsScrollPane;
	private JScrollPane soundsScrollPane;
	
	protected JTextArea preNote;
	protected JTextArea postNote;

	private JLabel lblPics;
	private JLabel lblSounds;
	private JLabel lblPreNote;
	private JLabel lblPostNote;

	protected final static int PANEL_WIDTH = 900;
	protected final static int PANEL_HEIGHT = 800;

	private int paragraphsTrigger = 1;

	public QuestView() {
		setLayout(null);
		setSize(PANEL_WIDTH, PANEL_HEIGHT);

		setOfpath = new HashSet<String>();

		picsListModel = new DefaultListModel<String>();
		soundsListModel = new DefaultListModel<String>();
		imageInventoryList = new ArrayList();
		
		sounds = new JList(soundsListModel);
		
		sounds.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		soundsScrollPane = new JScrollPane(sounds);
		soundsScrollPane.setBounds(23, 163, 302, 60);
		add(soundsScrollPane);

		lblPics = new JLabel("Obrazy");
		lblPics.setBounds(23, 42, 76, 14);
		add(lblPics);

		pics = new JList(picsListModel);
		pics.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		picsScrollPane = new JScrollPane(pics);
		picsScrollPane.setBounds(23, 67, 302, 60);
		add(picsScrollPane);

		btnAddParagraph = new JButton("Edytuj paragraf");
		btnAddParagraph.setBounds(23, 243, 120, 23);
		add(btnAddParagraph);


		lblSounds = new JLabel("Dźwięki");
		lblSounds.setBounds(23, 138, 76, 14);
		add(lblSounds);

		btnAddPics = new JButton("Dodaj");
		btnAddPics.setBounds(126, 38, 89, 23);
		add(btnAddPics);

		btnDelPics = new JButton("Usuń");
		btnDelPics.setBounds(236, 38, 89, 23);
		add(btnDelPics);

		btnAddSounds = new JButton("Dodaj");
		btnAddSounds.setBounds(126, 134, 89, 23);
		add(btnAddSounds);

		btnDelSounds = new JButton("Usun");
		btnDelSounds.setBounds(236, 134, 89, 23);
		add(btnDelSounds);

		lblPreNote = new JLabel("Notka początkowa");
		lblPreNote.setBounds(345, 38, 120, 23);
		add(lblPreNote);

		preNote = new JTextArea();
		preNote.setLineWrap(true);
		introScrollPane = new JScrollPane(preNote);
		introScrollPane.setBounds(345, 67, 302, 60);
		add(introScrollPane);

		lblPostNote = new JLabel("Notka końcowa");
		lblPostNote.setBounds(345, 138, 120, 23);
		add(lblPostNote);

		postNote = new JTextArea();
		postNote.setLineWrap(true);
		outroScrollPane = new JScrollPane(postNote);
		outroScrollPane.setBounds(345, 163, 302, 60);
		add(outroScrollPane);

		addButtonsListeners();
	}

	private void addButtonsListeners() {
		btnAddPics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getSoundsPath(picsListModel);
			}
		});

		btnAddSounds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getPicturesPath(soundsListModel);
			}
		});

		btnDelPics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (picsListModel.getSize() != 0)
					picsListModel.remove(pics.getSelectedIndex());
			}
		});

		btnDelSounds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (soundsListModel.getSize() != 0)
					soundsListModel.remove(sounds.getSelectedIndex());
			}
		});

		btnAddParagraph.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				paragraphInputDialog = new ParagraphInputDialog(paragraph);
				paragraphInputDialog.addWindowListener(new WindowAdapter()
				{
					public void windowClosed(WindowEvent e) {
				        paragraph = paragraphInputDialog.getParagraph();
				    }
				});
				/*paragraphs.addElement("Paragraf "
						+ Integer.toString(paragraphsTrigger));
				paragraphList.add("");
				paragraphsComboBox.setSelectedIndex(paragraphsTrigger - 1);
				paragraphsTrigger++;*/
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
			//String str = chooser.getSelectedFile().getName();
			String str = chooser.getSelectedFile().getPath();
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
			//String str = chooser.getSelectedFile().getName();
			String str = chooser.getSelectedFile().getPath();
			list.addElement(str);
			setOfpath.add(chooser.getCurrentDirectory().toString()
					+ chooser.getSelectedFile());
			System.out.println(setOfpath);
		} else {
			System.out.println("No Selection ");
		}
	}

	@Override
	public String introduceYourself() {
		return null;
	}
	
}
