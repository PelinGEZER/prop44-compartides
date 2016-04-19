package com.company;

import java.util.ArrayList;

/**
 * Created by Ruben Bagan Benavides on 11/04/2016.
 */

public class Matriu {
    private double[][] data;

    ////////////////////////////////////////////////////////////////////////////
    //                            CLASSE PRIVADA                              //
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
                    c[i][j] = this.data[filaInici+i][columnaInici+j] +
                            b.data[b.filaInici+i][b.columnaInici+j];
                }
            }

            Matriu m = new Matriu(this.tamany, this.tamany);
            m.set_data(c);
            return m;
        }

        public Matriu restar(SubMatriu b) {
            double[][] c = new double[this.tamany][this.tamany];
            for (int i = 0; i < this.tamany; i++) {
                for (int j = 0; j < this.tamany; j++) {
                    c[i][j] = this.data[filaInici+i][columnaInici+j] -
                            b.data[b.filaInici+i][b.columnaInici+j];
                }
            }

            Matriu m = new Matriu(this.tamany, this.tamany);
            m.set_data(c);
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
            m.set_data(c);
            return m;
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    //                          FUNCIONS PRIVADES                             //
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

        Matriu matriu_c11 = m1.sumar(m4).restar(m5).sumar(m7);
        Matriu matriu_c12 = m3.sumar(m5);
        Matriu matriu_c21 = m2.sumar(m4);
        Matriu matriu_c22 = m1.restar(m2).sumar(m3).sumar(m6);

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

    private Matriu convertir_a_matriu_quadrada() {
        int tamany = this.data.length;
        if (tamany < this.data[0].length) tamany = this.data[0].length;
        Matriu matriu_quadrada = new Matriu(tamany, tamany);
        matriu_quadrada.set_data(this.data);

        for (int i = tamany; i < this.data.length; i++) {
            for (int j = 0; j < this.data.length; j++) {
                matriu_quadrada.data[i][j] = 0;
            }
        }

        for (int i = tamany; i < this.data[0].length; i++) {
            for (int j = 0; j < this.data.length; j++) {
                matriu_quadrada.data[j][i] = 0;
            }
        }

        return matriu_quadrada;
    }

    ////////////////////////////////////////////////////////////////////////////
    //                         FUNCIONS PUBLIQUES                             //
    ////////////////////////////////////////////////////////////////////////////

    // Pre:  Cert.
    // Post: Crea una nova instancia d'una matriu sense inicialitzar.
    // Cost: O(1).
    public Matriu() {
        this.data = null;
    }

    // Pre:	 fila >= 0; columna >= 0.
    // Post: Crea una nova instancia d'una matriu inicialitzada amb les
    //		 dimensions fila x columna.
    // Cost: O(n²).
    public Matriu(int fila, int columna) {
        this.data = new double[fila][columna];
    }

    // Pre:  Cert.
    // Post: Retorna una referència a les dades de la matriu.
    // Cost: O(1)
    public double[][] get_data() {
        return data;
    }

    // Pre:  Cert.
    // Post: La matriu implicita apunta amb una referència a la matriu de dades.
    // Cost: O(1).
    public void set_data(double[][] data) {
        this.data = data;
    }

    // Pre:  La matriu implícita té que estar inicialitzada; 0 <= fila < Matriu.fila
    //		 data != NULL. La longitud de data té que ser igual al numero de
    //		 columnes de la matriu implícita.
    // Post: La fila i-èssima de la matriu implícita té com a valors data.
    // Cost: O(n)
    public void set_fila_iessima(int fila, double[] data) {
        for (int i = 0; i < data.length; i++) {
            this.data[fila][i] = data[i];
        }
    }

    // Pre:  La matriu implícita té que estar inicialitzada; 0 <= columna < Matriu.columna
    //		 data != NULL. La longitud de data té que ser igual al numero de
    //		 files de la matriu implícita.
    // Post: La columna i-èssima de la matriu implícita té com a valors data.
    // Cost: O(n)
    public void set_columna_iessima(int columna, double[] data) {
        for (int i = 0; i < data.length; i++) {
            this.data[i][columna] = data[i];
        }
    }

    // Pre:  La matriu implícita té que estar inicialitzada, 0 <= fila < Matriu.fila; 0 <= columna < Matriu.columna
    // Post: La matriu implicita té un nou valor en la fila i columna pasades per paràmetre.
    // Cost: O(1).
    public void set_valor(int columna, int fila, double valor) {
        this.data[fila][columna] = valor;
    }

    // Pre:  La matriu implícita té que estar inicialitzada.
    // Post: Retorna el nombre de files que té la matriu implícita.
    // Cost: O(1).
    public int get_nombre_files() {
        return this.data.length;
    }

    // Pre:  La matriu implícita té que estar inicialitzada.
    // Post: Retorna el nombre de columnes que té la matriu implícita.
    // Cost: O(1).
    public int get_nombre_columnes() {
        return  this.data[0].length;
    }

    // Pre:  La matriu implícita té que estar inicialitzada, 0 <= fila < Matriu.fila
    // Post: Retorna una Matriu C coma resultat de obtenir la fila i-èssima
    //		 de la matriu implícita.
    // Cost: O(n²).
    public Matriu get_fila_iessima(int fila) {
        Matriu m = new Matriu(1, this.data[0].length);
        for (int i = 0; i < this.data[0].length; i++) {
            m.data[0][i] = this.data[fila][i];
        }

        return m;
    }

    // Pre:  La matriu implícita té que estar inicialitzada, 0 <= fila < Matriu.fila; El numero de files de la matriu
    //       >= 2.
    // Post: S'ha eliminat de la matriu implícita la fila pasada com a paràmetre.
    // Cost: O(n²).
    public void eliminar_fila(int fila) {
        double[][] novaData = new double[this.data.length - 1][this.data[0].length];
        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < this.data[0].length; j++) {
                novaData[i][j] = this.data[i][j];
            }
        }

        for (int i = fila; i < this.data.length - 1; i++) {
            for (int j = 0; j < this.data[0].length; j++) {
                novaData[i][j] = this.data[i+1][j];
            }
        }
        this.data = novaData;
    }

    // Pre:  La matriu implícita té que estar inicialitzada, 0 <= columna < Matriu.fila; El numero de columnes de la matriu
    //       >= 2.
    // Post: S'ha eliminat de la matriu implícita la fila pasada com a paràmetre.
    // Cost: O(n²).
    public void eliminar_columna(int columna) {
        double[][] novaData = new double[this.data.length][this.data[0].length - 1];
        for (int i = 0; i < this.data.length; i++) {
            for (int j = 0; j < columna; j++) {
                novaData[i][j] = this.data[i][j];
            }
            for (int j = columna; j < this.data[0].length - 1; j++) {
                novaData[i][j] = this.data[i][j+1];
            }
        }

        this.data = novaData;
    }

    // Pre:  La matriu implícita té que estar inicialitzada.
    // Post: S'ha afegit a la matriu implícita una fila on tots els seus valors són 0. Aquesta nova fila és la
    //       última fila de la matriu. El numero de files de la matriu és Matriu.files + 1.
    // Cost: O(n²).
    public void afegir_fila() {
        double[][] novaData = new double[this.data.length + 1][this.data[0].length];
        for (int i = 0; i < this.data.length; ++i) {
            for (int j = 0; j < this.data[0].length; ++j) {
                novaData[i][j] = this.data[i][j];
            }
        }
        this.data = novaData;
    }

    // Pre:  La matriu implícita té que estar inicialitzada.
    // Post: S'ha afegit a la matriu implícita una columna on tots els seus valors són 0. Aquesta nova columna és la
    //       última columna de la matriu. El numero de columnes de la matriu és Matriu.columnes + 1.
    // Cost: O(n²).
    public void afegir_columna() {
        double[][] novaData = new double[this.data.length][this.data[0].length + 1];
        for (int i = 0; i < this.data.length; ++i) {
            for (int j = 0; j < this.data[0].length; ++j) {
                novaData[i][j] = this.data[i][j];
            }
        }
        this.data = novaData;
    }

    // Pre:  La matriu implícita té que estar inicialitzada, 0 <= columna < Matriu.columna
    // Post: Retorna una Matriu C coma resultat de obtenir la columna i-èssima
    //		 de la matriu implícita.
    // Cost: O(n²).
    public Matriu get_columna_iessima(int columna) {
        Matriu m = new Matriu(this.data.length, 1);
        for (int i = 0; i < this.data.length; i++) {
            m.data[0][i] = this.data[i][columna];
        }

        return m;
    }

    // Pre:  Anomanem la matriu implícita com A i la matriu pasada per referència
    //		 com B, tant A com B tenen que tenir mateixes dimensions i no ser
    //		 buides. B != NULL, A té que estar inicialitzada.
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
    //		 buides. B != NULL,  A té que estar inicialitzada.
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

    // Pre:  Anomanem la matriu implícita com A i la matriu pasada per referència
    //		 com B, B != NULL,  A té que estar inicialitzada. El nombre files de
    //		 la Matriu A té que ser igual al nombre de columnes de la Matriu B.
    // Post: Retorna una matriu C com a resultat de multiplicar la matriu
    //		 implícita (A) amb la matriu B. C = A * B.
    // Cost: O(n^2,807).
    public Matriu multiplicar_strassen(Matriu b) {
        Matriu a_quadrada = this.convertir_a_matriu_quadrada();
        Matriu b_quadrada = b.convertir_a_matriu_quadrada();
        return strassen(a_quadrada, b_quadrada);
    }

    // Pre:  Anomanem la matriu implícita com A i la matriu pasada per referència
    //		 com B, B != NULL,  A té que estar inicialitzada. El nombre files de
    //		 la Matriu A té que ser igual al nombre de columnes de la Matriu B.
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

    // Pre:  La matriu implícita té que estar inicialitzada.
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

    // Pre:  La matriu implícita té que estar inicialitzada.
    // Post: Retorna una Matriu C com a resultat de normalitzar per files
    //		 la matriu implícita.
    // Cost: O(n²).
    public Matriu normalitzar_fila() {
        Matriu m = new Matriu(this.data.length, this.data[0].length);
        for (int i = 0; i < this.data.length; i++) {
            double norma = 0;
            for (int j = 0; j < this.data[0].length; j++) {
                norma += Math.pow(this.data[i][j], 2);
            }
            norma = Math.sqrt(norma);
            for (int j = 0; j < this.data[0].length; j++) {
                m.data[i][j] = this.data[i][j] / norma;
            }
        }

        return m;
    }

    // Pre:  La matriu implícita té que estar inicialitzada.
    // Post: Retorna una Matriu C com a resultat de normalitzar per columnes
    //		 la matriu implícita.
    // Cost: O(n²).
    public Matriu normalitzar_columna() {
        Matriu m = new Matriu(this.data.length, this.data[0].length);
        for (int i = 0; i < this.data[0].length; i++) {
            double norma = 0;
            for (int j = 0; j < this.data.length; j++) {
                norma += Math.pow(this.data[j][i], 2);
            }
            norma = Math.sqrt(norma);
            for (int j = 0; j < this.data[0].length; j++) {
                m.data[j][i] = this.data[j][i] / norma;
            }
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