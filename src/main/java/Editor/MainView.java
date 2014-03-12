package Editor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MainView extends JFrame {

	private JTextField nameOfGame = new JTextField("Name of quest");
	private JTextField something = new JTextField("[something]");
	public JButton listBtn = new JButton("Details");
	private JPanel panel;
	private JList list;
	private String pointName = "default";
	
	private DetailsView detailsView;
	
	private String [] mapPoints = {"test 0", "test 1"};
	
	public MainView() {
		super("Editor");
		panel = new JPanel(); 
		listBtn.setBounds(150, 68, 80, 25);
		list = new JList(mapPoints);
		
//		Block of listeners
		list.addListSelectionListener(new ListSelectionListener() {
			
			public void valueChanged(ListSelectionEvent event) {
				pointName = list.getSelectedValue().toString();
			}
		});

		listBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {

				EventQueue.invokeLater(new Runnable() {
					public void run() {
						detailsView = new DetailsView(pointName);
						System.out.println(detailsView.getPointName());
					}
				});
			}
		});
		
		panel.setLayout(null);
		nameOfGame.setBounds(16, 8, 96, 19);
		
		panel.add(nameOfGame);
		something.setBounds(117, 8, 80, 19);
		panel.add(something);
		panel.add(listBtn);
		
		getContentPane().add(panel);
		
		list.setBounds(26, 35, 86, 103);
		panel.add(list);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(new Dimension(300, 300));
		setVisible(true);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				new MainView();
			}
		});
	}

	public DetailsView getDetailsView() {
		return detailsView;
	}

	public void setDetailsView(DetailsView detailsView) {
		this.detailsView = detailsView;
	}
}