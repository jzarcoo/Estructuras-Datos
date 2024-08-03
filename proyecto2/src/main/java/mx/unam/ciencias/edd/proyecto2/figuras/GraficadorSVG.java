package mx.unam.ciencias.edd.proyecto2.figuras;

import java.util.Iterator;
import mx.unam.ciencias.edd.Lista;

/**
 * <p>Clase para representar un graficador de figuras. Un graficador tiene 
 * una lista de figuras, las cuales se dibujan en el orden en el que se 
 * agregaron.</p>
 * 
 * <p>No utilizamos memoizacion para el alto y el ancho debido a que se 
 * pueden eliminar figuras.</p>
 */
public class GraficadorSVG extends FiguraBasica implements Iterable<Figura>{

    /* Clase interna privada para iteradores. */
    private class Iterador implements Iterator<Figura> {

        /* Iterador auxiliar. */
        private Iterator<Figura> iterador;

        /* Construye un nuevo iterador, auxiliándose de la lista de figuras. */
        public Iterador() {
	        iterador = figuras.iterator(); 
        }

        /* Nos dice si hay un siguiente elemento. */
        @Override public boolean hasNext() {
            return iterador.hasNext();
        }

        /* Regresa el siguiente elemento. */
        @Override public Figura next() {
            return iterador.next();
        }
    }

    /* Figuras del graficador. */
    protected Lista<Figura> figuras;

    /**
     * Crea un graficador con las figuras dadas.
     * @param figuras las figuras del graficador.
     */
    public GraficadorSVG(Figura... figuras) {
        super(0, 0);
        this.figuras = new Lista<Figura>();
        agrega(figuras);
    }

    /**
     * Agrega las figuras dadas al graficador.
     * @param figuras las figuras a agregar.
     */
    public void agrega(Figura... figuras) {
        for (Figura f : figuras)
            agrega(f);
    }

    /**
     * Agrega una figura al graficador.
     * @param figura la figura a agregar.
     */
    public void agrega(Figura figura) {
        figuras.agrega(figura);
    }

    /**
     * Elimina las figuras dadas del graficador.
     * @param figuras las figuras a eliminar.
     */
    public void elimina(Figura... figuras) {
        for (Figura f : figuras)
            elimina(f);
    }

    /**
     * Elimina una figura del graficador.
     * @param figura la figura a eliminar.
     */
    public void elimina(Figura figura) {
        figuras.elimina(figura);
    }

    /**
     * Elimina la última figura del graficador.
     */
    public void eliminaUltimo() {
        figuras.eliminaUltimo();
    }

    /**
     * Limpia el graficador.
     */
    public void limpia() {
        figuras.limpia();
    }

    /**
     * Regresa la coordenada x de la figura.
     * @return la coordenada x de la figura.
     */
    @Override
    public int getX() {
        if (figuras.esVacia())
            return 0;
        int x = figuras.get(0).getX();
        for (Figura f : figuras)
            if (f.getX() < x)
                x = f.getX();
        return x;
    }

    /**
     * Regresa la coordenada y de la figura.
     * @return la coordenada y de la figura.
     */
    @Override
    public int getY() {
        if (figuras.esVacia())
            return 0;
        int y = figuras.get(0).getY();
        for (Figura f : figuras)
            if (f.getY() < y)
                return f.getY();
        return y;
    }

    /**
     * Nos regresa el ancho svg que ocupan las figuras en el lienzo 
     * del graficador. Además, recorre las figuras para no dejar espacio 
     * en blanco a la izquierda.
     * @return el ancho svg que ocupan las figuras en el lienzo 
     *         del graficador
     */
    private int getAnchoYRecorre() {
        // Coordenada x más a la izquierda, (más pequeña)
        int minX = getX(); 
        int ancho = 0;
        for(Figura f : figuras) {
            // Recorro la figura el espacio sobrante.
            setX(f, f.getX() - minX);
            // Calculo el ancho.
            int figuraRelativaX = f.getX() - x;
            int figuraAncho = figuraRelativaX + f.getAncho();
            if (figuraAncho > ancho)
                ancho = figuraAncho;
        }
        return ancho;
    }

    /**
     * Define la coordenada x de la figura recibida.
     * @param figura la figura a la que queremos definirle la coordenada x.
     * @param x la nueva coordenada x de la figura.
     * @throws IllegalArgumentException si la figura no es válida.
     */
    private void setX(Figura figura, int x) {
        if (figura == null ||
            !(figura instanceof FiguraBasica)) {
            throw new IllegalArgumentException("Figura inválida.");
        }
        FiguraBasica f = (FiguraBasica) figura;
        f.setX(x);
    }

    /**
     * Regresa el ancho real de la figura.
     * @return el ancho real de la figura.
     */
    @Override
    public int getAncho() {
        return getAncho(getX());
    }

    /**
     * Regresa el ancho svg que ocupa la figura.
     * @return el ancho svg que ocupa la figura.
     */
    public int getAnchoSVG() {
        return getAncho(0);
    }

    /**
     * Regresa el ancho en base a la coordenada x.
     * @param x la coordenada x donde empezara a contar.
     */
    private int getAncho(int x) {
        int ancho = 0;
        for (Figura f : figuras) {
            int figuraRelativaX = f.getX() - x;
            int figuraAncho = figuraRelativaX + f.getAncho();
            if (figuraAncho > ancho)
                ancho = figuraAncho;
        }
        return ancho;
    }

    /**
     * Regresa el alto real de la figura.
     * @return el alto real de la figura.
     */
    @Override
    public int getAlto() {
        return getAlto(getY());
    }

    /**
     * Regresa el alto svg que ocupa la figura.
     * @return el alto svg que ocupa la figura.
     */
    public int getAltoSVG() {
        return getAlto(0);
    }

    /**
     * Regresa el alto en base a la coordenada y.
     * @param y la coordenada y donde empezara a contar.
     */
    private int getAlto(int y) {
        int alto = 0;
        for (Figura f : figuras) {
            int figuraRelativaY = f.getY() - y;
            int figuraAlto = figuraRelativaY + f.getAlto();
            if (figuraAlto > alto)
                alto = figuraAlto;
        }
        return alto;
    }

    /**
     * Regresa el código SVG de la figura.
     * @return el código SVG de la figura.
     */
    @Override
    public String toSVG() {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version='1.0' encoding='UTF-8' ?>\n");
        sb.append(String.format("<svg width='%d' height='%d'>\n", getAnchoYRecorre(), getAltoSVG()));
        sb.append("\t<g>\n");
        figuras.forEach(f -> sb.append("\t\t" + f.toSVG() + "\n"));
        sb.append("\t</g>\n");
        sb.append("</svg>");
        return sb.toString();
    }

    /**
     * Regresa un iterador para iterar el graficador SVG. El graficador SVG se itera en el
     * orden en que fueron agregados sus elementos.
     * @return un iterador para iterar el graficador SVG.
     */
    @Override public Iterator<Figura> iterator() {
        return new Iterador();
    }

}