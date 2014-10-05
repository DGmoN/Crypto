package sic.input;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.Window;

public class GUI extends Window {

	ButtonS FileSelection, Encrypt, Decript, Close, OpenOrderWindow;
	Label OutputF, TargetF, Working;
	Font labelFont = new Font("Verdana", Font.BOLD, 10);

	public GUI() {
		super(new Frame());
		this.setLayout(null);
		this.setBackground(Color.black);
		this.setLocation(
				Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 100,
				Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 100);
		this.setSize(200, 200);
		this.setVisible(true);
		this.setAlwaysOnTop(true);

		Close = new ButtonS("x", "ENDS:exit");
		Close.setLocation(5, 5);
		Close.setSize(10, 10);
		Close.setVisible(true);
		this.add(Close);

		FileSelection = new ButtonS("File...", "CRYPT:chooseFile");
		FileSelection.setSize(50, 24);
		FileSelection.setLocation(5, 20);

		FileSelection.setVisible(true);
		this.add(FileSelection);

		Encrypt = new ButtonS("Encrypt", "CRYPT:encrypt");
		Encrypt.setLocation(5, 45);
		Encrypt.setSize(50, 24);

		Encrypt.setVisible(true);
		Encrypt.setEnabled(false);
		if (START.SESSION != null)
			if (START.SESSION.outputFile != null && START.SESSION.TRGT != null)
				Encrypt.setEnabled(true);
		this.add(Encrypt);

		TargetF = new Label();
		if (START.SESSION != null)
			if (START.SESSION.TRGT != null)
				TargetF.setText(START.SESSION.TRGT.getName());
		TargetF.setSize(100, 24);
		TargetF.setFont(labelFont);
		TargetF.setBackground(Color.WHITE);
		TargetF.setLocation(60, 45);
		this.add(TargetF);

		OutputF = new Label();
		if (START.SESSION != null)
			if (START.SESSION.outputFile != null)
				OutputF.setText(START.SESSION.outputFile.getName());
		OutputF.setSize(100, 24);
		OutputF.setFont(labelFont);
		OutputF.setBackground(Color.WHITE);
		OutputF.setLocation(60, 70);
		this.add(OutputF);

		OpenOrderWindow = new ButtonS("Methods?", "CRYPT:OpenOreder");
		OpenOrderWindow.setVisible(true);
		OpenOrderWindow.setLocation(60, 104);
		OpenOrderWindow.setSize(100, 24);
		this.add(OpenOrderWindow);

		Working = new Label("Ready");
		Working.setLocation(5, 171);
		Working.setSize(100, 24);
		Working.setVisible(true);
		Working.setBackground(Color.green);

		this.add(Working);
	}

}
