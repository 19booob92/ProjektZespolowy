package com.pwr.UserView;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.UnknownHostException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pwr.UserRegistration.QuestDTO;
import com.pwr.UserRegistration.Requests;

@Component
public class QuestTableView extends JFrame {
	
	private JPanel downPanel;
	private JPanel tablePanel;
	private JButton deleteQuest;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	private int colNum, rowNum;
	private List<QuestDTO> questList = null;
	private JScrollPane scrollPanel;
	
	@Autowired
	public QuestTableView(final Requests requests) {
		super("Zagadki");

		questList = null;
		try {
			questList = requests.getAllQuests();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Sprawdz polaczenie z internetem");
		}

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(400, 200, 452, 361);
		setSize(new Dimension(400, 260));
		Toolkit toolkt = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkt.getScreenSize();
		this.setLocation(screenSize.width / 4, screenSize.height / 4);

		
		contentPane = new JPanel();
		setLayout(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		add(contentPane);

		deleteQuest = new JButton("Usun zagadke");

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

		deleteQuest.setBounds(257, 250, 164, 25);
		
		addTable();
		addRowToTable(questList);
		downPanel = new JPanel(new FlowLayout());
		downPanel.add(deleteQuest);
		contentPane.add(downPanel, BorderLayout.SOUTH);
	}

	// kiedy User będzie miał w tabeli maila, layout się ułoży
	private void addTable() {
		tableModel = new DefaultTableModel(new String[] { "id", "czas domyslny",
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
		
		contentPane.setLayout(new BorderLayout());
		table.setBounds(400, 22, 0, 0);
		tablePanel = new JPanel(new BorderLayout());

		tablePanel.add(table.getTableHeader(), BorderLayout.NORTH);
		tablePanel.add(table, BorderLayout.CENTER);
		scrollPanel = new JScrollPane(tablePanel);
		contentPane.add(scrollPanel);
	}
	private void addRowToTable(List<QuestDTO> usersList) {
		for (QuestDTO quest : usersList) {
			tableModel.addRow(quest.toArray());
		}
	}
}
