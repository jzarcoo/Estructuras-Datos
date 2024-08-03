package mx.unam.ciencias.edd.proyecto2.figuras;

/**
 * <p>Clase para representar rectangulos en SVG con borde. Un RectanguloConBorde cuenta
 * con un grosor y un color de borde.</p>
 */
public class RectanguloConBorde extends Rectangulo {

    /* Grosor del borde. */
    protected int grosor;
    /* Color del borde. */
    protected String colorBorde;

    /**
     * Define el estado inicial de un rectangulo con borde.
     * @param x la coordenada x del rectangulo.
     * @param y la coordenada y del rectangulo.
     * @param ancho el ancho del rectangulo.
     * @param alto el alto del rectangulo.
     * @param grosor el grosor del borde.
     * @param color el color del rectangulo.
     * @param colorBorde el color del borde.
     */
    public RectanguloConBorde(int x, 
                              int y, 
                              int ancho, 
                              int alto, 
                              String color,
                              int grosor,  
                              String colorBorde) {
        super(x, y, ancho, alto, color);
        this.grosor = grosor;
        this.colorBorde = colorBorde;
    }

    /**
     * Regresa la coordenada x del rectángulo.
     * @return la coordenada x del rectángulo.
     */
    @Override 
    public int getX() {
        return x;
    }

    /**
     * Regresa la coordenada y del rectángulo.
     * @return la coordenada y del rectángulo.
     */
    @Override 
    public int getY() {
        return y;
    }

    /**
     * Regresa el ancho de la figura.
     * @return el ancho de la figura.
     */
    @Override
    public int getAncho() {
        return ancho;
    }

    /**
     * Regresa el alto de la figura.
     * @return el alto de la figura.
     */
    @Override
    public int getAlto() {
        return alto;
    }

    /**
     * Regresa el código SVG del rectangulo con borde.
     * @return el código SVG del rectangulo con borde.
     */
    @Override
    public String toSVG() {
        return String.format("<rect x='%d' y='%d' width='%d' height='%d' fill='%s' stroke='%s' stroke-width='%d'/>",
                                x, y, ancho, alto, color, colorBorde, grosor);
    }
}