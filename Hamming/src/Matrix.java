import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Matrix {
	double[][] matrix;
	int lines, columns;
	
	public Matrix() {
		
	}
	
	public Matrix(String file) {
		readFile(file);
	}
	
	public Matrix(double[][] matrix) {
		this.matrix = matrix;
	}
	
	/**
	 * @return The matrix you are currently working with as an integer array
	 */
	public double[][] getMatrix() {
		return matrix;
	}
	
	/**
	 * Reads a file and turns it into a matrix
	 * @param file the file to read
	 */
	public void readFile(String file) {
		String line = "";
		String column[];
		
		Scanner scanner;
		lines = 0;
		columns = 0;
		try {
			scanner = new Scanner(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			System.err.println("Unable to read file: "+file);
			e.printStackTrace();
			return;
		}
		
		while(scanner.hasNextLine()) {
			lines++;
			line = scanner.nextLine();
		}
		
		column = line.split(" ");
		columns = column.length;
		
		try {
			scanner = new Scanner(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			System.err.println("Unable to read file: "+file);
			e.printStackTrace();
			return;
		}
		
		
		matrix = new double[lines][columns];
		
		for(int i = 0; i < lines; i++) {
			line = scanner.nextLine();
			column = line.split(" ");
			
			for(int j = 0; j < columns; j++) {
				matrix[i][j] = Double.parseDouble((column[j]));
			}
		}
	}
	
	/**
	 * set a value in the matrix
	 * @param line
	 * @param column
	 * @param value
	 */
	
	public void set(int line, int column, double value) {
		matrix[line][column] = value;
	}
	
	public double get(int line, int column) {
		return matrix[line][column];
	}
	
	public void printMatrix() {
		printMatrix(matrix);
	}
	
	public void printMatrixLine() {
		printMatrixLine(matrix);
	}
	
	public static void printMatrix(double[][] m) {
		System.out.print("[");
		for(int i = 0; i < m.length; i++) {
			System.out.print("[");
			for(int j = 0; j < m[0].length; j++) {
				System.out.print(m[i][j] + " ");
			}
			System.out.print("]");
			if(i < m.length-1) {
				System.out.println();
			}
		}
		System.out.println("]");
	}
	
	public static void printMatrixLine(double[][] m) {
		System.out.print("(");
		for(int i = 0; i < m[0].length; i++) {
			for(int j = 0; j < m.length; j++) {
				System.out.print(m[j][i]);
				if(j < m.length-1) {
					System.out.print(" ");
				}
			}
		}
		System.out.println(")");
	}
	
	public static double[][] multiply(double[][] m, double[][] n) {
		if(check(m, n) == false) {
			System.err.println("Matrices length not matching.");
			System.err.println("Number of columns of first matrix has to be number of rows of second matrix.");
			return null;
		}
		
		double[][] o = new double[m.length][n[0].length];
		for(int i = 0; i < o.length; i++) {
			for(int j = 0; j < o[0].length; j++) {
				o[i][j] = 0;
				for(int k = 0; k < n.length; k++) {
					o[i][j] += m[i][k]*n[k][j];
				}
			}
		}
		return o;
	}
	
	public static boolean check(double[][] m, double[][] n) {
		if(m[0].length != n.length) {
			return false;
		}
		return true;
	}
	
	public static boolean check(Matrix M, Matrix N) {
		return check(M.getMatrix(), N.getMatrix());
	}
	
	public static Matrix multiply(Matrix M, Matrix N) {
		return new Matrix(multiply(M.getMatrix(), N.getMatrix()));
	}
	
	public void modulate() {
		modulate(2);
	}
	
	public void modulate(int by) {
		matrix = modulate(matrix, by);
	}
	
	public static double[][] modulate(double[][] m, int by) {
		for(int i = 0; i < m.length; i++) {
			for(int j = 0; j < m[0].length; j++) {
				m[i][j] = m[i][j] % by;
			}
		}
		return m;
	}
	
	public static Matrix modulate(Matrix M, int by) {
		return new Matrix(modulate(M.getMatrix(), by));
	}
	
	public int toInt() {
		int sum = 0;
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				sum += matrix[i][j];
			}
		}
		return sum;
	}
}
