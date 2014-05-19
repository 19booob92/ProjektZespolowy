package com.pwr.NewInterface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.lowagie.text.pdf.TextField;
import com.pwr.Map.GoogleMapPanel;
import com.pwr.Quest.Campaign;
import com.pwr.Quest.TreasureBox;

public class TreasureBoxDialog extends JDialog{
	
	private JLabel noteLabel;
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
	
	public TreasureBoxDialog(Campaign campaign)
	{
		super();
		this.campaignRef=campaign;
		this.index=index;
		this.setSize(800,600);
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		googlePanel = new GoogleMapPanel(400,400);
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
		
		add(googlePanel,BorderLayout.WEST);
		
				
		textArea = new JTextArea();
		textArea.setPreferredSize(new Dimension(380,300));
		this.add(textArea,BorderLayout.EAST);
		
		
		dataPanel = new JPanel();
		dataPanel.setPreferredSize(new Dimension(800,200));
		dataPanel.setLayout(null);
		
		xLabel = new JLabel("X");
		xLabel.setPreferredSize(new Dimension(40,30));
		xLabel.setBounds(20,20,20,30);
		dataPanel.add(xLabel);
		
		xField = new JTextField();
		xField.setPreferredSize(new Dimension(40,30));
		xField.setBounds(50,20,150,30);
		xField.setEditable(false);
		dataPanel.add(xField);
		
		yLabel = new JLabel("Y");
		yLabel.setPreferredSize(new Dimension(40,30));
		yLabel.setBounds(20,60,20,30);
		dataPanel.add(yLabel);
		
		yField = new JTextField();
		yField.setPreferredSize(new Dimension(40,30));
		yField.setBounds(50,60,150,30);
		yField.setEditable(false);
		dataPanel.add(yField);
		
		widthLabel = new JLabel("Szerokość");
		widthLabel.setPreferredSize(new Dimension(200,30));
		widthLabel.setBounds(210,20,80,30);
		dataPanel.add(widthLabel);
		
		widthField = new JTextField();
		widthField.setPreferredSize(new Dimension(40,30));
		widthField.setBounds(300,20,150,30);
		widthField.setEditable(false);
		dataPanel.add(widthField);
		
		heightLabel = new JLabel("Wysokość");
		heightLabel.setPreferredSize(new Dimension(200,30));
		heightLabel.setBounds(210,60,80,30);
		dataPanel.add(heightLabel);
		
		heightField = new JTextField();
		heightField.setPreferredSize(new Dimension(40,30));
		heightField.setBounds(300,60,150,30);
		heightField.setEditable(false);
		dataPanel.add(heightField);
		
		btnAdd = new JButton("Dodaj");
		btnAdd.setBounds(460,30,120,30);
		
		btnAdd.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				addTreasureBox();
			}
			
		});
		dataPanel.add(btnAdd);
		
		btnSave = new JButton("Zapisz");
		btnSave.setBounds(460,60,120,30);
		btnSave.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				saveTreasureBox();
			}
			
		});
		
		dataPanel.add(btnSave);
		treasureBoxLabel = new JLabel("Skrytki");
		treasureBoxLabel.setBounds(600,10,60,30);
		dataPanel.add(treasureBoxLabel);
		
		treasureComboBox = new JComboBox(campaignRef.getTreasureBoxes().toArray());
		treasureComboBox.setBounds(600,40,120,30);
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
		
		this.add(dataPanel,BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
	
	public void setUpData(double x, double y, double width, double height, String text)
	{
		this.xField.setText(Double.toString(x));
		this.yField.setText(Double.toString(y));
		this.widthField.setText(Double.toString(width));
		this.heightField.setText(Double.toString(height));
		this.textArea.setText(text);
		
		this.googlePanel.setMapPoint(x,y,width,height);
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
		//treasureComboBox.setSelectedIndex(0);
	}

}
