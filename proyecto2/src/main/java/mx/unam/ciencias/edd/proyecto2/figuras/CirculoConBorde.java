package mx.unam.ciencias.edd.proyecto2.figuras;

/**
 * <p>Clase para representar círculos con borde.</p>
 */
public class CirculoConBorde extends Circulo {

    /* Grosor del borde. */
    protected int grosor;
    /* Color del borde. */
    protected String colorBorde;

    /**
     * Define el estado inicial de un círculo con borde.
     * @param x la coordenada x del círculo.
     * @param y la coordenada y del círculo.
     * @param radio el radio del círculo.
     * @param color el color del círculo.
     * @param grosor el grosor del borde.
     * @param colorBorde el color del borde.
     */
    public CirculoConBorde(int x,
                           int y,
                           int radio,
                           String color,
                           int grosor,
                           String colorBorde) {
        super(x, y, radio, color);
        this.grosor = grosor;
        this.colorBorde = colorBorde;
    }

    /**
     * Define la nueva coordenada x de la figura
     * @param x la nueva coordenada x de la figura.
     */
    @Override
    public void setX(int x) {
        this.x = x + radio + grosor;
    }

    /**
     * Regresa la coordenada x del círculo.
     * @return la coordenada x del círculo.
     */
    @Override 
    public int getX() {
        return x - radio - grosor;
    }

    /**
     * Regresa la coordenada y del círculo.
     * @return la coordenada y del círculo.
     */
    @Override 
    public int getY() {
        return y - radio - grosor;
    }

    /**
     * Regresa el ancho de la figura.
     * @return el ancho de la figura.
     */
    @Override 
    public int getAncho() {
        return 2 * (radio + grosor);
    }

    /**
     * Regresa el alto de la figura.
     * @return el alto de la figura.
     */
    @Override 
    public int getAlto() {
        return 2 * (radio + grosor);
    }

    /**
     * Regresa el código SVG del círculo con borde.
     * @return el código SVG del círculo con borde.
     */
    @Override
    public String toSVG() {
        return String.format("<circle cx='%d' cy='%d' r='%d' fill='%s' stroke='%s' stroke-width='%d'/>",
                                x, y, radio, color, colorBorde, grosor);
    }
}