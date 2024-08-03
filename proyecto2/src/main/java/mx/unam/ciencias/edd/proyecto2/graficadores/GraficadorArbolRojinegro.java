package mx.unam.ciencias.edd.proyecto2.graficadores;

import mx.unam.ciencias.edd.ArbolRojinegro;
import mx.unam.ciencias.edd.Coleccion;
import mx.unam.ciencias.edd.VerticeArbolBinario;
import mx.unam.ciencias.edd.proyecto2.figuras.CirculoConBorde;
import mx.unam.ciencias.edd.proyecto2.figuras.Texto;

/**
 * <p>Clase para graficar arboles binarios rojinegros.</p>
 */
public class GraficadorArbolRojinegro<T extends Comparable<T>> extends GraficadorArbolBinarioOrdenado<T> {

    /**
     * Define el estado inicial de un graficador de arboles binarios rojinegros.
     * @param arbolBinarioRojinegro el árbol binario rojinegro con el que se construye el árbol.
     */
    public GraficadorArbolRojinegro(ArbolRojinegro<T> arbolBinarioRojinegro) {
        super(arbolBinarioRojinegro);
    }

    /**
     * Define el estado inicial de un graficador de arboles binarios rojinegros.
     * @param coleccion la coleccion con la que se construye el árbol.
     */
    public GraficadorArbolRojinegro(Coleccion<T> coleccion) {
        super(new ArbolRojinegro<T>(coleccion));
    }

    /**
     * Regresa un círculo con borde.
     * @param v el vertice del arbol.
     * @param x la coordenada x del centro del círculo
     * @param y la coordenada y del centro del círculo
     * @return un círculo con borde.
     */
    @Override
    protected CirculoConBorde creaCirculo(VerticeArbolBinario<T> v, int x, int y) {
        String color = (v.toString().startsWith("R")) ? "red" : "black";
        return new CirculoConBorde(x, y, RADIO, color, GROSOR_BORDE, "black");
    }

    /**
     * Regresa un texto.
     * @param v el vertice del arbol.
     * @param x la coordenada x del texto.
     * @param y la coordenada y del texto.
     * @return un texto.
     */
    @Override
    protected Texto creaTexto(VerticeArbolBinario<T> v, int x, int y) {
        return new Texto(x, y, "white", v.get().toString(), TAMANO_TXT, "middle", "sans-serif");
    }
}
