package mx.unam.ciencias.edd.proyecto2.graficadores;

import mx.unam.ciencias.edd.ArbolBinario;
import mx.unam.ciencias.edd.VerticeArbolBinario;
import mx.unam.ciencias.edd.proyecto2.figuras.CirculoConBorde;
import mx.unam.ciencias.edd.proyecto2.figuras.GraficadorSVG;
import mx.unam.ciencias.edd.proyecto2.figuras.Linea;
import mx.unam.ciencias.edd.proyecto2.figuras.Texto;

/**
 * <p>Clase para graficar arboles binarios.</p>
 */
public abstract class GraficadorArbolBinario<T> extends GraficadorEstructura {

    /* Radio. */
    protected static final int RADIO = 20;
    /* Tamaño del círculo. */
    protected static final int TAMANO_CIRCULO = (RADIO + GROSOR_BORDE) * 2;
    /* Multiplicador para el desplazamiento vertical. */
    protected static final int MUL_Y = 2;
    /* Espacio entre los vértices del último nivel del árbol. */
    protected static final int MUL_X = TAMANO_CIRCULO;

    /* Arbol binario a graficar. */
    protected ArbolBinario<T> arbol;

    /**
     * Define el estado inicial de un graficador de arboles binarios.
     * @param arbol el arbol binario a graficar.
     */
    public GraficadorArbolBinario(ArbolBinario<T> arbol) {
        this.arbol = arbol;
    }

    /**
     * Grafica los elementos del arbol binario.
     */
    @Override
    public void graficaElementos() {
        // Dependiendo de la altura genera el ancho.
        // Multiplica el número de elementos en el nivel más grande por el tamaño del círculo.
        int n = (1 << arbol.altura());
	    int ancho = (n * TAMANO_CIRCULO) + (MUL_X * (n - 1));
        // int n = arbol.getElementos();
        // int ancho = n * TAMANO_CIRCULO;
        GraficadorSVG graficadorVertices = new GraficadorSVG();
        graficaElementos(0, ancho, arbol.raiz(), 0, graficadorVertices);
        // Agregamos los vértices al final para superponer sobre las líneas.
        graficadorVertices.forEach(f -> graficador.agrega(f));
    }

    /**
     * Grafica los elementos del arbol binario.
     * @param ini el inicio del canvas.
     * @param fin el fin del canvas.
     * @param v el vertice del arbol.
     * @param h la altura del canvas.
     * @param graficadorVertices el gravicador svg de vértices.
     */
    protected void graficaElementos(int ini, int fin, VerticeArbolBinario<T> v, int h, GraficadorSVG graficadorVertices) {
        // Centro del circulo.
        int x = ini + ((fin - ini) >> 1);
        int y = h + (RADIO + GROSOR_BORDE);
        if (v.hayPadre())
            graficador.agrega(creaLinea(ini, fin, v, h, x, y));
        graficaVertice(v, x, y, graficadorVertices);
        if (v.hayIzquierdo())
            graficaElementos(ini, x, v.izquierdo(), h + (TAMANO_CIRCULO * MUL_Y), graficadorVertices);
        if (v.hayDerecho())
            graficaElementos(x, fin, v.derecho(), h + (TAMANO_CIRCULO * MUL_Y), graficadorVertices);
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
    protected Linea creaLinea(int ini, int fin, VerticeArbolBinario<T> v, int h, int x, int y) {
        VerticeArbolBinario<T> p = v.padre();
        int xLinea = fin;
        if (p.hayDerecho())
            if (p.derecho() == v)
                xLinea = ini;
        int yLinea = h - TAMANO_CIRCULO - RADIO;
        return new Linea(x, y, xLinea, yLinea, GROSOR_BORDE);
    }

    /**
     * Grafica un vértice del árbol.
     * @param v el vertice del arbol.
     * @param x la coordenada x del vértice.
     * @param y la coordenada y del vértice.
     * @param graficadorVertices el gravicador svg de vértices.
     */
    protected void graficaVertice(VerticeArbolBinario<T> v, int x, int y, GraficadorSVG graficadorVertices) {
        // Configuración del texto.
        int yTexto = y + (TAMANO_TXT / 3);
        // Agrega figuras
        graficadorVertices.agrega(creaCirculo(v, x, y),
                          creaTexto(v, x, yTexto));
    }

    /**
     * Regresa un círculo con borde.
     * @param v el vertice del arbol.
     * @param x la coordenada x del centro del círculo
     * @param y la coordenada y del centro del círculo
     * @return un círculo con borde.
     */
    protected CirculoConBorde creaCirculo(VerticeArbolBinario<T> v, int x, int y) {
        return new CirculoConBorde(x, y, RADIO, "white", GROSOR_BORDE, "black");
    }

    /**
     * Regresa un texto.
     * @param v el vertice del arbol.
     * @param x la coordenada x del texto.
     * @param y la coordenada y del texto.
     * @return un texto.
     */
    protected Texto creaTexto(VerticeArbolBinario<T> v, int x, int y) {
        return new Texto(x, y, v.get().toString(), TAMANO_TXT, "middle", "sans-serif");
    }
}
