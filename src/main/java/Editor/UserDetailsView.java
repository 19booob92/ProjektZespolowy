 package Editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class UserDetailsView extends JFrame {

	private JPanel contentPane;

	public UserDetailsView(final String value) {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JButton btnDeleteUser = new JButton("Delete user");
		btnDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(value + "dupa");
			}
		});
		btnDeleteUser.setBounds(319, 238, 117, 25);
		contentPane.add(btnDeleteUser);
		setVisible(true);
		
	}
}
