package mx.unam.ciencias.edd.proyecto2.figuras;

/**
 * <p>Clase que modela un texto en SVG. Un texto cuenta con el contenido del texto, el tamaño 
 * de fuente, la alineación horizontal y el tipo de fuente.</p>
 */
public class Texto extends FiguraBasica {
	
	/* Texto. */
	protected String texto;
	/* Tamano del texto. */
	protected int tamano;
	/* Alineacion horizontal del texto. */
	protected String alineacionHorizontal;
	/* Fuente del texto. */
	protected String fuente;

	/**
	 * Define el estado inicial de un texto sin color.
	 * @param x la coordenada x del texto.
	 * @param y la coordenada y del texto.
	 * @param texto el texto del texto.
	 * @param tamano el tamano del texto.
	 * @param alineacionHorizontal la Alineacion horizontal del texto.
	 * @param fuente la fuente del texto.
	 */
	public Texto(int x,
				 int y,
				 String texto,
				 int tamano,
				 String alineacionHorizontal,
				 String fuente) {
		super(x, y);
		this.texto = texto;
		this.tamano = tamano;
		this.alineacionHorizontal = alineacionHorizontal;
		this.fuente = fuente;
	}

	/**
	 * Define el estado inicial de un texto con color.
	 * @param x la coordenada x del texto.
	 * @param y la coordenada y del texto.
	 * @param color el color del texto.
	 * @param texto el texto del texto.
	 * @param tamano el tamano del texto.
	 * @param alineacionHorizontal la Alineacion horizontal del texto.
	 * @param fuente la fuente del texto.
	 */
	public Texto(int x,
				int y,
				String color,
				String texto,
				int tamano,
				String alineacionHorizontal,
				String fuente) {
		super(x, y, color);
		this.texto = texto;
		this.tamano = tamano;
		this.alineacionHorizontal = alineacionHorizontal;
		this.fuente = fuente;
	}

    /**
     * Regresa el ancho de la figura.
     * @return el ancho de la figura.
     */
    @Override
    public int getAncho() {
        return texto.length() * tamano >> 1;
    }

    /**
     * Regresa el alto de la figura.
     * @return el alto de la figura.
     */
    @Override
    public int getAlto() {
        return tamano;
    }

    /**
     * Regresa el código SVG de la figura.
     * @return el código SVG de la figura.
     */
    @Override
    public String toSVG() {
		return String.format("<text x='%d' y='%d' fill='%s' font-family='%s' font-size='%d' text-anchor='%s'>%s</text>",
							 x, y, color, fuente, tamano, alineacionHorizontal, texto);
	}

}