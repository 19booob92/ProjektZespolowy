package Editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import UserRegistration.DeleteRequest;
import UserRegistration.UpdateRequest;
import UserRegistration.UserDTO;

public class UserDetailsView extends JFrame {

	private JPanel contentPane;
	private DeleteRequest deleteRequest;
	private UpdateRequest updateRequest;
	private static volatile UserDetailsView userDetailsView;
	private JTextField login;
	private JTextField pass;
	private JTextField email;

	private UserDetailsView(final String value) {
		setBounds(100, 100, 250, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JButton btnDeleteUser = new JButton("Delete user");
		btnDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteRequest = new DeleteRequest();
				try {
					deleteRequest.deleteUser(value, "/deleteUser");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnDeleteUser.setBounds(121, 226, 117, 25);
		contentPane.add(btnDeleteUser);

		JButton btnDeleteGame = new JButton("Delete game");
		btnDeleteGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				deleteRequest = new DeleteRequest();
				try {
					deleteRequest.deleteUser(value, "/deleteGame");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnDeleteGame.setBounds(121, 189, 117, 25);
		contentPane.add(btnDeleteGame);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				UserDTO userDTO = new UserDTO();
				userDTO.setLogin(login.getText());
				userDTO.setPassword(pass.getText());
				try {
						updateRequest = new UpdateRequest(userDTO);
						updateRequest.update(value);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnUpdate.setBounds(0, 189, 117, 25);
		contentPane.add(btnUpdate);

		login = new JTextField();
		login.setBounds(55, 29, 114, 19);
		contentPane.add(login);
		login.setColumns(10);

		pass = new JTextField();
		pass.setBounds(55, 91, 114, 19);
		contentPane.add(pass);
		pass.setColumns(10);

		email = new JTextField();
		email.setBounds(55, 158, 114, 19);
		contentPane.add(email);
		email.setColumns(10);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(51, 12, 70, 15);
		contentPane.add(lblLogin);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(52, 77, 70, 15);
		contentPane.add(lblPassword);

		JLabel lblEmail = new JLabel("E-Mail");
		lblEmail.setBounds(52, 140, 70, 15);
		contentPane.add(lblEmail);
		setVisible(true);
	}

	public static UserDetailsView getUserDetailsViewInstance(String value) {
		if (userDetailsView == null || !userDetailsView.isDisplayable()) {
			userDetailsView = new UserDetailsView(value);
			return userDetailsView;
		} else {
			return null;
		}
	}
}
