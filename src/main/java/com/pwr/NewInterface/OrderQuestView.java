package com.pwr.NewInterface;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OrderQuestView extends QuestView implements DescribeView{

	private QuestPoint quest;
	private JTable table;
	private DefaultTableModel tableModel;
	private JTableHeader header;
	private int colNum, rowNum;
	private JPanel tablePanel = new JPanel();
	private final JButton btnUp = new JButton("W górę");
	private final JButton btnDown = new JButton("W dół");
	private final JButton btnAdd = new JButton("Dodaj");

	public OrderQuestView() {
		super();
		setLayout(null);
		addAnswersTable();
		colNum = 0;
		rowNum = 0;
		quest = QuestFactory.createQuest(QuestType.ORDERQUEST);
	}

	private void addAnswersTable() {
		tableModel = new DefaultTableModel(new String[] { "Nr", "Treść odpowiedzi","Operacja"}, 0);
		
		tablePanel.setBounds(23, 286, 518, 203);
		tablePanel.setLayout(new BorderLayout());
		table = new JTable(tableModel);
		header = table.getTableHeader();
		
		table.getColumn("Nr").setMinWidth(50);
		table.getColumn("Nr").setMaxWidth(50);
		table.getColumn("Operacja").setMinWidth(80);
		table.getColumn("Operacja").setMaxWidth(80);
		
		//Moze bedzie kolumna JButtonow
		tableModel.addRow(new Object [] {"1","Odp1","Add"});
		tableModel.addRow(new Object [] {"2","Odp2","Add"});
		tableModel.addRow(new Object [] {"3","Odp3","Add"});
		
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
		
		JSplitPane splitPane = new JSplitPane();
		tablePanel.add(splitPane, BorderLayout.SOUTH);
		
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
				
			}
		});
		btnAdd.setBounds(551, 309, 89, 23);
		
		add(btnAdd);
	}
	
	private void moveUp(){
		int[] rows = table.getSelectedRows();
	    DefaultTableModel model =  (DefaultTableModel)table.getModel();
	    model.moveRow(rows[0],rows[rows.length-1],rows[0]-1);
	    table.setRowSelectionInterval(rows[0]-1, rows[rows.length-1]-1);
	}
	private void moveDown(){
		int[] rows = table.getSelectedRows();
	    DefaultTableModel model =  (DefaultTableModel)table.getModel();
	    model.moveRow(rows[0],rows[rows.length-1],rows[0]+1);
	    table.setRowSelectionInterval(rows[0]+1, rows[rows.length-1]+1);
	}
	
	@Override
	public String introduceYourself() {
		return "OrderQuest";
	}
}
