package banco;

import java.io.IOException;

/**
 *
 * @author Guillermo Lázaro
 */
public class banco {

        /**
         * @param args the command line arguments
         * main apenas realiza nada.
         * sólo crea un objeto de cliente y llama a la opción que ha elegido el usuario.
         */ 
   
    public static void main(String[] args) {
        
    boolean ok=true;
    int op;
        FCliente cliente = null;
        
        try{
            cliente=new FCliente();
        }
        catch(IOException e){
            Pantalla.muestra("Imposible abrir el fichero");
            ok=false;
        }
        
        while(ok){
            op=Pantalla.menuInicial();
            switch(op){ 
                case 0: //salir
                    cliente.cerrarFichero();
                    ok=false;
                    break;
                case 1: //alta
                    cliente.alta();
                    break;
                case 2: //listado
                    cliente.listado();
                    break;
                case 3: //operaciones en cuenta
                    cliente.operaciones();
                    break;
            }
    }
}
}
