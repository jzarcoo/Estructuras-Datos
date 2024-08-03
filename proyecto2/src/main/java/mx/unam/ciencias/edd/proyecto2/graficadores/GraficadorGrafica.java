package mx.unam.ciencias.edd.proyecto2.graficadores;

import mx.unam.ciencias.edd.Color;
import mx.unam.ciencias.edd.Grafica;
import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.VerticeGrafica;
import mx.unam.ciencias.edd.proyecto2.figuras.CirculoConBorde;
import mx.unam.ciencias.edd.proyecto2.figuras.GraficadorSVG;
import mx.unam.ciencias.edd.proyecto2.figuras.Linea;
import mx.unam.ciencias.edd.proyecto2.figuras.Texto;

/**
 * <p>Clase para graficar gráficas.</p>
 */
public class GraficadorGrafica<T> extends GraficadorEstructura {

    /* Clase interna privada para asignar coordenadas a los vértices. */
    protected class VerticeConCoordenadas<T> implements VerticeGrafica<T> {

        /* Vértice */
        private VerticeGrafica<T> vertice;
        /* Coordenada x del vértice. */
        private int x;
        /* Coordenada y del vértice */
        private int y;

        /**
         * Define el estado inicial de un vértice con coordenadas.
         * @param vertice el vértice original.
         * @param x la coordenada x del vértice.
         * @param y la coordenada y del vértice.
         */
        public VerticeConCoordenadas(VerticeGrafica<T> vertice, int x, int y) {
            this.vertice = vertice;
            this.x = x;
            this.y = y;
        }

        /**
         * Define el nuevo valor de la coordenada x del vértice.
         * @param x el nuevo valor de la coordenada x del vértice.
         */
        public void setX(int x) {
            this.x = x;
        }

        /**
         * Regresa el valor de la coordenada x del vértice.
         * @return el valor de la coordenada x del vértice.
         */
        public int getX() {
            return x;
        }

        /**
         * Define el nuevo valor de la coordenada y del vértice.
         * @param y el nuevo valor de la coordenada y del vértice.
         */
        public void setY(int y) {
            this.y = y;
        }

        /**
         * Regresa el valor de la coordenada y del vértice.
         * @return el valor de la coordenada y del vértice.
         */
        public int getY() {
            return y;
        }

        /**
         * Regresa el elemento del vértice.
         * @return el elemento del vértice.
         */
        public T get() {
            return vertice.get();
        }
    
        /**
         * Regresa el grado del vértice.
         * @return el grado del vértice.
         */
        public int getGrado() {
            return vertice.getGrado();
        }
    
        /**
         * Regresa el color del vértice.
         * @return el color del vértice.
         */
        public Color getColor() {
            return vertice.getColor();
        }
    
        /**
         * Regresa un iterable con los vecinos del vértice.
         * @return un iterable con los vecinos del vértice.
         */
        public Iterable<? extends VerticeGrafica<T>> vecinos() {
            return vertice.vecinos();
        }
    }

    /* Radio. */
    protected static final int RADIO = 20;
    /* Tamaño del círculo. */
    protected static final int TAMANO_CIRCULO = (RADIO + GROSOR_BORDE) * 2;
    /* Ángulo entre cada vértice en radianes. */
    protected final double ANGULO;
    /* Radio de la circunferencia de la gráfica. */
    protected final int RADIO_GRAFICA;

    /* Gráfica. */
    protected Grafica<T> grafica;

    /**
     * Define el estado inicial de un graficador de gráficas.
     * @param grafica la grafica a graficar.
     */
    public GraficadorGrafica(Grafica<T> grafica) {
        this.grafica = grafica;
        ANGULO = Math.PI * 2.0 / grafica.getElementos();
        RADIO_GRAFICA = grafica.getElementos() * RADIO;
    }

    /**
     * Grafica los elementos de la grafica.
     */
    @Override
    public void graficaElementos() {
        GraficadorSVG graficadorVertices = new GraficadorSVG();
        Lista<VerticeConCoordenadas<T>> verticesConCoordenadas = new Lista<VerticeConCoordenadas<T>>();
        grafica.paraCadaVertice(v -> verticesConCoordenadas.agrega(new VerticeConCoordenadas<T>(v, 0, 0)));
        double anguloSVG = 0;
        Lista<VerticeConCoordenadas<T>> verticesGraficados = new Lista<VerticeConCoordenadas<T>>();
        for(VerticeConCoordenadas<T> vertice : verticesConCoordenadas) {
            // Calcula coordenadas x y y del vértice.
            int x = (int) (RADIO_GRAFICA * Math.cos(anguloSVG)); // rcos(a)
            int y = (int) (RADIO_GRAFICA * Math.sin(anguloSVG)); // rsen(a)
            x += RADIO_GRAFICA + RADIO;
            y += RADIO_GRAFICA + RADIO;
            vertice.x = x;
            vertice.y = y;
            // Gráfica vértices.
            verticesGraficados.agrega(vertice);
            graficaVertice(vertice, graficadorVertices);
            // Gráfica aristas.
            for(VerticeGrafica<T> vecino : vertice.vecinos()) {
                VerticeConCoordenadas<T> v = busca(vecino, verticesGraficados);
                if(v != null)
                    graficaArista(vertice, v);
            }
            anguloSVG += ANGULO;
        }
        // Agregamos los vértices al final para superponer sobre las líneas.
        graficadorVertices.forEach(f -> graficador.agrega(f));
    }

    /**
     * Busca el vértice en la lista de vertices graficados dada.
     * @param vertice el vértice a buscar.
     * @param verticesGraficados los vértices graficados donde hay que buscar.
     * @return el vértice en la lista de vertices graficados dada o, <code>null</code> en otro caso.
     */
    private VerticeConCoordenadas<T> busca(VerticeGrafica<T> vertice, Lista<VerticeConCoordenadas<T>> verticesGraficados) {
        for (VerticeConCoordenadas<T> v : verticesGraficados)
            if (v.get().equals(vertice.get()))
                return v;
        return null;
    }

    /**
     * Grafica un vértice de la gráfica.
     * @param v el vertice con coordenadas de la gráfica.
     */
    protected void graficaVertice(VerticeConCoordenadas<T> v, GraficadorSVG graficador) {
        // Configuración del texto.
        int yTexto = v.getY() + (TAMANO_TXT / 3);
        // Agrega figuras
        graficador.agrega(creaCirculo(v, v.x, v.y),
                          creaTexto(v, v.x, yTexto));
    }

    /**
     * Regresa un círculo con borde.
     * @param v el vertice con coordenadas de la gráfica.
     * @param x la coordenada x del centro del círculo
     * @param y la coordenada y del centro del círculo
     * @return un círculo con borde.
     */
    protected CirculoConBorde creaCirculo(VerticeConCoordenadas<T> v, int x, int y) {
        return new CirculoConBorde(x, y, RADIO, "white", GROSOR_BORDE, "black");
    }

    /**
     * Regresa un texto.
     * @param v el vertice con coordenadas de la gráfica.
     * @param x la coordenada x del texto.
     * @param y la coordenada y del texto.
     * @return un texto.
     */
    protected Texto creaTexto(VerticeConCoordenadas<T> v, int x, int y) {
        return new Texto(x, y, v.get().toString(), TAMANO_TXT, "middle", "sans-serif");
    }

    /**
     * Gráfica una arista de la gráfica.
     * @param u el vértice con coordenadas del extremo de la arista.
     * @param v el vértice con coordenadas del extremo opuesto de la arista
     */
    protected void graficaArista(VerticeConCoordenadas<T> u, VerticeConCoordenadas<T> v) {
        graficador.agrega(new Linea(u.x, u.y, v.x, v.y, GROSOR_BORDE));
    }
}
