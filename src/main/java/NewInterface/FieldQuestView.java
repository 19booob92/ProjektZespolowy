package NewInterface;

import javax.swing.JPanel;

import Map.GoogleMapPanel;
import Quest.QuestFactory;
import Quest.QuestPoint;
import Quest.QuestType;

public class FieldQuestView extends QuestView {

	private QuestPoint quest;
	private GoogleMapPanel googlePanel;
	
	public FieldQuestView() {
		super();
		
		googlePanel = new GoogleMapPanel(338,329);
		
		//GoogleMap Listeners, labels etc
		googlePanel.addMouseListener(googlePanel);
        googlePanel.addMouseMotionListener(googlePanel);
        googlePanel.addMouseWheelListener(googlePanel);
        googlePanel.addKeyListener(googlePanel);
        googlePanel.setFocusable(true);
        googlePanel.setBounds(101, 231, 338, 329);
        
	    //endof
		add(googlePanel);
		quest = QuestFactory.createQuest(QuestType.TEXTQUEST);
	}

}
