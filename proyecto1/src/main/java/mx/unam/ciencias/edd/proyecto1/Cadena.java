package mx.unam.ciencias.edd.proyecto1;

import java.text.Collator;

/**
 * Clase para representar una Cadena. La clase implementa {@link Comparable}, por lo que puede
 * compararse de manera diferente a como lo hace una String.
 */
public class Cadena implements Comparable<Cadena> {

    /* Cadena del objeto Cadena. */
    private String cadena;
    /* Collator para comparar cadenas. */
    private Collator collator;
    /* Invierte el valor regresado del comparador si el usuario paso la bandera reversa. */
    private int esReversa;

    /**
     * Define el estado inicial del objeto Cadena.
     * @param cadena la cadena del objeto Cadena.
     */
    public Cadena(String cadena, boolean esReversa) {
	this.cadena = cadena;
        // Si es reversa, invierte el valor del comparador.
        this.esReversa = (esReversa) ? -1 : 1;
        // Define el collator para comparar cadenas.
        collator = Collator.getInstance();
	collator.setStrength(Collator.PRIMARY);
    }
    
    /**
     * Regresa la cadena del objeto Cadena.
     * @return la cadena del objeto Cadena.
     */
    public String getCadena() {
	return cadena;
    }
    
    /**
     * Define la cadena del objeto Cadena.
     * @param cadena la nueva cadena del objeto Cadena.
     */
    public void setCadena(String cadena) {
	this.cadena = cadena;
    }
    
    /**
     * Regresa una representación en cadena del objeto Cadena.
     * @return una representación en cadena del estudiante.
     */
    @Override public String toString(){
	return cadena;
    }

    /**
     * Nos dice si el objeto Cadena recibido es un objeto Cadena menor, mayor o igual al 
     * que manda llamar el método.
     * @param cadena el objeto Cadena con el que el objeto Cadena se comparará.
     * @return un entero menor que cero si y sólo si el objeto Cadena recibido es mayor que
     *                                  el objeto Cadena que manda llamar al método.
     *         un entero igual a cero si y sólo si el objeto Cadena recibido es igual
     *                                  el objeto Cadena que manda llamar al método.
     *         un entero mayor que cero si y sólo si el objeto Cadena recibido es menor que
     *                                  el objeto Cadena que manda llamar al método.
     */
    @Override 
    public int compareTo(Cadena c) {
	String c1 = cadena.trim();
	String c2 = c.cadena.trim();
	
	// Coloca las líneas vacías antes
	if(c1.isEmpty())
    	    return -1 * esReversa;
    	if(c2.isEmpty())
	    return 1 * esReversa;
	
	// Coloca minúsculas antes de mayúsculas
        if(limpiaCadena(c1).equals(limpiaCadena(c2).toLowerCase()))
	    return -1 * esReversa;
        if(limpiaCadena(c1).equals(limpiaCadena(c2).toUpperCase()))
	    return 1 * esReversa;
	
        return collator.compare(limpiaCadena(c1), limpiaCadena(c2)) * esReversa;
    }
    
    /**
     * Limpia la cadena del objeto Cadena.
     * @param c la cadena a limpiar.
     * @return una String limpia.
     */
    private static String limpiaCadena(String cadena) {
	return cadena
	    .replaceAll("[ñ]", "n")
	    .replaceAll("[Ñ]", "N")
            // Espacios.
	    .replaceAll("[\\s]","")
            // Caracter que no sea letra ni número.
	    .replaceAll("[^\\p{L}\\p{N}]", "");
    }
}
