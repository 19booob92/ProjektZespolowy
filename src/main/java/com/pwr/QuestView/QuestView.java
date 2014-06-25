package com.pwr.QuestView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
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
import com.pwr.MainView.ImagePreview;
import com.pwr.MainView.ProjectMainView;
import com.pwr.Other.ListRender;


public class QuestView extends JPanel implements DescribeView {
	protected JList<String> pics;
	protected JList<String> sounds;
	protected Set<String> setOfpath;
	protected String paragraph="";

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
	private JLabel lblNarration;
	
	protected JComboBox comboBoxNarration;
	protected ArrayList<String> listNarration;
	
	protected ArrayList<Boolean> soundInventoryList;
	protected ArrayList<Boolean> imageInventoryList;

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
		soundInventoryList = new ArrayList();
		
		sounds = new JList(soundsListModel);
		
		sounds.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		sounds.setCellRenderer(new ListRender());
		soundsScrollPane = new JScrollPane(sounds);
		soundsScrollPane.setBounds(23, 163, 302, 60);
		add(soundsScrollPane);

		lblPics = new JLabel("Obrazy");
		lblPics.setBounds(23, 42, 76, 14);
		add(lblPics);

		pics = new JList(picsListModel);
		pics.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pics.setCellRenderer(new ListRender());
		pics.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 2) {
		            int index = list.locationToIndex(evt.getPoint());
		            JFrame jf= new JFrame();
		            jf.setContentPane(new ImagePreview((String)list.getSelectedValue()));
		            jf.pack();
		            jf.setVisible(true);
		        } 
		    }
		});
		picsScrollPane = new JScrollPane(pics);
		picsScrollPane.setBounds(23, 67, 302, 60);
		add(picsScrollPane);
		
		lblNarration = new JLabel("Dźwięk do narracji");
		lblNarration.setBounds(23,234,120,23);
		add(lblNarration);
		
		listNarration = new ArrayList();
		listNarration.add("Brak");
		
		comboBoxNarration = new JComboBox(listNarration.toArray());
		comboBoxNarration.setBounds(188,234,137,23);
		add(comboBoxNarration);
		
		btnAddParagraph = new JButton("Edytuj treść zagadki");
		btnAddParagraph.setBounds(527, 234, 120, 23);
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

		btnDelSounds = new JButton("Usuń");
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
				{
					imageInventoryList.remove(pics.getSelectedIndex());
					String path = picsListModel.getElementAt(pics.getSelectedIndex());
					deleteFile(path);
					picsListModel.remove(pics.getSelectedIndex());
				}
			}
		});

		btnDelSounds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (soundsListModel.getSize() != 0)
				{
					String path = soundsListModel.getElementAt(sounds.getSelectedIndex());
					deleteFile(path);
					soundInventoryList.remove(sounds.getSelectedIndex());
					soundsListModel.remove(sounds.getSelectedIndex());
					int size = listNarration.size();
					listNarration = new ArrayList();
					listNarration.add("Brak");
					for(int i=1;i<size-1;i++)
					{
						listNarration.add(Integer.toString(i));
					}
					comboBoxNarration.removeAllItems();
					comboBoxNarration.setModel(new DefaultComboBoxModel(listNarration.toArray()));
				}
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
			}
		});

		sounds.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int index = sounds.getSelectedIndex();
					String selected = sounds.getSelectedValue();
					createDialog(soundInventoryList, index);
				}
			}
		});
		
		pics.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int index = pics.getSelectedIndex();
					String selected = pics.getSelectedValue();
					createDialog(imageInventoryList, index);
				}
			}
		});

	}
	
	private void createDialog(final ArrayList<Boolean> list, final int index) {
		final JDialog dialog = new JDialog();
		Boolean bool = list.get(index);
		dialog.setSize(360, 150);
		dialog.setResizable(false);
		dialog.getContentPane().setLayout(null);

		dialog.setLocationRelativeTo(this);
		dialog.setLayout(null);

		JLabel lblEquipmentLabel = new JLabel("Czy dodać jako ekwipunek?");
		lblEquipmentLabel.setBounds(25, 25, 200, 14);
		dialog.add(lblEquipmentLabel);

		final JCheckBox chckbxTrueOrFalse = new JCheckBox(
				"Tak/Nie");
		chckbxTrueOrFalse.setBounds(25, 47, 200, 23);
		chckbxTrueOrFalse.setSelected(bool);
		dialog.add(chckbxTrueOrFalse);

		JButton btnOk = new JButton("Ok");
		btnOk.setBounds(235, 70, 89, 23);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean trueOrFalse = chckbxTrueOrFalse.isSelected();
					if (trueOrFalse == true) {
						list.set(index, true);
					} else {
						list.set(index, false);
					}
				dialog.dispose();
			}
		});
		dialog.add(btnOk);
		dialog.setVisible(true);
	}

	private void getPicturesPath(DefaultListModel<String> list) {
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Chose JPEG file");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		if(ProjectMainView.actualFolder!=null)
			chooser.setCurrentDirectory(ProjectMainView.actualFolder);
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {	
			ProjectMainView.actualFolder=chooser.getSelectedFile().getParentFile();
			int dialogResult=JOptionPane.showConfirmDialog (null, "Czy chcesz dodać dźwięk jako ekwipunek?","Ekwipunek",JOptionPane.YES_NO_OPTION);
			if(dialogResult==JOptionPane.YES_OPTION)
			{
				soundInventoryList.add(true);
			}
			else{soundInventoryList.add(false);}
			//String str = chooser.getSelectedFile().getName();
			String str = chooser.getSelectedFile().getPath();
			list.addElement(str);
			listNarration.add(Integer.toString(list.size()));
			comboBoxNarration.removeAllItems();
			comboBoxNarration.setModel(new DefaultComboBoxModel(listNarration.toArray()));
			setOfpath.add(chooser.getCurrentDirectory().toString()
					+ chooser.getSelectedFile());
			System.out.println(setOfpath);
		} else {
			System.out.println("No Selection ");
		}
	}

	private void getSoundsPath(DefaultListModel<String> list) {
		JFileChooser chooser = new JFileChooser();
		if(ProjectMainView.actualFolder!=null)
			chooser.setCurrentDirectory(ProjectMainView.actualFolder);
		chooser.setDialogTitle("Chose MP3 file");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);

		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			ProjectMainView.actualFolder=chooser.getSelectedFile().getParentFile();
			int dialogResult=JOptionPane.showConfirmDialog (null, "Czy chcesz dodać obraz jako ekwipunek?","Ekwipunek",JOptionPane.YES_NO_OPTION);
			if(dialogResult==JOptionPane.YES_OPTION)
			{
				imageInventoryList.add(true);
			}
			else{imageInventoryList.add(false);}

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
	
	private void deleteFile(String path)
	{
		File file = new File(path);
		if(file.exists())
		{
			String folder = file.getParent();
			if(folder.equals("temp"))
			{
				file.delete();
			}
		}
	}
	
	public void newNarration()
	{
		comboBoxNarration.removeAllItems();
		comboBoxNarration.setModel(new DefaultComboBoxModel(listNarration.toArray()));
	}
	
}
