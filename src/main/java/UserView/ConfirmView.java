package UserView;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pwr.UserRegistration.Requests;

@Component
public class ConfirmView extends JFrame {

	@Autowired
	private Requests requests;
	private JButton btnOK;
	private JButton btnCancel;
	private JLabel confirm;

	ConfirmView() {
		super("Potwierdzenie");

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new FlowLayout());
		setSize(350, 150);

		btnOK = new JButton("OK");
		btnOK.setBounds(17, 156, 117, 25);

		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(40, 156, 117, 25);

		confirm = new JLabel(
				"Czy jestes pewny ze chcesz usunac wszsytkie dane?");

		add(confirm);
		add(btnOK);
		add(btnCancel);

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ConfirmView.this.dispose();
			}
		});

		btnOK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				requests.deleteAll();
			}
		});

	}

}
