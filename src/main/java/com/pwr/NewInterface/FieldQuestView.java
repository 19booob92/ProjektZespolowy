package com.pwr.NewInterface;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import com.pwr.Map.GoogleMapPanel;
import com.pwr.Quest.Campaign;
import com.pwr.Quest.QuestFactory;
import com.pwr.Quest.QuestPoint;
import com.pwr.Quest.QuestType;

public class FieldQuestView extends QuestView implements MouseListener, DescribeView {

	private QuestPoint quest;
	protected GoogleMapPanel googlePanel;
        
        private JLabel lblLatitude;
        private JLabel lblLongitude;
        private JLabel lblWidth;
        private JLabel lblHeight;
        
        protected JTextField latitudeField;
        protected JTextField longitudeField;
        protected JTextField widthField;
        protected JTextField heightField;
	
	public FieldQuestView() {
		super();
		setSize(panelWidth, panelHeight);
		googlePanel = new GoogleMapPanel(616,329);
		//GoogleMap Listeners, labels etc
		googlePanel.addMouseListener(googlePanel);
        googlePanel.addMouseMotionListener(googlePanel);
        googlePanel.addMouseWheelListener(googlePanel);
        googlePanel.addKeyListener(googlePanel);
        googlePanel.setFocusable(true);
        googlePanel.setBounds(26, 379, 616, 329);
        
        lblLatitude = new JLabel("Szerokość geograficzna");
        lblLatitude.setBounds(26,269,150,20);
        add(lblLatitude);
        
        latitudeField = new JTextField();
        latitudeField.setBounds(26,289,150,30);
        add(latitudeField);
        
        lblLongitude = new JLabel("Długość geograficzna");
        lblLongitude.setBounds(196,269,150,20);
        add(lblLongitude);
        
        longitudeField = new JTextField();
        longitudeField.setBounds(196,289,150,30);
        add(longitudeField);
        
        lblWidth = new JLabel("Szerokość obszaru");
        lblWidth.setBounds(26,319,150,20);
        add(lblWidth);
        
        widthField = new JTextField();
        widthField.setBounds(26,339,150,30);
        add(widthField);
        
        lblHeight = new JLabel("Wysokość obszaru");
        lblHeight.setBounds(196,319,150,20);
        add(lblHeight);
        
        heightField = new JTextField();
        heightField.setBounds(196,339,150,30);
        add(heightField);
        
        this.addMouseListener(this);
	    //endof
		add(googlePanel);
		quest = QuestFactory.createQuest(QuestType.TEXTQUEST);
	}

    @Override
    public void mouseClicked(MouseEvent e) {
      
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        latitudeField.setText(Double.toString(googlePanel.getMarkerLatitude()));
        longitudeField.setText(Double.toString(googlePanel.getMarkerLongtitude()));
        widthField.setText(Double.toString(googlePanel.getMarkerWidth()));
        heightField.setText(Double.toString(googlePanel.getMarkerHeight()));
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	@Override
	public String introduceYourself() {
		return "FieldQuest";
	}


}
