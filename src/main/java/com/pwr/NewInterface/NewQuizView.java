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
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
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
import com.pwr.Quest.ChoiceQuest;
import com.pwr.Quest.FieldQuest;
import com.pwr.Quest.OrderQuest;
import com.pwr.Quest.QuestFactory;
import com.pwr.Quest.QuestPoint;
import com.pwr.Quest.QuestType;
import com.pwr.Quest.DecisionQuest;
import com.pwr.Quest.TextQuest;

public class NewQuizView extends JFrame {

	// GUI Interface vars
	private JPanel leftSidePanel;
	private JPanel rightSidePanel;
	private JSplitPane splitPane;
	private JScrollPane leftScroll;
	private JScrollPane rightScroll;

	private JButton btnSaveQuiz;

	private JLabel lblTimeout;
	private JLabel lblType;
	
	private static final int panelWidth = 900;
	private static final int panelHeight = 800;

	private static final int windowWidth = 1000;
	private static final int windowHeight = 500;

	private JTextField timeoutField;
	private JTextField tfQuizName;
	private QuestView selectedCard;

	// Quest vars
	private Campaign campaignRef;
	private static int quizIndex;
	
	// Quest Card Views
	private FieldQuestView fieldView = new FieldQuestView();
	private MultipleChoiceQuestView choiceView = new MultipleChoiceQuestView();
	private TextQuestView textView = new TextQuestView();
	private OrderQuestView orderView = new OrderQuestView();
	private DecisionQuestView decisionView = new DecisionQuestView();
	
	
	public NewQuizView(Campaign campaign, int qInd) {
		super();
		campaignRef = campaign;
		quizIndex = qInd;
		initWindow();
		fillFieldsWithQuizData();
	}
	
	public NewQuizView(Campaign campaign) {
		super();
		campaignRef = campaign;
		quizIndex = -1;
		initWindow();
	}

	private void initWindow() {
		
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
	
	private void fillFieldsWithQuizData() {
		QuestPoint q = campaignRef.getQuizes().get(quizIndex);
		choiceView.setVisible(false);
		decisionView.setVisible(false);
		orderView.setVisible(false);
		textView.setVisible(false);
		fieldView.setVisible(false);
		if (q.getQuestType() == QuestType.CHOICEQUEST) {
			choiceView.setVisible(true);
			fillWithGeneralData(q, choiceView);
			fillWithChoiceQuestData((ChoiceQuest)q);
		} else if (q.getQuestType() == QuestType.DECISIONQUEST) {
			decisionView.setVisible(true);
			fillWithGeneralData(q, decisionView);
			fillWithDecisionQuestData((DecisionQuest)q);
		} else if (q.getQuestType() == QuestType.FIELDQUEST) {
			fieldView.setVisible(true);
			fillWithGeneralData(q, fieldView);
			fillWithFieldQuestData((FieldQuest)q);
		} else if (q.getQuestType() == QuestType.ORDERQUEST) {
			orderView.setVisible(true);
			fillWithGeneralData(q, orderView);
			fillWithOrderQuestData((OrderQuest)q);
		} else if (q.getQuestType() == QuestType.TEXTQUEST) {
			textView.setVisible(true);
			fillWithGeneralData(q, textView);
			fillWithTextQuestData((TextQuest)q);
		}
	}
	
	private void fillWithGeneralData(QuestPoint q, QuestView view) {
		view.points.setText(Integer.toString(q.getPoints()));
		view.date.setText(q.getDate());
		view.next.setText(q.getGoTo());
		view.wrong.setText(q.getWrong());
		view.preNote.setText(q.getPreNote());
		view.postNote.setText(q.getPostNote());
		this.tfQuizName.setText(q.getQuestName());
		this.timeoutField.setText(Integer.toString(q.getQuestTimeout()));
		view.pics.setModel(rewriteArrayListToJList(q.getPicturePaths(),q));
		view.sounds.setModel(rewriteArrayListToJList(q.getSoundPaths(),q));
	}
		
	private void fillWithFieldQuestData(FieldQuest q) {
		fieldView.heightField.setText(Double.toString(q.getHeight()));
		fieldView.widthField.setText(Double.toString(q.getWidth()));
		fieldView.latitudeField.setText(Double.toString(q.getYCoordinate()));
		fieldView.longitudeField.setText(Double.toString(q.getXCoordinate()));
	}

	private void fillWithTextQuestData(TextQuest q) {
		
	}

	private void fillWithOrderQuestData(OrderQuest q) {
		
	}

	private void fillWithChoiceQuestData(ChoiceQuest q) {
		
	}
	
	private void fillWithDecisionQuestData(DecisionQuest q) {
		
	}
	
	private void createLeftSidePanel() {

		JLabel lblTitle = new JLabel("Tytuł");
		lblTitle.setBounds(10, 42, 46, 14);
		leftSidePanel.add(lblTitle);

		String[] quizTypes = { "Zagadka terenowa", "Zagadka tekstowa",
				"Zagadka wielokrotnego wyboru", "Zagadka uporządkowania",
				"Zagadka decyzji" };

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

		btnSaveQuiz = new JButton("Zapisz quiz");
		btnSaveQuiz.setBounds(0, 239, 320, 23);
		leftSidePanel.add(btnSaveQuiz);
		
		//Refactor it!
		btnSaveQuiz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						QuestPoint newQuest;
						if (quizIndex == -1) {
							newQuest = null;
							for (Component component : rightSidePanel.getComponents()) {
								if (component.isVisible() == true) {
									selectedCard = (QuestView) component;
									if (selectedCard.introduceYourself() == "TextQuest") {
										newQuest = (TextQuest) QuestFactory.createQuest(QuestType.TEXTQUEST);
										GetTextQuestFields((TextQuest) newQuest, (TextQuestView) selectedCard);
									} else if (selectedCard.introduceYourself() == "MultipleChoiceQuest") {
										newQuest = (ChoiceQuest) QuestFactory.createQuest(QuestType.CHOICEQUEST);
										GetMultipleChoiceQuestFields((ChoiceQuest)newQuest, (MultipleChoiceQuestView)selectedCard);
									} else if (selectedCard.introduceYourself() == "DecisionQuest") {
										newQuest = (DecisionQuest) QuestFactory.createQuest(QuestType.DECISIONQUEST);
										GetDecisionQuestFields((DecisionQuest)newQuest, (DecisionQuestView)selectedCard);
									} else if (selectedCard.introduceYourself() == "FieldQuest") {
										newQuest = (FieldQuest) QuestFactory.createQuest(QuestType.FIELDQUEST);
										GetFieldQuestFields((FieldQuest)newQuest, (FieldQuestView)selectedCard);
									} else if (selectedCard.introduceYourself() == "OrderQuest") {
										newQuest = QuestFactory.createQuest(QuestType.ORDERQUEST);
										GetOrderQuestFields((OrderQuest)newQuest, (OrderQuestView)selectedCard);
									}
									GetGeneralQuestFields(newQuest, selectedCard);
								}
							}										
							newQuest.incrementId();
							campaignRef.addQuiz(newQuest);
							campaignRef.createdTrue();
						}
						else {
							newQuest = campaignRef.getQuizes().get(quizIndex);
							//for (Component comp : rightSidePanel.getComponents()) {
							for (Component comp : rightSidePanel.getComponents()) {
								if (comp.isVisible() == true) {
									if (newQuest.getQuestType() == QuestType.CHOICEQUEST){
										selectedCard = (MultipleChoiceQuestView) comp;
										GetMultipleChoiceQuestFields((ChoiceQuest)newQuest, (MultipleChoiceQuestView)selectedCard);
									} else if (newQuest.getQuestType() == QuestType.DECISIONQUEST){
										selectedCard = (DecisionQuestView) comp;
										GetDecisionQuestFields((DecisionQuest)newQuest, (DecisionQuestView)selectedCard);
									} else if (newQuest.getQuestType() == QuestType.FIELDQUEST){
										selectedCard = (FieldQuestView) comp;
										GetFieldQuestFields((FieldQuest)newQuest, (FieldQuestView)selectedCard);
									} else if (newQuest.getQuestType() == QuestType.ORDERQUEST){
										selectedCard = (OrderQuestView) comp;
										GetOrderQuestFields((OrderQuest)newQuest, (OrderQuestView)selectedCard);
									} else if (newQuest.getQuestType() == QuestType.TEXTQUEST){
										selectedCard = (TextQuestView) comp;
										GetTextQuestFields((TextQuest) newQuest, (TextQuestView) selectedCard);
									}
									GetGeneralQuestFields(newQuest, selectedCard);
								}
							}
							campaignRef.editedTrue();
						}
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
		
		rightSidePanel.add(fieldView, "Zagadka terenowa");
		rightSidePanel.add(textView, "Zagadka tekstowa");
		rightSidePanel.add(choiceView,"Zagadka wielokrotnego wyboru");
		rightSidePanel.add(orderView, "Zagadka uporządkowania");
		rightSidePanel.add(decisionView, "Zagadka decyzji");

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
		ZipPacker zip = new ZipPacker("./Paczka/paczka.zip");
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
	
	private void GetGeneralQuestFields(QuestPoint newQuest, QuestView questView) {
		newQuest.getPicturePaths().addAll(rewriteJListToArrayList(selectedCard.pics));
		newQuest.getSoundPaths().addAll(rewriteJListToArrayList(selectedCard.sounds));
		newQuest.setQuestDescription(selectedCard.paragraphList);
		newQuest.setQuestName(tfQuizName.getText());
		newQuest.setQuestTimeout(Integer.parseInt(timeoutField.getText()));
		newQuest.setPoints(Integer.parseInt(selectedCard.points.getText()));
		newQuest.setPostNote(selectedCard.postNote.getText());
		newQuest.setPreNote(selectedCard.preNote.getText());
		newQuest.setDate(selectedCard.date.getText());
		newQuest.setWrong(selectedCard.wrong.getText());
		newQuest.setGoTo(selectedCard.next.getText());
		/*for (QuestPoint q : campaignRef.getQuizes()) {
			if (selectedCard.next.getText() == q.getQuestName()) {
				newQuest.setGoTo(Integer.toString(q.getId()));
			}
			if (selectedCard.wrong.getText() == q.getWrong()) {
				newQuest.setWrong(Integer.toString(q.getId()));
			}
		}*/
	}
	
	private void GetTextQuestFields(TextQuest newQuest, TextQuestView questView) {
		
		newQuest.setGoTo(questView.textGoTo.getText());
		newQuest.setQuestAnswer(questView.textAnswer);
	}
	
	private void GetDecisionQuestFields(DecisionQuest newQuest, DecisionQuestView questView) {
		newQuest.setDecisionAnswer(questView.getAnswers(), questView.getGoToList());
	}
	
	private void GetMultipleChoiceQuestFields(ChoiceQuest newQuest, MultipleChoiceQuestView questView) {
		newQuest.setQuestAnswer(questView.getAnswers(),questView.getAnswersBooleans());
	}
	
	private void GetOrderQuestFields(OrderQuest newQuest, OrderQuestView questView) {
		newQuest.setQuestAnswer(questView.getAnswers());
	}

	private void GetFieldQuestFields(FieldQuest newQuest, FieldQuestView questView) {
		newQuest.setYCoordinate(Double.parseDouble(questView.latitudeField.getText()));
		newQuest.setXCoordinate(Double.parseDouble(questView.longitudeField.getText()));
		newQuest.setXWidth(Double.parseDouble(questView.widthField.getText()));
		newQuest.setYWidth(Double.parseDouble(questView.heightField.getText()));
	}
	
	private ArrayList rewriteJListToArrayList(JList list) {
		ArrayList newList = new ArrayList();
		for (int i = 0; i < list.getModel().getSize(); i++) {
			newList.add(list.getModel().getElementAt(i));
		}
		return newList;
	}
	
	private DefaultListModel rewriteArrayListToJList(ArrayList<String> arr, QuestPoint q) {
		DefaultListModel<String> newmodel = new DefaultListModel<String>();
		for(int i = 0; i < arr.size(); i++) {
		    newmodel.addElement(arr.get(i));
		}
		return newmodel;
	}
	
}
