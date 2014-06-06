package UserView;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


import com.pwr.MainView.ProjectMainView;
import com.pwr.UserRegistration.Requests;
import com.pwr.UserRegistration.UserDTO;

@Component
@Lazy(value = true)
public class UserDetailsView extends JFrame {

	@Autowired
	private ProjectMainView projectMainView;
	@Autowired
	private Requests requests;

	private JPanel contentPane;
	private static volatile UserDetailsView userDetailsView;
	private JTextField login;
	private JTextField pass;
	private JTextField email;
	private JButton btnDeleteAllDoneQuests;
	private JButton btnDeleteUser;
	private JButton btnCreateGameForUser;

	static final int STATUS_CONFLICT = 409;
	
	private static String userName;

	public UserDetailsView() {
		setBounds(100, 100, 534, 300);
		Toolkit toolkt = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkt.getScreenSize();
		this.setLocation(screenSize.width / 4, screenSize.height / 4);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		btnDeleteUser = new JButton("Delete user");
		btnDeleteAllDoneQuests = new JButton(
				"<html><body style=\"text-align:center;\">Usun wszystkie<br />zakonczone questy</body></html>");

		btnDeleteAllDoneQuests.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					requests.delete(userName, "/doneQuest/");
					projectMainView.updateTable();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		btnCreateGameForUser = new JButton("Stworz gre");

		btnCreateGameForUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (requests.createNewGame(userName) == STATUS_CONFLICT ) {
						JOptionPane.showMessageDialog(null, "Dla tego uzytkownika utworzono juz gre");
					} else {
						projectMainView.updateTable();
						UserDetailsView.this.show();
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		btnCreateGameForUser.setBounds(10, 132, 117, 25);
		getContentPane().add(btnCreateGameForUser);

		btnDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					requests.delete(userName, "/deleteUser");
					projectMainView.updateTable();
					UserDetailsView.this.show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnDeleteUser.setBounds(391, 90, 117, 25);
		btnDeleteAllDoneQuests.setBounds(38, 213, 196, 37);
		contentPane.add(btnDeleteUser);
		contentPane.add(btnDeleteAllDoneQuests);

		JButton btnDeleteGame = new JButton("Usun gre");
		btnDeleteGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					requests.delete(userName, "/deleteGame");
					projectMainView.updateTable();
					UserDetailsView.this.show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnDeleteGame.setBounds(137, 132, 117, 25);
		contentPane.add(btnDeleteGame);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UserDTO userDTO = new UserDTO();
				userDTO.setLogin(login.getText());
				userDTO.setPassword(pass.getText());
				try {
					requests.update(userName, userDTO);
					projectMainView.updateTable();
					updateThisViewData(userDTO.getLogin());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}

			private void updateThisViewData(String name) {
				setUserName(name);
			}
		});
		btnUpdate.setBounds(264, 90, 117, 25);
		contentPane.add(btnUpdate);

		login = new JTextField();
		login.setBounds(10, 30, 246, 30);
		contentPane.add(login);
		login.setColumns(10);

		pass = new JTextField();
		pass.setBounds(262, 30, 246, 30);
		contentPane.add(pass);
		pass.setColumns(10);

		email = new JTextField();
		email.setBounds(10, 87, 244, 30);
		contentPane.add(email);
		email.setColumns(10);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(20, 11, 70, 15);
		contentPane.add(lblLogin);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(262, 11, 70, 15);
		contentPane.add(lblPassword);

		JLabel lblEmail = new JLabel("E-Mail");
		lblEmail.setBounds(20, 68, 70, 15);
		contentPane.add(lblEmail);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setLoginField(String userName2) {
		login.setText(userName2);
	}

	public void prepareUserDetailsView(String name) {
		setUserName(name);
		setLoginField(name);
		setTitle(name);
		setVisible(true);
	}

}