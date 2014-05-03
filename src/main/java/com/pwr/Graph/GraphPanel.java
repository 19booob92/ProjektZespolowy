package com.pwr.Graph;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

public class GraphPanel extends JPanel {
	ArrayList<QuizDataObject> quizList;
	static public int space =70;
	static public int xOffset = 50;
	static public int yOffset = 50;

	public GraphPanel() {
		super();
		quizList = new ArrayList<QuizDataObject>();
		setPreferredSize(new Dimension((int) (200+GraphPanel.xOffset+quizList.size() * (QuizDataObject.getSize()+GraphPanel.space)), (int)(200+GraphPanel.yOffset+quizList.size() * (QuizDataObject.getSize()+GraphPanel.space))));
		
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
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(new Color(0.7f, 0.1f, 0.1f));

		drawQuizList(g);
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
			for (int m = y; m < busyMap.length; m++) {
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
					qDB.draw(g, tx, ty);
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
		for (double n = pitch; n <= 1; n += pitch) {
			p2 = getBezierCurvePoint(n, px1, py1, px2, py2, px3, py3, px4, py4);
			drawLine(g, p1.x, p1.y, p2.x, p2.y);
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
							QuizDataObject.DRAW_MODE_OUT, quizList.get(m),
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

}
