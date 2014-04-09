package NewInterface;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.border.EtchedBorder;

public class ProjectOptionsView extends JPanel {

	private static final int panelWidth = 1000;
	private static final int panelHeight = 800;
	private JTextField tfGameTitle;

	private JLabel lblGameTitle;
	private JLabel lblIntroModulePics;
	private JLabel lblOutroModulePics;

	private JList introPics;
	private JList outroPics;
	
	public ProjectOptionsView() {
		this.setSize(panelWidth, panelHeight);
		setLayout(null);

		lblGameTitle = new JLabel("Tytu\u0142 gry");
		lblGameTitle.setBounds(10, 28, 81, 14);
		add(lblGameTitle);

		tfGameTitle = new JTextField();
		tfGameTitle.setBounds(10, 53, 290, 28);
		add(tfGameTitle);
		tfGameTitle.setColumns(10);

		lblIntroModulePics = new JLabel("Modu\u0142 intra");
		lblIntroModulePics.setBounds(10, 101, 81, 14);
		add(lblIntroModulePics);

		lblOutroModulePics = new JLabel("Modu\u0142 outra");
		lblOutroModulePics.setBounds(10, 215, 81, 14);
		add(lblOutroModulePics);

		introPics = new JList();
		introPics.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		introPics.setBounds(10, 124, 290, 69);
		add(introPics);

		outroPics = new JList();
		outroPics.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		outroPics.setBounds(10, 240, 290, 69);
		add(outroPics);

		JLabel lblMiejsceNaGraf = new JLabel(
				"Miejsce na graf kampanii, t.j list\u0119 quiz\u00F3w + graficzn\u0105 reprezentacj\u0119 gry. Tu mo\u017Cna wstawi\u0107 te\u017C wiele innych rzeczy");
		lblMiejsceNaGraf.setBounds(378, 114, 565, 14);
		add(lblMiejsceNaGraf);
	}
}
