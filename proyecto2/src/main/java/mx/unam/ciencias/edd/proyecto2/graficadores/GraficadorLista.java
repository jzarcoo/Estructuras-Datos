package mx.unam.ciencias.edd.proyecto2.graficadores;

import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.proyecto2.figuras.FlechaBidireccional;

/**
 * <p>Clase para graficar listas.</p>
 */
public class GraficadorLista<T> extends GraficadorLineal<T> {
    
    /* Espacio entre los rectángulos. */
    protected static final int ESPACIO_RECTANGULO = 50;

    /**
     * Crea un graficador de listas con la lista dada.
     * @param lista la lista a graficar.
     */
    public GraficadorLista(Lista<T> lista) {
        super(lista);
    }

    /**
     * Grafica los elementos de la lista en el graficador.
     */
    @Override
    public void graficaElementos() {
        int x = 0;
        int y = 0;
        for (T elemento : lista) {
            graficaCuadro(x, y, elemento);
            x += ANCHO_RECTANGULO + ESPACIO_RECTANGULO;
        }
        // Elimina la última flecha.
        graficador.eliminaUltimo();
    }

    /**
     * Gráfica un cuadro de la estructura: rectangulo y texto.
     * @param x la coordenada x del cuadro.
     * @param y la coordenada y del cuadro.
     * @param e el elemento a graficar.
     */
    @Override
    protected void graficaCuadro(int x, int y, T e) {
        // Configuración del texto.
        int xTexto = x + (ANCHO_RECTANGULO >> 1);
        int yTexto = y + (ALTO_RECTANGULO >> 1) + (TAMANO_TXT / 3);
        // Configuración de la línea.
        int espacio = 5; // espacio entre la flecha y el cuadro.
        int xLinea = x + ANCHO_RECTANGULO + espacio;
        int x2Linea = x + ANCHO_RECTANGULO + ESPACIO_RECTANGULO - espacio;
        int yLinea = y + ALTO_RECTANGULO >> 1;
        // Agrega figuras.
        graficador.agrega(creaRectanguloConBorde(x, y),
                          creaTexto(xTexto, yTexto, e),
                          new FlechaBidireccional(xLinea, yLinea, x2Linea, yLinea, GROSOR_BORDE));
    }

}