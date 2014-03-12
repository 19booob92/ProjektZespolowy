package Editor;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class DetailsView extends JFrame {

	private JPanel panel;
	private JTextArea questContent = new JTextArea("Insert quest content");
	private JButton choosePics = new JButton("Chose files");
	private JList<String> paths;
	private JLabel pointNameLbl;
	
	private String pointName = "default";
	
	public DetailsView(String pointName) {
		super("Details view");
		
		this.pointName = pointName;
		
		panel = new JPanel();
		
		paths = new JList<String>();
		paths.setBounds(121, 37, 70, 50);
		paths.setPreferredSize(new Dimension(70, 50));
		questContent.setBounds(26, 10, 129, 15);
		pointNameLbl = new JLabel(this.pointName);
		
		panel.add(questContent);
		choosePics.setBounds(160, 5, 112, 25);
		panel.add(choosePics);
		panel.add(paths);
		panel.add(pointNameLbl);
		
		getContentPane().add(panel);
		
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
