package Editor;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import UserRegistration.GetRequest;
import UserRegistration.UserDTO;
import UserRegistration.UserDataRegister;


public class UserProperties extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	public UserProperties() {
		super("User Properties");
		
		GetRequest getRequest = new GetRequest();
		List<UserDTO> usersList = null;
		try {
			usersList = getRequest.getData();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
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
						new UserDataRegister();
					}
				});
			}
		});
		btnRegisterNewUser.setBounds(257, 238, 164, 25);
		contentPane.add(btnRegisterNewUser);

		tableModel = new DefaultTableModel(new String[] {"login" , "password", "id", "role"}, 7);
		
		table = new JTable(tableModel);
		table.setBounds(74, 12, 312, 154);
		contentPane.add(table);
		
		for (UserDTO u : usersList ) {
			tableModel.addRow(new String [] {"asdas", "asdasd"});
		}
		
		
		setVisible(true);
	}
}
