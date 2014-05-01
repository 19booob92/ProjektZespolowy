package com.pwr.Graph;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class GraphFacade extends JFrame {

	GraphPanel graphPanel;

	public GraphFacade() {
		super("Graf");
		graphPanel = new GraphPanel();
		JScrollPane scrollPanel= new JScrollPane(graphPanel);
		scrollPanel.setSize(new Dimension(800, 600));
		add(scrollPanel);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setVisible(true);
	}
	/*
	 * Tutaj wrzucacie w argumencie upakowan¹ tablice quizow
	 * no i te quizy to sa takie DTO (QuizDataObject)
	 * 
	 * stawialem na semantyke, wiec wszystko powinno byc jasne
	 */
	public GraphFacade(QuizDataObject [] tqDBArray) {
		super("Graf");
		graphPanel = new GraphPanel();
		JScrollPane scrollPanel= new JScrollPane(graphPanel);
		scrollPanel.setSize(new Dimension(800, 600));
		add(scrollPanel);
		graphPanel.setQuizListFromArray(tqDBArray);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		GraphFacade main = new GraphFacade();
		main.graphPanel.setQuizListFromArray(main.createFilledQuizList(2));
	}

	public void addQuizDataObjectArray(QuizDataObject [] tqDBArray)
	{
		graphPanel.setQuizListFromArray(tqDBArray);
	}
	public QuizDataObject[] createFilledQuizList(int size)
	{
		size=6;
		QuizDataObject [] qDBArray=new QuizDataObject[size];
		
		
		qDBArray[0]=new QuizDataObject("dsad0",new String[]{""+(1)},"",""+0);
		qDBArray[1]=new QuizDataObject("dsad1",new String[]{""+(2)},""+0,""+(1));
		qDBArray[2]=new QuizDataObject("asfs2",new String[]{""+(4)},""+1,""+(2));
		qDBArray[3]=new QuizDataObject("asa3",new String[]{""+(5)},""+2,""+(3));
		qDBArray[4]=new QuizDataObject("aas4",new String[]{""+(3)},"",""+(4));
		qDBArray[5]=new QuizDataObject("ss5",new String[]{""},"",""+(5));

		return qDBArray;
	}
}
