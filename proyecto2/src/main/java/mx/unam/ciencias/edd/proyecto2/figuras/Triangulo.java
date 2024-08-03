package mx.unam.ciencias.edd.proyecto2.figuras;

/**
 * <p>Clase para representar triángulos. Un Triangulo cuenta con una coordenada x y y,
 *  y dos puntos extremos.</p>
 */
public class Triangulo extends FiguraBasica {

    /* Punto dos. */
    protected Punto p2;
    /* Punto tres */
    protected Punto p3;

    /**
     * Define el estado inicial de un Triangulo sin color.
     * @param x la coordenada x del punto uno triángulo.
     * @param y la coordenada y del punto uno triángulo.
     * @param x2 la coordenada x del punto dos triángulo.
     * @param y2 la coordenada y del punto dos triángulo.
     * @param x3 la coordenada x del punto tres triángulo.
     * @param y3 la coordenada y del punto tres triángulo.
     */
    public Triangulo(int x,
                     int y,
                     int x2,
                     int y2,
                     int x3,
                     int y3) {
        super(x, y);
        p2 = new Punto(x2,y2);
        p3 = new Punto(x3,y3);
    }

    /**
     * Define el estado inicial de un Triangulo sin color.
     * @param x la coordenada x del punto uno triángulo.
     * @param y la coordenada y del punto uno triángulo.
     * @param x2 la coordenada x del punto dos triángulo.
     * @param y2 la coordenada y del punto dos triángulo.
     * @param x3 la coordenada x del punto tres triángulo.
     * @param y3 la coordenada y del punto tres triángulo.
     */
    public Triangulo(int x,
                     int y,
                     int x2,
                     int y2,
                     int x3,
                     int y3,
                     String color) {
        super(x, y, color);
        p2 = new Punto(x2,y2);
        p3 = new Punto(x3,y3);
    }

    /**
     * Regresa la coordenada x del triángulo.
     * @return la coordenada x del triángulo.
     */
    @Override 
    public int getX() {
        return Math.min(Math.min(x, p2.x), p3.x);
    }

    /**
     * Regresa la coordenada y del triángulo.
     * @return la coordenada y del triángulo.
     */
    @Override 
    public int getY() {
        return Math.min(Math.min(y, p2.y), p3.y);
    }

    /**
     * Regresa el ancho de la figura.
     * @return el ancho de la figura.
     */
    @Override 
    public int getAncho() {
        return Math.max(Math.max(Math.abs(x - p2.x), Math.abs(p3.x - p2.x)), Math.abs(p3.x - x));
    }

    /**
     * Regresa el alto de la figura.
     * @return el alto de la figura.
     */
    @Override 
    public int getAlto() {
        return Math.max(Math.max(Math.abs(y - p2.y), Math.abs(p3.y - p2.y)), Math.abs(p3.y - y));
    }

    /**
     * Regresa el código SVG de la figura.
     * @return el código SVG de la figura.
     */
    @Override 
    public String toSVG() {
        return String.format("<polygon points='%d,%d %d,%d %d,%d' fill='%s' />",
                            x, y,
                            p2.x, p2.y,
                            p3.x, p3.y,
                            color);
    }
}