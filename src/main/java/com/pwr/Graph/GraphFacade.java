package com.pwr.Graph;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class GraphFacade {

	private GraphPanel graphPanel;

	public GraphFacade() {
		setGraphPanel(new GraphPanel());
	}

	public GraphFacade(QuizDataObject [] tqDBArray) {
		getGraphPanel().setQuizListFromArray(tqDBArray);
	}

	public GraphFacade(List<QuizDataObject> quizes) {
		getGraphPanel().setQuizListFromArrayList(quizes);
	}
	
	public void addQuizDataObjectArray(QuizDataObject [] tqDBArray)
	{
		getGraphPanel().setQuizListFromArray(tqDBArray);
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
	
	public GraphPanel getGraphPanel() {
		return graphPanel;
	}

	public void setGraphPanel(GraphPanel graphPanel) {
		this.graphPanel = graphPanel;
	}
}
