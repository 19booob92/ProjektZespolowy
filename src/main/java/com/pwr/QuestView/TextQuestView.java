package com.pwr.QuestView;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.pwr.DetailsView.DescribeView;
import com.pwr.Quest.QuestFactory;
import com.pwr.Quest.QuestPoint;
import com.pwr.Quest.QuestType;

public class TextQuestView extends QuestView implements DescribeView{

private QuestPoint quest;
//protected JTextField textGoTo;
private DefaultComboBoxModel answers = new DefaultComboBoxModel();
private JComboBox answersComboBox;
protected ArrayList<String> textAnswer = new ArrayList<String>();
private JLabel lblAnswer;

private JButton btnAddAnswer;
private JButton btnEditAnswer;
private JButton btnDeleteAnswer;

private Border border = BorderFactory.createLineBorder(Color.BLACK);
private int answerTrigger=0;

public TextQuestView() {
            super();	
            this.add(new JLabel("Field"));
            setSize(PANEL_WIDTH, PANEL_HEIGHT);
            
            //textGoTo = new JTextField();
            //textGoTo.setBounds(23,302,157,29);
            //add(textGoTo);

            lblAnswer = new JLabel("Odpowiedź");
            lblAnswer.setBounds(23, 277, 87, 14);
            add(lblAnswer);
            
            answersComboBox = new JComboBox(answers);
            answersComboBox.setSelectedIndex(-1);
            answersComboBox.setBounds(23,302,160,23);
            add(answersComboBox);
            
            btnAddAnswer = new JButton("Dodaj odpowiedź");
            btnAddAnswer.setBounds(183,302,122,23);
            add(btnAddAnswer);
            
            btnEditAnswer = new JButton("Edytuj odpowiedź");
            btnEditAnswer.setBounds(305,302,117,23);
            add(btnEditAnswer);
            
            btnDeleteAnswer = new JButton("Usuń odpowiedź");
            btnDeleteAnswer.setBounds(422,302,122,23);
            add(btnDeleteAnswer);
            
            addButtonsListeners();

            //quest = QuestFactory.createQuest(QuestType.FIELDQUEST);
                
}
    private void addButtonsListeners()
    {
        btnAddAnswer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	String answ = JOptionPane.showInputDialog("Podaj odpowiedź");
            	String answTest = answ;
            	if (answ.trim().length()>0) {
            		textAnswer.add(answ);
            		answers.addElement(textAnswer.get(answerTrigger));
            		answersComboBox.setSelectedIndex(answerTrigger);
            		answerTrigger++;	            		
            	} else {
            		JOptionPane.showMessageDialog(null, "Odpowiedź nie może być pusta!");
            	}
            }
        });
        
        btnEditAnswer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = answersComboBox.getSelectedIndex();
                if(selectedIndex!=-1){
                String tempAnswer=JOptionPane.showInputDialog("Podaj odpowiedÅº",textAnswer.get(selectedIndex));
                textAnswer.set(selectedIndex, tempAnswer);
                answers.removeAllElements();
                for(int i=0;i<textAnswer.size();i++)
                {
                    answers.addElement(textAnswer.get(i));
                }
                answersComboBox.setSelectedIndex(selectedIndex);
            }}
        });
        
        btnDeleteAnswer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = answersComboBox.getSelectedIndex();
                if(selectedIndex!=-1){
                textAnswer.remove(selectedIndex);
                answers.removeElementAt(selectedIndex);
                answerTrigger--;
                answersComboBox=new JComboBox(answers);}
            }
        });
    }
	@Override
	public String introduceYourself() {
		return "TextQuest";
	}
	
	public DefaultComboBoxModel getAnswersCombo() {
		return answers;
	}
	
	public void setAnswers(ArrayList<String> answers)
	{
		textAnswer=answers;
	}
}