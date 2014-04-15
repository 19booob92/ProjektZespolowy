package com.pwr.Editor;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
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

import com.pwr.UserRegistration.Requests;
import com.pwr.UserRegistration.UserDTO;
import com.pwr.UserRegistration.UserDataRegister;

public class UserProperties extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	private int colNum, rowNum;
	@Autowired
	private Requests requests;
	private List<UserDTO> usersList = null;
	
	public UserProperties() {
		super("User Properties");
		
		usersList = null;
		try {
			usersList = requests.getAllUsers();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		Toolkit toolkt = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkt.getScreenSize();
		this.setLocation(screenSize.width/4, screenSize.height/4);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnRegisterNewUser = new JButton("Register new user");

		btnRegisterNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {

					@Override
					public void run() {
						UserDataRegister.getInstance();
					}
				});
			}
		});
		btnRegisterNewUser.setBounds(257, 238, 164, 25);
		contentPane.add(btnRegisterNewUser);

		addTable();

		addRowToTable(usersList);

		setVisible(true);
	}

	// kiedy User będzie miał w tabeli maila, layout się ułoży
	private void addTable() {
		tableModel = new DefaultTableModel(new String[] { "login", "e-mail",
				"points", "end time" }, 0);

		table = new JTable(tableModel);

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					JTable target = (JTable) e.getSource();
					rowNum = target.getSelectedRow();
					colNum = target.getSelectedColumn();
					EventQueue.invokeLater(new Runnable() {

						@Override
						public void run() {
							//UserDetailsView.getUserDetailsViewInstance((String) tableModel
							//		.getValueAt(rowNum, 0), null);
						}

					});
				}
			}
		});

		table.setBounds(74, 12, 312, 154);
		contentPane.setLayout(new BorderLayout());
		contentPane.add(table.getTableHeader(), BorderLayout.NORTH);
		contentPane.add(table, BorderLayout.CENTER);
	}

	private void addRowToTable(List<UserDTO> usersList) {
		for (UserDTO user : usersList) {
			if (user.getUserGame() != null) {
				tableModel.addRow(user.toArray());
			} else {
				tableModel.addRow(user.toArrayOnlyUser());
			}
		}
	}
}
