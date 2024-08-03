package mx.unam.ciencias.edd.proyecto2.graficadores;

import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.proyecto2.figuras.Linea;
import mx.unam.ciencias.edd.proyecto2.figuras.Rectangulo;
import mx.unam.ciencias.edd.proyecto2.figuras.RectanguloConBorde;
import mx.unam.ciencias.edd.proyecto2.figuras.Texto;

/**
 * <p>Clase para graficar listas, pilas y colas.</p>
 */
public abstract class GraficadorLineal<T> extends GraficadorEstructura {

    /* Ancho de los rectángulos. */
    protected static final int ANCHO_RECTANGULO = 70;
    /* Alto de los rectángulos. */
    protected static final int ALTO_RECTANGULO = 40;

    /* Lista a graficar. */
    protected Lista<T> lista;

    /**
     * Define el estado inicial de un graficador lineal.
     * @param lista la lista a graficar.
     */
    public GraficadorLineal(Lista<T> lista) {
        this.lista = lista;
    }

    /**
     * Gráfica un cuadro de la estructura: rectangulo y texto.
     * @param x la coordenada x del cuadro.
     * @param y la coordenada y del cuadro.
     * @param e el elemento a graficar.
     */
    protected void graficaCuadro(int x, int y, T e) {
        // Configuración del texto.
        int xTexto = x + (ANCHO_RECTANGULO >> 1);
        int yTexto = y + (ALTO_RECTANGULO >> 1) + (TAMANO_TXT / 3);
        graficador.agrega(creaRectangulo(x, y),
                          creaTexto(xTexto, yTexto, e));
    }

    /**
     * Regresa un rectángulo.
     * @param x la coordenada x del rectángulo.
     * @param y la coordenada y del rectángulo.
     * @return un rectángulo con borde.
     */
    protected Rectangulo creaRectangulo(int x, int y) {
        return new Rectangulo(x, y, ANCHO_RECTANGULO, ALTO_RECTANGULO, "white");
    }

    /**
     * Regresa un texto
     * @param x la coordenada x del texto.
     * @param y la coordenada y del texto.
     * @param e el elemento.
     * @return un texto
     */
    protected Texto creaTexto(int x, int y, T e) {
        return new Texto(x, y, e.toString(), TAMANO_TXT, "middle", "sans-serif");
    }

    /**
     * Regresa una línea.
     * @param x la coordenada x del punto de inicio de la linea.
     * @param y la coordenada y del punto de inicio de la linea.
     * @param x2 la coordenada x del punto final de la linea.
     * @param y2 la coordenada y del punto final de la linea.
     * @return una línea.
     */
    protected Linea creaLinea(int x, int y, int x2, int y2) {
        return new Linea(x, y, x2, y2, GROSOR_BORDE);
    }

    /**
     * Regresa un rectángulo con borde.
     * @param x la coordenada x del rectángulo.
     * @param y la coordenada y del rectángulo.
     * @return un rectángulo con borde.
     */
    protected RectanguloConBorde creaRectanguloConBorde(int x, int y) {
        return new RectanguloConBorde(x, y, ANCHO_RECTANGULO, ALTO_RECTANGULO, "white", GROSOR_BORDE, "black");
    }
}
