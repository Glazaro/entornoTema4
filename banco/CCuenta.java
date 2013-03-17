
package banco;

/**
 *@author guillermo Lázaro
 * La clase trata todo lo relacionado con las propias cuentas
 */

public class CCuenta {
    /**
     * Variables de clase. un string y 3 float
     */ 
    private String concepto;
    private float debe, haber, saldo;
    
    /**
         * Constructor para el primer registro de la cuenta
         */ 
    CCuenta(){
        
        concepto="Saldo inicial";
        haber=0;
        debe=0;
        saldo=0.0f;
    }
    /**
         * Constructor que debe recibir como parámetros todas las variables de clase
         */
    CCuenta(String concepto, float haber, float debe, float saldo){
         
        this.concepto=concepto;
        this.haber=haber;  
        this.debe=debe;
        this.saldo=saldo;
    }
    /**
         * @return concepto
         * Método getter
         */ 
    public String getConcepto() {
        
        return concepto;
    }
    /**
         * Método setter
         */
    public void setConcepto() {
          
        this.concepto = Pantalla.pideCadena("Introduce concepto: ");
    }
    /**
         * @return haber
         * Método getter
         */
    public float getHaber() {
         
        return haber;
    }
    /**
         * Método setter
         */ 
    public void setHaber() {
         
        this.haber = Pantalla.pideFloat("Introduce cantidad a ingresar: ");
    }
    /**
         * @return debe
         * Método getter
         */ 
    public float getDebe() {
        
        return debe;
    }
    /**
         * Método setter
         */ 
    public void setDebe() {
         
        this.debe = Pantalla.pideFloat("Introduce cantidad a retirar: ");
    }
    /**
         * @return saldo
         * Método getter
         */ 
    public float getSaldo() {
        
        return saldo;
    }
    /**
         * Método setter
         */ 
    public void setSaldo(float saldo) {
        
        this.saldo=saldo;
    }
     /**
         * Método que imprime con formato los datos de cada cuenta.
         */ 
     public void muestraCuenta(){
        
        System.out.printf("\n\t%10s\t\t%6.2f\t\t%6.2f\t\t%6.2f",concepto,debe,haber,saldo);
    }
    
}
