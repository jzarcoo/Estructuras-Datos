package mx.unam.ciencias.edd.proyecto2.figuras;

/**
 * <p>Clase para representar Flechas en SVG apuntando a la derecha y a la izquierda. Una FlechaBidireccional
 * cuenta con una línea, dos puntas en cada extremo y un grosor.</p>
 */
public class FlechaBidireccional extends FiguraBasica {

    /* Linea de la Flecha. */
    protected Linea linea;
    /* Grosor de la Flecha. */
    protected int grosor;
    /* Punta uno de la Flecha. */
    protected Triangulo p1;
    /* Punta dos de la Flecha. */
    protected Triangulo p2;

    /**
     * Define el estado inicial de una Flecha sin color.
     * @param x1 la coordenada x del punto de inicio de la Flecha.
     * @param y1 la coordenada y del punto de inicio de la Flecha.
     * @param x2 la coordenada x del punto final de la Flecha.
     * @param y2 la coordenada y del punto final de la Flecha.
     * @param grosor el grosor de la línea.
     */
    public FlechaBidireccional(int x1, 
                 int y1, 
                 int x2, 
                 int y2,
                 int grosor) {
        super(x1, y1);
        linea = new Linea(x1, y1, x2, y2, grosor);
        this.grosor = grosor;
        // Crea puntas
        int largo = Math.max(linea.getAlto(), linea.getAncho());
        int t = largo / 4; // Tamaño punta
        int gx = (x1 > x2) ? x1 : x2; //grande x
        int gy = (x1 == gx) ? y1 : y2; //grande y
        int px = (gx == x1) ? x2 : x1; //pequeño x
        int py = (gy == y1) ? y2 : y1; //pequeño y
        p1 = new Triangulo(px, py,
                           px + t, py - t,
                           px + t, py + t);
        p2 = new Triangulo(gx, gy,
                            gx - t, gy - t,
                            gx - t, gy + t);
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
    public FlechaBidireccional(int x1, 
                 int y1, 
                 int x2, 
                 int y2,
                 int grosor,
                 String color) {
        super(x1, y1, color);
        linea = new Linea(x1, y1, x2, y2, grosor);
        this.grosor = grosor;
        // Crea puntas
        int largo = Math.max(linea.getAlto(), linea.getAncho());
        int t = largo; // Tamaño punta
        int gx = (x1 > x2) ? x1 : x2; //grande x
        int gy = (x1 == gx) ? y1 : y2; //grande y
        int px = (x2 > x1) ? x2 : x1; //pequeño x
        int py = (x2 == px) ? y2 : y1; //pequeño y
        p1 = new Triangulo(px, py,
                           px + t, py - t,
                           px + t, py + t);
        p2 = new Triangulo(gx, gy,
                            gx - t, gy - t,
                            gx - t, gy + t);
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
        return Math.max(Math.max(linea.getAncho(), p1.getAncho()), p2.getAncho());
    }

    /**
     * Regresa el alto de la figura.
     * @return el alto de la figura.
     */
    @Override
    public int getAlto() {
        return Math.max(Math.max(linea.getAlto(), p1.getAlto()), p2.getAlto());
    }

    /**
     * Regresa el código SVG de la Flecha.
     * @return el código SVG de la Flecha.
     */
    @Override
    public String toSVG() {
        StringBuilder sb = new StringBuilder();
        sb.append(linea.toSVG());
        sb.append(p1.toSVG());
        sb.append(p2.toSVG());
        return sb.toString();
    }

}