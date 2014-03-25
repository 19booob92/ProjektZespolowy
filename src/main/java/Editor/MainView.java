package Editor;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;

public class MainView extends JFrame {

	private JTextField nameOfGame = new JTextField("Name of quest");
	
	private JPanel panel;
	private GoogleMapPanel googlePanel;
	private DetailsView detailsView;
	
	private static DefaultListModel listModel;
	private static JList list;
	
	private int QuestType = 0;
	
	
	private String[] mapPoints = { "test 0", "test 1" };
	private String pointName = "default";
	
	private JLabel lblQuestName;
	private JLabel lblNodeList;
	
	private JButton btnCreate;
	private JButton listBtn = new JButton("Details");
	private JButton userDataBtn;
	private ButtonGroup QuestGroup;
	
	//Here Goes GoogleMap

    private static int zoom=15;
    private static int imageSizeW=640;
    private static int imageSizeH=640;
    private static double latitude=51.110851;
    private static double longtitude=17.029839;
    private static double step=0.003;
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
		
		panel = new JPanel();
		googlePanel = new GoogleMapPanel();
		
		//GoogleMap Listeners, labels etc
		googlePanel.addMouseListener(googlePanel);
        googlePanel.addMouseMotionListener(googlePanel);
        googlePanel.addKeyListener(googlePanel);
        googlePanel.setFocusable(true);
        googlePanel.setBounds(272, 32, 338, 329);
        
        display = new JLabel();
        display.setPreferredSize(new Dimension(imageSizeW,imageSizeH));
        display.setMaximumSize(new Dimension(imageSizeW,imageSizeH));
        display.setAlignmentX(Component.CENTER_ALIGNMENT);
        MapGetter.getMapImage(MapGetter.createUrl(0, 0));
	    //endof
		
		
		listBtn.setBounds(159, 372, 107, 25);
		userDataBtn = new JButton("Create User");
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
						detailsView = new DetailsView(pointName, QuestType);
						System.out.println(detailsView.getPointName());
					}
				});
			}
		});

		panel.setLayout(null);
		panel.add(listBtn);
		getContentPane().add(panel);

		userDataBtn.setBounds(467, 372, 143, 25);
		panel.add(userDataBtn);

		btnCreate = new JButton("Generate Package");
		btnCreate.setBounds(276, 372, 181, 25);
		panel.add(btnCreate);
		
		QuestGroup = new ButtonGroup();
		
		JPanel controlsPanel = new JPanel();
		controlsPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		controlsPanel.setBounds(12, 32, 250, 329);
		
		panel.add(controlsPanel);
		panel.add(googlePanel);
		
		controlsPanel.setLayout(null);
		
		JLabel lblQuestType = new JLabel("Quest Type");
		lblQuestType.setBounds(20, 54, 56, 14);
		controlsPanel.add(lblQuestType);
		
		lblNodeList = new JLabel("Node list");
		lblNodeList.setBounds(20, 120, 56, 14);
		controlsPanel.add(lblNodeList);
		nameOfGame.setBounds(101, 23, 139, 20);
		controlsPanel.add(nameOfGame);
		
		lblQuestName = new JLabel("Quest Name");
		lblQuestName.setBounds(20, 26, 59, 14);
		
		controlsPanel.add(lblQuestName);
		
		listModel = new DefaultListModel();
		listModel.addElement(mapPoints[0]);
		listModel.addElement(mapPoints[1]);
		list = new JList(listModel);
		list.setBounds(101, 119, 139, 199);
		controlsPanel.add(list);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Zagadka ...");
		rdbtnNewRadioButton_1.setBounds(101, 76, 129, 23);
		controlsPanel.add(rdbtnNewRadioButton_1);
		QuestGroup.add(rdbtnNewRadioButton_1);
		
		//Pogrupowane typy zagadek + listenery
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Zagadka tekstowa");
		rdbtnNewRadioButton.setBounds(101, 50, 129, 23);
		controlsPanel.add(rdbtnNewRadioButton);
		QuestGroup.add(rdbtnNewRadioButton);
		
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						QuestType = 0;
						System.out.println(detailsView.getPointName());
					}
				});
			}
		});
		
		rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						QuestType = 1;
						System.out.println(detailsView.getPointName());
					}
				});
			}
		});
		
		// Block of listeners
		list.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent event) {
				pointName = list.getSelectedValue().toString();
			}
		});
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(new Dimension(637, 451));
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
	
	public static void createPoint(String testValue)
	{
		listModel.addElement(testValue);
	}
}