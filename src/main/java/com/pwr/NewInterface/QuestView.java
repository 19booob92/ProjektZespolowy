package com.pwr.NewInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class QuestView extends JPanel {
	protected JList<String> pics;
	protected JList<String> sounds;
        protected DefaultComboBoxModel paragraphs = new DefaultComboBoxModel();
        protected JComboBox paragraphsComboBox;
	protected Set<String> setOfpath;
        
        protected ArrayList<String> paragraphList = new ArrayList<String>();
        
	protected DefaultListModel<String> picsListModel;
	protected DefaultListModel<String> soundsListModel;

	private JButton btnAddPics;
	private JButton btnAddSounds;
	private JButton btnDelPics;
	private JButton btnDelSounds;
        private JButton btnAddParagraph;
        private JButton btnEditParagraph;
        private JButton btnDeleteParagraph;

        protected JTextArea preNote;
        protected JTextArea postNote;
        protected JTextField points;
        protected JTextField wrong;
        protected JTextField date;
        
        private JLabel lblParagraph;
	private JLabel lblPics;
	private JLabel lblSounds;
        private JLabel lblPreNote;
        private JLabel lblPostNote;
        private JLabel lblPoints;
        private JLabel lblWrong;
        private JLabel lblDate;
        
	protected final static int panelWidth = 800;
	protected final static int panelHeight = 800;
        
        private int paragraphsTrigger=1;
	public QuestView() {
		setLayout(null);
		setSize(panelWidth, panelHeight);

		setOfpath = new HashSet<>();

		picsListModel = new DefaultListModel<String>();
		soundsListModel = new DefaultListModel<String>();

		sounds = new JList(soundsListModel);
		sounds.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		sounds.setBounds(23, 67, 302, 60);
		add(sounds);
                
		lblPics = new JLabel("Obrazy");
		lblPics.setBounds(23, 42, 76, 14);
		add(lblPics);

		pics = new JList(picsListModel);
		pics.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pics.setBounds(23, 163, 302, 60);
		add(pics);
                
                lblParagraph = new JLabel("Opis zagadki");
                lblParagraph.setBounds(23, 220, 120, 23);
                add(lblParagraph);
                
                paragraphsComboBox = new JComboBox(paragraphs);
                paragraphsComboBox.setSelectedIndex(-1);
                paragraphsComboBox.setBounds(23, 243, 160, 23);
                add(paragraphsComboBox);
                
                btnAddParagraph = new JButton("Dodaj paragraf");
                btnAddParagraph.setBounds(183,243,120,23);
                add(btnAddParagraph);
                
                btnEditParagraph = new JButton("Edytuj paragraf");
                btnEditParagraph.setBounds(303, 243, 120, 23);
                add(btnEditParagraph);
                
                btnDeleteParagraph = new JButton("Usuń paragraf");
                btnDeleteParagraph.setBounds(423, 243, 120, 23);
                add(btnDeleteParagraph);

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
                preNote.setBounds(345, 67, 302, 60);
                preNote.setLineWrap(true);
                add(preNote);
                
                lblPostNote = new JLabel("Notka końcowa");
                lblPostNote.setBounds(345,138,120,23);
                add(lblPostNote);
                
                postNote = new JTextArea();
                postNote.setBounds(345, 163, 302, 60);
                postNote.setLineWrap(true);
                add(postNote);
                
                lblPoints = new JLabel("Punkty");
                lblPoints.setBounds(667,38,120,30);
                add(lblPoints);
                
                points = new JTextField();
                points.setBounds(667, 68, 120, 30);
                add(points);
                
                
                lblWrong = new JLabel("Zagadka przy błędnej odpowiedzi");
                lblWrong.setBounds(667,88,200,30);
                add(lblWrong);
                
                wrong = new JTextField();
                wrong.setBounds(667,118,120,30);
                add(wrong);
                
                lblDate = new JLabel("Data");
                lblDate.setBounds(667,138,120,30);
                add(lblDate);
                
                date = new JTextField();
                date.setBounds(667,168,120,30);
                add(date);

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
				/*
				 * for (String s : mapPoint.getQuest().getPicturePaths()) if (s
				 * == pics.getSelectedValue()) {
				 * mapPoint.getQuest().getPicturePaths() .remove(selectedIndex);
				 * picsListModel.remove(selectedIndex); }
				 */
			}
		});

		btnDelSounds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = sounds.getSelectedIndex();
				/*
				 * for (String s : mapPoint.getQuest().getSoundPaths()) if (s ==
				 * sounds.getSelectedValue()) {
				 * mapPoint.getQuest().getSoundPaths() .remove(selectedIndex);
				 * soundsListModel.remove(selectedIndex); }
				 */
			}
		});
                
                btnAddParagraph.addActionListener(new ActionListener()
                {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                          paragraphs.addElement("Paragraf "+Integer.toString(paragraphsTrigger));
                          paragraphList.add("");
                          paragraphsComboBox.setSelectedIndex(paragraphsTrigger-1);
                          paragraphsTrigger++;
                    }
                });
                                
                btnEditParagraph.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int selectedIndex = paragraphsComboBox.getSelectedIndex();
                        if(selectedIndex!=-1){
                            String tempParagraph=JOptionPane.showInputDialog("Podaj treść ",paragraphList.get(selectedIndex));
                            paragraphList.set(selectedIndex, tempParagraph);
                        }
                    }
                });
                
                btnDeleteParagraph.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int selectedIndex = paragraphsComboBox.getSelectedIndex();
                        if(selectedIndex!=-1)
                        {
                            paragraphsTrigger--;
                            paragraphList.remove(selectedIndex);
                            paragraphs.removeElementAt(selectedIndex);
                            paragraphsComboBox = new JComboBox(paragraphs);
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
