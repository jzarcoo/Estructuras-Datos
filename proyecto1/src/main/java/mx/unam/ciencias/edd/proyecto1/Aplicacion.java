package mx.unam.ciencias.edd.proyecto1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import mx.unam.ciencias.edd.ArbolRojinegro;
import mx.unam.ciencias.edd.Lista;

/**
 * Clase para aplicaciones del proyecto1.
 */
public class Aplicacion {
    
    // Códigos ANSI para colores en la terminal.
    // See: https://www.geeksforgeeks.org/how-to-print-colored-text-in-java-console/
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";

    /* Código de terminación por error de lectura. */
    private static final int ERROR_LECTURA = 5;
    
    /* Líneas de archivos*/
    private ArbolRojinegro<Cadena> lineasArchivos;
    /* Impresor de la aplicación. */
    private Impresor<Cadena> impresor;
    /* Fábrica de impresores. */
    private FabricaImpresores fabrica;
    /* Entrada estándar de la aplicación. */
    private EntradaEstandar entradaEstandar;

    /**
     * Define el estado inicial de la aplicación.
     * @param entradaEstandar la entrada estándar.
     */
    public Aplicacion (EntradaEstandar entradaEstandar) {
	this.entradaEstandar = entradaEstandar;
	fabrica = new FabricaImpresores();
	lineasArchivos = new ArbolRojinegro<Cadena>();
	lee(entradaEstandar.getArchivos());
    }

    /**
     * Define el estado inicial de la aplicación.
     * @param entradaEstandar la entrada estándar.
     * @param in la entrada estándar a leer.
     */
    public Aplicacion (EntradaEstandar entradaEstandar, BufferedReader in) {
	this.entradaEstandar = entradaEstandar;
	fabrica = new FabricaImpresores();
	lineasArchivos = new ArbolRojinegro<Cadena>();
	lee(in);
    }

    /**
     * Lee la lista de archivos de la entrada estándar.
     * @param archivos la lista de archivos a leer.
     */
    private void lee(Lista<String> archivos) {
	for (String archivo : archivos) {
    	    try {
		BufferedReader in = new BufferedReader(
		                        new InputStreamReader(
					    new FileInputStream(archivo)));
		agregaLineas(in);
	    } catch (IOException ioe) {
		System.err.printf(RED +
				  "No pude leer el archivo \"%s\".\n",
				  archivo
				  + RESET);
		System.exit(ERROR_LECTURA);
	    }
	}
    }
    
    /**
     * Lee la entrada estándar.
     * @param in la entrada estándar a leer.
     */
    private void lee(BufferedReader in) {
	try {
    	    agregaLineas(in);
    	} catch (IOException ioe) {
	    System.err.printf(RED +
		    	      "No pude cargar de la entrada estándar. \n"
			      + RESET);
	    System.exit(ERROR_LECTURA);
	}
    }
    
    /**
     * Agrega líneas a líneas de archivos.
     * @param in la entrada estándar a leer.
     */
    private void agregaLineas(BufferedReader in) throws IOException {
	String linea;
	while((linea = in.readLine()) != null)
    	    lineasArchivos.agrega(new Cadena(linea, entradaEstandar.esReversa()));
    	in.close();
    }
    
    /**
     * Ejecuta la aplicación.
     */
    public void ejecuta() {
	impresor = fabrica.crearImpresor(entradaEstandar);
	impresor.imprimir(lineasArchivos, entradaEstandar.getIdentificador());
    }
}
