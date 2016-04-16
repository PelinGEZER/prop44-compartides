package matriu;

import java.util.ArrayList;
import java.util.Scanner;

// Autor: Ruben Bagan Benavides

/*
double[][] datosA01 = {{1,3,1},{1,0,0}};
double[][] datosB01 = {{0,0,5},{7,5,0}};
double[][] datosA02 = {{1,3,1},{1,0,0},{4,-1,3}};
double[][] datosB02 = {{0,0,5},{7,5,0},{-2,0,10}};
*/

public class MatriuDriver {
	static ArrayList<Matriu> cjtMatrius;
	static Scanner scan = new Scanner(System.in);
	public static int menu() {
		System.out.println("Per obtenir el llistat de identificadors de les matrius tria l'opcio 11");
		System.out.println("0\t Sortir");
		System.out.println("1\t Nova matriu");
		System.out.println("2\t Multiplicar");
		System.out.println("3\t Strassen");
		System.out.println("4\t Sumar");
		System.out.println("5\t Restar");
		System.out.println("6\t Transposar");
		System.out.println("7\t Normalitzar per files");
		System.out.println("8\t Normalitzar per columnes");
		System.out.println("9\t Fila i-èssima");
		System.out.println("10\t Columna i-èssima");
		System.out.println("11\t Llistar matrius");
		System.out.println("Escriu una opció: ");
		return scan.nextInt();
	}
	
	public static void nova_matriu() {
		System.out.println("Introdueix el nombre de files i columnes (ej: 4 2): ");
		int files = scan.nextInt();
		int columnes = scan.nextInt();
		System.out.println("Introdueix les dades: ");
		double[][] dades = new double[files][columnes];
		for (int i = 0; i < files; i++) {
			for (int j = 0; j < columnes; j++) {
				dades[i][j] = scan.nextDouble();
			}
		}
		Matriu m = new Matriu();
		m.set_data(dades);
		cjtMatrius.add(m);
	}

	public static void multiplicar() {
		System.out.println("Introdueix l'identificador de la matriu A: ");
		int idA = scan.nextInt();
		System.out.println("Introdueix l'identificador de la matriu B: ");
		int idB = scan.nextInt();
		Matriu a = cjtMatrius.get(idA);
		System.out.println("Matriu A: ");
		a.print_matriu();
		Matriu b = cjtMatrius.get(idB);
		System.out.println("Matriu B: ");
		b.print_matriu();
		if (a.get_data().length == b.get_data()[0].length) {
			System.out.println("Resultat de la multiplicacio: ");
			a.multiplicar(b).print_matriu();
		}
		else {
			System.out.println("Les dimensions de les matrius son incompatibles");
		}
	}
	
	public static void strassen() {
		System.out.println("Introdueix l'identificador de la matriu A: ");
		int idA = scan.nextInt();
		System.out.println("Introdueix l'identificador de la matriu B: ");
		int idB = scan.nextInt();
		Matriu a = cjtMatrius.get(idA);
		System.out.println("Matriu A: ");
		a.print_matriu();
		Matriu b = cjtMatrius.get(idB);
		System.out.println("Matriu B: ");
		b.print_matriu();
		if (a.get_data().length == b.get_data()[0].length) {
			System.out.println("Resultat de la multiplicacio strassen: ");
			a.multiplicar_strassen(b).print_matriu();
		}
		else {
			System.out.println("Les dimensions de les matrius son incompatibles");
		}
	}
	
	public static void sumar() {
		System.out.println("Introdueix l'identificador de la matriu A: ");
		int idA = scan.nextInt();
		System.out.println("Introdueix l'identificador de la matriu B: ");
		int idB = scan.nextInt();
		Matriu a = cjtMatrius.get(idA);
		System.out.println("Matriu A: ");
		a.print_matriu();
		Matriu b = cjtMatrius.get(idB);
		System.out.println("Matriu B: ");
		b.print_matriu();
		if (a.get_data().length == b.get_data().length &&
		    a.get_data()[0].length == b.get_data()[0].length) {
			System.out.println("Resultat de la suma: ");
			a.sumar(b).print_matriu();
		}
		else {
			System.out.println("Les dimensions de les matrius son incompatibles");
		}
	}
	
	public static void restar() {
		System.out.println("Introdueix l'identificador de la matriu A: ");
		int idA = scan.nextInt();
		System.out.println("Introdueix l'identificador de la matriu B: ");
		int idB = scan.nextInt();
		Matriu a = cjtMatrius.get(idA);
		System.out.println("Matriu A: ");
		a.print_matriu();
		Matriu b = cjtMatrius.get(idB);
		System.out.println("Matriu B: ");
		b.print_matriu();
		if (a.get_data().length == b.get_data().length &&
			a.get_data()[0].length == b.get_data()[0].length) {
			System.out.println("Resultat de la resta: ");
			a.restar(b).print_matriu();
		}
		else {
			System.out.println("Les dimensions de les matrius son incompatibles");
		}
	}
	
	public static void transposar() {
		System.out.println("Introdueix l'identificador de la matriu: ");
		int id = scan.nextInt();
		Matriu a = cjtMatrius.get(id);
		System.out.println("Matriu: ");
		a.print_matriu();
		System.out.println("Resultat de la transposada: ");
		a.transposar().print_matriu();
	}
	
	public static void normalitzar_files() {
		System.out.println("Introdueix l'identificador de la matriu: ");
		int id = scan.nextInt();
		Matriu a = cjtMatrius.get(id);
		System.out.println("Matriu: ");
		a.print_matriu();
		System.out.println("Resultat de la normalitzada per files: ");
		a.normalitzar_fila().print_matriu();		
	}
	
	public static void normalitzar_columnes() {
		System.out.println("Introdueix l'identificador de la matriu: ");
		int id = scan.nextInt();
		Matriu a = cjtMatrius.get(id);
		System.out.println("Matriu: ");
		a.print_matriu();
		System.out.println("Resultat de la normalitzada per columnes: ");
		a.normalitzar_columna().print_matriu();		
	}
	
	public static void fila_iessima() {
		System.out.println("Introdueix l'identificador de la matriu: ");
		int id = scan.nextInt();
		Matriu a = cjtMatrius.get(id);
		System.out.println("Matriu: ");
		a.print_matriu();
		System.out.println("Introdueix la fila [1,"+ a.get_data().length + "]: ");
		int fila = scan.nextInt();
		--fila;
		System.out.println("Fila i-èssima: ");
		a.get_fila_iessima(fila).print_matriu();			
	}
	
	public static void columna_iessima() {
		System.out.println("Introdueix l'identificador de la matriu: ");
		int id = scan.nextInt();
		Matriu a = cjtMatrius.get(id);
		System.out.println("Matriu: ");
		a.print_matriu();
		System.out.println("Introdueix la columna [1,"+ a.get_data()[0].length + "]: ");
		int columna = scan.nextInt();
		--columna;
		System.out.println("Fila i-èssima: ");
		a.get_columna_iessima(columna).print_matriu();
		
	}	

	public static void llistar_matrius() {
		System.out.println("Llistar matrius: ");
		int id = 0;
		for (Matriu m : cjtMatrius) {
			System.out.println("ID: " + id);
			m.print_matriu();
			System.out.println();
			++id;
		}		
	}
	
	public static void main(String[] args) {
		cjtMatrius = new ArrayList<Matriu>();
		int opcio = menu();
		while (opcio != 0) {
			switch (opcio) {
			case 1:
				nova_matriu();
				break;
			case 2:
				if (cjtMatrius.isEmpty()) System.out.println("Necesites crear una matriu primer");
				else multiplicar();
				break;
			case 3:
				if (cjtMatrius.isEmpty()) System.out.println("Necesites crear una matriu primer");
				else strassen();
				break;
			case 4:
				if (cjtMatrius.isEmpty()) System.out.println("Necesites crear una matriu primer");
				else sumar();
				break;
			case 5:
				if (cjtMatrius.isEmpty()) System.out.println("Necesites crear una matriu primer");
				else restar();
				break;
			case 6:
				if (cjtMatrius.isEmpty()) System.out.println("Necesites crear una matriu primer");
				else transposar();
				break;
			case 7:
				if (cjtMatrius.isEmpty()) System.out.println("Necesites crear una matriu primer");
				else normalitzar_files();
				break;
			case 8:
				if (cjtMatrius.isEmpty()) System.out.println("Necesites crear una matriu primer");
				else normalitzar_columnes();
				break;
			case 9:
				if (cjtMatrius.isEmpty()) System.out.println("Necesites crear una matriu primer");
				else fila_iessima();
				break;
			case 10:
				if (cjtMatrius.isEmpty()) System.out.println("Necesites crear una matriu primer");
				else columna_iessima();
				break;
			case 11:
				if (cjtMatrius.isEmpty()) System.out.println("Necesites crear una matriu primer");
				else llistar_matrius();
				break;
			default:
				System.out.println("Opcio incorrecte");
				break;
			}
			opcio = menu();
		}				
	}
}
