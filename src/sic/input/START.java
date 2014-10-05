/////////////////////////////////////////////////////////////////////////////////////
/* Author: Eslem
 * Purpose: To serve as an encrypter/decrypter for my data and so.
 */

package sic.input;

import static Formating.Misc.Contains;
import static Formating.Misc.extract;
import static Formating.Misc.getIndex;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import quickLinks.QuickLinks;
import sic.incryption.Incription;
import Formating.Strings;
import Tracker.EventTracker;

public class START {

	public static START SESSION; // the instance of the program

	private static QuickLinks LINKCASH = new LinkCash();

	public GUI Window;

	public static final int DefSampleSize = 8; // default amount of bits to be
												// sampled per cycle
	public int SampleRate; // the actual sample rate

	public File TRGT; // the file to be incrypted
	public File outputFile; // the result of the incryption

	public Incription[] IncriptionOrder; // the ordering of phases

	public boolean FilesReady = false;

	InputStream In;
	OutputStream Out;

	static EventTracker Logs = EventTracker.init("Logs");

	/**
	 * Constructor
	 * **/

	public START(String targetFile) throws Exception {
		setImputStream(targetFile);
	}

	public void setImputStream(String File) throws Exception {
		TRGT = new File(File);
		if (!TRGT.exists()) {
			throw new Exception("NO_SUTCH_FILE");
		} else {
			In = new FileInputStream(TRGT);
			if (Window != null)
				Window.TargetF.setText(TRGT.getName());
		}
	}

	/**
	 * Sets target file for output
	 * **/

	public void setOutpuFile(String a) throws Exception {
		outputFile = new File(a);
		if (outputFile.exists()) {
			outputFile.delete();
		}
		outputFile.createNewFile();
		Out = new FileOutputStream(outputFile);
		if (Window != null) {
			Window.OutputF.setText(outputFile.getName());
			if (TRGT != null) {
				FilesReady = true;
				if (IncriptionOrder != null) {
					Window.Encrypt.setEnabled(true);
				}
			}
		}
	}

	public void setEncryptions(String... a) {
		int x = 0;
		SESSION.IncriptionOrder = new Incription[a.length];
		for (String s : a) {
			SESSION.IncriptionOrder[x++] = Incription.getIncryption(s);
		}
	}

	/**
	 * The actual incription
	 * **/
	public void encrypt() {
		if (SampleRate == 0)
			SampleRate = DefSampleSize;

		if (Window != null)
			START.SESSION.Window.Working.setText("WORKING");

		byte[] bytesRead = new byte[SampleRate];
		try {
			Logs.Write("Starting encription -->", 0);
			Logs.Write("Target for incryption: " + TRGT.getAbsolutePath(),
					"FileLength = " + TRGT.length());
			Logs.Write("Output File: " + outputFile.getAbsolutePath(), 0);
			Logs.Write("Incriptions to be preformed--->", 0);
			Logs.Write("Counted: " + IncriptionOrder.length, 0);
			for (Incription a : IncriptionOrder) {
				Logs.Write("\t" + a.Signature, 0);
			}
			for (Incription MODE : IncriptionOrder) {
				Logs.Write("Mode -> " + MODE, 0);
				int bytesReadT = 0;
				while (bytesReadT < TRGT.length()) {
					bytesReadT += In.read(bytesRead);
					Logs.Write("total bytes read =  " + bytesReadT, 0);
					Logs.Write(
							"Bytes = "
									+ DataTypes.ByteConventions
											.bytesToHexes(bytesRead), 0);
					Out.write(MODE.inCrypt(bytesRead));
					Out.flush();
					if (bytesReadT == 0)
						throw new UnknownError("Somting not working");
					bytesRead = new byte[SampleRate];
				}
			}
			Out.close();
			if (Window != null)
				Window.Working.setText("READY");
		} catch (Exception e) {
			Logs.Write(e);
			e.printStackTrace();
		}
	}

	private void decrypt() {
		byte[] bytesRead = new byte[SampleRate];
		try {
			Logs.Write("Starting encription -->", 0);
			for (Incription MODE : IncriptionOrder) {
				Logs.Write("Mode -> " + MODE, 0);
				int bytesReadT = 0;
				while (bytesReadT < TRGT.length()) {
					bytesReadT += In.read(bytesRead);
					Logs.Write("total bytes read =  " + bytesReadT, 0);
					Logs.Write(
							"Bytes = "
									+ DataTypes.ByteConventions
											.bytesToHexes(bytesRead), 0);
					Out.write(MODE.deCrypt(bytesRead));
					Out.flush();
					bytesRead = new byte[SampleRate];
				}
			}
			Out.close();
		} catch (Exception e) {

			Logs.Write(e);
			e.printStackTrace();
		}
		try {
			Out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * main Method: Data assimilation for usage.
	 **/
	public static void main(String[] args) throws Exception {
		Logs.Write("Starting up", 0);
		QuickLinks.init();
		if (args.length > 0) {
			Logs.Write("Running lowLevel", 0);
			Logs.Write(args);
			if (Contains("-f", args)) { // checks to see if file is specified
				SESSION = new START(args[getIndex("-f", args) + 1]); // sets the
																		// target
																		// dir
																		// to
																		// the
																		// value
																		// following
																		// '-f'
				if (Contains("-o", args)) { // is output spesified?
					SESSION.setOutpuFile(args[getIndex("-o", args) + 1]);// if
																			// true
																			// set
																			// so
				} else {
					SESSION.setOutpuFile("OUT-" + Strings.getTime());// else set
																		// to
																		// 'OUT_+CurrentSystemTime'
				}
				if (Contains("-s", args))// is sample rate preset
					SESSION.SampleRate = Integer // yes = assign sample rate
							.parseInt(args[getIndex("-s", args) + 1]);
				else
					SESSION.SampleRate = DefSampleSize; // no = default sample
														// rate
				if (Contains("-i", args)) { // is incription specified?
					SESSION.setEncryptions(Strings.objectArrToStrings(extract( // yes
							// =
							// get
							// and
							// pass
							// to
							// session
							getIndex("-i", args), args.length, args)));

				} else {
					throw new Exception("NO_METOD_INDICATED"); // no = problems
				}
				if (Contains("-e", args))
					SESSION.encrypt();
				else if (Contains("-d", args))
					SESSION.decrypt();
				else
					throw new Exception(
							"NO_MODE_SPESEFIED : So what should I do?");
			} else {
				throw new Exception("NO_PATH_SPESAFIED");
			}
		} else {
			Logs.Write("Running user frendly", 0);
			SESSION = new START();
		}

	}

	public START() {
		Window = new GUI();
	}

}
