package matriu;

// Autor: Ruben Bagan Benavides

public class Matriu {
	private double[][] data;
	
	////////////////////////////////////////////////////////////////////////////
	//								CLASSE PRIVADA				   			  //
	////////////////////////////////////////////////////////////////////////////
	
	private class SubMatriu {
		private double[][] data;
		private int tamany;
		private int filaInici;
		private int columnaInici;
		
		public SubMatriu(double[][] data, int filaInici, int columnaInici, int tamany) {
			this.data = data;
			this.tamany = tamany;
			this.filaInici = filaInici;
			this.columnaInici = columnaInici;
		}
		
		public Matriu sumar(SubMatriu b) {
			double[][] c = new double[this.tamany][this.tamany];
			for (int i = 0; i < this.tamany; i++) {
				for (int j = 0; j < this.tamany; j++) {
					c[i][j] = this.data[filaInici+i][columnaInici+j] + b.data[b.filaInici+i][b.columnaInici+j];
				}
			}

			Matriu m = new Matriu(this.tamany, this.tamany);
			m.setData(c);
			return m;
		}
		
		public Matriu restar(SubMatriu b) {
			double[][] c = new double[this.tamany][this.tamany];
			for (int i = 0; i < this.tamany; i++) {
				for (int j = 0; j < this.tamany; j++) {
					c[i][j] = this.data[filaInici+i][columnaInici+j] - b.data[b.filaInici+i][b.columnaInici+j];
				}
			}

			Matriu m = new Matriu(this.tamany, this.tamany);
			m.setData(c);
			return m;
		}		
		
		public Matriu toMatriu() {
			double[][] c = new double[this.tamany][this.tamany];
			for (int i = 0; i < this.tamany; i++) {
				for (int j = 0; j < this.tamany; j++) {
					c[i][j] = this.data[filaInici+i][columnaInici+j];
				}
			} 
			
			Matriu m = new Matriu(this.tamany, this.tamany);
			m.setData(c);
			return m;		
		}
	}
	
	////////////////////////////////////////////////////////////////////////////
	//								FUNCIONS PRIVADES			   			  //
	////////////////////////////////////////////////////////////////////////////
	
	private Matriu strassen(Matriu a, Matriu b) {		
		int n = a.data.length;
		if (n <= 128) {
			return a.multiplicar(b);
		}
		
		n = n/2;
		SubMatriu matriu_a11 = new SubMatriu(a.data, 0, 0, n);
		SubMatriu matriu_a12 = new SubMatriu(a.data, 0, n, n);
		SubMatriu matriu_a21 = new SubMatriu(a.data, n, 0, n);
		SubMatriu matriu_a22 = new SubMatriu(a.data, n, n, n);	

		SubMatriu matriu_b11 = new SubMatriu(b.data, 0, 0, n);
		SubMatriu matriu_b12 = new SubMatriu(b.data, 0, n, n);
		SubMatriu matriu_b21 = new SubMatriu(b.data, n, 0, n);
		SubMatriu matriu_b22 = new SubMatriu(b.data, n, n, n);
			
			
		Matriu m1 = strassen((matriu_a11.sumar(matriu_a22)), (matriu_b11.sumar(matriu_b22))); 
		Matriu m2 = strassen((matriu_a21.sumar(matriu_a22)), matriu_b11.toMatriu());
		Matriu m3 = strassen(matriu_a11.toMatriu(), (matriu_b12.restar(matriu_b22)));
		Matriu m4 = strassen(matriu_a22.toMatriu(), (matriu_b21.restar(matriu_b11)));
		Matriu m5 = strassen((matriu_a11.sumar(matriu_a12)), matriu_b22.toMatriu());
		Matriu m6 = strassen((matriu_a21.restar(matriu_a11)), (matriu_b11.sumar(matriu_b12)));
		Matriu m7 = strassen((matriu_a12.restar(matriu_a22)), (matriu_b21.sumar(matriu_b22)));

		Matriu matriu_c11 = new Matriu(n, n);
		Matriu matriu_c12 = new Matriu(n, n);
		Matriu matriu_c21 = new Matriu(n, n);
		Matriu matriu_c22 = new Matriu(n, n);
		
		matriu_c11 = m1.sumar(m4).restar(m5).sumar(m7);
		matriu_c12 = m3.sumar(m5);
		matriu_c21 = m2.sumar(m4);
		matriu_c22 = m1.restar(m2).sumar(m3).sumar(m6);
		
		Matriu c = new Matriu(n*2, n*2);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				c.data[i][j] = matriu_c11.data[i][j];
				c.data[i][n+j] = matriu_c12.data[i][j];
				c.data[n+i][j] = matriu_c21.data[i][j];
				c.data[n+i][n+j] = matriu_c22.data[i][j];					
			}
		}		
		
		return c;
	}
	
	private Matriu convertir_a_matriu_cuadrada() {
		int tamany = this.data.length;
		if (tamany < this.data[0].length) tamany = this.data[0].length;
		Matriu matriu_cuadrada = new Matriu(tamany, tamany);
		matriu_cuadrada.setData(this.data);
		
		for (int i = tamany; i < data.length; i++) {
			for (int j = 0; j < data.length; j++) {
				matriu_cuadrada.data[i][j] = 0;
			}
		}
		
		for (int i = tamany; i < data[0].length; i++) {
			for (int j = 0; j < data.length; j++) {
				matriu_cuadrada.data[j][i] = 0;
			}
		}
		
		return matriu_cuadrada;
	}
	
	////////////////////////////////////////////////////////////////////////////
	//								FUNCIONS PUBLIQUES						  //
	////////////////////////////////////////////////////////////////////////////
	
	// Pre:  Cert.
	// Post: Crea una nova instancia d'una matriu sense inicialitzar.
	// Cost: O(1).
	public Matriu() {
		
	}
	
	// Pre:	 fila >= 0; columna >= 0.
	// Post: Crea una nova instancia d'una matriu de dimensions fila x columna.
	// Cost: O(n²).
	public Matriu(int fila, int columna) {
		this.data = new double[fila][columna];
	}
	
	// Pre:  Cert.
	// Post: Retorna una referència a les dades de la matriu.
	// Cost: O(1)
	public double[][] getData() {
		return data;
	}

	// Pre:  data != NULL.
	// Post: La matriu implicita apunta amb una referència a la matriu de dades.
	// Cost: O(1).
	public void setData(double[][] data) {
		this.data = data;
	}
		
	// Pre:  Anomanem la matriu implícita com A i la matriu pasada per referència 
	//		 com B, tant A com B tenen que tenir mateixes dimensions i no ser
	//		 buides. B != NULL.
	// Post: Retorna una matriu C com a resultat de la suma de la matriu 
	//		 implícita (A) amb la matriu B. C = A + B.
	// Cost: O(n²).
	public Matriu sumar(Matriu b) {		
		Matriu c = new Matriu(this.data.length, this.data[0].length);
		for (int i = 0; i < this.data.length; ++i) {
			for (int j = 0; j < this.data[0].length; ++j) {
				c.data[i][j] = this.data[i][j] + b.data[i][j]; 
			}
		}		
		return c;
	}

	// Pre:  Anomanem la matriu implícita com A i la matriu pasada per referència 
	//		 com B, tant A com B tenen que tenir mateixes dimensions i no ser 
	//		 buides. B != NULL.
	// Post: Retorna una matriu C com a resultat de la resta de la matriu 
	//		 implícita (A) amb la matriu B. C = A - B.
	// Cost: O(n²).
	public Matriu restar(Matriu b) {		
		Matriu c = new Matriu(this.data.length, this.data[0].length);
		for (int i = 0; i < this.data.length; ++i) {
			for (int j = 0; j < this.data[0].length; ++j) {
				c.data[i][j] = this.data[i][j] - b.data[i][j]; 
			}
		}
		
		return c;
	}
	
	// Pre:  b != NULL.
	// Post: Retorna una matriu C com a resultat de multiplicar la matriu
	//		 implícita (A) amb la matriu B. C = A * B.
	// Cost: O(n^2,807).
	public Matriu multiplicar_strassen(Matriu b) {
		Matriu a_cuadrada = this.convertir_a_matriu_cuadrada();
		Matriu b_cuadrada = b.convertir_a_matriu_cuadrada();
		return strassen(a_cuadrada, b_cuadrada);		
	}
	
	// Pre:  b != NULL.
	// Post: Retorna una matriu C com a resultat de multiplicar la matriu
	//		 implícita (A) amb la matriu B. C = A * B.
	// Cost: O(n³).
	public Matriu multiplicar(Matriu b) {		
		Matriu c = new Matriu(this.data.length, b.data[0].length);
	    for (int i = 0; i < this.data.length; i++) {
	        for (int j = 0; j < b.data[0].length; j++) {
	            for (int k = 0; k < b.data.length; k++) {
	                c.data[i][j] += this.data[i][k] * b.data[k][j];
	            }
	        }
	    }
	    
		return c;
	}
	
	// Pre:  Cert.
	// Post: Retorna una matriu C com a resultat de transposar la matriu
	//		 implícita.
	// Cost: O(n²).
	public Matriu transposar() {
		Matriu matriu_transposada = new Matriu(this.data[0].length, this.data.length);
		for (int i = 0; i < this.data[0].length; i++) {
			for (int j = 0; j < this.data.length; j++) {
				matriu_transposada.data[i][j] = this.data[j][i];
			}
		}
		
		return matriu_transposada;
	}
	
	// Pre:  Cert.
	// Post: Retorna una Matriu C com a resultat de normalitzar per files
	//		 la matriu implícita.
	// Cost: O(n²).
	public Matriu normalitzar_fila() {
		Matriu m = new Matriu(this.data.length, this.data[0].length);
		for (int i = 0; i < data.length; i++) {
			double norma = 0;
			for (int j = 0; j < data[0].length; j++) {
				norma += Math.pow(this.data[i][j], 2);
			}
			norma = Math.sqrt(norma);
			for (int j = 0; j < data[0].length; j++) {
				m.data[i][j] = this.data[i][j] / norma;
			}
		}
		
		return m;
	}
	
	// Pre:  Cert.
	// Post: Retorna una Matriu C coma resultat de obtenir la fila i-éssima 
	//		 de la matriu implícita.
	// Cost: O(n).
	public Matriu get_fila_iessima(int fila) {
		Matriu m = new Matriu(1, this.data[0].length);
		for (int i = 0; i < this.data[0].length; i++) {
			m.data[0][i] = this.data[fila][i];
		}
		
		return m;
	}
	
	////////////////////////////////////////////////////////////////////////////
	// 						FUNCIONES EXTRAS								  //
	////////////////////////////////////////////////////////////////////////////

	// EXPERIMENTAL / TEMPORAL
	public void print_matriu() {
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				System.out.printf("%.2f\t", data[i][j]);
			}
			System.out.println();
		}
	}

	// EXPERIMENTAL / TEMPORAL
	public static Matriu random(int tamany) {
		Matriu a = new Matriu(tamany,tamany);
		for (int i = 0; i < tamany; i++) {
			for (int j = 0; j < tamany; j++) {
				a.data[i][j] = Math.random();
			}
		}
		
		return a;
	}
}