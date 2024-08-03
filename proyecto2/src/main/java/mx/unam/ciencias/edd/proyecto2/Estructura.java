package mx.unam.ciencias.edd.proyecto2;

/**
 * <p>Enumeracion para representar las estructuras de datos.</p>
 */
public enum Estructura {

    /* Lista doblemente ligada. */
    LISTA("Lista"),
    /* Pila. */
    PILA("Pila"),
    /* Cola. */
    COLA("Cola"),
    /* Arbol binario completo. */
    ARBOL_BINARIO_COMPLETO("ArbolBinarioCompleto"),
    /* Arbol binario ordenado. */
    ARBOL_BINARIO_ORDENADO("ArbolBinarioOrdenado"),
    /* Arbol rojinegro. */
    ARBOL_ROJINEGRO("ArbolRojinegro"),
    /* Arbol AVL. */
    ARBOL_AVL("ArbolAVL"),
    /* Grafica */
    GRAFICA("Grafica"),
    /* Monticulo minimo. */
    MONTICULO_MINIMO("MonticuloMinimo");

    /* Nombre de la estructura. */
    private String nombre;

    /**
     * Crea una estructura con el nombre dado.
     * @param nombre el nombre de la estructura.
     */
    private Estructura(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Descifra una estructura a partir de su nombre.
     * @param nombre el nombre de la estructura.
     * @return la estructura correspondiente al nombre.
     */
    public static Estructura getEstructura(String nombre) {
        for (Estructura e : Estructura.values())
            if (e.toString().equals(nombre))
                return e;
        return null;
    }

    /**
     * Regresa una representacion en cadena de la estructura.
     * @return una representacion en cadena de la estructura.
     */
    @Override
    public String toString() {
        return nombre;
    }
}