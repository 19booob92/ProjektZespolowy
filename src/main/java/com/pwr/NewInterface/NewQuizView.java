package com.pwr.NewInterface;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import com.pwr.Editor.ZipPacker;
import com.pwr.Quest.Campaign;
import com.pwr.Quest.QuestFactory;
import com.pwr.Quest.QuestPoint;
import com.pwr.Quest.QuestType;
import com.pwr.Quest.TextQuest;

public class NewQuizView extends JFrame {

	// GUI Interface vars
	private JPanel leftSidePanel;
	private JPanel rightSidePanel;
	private JSplitPane splitPane;
	private JScrollPane leftScroll;
	private JScrollPane rightScroll;

	private JButton btnSafeQuiz;

	private JLabel lblTimeout;
	private JLabel lblType;

	private static final int panelWidth = 800;
	private static final int panelHeight = 800;

	private static final int windowWidth = 1000;
	private static final int windowHeight = 500;

	private JTextField timeoutField;
	private JTextField tfQuizName;
	private QuestView selectedCard;

	// Quest vars
	private Campaign campaignRef;

	public NewQuizView(Campaign campaign) {
		super();
		campaignRef = campaign;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());

		setSize(windowWidth, windowHeight);
		leftSidePanel = new JPanel();
		leftSidePanel.setPreferredSize(new Dimension(320, panelHeight));
		rightSidePanel = new JPanel(new CardLayout());
		rightSidePanel.setPreferredSize(new Dimension(panelWidth, panelHeight));

		leftScroll = new JScrollPane(leftSidePanel);
		rightScroll = new JScrollPane(rightSidePanel);
		rightScroll
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftScroll,
				rightScroll);

		leftSidePanel.setLayout(null);

		createLeftSidePanel();

		splitPane.setResizeWeight(0.3);
		splitPane.setBounds(10, 11, 764, 418);
		getContentPane().add(splitPane);
		setVisible(true);
	}

	private void createLeftSidePanel() {

		JLabel lblTitle = new JLabel("Tytuł");
		lblTitle.setBounds(10, 42, 46, 14);
		leftSidePanel.add(lblTitle);

		String[] quizTypes = { "Zagadka terenowa", "Zagadka tekstowa",
				"Zagadka wielokrotnego wyboru", "Zagadka uporządkowania",
				"Zagadka zasięgu" };

		final JComboBox questTypeCombo = new JComboBox(quizTypes);
		questTypeCombo.setBounds(76, 112, 154, 27);
		leftSidePanel.add(questTypeCombo);

		lblType = new JLabel("Typ");
		lblType.setBounds(10, 118, 46, 14);
		leftSidePanel.add(lblType);

		JLabel lblOpcjeQuizu = new JLabel("Opcje quizu");
		lblOpcjeQuizu.setBounds(10, 11, 126, 14);
		leftSidePanel.add(lblOpcjeQuizu);

		tfQuizName = new JTextField();
		tfQuizName.setBounds(76, 36, 154, 27);
		leftSidePanel.add(tfQuizName);
		tfQuizName.setColumns(10);

		timeoutField = new JTextField("0");
		timeoutField.setBounds(76, 74, 154, 27);
		leftSidePanel.add(timeoutField);
		timeoutField.setColumns(10);

		lblTimeout = new JLabel("Timeout");
		lblTimeout.setBounds(10, 80, 46, 14);
		leftSidePanel.add(lblTimeout);

		btnSafeQuiz = new JButton("Zapisz quiz");
		btnSafeQuiz.setBounds(0, 239, 320, 23);
		leftSidePanel.add(btnSafeQuiz);

		btnSafeQuiz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						TextQuestView textQuestView = null;
						QuestPoint newQuest = null;
						for (Component component : rightSidePanel
								.getComponents()) {
							if (component.isVisible() == true) {
								selectedCard = (QuestView) component;
								if (selectedCard.introduceYourself() == "TextQuest") {
									textQuestView = (TextQuestView) component;
									newQuest = QuestFactory.createQuest(QuestType.TEXTQUEST);
								} else if (selectedCard.introduceYourself() == "MultipleChoiceQuest") {
									newQuest = QuestFactory.createQuest(QuestType.CHOICEQUEST);
								} else if (selectedCard.introduceYourself() == "RangeQuest") {
									newQuest = QuestFactory.createQuest(QuestType.RANGEQUEST);
								} else if (selectedCard.introduceYourself() == "FieldQuest") {
									newQuest = QuestFactory.createQuest(QuestType.FIELDQUEST);
								} else if (selectedCard.introduceYourself() == "OrderQuest") {
									newQuest = QuestFactory.createQuest(QuestType.ORDERQUEST);
								}
							}
						}										

						/*
						 * XmlBuilder xml = new XmlBuilder("tytul");
						 * 
						 * xml.addQuizText(newTextQuest.getQuestName(),
						 * newTextQuest
						 * .getSoundPaths(),newTextQuest.getPicturePaths(),
						 * newTextQuest.getQuestDescription(),
						 * newTextQuest.getPreNote(),
						 * newTextQuest.getPostNote(), newTextQuest.getGoTo(),
						 * newTextQuest.getPoints(), newTextQuest.getDate(),
						 * newTextQuest.getQuestAnswer(),
						 * newTextQuest.getQuestTimeout(),
						 * newTextQuest.getWrong()); try { xml.saveXml();
						 * //dodac pola w zaleznosci od innych typow } catch
						 * (TransformerException ex) {
						 * Logger.getLogger(NewQuizView
						 * .class.getName()).log(Level.SEVERE, null, ex); }
						 */
						ZipPacking(newQuest);

						campaignRef.addQuiz(newQuest);
						campaignRef.createXml("title");

						System.out.println(campaignRef.getQuizes().get(0)
								.getQuestName());
						System.out.println(campaignRef.getQuizes().get(0)
								.getSoundPaths().get(0));
						System.out.println(campaignRef.getQuizes().get(0)
								.getPicturePaths().get(0));

						dispose();
					}
				});
			}
		});
		
		rightSidePanel.add(new FieldQuestView(), "Zagadka terenowa");
		rightSidePanel.add(new TextQuestView(), "Zagadka tekstowa");
		rightSidePanel.add(new MultipleChoiceQuestView(),"Zagadka wielokrotnego wyboru");
		rightSidePanel.add(new OrderQuestView(), "Zagadka uporządkowania");
		rightSidePanel.add(new RangeQuestView(), "Zagadka zasięgu");

		questTypeCombo.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent aE) {
				CardLayout cl = (CardLayout) rightSidePanel.getLayout();
				cl.show(rightSidePanel, aE.getItem().toString());
			}
		});

	}

	private void ZipPacking(QuestPoint newQuest)
	{
		ZipPacker zip = new ZipPacker("paczka.zip");
		for (int i = 0; i < newQuest.getPicturePaths().size(); i++) {
			try {
				zip.addFile(newQuest.getPicturePaths().get(i));
			} catch (IOException ex) {
				Logger.getLogger(NewQuizView.class.getName())
						.log(Level.SEVERE, null, ex);
			}
		}

		for (int i = 0; i < newQuest.getSoundPaths().size(); i++) {
			try {
				zip.addFile(newQuest.getSoundPaths().get(i));
			} catch (IOException ex) {
				Logger.getLogger(NewQuizView.class.getName())
						.log(Level.SEVERE, null, ex);
			}
		}

		try {
			zip.addFile("Config.xml");
		} catch (IOException ex) {
			Logger.getLogger(NewQuizView.class.getName()).log(
					Level.SEVERE, null, ex);
		}
		zip.closeZip();
	}
	
	private void TextQuestFieldsGetter(QuestPoint newQuest, TextQuestView questView) {
		newQuest.getPicturePaths().addAll(rewriteJListToArrayList(selectedCard.pics));
		newQuest.getSoundPaths().addAll(rewriteJListToArrayList(selectedCard.sounds));
		newQuest.setQuestDescription(selectedCard.paragraphList);
		newQuest.setQuestName(tfQuizName.getText());
		newQuest.setQuestTimeout(Integer.parseInt(timeoutField.getText()));
		newQuest.setPoints(Integer.parseInt(selectedCard.points.getText()));
		//newQuest.setGoTo(questView.textGoTo.getText());
		newQuest.setPostNote(selectedCard.postNote.getText());
		newQuest.setPreNote(selectedCard.preNote.getText());
		newQuest.setDate(selectedCard.date.getText());
		newQuest.setWrong(selectedCard.wrong.getText());
		newQuest.setQuestAnswer(questView.textAnswer);
	}
	
	private void RangeQuestFieldsGetter(QuestPoint newQuest) {
			
	}
	
	private void MultipleChoiceQuestFieldsGetter(QuestPoint newQuest) {
		
	}
	
	private void OrderQuestFieldsGetter(QuestPoint newQuest) {
		
	}

	private void FieldQuestFieldsGetter(QuestPoint newQuest) {
		
	}
	
	private ArrayList rewriteJListToArrayList(JList list) {
		ArrayList newList = new ArrayList();
		for (int i = 0; i < list.getModel().getSize(); i++) {
			newList.add(list.getModel().getElementAt(i));
		}
		return newList;

	}
}
