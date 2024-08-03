package mx.unam.ciencias.edd.proyecto2.graficadores;

import mx.unam.ciencias.edd.Coleccion;
import mx.unam.ciencias.edd.ArbolAVL;
import mx.unam.ciencias.edd.VerticeArbolBinario;
import mx.unam.ciencias.edd.proyecto2.figuras.GraficadorSVG;
import mx.unam.ciencias.edd.proyecto2.figuras.Texto;
import mx.unam.ciencias.edd.proyecto2.figuras.Linea;

/**
 * <p>Clase para graficar arboles binarios AVL.</p>
 * 
 * <p>Le sumamos el RADIO a todas las varibles del eje Y para colocar la información
 * del vértice AVL.</p>
 */
public class GraficadorArbolAVL<T extends Comparable<T>> extends GraficadorArbolBinarioOrdenado<T> {

    /**
     * Define el estado inicial de un graficador de arboles binarios avl.
     * @param arbolBinarioAVL el árbol binario AVL con el que se construye el árbol.
     */
    public GraficadorArbolAVL(ArbolAVL<T> arbolBinarioAVL) {
        super(arbolBinarioAVL);
    }

    /**
     * Define el estado inicial de un graficador de arboles binarios avl.
     * @param coleccion la coleccion con la que se construye el árbol.
     */
    public GraficadorArbolAVL(Coleccion<T> coleccion) {
        super(new ArbolAVL<T>(coleccion));
    }

    /**
     * Regresa una línea.
     * @param ini el inicio del canvas.
     * @param fin el fin del canvas.
     * @param v el vertice del arbol.
     * @param h la altura del canvas.
     * @param x la coordenada x de la línea.
     * @param y la coordenada y de la línea.
    */
    @Override
    protected Linea creaLinea(int ini, int fin, VerticeArbolBinario<T> v, int h, int x, int y) {
        VerticeArbolBinario<T> p = v.padre();
        int xLinea = fin;
        if (p.hayDerecho())
            if (p.derecho() == v)
                xLinea = ini;
        int yLinea = h - TAMANO_CIRCULO - RADIO;
        return new Linea(x, y + RADIO, xLinea, yLinea + RADIO, GROSOR_BORDE);
    }

    /**
     * Grafica un vértice del árbol.
     * @param v el vertice del arbol.
     * @param x la coordenada x del vértice.
     * @param y la coordenada y del vértice.
     * @param graficadorVertices el gravicador svg de vértices.
     */
    @Override
    protected void graficaVertice(VerticeArbolBinario<T> v, int x, int y, GraficadorSVG graficadorVertices) {
        // Configuración del texto.
        int yTexto = y + (TAMANO_TXT / 3);
        // Agrega figuras
        graficadorVertices.agrega(creaCirculo(v, x, y + RADIO),
                          creaTexto(v, x, yTexto + RADIO));
        // Información del vérice
        String[] avl = v.toString().split(" ");
        String info = "[" + avl[1] + "]";
        // Posición
        String pTexto = "middle";
        int yInfo = y - RADIO - TAMANO_TXT / 3;
        if (v.hayPadre()) {
            x -= 2 * RADIO;
            pTexto = "start";
            // pTexto = "end";
            VerticeArbolBinario<T> p = v.padre();
            if (p.hayDerecho()) 
                if (p.derecho() == v) 
                    x += 3 * TAMANO_TXT;
                    // pTexto = "start";
        }
        graficadorVertices.agrega(new Texto(x, yInfo + RADIO, "black", info, TAMANO_TXT, pTexto, "sans-serif"));
    }
}
