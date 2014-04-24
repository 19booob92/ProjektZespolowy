package com.pwr.NewInterface;

import javax.swing.JPanel;

import com.pwr.Quest.Campaign;
import com.pwr.Quest.QuestFactory;
import com.pwr.Quest.QuestPoint;
import com.pwr.Quest.QuestType;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class DecisionQuestView extends QuestView implements DescribeView{

	private QuestPoint quest;
	private JTable table;
	private JPanel tablePanel;
	private JSplitPane splitPane;
	private JTableHeader header;
	protected DefaultTableModel tableModel;
	private int colNum, rowNum;
	
	private final JButton btnAdd = new JButton("Dodaj");
	private JButton btnDel = new JButton("Usuń");
	
	
	public DecisionQuestView() {
		super();
		
		this.add(new JLabel("Decision"));
		quest = QuestFactory.createQuest(QuestType.DECISIONQUEST);
		addAnswersTable();
	}
	
	private void addAnswersTable() {
		tablePanel = new JPanel();
		tableModel = new DefaultTableModel(new String[] { "Nr", "Treść odpowiedzi","Kolejna zagadka"}, 0);
		
		tablePanel.setBounds(23, 286, 518, 203);
		tablePanel.setLayout(new BorderLayout());
		table = new JTable(tableModel);
		header = table.getTableHeader();
		
		table.getColumn("Nr").setMinWidth(50);
		table.getColumn("Nr").setMaxWidth(50);
		table.getColumn("Kolejna zagadka").setMinWidth(100);
		table.getColumn("Kolejna zagadka").setMaxWidth(100);
		
		table.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					JTable target = (JTable) e.getSource();
					rowNum = target.getSelectedRow();
					colNum = target.getSelectedColumn();
					EventQueue.invokeLater(new Runnable() {

						@Override
						public void run() {

						}

					});
				}
			}
		});
		add(tablePanel);
		tablePanel.add(header, BorderLayout.NORTH);
		tablePanel.add(table, BorderLayout.CENTER);
		
		splitPane = new JSplitPane();
		tablePanel.add(splitPane, BorderLayout.SOUTH);
		
		splitPane.setLeftComponent(btnAdd);
		splitPane.setRightComponent(btnDel);
		
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tempAnswer = "";
				createDialog(tempAnswer);
			}
		});	
	}
	
	private void createDialog(String answ) {
		  final JDialog dialog = new JDialog();
		  dialog.setSize(500, 250);
		  dialog.getContentPane().setLayout(null);
		  
		  dialog.setLocationRelativeTo(this);
		  dialog.setLayout(null);
		  
		  JLabel lblAnswerLabel = new JLabel("Wprowadź odpowiedź");
		  lblAnswerLabel.setBounds(25, 25, 134, 14);
		  dialog.add(lblAnswerLabel);
		  
		  final JTextField tfAnswer = new JTextField();
		  tfAnswer.setBounds(25, 49, 399, 30);
		  dialog.add(tfAnswer);
		  tfAnswer.setColumns(10);
		  
		  //tu bedzie JCombo, z ktorego mozna bedzie wybrac numer zagadki obiektu Campaign
		  
		  JLabel lblGoToLabel = new JLabel("Wprowadź numer kolejnej zagadki");
		  lblGoToLabel.setBounds(25, 85, 134, 14);
		  dialog.add(lblGoToLabel);
		 
		  final JTextField tfGoTo = new JTextField();
		  tfGoTo.setBounds(25, 100, 399, 30);
		  dialog.add(tfGoTo);
		  tfGoTo.setColumns(10);
		  
		  

		  JButton btnOk = new JButton("Ok");
		  btnOk.setBounds(335, 137, 89, 23);
		  btnOk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String tempAnswer = tfAnswer.getText();
					String goToNumber = tfGoTo.getText();
					tableModel.addRow(new Object [] {"",tempAnswer,goToNumber});
					dialog.dispose();
				}
			});
		  dialog.add(btnOk);
		  dialog.setVisible(true);
		}
	

	@Override
	public String introduceYourself() {
		return "DecisionQuest";
	}

	public ArrayList<String> getAnswers() {
		ArrayList collectedAnswers = new ArrayList();
		
		for (int i = 0; i < table.getModel().getRowCount(); i++) {
			collectedAnswers.add(tableModel.getValueAt(i, 1));
		}
		return collectedAnswers;
	}

	public ArrayList<String> getGoToList() {
		ArrayList collectedAnswers = new ArrayList();
		
		for (int i = 0; i < table.getModel().getRowCount(); i++) {
			collectedAnswers.add(tableModel.getValueAt(i, 2));
		}
		return collectedAnswers;
	}
}
