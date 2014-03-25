package Editor;

import java.util.ArrayList;

public class QuestPoint  implements DescribeQuest{
	private ArrayList<String> PicturePaths;
	private ArrayList<String> SoundPaths;
	
	private String QuestDescription;
	private String QuestAnswer;
	
	public QuestPoint()
	{
		PicturePaths = new ArrayList<>();
		SoundPaths = new ArrayList<>();
		QuestDescription = "";
		QuestAnswer = "";
	}
}
