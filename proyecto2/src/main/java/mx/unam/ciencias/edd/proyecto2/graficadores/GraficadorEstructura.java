package mx.unam.ciencias.edd.proyecto2.graficadores;

import mx.unam.ciencias.edd.proyecto2.figuras.GraficadorSVG;

/**
 * <p>Clase para graficar estructuras de datos.</p>
 */
public abstract class GraficadorEstructura {

    /* Tamaño del texto. */
    protected static final int TAMANO_TXT = 15;
    /* Grosor del borde. */
    protected static final int GROSOR_BORDE = 4;

    /* Graficador. */
    protected GraficadorSVG graficador;

    /**
     * Crea un graficador de estructuras con la colección dada.
     * @param coleccion la colección a graficar.
     */
    public GraficadorEstructura() {
        this.graficador = new GraficadorSVG();
    }

    /**
     * Grafica la estructura de datos.
     */
    public void grafica() {
        graficaElementos();
        System.out.println(graficador.toSVG());
    }

    /**
     * Grafica los elementos de la colección en el graficador.
     */
    public abstract void graficaElementos();
}