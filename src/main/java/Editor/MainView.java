package Editor;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MainView extends JFrame {

	private JTextField nameOfGame = new JTextField("Name of quest");
	private JButton listBtn = new JButton("Details");
	private GoogleMapPanel panel;
	private JList list;
	private String pointName = "default";
	private DetailsView detailsView;
	private String[] mapPoints = { "test 0", "test 1" };
	private JButton userDataBtn;
	private JLabel lblQuestName;
	private JLabel lblNodeList;
	private JButton btnCreate;

	//Here Goes GoogleMap
	private static double step=0.003;
    private static int zoom=15;
    private static int imageSizeW=440;
    private static int imageSizeH=440;
    private static double latitude=51.110851;
    private static double longtitude=17.029839;
    private static  final String googleKey="AIzaSyAYEbDIFRtcBXkDn4XbE_VH7A7WqHx1Z8o";
    
    private static JLabel display = null;
    private static JButton up = null;
    private static JButton down = null;
    private static JButton left = null;
    private static JButton right = null;
	//endof
	
	public MainView() {
		super("Editor");
		this.setBounds(0, 0, 600, 600);
		setLocationRelativeTo(null);
		panel = new GoogleMapPanel();
		
		//GoogleMap Listeners, labels etc
		panel.addMouseListener(panel);
        panel.addMouseMotionListener(panel);
        panel.addKeyListener(panel);
        panel.setFocusable(true);
        panel.setBounds(213, 86, 338, 329);
        
        display = new JLabel();
        display.setPreferredSize(new Dimension(imageSizeW,imageSizeH));
        display.setMaximumSize(new Dimension(imageSizeW,imageSizeH));
        display.setAlignmentX(Component.CENTER_ALIGNMENT);
        MapGetter.getMapImage(MapGetter.createUrl(0, 0));
	    //endof
		
		
		listBtn.setBounds(12, 533, 107, 25);
		list = new JList(mapPoints);
		userDataBtn = new JButton("Create User");

		// Block of listeners
		list.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent event) {
				pointName = list.getSelectedValue().toString();
			}
		});

		userDataBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {

					public void run() {
						new UserDataRegister();
					}
				});
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
		nameOfGame.setBounds(12, 30, 96, 19);

		panel.add(nameOfGame);
		panel.add(listBtn);
		getContentPane().add(panel);

		list.setBounds(26, 85, 129, 190);
		panel.add(list);

		userDataBtn.setBounds(430, 533, 143, 25);
		panel.add(userDataBtn);

		lblQuestName = new JLabel("Quest Name");
		lblQuestName.setBounds(12, 10, 113, 9);
		panel.add(lblQuestName);

		lblNodeList = new JLabel("Node list");
		lblNodeList.setBounds(12, 61, 70, 15);
		panel.add(lblNodeList);

		btnCreate = new JButton("Generate Package");
		btnCreate.setBounds(247, 533, 181, 25);
		panel.add(btnCreate);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(new Dimension(800, 600));
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