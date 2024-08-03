package mx.unam.ciencias.edd.proyecto2.fabricas;

import mx.unam.ciencias.edd.ComparableIndexable;
import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.MonticuloMinimo;
import mx.unam.ciencias.edd.proyecto2.graficadores.GraficadorEstructura;
import mx.unam.ciencias.edd.proyecto2.graficadores.GraficadorMonticuloMinimo;

/**
 * <p>Clase para representar una fábrica para crear objetos GraficadorMonticuloMinimo.</p>
 * 
 * <p>Dado que el tipo Integer no implementa la intefaz ComparableIndexable, le creamos un adaptador.</p>
 */
public class FabricaGraficadorMonticuloMinimo extends FabricaGraficadorEstructura {

    /* Clase privada para adaptadores. */
    private class Adaptador<T extends Comparable<T>>
        implements ComparableIndexable<Adaptador<T>> {

        /* El elemento. */
        private T elemento;
        /* El índice. */
        private int indice;

        /* Crea un nuevo comparable indexable. */
        public Adaptador(T elemento) {
            this.elemento = elemento;
            indice = -1;
        }

        /* Regresa el índice. */
        @Override public int getIndice() {
            return indice;
        }

        /* Define el índice. */
        @Override public void setIndice(int indice) {
            this.indice = indice;
        }

        /* Compara un adaptador con otro. */
        @Override public int compareTo(Adaptador<T> adaptador) {
            return elemento.compareTo(adaptador.elemento);
        }

        /**
         * Nos dice si el adaptador es igual al objeto recibido.
         * @param objeto el objeto con el que queremos comparar el adaptador.
         * @return <code>true</code> si el objeto recibido es un adaptador
         *         igual al que llama el método; <code>false</code> en otro caso.
         */
        @Override public boolean equals(Object objeto) {
            if (objeto == null || getClass() != objeto.getClass())
                return false;
            @SuppressWarnings("unchecked") Adaptador<T> valorIndexable =
                (Adaptador<T>)objeto;
            return elemento.equals(valorIndexable.elemento);
        }
    
        /**
         * Regresa una representación en cadena del adaptador.
         * @return una representación en cadena del adaptador.
         */
        @Override public String toString() {
            return elemento.toString();
        }
    }

    /**
     * Crea un objeto GraficadorEstructura a partir de una lista de enteros.
     * @param lista la lista de enteros a ser graficada.
     * @return un objeto GraficadorEstructura para la lista especificada.
     */
    @Override
    public GraficadorEstructura creaGraficador(Lista<Integer> lista) {
        Lista<Adaptador<Integer>> adaptadores = new Lista<Adaptador<Integer>>();
        lista.forEach(e -> adaptadores.agrega(new Adaptador<Integer>(e)));
        return new GraficadorMonticuloMinimo<Adaptador<Integer>>(new MonticuloMinimo<Adaptador<Integer>>(adaptadores));
    }
}