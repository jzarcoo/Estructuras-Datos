package mx.unam.ciencias.edd.proyecto2.graficadores;

import mx.unam.ciencias.edd.Coleccion;
import mx.unam.ciencias.edd.ArbolBinarioCompleto;

/**
 * <p>Clase para graficar arboles binarios completos.</p>
 */
public class GraficadorArbolBinarioCompleto<T> extends GraficadorArbolBinario<T> {

    /**
     * Define el estado inicial de un graficador de arboles binarios completos.
     * @param arbolBinarioCompleto el árbol binario completo con el que se construye el árbol.
     */
    public GraficadorArbolBinarioCompleto(ArbolBinarioCompleto<T> arbolBinarioCompleto) {
        super(arbolBinarioCompleto);
    }

    /**
     * Define el estado inicial de un graficador de arboles binarios completos.
     * @param coleccion la coleccion con la que se construye el árbol.
     */
    public GraficadorArbolBinarioCompleto(Coleccion<T> coleccion) {
        super(new ArbolBinarioCompleto<T>(coleccion));
    }
}
