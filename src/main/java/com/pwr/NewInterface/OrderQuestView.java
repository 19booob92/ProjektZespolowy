package com.pwr.NewInterface;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.pwr.Quest.Campaign;
import com.pwr.Quest.QuestFactory;
import com.pwr.Quest.QuestPoint;
import com.pwr.Quest.QuestType;

import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JSplitPane;
import javax.swing.JButton;

import org.springframework.ui.Model;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class OrderQuestView extends QuestView implements DescribeView{
	private QuestPoint quest;

	private JPanel tablePanel;
	private JSplitPane splitPane;
	private JTableHeader header;
	protected JTable table;
	protected DefaultTableModel tableModel;
	private int colNum, rowNum;
	private JScrollPane tableScrollPane;
	
	private final JButton btnUp = new JButton("W górę");
	private final JButton btnDown = new JButton("W dół");
	private final JButton btnAdd = new JButton("Dodaj");

	public OrderQuestView() {
		super();
		setLayout(null);
		addAnswersTable();
		colNum = 0;
		rowNum = 0;
		//quest = QuestFactory.createQuest(QuestType.ORDERQUEST);
	}

	private void addAnswersTable() {
		tablePanel = new JPanel();
		tableModel = new DefaultTableModel(new String[] { "Nr", "Treść odpowiedzi","Operacja"}, 0);
		
		tablePanel.setLayout(new BorderLayout());
		
		table = new JTable(tableModel);
		header = table.getTableHeader();
		
		table.getColumn("Nr").setMinWidth(50);
		table.getColumn("Nr").setMaxWidth(50);
		table.getColumn("Operacja").setMinWidth(80);
		table.getColumn("Operacja").setMaxWidth(80);
		
		//Moze bedzie kolumna JButtonow
		
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
		tablePanel.add(header, BorderLayout.NORTH);
		tablePanel.add(table, BorderLayout.CENTER);
		
		splitPane = new JSplitPane();
		tablePanel.add(splitPane, BorderLayout.SOUTH);
		
		tableScrollPane = new JScrollPane(tablePanel);
		tableScrollPane.setBounds(23, 286, 518, 203);
		add(tableScrollPane);
		
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				moveUp();
			}
		});
		splitPane.setLeftComponent(btnUp);
		
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				moveDown();
			}
		});
		splitPane.setRightComponent(btnDown);
		
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tempAnswer = JOptionPane.showInputDialog("Podaj odpowiedź",null);
				tableModel.addRow(new Object [] {"",tempAnswer,"Add"});
			}
		});
		btnAdd.setBounds(551, 309, 89, 23);
		
		add(btnAdd);
	}
	
	private void moveUp(){
		int[] rows = table.getSelectedRows();
	    DefaultTableModel model =  (DefaultTableModel)table.getModel();
	    if (model.getRowCount() >= 2)
	    {
	    	model.moveRow(rows[0],rows[rows.length-1],rows[0]-1);
	    	table.setRowSelectionInterval(rows[0]-1, rows[rows.length-1]-1);
	    }
	}
	private void moveDown(){
		int[] rows = table.getSelectedRows();
	    DefaultTableModel model =  (DefaultTableModel)table.getModel();
	    if (model.getRowCount() >= 2)
	    {
	    	model.moveRow(rows[0],rows[rows.length-1],rows[0]+1);
	    	table.setRowSelectionInterval(rows[0]+1, rows[rows.length-1]+1);
	    }
	}
	
	@Override
	public String introduceYourself() {
		return "OrderQuest";
	}
	
	public ArrayList getAnswers() {
		ArrayList collectedAnswers = new ArrayList<String>();
		for (int i = 0; i < table.getModel().getRowCount(); i++) {
			collectedAnswers.add(tableModel.getValueAt(i, 1));
		}
		return collectedAnswers;
	}
}
