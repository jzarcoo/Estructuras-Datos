package mx.unam.ciencias.edd.proyecto2.fabricas;

import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.proyecto2.graficadores.GraficadorEstructura;

/**
 * <p>Clase para representar una fábrica para crear objetos GraficadorEstructura.</p>
 * 
 * <p>Las implementaciones concretas de esta clase deben proporcionar su propia lógica para 
 * crear un objeto GraficadorEstructura específico para la lista de enteros dada.</p>
 * 
 * <p>Cada fábrica supone que recibe una lista de enteros para trabajar, esto con el fin de manejar mejor los tipos,
 * por ejemplo, al construir una gráfica o, verificar que el tipo extienda la interfaz ComparableIndexable.</p>
 */
public abstract class FabricaGraficadorEstructura {

    /**
     * Crea un objeto GraficadorEstructura a partir de una lista de enteros.
     * @param lista La lista de enteros a ser graficada.
     * @return un objeto GraficadorEstructura para la lista especificada.
     */
    public GraficadorEstructura crea(Lista<Integer> lista) {
        return creaGraficador(lista);
    }

    /**
     * Crea un objeto GraficadorEstructura a partir de una lista de enteros.
     * @param lista la lista de enteros a ser graficada.
     * @return un objeto GraficadorEstructura para la lista especificada.
     */
    public abstract GraficadorEstructura creaGraficador(Lista<Integer> lista);
}
