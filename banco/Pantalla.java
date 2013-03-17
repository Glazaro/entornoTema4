
package banco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * La clase realiza todo lo relacionado con la presentación en pantalla.
 * @author Guillermo Lázaro
 */

public class Pantalla {
        
        /**
         * @return int
         * Pide un entero por teclado y lo devuelve. Con tratamiento de errores.
         * Recibe una cadena como parámetro
         */ 
public static int pideInt(String texto) {
        InputStreamReader flujo=new InputStreamReader(System.in);
        BufferedReader teclado=new BufferedReader(flujo);
        String cadnum;
        int numero=0;
        boolean correcto;
        do {
                try {
                    correcto=true;
                    System.out.print(texto);
                    cadnum=teclado.readLine();
                    numero=Integer.parseInt(cadnum);
                }
                catch (NumberFormatException e) {
                    System.out.println("\t\tTienes que introducir un nº: " + e);
                    correcto=false;
                }
                catch (IOException e) {
                    System.out.println("\t\tSe ha producido un error: " + e);
                    correcto=false;
                }
        }while (!correcto);
        return numero;
    }
        /**
         * @return float
         * Pide un float por teclado y lo devuelve. Con tratamiento de errores.
         * Recibe una cadena como parámetro
         */ 
    public static float pideFloat(String texto) {
        
        InputStreamReader flujo=new InputStreamReader(System.in);
        BufferedReader teclado=new BufferedReader(flujo);
        String cadnum;
        float numero=0;
        boolean correcto;
        do {
                try {
                    correcto=true;
                    System.out.print(texto);
                    cadnum=teclado.readLine();
                    numero=Float.parseFloat(cadnum);
                }
                catch (NumberFormatException e) {
                    System.out.println("\t\tTienes que introducir un nº: " + e);
                    correcto=false;
                }
                catch (IOException e) {
                    System.out.println("\t\tSe ha producido un error: " + e);
                    correcto=false;
                }
        }while (!correcto);
        return numero;
    }
        /**
         * @return String
         * Pide cadena por teclado y lo devuelve. Con tratamiento de errores.
         * Recibe una cadena como parámetro
         */ 
    
    public static String pideCadena(String texto) {
        
        InputStreamReader flujo=new InputStreamReader(System.in);
        BufferedReader teclado=new BufferedReader(flujo);
        String cadnum = null;
        System.out.print(texto);
        try {
            cadnum=teclado.readLine();
        } catch (IOException ex) {
            System.out.println("\tHay errores: "+ex);
        }
        return cadnum;
    }
    
    public static int menuInicial() {
        /**
         * @return int
         * Presenta un menú y devuelve la opción elegida por el usuario.
         */ 
        int op;
        cabecera();
        System.out.println("\t\t\t\t1.- Alta.");
        System.out.println("\t\t\t\t2.- Listado.");
        System.out.println("\t\t\t\t3.- Operaciones.");
        
        System.out.println("\t\t\t\t0.- FIN.");
        do {
           op=Pantalla.pideInt("Introduce una opción: "); 
        } while (op<0 || op>3);
        return op;
    }
     /**
         * imprime en pantalla cualquier texto que le mandemos como parámetro.
         */ 
    public static void muestra(String texto) {
       
        System.out.println(texto);
    } 
    /**
         * imprime en pantalla la cabecera del banco
         */ 
    public static void cabecera() {
        
        System.out.println("\n\n\t\t******************************************");
        System.out.println("\t\t********* BANCO MUY MUY BUENO ************");
        System.out.println("\t\t******************************************");
       
    }
     /**
         * imprime con formato en pantalla la cabecera de los datos del cliente
         */ 
     public static void cabeceraClientes() {
        
        System.out.printf("\n\t%10s\t\t%20s\t\t%20s","DNI","nombre","cuenta");
     }
      /**
         * imprime con formato en pantalla la cabecera de los datos de la cuenta
         */ 
     public static void cabeceraCuenta() {
        
        System.out.printf("\n\t%10s\t\t%6s\t\t%6s\t\t%6s","Concepto","debe","haber","saldo");
     }
      /**
         * @return int
         * Presenta un menú y devuelve la opción elegida por el usuario.
         */ 
     public static int menuCuentas() {
        
        int op;
        cabecera();
        System.out.println("\t\t\t\t1.- Ingreso.");
        System.out.println("\t\t\t\t2.- Reintegro.");
        System.out.println("\t\t\t\t3.- Últimos movimientos.");
        
        System.out.println("\t\t\t\t0.- FIN.");
        do {
           op=Pantalla.pideInt("Introduce una opción: "); 
        } while (op<0 || op>3);
        return op;
    }
}

