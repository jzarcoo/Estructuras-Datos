package mx.unam.ciencias.edd.proyecto2.graficadores;

import mx.unam.ciencias.edd.Coleccion;
import mx.unam.ciencias.edd.ArbolBinarioOrdenado;

/**
 * <p>Clase para graficar arboles binarios ordenados.</p>
 */
public class GraficadorArbolBinarioOrdenado<T extends Comparable<T>> extends GraficadorArbolBinario<T> {

    /**
     * Define el estado inicial de un graficador de arboles binarios ordenados.
     * @param arbolBinarioOrdenado el árbol binario ordenado con el que se construye el árbol.
     */
    public GraficadorArbolBinarioOrdenado(ArbolBinarioOrdenado<T> arbolBinarioOrdenado) {
        super(arbolBinarioOrdenado);
    }

    /**
     * Define el estado inicial de un graficador de arboles binarios ordenados.
     * @param coleccion la coleccion con la que se construye el árbol.
     */
    public GraficadorArbolBinarioOrdenado(Coleccion<T> coleccion) {
        super(new ArbolBinarioOrdenado<T>(coleccion));
    }

}
