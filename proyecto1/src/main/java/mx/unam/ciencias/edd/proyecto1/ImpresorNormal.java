package mx.unam.ciencias.edd.proyecto1;

import mx.unam.ciencias.edd.ArbolRojinegro;

/**
 * Clase para impresores en terminal de líneas de archivos de árboles rojinegros.
 */
public class ImpresorNormal<T extends Comparable<T>> implements Impresor<T>{
    
    /**
     * Imprime las lineas de archivo del árbol rojinegro.
     * @param rbt el árbol rojinegro a imprimir.
     * @param identificador el identificador del archivo.
     */
    @Override
    public void imprimir(ArbolRojinegro<T> rbt, String identificador) {
        for (T v : rbt)
            System.out.println(v);        
    }
    
}
