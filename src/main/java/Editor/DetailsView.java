package Editor;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class DetailsView extends JFrame {

	private Set<String> setOfpath;

	private JPanel panel;
	private JTextArea questContent = new JTextArea("Insert quest content");
	private JButton choosePics = new JButton("Chose files");
	private JList<String> paths;
	private DefaultListModel<String> listModel;
	private JLabel pointNameLbl;
	private String pointName = "default";
	private JLabel lblNewLabel;

	public DetailsView(String pointName) {
		super("Details view");

		this.pointName = pointName;

		panel = new JPanel();
		setOfpath = new HashSet<>();
		listModel = new DefaultListModel<String>();
		paths = new JList<String>(listModel);
		paths.setBounds(165, 110, 121, 126);
		paths.setPreferredSize(new Dimension(70, 100));
		setLocationRelativeTo(null);
		questContent.setBounds(12, 48, 136, 188);
		pointNameLbl = new JLabel(this.pointName);
		pointNameLbl.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC,
				15));
		pointNameLbl.setBounds(105, 12, 117, 15);
		panel.setLayout(null);

		panel.add(questContent);
		choosePics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getPath();
			}

			private void getPath() {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Chose JPEG file");
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);

				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					// short path looks better in GUI
					listModel.addElement(chooser.getSelectedFile().getName());
					// full path for creating package
					setOfpath.add(chooser.getCurrentDirectory().toString()
							+ chooser.getSelectedFile());
					System.out.println(setOfpath);
				} else {
					System.out.println("No Selection ");
				}
			}
		});

		choosePics.setBounds(160, 37, 112, 25);
		panel.add(choosePics);
		panel.add(paths);
		panel.add(pointNameLbl);

		getContentPane().add(panel);

		lblNewLabel = new JLabel("List of pic's");
		lblNewLabel.setBounds(170, 83, 102, 15);
		panel.add(lblNewLabel);

		setSize(new Dimension(300, 300));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);

	}

	public String getPointName() {
		return pointName;
	}

	public void setPointName(String pointName) {
		this.pointName = pointName;
	}
}
