package mx.unam.ciencias.edd;

import java.util.Iterator;

/**
 * <p>Clase para árboles binarios ordenados. Los árboles son genéricos, pero
 * acotados a la interfaz {@link Comparable}.</p>
 *
 * <p>Un árbol instancia de esta clase siempre cumple que:</p>
 * <ul>
 *   <li>Cualquier elemento en el árbol es mayor o igual que todos sus
 *       descendientes por la izquierda.</li>
 *   <li>Cualquier elemento en el árbol es menor o igual que todos sus
 *       descendientes por la derecha.</li>
 * </ul>
 */
public class ArbolBinarioOrdenado<T extends Comparable<T>>
    extends ArbolBinario<T> {

    /* Clase interna privada para iteradores. */
    private class Iterador implements Iterator<T> {

        /* Pila para recorrer los vértices en DFS in-order. */
        private Pila<Vertice> pila;

        /* Inicializa al iterador. */
        private Iterador() {
	    	pila = new Pila<Vertice>();
	    	Vertice v = raiz;
	    	while (v != null) {
				pila.mete(v);
				v = v.izquierdo;
	    	}
        }

        /* Nos dice si hay un elemento siguiente. */
        @Override public boolean hasNext() {
            return !pila.esVacia();
        }

        /* Regresa el siguiente elemento en orden DFS in-order. */
        @Override public T next() {
	    	Vertice v = pila.saca();
	    	T elemento = v.elemento;
	    	if (v.derecho == null)
				return elemento;
	    	v = v.derecho;
	    	while (v != null) {
				pila.mete(v);
				v = v.izquierdo;
	    	}
	    	return elemento;
        }
    }

    /**
     * El vértice del último elemento agegado. Este vértice sólo se puede
     * garantizar que existe <em>inmediatamente</em> después de haber agregado
     * un elemento al árbol. Si cualquier operación distinta a agregar sobre el
     * árbol se ejecuta después de haber agregado un elemento, el estado de esta
     * variable es indefinido.
     */
    protected Vertice ultimoAgregado;

    /**
     * Constructor sin parámetros. Para no perder el constructor sin parámetros
     * de {@link ArbolBinario}.
     */
    public ArbolBinarioOrdenado() { super(); }

    /**
     * Construye un árbol binario ordenado a partir de una colección. El árbol
     * binario ordenado tiene los mismos elementos que la colección recibida.
     * @param coleccion la colección a partir de la cual creamos el árbol
     *        binario ordenado.
     */
    public ArbolBinarioOrdenado(Coleccion<T> coleccion) {
        super(coleccion);
    }

    /**
     * Agrega un nuevo elemento al árbol. El árbol conserva su orden in-order.
     * @param elemento el elemento a agregar.
     */
    @Override public void agrega(T elemento) {
		if (elemento == null)
			throw new IllegalArgumentException("Elemento inválido.");
		elementos++;
		Vertice nuevo = nuevoVertice(elemento);
		if (raiz == null) 
			raiz = nuevo;
		else
			agrega(raiz, nuevo);
		ultimoAgregado = nuevo;
    }

    /**
     * Agrega en orden el nuevo vértice en el árbol.
     * @param actual el vértice actual.
     * @param nuevo el nuevo vértice a agregar.
     */
    private void agrega(Vertice actual, Vertice nuevo) {
		if (nuevo.elemento.compareTo(actual.elemento) <= 0) 
			if (actual.izquierdo == null) {
				actual.izquierdo = nuevo;
				nuevo.padre = actual;
			} else
				agrega(actual.izquierdo, nuevo);
		else 
			if (actual.derecho == null) {
				actual.derecho = nuevo;
				nuevo.padre = actual;
			} else 
				agrega(actual.derecho, nuevo);
    }

    /**
     * Elimina un elemento. Si el elemento no está en el árbol, no hace nada; si
     * está varias veces, elimina el primero que encuentre (in-order). El árbol
     * conserva su orden in-order.
     * @param elemento el elemento a eliminar.
     */
    @Override public void elimina(T elemento) {
		if (elemento == null)
			return;
		Vertice v = vertice(busca(elemento));
		if (v == null)
			return;
		elementos--;
		if (v.izquierdo != null && v.derecho != null)
			v = intercambiaEliminable(v);
		eliminaVertice(v);
    }

    /**
     * Intercambia el elemento de un vértice con dos hijos distintos de
     * <code>null</code> con el elemento de un descendiente que tenga a lo más
     * un hijo.
     * @param vertice un vértice con dos hijos distintos de <code>null</code>.
     * @return el vértice descendiente con el que vértice recibido se
     *         intercambió. El vértice regresado tiene a lo más un hijo distinto
     *         de <code>null</code>.
     */
    protected Vertice intercambiaEliminable(Vertice vertice) {
		Vertice v = MaximoEnSubArbol(vertice.izquierdo);
		vertice.elemento = v.elemento;
		return v;
    }

    /**
     * Regresa un elemento que es mayor o igual que todos los elementos del
     * subárbol definido por el vértice que recibe como parámetro.
     * @param v el vértice.
     */
    private Vertice MaximoEnSubArbol(Vertice v) {
		while (v.derecho != null)
			v = v.derecho;
		return v;
    }

    /**
     * Elimina un vértice que a lo más tiene un hijo distinto de
     * <code>null</code> subiendo ese hijo (si existe).
     * @param vertice el vértice a eliminar; debe tener a lo más un hijo
     *                distinto de <code>null</code>.
     */
    protected void eliminaVertice(Vertice vertice) {
		Vertice u = (vertice.izquierdo == null) ? vertice.derecho : vertice.izquierdo;
		if (vertice.padre == null)
			raiz = u;
		else
			if (vertice.padre.izquierdo == vertice)
				vertice.padre.izquierdo = u;
			else
				vertice.padre.derecho = u;
		if (u != null)
			u.padre = vertice.padre;
    }

    /**
     * Busca un elemento en el árbol recorriéndolo in-order. Si lo encuentra,
     * regresa el vértice que lo contiene; si no, regresa <code>null</code>.
     * @param elemento el elemento a buscar.
     * @return un vértice que contiene al elemento buscado si lo
     *         encuentra; <code>null</code> en otro caso.
     */
    @Override public VerticeArbolBinario<T> busca(T elemento) {
		Vertice v = raiz;
		while (v != null) {
			int cmp = elemento.compareTo(v.elemento);
			if (cmp == 0)
				return v;
			v = (cmp < 0) ? v.izquierdo : v.derecho;
		}
		return null;
    }

    /**
     * Regresa el vértice que contiene el último elemento agregado al
     * árbol. Este método sólo se puede garantizar que funcione
     * <em>inmediatamente</em> después de haber invocado al método {@link
     * agrega}. Si cualquier operación distinta a agregar sobre el árbol se
     * ejecuta después de haber agregado un elemento, el comportamiento de este
     * método es indefinido.
     * @return el vértice que contiene el último elemento agregado al árbol, si
     *         el método es invocado inmediatamente después de agregar un
     *         elemento al árbol.
     */
    public VerticeArbolBinario<T> getUltimoVerticeAgregado() {
        return ultimoAgregado;
    }

    /**
     * Gira el árbol a la derecha sobre el vértice recibido. Si el vértice no
     * tiene hijo izquierdo, el método no hace nada.
     * @param vertice el vértice sobre el que vamos a girar.
     */
    public void giraDerecha(VerticeArbolBinario<T> vertice) {
		Vertice q = vertice(vertice);
		if (q.izquierdo == null)
			return;
		Vertice p = q.izquierdo;
		Vertice s = p.derecho;
		p.padre = q.padre;
		if (p.padre == null)
			raiz = p;
		else 
			if (q.padre.izquierdo == q)
				q.padre.izquierdo = p;
			else
				q.padre.derecho = p;
		q.izquierdo = s;
		if (s != null)
			s.padre = q;
		p.derecho = q;
		q.padre = p;
    }

    /**
     * Gira el árbol a la izquierda sobre el vértice recibido. Si el vértice no
     * tiene hijo derecho, el método no hace nada.
     * @param vertice el vértice sobre el que vamos a girar.
     */
    public void giraIzquierda(VerticeArbolBinario<T> vertice) {
		Vertice p = vertice(vertice);
		if (p.derecho == null)
			return;
		Vertice q = p.derecho;
		Vertice s = q.izquierdo;
		q.padre = p.padre;
		if (q.padre == null)
			raiz = q;
		else 
			if (p.padre.izquierdo == p)
				p.padre.izquierdo = q;
			else
				p.padre.derecho = q;
		p.derecho = s;
		if (s != null)
			s.padre = p;
		q.izquierdo = p;
		p.padre = q;
    }

    /**
     * Realiza un recorrido DFS <em>pre-order</em> en el árbol, ejecutando la
     * acción recibida en cada elemento del árbol.
     * @param accion la acción a realizar en cada elemento del árbol.
     */
    public void dfsPreOrder(AccionVerticeArbolBinario<T> accion) {
		if (raiz == null)
			return;
		Pila<Vertice> pila = new Pila<Vertice>();
		pila.mete(raiz);
		while (!pila.esVacia()) {
			Vertice v = pila.saca();
			accion.actua(v);
			if (v.derecho != null)
				pila.mete(v.derecho);
			if (v.izquierdo != null)
				pila.mete(v.izquierdo);
		}
    }

    /**
     * Realiza un recorrido DFS <em>in-order</em> en el árbol, ejecutando la
     * acción recibida en cada elemento del árbol.
     * @param accion la acción a realizar en cada elemento del árbol.
     */
    public void dfsInOrder(AccionVerticeArbolBinario<T> accion) {
		Pila<Vertice> pila = new Pila<Vertice>();
		Vertice vertice = raiz;
		while (vertice != null) {
			pila.mete(vertice);
			vertice = vertice.izquierdo;
		}
		while (!pila.esVacia()) {
			Vertice v = pila.saca();
			accion.actua(v);
			v = v.derecho;
			while (v != null) {
				pila.mete(v);
				v = v.izquierdo;
			}
		}
    }

    /**
     * Realiza un recorrido DFS <em>post-order</em> en el árbol, ejecutando la
     * acción recibida en cada elemento del árbol.
     * @param accion la acción a realizar en cada elemento del árbol.
     */
    public void dfsPostOrder(AccionVerticeArbolBinario<T> accion) {
		if (raiz == null)
			return;
		Pila<Vertice> pila = new Pila<Vertice>();
		pila.mete(raiz);
		pila.mete(raiz);
		while (!pila.esVacia()) {
			Vertice v = pila.saca();
			Vertice debajo = (pila.esVacia()) ? null : pila.mira();
			if (!v.equals(debajo)) {
				accion.actua(v);
				continue;
			}
			if (v.derecho != null) {
				pila.mete(v.derecho);
				pila.mete(v.derecho);
			}
			if (v.izquierdo != null) {
				pila.mete(v.izquierdo);
				pila.mete(v.izquierdo);
			}
		}
    }
    
    /**
     * Regresa un iterador para iterar el árbol. El árbol se itera en orden.
     * @return un iterador para iterar el árbol.
     */
    @Override public Iterator<T> iterator() {
        return new Iterador();
    }
}
