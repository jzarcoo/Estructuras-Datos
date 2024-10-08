package mx.unam.ciencias.edd;

import java.util.Comparator;

/**
 * Clase para ordenar y buscar arreglos genéricos.
 */
public class Arreglos {

    /* Constructor privado para evitar instanciación. */
    private Arreglos() {}

    /**
     * Ordena el arreglo recibido usando QickSort.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo el arreglo a ordenar.
     * @param comparador el comparador para ordenar el arreglo.
     */
    public static <T> void
    quickSort(T[] arreglo, Comparator<T> comparador) {
        if (arreglo.length <= 1)
            return;
        Pila<Integer> pila = new Pila<Integer>();
        pila.mete(0);
        pila.mete(arreglo.length - 1);
        while (!pila.esVacia()) {
            int fin = pila.saca();
            int ini = pila.saca();
            if (fin <= ini)
                continue;
            int i = ini + 1;
            int j = fin;
            while (i < j) 
                if (comparador.compare(arreglo[i], arreglo[ini]) > 0 && comparador.compare(arreglo[j], arreglo[ini]) <= 0)
                    intercambia(arreglo, i++, j--);
                else if (comparador.compare(arreglo[i], arreglo[ini]) <= 0)
                    i++;
                else
                    j--;
            if (comparador.compare(arreglo[i], arreglo[ini]) > 0)
                i--;
            intercambia(arreglo, ini, i);
            pila.mete(ini);
            pila.mete(i - 1);
            pila.mete(i + 1);
            pila.mete(fin);
        }
    }

    /**
     * Ordena el arreglo recibido usando QickSort.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo un arreglo cuyos elementos son comparables.
     */
    public static <T extends Comparable<T>> void
    quickSort(T[] arreglo) {
        quickSort(arreglo, (a, b) -> a.compareTo(b));
    }

    /**
     * Intercambia dos elementos en el arreglo.
     * @param i el índice uno a intercambiar.
     * @param j el indice dos a intercambiar.
     */
    private static <T> void intercambia(T[] arreglo, int i, int j) {
        if (i == j)
            return;
        T t = arreglo[j];
        arreglo[j] = arreglo[i];
        arreglo[i] = t;
    }

    /**
     * Ordena el arreglo recibido usando SelectionSort.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo el arreglo a ordenar.
     * @param comparador el comparador para ordernar el arreglo.
     */
    public static <T> void
	selectionSort(T[] arreglo, Comparator<T> comparador) {
        for (int i = 0; i < arreglo.length - 1; i++) {
            int menor = i;
            for (int j = i + 1; j < arreglo.length; j++)
                if (comparador.compare(arreglo[j], arreglo[menor]) < 0)
                    menor = j;
            intercambia(arreglo, i, menor);
        }
    }
    
    /**
     * Ordena el arreglo recibido usando SelectionSort.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo un arreglo cuyos elementos son comparables.
     */
    public static <T extends Comparable<T>> void
    selectionSort(T[] arreglo) {
        selectionSort(arreglo, (a, b) -> a.compareTo(b));
    }

    /**
     * Hace una búsqueda binaria del elemento en el arreglo. Regresa el índice
     * del elemento en el arreglo, o -1 si no se encuentra.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo el arreglo dónde buscar.
     * @param elemento el elemento a buscar.
     * @param comparador el comparador para hacer la búsqueda.
     * @return el índice del elemento en el arreglo, o -1 si no se encuentra.
     */
    public static <T> int
    busquedaBinaria(T[] arreglo, T elemento, Comparator<T> comparador) {
        int ini = 0;
        int fin = arreglo.length - 1;
        while (ini <= fin) {
            int mitad = ini + ((fin - ini) / 2);
            if (comparador.compare(elemento, arreglo[mitad]) == 0)
                return mitad;
            else if (comparador.compare(elemento, arreglo[mitad]) < 0)
                fin = mitad - 1;
            else
                ini = mitad + 1;
        }
        return -1;
    }
    
    /**
     * Hace una búsqueda binaria del elemento en el arreglo. Regresa el índice
     * del elemento en el arreglo, o -1 si no se encuentra.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo un arreglo cuyos elementos son comparables.
     * @param elemento el elemento a buscar.
     * @return el índice del elemento en el arreglo, o -1 si no se encuentra.
     */
    public static <T extends Comparable<T>> int
    busquedaBinaria(T[] arreglo, T elemento) {
        return busquedaBinaria(arreglo, elemento, (a, b) -> a.compareTo(b));
    }
}
