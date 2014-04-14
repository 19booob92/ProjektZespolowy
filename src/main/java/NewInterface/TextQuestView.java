package NewInterface;

import Editor.XmlBuilder;
import Quest.Campaign;
import Quest.QuestFactory;
import Quest.QuestPoint;
import Quest.QuestType;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.xml.transform.TransformerException;

public class TextQuestView extends QuestView {

private QuestPoint quest;
protected JTextField textAnswer;
protected JTextField textGoTo;

private JLabel lblGoTo;
private JLabel lblAnswer;


private Border border = BorderFactory.createLineBorder(Color.BLACK);
        

public TextQuestView() {
            super();	
            this.add(new JLabel("Field"));
            setSize(panelWidth, panelHeight);
            
            lblGoTo = new JLabel("Następna zagadka");
            lblGoTo.setBounds(26,283,120,29);
            add(lblGoTo);
            
            textGoTo = new JTextField();
            textGoTo.setBounds(26,308,60,29);
            add(textGoTo);

            lblAnswer = new JLabel("Odpowied\u017A");
            lblAnswer.setBounds(26, 353, 87, 14);
            add(lblAnswer);

            textAnswer = new JTextField();
            textAnswer.setBounds(26, 378, 567, 29);
            add(textAnswer);
            textAnswer.setColumns(10);
            quest = QuestFactory.createQuest(QuestType.FIELDQUEST);
                
}
}