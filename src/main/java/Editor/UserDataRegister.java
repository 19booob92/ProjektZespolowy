package Editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class UserDataRegister extends JFrame {
	private JPanel contentPane;
	private PostReqest postReqest;
	private JButton btnCommit;
	private JTextField loginTxt;
	private JTextField passTxt;
	private JTextField roletxT;
	private final JLabel lblUserLogin = new JLabel("User login");
	private JLabel roleLbl;

	public UserDataRegister() {

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(100, 100, 165, 230);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnCommit = new JButton("commit");
		btnCommit.setBounds(25, 161, 117, 25);
		contentPane.add(btnCommit);

		loginTxt = new JTextField();
		loginTxt.setBounds(25, 30, 114, 19);
		contentPane.add(loginTxt);
		loginTxt.setColumns(10);

		passTxt = new JTextField();
		passTxt.setBounds(22, 79, 114, 19);
		contentPane.add(passTxt);
		passTxt.setColumns(10);

		roletxT = new JTextField();
		roletxT.setBounds(25, 130, 114, 19);
		contentPane.add(roletxT);
		roletxT.setColumns(10);

		JLabel loginLbl = new JLabel("Login Label");
		loginLbl.setBounds(22, 52, 200, 15);
		contentPane.add(loginLbl);

		JLabel passLbl = new JLabel("Password Label");
		loginLbl.setBounds(12, 65, 200, 15);
		contentPane.add(loginLbl);

		roleLbl = new JLabel("Role Label");
		roleLbl.setBounds(12, 110, 200, 15);
		contentPane.add(roleLbl);
		lblUserLogin.setBounds(12, 0, 174, 33);
		contentPane.add(lblUserLogin);
		this.setVisible(true);

		btnCommit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					User user = new User();
					user.setLogin(loginTxt.getText());
					user.setPassword((passTxt.getText()));
					user.setRole(roletxT.getText());

					postReqest = new PostReqest(user);
					postReqest.sendData();
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		});

	}
}
