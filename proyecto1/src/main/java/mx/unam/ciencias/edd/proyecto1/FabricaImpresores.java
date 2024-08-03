package mx.unam.ciencias.edd.proyecto1;

/**
 * Clase para fábrica de impresores.
 */
public class FabricaImpresores {
    
    /**
     * Crea un impresor de líneas de archivos de árboles rojinegros.
     * @param entradaEstandar la entrada estándar.
     * @return el impresor de líneas de archivos de árboles rojinegros.
     */
    public Impresor<Cadena> crearImpresor(EntradaEstandar entradaEstandar) {
	Impresor<Cadena> impresor = null;
	
	if (entradaEstandar.getIdentificador() != null)
	    impresor = new ImpresorGuardado<Cadena>();
	else
	    impresor = new ImpresorNormal<Cadena>();
	
	return impresor;
    }
}
