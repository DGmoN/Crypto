package sic.input;

import java.awt.Frame;
import java.awt.Window;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import sic.incryption.Incription;
import Formating.Strings.LINES;

public class IncryptionOrderin extends Window {

	public ButtonS Close, Options[];
	JPanel OptionS = new JPanel();
	public JTextArea TA = new JTextArea();

	public LINES lines = new LINES();

	public IncryptionOrderin() {
		super(new Frame());
		this.setAlwaysOnTop(true);
		Close = new ButtonS("x", "CRYPT:closeOrder");
		Close.setLocation(5, 5);
		Close.setSize(10, 10);
		Close.setVisible(true);
		this.add(Close);

		OptionS.setVisible(true);
		OptionS.setLocation(5, 15);
		OptionS.setBorder(BorderFactory.createEtchedBorder(1));
		OptionS.setLayout(null);
		this.add(OptionS);

		this.setLayout(null);
		this.setSize(200, 500);

		TA.setSize(100, 195);
		TA.setLocation(100, 5);
		TA.setVisible(true);
		TA.setEditable(false);
		this.add(TA);

		int x = 0;
		Options = new ButtonS[Incription.getCandidate().length];
		for (String a : Incription.getCandidate()) {
			Options[x] = new ButtonS(a, "CRYPT:addToOrder", a);
			Options[x].setLocation(5, ((x) * 24) + ((x) * 5) + 5);
			Options[x].setSize(75, 24);
			Options[x].setVisible(true);
			OptionS.add(Options[x++]);
		}
		OptionS.setSize(95, (24 * Options.length) + (5 * Options.length) + 10);
	}

}
