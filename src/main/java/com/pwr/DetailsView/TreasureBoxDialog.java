package com.pwr.DetailsView;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.pwr.Map.GoogleMapPanel;
import com.pwr.Map.MapGetter;
import com.pwr.Quest.Campaign;
import com.pwr.Quest.TreasureBox;

import javax.swing.border.EtchedBorder;

public class TreasureBoxDialog extends JDialog{
	
	private JLabel xLabel;
	private JLabel yLabel;
	private JLabel widthLabel;
	private JLabel heightLabel;
	private JLabel treasureBoxLabel;
	
	private JTextArea textArea;
	
	private JTextField xField;
	private JTextField yField;
	private JTextField widthField;
	private JTextField heightField;
	
	private JComboBox treasureComboBox;
	
	private JButton btnAdd;
	private JButton btnSave;
	
	private JPanel dataPanel;
	private GoogleMapPanel googlePanel;
	
	private Campaign campaignRef;
	private int index;
	private JButton btnClose;
	
	public TreasureBoxDialog(Campaign campaign)
	{
		super();
		setTitle("Zarz\u0105dzanie skrytkami");
		this.campaignRef=campaign;
		this.setSize(904,479);
		this.setResizable(false);
		googlePanel = new GoogleMapPanel(400,400);
		googlePanel.setBounds(10, 40, 380, 371);
		googlePanel.addMouseListener(googlePanel);
		googlePanel.addMouseMotionListener(googlePanel);
		googlePanel.addMouseWheelListener(googlePanel);
		googlePanel.addKeyListener(googlePanel);
		googlePanel.setFocusable(true);
		googlePanel.setPreferredSize(new Dimension(380,300));
		
		googlePanel.addMouseListener(new MouseListener()
		{

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				xField.setText(Double.toString(googlePanel
						.getMarkerLatitude()));
				yField.setText(Double.toString(googlePanel
						.getMarkerLongtitude()));
				widthField.setText(Double.toString(googlePanel.getMarkerWidth()));
				heightField.setText(Double.toString(googlePanel
						.getMarkerHeight()));
			}
		});
		getContentPane().setLayout(null);
		
		getContentPane().add(googlePanel);
		
				
		textArea = new JTextArea();
		textArea.setBounds(400, 40, 486, 173);
		textArea.setPreferredSize(new Dimension(380,300));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		getContentPane().add(textArea);
		
		
		dataPanel = new JPanel();
		dataPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		dataPanel.setBounds(400, 224, 486, 187);
		dataPanel.setPreferredSize(new Dimension(800,200));
		dataPanel.setLayout(null);
		
		xLabel = new JLabel("X");
		xLabel.setPreferredSize(new Dimension(40,30));
		xLabel.setBounds(30,11,20,30);
		dataPanel.add(xLabel);
		
		xField = new JTextField();
		xField.setPreferredSize(new Dimension(40,30));
		xField.setBounds(60,11,150,30);
		xField.setEditable(false);
		dataPanel.add(xField);
		
		yLabel = new JLabel("Y");
		yLabel.setPreferredSize(new Dimension(40,30));
		yLabel.setBounds(30,52,20,30);
		dataPanel.add(yLabel);
		
		yField = new JTextField();
		yField.setPreferredSize(new Dimension(40,30));
		yField.setBounds(60,52,150,30);
		yField.setEditable(false);
		dataPanel.add(yField);
		
		widthLabel = new JLabel("Szeroko\u015B\u0107");
		widthLabel.setPreferredSize(new Dimension(200,30));
		widthLabel.setBounds(234,11,80,30);
		dataPanel.add(widthLabel);
		
		widthField = new JTextField();
		widthField.setPreferredSize(new Dimension(40,30));
		widthField.setBounds(320,11,150,30);
		widthField.setEditable(false);
		dataPanel.add(widthField);
		
		heightLabel = new JLabel("Wysoko\u015B\u0107");
		heightLabel.setPreferredSize(new Dimension(200,30));
		heightLabel.setBounds(234,51,80,30);
		dataPanel.add(heightLabel);
		
		heightField = new JTextField();
		heightField.setPreferredSize(new Dimension(40,30));
		heightField.setBounds(320,51,150,30);
		heightField.setEditable(false);
		dataPanel.add(heightField);
		
		btnAdd = new JButton("Dodaj");
		btnAdd.setBounds(60,105,150,30);
		
		btnAdd.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				addTreasureBox();
			}
			
		});
		dataPanel.add(btnAdd);
		
		btnSave = new JButton("Zapisz");
		btnSave.setBounds(60,146,150,30);
		btnSave.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				saveTreasureBox();
			}
			
		});
		
		dataPanel.add(btnSave);
		treasureBoxLabel = new JLabel("Skrytki");
		treasureBoxLabel.setBounds(10,105,60,30);
		dataPanel.add(treasureBoxLabel);
		
		treasureComboBox = new JComboBox(campaignRef.getTreasureBoxes().toArray());
		treasureComboBox.setBounds(320,105,150,30);
		treasureComboBox.setSelectedIndex(-1);
		treasureComboBox.addItemListener(new ItemListener()
		{

			@Override
			public void itemStateChanged(ItemEvent event) {
				if(event.getStateChange() == ItemEvent.SELECTED)
				{
					TreasureBox box = campaignRef.getTreasureBox(treasureComboBox.getSelectedIndex());
					setUpData(box.getxCoordinate(),box.getyCoordinate(),box.getWidth(),box.getHeight(),box.getNote());
				}
			}
		});
		dataPanel.add(treasureComboBox);
		
		getContentPane().add(dataPanel);
		
		JLabel lblNotka = new JLabel("Notka");
		lblNotka.setBounds(401, 15, 83, 14);
		getContentPane().add(lblNotka);
		
		JLabel lblMapaSkrytek = new JLabel("Po\u0142o\u017Cenie skrytki");
		lblMapaSkrytek.setBounds(10, 15, 124, 14);
		getContentPane().add(lblMapaSkrytek);
		
		btnClose = new JButton("Zamknij");
		btnClose.setBounds(722, 416, 164, 23);
		
		btnClose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		getContentPane().add(btnClose);
		
		this.setVisible(true);
	}
	
	public void setUpData(double x, double y, double width, double height, String text)
	{
		this.xField.setText(Double.toString(x));
		this.yField.setText(Double.toString(y));
		this.widthField.setText(Double.toString(width));
		this.heightField.setText(Double.toString(height));
		this.textArea.setText(text);
		
		this.googlePanel.setMapPoint(y,x,width,height);
		MapGetter.getMapImage(MapGetter.createUrl(0, 0));
	}
	
	private void addTreasureBox()
	{
		if(validateData())
		{
			TreasureBox box = new TreasureBox();
			box.setHeight(Double.parseDouble(heightField.getText()));
			box.setWidth(Double.parseDouble(widthField.getText()));
			box.setxCoordinate(Double.parseDouble(xField.getText()));
			box.setyCoordinate(Double.parseDouble(yField.getText()));
			box.setX2Coordinate(googlePanel.get2CoordLat());
			box.setY2Coordinate(googlePanel.get2CoordLong());
			box.setNote(textArea.getText());
			
			campaignRef.addTreasureBox(box);
			
			updateComboBox();
		}
	}
	
	private void saveTreasureBox()
	{
		if(validateData())
		{
			TreasureBox box = new TreasureBox();
			box.setHeight(Double.parseDouble(heightField.getText()));
			box.setWidth(Double.parseDouble(widthField.getText()));
			box.setxCoordinate(Double.parseDouble(xField.getText()));
			box.setyCoordinate(Double.parseDouble(yField.getText()));
			box.setX2Coordinate(googlePanel.get2CoordLat());
			box.setY2Coordinate(googlePanel.get2CoordLong());
			box.setNote(textArea.getText());
			
			campaignRef.putTreasureBox(box, treasureComboBox.getSelectedIndex());
			updateComboBox();
		}
	}
	
	private boolean validateData()
	{
		if(heightField.getText().equals("")||widthField.getText().equals("")||xField.getText().equals("")||yField.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Nie wybrano punktu na mapie!");
			return false;
		}
		
		if(textArea.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Brak notki!");
			return false;
		}
		return true;
	}
	
	private void updateComboBox()
	{
		treasureComboBox.removeAllItems();
		treasureComboBox.setModel(new DefaultComboBoxModel(campaignRef.getTreasureBoxes().toArray()));
	}
}
