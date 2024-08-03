package mx.unam.ciencias.edd.proyecto1;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import mx.unam.ciencias.edd.ArbolRojinegro;

/**
 * Clase para impresores de guardado de líneas de archivos de árboles rojinegros.
 */
public class ImpresorGuardado<T extends Comparable<T>> implements Impresor<T> {
    
    // Códigos ANSI para colores en la terminal
    // See: https://www.geeksforgeeks.org/how-to-print-colored-text-in-java-console/
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
      
    /* Código de terminación por error de guardado. */
    private static final int ERROR_GUARDA = 1;
    
    /**
     * Imprime las lineas de archivo del árbol rojinegro.
     * @param rbt el árbol rojinegro a imprimir.
     * @param identificador el identificador del archivo.
     */
    @Override
    public void imprimir(ArbolRojinegro<T> rbt, String identificador) {
	try {
	    BufferedWriter out = new BufferedWriter(
 		                     new OutputStreamWriter(
					 new FileOutputStream(identificador)));
	    for (T v : rbt)
		out.write(v + "\n");
	    out.close();
	} catch (IOException ioe) {
	    System.err.printf(RED +
			      "No pude guardar en el archivo \"%s\".\n",
			      identificador
			      + RESET);
	    System.exit(ERROR_GUARDA);
	}
    }
    
}
