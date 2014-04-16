import java.io.IOException;
import java.util.Scanner;

class CRC {

	static int[] impartire(int div[], int divizor[], int rem[]) {
		int curr = 0;
		while (true) {
			for (int i = 0; i < divizor.length; i++)
				rem[curr + i] = (rem[curr + i] ^ divizor[i]);

			while (rem[curr] == 0 && curr != rem.length - 1)
				curr++;

			if ((rem.length - curr) < divizor.length)
				break;
		}
		return rem;
	}

	static int[] info;
	static int[] div;
	static int[] divizor;
	static int[] rem;
	static int[] crc;
	static int data_bits, divizor_bits, tot_length;

	public static void main(String args[]) throws IOException {
		Scanner sc = new Scanner(System.in);

		System.out.println("Numarul de biti : ");

		data_bits = sc.nextInt();
		info = new int[data_bits];

		System.out.println("Sirul de biti : ");
		for (int i = 0; i < data_bits; i++)
			info[i] = sc.nextInt();

		System.out.println("Numarul de biti ai divizorului : ");
		divizor_bits = sc.nextInt();
		divizor = new int[divizor_bits];

		System.out.println("Bitii divizorului : ");
		for (int i = 0; i < divizor_bits; i++)
			divizor[i] = sc.nextInt();

		sc.close();

		tot_length = data_bits + divizor_bits - 1;

		div = new int[tot_length];
		rem = new int[tot_length];
		crc = new int[tot_length];

		/*------------------ Codificare -----------------------*/
		for (int i = 0; i < info.length; i++)
			div[i] = info[i];

		for (int j = 0; j < div.length; j++) {
			rem[j] = div[j];
		}

		rem = impartire(div, divizor, rem);

		for (int i = 0; i < div.length; i++) // append dividend and ramainder
		{
			crc[i] = (div[i] ^ rem[i]);
		}

		System.out.println();
		System.out.println("Sirul codificat : ");
		for (int i = 0; i < crc.length; i++)
			System.out.print(crc[i]);

		/*-------------------detectare eroare---------------------*/
		System.out.println();
		System.out.println("Mesajul receptionat(" + tot_length + ") biti: ");
		for (int i = 0; i < crc.length; i++)
			crc[i] = sc.nextInt();

		for (int j = 0; j < crc.length; j++) {
			rem[j] = crc[j];
		}

		rem = impartire(crc, divizor, rem);

		for (int i = 0; i < rem.length; i++) {
			if (rem[i] != 0) {
				System.out.println("Eroare");
				break;
			}
			if (i == rem.length - 1)
				System.out.println("Fara eroare");
		}
	}
}
