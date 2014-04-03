
public class Aufgaben {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HammingSimulator h = new HammingSimulator();
		h.setData(new Matrix("p1.txt"));
		h.doTransmission();
		
		System.out.println();
		
		h.setData(new Matrix("p2.txt"));
		h.doTransmission();
		
		
		for(int i = 1; i <= 3; i++) {
			System.out.println();
			
			System.out.println("Received data: ");
			h.received = new Matrix("a"+i);
			h.received.printMatrixLine();
			h.check();
			h.decode();
		}
		
		Matrix.modulate(Matrix.multiply(new Matrix("m1"), new Matrix("m2")), 11).printMatrix();
	}

}
