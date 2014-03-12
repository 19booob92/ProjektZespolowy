package Editor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class MainView extends JFrame{
	
	private JTextField nameOfGame = new JTextField("Name of quest");
	private JTextField something = new JTextField("[something]");
	private JList<MapPoint> list;
	private JButton listBtn = new JButton("Detals");
	private JPanel panel;
	
	private MapPoint [] mapPoints = {new MapPoint(), new MapPoint()};
	
	public MainView() {
		super("Editor");
		panel = new JPanel(); 
		panel.setLayout(new GridLayout(2,2));
		list = new JList<MapPoint>(mapPoints);
		list.setPreferredSize(new Dimension(50,100));

//		Block of listeners
		
		listBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					
					public void run() {
						DetailsView dv = new DetailsView();
					}
				});
			}
		});
		
		panel.add(nameOfGame);
		panel.add(something);
		panel.add(list);
		panel.add(listBtn);
		
		add(panel);
		
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
}
