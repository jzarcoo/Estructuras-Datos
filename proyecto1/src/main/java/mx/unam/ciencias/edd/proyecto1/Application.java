package mx.unam.ciencias.edd.proyecto1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * Clase para cargar las aplicaciones del proyecto1.
 */
public class Application {
    
    /* Código de terminación por error de uso. */
    private static final int ERROR_USO = 1;
    
    /* Imprime en pantalla cómo debe usarse el programa y lo termina. */
    private static void uso() {
		System.out.println("Uso: java -jar target/proyecto1.jar [-r|-o <archivo>] <archivos> \n");
		System.exit(ERROR_USO);
    }

    /**
     * Crea una instancia de la aplicación e invoca a su método ejecuta
     * @param args el arreglo de cadenas de la línea de comandos.
     */
    public static void launch(String[] args){
	try {
	    EntradaEstandar entradaEstandar = new EntradaEstandar(args);
	    Aplicacion aplicacion;
	    // Inicia la aplicación para leer de la entrada estándar.
	    if (entradaEstandar.getArchivos().esVacia()) {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
		aplicacion = new Aplicacion(entradaEstandar, in);
	    }
	    // Inicia la aplicación con archivos de la línea de comandos.
	    else 
		aplicacion = new Aplicacion(entradaEstandar);
	    aplicacion.ejecuta();
	} catch (IOException |
		 IllegalArgumentException e) {
	    uso();
	}
    }
}
