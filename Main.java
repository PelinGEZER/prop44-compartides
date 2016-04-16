package matriu;

public class Main {
	public static void main(String[] args) {
		System.out.println(" ==== TEST 01 - SUMAS =====");
		double[][] datosA01 = {{1,3,1},{1,0,0}};
		double[][] datosB01 = {{0,0,5},{7,5,0}};
		double[][] datosA02 = {{1,3,1},{1,0,0},{4,-1,3}};
		double[][] datosB02 = {{0,0,5},{7,5,0},{-2,0,10}};
		
		Matriu a = new Matriu();
		Matriu b = new Matriu();
		a.setData(datosA02);
		b.setData(datosB02);
		
		System.out.println(" ==== Matriz A =====");
		a.print_matriu();
		System.out.println(" ==== Matriz B =====");
		b.print_matriu();
		System.out.println(" ==== SUMA =====");
		a.sumar(b).print_matriu();		
		System.out.println(" ==== RESTAS =====");
		a.restar(b).print_matriu();
		System.out.println(" ==== TRANSPOSADES =====");
		a.transposar().print_matriu();
		System.out.println();
		b.transposar().print_matriu();
		System.out.println(" ==== MULTIPLICACIO =====");
		a.multiplicar(b).print_matriu();
		System.out.println(" ==== STRASSEN =====");
		a.multiplicar_strassen(b).print_matriu();
		System.out.println(" ==== NORMALITZAR =====");
		a.normalitzar_fila().print_matriu();
		System.out.println();
		b.normalitzar_fila().print_matriu();
		System.out.println(" ===== FILA IESSIMA ====");
		a.get_fila_iessima(0).print_matriu();
		System.out.println();
		a.get_fila_iessima(1).print_matriu();
		System.out.println();
		a.get_fila_iessima(2).print_matriu();
	}
}
