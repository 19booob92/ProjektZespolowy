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
private JTextArea questContent;
private JTextField textField;

private JLabel lblAnswer;
private JLabel lblQuestContent;
private Border border = BorderFactory.createLineBorder(Color.BLACK);
        
        private JButton btnCreateQuest;

public TextQuestView() {
super();	
this.add(new JLabel("Field"));
setSize(panelWidth, panelHeight);
lblQuestContent = new JLabel("Tre\u015B\u0107 Quizu");
lblQuestContent.setBounds(26, 239, 75, 14);
add(lblQuestContent);

questContent = new JTextArea();
questContent.setBorder(BorderFactory.createCompoundBorder(border,
BorderFactory.createEmptyBorder(10, 10, 10, 10)));
questContent.setLineWrap(true);
questContent.setBounds(26, 264, 567, 61);
add(questContent);

lblAnswer = new JLabel("Odpowied\u017A");
lblAnswer.setBounds(26, 353, 87, 14);
add(lblAnswer);

textField = new JTextField();
textField.setBounds(26, 378, 567, 29);
add(textField);
textField.setColumns(10);
quest = QuestFactory.createQuest(QuestType.FIELDQUEST);
                
                btnCreateQuest =new JButton("Dodaj zagadke");
                btnCreateQuest.setBounds(26,440,120,30);
                btnCreateQuest.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                    quest.setQuestAnswer(textField.getText());
                    quest.setQuestDescription(questContent.getText());
                    quest.setQuestName("Zagadka1");
                    quest.setQuestTimeout(0);
                    String s = quest.getPicturePaths().get(0);
                    XmlBuilder xml = new XmlBuilder("Tytul");
                    ArrayList<String> list = new ArrayList<String>();
                    list.add(quest.getQuestDescription());
                    xml.addQuizText(quest.getQuestName(), quest.getPicturePaths(), quest.getSoundPaths(), list, "prenote", "postnote", "1", 10, "06-08-1992 15:13", quest.getQuestAnswer(), (int) quest.getQuestTimeout(), null);
                        try {
                            xml.saveXml();
                        } catch (TransformerException ex) {
                            Logger.getLogger(TextQuestView.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                
                add(btnCreateQuest);
}
}