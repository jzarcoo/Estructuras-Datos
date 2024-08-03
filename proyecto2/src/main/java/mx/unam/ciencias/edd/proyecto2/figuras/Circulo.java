package mx.unam.ciencias.edd.proyecto2.figuras;

/**
 * <p>Clase para representar círculos. Un circulo cuenta con un radio.</p>
 */
public class Circulo extends FiguraBasica {

    /* Radio del círculo. */
    protected int radio;

    /**
     * Define el estado inicial de un circulo sin color.
     * @param x la coordenada x del círculo.
     * @param y la coordenada y del círculo.
     * @param radio el radio del círculo.
     */
    public Circulo(int x,
                   int y,
                   int radio) {
        super(x, y);
        this.radio = radio;
    }

    /**
     * Define el estado inicial de un circulo con color.
     * @param x la coordenada x del círculo.
     * @param y la coordenada y del círculo.
     * @param radio el radio del círculo.
     * @param color el color del círculo.
     */
    public Circulo(int x,
                   int y,
                   int radio,
                   String color) {
        super(x, y, color);
        this.radio = radio;
    }

    /**
     * Define la nueva coordenada x de la figura
     * @param x la nueva coordenada x de la figura.
     */
    @Override
    public void setX(int x) {
        this.x = x + radio;
    }

    /**
     * Regresa la coordenada x del círculo.
     * @return la coordenada x del círculo.
     */
    @Override 
    public int getX() {
        return x - radio;
    }

    /**
     * Regresa la coordenada y del círculo.
     * @return la coordenada y del círculo.
     */
    @Override 
    public int getY() {
        return y - radio;
    }

    /**
     * Regresa el ancho de la figura.
     * @return el ancho de la figura.
     */
    @Override 
    public int getAncho() {
        return radio * 2;
    }

    /**
     * Regresa el alto de la figura.
     * @return el alto de la figura.
     */
    @Override 
    public int getAlto() {
        return radio * 2;
    }

    /**
     * Regresa el código SVG de la figura.
     * @return el código SVG de la figura.
     */
    @Override 
    public String toSVG() {
        return String.format("<circle cx='%d' cy='%d' r='%d' fill='%s'/>",
                             x, y, radio, color);
    }
}