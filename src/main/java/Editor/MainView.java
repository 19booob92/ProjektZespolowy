package Editor;

import Map.GoogleMapPanel;
import Quest.MapPoint;
import UserRegistration.UserDataRegister;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MainView extends JFrame {

	private JTextField nameOfGame = new JTextField("Name");
	
	private JPanel panel;
	private GoogleMapPanel googlePanel;
	private DetailsView detailsView;
	
	//Lista byc moze do usuniecia
	private static ArrayList<MapPoint> mPoints;
	private static MapPoint temporaryMapPoint;
	
	private static JScrollPane scrollPane;
	private static DefaultListModel listModel;
	private static JList list;
        	
	private int QuestType = 0;
		
	private String pointName;// = "default";
	
	private JLabel lblQuestName;
	private JLabel lblNodeList;
	
        private JButton btnDeleteMarker = new JButton("Usuń");
	private JButton btnCreate;
	private JButton listBtn = new JButton("Details");
	private JButton userDataBtn;
	private ButtonGroup QuestGroup;
        
	
    private static int imageSizeW=640;
    private static int imageSizeH=640;

	public MainView() 
	{
		super("Editor");
		this.setBounds(0, 0, 600, 600);
		setLocationRelativeTo(null);
		
		mPoints = new ArrayList<>();
		panel = new JPanel();
		googlePanel = new GoogleMapPanel(338,329);
		
		//GoogleMap Listeners, labels etc
		googlePanel.addMouseListener(googlePanel);
        googlePanel.addMouseMotionListener(googlePanel);
        googlePanel.addMouseWheelListener(googlePanel);
        googlePanel.addKeyListener(googlePanel);
        googlePanel.setFocusable(true);
        googlePanel.setBounds(272, 32, 338, 329);
        
	    //endof
		
		
		listBtn.setBounds(12, 372, 107, 25);
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
						//detailsView = new DetailsView(pointName, QuestType);
						detailsView = new DetailsView(temporaryMapPoint, QuestType);
						System.out.println(detailsView.getPointName());
					}
				});
			}
		});

		panel.setLayout(null);
		panel.add(listBtn);

                
                btnDeleteMarker.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        googlePanel.deleteMarker(list.getSelectedIndex());
                    }
                });
                btnDeleteMarker.setBounds(120, 372, 107, 25);
                
                //panel.add(btnDeleteMarker);
                  
		userDataBtn.setBounds(467, 372, 143, 25);
		panel.add(userDataBtn);

		btnCreate = new JButton("Generate Package");
		btnCreate.setBounds(276, 372, 181, 25);
		panel.add(btnCreate);
		
		QuestGroup = new ButtonGroup();
		
		JPanel controlsPanel = new JPanel();
		controlsPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		controlsPanel.setBounds(12, 32, 250, 329);
			
		controlsPanel.setLayout(null);
		
		JLabel lblQuestType = new JLabel("Quest Type");
		lblQuestType.setBounds(20, 54, 71, 14);
		controlsPanel.add(lblQuestType);
		
		lblNodeList = new JLabel("Node list");
	
		lblNodeList.setBounds(20, 200, 71, 14);
		controlsPanel.add(lblNodeList);
		nameOfGame.setBounds(101, 23, 139, 20);
		controlsPanel.add(nameOfGame);
		
		lblQuestName = new JLabel("Name of Game");
		lblQuestName.setBounds(20, 26, 71, 14);
		
		controlsPanel.add(lblQuestName);
		
		listModel = new DefaultListModel();
		list = new JList(listModel);
		
		//Testowe punkty
		//MapPoint testOne = new MapPoint(5.0, 3.0);
		//MapPoint testTwo = new MapPoint(3.0, 4.0);
		//listModel.addElement(testOne);
		//listModel.addElement(testTwo);
		ArrayList testOnePaths = new ArrayList<String>();
		ArrayList testTwoPaths = new ArrayList<String>();
		testOnePaths.add("asd");
		testOnePaths.add("zxc");
		testOnePaths.add("cvb");
		
		testTwoPaths.add("qwe");
		testTwoPaths.add("rty");
		//testOne.getQuest().setSoundPaths(testOnePaths);
		//testOne.getQuest().setPicturePaths(testTwoPaths);
		
		//scrollPane =  new JScrollPane(list);
		//scrollPane.setViewportView(list);
		list.setBounds(20, 225, 220, 93);
		controlsPanel.add(list);
		
		//Pogrupowane typy zagadek + listenery
		JRadioButton rdbtnRangeQuest = new JRadioButton("Zagadka ...");
		rdbtnRangeQuest.setBounds(101, 76, 129, 23);
		
		JRadioButton rdbtnTextQuest = new JRadioButton("Zagadka tekstowa");
		rdbtnTextQuest.setBounds(101, 50, 129, 23);
		
		JRadioButton rdbtnChoiceTest = new JRadioButton("Test wyboru");
		rdbtnChoiceTest.setBounds(101, 102, 109, 23);
		
		JRadioButton rdbtnFieldQuest = new JRadioButton("Zadanie terenowe");
		rdbtnFieldQuest.setBounds(101, 128, 109, 23);
		
		JRadioButton rdbtnOrderBy = new JRadioButton("Uporz\u0105dkuj ");
		rdbtnOrderBy.setBounds(101, 154, 109, 23);
			
		QuestGroup.add(rdbtnRangeQuest);
		QuestGroup.add(rdbtnTextQuest);
		QuestGroup.add(rdbtnChoiceTest);
		QuestGroup.add(rdbtnFieldQuest);
		QuestGroup.add(rdbtnOrderBy);
		
		controlsPanel.add(rdbtnRangeQuest);
		controlsPanel.add(rdbtnTextQuest);
		controlsPanel.add(rdbtnChoiceTest);
		controlsPanel.add(rdbtnOrderBy);
		controlsPanel.add(rdbtnFieldQuest);
		
		// Block of listeners
		rdbtnTextQuest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						QuestType = 0;
						System.out.println(detailsView.getPointName());
					}
				});
			}
		});
		
		rdbtnRangeQuest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						QuestType = 1;
						System.out.println(detailsView.getPointName());
					}
				});
			}
		});
		
		list.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent event) {
				pointName = list.getSelectedValue().toString();
				temporaryMapPoint = (MapPoint) list.getSelectedValue();
			}
                        
		});
		
                list.addMouseListener(new MouseListener()
                {

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        if(list.getSelectedIndex()!=-1)
                        {
                            googlePanel.centerViewOnPoint(list.getSelectedIndex());
                            googlePanel.selectMarker(list.getSelectedIndex());
                        }
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                       }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                       }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        }
                    
                });
		panel.add(controlsPanel);
		panel.add(googlePanel);
		getContentPane().add(panel);
		
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
	
	public static void createPoint(double x, double y)
	{
		listModel.addElement(new MapPoint(x,y));      
	}
        
        public static void setPoint(int index, double x, double y)
        {
                listModel.set(index, new MapPoint(x,y));
        }
}