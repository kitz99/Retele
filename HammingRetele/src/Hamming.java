import java.util.Scanner;

class Hamming {

	private static boolean is2pow(int x) {
		while (((x & 1) == 0) && x > 1)
			x >>= 1;
		if (x == 1) return true;
		return false;
	}

	public static void readData() {
		System.out.println("Algoritmul Hamming(7, 4)");
		System.out.println("Introduceti cei 4 biti de date: d1, d2, d3, d4");
		for (int i = 1; i <= 4; i++) {
			d[i] = sc.nextByte();
		}
	}

	private static void encodeData() {
		byte p[] = new byte[4];
		byte data[] = new byte[8];
		// calcularea bitilor de paritate
		p[1] = (byte) ((d[1] + d[2] + d[4]) % 2);
		p[2] = (byte) ((d[1] + d[3] + d[4]) % 2);
		p[3] = (byte) ((d[2] + d[3] + d[4]) % 2);
		
		// Codificare sir
		int pi = 1, di = 1;
		for (int i = 1; i <= 7; i++) {
			if (is2pow(i)) {
				data[i] = p[pi];
				pi++;
			} else {
				data[i] = d[di];
				di++;
			}
		}
		System.out.print("Sirul codificat este: ");
		for(int i = 1; i <= 7; i++)
			System.out.print(data[i] + " ");
		System.out.println();
	}
	
	private static void readMessage() {
		System.out.println("Introduceti sirul receptionat: ");
		for(int i = 1; i <= 7; i++){
			message[i] = sc.nextByte();
		}
	}
	
	private static void decodeAndRepair() {
		byte p[] = new byte[4];
		byte inf[] = new byte[5];
		
		int pi = 1, di = 1;
		for(int i = 1; i <= 7; i++){
			if(is2pow(i)) p[pi++] = message[i];
			else inf[di++] = message[i];
		}
		byte pz[] = new byte[3];
		pz[0] = (byte) ((p[1] + inf[1] + inf[2] + inf[4]) % 2);
		pz[1] = (byte) ((p[2] + inf[1] + inf[3] + inf[4]) % 2);
		pz[2] = (byte) ((p[3] + inf[2] + inf[3] + inf[4]) % 2);
		
		int poz = (int)(pz[2] * 4 + pz[1] * 2 + pz[0]);
		if(poz == 0) {
			System.out.println("Mesaj receptionat fara eroare");
		}
		else{
			System.out.println("Mesaj receptionat cu eroare la pozitia " + poz);
			if(message[poz] == 0) 
				message[poz] = 1;
			else
				message[poz] = 0;
			
			System.out.print("Mesajul corectat : ");
			for(int i = 1; i <= 7; i++)
				System.out.print(message[i] + " ");
		}
		
	}

	static byte message[];
	static byte d[];
	static Scanner sc;

	public static void main(String[] args) {
		d = new byte[5];
		message = new byte[8];
		sc = new Scanner(System.in);
		readData();
		encodeData();
		readMessage();
		decodeAndRepair();
	}
}