package sic.incryption;

import java.util.ArrayList;

public abstract class Incription {

	private static ArrayList<Incription> RegisterdTypes = new ArrayList<Incription>();

	private static Incription RAILFENCE = new RailFence();

	public static void register(Incription a) {
		if (!RegisterdTypes.contains(a))
			RegisterdTypes.add(a);
	}

	public static Incription getIncryption(String Sig) {
		Incription ret = null;
		for (Incription a : RegisterdTypes) {
			if (a.Signature.equals(Sig)) {
				ret = a;
				return a;
			}

		}
		return ret;
	}

	public static String[] getCandidate() {
		String[] temp = new String[RegisterdTypes.size()];
		int x = 0;
		for (Incription a : RegisterdTypes) {
			temp[x++] = a.Signature;
		}
		return temp;
	}

	private static class Sic extends Incription {

		public Sic() {
			super("Sic");
		}

		@Override
		public byte[] inCrypt(byte... in) {
			return null;
		}

		@Override
		public byte[] deCrypt(byte... in) {
			return null;
		}

	}

	private static class RailFence extends Incription {

		public RailFence() {
			super("Railfence");
		}

		@Override
		public byte[] inCrypt(byte... in) {
			byte[] returns = new byte[in.length];
			for (int x = 0, y = 0; x < in.length; x++) {
				if ((x) % 2 == 0) {
					returns[x] = in[x + 1];
				} else
					returns[x] = in[x - 1];

			}
			return returns;
		}

		@Override
		public byte[] deCrypt(byte... in) {
			return inCrypt(in);
		}
	}

	public final String Signature;

	public Incription(String Sig) {
		Signature = Sig;
		register(this);
	}

	public abstract byte[] inCrypt(byte... in);

	public abstract byte[] deCrypt(byte... in);

	public String toString() {
		return Signature;
	}

}
