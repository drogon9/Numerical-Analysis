/** Enkripton mesazhin me metoden e enkodimit matricor */
public class Kodimi {
	public byte[] m = new byte[26];

	public Kodimi() {
		byte j = 0;
		for(byte i = 97; i <= 122; i++)
			m[j++] = i;
	}

	/** 
	 * Enkripton mesazhin me metoden e enkodimit matricor
	 * @param message - mesazhi qe duhet te enkriptohet
	 * @param A - matrica e enkodimit
	 * @param B - vektori i enkodimit
	 * @return - mesazhi i enkriptuar
	 */
	public byte[] Encipher(byte[] message, byte[][] A, byte[] B) {
		byte[] answer = new byte[message.length];
		for (byte i = 0; i < message.length; i+=2) {  
			answer[i] = (byte)(m[(A[0][0]*(message[i] - 19) +  
					A[0][1]*(message[i+1] - 19) + B[0]) % 26]);
			answer[i+1]=(byte)(m[(A[1][0]*(message[i] - 19) + 
					A[1][1]*(message[i+1] - 19) + B[1]) % 26]);
		}
		return answer;
	}

	/**
	 * Dekripton mesazhin me metoden e enkodimit matricor
	 * @param message - mesazhi qe duhet te dekriptohet
	 * @param A - matrica me te cilen do te behet dekriptimi
	 * @param B - vektori per dekriptim (i njejte me ate per enkriptim)
	 * @return - mesazhi i dekriptuar
	 */
	public byte[] Decipher(byte[] message, byte[][] A, byte[] B) {
		byte[] answer = new byte[message.length];
		for (int i = 0; i < message.length; i+=2) {
			answer[i] = (byte)(m[(A[0][0]*(message[i] - 
					B[0] + 7) + A[0][1]*(message[i+1] - B[1] + 7)) % 26]);
			answer[i+1] = (byte)(m[(A[1][0]*(message[i] - 
					B[0] + 7) + A[1][1]*(message[i+1] - B[1] + 7)) % 26]);
		}
		return answer;
	}
	
	public static void main(String args[]) {

		Kodimi c = new Kodimi();
		String msg = "theend"; 
		byte[][] A = new byte[2][2];	
		byte[][] I = new byte[2][2];	
		byte[] B = new byte[2];
		A[0][0] = 5;
		A[0][1] = 17;
		A[1][0] = 4;
		A[1][1] = 15;
		B[0] = 5;
		B[1] = 2;
		I[0][0] = 17;
		I[0][1] = 5;
		I[1][0] = 18;
		I[1][1] = 23;
		byte[] msgArray = msg.getBytes();
		byte[] enc = c.Encipher(msgArray, A, B);
		String enc1 = new String(enc);
		System.out.println(enc1);
		byte[] dec = c.Decipher(enc, I, B);
		String dec1 = new String(dec);
		System.out.println(dec1);
	}
}
