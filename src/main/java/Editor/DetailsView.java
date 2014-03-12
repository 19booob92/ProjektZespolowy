package Editor;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class DetailsView extends JFrame{
	
	private JPanel panel;
	private JTextArea questContent = new JTextArea("Insert quest content");
	private JButton choosePics = new JButton("Chose files");
	private JList<String> paths;
	
	public DetailsView() {
		super("Details view");
		
		panel = new JPanel();
		
		panel.setLayout(new GridLayout());
		
		paths = new JList<String>();
		paths.setPreferredSize(new Dimension(70, 50));
		
		panel.add(questContent);
		panel.add(choosePics);
		panel.add(paths);
		
		add(panel);
		
		setSize(new Dimension(300, 300));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		
	}
}
