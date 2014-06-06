package com.pwr.QuestView;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.swingx.calendar.SingleDaySelectionModel;
import org.jdesktop.swingx.JXDatePicker;

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


import com.pwr.Other.DateTimePicker;
import com.pwr.Other.NoDataInFieldException;
import com.pwr.Package.ZipPacker;
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
	static Toolkit toolkit;
	private Date datePickerDate;
	private DateTimePicker dateTimePicker;
	private JButton btnSaveQuiz;
	private JLabel lblTimeout;
	private JLabel lblType;
	private JLabel lblWrong;
	private JLabel lblPoints;
	private JLabel lblDate;
	private static final int PANEL_WIDTH = 1000;
	private static final int PANEL_HEIGHT = 700;

	private static final int WINDOW_WIDTH = 1000;
	private static final int WINDOW_HEIGHT = 700;

	protected JTextField wrong;
	private JTextField timeoutField;
	private JTextField tfQuizName;
	protected JTextField points;

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

	private String thisName;
	
	private static NewQuizView instanceOfNewQuizView = null;
	
	private NewQuizView(Campaign campaign, int qInd) {
		super();
		campaignRef = campaign;
		quizIndex = qInd;
		initWindow();
		fillFieldsWithQuizData();
		pack();
		System.out.println(dateTimePicker.getDate());
	}

	public static NewQuizView getInstance(Campaign campaign, int qInd) {
		
		if (instanceOfNewQuizView == null) {
			instanceOfNewQuizView = new NewQuizView(campaign, qInd);
		}
		return instanceOfNewQuizView;
	}

	public static NewQuizView getInstance(Campaign campaign) {
		
		if (instanceOfNewQuizView == null) {
			instanceOfNewQuizView = new NewQuizView(campaign);
		}
		return instanceOfNewQuizView;
	}
	/**
	 * @wbp.parser.constructor
	 */
	private NewQuizView(Campaign campaign) {
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

		datePickerDate = new Date();
		dateTimePicker = new DateTimePicker(datePickerDate);
		dateTimePicker.setBounds(24, 205, 226, 27);
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		dateTimePicker.setFormats(dateFormat);
		dateTimePicker.setTimeFormat(DateFormat
				.getTimeInstance(DateFormat.MEDIUM));
		dateTimePicker.setDate(datePickerDate);
		leftSidePanel.add(dateTimePicker);

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
		QuestPoint q = campaignRef.getQuizes().get(quizIndex - 1);
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
		System.out.println(dateTimePicker.getDate());
	}

	private void fillWithGeneralData(QuestPoint q, QuestView view) {

		view.preNote.setText(q.getPreNote());
		view.postNote.setText(q.getPostNote());
		this.tfQuizName.setText(q.getQuestName());
		thisName = this.tfQuizName.getText();
		this.timeoutField.setText(Integer.toString(q.getQuestTimeout()));
		rewriteArrayListToJList(q.getPicturePaths(), view.picsListModel);
		rewriteArrayListToJList(q.getSoundPaths(), view.soundsListModel);
		points.setText(Integer.toString(q.getPoints()));
		SimpleDateFormat simple = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		try {

			datePickerDate = simple.parse(q.getDate());
			dateTimePicker.setDate(simple.parse(q.getDate()));
			dateTimePicker.updateUI();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(dateTimePicker.getDate());
		view.paragraph = q.getParagraph();
	}

	private void fillWithFieldQuestData(FieldQuest q) {
		fieldView.heightField.setText(Double.toString(q.getHeight()));
		fieldView.widthField.setText(Double.toString(q.getWidth()));
		fieldView.latitudeField.setText(Double.toString(q.getYCoordinate()));
		fieldView.longitudeField.setText(Double.toString(q.getXCoordinate()));
		fieldView.googlePanel.setMapPoint(q.getXCoordinate(),
				q.getYCoordinate(), q.getWidth(), q.getHeight());
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
//		for (int i = 0; i < q.getQuestAnswer().size(); i++) {
//			String qname = "";
//			for (int j = 0; j < ProjectMainView.getCampaign().getQuizes()
//					.size(); j++) {
//				if (ProjectMainView.getCampaign().getQuizes().get(j).getId() == Integer
//						.parseInt(q.getGoToList().get(i))) {
//					qname = ProjectMainView.getCampaign().getQuizes().get(j).getQuestName();
//					break;
//				}
//			}
//			decisionView.tableModel.addRow(new Object[] { "",
//					q.getQuestAnswer().get(i), q.getGoToList().get(i),
//					qname });
//		}
		for (int i = 0; i < q.getQuestAnswer().size(); i++) {
			decisionView.tableModel.addRow(new Object[] { "",
					q.getQuestAnswer().get(i), q.getGoToList().get(i), q.getGoToList().get(i)});
		}
	}

	private void createLeftSidePanel() {

		JLabel lblTitle = new JLabel("Tytuł");
		lblTitle.setBounds(24, 11, 46, 14);
		leftSidePanel.add(lblTitle);

		String[] quizTypes = { "Zagadka terenowa", "Zagadka tekstowa",
				"Zagadka wielokrotnego wyboru", "Zagadka uporządkowania",
				"Zagadka decyzji" };

		tfQuizName = new JTextField();
		tfQuizName.setBounds(24, 36, 226, 27);
		leftSidePanel.add(tfQuizName);
		tfQuizName.setColumns(10);

		timeoutField = new JTextField("0");
		timeoutField.setBounds(24, 95, 226, 27);
		leftSidePanel.add(timeoutField);
		timeoutField.setColumns(10);

		lblTimeout = new JLabel("Timeout");
		lblTimeout.setBounds(24, 78, 46, 14);
		leftSidePanel.add(lblTimeout);

		final JComboBox questTypeCombo = new JComboBox(quizTypes);
		questTypeCombo.setBounds(24, 321, 226, 27);
		leftSidePanel.add(questTypeCombo);

		lblType = new JLabel("Typ");
		lblType.setBounds(24, 296, 46, 14);
		leftSidePanel.add(lblType);

		btnSaveQuiz = new JButton("Zapisz quest");
		btnSaveQuiz.setBounds(24, 359, 226, 23);
		leftSidePanel.add(btnSaveQuiz);

		wrong = new JTextField();
		leftSidePanel.add(wrong);
		wrong.setBounds(24, 258, 226, 27);

		points = new JTextField();
		leftSidePanel.add(points);
		points.setBounds(24, 148, 226, 27);

		lblPoints = new JLabel("Punkty");
		leftSidePanel.add(lblPoints);
		lblPoints.setBounds(24, 133, 46, 14);

		lblDate = new JLabel("Data");
		leftSidePanel.add(lblDate);
		lblDate.setBounds(24, 186, 46, 14);

		lblWrong = new JLabel("Zagadka kara");
		leftSidePanel.add(lblWrong);
		lblWrong.setBounds(24, 243, 105, 14);

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
								newQuest = whichTestCreate(newQuest, component);
							}
							// newQuest.incrementId();
							campaignRef.addQuiz(newQuest);
							campaignRef.createdTrue();
						} else {
							newQuest = campaignRef.getQuizes().get(
									quizIndex - 1);
							for (Component comp : rightSidePanel
									.getComponents()) {
								choiceQuestType(newQuest, comp);
							}
							campaignRef.setQuiz(newQuest, quizIndex - 1);
							campaignRef.editedTrue();
						}
						dispose();
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

	private void choiceQuestType(QuestPoint newQuest, Component comp) {
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
			GetGeneralQuestFields(newQuest, selectedCard);
		}
	}

	private QuestPoint whichTestCreate(QuestPoint newQuest, Component component) {
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
			GetGeneralQuestFields(newQuest, selectedCard);
		}
		return newQuest;
	}

	private void caseTextQuest(QuestPoint newQuest, Component comp) {
		selectedCard = (TextQuestView) comp;
		GetTextQuestFields((TextQuest) newQuest, (TextQuestView) selectedCard);
	}

	private void caseOrderQuest(QuestPoint newQuest, Component comp) {
		selectedCard = (OrderQuestView) comp;
		GetOrderQuestFields((OrderQuest) newQuest,
				(OrderQuestView) selectedCard);
	}

	private void caseFieldQuest(QuestPoint newQuest, Component comp) {
		selectedCard = (FieldQuestView) comp;
		GetFieldQuestFields((FieldQuest) newQuest,
				(FieldQuestView) selectedCard);
	}

	private void caseDecisionQuest(QuestPoint newQuest, Component comp) {
		selectedCard = (DecisionQuestView) comp;
		GetDecisionQuestFields((DecisionQuest) newQuest,
				(DecisionQuestView) selectedCard);
	}

	private void caseChoiceQuest(QuestPoint newQuest, Component comp) {
		selectedCard = (MultipleChoiceQuestView) comp;
		GetMultipleChoiceQuestFields((ChoiceQuest) newQuest,
				(MultipleChoiceQuestView) selectedCard);
	}

	private QuestPoint caseOrderQuest() {
		QuestPoint newQuest;
		newQuest = QuestFactory.createQuest(QuestType.ORDERQUEST);
		GetOrderQuestFields((OrderQuest) newQuest,
				(OrderQuestView) selectedCard);
		return newQuest;
	}

	private QuestPoint caseFieldQuest() {
		QuestPoint newQuest;
		newQuest = (FieldQuest) QuestFactory.createQuest(QuestType.FIELDQUEST);
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
		newQuest = (TextQuest) QuestFactory.createQuest(QuestType.TEXTQUEST);
		GetTextQuestFields((TextQuest) newQuest, (TextQuestView) selectedCard);
		return newQuest;
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

		/*
		 * try { zip.addFile("temp"+java.io.File.separator+"Config.xml"); }
		 * catch (IOException ex) {
		 * Logger.getLogger(NewQuizView.class.getName()).log(Level.SEVERE, null,
		 * ex); }
		 */
		zip.closeZip();
	}

	private void GetGeneralQuestFields(QuestPoint newQuest, QuestView questView) {
		newQuest.setSoundNarration(selectedCard.comboBoxNarration.getSelectedIndex()-1);
		newQuest.setPicturePaths(rewriteJListToArrayList(selectedCard.pics));
		newQuest.setSoundPaths(rewriteJListToArrayList(selectedCard.sounds));
		newQuest.setParagraph(selectedCard.paragraph);
		newQuest.setQuestTimeout(Integer.parseInt(timeoutField.getText()));
		SimpleDateFormat simpleFormat = new SimpleDateFormat(
				"dd-MM-yyyy HH:mm:ss");
		Date dsa = dateTimePicker.getDate();
		newQuest.setDate(simpleFormat.format(dsa));
		System.out.println(newQuest.getDate());
		validateName(newQuest);
		validateData(newQuest);
		validatePoints(newQuest);
		// newQuest.setDate(date.getText());
		newQuest.setPostNote(selectedCard.postNote.getText());
		newQuest.setPreNote(selectedCard.preNote.getText());
	}

	private void validateData(QuestPoint newQuest) {
		if (!newQuest
				.getDate()
				.matches(
						"[0-3][0-9]-[0-1][0-9]-[0-9]{4} [0-2][0-9]:[0-6][0-9]:[0-6][0-9]")) {
			JOptionPane.showMessageDialog(null,
					"Podaj date w formacie dd-MM-rrrr hh:mm:ss");
			throw new NoDataInFieldException();
		}
	}

	private void validatePoints(QuestPoint newQuest) {
		try {
			newQuest.setPoints(Integer.parseInt(this.points.getText()));
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Podaj ilosc punktow");
			throw new EmptyStackException();
		}
	}
	// jeśli tworzymy quesr musi on mieć indywidualną nazwę,
	// jeśli edytujemy nazwa może się powtarzać
	private void validateName(QuestPoint newQuest) {
		if (!tfQuizName.getText().equals("")) {
			// jesli jest edytowana
			if (this.tfQuizName.getText().equals(thisName)) {
				newQuest.setQuestName(tfQuizName.getText());
			// jesli jest tworzona nowa zagadka
			} else {
				if (!campaignRef.getQuizesNames().contains(
						tfQuizName.getText())) {
					newQuest.setQuestName(tfQuizName.getText());
				} else {
					JOptionPane.showMessageDialog(null, "Podaj inną nazwę zagadki");
					throw new NoDataInFieldException();
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "Podaj inną nazwę zagadki");
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
