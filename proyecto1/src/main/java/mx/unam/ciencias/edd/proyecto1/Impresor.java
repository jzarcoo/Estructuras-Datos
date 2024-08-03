package mx.unam.ciencias.edd.proyecto1;

import mx.unam.ciencias.edd.ArbolRojinegro;

/**
 * Interfaz para impresores de líneas de archivos de árboles rojinegros.
 */
public interface Impresor<T extends Comparable<T>> {
    
    /**
     * Imprime las lineas de archivo del árbol rojinegro.
     * @param rbt el árbol rojinegro a imprimir.
     * @param identificador el identificador del archivo.
     */
    public void imprimir(ArbolRojinegro<T> rbt, String identificador);
}
