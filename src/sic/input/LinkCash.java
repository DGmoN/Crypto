package sic.input;

import javax.swing.JFileChooser;

import quickLinks.QuickLinks;
import sic.incryption.Incription;

public class LinkCash extends QuickLinks {

	IncryptionOrderin a;
	int index = 1;

	Incription[] INCRYPTIONS;

	String location = "";

	public LinkCash() {
		super("CRYPT");
	}

	public void encrypt() {

		if (START.SESSION.IncriptionOrder != null) {
			START.SESSION.encrypt();

		}
	}

	public void decrypt() {

	}

	public void OpenOreder() {
		START.SESSION.Window.setVisible(false);
		a = new IncryptionOrderin();
		a.setVisible(true);
		if (START.SESSION.FilesReady)
			START.SESSION.Window.Encrypt.setEnabled(true);
	}

	public void addToOrder(String d) {
		a.lines.add(d);
		String temp;
		a.lines.reset();
		a.TA.setText("");
		INCRYPTIONS = new Incription[a.lines.getAllLines().length];

		while ((temp = a.lines.nextLine()) != "EOL") {
			a.TA.setText(a.TA.getText() + temp);
		}
	}

	public void closeOrder() {
		a.setVisible(false);
		START.SESSION.setEncryptions(a.lines.getAllLines());
		START.SESSION.Window.setVisible(true);
	}

	public void chooseFile() {
		START.SESSION.Window.setVisible(false);
		final JFileChooser a = new JFileChooser(location);
		int ret = a.showOpenDialog(null);
		if (ret == 0) {
			try {
				if (START.SESSION.TRGT == null)
					START.SESSION.setImputStream(a.getSelectedFile()
							.getAbsolutePath());
				else {
					START.SESSION.setOutpuFile(a.getSelectedFile()
							.getAbsolutePath());

				}
				location = a.getSelectedFile().getPath();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("Selected: " + a.getSelectedFile().getName());
		}
		START.SESSION.Window.setVisible(true);

	}

}
