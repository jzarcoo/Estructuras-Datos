package mx.unam.ciencias.edd.proyecto2.graficadores;

import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.IteradorLista;
import mx.unam.ciencias.edd.proyecto2.figuras.Linea;
import mx.unam.ciencias.edd.proyecto2.figuras.Rectangulo;
import mx.unam.ciencias.edd.proyecto2.figuras.Texto;

/**
 * <p>Clase para graficar pilas.</p>
 * 
 * <p>Se apoya de una lista para graficar los elementos de la pila.</p>
 */
public class GraficadorPila<T> extends GraficadorLineal<T>  {

    /**
     * Define el estado inicial de un graficador de pilas.
     * @param lista la pila a graficar.
     */
    public GraficadorPila(Lista<T> lista) {
        super(lista);
    }

    /**
     * Regresa el codigo SVG de la pila.
     * @return el codigo SVG de la pila.
     */
    @Override
    public void graficaElementos() {
        int x = 0;
        int y = 0;
        IteradorLista<T> iterador = lista.iteradorLista();
        iterador.end();
        while (iterador.hasPrevious()) {
            graficaCuadro(x, y, iterador.previous());
            y += ALTO_RECTANGULO;
        }
        graficaMeteSaca(x, y);
    }

    /**
     * Grafica la pila.
     * @param x la coordenada x.
     * @param y la coordenada y.
     */
    protected void graficaMeteSaca(int x, int y) {
        x += ANCHO_RECTANGULO;
        graficador.agrega(creaLinea(0, y, x, y), // linea de abajo
                          creaLinea(0, 0, 0, y), // línea izquierda
                          creaLinea(x, 0, x, y)); // línea derecha
    }
}