package mx.unam.ciencias.edd.proyecto2.graficadores;

import mx.unam.ciencias.edd.Coleccion;
import mx.unam.ciencias.edd.ComparableIndexable;
import mx.unam.ciencias.edd.ArbolBinarioCompleto;
import mx.unam.ciencias.edd.MonticuloMinimo;
import mx.unam.ciencias.edd.proyecto2.figuras.CirculoConBorde;
import mx.unam.ciencias.edd.proyecto2.figuras.GraficadorSVG;
import mx.unam.ciencias.edd.proyecto2.figuras.Linea;
import mx.unam.ciencias.edd.proyecto2.figuras.RectanguloConBorde;
import mx.unam.ciencias.edd.proyecto2.figuras.Texto;

/**
 * <p>Clase para graficar montículos mínimos.</p>
 * 
 * <p>Grafica su representación en memoria, así como su representación conceptual.</p>
 */
public class GraficadorMonticuloMinimo<T extends ComparableIndexable<T>> extends GraficadorEstructura{

    /* Representación en memoria. */
    /* Alto del rectángulo */
    protected static final int ALTO_RECTANGULO = 65;
    /* Ancho del rectángulo */
    protected static final int ANCHO_RECTANGULO = 55;
    /* Espacio entre árbol y arreglo */
    protected static final int ESPACIO = 10;

    /* Representación conceptual. */
    /* Radio. */
    protected static final int RADIO = 20;
    /* Tamaño del círculo. */
    protected static final int TAMANO_CIRCULO = (RADIO + GROSOR_BORDE) * 2;
    /* Multiplicador para el desplazamiento vertical. */
    protected static final int MUL_Y = 2;
    /* Espacio entre los vértices del último nivel del árbol. */
    protected static final int MUL_X = TAMANO_CIRCULO;

    /* Montículo mínimo. */
    protected MonticuloMinimo<T> monticuloMinimo;

    /**
     * Define el estado inicial de un graficador para montículos mínimos.
     * @param monticuloMinimo el montículo mínimo con el que se construye el montículo mínimo.
     */
    public GraficadorMonticuloMinimo(MonticuloMinimo<T> monticuloMinimo) {
        this.monticuloMinimo = monticuloMinimo;
    }

    /**
     * Define el estado inicial de un graficador de montículos mínimos
     * @param coleccion la coleccion con la que se construye el montículo
     */
    public GraficadorMonticuloMinimo(Coleccion<T> coleccion) {
        this.monticuloMinimo = new MonticuloMinimo<T>(coleccion);
    }

    /**
     * Grafica los elementos del montículo mínimo
     */
    @Override
    public void graficaElementos() {
        graficaArreglo();
        // Dependiendo de la altura genera el ancho.
        // Multiplica el número de elementos en el nivel más grande por el tamaño del círculo.
        int n = monticuloMinimo.getElementos();
	    int ancho = (n * TAMANO_CIRCULO) + (MUL_X * (n - 1));
        int y = ALTO_RECTANGULO + ESPACIO * 3;
        GraficadorSVG graficadorVertices = new GraficadorSVG();
        graficaElementos(0, ancho, 0, y, graficadorVertices);
        // Agregamos los vértices al final para superponer sobre las líneas.
        graficadorVertices.forEach(f -> graficador.agrega(f));
    }

    /**
     * Grafica los elementos del montículo mínimo en forma de arreglo.
     */
    protected void graficaArreglo() {
        int i = 0;
        int x = 0;
        int y = 0;
        for(T e : monticuloMinimo) {
            graficaArreglo(x, y, e, i++);
            x += ANCHO_RECTANGULO;
        }
    }

    /**
     * Grafica un elemento del montículo mínimo en forma de arreglo.
     * @param x la coordemada x.
     * @param y la coordenada y.
     * @param e el elemento.
     * @param i el índice
     */
    protected void graficaArreglo(int x, int y, T e, int i) {
        // Configuración del texto.
        int xTexto = x + (ANCHO_RECTANGULO >> 1);
        int yTexto = y + (ALTO_RECTANGULO >> 1) + (TAMANO_TXT >> 1);
        String txt = e.toString();

        graficador.agrega(new RectanguloConBorde(x, y, ANCHO_RECTANGULO, ALTO_RECTANGULO, "white", GROSOR_BORDE, "black"), // Rectangulo
                          new Texto(xTexto, yTexto, txt, TAMANO_TXT, "middle", "sans-serif"), // Texto
                          new Texto(x + ESPACIO, y + TAMANO_TXT, String.format("%d", i), 2 * TAMANO_TXT / 3, "middle", "sans-serif")); //índice
    }

    /**
     * Grafica los elementos del montículo mínimo en su representación conceptual.
     * @param ini el inicio del canvas.
     * @param fin el fin del canvas.
     * @param i el índice del vertice del montículo mínimo.
     * @param h la altura del canvas.
     * @param graficadorVertices el gravicador svg de vértices.
     */
    protected void graficaElementos(int ini, int fin, int i, int h, GraficadorSVG graficadorVertices) {
        // Centro del circulo.
        int x = ini + ((fin - ini) >> 1);
        int y = h + (RADIO + GROSOR_BORDE);
        if (i != 0)
            graficador.agrega(creaLinea(ini, fin, i, h, x, y));
        graficaVertice(i, x, y, graficadorVertices);
        int izq = 2 * i + 1; // izquierdo
        int der = 2 * i + 2; // derecho
        if (izq < monticuloMinimo.getElementos())
            graficaElementos(ini, x, izq, h + (TAMANO_CIRCULO * MUL_Y), graficadorVertices);
        if (der < monticuloMinimo.getElementos())
            graficaElementos(x, fin, der, h + (TAMANO_CIRCULO * MUL_Y), graficadorVertices);
    }

    /**
     * Regresa una línea.
     * @param ini el inicio del canvas.
     * @param fin el fin del canvas.
     * @param i el índice del vertice del montículo mínimo.
     * @param h la altura del canvas.
     * @param x la coordenada x de la línea.
     * @param y la coordenada y de la línea.
    */
    protected Linea creaLinea(int ini, int fin, int i, int h, int x, int y) {
        int p = i - 1 >> 1; // padre
        int der = 2 * p + 2; // derecho
        int xLinea = fin;
        if (der < monticuloMinimo.getElementos())  // hay derecho
            if (monticuloMinimo.get(der) == (monticuloMinimo.get(i))) 
                xLinea = ini;
        int yLinea = h - TAMANO_CIRCULO - RADIO;
        return new Linea(x, y, xLinea, yLinea, GROSOR_BORDE);
    }

    /**
     * Grafica un vértice del montículo mínimo.
     * @param i el índice del vertice del montículo mínimo.
     * @param x la coordenada x del vértice.
     * @param y la coordenada y del vértice.
     * @param graficadorVertices el graficador svg de vértices.
     */
    protected void graficaVertice(int i, int x, int y, GraficadorSVG graficadorVertices) {
        // Configuración del texto.
        int yTexto = y + (TAMANO_TXT / 3);
        // Agrega figuras
        graficadorVertices.agrega(creaCirculo(i, x, y),
                          creaTexto(i, x, yTexto));
    }

    /**
     * Regresa un círculo con borde.
     * @param i el índice del vertice del montículo mínimo.
     * @param x la coordenada x del centro del círculo
     * @param y la coordenada y del centro del círculo
     * @return un círculo con borde.
     */
    protected CirculoConBorde creaCirculo(int i, int x, int y) {
        return new CirculoConBorde(x, y, RADIO, "white", GROSOR_BORDE, "black");
    }

    /**
     * Regresa un texto.
     * @param i el índice del vertice del montículo mínimo.
     * @param x la coordenada x del texto.
     * @param y la coordenada y del texto.
     * @return un texto.
     */
    protected Texto creaTexto(int i, int x, int y) {
        String txt = monticuloMinimo.get(i).toString();
        return new Texto(x, y, txt, TAMANO_TXT, "middle", "sans-serif");
    }
}