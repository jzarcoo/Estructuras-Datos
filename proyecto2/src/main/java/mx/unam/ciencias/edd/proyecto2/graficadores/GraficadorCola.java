package mx.unam.ciencias.edd.proyecto2.graficadores;

import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.IteradorLista;
import mx.unam.ciencias.edd.proyecto2.figuras.FlechaDerecha;

/**
 * <p>Clase para graficar colas.</p>
 * 
 * <p>Se apoya de una lista para graficar los elementos de la cola.</p>
 */
public class GraficadorCola<T> extends GraficadorLineal<T> {

    /**
     * Define el estado inicial de un graficador de colas.
     * @param lista la cola a graficar.
     */
    public GraficadorCola(Lista<T> lista) {
        super(lista);
    }

    /**
     * Regresa el codigo SVG de la cola.
     * @return el codigo SVG de la cola.
     */
    @Override
    public void graficaElementos() {
        int x = ANCHO_RECTANGULO;
        int y = 0;
        IteradorLista<T> iterador = lista.iteradorLista();
        iterador.end();
        while (iterador.hasPrevious()) {
            graficaCuadro(x, y, iterador.previous());
            x += ANCHO_RECTANGULO;
        }
        graficaMeteSaca(x, y);
    }

    /**
     * Grafica la cola.
     * @param x la coordenada x.
     * @param y la coordenada y.
     */
    protected void graficaMeteSaca(int x, int y) {
        x = ANCHO_RECTANGULO;
        int largo = ANCHO_RECTANGULO * (lista.getElementos() + 1);
        graficador.agrega(creaLinea(x, y, largo, y));
        y += ALTO_RECTANGULO;
        graficador.agrega(creaLinea(x, y, largo, y));
        graficaFlechas(largo);
    }

    /**
     * Grafica lad flechas de meter y sacar a la cola.
     * @param l el largo de la cola.
     */
    protected void graficaFlechas(int l) {
        int y = (ALTO_RECTANGULO + GROSOR_BORDE) >> 1;
        graficador.agrega(new FlechaDerecha(0, y, ANCHO_RECTANGULO, y, GROSOR_BORDE),
                          new FlechaDerecha(l, y, l + ANCHO_RECTANGULO, y, GROSOR_BORDE));

    }
}