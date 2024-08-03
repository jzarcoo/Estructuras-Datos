package mx.unam.ciencias.edd.proyecto2.figuras;

/**
 * <p>Interfaz de solo lectura para representar figuras en SVG.</p>
 */
public interface Figura {

    /**
     * Regresa la coordenada x de la figura.
     * @return la coordenada x de la figura.
     */
    public int getX();

    /**
     * Regresa la coordenada y de la figura.
     * @return la coordenada y de la figura.
     */
    public int getY();

    /**
     * Regresa el ancho de la figura.
     * @return el ancho de la figura.
     */
    public int getAncho();

    /**
     * Regresa el alto de la figura.
     * @return el alto de la figura.
     */
    public int getAlto();

    /**
     * Regresa el código SVG de la figura.
     * @return el código SVG de la figura.
     */
    public String toSVG();
}