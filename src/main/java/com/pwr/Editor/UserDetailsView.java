package com.pwr.Editor;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.pwr.NewInterface.ProjectMainView;
import com.pwr.UserRegistration.Requests;
import com.pwr.UserRegistration.UserDTO;

@Component
@Lazy (value = true)
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
	private JLabel lblUserName;
	
	private static String userName;
	
	public UserDetailsView() {
		setBounds(100, 100, 293, 300);
		Toolkit toolkt = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkt.getScreenSize();
		this.setLocation(screenSize.width / 4, screenSize.height / 4);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		btnDeleteUser = new JButton("Delete user");
		btnDeleteAllDoneQuests = new JButton("<html><body style=\"text-align:center;\">Usun wszystkie<br />zakonczone questy</body></html>");

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

		btnDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					requests.delete(userName, "/deleteUser");
					projectMainView.updateTable();
					UserDetailsView.this.dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnDeleteUser.setBounds(17, 156, 117, 25);
		btnDeleteAllDoneQuests.setBounds(162, 120, 117, 50);
		contentPane.add(btnDeleteUser);
		contentPane.add(btnDeleteAllDoneQuests);

		JButton btnDeleteGame = new JButton("Delete game");
		btnDeleteGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					requests.delete(userName, "/deleteGame");
					projectMainView.updateTable();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnDeleteGame.setBounds(17, 192, 117, 25);
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
				setUserNameLabelTxt(name);
			}
		});
		btnUpdate.setBounds(17, 120, 117, 25);
		contentPane.add(btnUpdate);

		login = new JTextField();
		login.setBounds(20, 30, 122, 30);
		contentPane.add(login);
		login.setColumns(10);

		pass = new JTextField();
		pass.setBounds(144, 30, 122, 30);
		contentPane.add(pass);
		pass.setColumns(10);

		email = new JTextField();
		email.setBounds(20, 75, 122, 30);
		contentPane.add(email);
		email.setColumns(10);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(20, 11, 70, 15);
		contentPane.add(lblLogin);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(143, 11, 70, 15);
		contentPane.add(lblPassword);

		JLabel lblEmail = new JLabel("E-Mail");
		lblEmail.setBounds(20, 60, 70, 15);
		contentPane.add(lblEmail);
		
		JLabel lblUserNameLabel = new JLabel();
		lblUserNameLabel.setBounds(178, 197, 70, 15);
		contentPane.add(lblUserNameLabel);
		
		lblUserName = new JLabel("UserName");
		lblUserName.setBounds(178, 197, 70, 15);
		contentPane.add(lblUserName);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void setUserNameLabelTxt(String name) {
		lblUserName.setText(name);
	}

	public void setLoginField(String userName2) {
		login.setText(userName2);
	}
	
	public void prepareUserDetailsView(String name) {
		setUserName(name);
		setLoginField(name);
		setUserNameLabelTxt(name);
		setTitle(name);
		setVisible(true);
	}

}