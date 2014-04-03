import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;


public class ReteleLab {
	
	private static String pune0(String cadrul){
		char C [] = cadrul.toCharArray();
		int nr1 = 0;
		for(int k = 0; k < C.length; k++){
			if(C[k] == '1') nr1++;
			if(C[k] == '0') nr1 = 0;
			if(nr1 == 5) {
				char dePus = '0';
				String aux = "";
				for(int t = 0; t <= k; t++){
					aux = aux + C[t];
				}
				aux = aux + dePus;
				for(int t = k+1; t < C.length; t++){
					aux = aux + C[t];
				}
				C = aux.toCharArray();
			}
		}
		cadrul = new String(C);
		return cadrul;
	}
	
	private static String putFlags(String cadrul){
		String flag = "01111110";
		flag = flag + cadrul;
		cadrul = flag;
		flag = "01111110";
		cadrul = cadrul + flag;
		
		return cadrul;
	}
	
	public static boolean verifica(){
		String input = "";
		for(int i = 0; i < informatie.size(); i++){
			input = input + informatie.get(i).toString();
		}
		System.out.println("Input: " + input);
		System.out.println("Output:" + output);
		if(input.equals(output)) return true;
		return false;
	}
	
	public static void decode(String cadrul){
		char C [] = cadrul.toCharArray();
		String aux = "";
		for(int i = 8; i < C.length - 8; i ++){
			aux = aux + C[i];
		}
		
		C = aux.toCharArray();
		aux = "";
		int nr1 = 0;
		for(int i = 0; i < C.length; i++){
			if(C[i] == '1') nr1++;
			if(C[i] == '0') nr1 = 0;
			if(nr1 == 5){
				nr1 = 0;
				aux = aux + C[i];
				i++;
			}
			else aux = aux + C[i];
			
		}
		output = output + aux;
	}
	
	public static void encode(LinkedList<Integer> info, int dimCadru){
		String cadrul = "";
		int dim = (dimCadru - 16) - (dimCadru % 5);
		int iteratii = info.size() / dim;
		int indexP = 0;
		
		for(int i = 0; i < iteratii; i++){
			int j = 0;
			while(j < dim){
				cadrul = cadrul + info.get(indexP).toString();
				j++; 
				indexP++;
			}
			cadrul = pune0(cadrul);
			
			cadrul = putFlags(cadrul);
			decode(cadrul);
			cadrul = "";
		}
		cadrul = "";
		
		if(indexP != info.size()){
			
			while(indexP != info.size()){
				cadrul = cadrul + info.get(indexP).toString();
				indexP++;
			}
			cadrul = pune0(cadrul);
			cadrul = putFlags(cadrul);
		    
			decode(cadrul);
			cadrul = "";
		}
	}
	
	static LinkedList<Integer> informatie;
	static int n, dimCadru;
	static String output;
	
	public static void main(String[] args) {
		output = "";
		Scanner sc = new Scanner(System.in);
		System.out.print("Dimensiunea informatiei: ");
		n = sc.nextInt();
		System.out.print("Dimensiunea cadrului:");
		dimCadru = sc.nextInt();
		informatie = new LinkedList<>();
		
		try {
			sc = new Scanner(new File("info.txt"));
			for(int i = 0; i < n; i++){
				informatie.add(sc.nextInt());
			}
			sc.close();
		}
		catch(Exception ex){
			System.out.println("Eroare citire din fisier");
		}
		
		encode(informatie, dimCadru);
		
		System.out.println(verifica());
	}

}
