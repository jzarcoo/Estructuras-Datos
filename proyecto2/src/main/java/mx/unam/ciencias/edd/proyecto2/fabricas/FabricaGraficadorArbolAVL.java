package mx.unam.ciencias.edd.proyecto2.fabricas;

import mx.unam.ciencias.edd.ArbolAVL;
import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.proyecto2.graficadores.GraficadorArbolAVL;
import mx.unam.ciencias.edd.proyecto2.graficadores.GraficadorEstructura;

/**
 * <p>Clase para representar una fábrica para crear objetos GraficadorArbolAVL.</p>
 */
public class FabricaGraficadorArbolAVL extends FabricaGraficadorEstructura {

    /**
     * Crea un objeto GraficadorEstructura a partir de una lista de enteros.
     * @param lista la lista de enteros a ser graficada.
     * @return un objeto GraficadorEstructura para la lista especificada.
     */
    @Override
    public GraficadorEstructura creaGraficador(Lista<Integer> lista) {
        return new GraficadorArbolAVL<Integer>(new ArbolAVL<Integer>(lista));
    }
}
