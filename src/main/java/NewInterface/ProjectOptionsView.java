package NewInterface;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;

public class ProjectOptionsView extends JPanel {

	private static final int panelWidth = 1000;
	private static final int panelHeight = 800;
	private JTextField tfGameTitle;

	private JLabel lblGameTitle;
	private JLabel lblIntroModulePics;
	private JLabel lblOutroModulePics;

	private JList introPics;
	private JList outroPics;
	private JButton btnDodaj;
	private JButton btnUsun;
	private JButton btnDodaj_1;
	private JButton btnUsun_1;
	
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

		lblIntroModulePics = new JLabel("Obrazy Intra");
		lblIntroModulePics.setBounds(10, 210, 81, 14);
		add(lblIntroModulePics);

		lblOutroModulePics = new JLabel("Obrazy Outra");
		lblOutroModulePics.setBounds(10, 317, 81, 14);
		add(lblOutroModulePics);

		introPics = new JList();
		introPics.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		introPics.setBounds(10, 233, 290, 69);
		add(introPics);

		outroPics = new JList();
		outroPics.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		outroPics.setBounds(10, 342, 290, 69);
		add(outroPics);

		JLabel lblMiejsceNaGraf = new JLabel(
				"Miejsce na graf kampanii, t.j list\u0119 quiz\u00F3w + graficzn\u0105 reprezentacj\u0119 gry. Tu mo\u017Cna wstawi\u0107 te\u017C wiele innych rzeczy");
		lblMiejsceNaGraf.setBounds(314, 60, 565, 14);
		add(lblMiejsceNaGraf);
		
		btnDodaj = new JButton("Dodaj");
		btnDodaj.setBounds(101, 206, 89, 23);
		add(btnDodaj);
		
		btnUsun = new JButton("Usun");
		btnUsun.setBounds(211, 206, 89, 23);
		add(btnUsun);
		
		btnDodaj_1 = new JButton("Dodaj");
		btnDodaj_1.setBounds(101, 313, 89, 23);
		add(btnDodaj_1);
		
		btnUsun_1 = new JButton("Usun");
		btnUsun_1.setBounds(211, 313, 89, 23);
		add(btnUsun_1);
	}
}
