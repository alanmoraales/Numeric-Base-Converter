import java.util.Scanner;

//import Interfaz.PanelEntrada;

public class Convertir {

	private static Scanner entradaString;
	private static Scanner entradaInt;
	
	private static Conversor conversor;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		entradaString = new Scanner(System.in);
		entradaInt = new Scanner(System.in);
		conversor = new Conversor();
		
	    int opcion=1;
	    
	    while(opcion!=2) {
	    	
	    	imprimirMenu();
	    	opcion = entradaInt.nextInt();
	    	
	    	switch(opcion) {
	    	case 1:
	    		convertirNumero();
	    		break;
	    	case 2:
	    		break;
	    	default:
	    		System.out.println("\n¡Introduce una opción válida!");	
	    		break;
	    	}
	    }
			
	}
	
	public static void imprimirMenu() {
		System.out.println("\n\n----- Conversión de Bases -----\n");
		System.out.println("1. Convertir un número.");
		System.out.println("2. Salir");
		System.out.println("\nOpción:");
		
	}
	
	public static void convertirNumero() {
		
		String numero;
		int base; 
		int baseObjetivo;
		float auxResultado;
		String resultado;
		
		System.out.println("Introduce un número: ");
		numero = entradaString.nextLine();
		System.out.println("Introduce la base del número: ");
		base = entradaInt.nextInt();
		System.out.println("Introduce la base a la que quiere convertir: ");
		baseObjetivo = entradaInt.nextInt();
		
		// corroboramos que la info. sea válida.
		// primero revisamos la base.
		
		if(numeroEsEntero(numero)==true) {
			if(baseEsValida(base) == true) {
				if(numeroEsValido(numero,base) == true) {
					if(baseEsValida(baseObjetivo) == true) {
						// si todos los datos ingresados son correctos.
						auxResultado = conversor.toDecimal(numero, base);
						//System.out.println("El resultado en decimal es: " + auxResultado);
						resultado = conversor.toBaseN(auxResultado, baseObjetivo);
						
						System.out.println("\nEl resultado es: " + resultado);
					}
					else {
						System.out.println("La base a la que quieres convertir no está soportada.");
					}
				}
				else {
					System.out.println("El número que ingresaste no coincide con su base.");
				}
			}
			else {
				System.out.println("La base del número no está soportada.");
			}
		}
		else {
			System.out.println("Introduce un número entero.");
		}
		
		
	}
	
	public static boolean numeroEsEntero(String numero) {
		int index = numero.indexOf("."); // buscamos un punto decimal en el número.
		
		// el método indexOf() devuelve -1 si no encuentra el caracter.
		// entonces, si en cuentra un punto decimal.
		if(index != -1) {
			return false; // el número no es entero.
		}
		
		return true;
	}
	
	
	public static boolean baseEsValida(int base) {
		
		for(int i=2; i<=10; i++) { // si la base es igual algún número entre 2 y 10, es válida.
			if(base == i) {
				return true;
			}
		}
		
		// si la base es 16.
		if(base == 16) { 
			return true;
		}
		
		// si no es ninguna.
		return false;
	}
	
	public static boolean numeroEsValido(String numero, int base) {
		
		char[] hexadecimal = {'A','B','C','D','E','F'}; // CARACTERES ESPECIALES ACEPTADOR POR EL SISTEMA HEXADECIMAL
		char[] arrayNumero = numero.toCharArray(); // convertimos el numero a un arreglo de caracteres.
		int contador=0; 
		String auxString, auxString2; // para comparar.
		
		// si la base es diferente de 16.
		if(base != 16) {
			// para bases del 2-10.
			// desde 0 hasta la longitud del numero.
			for(int i=0; i<numero.length(); i++) {
				auxString = "" + arrayNumero[i];
				//System.out.println("" + numero.length());
				// numeros del 0 hasta base-1 (números aceptados por esa base)
				for(int j=0; j<base; j++){
					auxString2 = Integer.toString(j);
					if(auxString.equals(auxString2)) {
						contador++;
						//System.out.println("" + contador);
						break;
					}
				}	
			}
		}
		else {
			// contamos numeros
			// para base hexadecimal.
			// puede aceptar numeros del 1-9 y las letras A,B,C,D,E,F.
			for(int i=0; i<numero.length(); i++) {
				auxString = "" + arrayNumero[i];
				for(int j=0; j<=9; j++) {
					auxString2 = Integer.toString(j);
					if(auxString.equals(auxString2)) {
						contador++;
						break;
					}
				}
			}
			// contamos letras
			// si no coincide con alguno de los números, probamos con las letras.
			for(int i=0; i<numero.length(); i++) {
				for(int k=0; k<hexadecimal.length; k++) {
						if(arrayNumero[i] == hexadecimal[k]) {
							contador++;
							break;
						}
				}
			}
			
		}
		
		//System.out.println("" + contador);
		// si no todos los numeros coincidieron alguno de los numeros aceptados por la base.
		if(contador != numero.length()) {
			return false;
		}
		
		return true; // si todos coincidieron, entonces el número es válido.
	}
	

}
