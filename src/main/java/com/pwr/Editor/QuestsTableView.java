package com.pwr.Editor;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.pwr.UserRegistration.Requests;

public class QuestsTableView extends JFrame {
	private Requests requests;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	private int colNum, rowNum;
	
	public QuestsTableView() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		Toolkit toolkt = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkt.getScreenSize();
		this.setLocation(screenSize.width/4, screenSize.height/4);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		metoda();
		/*addTable();

		addRowToTable(questList);

		setVisible(true);*/
	}

	private void metoda() {
		try {
			requests = new Requests();
			requests.getAllQuests();
		} catch (Exception except) {
			except.printStackTrace();
		}
				
	}
	
	
}
