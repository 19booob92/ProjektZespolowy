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
import java.util.EmptyStackException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import Utils.NoDataInFieldException;

import com.pwr.Editor.ZipPacker;
import com.pwr.Quest.Campaign;
import com.pwr.Quest.ChoiceQuest;
import com.pwr.Quest.DecisionQuest;
import com.pwr.Quest.FieldQuest;
import com.pwr.Quest.OrderQuest;
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

	private JButton btnSaveQuiz;
	private JLabel lblTimeout;
	private JLabel lblType;
	private JLabel lblWrong;
	private JLabel lblPoints;
	private JLabel lblDate;

	private static final int PANEL_WIDTH = 650;
	private static final int PANEL_HEIGHT = 900;

	private static final int WINDOW_WIDTH = 1000;
	private static final int WINDOW_HEIGHT = 500;

	protected JTextField wrong;
	private JTextField timeoutField;
	private JTextField tfQuizName;
	protected JTextField points;
	protected JTextField date;

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

	/**
	 * @wbp.parser.constructor
	 */
	public NewQuizView(Campaign campaign) {
		super();
		campaignRef = campaign;
		quizIndex = -1;
		initWindow();
	}

	private void initWindow() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());

		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		leftSidePanel = new JPanel();
		leftSidePanel.setPreferredSize(new Dimension(320, PANEL_HEIGHT));
		rightSidePanel = new JPanel(new CardLayout());
		rightSidePanel
				.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));

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
			fillWithChoiceQuestData((ChoiceQuest) q);
		} else if (q.getQuestType() == QuestType.DECISIONQUEST) {
			decisionView.setVisible(true);
			fillWithGeneralData(q, decisionView);
			fillWithDecisionQuestData((DecisionQuest) q);
		} else if (q.getQuestType() == QuestType.FIELDQUEST) {
			fieldView.setVisible(true);
			fillWithGeneralData(q, fieldView);
			fillWithFieldQuestData((FieldQuest) q);
		} else if (q.getQuestType() == QuestType.ORDERQUEST) {
			orderView.setVisible(true);
			fillWithGeneralData(q, orderView);
			fillWithOrderQuestData((OrderQuest) q);
		} else if (q.getQuestType() == QuestType.TEXTQUEST) {
			textView.setVisible(true);
			fillWithGeneralData(q, textView);
			fillWithTextQuestData((TextQuest) q);
		}
	}

	private void fillWithGeneralData(QuestPoint q, QuestView view) {

		view.preNote.setText(q.getPreNote());
		view.postNote.setText(q.getPostNote());
		this.tfQuizName.setText(q.getQuestName());
		this.timeoutField.setText(Integer.toString(q.getQuestTimeout()));
		rewriteArrayListToJList(q.getPicturePaths(), view.picsListModel);
		rewriteArrayListToJList(q.getSoundPaths(), view.soundsListModel);
		view.paragraphs = new DefaultComboBoxModel();
		view.paragraphList = new ArrayList();
		// view.paragraphList.addAll(q.getQuestDescription());
		points.setText(Integer.toString(q.getPoints()));
		date.setText(q.getDate());
		for (int i = 0; i < q.getQuestDescription().size(); i++)
			view.paragraphs.addElement(q.getQuestDescription().get(i));
	}

	private void fillWithFieldQuestData(FieldQuest q) {
		fieldView.heightField.setText(Double.toString(q.getHeight()));
		fieldView.widthField.setText(Double.toString(q.getWidth()));
		fieldView.latitudeField.setText(Double.toString(q.getYCoordinate()));
		fieldView.longitudeField.setText(Double.toString(q.getXCoordinate()));
	}

	private void fillWithTextQuestData(TextQuest q) {
		textView.textAnswer = new ArrayList();
		// textView.textAnswer.addAll(q.getQuestAnswer());
		for (int i = 0; i < q.getQuestAnswer().size(); i++) {
			textView.getAnswersCombo().addElement(q.getQuestAnswer().get(i));
		}
	}

	private void fillWithOrderQuestData(OrderQuest q) {
		for (int i = 0; i < q.getQuestAnswer().size(); i++) {
			orderView.tableModel.addRow(new Object[] { "",
					q.getQuestAnswer().get(i), "add" });
		}
	}

	private void fillWithChoiceQuestData(ChoiceQuest q) {
		for (int i = 0; i < q.getQuestAnswer().size(); i++) {
			choiceView.tableModel
					.addRow(new Object[] { "", q.getQuestAnswer().get(i),
							q.getQuestAnswerCorrect().get(i) });
		}
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

		JLabel lblOpcjeQuizu = new JLabel("Opcje quizu");
		lblOpcjeQuizu.setBounds(10, 11, 126, 14);
		leftSidePanel.add(lblOpcjeQuizu);

		tfQuizName = new JTextField();
		tfQuizName.setBounds(76, 36, 160, 27);
		leftSidePanel.add(tfQuizName);
		tfQuizName.setColumns(10);

		timeoutField = new JTextField("0");
		timeoutField.setBounds(76, 74, 160, 27);
		leftSidePanel.add(timeoutField);
		timeoutField.setColumns(10);

		lblTimeout = new JLabel("Timeout");
		lblTimeout.setBounds(10, 80, 46, 14);
		leftSidePanel.add(lblTimeout);

		final JComboBox questTypeCombo = new JComboBox(quizTypes);
		questTypeCombo.setBounds(76, 254, 154, 27);
		leftSidePanel.add(questTypeCombo);

		lblType = new JLabel("Typ");
		lblType.setBounds(10, 260, 46, 14);
		leftSidePanel.add(lblType);

		btnSaveQuiz = new JButton("Zapisz quest");
		btnSaveQuiz.setBounds(76, 292, 120, 23);
		leftSidePanel.add(btnSaveQuiz);

		wrong = new JTextField();
		leftSidePanel.add(wrong);
		wrong.setBounds(76, 192, 160, 27);

		date = new JTextField();
		leftSidePanel.add(date);
		date.setBounds(76, 154, 160, 27);

		points = new JTextField();
		leftSidePanel.add(points);
		points.setBounds(76, 112, 160, 27);

		lblPoints = new JLabel("Punkty");
		leftSidePanel.add(lblPoints);
		lblPoints.setBounds(10, 118, 46, 14);

		lblDate = new JLabel("Data");
		leftSidePanel.add(lblDate);
		lblDate.setBounds(10, 160, 46, 14);

		lblWrong = new JLabel("<html><body>Zagadka<br/>kara</body></html>");
		leftSidePanel.add(lblWrong);
		lblWrong.setBounds(10, 192, 50, 30);

		// Refactor it!
		btnSaveQuiz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						QuestPoint newQuest;
						if (quizIndex == -1) {
							newQuest = null;
							for (Component component : rightSidePanel
									.getComponents()) {
								if (component.isVisible() == true) {
									selectedCard = (QuestView) component;

									switch (selectedCard.introduceYourself()) {
									case "TextQuest":
										newQuest = caseTextQuest();
										break;
									case "MultipleChoiceQuest":
										newQuest = caseMultipleChioceQuest();
										break;
									case "DecisionQuest":
										newQuest = caseDecisionQuest();
										break;
									case "FieldQuest":
										newQuest = caseFieldQuest();
										break;
									case "OrderQuest":
										newQuest = caseOrderQuest();
										break;
									}
									GetGeneralQuestFields(newQuest,
											selectedCard);
								}
							}
							newQuest.incrementId();
							campaignRef.addQuiz(newQuest);
							campaignRef.createdTrue();
						} else {
							newQuest = campaignRef.getQuizes().get(quizIndex);
							for (Component comp : rightSidePanel
									.getComponents()) {
								if (comp.isVisible() == true) {
									switch (newQuest.getQuestType()) {
									case CHOICEQUEST:
										caseChoiceQuest(newQuest, comp);
										break;
									case DECISIONQUEST:
										caseDecisionQuest(newQuest, comp);
										break;
									case FIELDQUEST:
										caseFieldQuest(newQuest, comp);
										break;
									case ORDERQUEST:
										caseOrderQuest(newQuest, comp);
										break;
									case TEXTQUEST:
										caseTextQuest(newQuest, comp);
										break;
									}
									GetGeneralQuestFields(newQuest,
											selectedCard);
								}
							}
							campaignRef.editedTrue();
						}
						System.out.println(campaignRef.getQuizes().get(0)
								.getQuestName());

						dispose();
					}

					private void caseTextQuest(QuestPoint newQuest,
							Component comp) {
						selectedCard = (TextQuestView) comp;
						GetTextQuestFields((TextQuest) newQuest,
								(TextQuestView) selectedCard);
					}

					private void caseOrderQuest(QuestPoint newQuest,
							Component comp) {
						selectedCard = (OrderQuestView) comp;
						GetOrderQuestFields((OrderQuest) newQuest,
								(OrderQuestView) selectedCard);
					}

					private void caseFieldQuest(QuestPoint newQuest,
							Component comp) {
						selectedCard = (FieldQuestView) comp;
						GetFieldQuestFields((FieldQuest) newQuest,
								(FieldQuestView) selectedCard);
					}

					private void caseDecisionQuest(QuestPoint newQuest,
							Component comp) {
						selectedCard = (DecisionQuestView) comp;
						GetDecisionQuestFields((DecisionQuest) newQuest,
								(DecisionQuestView) selectedCard);
					}

					private void caseChoiceQuest(QuestPoint newQuest,
							Component comp) {
						selectedCard = (MultipleChoiceQuestView) comp;
						GetMultipleChoiceQuestFields((ChoiceQuest) newQuest,
								(MultipleChoiceQuestView) selectedCard);
					}

					private QuestPoint caseOrderQuest() {
						QuestPoint newQuest;
						newQuest = QuestFactory
								.createQuest(QuestType.ORDERQUEST);
						GetOrderQuestFields((OrderQuest) newQuest,
								(OrderQuestView) selectedCard);
						return newQuest;
					}

					private QuestPoint caseFieldQuest() {
						QuestPoint newQuest;
						newQuest = (FieldQuest) QuestFactory
								.createQuest(QuestType.FIELDQUEST);
						GetFieldQuestFields((FieldQuest) newQuest,
								(FieldQuestView) selectedCard);
						return newQuest;
					}

					private QuestPoint caseDecisionQuest() {
						QuestPoint newQuest;
						newQuest = (DecisionQuest) QuestFactory
								.createQuest(QuestType.DECISIONQUEST);
						GetDecisionQuestFields((DecisionQuest) newQuest,
								(DecisionQuestView) selectedCard);
						return newQuest;
					}

					private QuestPoint caseMultipleChioceQuest() {
						QuestPoint newQuest;
						newQuest = (ChoiceQuest) QuestFactory
								.createQuest(QuestType.CHOICEQUEST);
						GetMultipleChoiceQuestFields((ChoiceQuest) newQuest,
								(MultipleChoiceQuestView) selectedCard);
						return newQuest;
					}

					private QuestPoint caseTextQuest() {
						QuestPoint newQuest;
						newQuest = (TextQuest) QuestFactory
								.createQuest(QuestType.TEXTQUEST);
						GetTextQuestFields((TextQuest) newQuest,
								(TextQuestView) selectedCard);
						return newQuest;
					}
				});

			}
		});

		rightSidePanel.add(fieldView, "Zagadka terenowa");
		rightSidePanel.add(textView, "Zagadka tekstowa");
		rightSidePanel.add(choiceView, "Zagadka wielokrotnego wyboru");
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

	private void ZipPacking(QuestPoint newQuest) {
		ZipPacker zip = new ZipPacker("./Paczka/paczka.zip");
		for (int i = 0; i < newQuest.getPicturePaths().size(); i++) {
			try {
				zip.addFile(newQuest.getPicturePaths().get(i));
			} catch (IOException ex) {
				Logger.getLogger(NewQuizView.class.getName()).log(Level.SEVERE,
						null, ex);
			}
		}

		for (int i = 0; i < newQuest.getSoundPaths().size(); i++) {
			try {
				zip.addFile(newQuest.getSoundPaths().get(i));
			} catch (IOException ex) {
				Logger.getLogger(NewQuizView.class.getName()).log(Level.SEVERE,
						null, ex);
			}
		}

		try {
			zip.addFile("Config.xml");
		} catch (IOException ex) {
			Logger.getLogger(NewQuizView.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		zip.closeZip();
	}

	private void GetGeneralQuestFields(QuestPoint newQuest, QuestView questView) {
		if (newQuest.getPicturePaths().size() != 0
				&& newQuest.getSoundPaths().size() != 0) {
			newQuest.setPicturePaths(rewriteJListToArrayList(selectedCard.pics));
			newQuest.setSoundPaths(rewriteJListToArrayList(selectedCard.sounds));
		}
		newQuest.setQuestDescription(selectedCard.paragraphList);
		newQuest.setQuestTimeout(Integer.parseInt(timeoutField.getText()));
		validateName(newQuest);

		validatePoints(newQuest);

		newQuest.setPostNote(selectedCard.postNote.getText());
		newQuest.setPreNote(selectedCard.preNote.getText());
	}

	private void validatePoints(QuestPoint newQuest) {
		try {
			newQuest.setPoints(Integer.parseInt(this.points.getText()));
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Podaj ilosc punktow");
			throw new EmptyStackException();
		}
	}

	private void validateName(QuestPoint newQuest) {
		if (!tfQuizName.getText().equals("")) {
			newQuest.setQuestName(tfQuizName.getText());
		} else {

			JOptionPane.showMessageDialog(null, "Podaj nazwę zagadki");
			throw new NoDataInFieldException();
		}
	}

	private void GetTextQuestFields(TextQuest newQuest, TextQuestView questView) {

		// newQuest.setGoTo(questView.textGoTo.getText());
		newQuest.setQuestAnswer(questView.textAnswer);
	}

	private void GetDecisionQuestFields(DecisionQuest newQuest,
			DecisionQuestView questView) {
		newQuest.setDecisionAnswer(questView.getAnswers(),
				questView.getGoToList());
	}

	private void GetMultipleChoiceQuestFields(ChoiceQuest newQuest,
			MultipleChoiceQuestView questView) {
		newQuest.setQuestAnswer(questView.getAnswers(),
				questView.getAnswersBooleans());
	}

	private void GetOrderQuestFields(OrderQuest newQuest,
			OrderQuestView questView) {
		newQuest.setQuestAnswer(questView.getAnswers());
	}

	private void GetFieldQuestFields(FieldQuest newQuest,
			FieldQuestView questView) {
		try {
			newQuest.setYCoordinate(Double.parseDouble(questView.latitudeField
					.getText()));
			newQuest.setXCoordinate(Double.parseDouble(questView.longitudeField
					.getText()));
			newQuest.setXWidth(Double.parseDouble(questView.widthField
					.getText()));
			newQuest.setYWidth(Double.parseDouble(questView.heightField
					.getText()));
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Wybierz punkt na mapie");
			throw new NoDataInFieldException();
		}
	}

	private ArrayList rewriteJListToArrayList(JList list) {
		ArrayList newList = new ArrayList();
		for (int i = 0; i < list.getModel().getSize(); i++) {
			if (list.getModel().getElementAt(i) != null)
				newList.add(list.getModel().getElementAt(i));
		}
		return newList;
	}

	private DefaultListModel rewriteArrayListToJList(ArrayList<String> arr,
			DefaultListModel<String> model) {

		model.removeAllElements();
		for (int i = 0; i < arr.size(); i++) {
			if (arr.get(i) != null)
				model.addElement(arr.get(i));
		}

		return model;
	}
}
