package mx.unam.ciencias.edd.proyecto2.fabricas;

import mx.unam.ciencias.edd.Grafica;
import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.proyecto2.graficadores.GraficadorEstructura;
import mx.unam.ciencias.edd.proyecto2.graficadores.GraficadorGrafica;

/**
 * <p>Clase para representar una fábrica para crear objetos GraficadorGrafica.</p>
 * 
 * <p>Construye una gráfica donde cada par de elementos es una arista. Si un par de 
 * elementos son iguales, esto representa un vértice desconectado del resto de la gráfica.</p>
 * 
 * <p>No lanza excepciones al conectar vértices, pues otorga una mejor experiencia de usuario.</p>
 */
public class FabricaGraficadorGrafica extends FabricaGraficadorEstructura {

    /**
     * Crea un objeto GraficadorEstructura a partir de una lista de enteros.
     * @param lista la lista de enteros a ser graficada.
     * @return un objeto GraficadorEstructura para la lista especificada.
     */
    @Override
    public GraficadorEstructura creaGraficador(Lista<Integer> lista) {
        Grafica<Integer> grafica = new Grafica<Integer>();
        int i = 0;
        Integer anterior = null;
        for (Integer e : lista) {
            // Agrega vértices.
            try {
                grafica.agrega(e);
            } catch(IllegalArgumentException iae ) {} // Elemento ya existente
            // Guarda el anterior para conectarlos después
            if (i++ % 2 == 0) {
                anterior = e;
                continue;
            }
            // Cada par son conectados.
            try {
                grafica.conecta(anterior, e);
            } catch(IllegalArgumentException iae) {
                // Elementos ya conectados, o bien,
                // Elementos iguales, representan un vértice desconectado del resto de la gráfica.
                continue;
            }
        }
        return new GraficadorGrafica<Integer>(grafica);
    }
}