package com.pwr.QuestView;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import com.pwr.DetailsView.DescribeView;
import com.pwr.Map.GoogleMapPanel;
import com.pwr.Quest.QuestPoint;

public class FieldQuestView extends QuestView implements DescribeView {

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

		setSize(PANEL_WIDTH, PANEL_HEIGHT);
		googlePanel = new GoogleMapPanel(616, 329);
		// GoogleMap Listeners, labels etc
		googlePanel.addMouseListener(googlePanel);
		googlePanel.addMouseMotionListener(googlePanel);
		googlePanel.addMouseWheelListener(googlePanel);
		googlePanel.addKeyListener(googlePanel);
		googlePanel.setFocusable(true);
		googlePanel.setBounds(26, 379, 616, 329);

		lblLatitude = new JLabel("Szerokość geograficzna");
		lblLatitude.setBounds(26, 269, 150, 20);
		add(lblLatitude);

		latitudeField = new JTextField();
		latitudeField.setBounds(26, 289, 150, 30);
		latitudeField.setEditable(false);
		add(latitudeField);

		lblLongitude = new JLabel("Długość geograficzna");
		lblLongitude.setBounds(196, 269, 150, 20);
		add(lblLongitude);

		longitudeField = new JTextField();
		longitudeField.setBounds(196, 289, 150, 30);
		longitudeField.setEditable(false);
		add(longitudeField);

		lblWidth = new JLabel("Szerokość obszaru");
		lblWidth.setBounds(26, 319, 150, 20);
		add(lblWidth);

		widthField = new JTextField();
		widthField.setBounds(26, 339, 150, 30);
		widthField.setEditable(false);
		add(widthField);

		lblHeight = new JLabel("Wysokość obszaru");
		lblHeight.setBounds(196, 319, 150, 20);
		add(lblHeight);

		heightField = new JTextField();
		heightField.setBounds(196, 339, 150, 30);
		heightField.setEditable(false);
		add(heightField);
		
		add(googlePanel);
		// quest = QuestFactory.createQuest(QuestType.TEXTQUEST);

		googlePanel.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				latitudeField.setText(Double.toString(googlePanel
						.getMarkerLongtitude()));
				longitudeField.setText(Double.toString(googlePanel
						.getMarkerLatitude()));
				widthField.setText(Double.toString(googlePanel.getMarkerWidth()));
				//widthField.setText(Double.toString(googlePanel.get2CoordLong()));
				heightField.setText(Double.toString(googlePanel.getMarkerHeight()));
				//heightField.setText(Double.toString(googlePanel.get2CoordLat()));
				FieldQuestView.this.repaint();
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		
	}

	@Override
	public String introduceYourself() {
		return "FieldQuest";
	}

}
