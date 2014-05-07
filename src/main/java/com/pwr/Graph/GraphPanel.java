package com.pwr.Graph;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import com.pwr.NewInterface.ProjectMainView;
import com.pwr.Quest.Campaign;
import com.pwr.Quest.QuestPoint;

public class GraphPanel extends JPanel  implements MouseListener{
	ArrayList<QuizDataObject> quizList;
	static public int space =70;
	static public int xOffset = 50;
	static public int yOffset = 50;
	int lineColor=0;
	
	ArrayList<GraphClick> oldClick= new ArrayList<GraphClick>();
 Color myColors[] = { Color.red,Color.green, Color.black,Color.blue };

 	ArrayList<GraphRect> quizRect = new ArrayList<GraphRect>();
 	ArrayList<GraphRect> quizIn = new ArrayList<GraphRect>();
 	ArrayList<GraphRect> quizOut = new ArrayList<GraphRect>();
 	ArrayList<GraphRect> quizWrong = new ArrayList<GraphRect>();
	public GraphPanel() {
		super();
		quizList = new ArrayList<QuizDataObject>();
		setPreferredSize(new Dimension((int) (200+GraphPanel.xOffset+quizList.size() * (QuizDataObject.getSize()+GraphPanel.space)), (int)(200+GraphPanel.yOffset+quizList.size() * (QuizDataObject.getSize()+GraphPanel.space))));
		addMouseListener(this);
	}

	public void setQuizListFromArray(QuizDataObject qDB[]) {
		for (int n = 0; n < qDB.length; n++) {
			quizList.add(qDB[n]);
		}
		setPreferredSize(new Dimension((int) (200+GraphPanel.xOffset+quizList.size() * (QuizDataObject.getSize()+GraphPanel.space)), (int)(200+GraphPanel.yOffset+quizList.size() * (QuizDataObject.getSize()+GraphPanel.space))));
		
	}

	public void setQuizListFromArrayList(List<QuizDataObject> qDB){
		for (QuizDataObject q : qDB) {
			quizList.add(q);
		}
		setPreferredSize(new Dimension((int) (200+GraphPanel.xOffset+quizList.size() * (QuizDataObject.getSize()+GraphPanel.space)), (int)(200+GraphPanel.yOffset+quizList.size() * (QuizDataObject.getSize()+GraphPanel.space))));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		lineColor=0;
		quizIn.clear();
		quizOut.clear();
		quizRect.clear();
		quizWrong.clear();
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(new Color(0.7f, 0.1f, 0.1f));

		
		drawQuizList(g);
		g.setColor(Color.RED);
		for(int m=0;m<oldClick.size();m++)
		{
			if(oldClick.get(m).type==0)
			for(int n=0;n<quizIn.size();n++)
				if(quizIn.get(n).id.equals(oldClick.get(m).id))
				{
					g.drawRect(quizIn.get(n).x, quizIn.get(n).y, quizIn.get(n).width, quizIn.get(n).height);
				}
			if(oldClick.get(m).type==1)
			for(int n=0;n<quizOut.size();n++)
				if(quizOut.get(n).id.equals(oldClick.get(m).id) && quizOut.get(n).whichOne==oldClick.get(m).whichOne)
				{
					g.drawRect(quizOut.get(n).x, quizOut.get(n).y, quizOut.get(n).width, quizOut.get(n).height);
				}
			if(oldClick.get(m).type==2)
			for(int n=0;n<quizWrong.size();n++)
				if(quizWrong.get(n).id.equals(oldClick.get(m).id))
				{
					g.drawRect(quizWrong.get(n).x, quizWrong.get(n).y, quizWrong.get(n).width, quizWrong.get(n).height);
				}
		}
	}

	public void drawQuizList(Graphics g) {
		ArrayList<String> alreadyDone = new ArrayList<String>();
		Boolean[][] busyMap = new Boolean[quizList.size()][quizList.size()];
		for (int n = 0; n < busyMap.length; n++)
			for (int m = 0; m < busyMap.length; m++)
				busyMap[n][m] = false;
		for (int n = 0; n < quizList.size(); n++) {

			drawRecursive(g, quizList.get(n), alreadyDone, n, 0, busyMap);
		}
		for (int n = 0; n < quizList.size(); n++) {

			drawQuizConnectionBezierCurve(g, quizList.get(n));

		}
	}

	public void drawRecursive(Graphics g, QuizDataObject qDB,
			ArrayList<String> alreadyDone, int x, int y, Boolean[][] busyMap) {
		for (int n = 0; n < alreadyDone.size(); n++) {
			if (alreadyDone.get(n).equals(qDB.getId()))
				return;
		}

		Boolean przeszlo = false;
		for (int n = x; n < busyMap.length; n++) {
			for (int m = y; m < busyMap[n].length; m++) {
				
				if (!busyMap[n][m]) {
					x = n;
					y = m;
					przeszlo = true;
					break;
				}

			}
			if (przeszlo)
				break;
		}
		drawOneOfMany(qDB, x, y, busyMap, g);
		alreadyDone.add(qDB.getId());
		int length = qDB.getCorrect().length;
		for (int n = 0; n < length; n++) {
			for (int m = 0; m < quizList.size(); m++)
				if (quizList.get(m).getId().equals(qDB.getCorrectByIndex(n))) {

					drawRecursive(g, quizList.get(m), alreadyDone, x + 1,
							y + n, busyMap);
					break;
				}
		}

		for (int m = 0; m < quizList.size(); m++)
			if (quizList.get(m).getId().equals(qDB.getWrong())) {
				drawRecursive(g, quizList.get(m), alreadyDone, x == 0 ? 0 : x - 1, y== 0 ? 0: y - 1, busyMap);
				break;
			}
	}

	private void drawOneOfMany(QuizDataObject qDB, int x, int y,
			Boolean[][] busyMap, Graphics g) {
		for (int n = x; n < busyMap.length; n++)
			for (int m = y; m < busyMap.length; m++) {
				if (!busyMap[n][m]) {
					qDB.setxCoord(x);
					qDB.setyCoord(y);
					int tx = (int) (xOffset + x
							* (QuizDataObject.getSize() + space));
					int ty = (int) (yOffset + y
							* (QuizDataObject.getSize() + space));
					ArrayList<ArrayList<GraphRect>> tempGRect=qDB.drawQuizWithManyCorrect(g, tx, ty,qDB.correct.length, qDB.id);
					
					quizIn.add(tempGRect.get(0).get(0));
					for(int hm=0;hm<tempGRect.get(1).size();hm++)
					quizOut.add(tempGRect.get(1).get(hm));
					quizWrong.add(tempGRect.get(2).get(0));
					
					quizRect.add(tempGRect.get(3).get(0));
					busyMap[n][m] = true;
					return;

				}
			}
	}

	private void drawLine(Graphics g, int x1, int y1, int x2, int y2) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.black);
		g2d.setStroke(new BasicStroke(2));
		g2d.drawLine(x1, y1, x2, y2);
		g2d.setColor(Color.white);
	//	g2d.setStroke(new BasicStroke(3));
	//	g2d.drawLine(x1, y1, x2, y2);

	}

	private void drawBezierCurve(Graphics g, int px1, int py1, int px2,
			int py2, int px3, int py3, int px4, int py4) {
		Point p1 = getBezierCurvePoint(0, px1, py1, px2, py2, px3, py3, px4,
				py4);
		Point p2 = new Point();
		double pitch=0.01;
		g.setColor(myColors[lineColor]);
		lineColor=(lineColor+1)>=myColors.length?lineColor=0:++lineColor;
		Graphics2D g2d= (Graphics2D) g;
		g2d.setStroke(new BasicStroke(3));
		for (double n = pitch; n <= 1; n += pitch) {
			p2 = getBezierCurvePoint(n, px1, py1, px2, py2, px3, py3, px4, py4);
			
			g2d.drawLine( p1.x, p1.y, p2.x, p2.y);
			p1 = getBezierCurvePoint(n, px1, py1, px2, py2, px3, py3, px4, py4);
		}
	}

	public Point getBezierCurvePoint(double t, int px1, int py1, int px2,
			int py2, int px3, int py3, int px4, int py4) {
		Point p = new Point();
		p.x = (int) (px1 + 3 * t * (px2 - px1) + 3 * (t * t)
				* (px1 + px3 - (2 * px2)) + (t * t * t)
				* (px4 - px1 + 3 * px2 - 3 * px3));
		p.y = (int) (py1 + 3 * t * (py2 - py1) + 3 * (t * t)
				* (py1 + py3 - (2 * py2)) + (t * t * t)
				* (py4 - py1 + 3 * py2 - 3 * py3));
		return p;
	}

	private void connectQuiz(Graphics g, QuizDataObject qDB1, int mode1,
			QuizDataObject qDB2, int mode2) {
		int random = 0;
		drawLine(g, qDB1.getxCoordAbsolute(mode1),
				qDB1.getyCoordAbsolute(mode1), qDB2.getxCoordAbsolute(mode2)
						+ random, qDB1.getyCoordAbsolute(mode1) + random);
		drawLine(g, qDB2.getxCoordAbsolute(mode2) + random,
				qDB1.getyCoordAbsolute(mode1) + random,
				qDB2.getxCoordAbsolute(mode2), qDB2.getyCoordAbsolute(mode2));
	}

	private void connectQuizWithBezierCurve(Graphics g, QuizDataObject qDB1,
			int mode1, QuizDataObject qDB2, int mode2) {
		drawBezierCurve(g,
				qDB1.getxCoordAbsolute(mode1), qDB1.getyCoordAbsolute(mode1) ,
				qDB1.getxCoordAbsolute(mode1), qDB2.getyCoordAbsolute(mode2)+70, 
				qDB2.getxCoordAbsolute(mode2), qDB1.getyCoordAbsolute(mode1)+70, 
				qDB2.getxCoordAbsolute(mode2), qDB2.getyCoordAbsolute(mode2));
	}

	private void drawQuizConnection(Graphics g, QuizDataObject qDB1) {
		for (int n = 0; n < qDB1.getCorrect().length; n++) {
			for (int m = 0; m < quizList.size(); m++) {
				if (quizList.get(m).getId().equals(qDB1.getCorrectByIndex(n))) {
					connectQuiz(g, qDB1, QuizDataObject.DRAW_MODE_OUT,
							quizList.get(m), QuizDataObject.DRAW_MODE_IN);
				}

			}
		}

		for (int m = 0; m < quizList.size(); m++) {
			if (quizList.get(m).getId().equals(qDB1.getWrong())) {
				connectQuiz(g, qDB1, QuizDataObject.DRAW_MODE_WRONG,
						quizList.get(m), QuizDataObject.DRAW_MODE_IN);
			}

		}
	}

	private void drawQuizConnectionBezierCurve(Graphics g, QuizDataObject qDB1) {
		for (int n = 0; n < qDB1.getCorrect().length; n++) {
			for (int m = 0; m < quizList.size(); m++) {
				if (quizList.get(m).getId().equals(qDB1.getCorrectByIndex(n))) {
					connectQuizWithBezierCurve(g, qDB1,
							QuizDataObject.DRAW_MODE_OUT, n, quizList.get(m),
							QuizDataObject.DRAW_MODE_IN);
				}

			}
		}

		for (int m = 0; m < quizList.size(); m++) {
			if (quizList.get(m).getId().equals(qDB1.getWrong())) {
				connectQuizWithBezierCurve(g, qDB1,
						QuizDataObject.DRAW_MODE_WRONG, quizList.get(m),
						QuizDataObject.DRAW_MODE_IN);
			}

		}
	}

	private void connectQuizWithBezierCurve(Graphics g, QuizDataObject qDB1, 
			int mode1, int whichOne,QuizDataObject qDB2, int mode2)  {
		drawBezierCurve(g,
				qDB1.getxCoordAbsolute(mode1), qDB1.getyCoordAbsolute(mode1) +whichOne*15,
				qDB1.getxCoordAbsolute(mode1), qDB2.getyCoordAbsolute(mode2)+70, 
				qDB2.getxCoordAbsolute(mode2), qDB1.getyCoordAbsolute(mode1)+70, 
				qDB2.getxCoordAbsolute(mode2), qDB2.getyCoordAbsolute(mode2));
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
			boolean clicked=false;
			for(int n=0;n<quizIn.size();n++)
			{
				if(quizIn.get(n).contains(e.getX(),e.getY()))
				{
					clicked=true;
					System.out.println(quizIn.get(n).id+" "+"In " + quizIn.get(n).whichOne);
					oldClick.add(new GraphClick(0, quizIn.get(n).id, quizIn.get(n).whichOne));
				}
			}
			for(int n=0;n<quizOut.size();n++)
			{
				if(quizOut.get(n).contains(e.getX(),e.getY()))
				{
					clicked=true;
					System.out.println(quizOut.get(n).id+" "+"Out " + quizOut.get(n).whichOne);
					oldClick.add(new GraphClick(1, quizOut.get(n).id, quizOut.get(n).whichOne));
				}
			}
			for(int n=0;n<quizWrong.size();n++)
			{
				if(quizWrong.get(n).contains(e.getX(),e.getY()))
				{
					clicked=true;
					System.out.println(quizWrong.get(n).id+" "+"Wrong " + quizWrong.get(n).whichOne);
					oldClick.add(new GraphClick(2, quizWrong.get(n).id, quizWrong.get(n).whichOne));
				}
			}
			
			
		if(e.getButton()==MouseEvent.BUTTON1)
		{
			
			for(int n=0;n<quizRect.size();n++)
			{
				if(quizRect.get(n).contains(e.getX(),e.getY()))
				{
					clicked=true;
				
					onQuizClicked(quizRect.get(n).id);
				}
			}
			if(!clicked)
			{
				System.out.println("Klikniecie w tlo, kasuje");
				oldClick.clear();
			}
			else
			{
				repaint();
			}
			if(oldClick.size()>1)
			{
				System.out.println("Mam dwa clickniecia");
				GraphClick outClick= null;
				GraphClick inClick= null;
				
				if(oldClick.get(0).type==0)
				{
					inClick=oldClick.get(0);
				}
				if(oldClick.get(1).type==0)
				{
					inClick=oldClick.get(1);
				}
				
				if(oldClick.get(0).type==1)
				{
					outClick=oldClick.get(0);
				}
				if(oldClick.get(1).type==1)
				{
					outClick=oldClick.get(1);
				}
				if(oldClick.get(0).type==2)
				{
					outClick=oldClick.get(0);
				}
				if(oldClick.get(1).type==2)
				{
					outClick=oldClick.get(1);
				}
				if((inClick!=null && outClick!=null) && !inClick.equals(outClick))
				{
					String id=outClick.id;
					int whichOne=outClick.whichOne;
					int type= outClick.type;
					System.out.println("Przeszlo");
					if(type==1)
					{
						System.out.println("Przypisuje correct nr"+whichOne+" grafu "+id);
					}
					if(type==2)
					{
						System.out.println("Przypisuje wrong nr"+whichOne+" grafu "+id);
					}
					System.out.println("do wejscia grafu "+inClick.id);
					
					boolean stillLooping=true;
					for(int n=0;n<quizList.size() && stillLooping;n++)
						for(int m=0;m<quizList.size() && stillLooping;m++)
							if(quizList.get(n).id.equals(outClick.id) && quizList.get(m).id.equals(inClick.id))
							{
								if(type==1)
									quizList.get(n).correct[whichOne]=inClick.id;
								if(type==2)
									quizList.get(n).wrong=inClick.id;
								stillLooping=false;
								onGraphEdit();
							}
					repaint();
				}
				else
					System.out.println("Nie przeszlo");
				oldClick.clear();
			}
		}
		if(e.getButton()==MouseEvent.BUTTON3)
		{
			
			
			if(oldClick.size()>1)
			{
				oldClick.clear();
				System.out.println("anulowano, kasuje");
			}
			
			else if(oldClick.size()==1)
			{
				String id=oldClick.get(0).id;
				int whichOne=oldClick.get(0).whichOne;
				int type=oldClick.get(0).type;
				if(type==1 || type==2)
				{
				for(int n=0;n<quizList.size();n++)
						if(quizList.get(n).id.equals(id))
						{
							if(type==1)
								quizList.get(n).correct[whichOne]="";
							if(type==2)
								quizList.get(n).wrong="";
							onGraphEdit();
							
						}
				}
				else
				{
					for(int n=0;n<quizList.size();n++)
					{
						if(quizList.get(n).wrong.equals(id))
						{
								quizList.get(n).wrong="";
								
						}
						for(int m=0;m<quizList.get(n).correct.length;m++)
						{
							if(quizList.get(n).correct[m].equals(id))
							{
								quizList.get(n).correct[m]="";
								
							}
							
						}
					}
					onGraphEdit();
				}
				oldClick.clear();
				System.out.println("kasuje");
				repaint();
			}
			
			for(int n=0;n<quizRect.size();n++)
			{
				if(quizRect.get(n).contains(e.getX(),e.getY()))
				{
					clicked=true;
					 JPopupMenu menu = new JPopupMenu("Popup");
					 JMenuItem item = new JMenuItem("Usuń quiz");
					 item.setToolTipText(quizRect.get(n).id);
					 menu.add(item);
					 item.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							// TODO Auto-generated method stub
							JMenuItem src=(JMenuItem)arg0.getSource();
							String id=src.getToolTipText();
							
							onQuizDelete(id);
						}
					    });
					 menu.show(e.getComponent(), e.getX(), e.getY());
				
				}
			}
			if(!clicked)
			{
				 JPopupMenu menu = new JPopupMenu("Popup");
				 JMenuItem item = new JMenuItem("Dodaj nowy quiz");
				 menu.add(item);
				 item.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						JMenuItem src=(JMenuItem)arg0.getSource();
						onQuizAdd();
					}
				    });
				 
				 menu.show(e.getComponent(), e.getX(), e.getY());
			}
			
		}
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void onQuizClicked(String id)
	{
		int quizId = Integer.parseInt(id);
		ProjectMainView.invokeNewQuizView(quizId);
		System.out.println("Kliklem quiz "+ id);
	}
	
	public void onGraphEdit()
	{
		ProjectMainView.quizConnectionsChanged(quizList);
		System.out.println("Zlapalem zmiane!");
	}
	
	public void onQuizDelete(String id)
	{
		int quizId = Integer.parseInt(id);
		quizList.remove(quizId);
		ProjectMainView.invokeQuizRemoving(quizId);
		System.out.println("Usuwam quiz "+ id);
	}
	
	public void onQuizAdd()
	{
		ProjectMainView.invokeNewQuizView();
		System.out.println("Dodaje nowy quiz");
	}
}
