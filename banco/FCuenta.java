
package banco;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author Guillermo Lázaro
 * La clase trata todo lo relacionado con los archivos de las cuentas
 */
public class FCuenta {
     /**
         * Variables de clase
         */ 
    RandomAccessFile archivo;
    int nregs=0;
    int tam=94;//80+2 para concepto, 4 para cada float



    /**
     * @param cuenta
     * @throws IOException
     * Constructor para crear fichero
     * Escribe el primer registro de la cuenta, usando para ello el constructor (sin parámetros) de clase CCuenta.
     */
    public FCuenta(String cuenta) throws IOException {
        String numcuenta=cuenta+".txt";
        archivo=new RandomAccessFile(numcuenta,"rw");
        CCuenta cta=new CCuenta();
        if (escribirCuenta (nregs,cta)){
            nregs++;
        }
    }
    
     /**
     *
     * @param cuenta
     * @param p
     * @throws IOException
     * Constructor para acceder a fichero. Recibe parámetro de nº cuenta y un int irrelevante porque ya hay otro constructor que sólo recibe string
     * Lee último registro e imprime el saldo existente.
     * Sólo se accede a él a través de la petición operaciones (en FCliente)
     * Enseña el menú operaciones pasándole como parámetro el saldo existente.
     */
    public FCuenta(String cuenta,int p) throws IOException {
        
        String numcuenta=cuenta+".txt";
        archivo=new RandomAccessFile(numcuenta,"rw");
        nregs=(int)Math.ceil((double)archivo.length()/(double)tam);;
        CCuenta cta=leer(nregs-1);
        Pantalla.muestra("\n\t El saldo es: "+cta.getSaldo()+" Euros.");
        operaciones();
        
    }
         /**
         * @return objeto clase CCuenta
         * Lee el registro indicado a través del parámetro pos.
         */
    private CCuenta leer(int pos){
        
        String concepto;
        float debe, haber, saldo;
             try{
                 archivo.seek(pos*tam);   
                 concepto=archivo.readUTF();
                 haber=archivo.readFloat();
                 debe=archivo.readFloat();
                 saldo=archivo.readFloat();
                 return new CCuenta (concepto, haber,debe, saldo);
             }
             catch (IOException e){
                 Pantalla.muestra("Problemas de lectura");
                 return null;
             }
    
    }
    /**
         * @return boolean true si escribe bien, false si no puede escribir
         * escribe nuevo registro en el archivo.
         */
    private boolean escribirCuenta (int i,CCuenta cuenta){
        
        if(i>=0 && i<= nregs){
            try{
                archivo.seek(i*tam);    //situamos el puntero en la posición que me indica i
                archivo.writeUTF(cuenta.getConcepto());
                archivo.writeFloat(cuenta.getDebe());
                archivo.writeFloat(cuenta.getHaber());
                archivo.writeFloat(cuenta.getSaldo());
                return true;
            }
            catch (IOException e){
                Pantalla.muestra("Imposible escribir el registro");
            }
        }else{
            Pantalla.muestra("Registro excedido");
        }
        return false;
    }
    /**
         * @return void
         * Sirve para presentar el menú de operaciones (a través de clase Pantalla) y para ejecutar otros métodos según opción elegida
         * Recibe parámetro saldo, para pasárselo luego a los métodos ingresos y reintegros.
         */
    private void operaciones(){
        
         boolean ok=true;
         int op;
         while(ok){
            op=Pantalla.menuCuentas();
            switch(op){ 
                case 0: //salir
                    cerrarFichero();
                    ok=false;
                    break;
                case 1: //ingresos
                    ingresos();
                    break;
                case 2: //reintegros
                    reintegros();
                    break;
                case 3: //Últimos movimientos
                    ultimosMovimientos();
                    break;
            }
        }
     }
    
    /**
         * @return void
         * Crea un objeto de clase CCuenta, asignándole los valores de las variables de ese objeto.
         * Escribe el nuevo registro en el archivo.
         */
    public void ingresos(){
        
        String concepto;
        float debe, haber, saldo,saldonuevo;
        CCuenta cta=leer(nregs-1);
        saldo=cta.getSaldo();
        concepto = Pantalla.pideCadena("Introduce concepto: ");
        haber = Pantalla.pideFloat("Introduce cantidad a ingresar: ");
        debe=0;//siempre debe ser 0.
        saldonuevo=saldo+haber;//sumo saldo existente al ingreso efectuado.
        cta=new CCuenta(concepto,debe,haber,saldonuevo);
        if (escribirCuenta (nregs,cta)){
            nregs++;
        }
        Pantalla.muestra("\n Acaba de ingresar "+ haber+" €.");
        Pantalla.muestra("\n El saldo actual es "+ saldonuevo+" €.");
    
    }
    
    /**
         * @return void
         * Crea un objeto de clase CCuenta, asignándole los valores de las variables de ese objeto.
         * Escribe el nuevo registro en el archivo.
         */
    public void reintegros(){
        
        String concepto;
        float debe, haber,saldo,saldonuevo;
        CCuenta cta=leer(nregs-1);
        saldo=cta.getSaldo();
        concepto = Pantalla.pideCadena("Introduce concepto: ");
        haber = 0;//siempre 0
        debe=Pantalla.pideFloat("Introduce cantidad a retirar: ");
        if (debe>saldo){
            do{
            debe=Pantalla.pideFloat("Saldo insuficiente. Introduce una cantidad inferior a "+saldo+": ");
            }while (debe>saldo);
        }
        saldonuevo=saldo-debe;//resto saldo existente del reintegro efectuado.
        cta=new CCuenta(concepto,debe,haber,saldonuevo);
        if (escribirCuenta (nregs,cta)){
            nregs++;
        }
        Pantalla.muestra("\n Acaba de sacar "+ debe+" €.");
        Pantalla.muestra("\n El saldo actual es "+ saldonuevo+" €.");
    
    }
    /**
         * @return void
         * Enseña los últimos movimientos.
         * Usa variable ultimos para pedir cuantos quiere enseñar.
         * Si el número de registros es mayor que los solicitados enseña sólo los solicitados.
         * Si es menor, enseña todos.
         */
    private void ultimosMovimientos(){
        
        CCuenta cta; // Sólo declaro el objeto porque recibiré uno luego.
        int ultimos=Pantalla.pideInt("\nIntroduzca el nº de movimientos que quiere visualizar: ");
        Pantalla.cabecera();
        Pantalla.cabeceraCuenta();
        if (nregs>ultimos){//Si el número de registros es mayor que los solicitados enseña sólo los solicitados.
            for(int i=nregs-1; i>=(nregs-ultimos); i--){ //recorro, con el for, del último registro al nº que se han pedido
                cta=leer(i);  //envío por parametro la posicion del registro y este me devuelve un objeto CCuenta.
                cta.muestraCuenta(); //accedo al metodo muestracliente() a traves del objeto de su clase.
            }
        }else{
            for(int i=0; i<nregs; i++){ //Recorro, con el for, todos los registros existentes.
            cta=leer(i);  //envío por parametro la posicion del registro y este me devuelve un objeto CCuenta.
            cta.muestraCuenta(); //accedo al metodo muestracliente() a traves del objeto de su clase.
        }
        }
    }
    
    /**
         * Método para cerrar el archivo con su tratamiento de error.
         */
    public void cerrarFichero(){
        
        try{
            archivo.close();
        }
        catch(IOException e){
            Pantalla.muestra("Problemas con los ficheros");
        }
    }
}
