package com.pwr.Editor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pwr.UserRegistration.QuestDTO;
import com.pwr.UserRegistration.Requests;

@Component
public class QuestTableView extends JFrame {
	
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	private int colNum, rowNum;
	private List<QuestDTO> questList = null;

	@Autowired
	public QuestTableView(final Requests requests) {
		super("Quests");

		questList = null;
		try {
			questList = requests.getAllQuests();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 452, 361);
		Toolkit toolkt = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkt.getScreenSize();
		this.setLocation(screenSize.width / 4, screenSize.height / 4);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton deleteQuest = new JButton("Usun quest");

		deleteQuest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					requests.delete((String) tableModel.getValueAt(rowNum, 0), "/deleteQuest");
					tableModel.removeRow(rowNum);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		deleteQuest.setBounds(257, 238, 164, 25);
		contentPane.add(deleteQuest);

		
		addTable();

		addRowToTable(questList);
	}

	// kiedy User będzie miał w tabeli maila, layout się ułoży
	private void addTable() {
		tableModel = new DefaultTableModel(new String[] { "id", "czas domysny",
				"domyslne punkty", "nazwa" }, 0);

		table = new JTable(tableModel);

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					JTable target = (JTable) e.getSource();
					rowNum = target.getSelectedRow();
					colNum = target.getSelectedColumn();
				}
			}
		});

		table.setBounds(74, 12, 312, 154);
		contentPane.setLayout(new BorderLayout());
		contentPane.add(table.getTableHeader(), BorderLayout.NORTH);
		contentPane.add(table, BorderLayout.CENTER);
	}

	private void addRowToTable(List<QuestDTO> usersList) {
		for (QuestDTO quest : usersList) {
			tableModel.addRow(quest.toArray());
		}
	}
}
