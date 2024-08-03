package mx.unam.ciencias.edd.proyecto2.figuras;

/**
 * <p>Clase para representar Flechas en SVG apuntando a la derecha. Una FlechaDerecha cuenta
 * con unna línea, un grosor y una punta.</p>
 */
public class FlechaDerecha extends FiguraBasica {

    /* Linea de la Flecha. */
    protected Linea linea;
    /* Grosor de la Flecha. */
    protected int grosor;
    /* Punta de la Flecha. */
    protected Triangulo punta;

    /**
     * Define el estado inicial de una Flecha sin color.
     * @param x1 la coordenada x del punto de inicio de la Flecha.
     * @param y1 la coordenada y del punto de inicio de la Flecha.
     * @param x2 la coordenada x del punto final de la Flecha.
     * @param y2 la coordenada y del punto final de la Flecha.
     * @param grosor el grosor de la línea.
     */
    public FlechaDerecha(int x1, 
                 int y1, 
                 int x2, 
                 int y2,
                 int grosor) {
        super(x1, y1);
        linea = new Linea(x1, y1, x2, y2, grosor);
        this.grosor = grosor;
        int largo = Math.max(linea.getAlto(), linea.getAncho());
        int t = largo / 7; // Tamaño punta
        punta = new Triangulo(linea.x2, linea.y2,
                              linea.x2 - t, linea.y2 - t,
                              linea.x2 - t, linea.y2 + t);
    }

    /**
     * Define el estado inicial de una Flecha con color.
     * @param x1 la coordenada x del punto de inicio de la Flecha.
     * @param y1 la coordenada y del punto de inicio de la Flecha.
     * @param x2 la coordenada x del punto final de la Flecha.
     * @param y2 la coordenada y del punto final de la Flecha.
     * @param grosor el grosor de la línea.
     * @param color el color de la Flecha.
     */
    public FlechaDerecha(int x1, 
                 int y1, 
                 int x2, 
                 int y2,
                 int grosor,
                 String color) {
        super(x1, y1, color);
        linea = new Linea(x1, y1, x2, y2, grosor);
        this.grosor = grosor;
        int largo = Math.max(linea.getAlto(), linea.getAncho());
        int t = largo / 3; // Tamaño punta
        punta = new Triangulo(linea.x2, linea.y2,
                              linea.x2 - t, linea.y2 - t,
                              linea.x2 - t, linea.y2 + t);
    }

    /**
     * Regresa la coordenada x de la línea.
     * @return la coordenada x de la línea.
     */
    @Override 
    public int getX() {
        return linea.getX();
    }

    /**
     * Regresa la coordenada y de la línea.
     * @return la coordenada y de la línea.
     */
    @Override 
    public int getY() {
        return linea.getY();
    }

    /**
     * Regresa el ancho de la figura.
     * @return el ancho de la figura.
     */
    @Override
    public int getAncho() {
        return Math.max(linea.getAncho(), punta.getAncho());
    }

    /**
     * Regresa el alto de la figura.
     * @return el alto de la figura.
     */
    @Override
    public int getAlto() {
        return Math.max(linea.getAlto(), punta.getAlto());
    }

    /**
     * Regresa el código SVG de la Flecha.
     * @return el código SVG de la Flecha.
     */
    @Override
    public String toSVG() {
        StringBuilder sb = new StringBuilder();
        sb.append(linea.toSVG());
        sb.append(punta.toSVG());
        return sb.toString();
    }

}