package Quest;

import Editor.XmlBuilder;
import static Quest.QuestType.TEXTQUEST;
import java.util.ArrayList;

public class Campaign {

	private ArrayList<QuestPoint> quests;
	private ArrayList<TreasureBox> boxes;

	public Campaign() {
		quests = new ArrayList();
		boxes = new ArrayList();
	}

	public void addQuiz(QuestPoint p) {
		quests.add(p);
	}

	public void addTreasureBox(TreasureBox b) {
		boxes.add(b);
	}

	public void setQuiz() {

	}

	public QuestPoint getQuiz() {
		return null;
	}

	public TreasureBox getTreasureBox() {
		return null;
	}
        


	public ArrayList<QuestPoint> getQuizes() {
		return quests;
	}

	public ArrayList<TreasureBox> getTreasureBoxes() {
		return boxes;
	}
}
