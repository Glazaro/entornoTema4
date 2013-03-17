
package banco;

/**
 * @author Guillermo Lázaro
 * La clase trata todo lo relacionado con los clientes
 */
public class CCliente {
    /**
     * Variable de clase. 3 string
     */ 
    
    private String DNI; //dni de 9 caracteres (18 bytes)
    private String nombre;   //nombre no más de 40 caracteres (80 bytes) 1 caracter = 2 bytes.
    private String cuenta; //cuenta de 20 caracteres (40 bytes)
    
    /**
         * constructor sin parámetros para cuando creemos un objeto
         */ 
    CCliente(){
          
    }
    /**
         * constructor con parámetros para cuando creemos objeto que nos falte conocer el nombre
         */ 
    
    CCliente(String dni, String cuenta){
        
        
        DNI=dni;
        do{
            nombre=Pantalla.pideCadena("Introduce el nombre entre 1 y 40: ");
        }while(nombre.length()<=0  || nombre.length()>40);
        this.cuenta=cuenta;
    }
    /**
         * constructor con parámetros para cuando creemos objeto del que tenemos todos los datos
         */ 
    CCliente(String dni,String nombre,String cuenta){
        
        DNI=dni;
        this.nombre=nombre;
        this.cuenta=cuenta;
    }
    
    /**
         * @return DNI
         * Método getter
         */ 
    public String getDNI() {
        
        return DNI;
    }
    /**
         * Método setter
         */
    public void setDNI() {
         
        this.DNI = Pantalla.pideCadena("Introduzca nº DNI: ");
    }
    /**
         * @return nombre
         * Método getter
         */ 
    public String getNombre() {
        
        return nombre;
    }
    /**
         * Método setter
         */
    public void setNombre() {
         
        this.nombre = Pantalla.pideCadena("introduzca nombre: ");
    }
    /**
         * @return cuenta
         * Método getter
         */ 
    public String getCuenta() {
        
        return cuenta;
    }
    /**
         * Método setter
         */ 
    public void setCuenta() {
        
        this.cuenta = Pantalla.pideCadena("introduzca número de cuenta: ");
    }
    /**
         * Método que imprime con formato los datos de cada cliente.
         */ 
    public void muestraCliente(){
        
        System.out.printf("\n\t%10s\t\t%20s\t\t%20s",DNI,nombre,cuenta);
    }
}
