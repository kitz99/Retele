public class HammingSimulator {
	Matrix generator, checker, receiver, encoded, checkresult, received, decoded, data;
	
	public HammingSimulator(Matrix data) {
		this();
		setData(data);
	}
	
	public HammingSimulator() {
		generator = new Matrix("g.txt");
		checker = new Matrix("h.txt");
		receiver = new Matrix("r.txt");
	}
	
	/**
	 * specifies the line of data to work with
	 * @param data data to work with
	 */
	public void setData(Matrix data) {
		this.data = data;
		System.out.println("Data to work with is:");
		data.printMatrixLine();
	}
	
	/**
	 * encodes the data
	 */
	public void encode() {
		encoded = Matrix.multiply(generator, data);
		encoded.modulate();
		System.out.println("Encoding data...");
		encoded.printMatrixLine();
	}
	
	/**
	 * simulates the transmission of the data
	 */
	public void transmit() {
		received = encoded;
		System.out.println("Transferring data...");
	}
	
	/**
	 * checks the transmitted data for errors and corrects them
	 */
	public void check() {
		checkresult = Matrix.multiply(checker, received);
		checkresult.modulate();
		System.out.println("Checking for transmission errors...");
		int error = getError();
		if(error == 0) {
			System.out.println("Data was transmitted without errors.");
		}
		else {
			System.out.println("Error during data transmission...");
			correctError(error);
		}
	}
	
	/**
	 * finds if and where there is an error in the transmitted data
	 * @return position of the error
	 */
	public int getError() {
		int sum = 0;
		for(int j = 0; j < checkresult.getMatrix().length; j++) {
			System.out.print(checkresult.get(j, 0) + " * 2^"+j);
			if(j < checkresult.getMatrix().length-1) {
				System.out.println(" + ");
			}
			else {
			System.out.println(" = "+sum);
			}
			sum += (checkresult.get(j, 0)) * Math.round(Math.pow(2, j));
		}
		return sum;
	}
	
	/**
	 * swaps the bit at the given error position
	 * @param errorpos position of the error
	 */
	public void correctError(int errorpos) {
		double prev, now;
		prev = received.get(errorpos-1, 0);
		now = (received.get(errorpos-1, 0)+1)%2;
		received.set(errorpos-1, 0, now);
		System.out.println("Fixing error at position "+errorpos+"...");
		System.out.println("Old value: "+prev+", new value: "+now);
	}
	
	/**
	 * decodes the transmitted data
	 */
	public void decode() {
		decoded = Matrix.multiply(receiver, received);
		System.out.println("Decoding data...");
		decoded.printMatrixLine();
	}
	
	/**
	 * performs a full transmission set of encode, transmit, error check and decode
	 */
	public void doTransmission() {
		encode();
		transmit();
		check();
		decode();
	}
}
