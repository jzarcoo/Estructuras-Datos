package mx.unam.ciencias.edd.proyecto1;

import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.ExcepcionIndiceInvalido;

/**
 * Clase para representar la entrada estándar. Una entrada estándar tiene una lista de
 * archivos pasados por la linea de comandos y banderas, en este caso reversa y guarda,
 * donde en esta última posee un identificador.
 */
public class EntradaEstandar {
    
    // Códigos ANSI para colores en la terminal.
    // See: https://www.geeksforgeeks.org/how-to-print-colored-text-in-java-console/
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";

    /* Bandera reversa de la entrada estándar. */
    private static final String BANDERA_REVERSA = "-r";
    /* Bandera guarda de la entrada estándar. */
    private static final String BANDERA_GUARDA = "-o";

    /* Lista de archivos de la entrada estándar. */
    private Lista<String> archivos;
    /* Bandera reversa de la entrada estándar. */
    private boolean reversa;
    /* Bandera guarda de la entrada estándar. */
    private boolean guarda;
    /* Archivo identificador para la bandera guarda de la entrada estándar. */
    private String identificador;

    /**
     * Define el estado inicial de una entrada estándar.
     * @param args el arreglo de cadenas de la línea de comandos.
     * @throws IllegalArgumentException si se paso una bandera inválida o
     *         un archivo inválido.
     */
    public EntradaEstandar(String[] args) {
    	archivos = new Lista<String>();
    	for(int i = 0; i < args.length; i++) {
	    if(args[i].endsWith(".txt"))
		archivos.agrega(args[i]); // agrega archivos a la lista
	    else if(args[i].contains("-") && args[i].length() == 2) { // obtiene banderas
		if(args[i].equals(BANDERA_REVERSA))
		    reversa = true;
		else if(args[i].equals(BANDERA_GUARDA)) {
		    fijaIdentificador(++i, args);
		    guarda = true;
		} else { // bandera inválida
		    System.err.printf(RED +
				      "Bandera inválida \"%s\".\n",
				      args[i]
				      + RESET);
		    throw new IllegalArgumentException();
		}
	    } else { // tipo de archivo inválido
		System.err.printf(RED +
				  "Ingresaste un archivo no válido \"%s\".\n",
				  args[i]
				  + RESET);
		throw new IllegalArgumentException();
	    }
	}
    }

    /**
     * Fija el identificador de la entrada estándar.
     * @param indice la localidad donde se encuentra el identificador en el arreglo.
     * @param args el arreglo de cadenas de la línea de comandos.
     * @throws IllegalArgumentException si no se colocó un identificador o
     *         se colocaron varios de ellos.
     */
    private void fijaIdentificador(int indice, String[] args) {
	try {
	    if(indice >= args.length)
		throw new ExcepcionIndiceInvalido("Índice inválido");
	    else
		identificador = args[indice];
	} catch (ExcepcionIndiceInvalido e) {
    	    System.err.printf(RED +
			      "La opción '-o' requiere un argumento. \n"
			      + RESET);
	    throw new IllegalArgumentException();
	}
	if (guarda) {
    	    System.err.printf(RED +
			      "Múltiples archivos de salida especificados. \n"
			      + RESET);
	    throw new IllegalArgumentException();
	}
    }
    
    /**
     * Regresa el valor de la bandera reversa de la entrada estándar.
     * @return el valor de la bandera reversa de la entrada estándar.
     */
    public boolean esReversa(){
	    return reversa;
    }

    /**
     * Define el valor de la bandera reversa de la entrada estándar.
     * @param reversa el nuevo valor de la bandera reversa de la entrada estándar.
     */
    public void setReversa(boolean reversa){
        this.reversa = reversa;
    }

    /**
     * Regresa el valor de la bandera guarda de la entrada estándar.
     * @return el valor de la bandera guarda de la entrada estándar.
     */
    public boolean esGuarda(){
	    return guarda;
    }

    /**
     * Define el valor de la bandera guarda de la entrada estándar.
     * @param guarda el nuevo valor de la bandera guarda de la entrada estándar.
     */
    public void setGuarda(boolean guarda){
        this.guarda = guarda;
    }

    /**
     * Regresa el archivo identificador de la entrada estándar.
     * @return el archivo identificador de la entrada estándar.
     */
    public String getIdentificador(){
	    return identificador;
    }

    /**
     * Define el archivo identificador de la entrada estándar.
     * @param identificador el nuevo archivo identificador de la entrada estándar.
     */
    public void setIdentificador(String identificador){
        this.identificador = identificador;
    }

    /**
     * Regresa la lista de archivos de la entrada estándar.
     * @return la lista de archivos de la entrada estándar.
     */
    public Lista<String> getArchivos(){
	    return archivos;
    }

    /**
     * Define la lista de archivos de la entrada estándar.
     * @param archivos la nueva lista de archivos de la entrada estándar.
     */
    public void setArchivos(Lista<String> archivos){
        this.archivos = archivos;
    }
    
}
