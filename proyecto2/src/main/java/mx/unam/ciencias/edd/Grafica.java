package mx.unam.ciencias.edd;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Clase para gráficas. Una gráfica es un conjunto de vértices y aristas, tales
 * que las aristas son un subconjunto del producto cruz de los vértices.
 */
public class Grafica<T> implements Coleccion<T> {

    /* Clase interna privada para iteradores. */
    private class Iterador implements Iterator<T> {

        /* Iterador auxiliar. */
        private Iterator<Vertice> iterador;

        /* Construye un nuevo iterador, auxiliándose de la lista de vértices. */
        public Iterador() {
	    iterador = vertices.iterator(); 
        }

        /* Nos dice si hay un siguiente elemento. */
        @Override public boolean hasNext() {
            return iterador.hasNext();
        }

        /* Regresa el siguiente elemento. */
        @Override public T next() {
            return iterador.next().elemento;
        }
    }

    /* Clase interna privada para vértices. */
    private class Vertice implements VerticeGrafica<T>,
                          ComparableIndexable<Vertice> {

        /* El elemento del vértice. */
        private T elemento;
        /* El color del vértice. */
        private Color color;
        /* La distancia del vértice. */
        private double distancia;
        /* El índice del vértice. */
        private int indice;
        /* La lista de vecinos del vértice. */
        private Lista<Vecino> vecinos;

        /* Crea un nuevo vértice a partir de un elemento. */
        public Vertice(T elemento) {
            this.elemento = elemento;
            color = Color.NINGUNO;
            vecinos = new Lista<Vecino>();
        }

        /* Regresa el elemento del vértice. */
        @Override public T get() {
            return elemento;
        }

        /* Regresa el grado del vértice. */
        @Override public int getGrado() {
            return vecinos.getLongitud();
        }

        /* Regresa el color del vértice. */
        @Override public Color getColor() {
            return color;
        }

        /* Regresa un iterable para los vecinos. */
        @Override public Iterable<? extends VerticeGrafica<T>> vecinos() {
            return vecinos;
        }

        /* Define el índice del vértice. */
        @Override public void setIndice(int indice) {
            this.indice = indice;
        }

        /* Regresa el índice del vértice. */
        @Override public int getIndice() {
            return indice;
	}

        /* Compara dos vértices por distancia. */
        @Override public int compareTo(Vertice vertice) {
	    return Double.compare(distancia, vertice.distancia);
        }
    }

    /* Clase interna privada para vértices vecinos. */
    private class Vecino implements VerticeGrafica<T> {

        /* El vértice vecino. */
        public Vertice vecino;
        /* El peso de la arista conectando al vértice con su vértice vecino. */
        public double peso;

        /* Construye un nuevo vecino con el vértice recibido como vecino y el
         * peso especificado. */
        public Vecino(Vertice vecino, double peso) {
            this.vecino = vecino;
            this.peso = peso;
        }

        /* Regresa el elemento del vecino. */
        @Override public T get() {
            return vecino.elemento;
        }

        /* Regresa el grado del vecino. */
        @Override public int getGrado() {
            return vecino.vecinos.getLongitud();
        }

        /* Regresa el color del vecino. */
        @Override public Color getColor() {
            return vecino.color;
        }

        /* Regresa un iterable para los vecinos del vecino. */
        @Override public Iterable<? extends VerticeGrafica<T>> vecinos() {
            return vecino.vecinos;
        }
    }

    /* Interface para poder usar lambdas al buscar el elemento que sigue al
     * reconstruir un camino. */
    @FunctionalInterface
    private interface BuscadorCamino<T> {
        /* Regresa true si el vértice se sigue del vecino. */
        public boolean seSiguen(Grafica<T>.Vertice v, Grafica<T>.Vecino a);
    }

    /* Vértices. */
    private Lista<Vertice> vertices;
    /* Número de aristas. */
    private int aristas;

    /**
     * Constructor único.
     */
    public Grafica() {
        vertices = new Lista<Vertice>();
    }

    /**
     * Regresa el número de elementos en la gráfica. El número de elementos es
     * igual al número de vértices.
     * @return el número de elementos en la gráfica.
     */
    @Override public int getElementos() {
        return vertices.getLongitud();
    }

    /**
     * Regresa el número de aristas.
     * @return el número de aristas.
     */
    public int getAristas() {
        return aristas;
    }

    /**
     * Agrega un nuevo elemento a la gráfica.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si el elemento ya había sido agregado a
     *         la gráfica.
     */
    @Override public void agrega(T elemento) {
        if (elemento == null || contiene(elemento))
            throw new IllegalArgumentException("Elemento invalido.");
        vertices.agrega(new Vertice(elemento));
    }

    /**
     * Conecta dos elementos de la gráfica. Los elementos deben estar en la
     * gráfica. El peso de la arista que conecte a los elementos será 1.
     * @param a el primer elemento a conectar.
     * @param b el segundo elemento a conectar.
     * @throws NoSuchElementException si a o b no son elementos de la gráfica.
     * @throws IllegalArgumentException si a o b ya están conectados, o si a es
     *         igual a b.
     */
    public void conecta(T a, T b) {
        conecta(a, b, 1);
    }

    /**
     * Convierte el vértice (visto como instancia de {@link
     * VerticeGrafica}) en vértice (visto como instancia de {@link
     * Vertice}). Método auxiliar para hacer esta audición en un único lugar.
     * @param vertice el vértice de la interfaz vértice gráfica que queremos como vértice.
     * @return el vértice recibido visto como vértice.
     * @throws IllegalArgumentException si el vértice no es válido.
     */
    private Vertice vertice(VerticeGrafica<T> vertice) {
        if (vertice.getClass() != Vertice.class)
            throw new IllegalArgumentException("Vértice invalido.");
        return (Vertice) vertice;
    }

    /**
     * Conecta dos elementos de la gráfica. Los elementos deben estar en la
     * gráfica.
     * @param a el primer elemento a conectar.
     * @param b el segundo elemento a conectar.
     * @param peso el peso de la nueva vecino.
     * @throws NoSuchElementException si a o b no son elementos de la gráfica.
     * @throws IllegalArgumentException si a o b ya están conectados, si a es
     *         igual a b, o si el peso es no positivo.
     */
    public void conecta(T a, T b, double peso) {
        if (a.equals(b) || peso <= 0)
            throw new IllegalArgumentException("Argumentos inválidos.");
        Vertice A = vertice(vertice(a));
        Vertice B = vertice(vertice(b));
	if (sonVecinos(A, B))
	    throw new IllegalArgumentException("Elementos ya conectados.");
        A.vecinos.agrega(new Vecino(B, peso));
        B.vecinos.agrega(new Vecino(A, peso));
        aristas++;
    }

    /**
     * Desconecta dos elementos de la gráfica. Los elementos deben estar en la
     * gráfica y estar conectados entre ellos.
     * @param a el primer elemento a desconectar.
     * @param b el segundo elemento a desconectar.
     * @throws NoSuchElementException si a o b no son elementos de la gráfica.
     * @throws IllegalArgumentException si a o b no están conectados.
     */
    public void desconecta(T a, T b) {
	if (a.equals(b))
            throw new IllegalArgumentException("Elementos invalidos.");
	desconecta(vertice(vertice(a)), vertice(vertice(b)));
    }

    /**
     * Desconecta dos vértices de la gráfica. Los vértices deben estar en la
     * gráfica y estar conectados entre ellos.
     * @param a el primer vértice a desconectar.
     * @param b el segundo vértice a desconectar.
     * @throws NoSuchElementException si a o b no son vértices de la gráfica.
     * @throws IllegalArgumentException si a o b no están conectados.
     */
    private void desconecta(Vertice a, Vertice b) {
        a.vecinos.elimina(getVecinoDeVertice(b, a));
        b.vecinos.elimina(getVecinoDeVertice(a, b));
        aristas--;
    }

    /**
     * Nos dice si el elemento está contenido en la gráfica.
     * @return <code>true</code> si el elemento está contenido en la gráfica,
     *         <code>false</code> en otro caso.
     */
    @Override public boolean contiene(T elemento) {
        for (Vertice vertice : vertices)
            if (vertice.elemento.equals(elemento))
                return true;
        return false;
    }

    /**
     * Elimina un elemento de la gráfica. El elemento tiene que estar contenido
     * en la gráfica.
     * @param elemento el elemento a eliminar.
     * @throws NoSuchElementException si el elemento no está contenido en la
     *         gráfica.
     */
    @Override public void elimina(T elemento) {
        Vertice vertice = vertice(vertice(elemento));
        vertice.vecinos.forEach(v -> desconecta(vertice, v.vecino));
        vertices.elimina(vertice);
    }

    /**
     * Nos dice si dos elementos de la gráfica están conectados. Los elementos
     * deben estar en la gráfica.
     * @param a el primer elemento.
     * @param b el segundo elemento.
     * @return <code>true</code> si a y b son vecinos, <code>false</code> en otro caso.
     * @throws NoSuchElementException si a o b no son elementos de la gráfica.
     */
    public boolean sonVecinos(T a, T b) {
        return sonVecinos(vertice(vertice(a)), vertice(vertice(b)));
    }

    /**
     * Nos dice si dos vertices de la gráfica están conectados. Los vértices
     * deben estar en la gráfica.
     * @param a el primer vertice.
     * @param b el segundo vertice.
     * @return <code>true</code> si a y b son vecinos, <code>false</code> en otro caso.
     */
    private boolean sonVecinos(Vertice a, Vertice b) {
	try {
	    return a.vecinos.contiene(getVecinoDeVertice(b, a)) &&
		b.vecinos.contiene(getVecinoDeVertice(a, b));
	} catch (IllegalArgumentException iae) {
	    return false;
	}
    }

    /**
     * Regresa el vecino (visto como instancia de {@link
     * Vecino}) correspondiente del vertice dado.
     * @param vecino el vecino a buscar.
     * @param vertice el vértice sobre el que hay que buscar.
     * @throws IllegalArgumentException si a o b no están conectados.
     */
    private Vecino getVecinoDeVertice(Vertice vecino, Vertice vertice) {
	for (Vecino v : vertice.vecinos)
	    if (v.vecino.equals(vecino))
		return v;
	throw new IllegalArgumentException("Vértices no conectados.");
    }

    /**
     * Regresa el peso de la arista que comparten los vértices que contienen a
     * los elementos recibidos.
     * @param a el primer elemento.
     * @param b el segundo elemento.
     * @return el peso de la arista que comparten los vértices que contienen a
     *         los elementos recibidos.
     * @throws NoSuchElementException si a o b no son elementos de la gráfica.
     * @throws IllegalArgumentException si a o b no están conectados.
     */
    public double getPeso(T a, T b) {
	return getVecinoDeVertice(vertice(vertice(a)), vertice(vertice(b))).peso;
    }

    /**
     * Define el peso de la arista que comparten los vértices que contienen a
     * los elementos recibidos.
     * @param a el primer elemento.
     * @param b el segundo elemento.
     * @param peso el nuevo peso de la arista que comparten los vértices que
     *        contienen a los elementos recibidos.
     * @throws NoSuchElementException si a o b no son elementos de la gráfica.
     * @throws IllegalArgumentException si a o b no están conectados, o si peso
     *         es menor o igual que cero.
     */
    public void setPeso(T a, T b, double peso) {
	if (peso <= 0)
	    throw new IllegalArgumentException("Peso inválido.");
	Vertice A = vertice(vertice(a));
	Vertice B = vertice(vertice(b));
	getVecinoDeVertice(A, B).peso = peso;
	getVecinoDeVertice(B, A).peso = peso;
    }

    /**
     * Regresa el vértice correspondiente el elemento recibido.
     * @param elemento el elemento del que queremos el vértice.
     * @throws NoSuchElementException si elemento no es elemento de la gráfica.
     * @return el vértice correspondiente el elemento recibido.
     */
    public VerticeGrafica<T> vertice(T elemento) {
        if (elemento == null)
            throw new NoSuchElementException("Elemento nulo.");
        for (Vertice vertice : vertices)
            if (vertice.elemento.equals(elemento))
                return vertice;
        throw new NoSuchElementException("Elemento inexistente.");
    }

    /**
     * Define el color del vértice recibido.
     * @param vertice el vértice al que queremos definirle el color.
     * @param color el nuevo color del vértice.
     * @throws IllegalArgumentException si el vértice no es válido.
     */
    public void setColor(VerticeGrafica<T> vertice, Color color) {
        if (vertice == null ||
            (vertice.getClass() != Vertice.class &&
             vertice.getClass() != Vecino.class)) {
            throw new IllegalArgumentException("Vértice inválido");
        }
        if (vertice.getClass() == Vertice.class) {
            Vertice v = (Vertice) vertice;
            v.color = color;
            return;
        }
        Vecino v = (Vecino) vertice;
        v.vecino.color = color;
    }

    /**
     * Nos dice si la gráfica es conexa.
     * @return <code>true</code> si la gráfica es conexa, <code>false</code> en
     *         otro caso.
     */
    public boolean esConexa() {
        if (vertices == null || vertices.getLongitud() <= 1)
            return true;
        recorre(vertices.getPrimero().elemento, v -> {}, new Cola<Vertice>());
        for (Vertice v : vertices)
            if (v.color == Color.ROJO)
                return false;
        return true;
    }

    /**
     * Realiza la acción recibida en cada uno de los vértices de la gráfica, en
     * el orden en que fueron agregados.
     * @param accion la acción a realizar.
     */
    public void paraCadaVertice(AccionVerticeGrafica<T> accion) {
        vertices.forEach(v -> accion.actua(v));
    }

    /**
     * Realiza la acción recibida en todos los vértices de la gráfica, en el
     * orden determinado por BFS, comenzando por el vértice correspondiente al
     * elemento recibido. Al terminar el método, todos los vértices tendrán
     * color {@link Color#NINGUNO}.
     * @param elemento el elemento sobre cuyo vértice queremos comenzar el
     *        recorrido.
     * @param accion la acción a realizar.
     * @throws NoSuchElementException si el elemento no está en la gráfica.
     */
    public void bfs(T elemento, AccionVerticeGrafica<T> accion) {
        recorre(elemento, accion, new Cola<Vertice>());
        paraCadaVertice(v -> setColor(v, Color.NINGUNO));
    }

    /**
     * Realiza la acción recibida en todos los vértices de la gráfica, en el
     * orden determinado por DFS, comenzando por el vértice correspondiente al
     * elemento recibido. Al terminar el método, todos los vértices tendrán
     * color {@link Color#NINGUNO}.
     * @param elemento el elemento sobre cuyo vértice queremos comenzar el
     *        recorrido.
     * @param accion la acción a realizar.
     * @throws NoSuchElementException si el elemento no está en la gráfica.
     */
    public void dfs(T elemento, AccionVerticeGrafica<T> accion) {
        recorre(elemento, accion, new Pila<Vertice>());
        paraCadaVertice(v -> setColor(v, Color.NINGUNO));
    }

    /**
     * Realiza la acción recibida en todos los vértices de la gráfica, en el orden 
     * determinado por BFS o DFS, según la estructura dada, comenzando por el vértice 
     * correspondiente al elemento recibido.
     * @param elemento el elemento sobre cuyo vértice queremos comenzar el
     *        recorrido.
     * @param accion la acción a realizar.
     * @param meteSaca la estructura de datos que se utilizará para recorrer la gráfica.
     * @throws NoSuchElementException si el elemento no está en la gráfica.
     */
    private void recorre(T elemento, AccionVerticeGrafica<T> accion, MeteSaca<Vertice> meteSaca) {
        Vertice vertice = vertice(vertice(elemento));
        paraCadaVertice(v -> setColor(v, Color.ROJO));
        vertice.color = Color.NEGRO;
        meteSaca.mete(vertice);
        while(!meteSaca.esVacia()) {
            Vertice v = meteSaca.saca();
            accion.actua(v);
            for (Vecino vec : v.vecinos) {
                if (vec.vecino.color == Color.ROJO) {
                    vec.vecino.color = Color.NEGRO;
                    meteSaca.mete(vec.vecino);
                }
            }
        }
    }

    /**
     * Nos dice si la gráfica es vacía.
     * @return <code>true</code> si la gráfica es vacía, <code>false</code> en
     *         otro caso.
     */
    @Override public boolean esVacia() {
        return vertices.esVacia();
    }

    /**
     * Limpia la gráfica de vértices y aristas, dejándola vacía.
     */
    @Override public void limpia() {
        vertices.limpia();
        aristas = 0;
    }

    /**
     * Regresa una representación en cadena de la gráfica.
     * @return una representación en cadena de la gráfica.
     */
    @Override public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("{");
        for (Vertice vertice : vertices)
            sb.append(vertice.elemento + ", ");
        sb.append("}, {");
        Lista<T> visitados = new Lista<T>();
        for (Vertice vertice : vertices) {
            for (Vecino v : vertice.vecinos) 
                if (!visitados.contiene(v.vecino.elemento))
                    sb.append("(" + vertice.elemento + ", " + v.vecino.elemento + "), ");
            visitados.agrega(vertice.elemento);
        }
        return sb.append("}").toString();
    }

    /**
     * Nos dice si la gráfica es igual al objeto recibido.
     * @param objeto el objeto con el que hay que comparar.
     * @return <code>true</code> si la gráfica es igual al objeto recibido;
     *         <code>false</code> en otro caso.
     */
    @Override public boolean equals(Object objeto) {
        if (objeto == null || getClass() != objeto.getClass())
            return false;
        @SuppressWarnings("unchecked") Grafica<T> grafica = (Grafica<T>)objeto;
        if (aristas != grafica.getAristas() || getElementos() != grafica.getElementos())
            return false;
        for (Vertice vertice : vertices) {
            if (!grafica.contiene(vertice.elemento))
                return false;
            for (Vecino v : vertice.vecinos)
                if (!grafica.sonVecinos(vertice.elemento, v.vecino.elemento))
                    return false;
        }
        return true;
    }

    /**
     * Regresa un iterador para iterar la gráfica. La gráfica se itera en el
     * orden en que fueron agregados sus elementos.
     * @return un iterador para iterar la gráfica.
     */
    @Override public Iterator<T> iterator() {
        return new Iterador();
    }

    /**
     * Calcula una trayectoria de distancia mínima entre dos vértices.
     * @param origen el vértice de origen.
     * @param destino el vértice de destino.
     * @return Una lista con vértices de la gráfica, tal que forman una
     *         trayectoria de distancia mínima entre los vértices <code>a</code> y
     *         <code>b</code>. Si los elementos se encuentran en componentes conexos
     *         distintos, el algoritmo regresa una lista vacía.
     * @throws NoSuchElementException si alguno de los dos elementos no está en
     *         la gráfica.
     */
    public Lista<VerticeGrafica<T>> trayectoriaMinima(T origen, T destino) {
	Vertice s = vertice(vertice(origen));
	Vertice t = vertice(vertice(destino));
	if (s.equals(t)) {
	    Lista<VerticeGrafica<T>> trayectoria = new Lista<VerticeGrafica<T>>();
	    trayectoria.agrega(s);
	    return trayectoria;
	}
	for (Vertice vertice : vertices)
	    vertice.distancia = Double.POSITIVE_INFINITY;
	s.distancia = 0;
	Cola<Vertice> q = new Cola<Vertice>();
	q.mete(s);
	while(!q.esVacia()) {
	    Vertice u = q.saca();
	    for (Vecino v : u.vecinos) 
		if (v.vecino.distancia == Double.POSITIVE_INFINITY) {
		    v.vecino.distancia = u.distancia + 1;
		    q.mete(v.vecino);
		}
	}
	return reconstruyeTrayectoria(t, (u, v) -> v.vecino.distancia == (u.distancia - 1));
    }

    /**
     * Calcula la ruta de peso mínimo entre el elemento de origen y el elemento
     * de destino.
     * @param origen el vértice origen.
     * @param destino el vértice destino.
     * @return una trayectoria de peso mínimo entre el vértice <code>origen</code> y
     *         el vértice <code>destino</code>. Si los vértices están en componentes
     *         conexas distintas, regresa una lista vacía.
     * @throws NoSuchElementException si alguno de los dos elementos no está en
     *         la gráfica.
     */
    public Lista<VerticeGrafica<T>> dijkstra(T origen, T destino) {
	Vertice s = vertice(vertice(origen));
	Vertice t = vertice(vertice(destino));
	if (s.equals(t)) {
	    Lista<VerticeGrafica<T>> trayectoria = new Lista<VerticeGrafica<T>>();
	    trayectoria.agrega(s);
	    return trayectoria;
	}
	for (Vertice vertice : vertices)
	    vertice.distancia = Double.POSITIVE_INFINITY;
	s.distancia = 0;
	int n = vertices.getLongitud();
	int N = ((n * (n-1)) >> 1) - n;
	MonticuloDijkstra<Vertice> monticulo = (aristas > N) ? new MonticuloArreglo<Vertice>(vertices) : new MonticuloMinimo<Vertice>(vertices);
	while(!monticulo.esVacia()) {
	    Vertice u = monticulo.elimina();
	    for (Vecino v : u.vecinos)
		if ((u.distancia + v.peso) < v.vecino.distancia) {
		    v.vecino.distancia = u.distancia + v.peso;
		    monticulo.reordena(v.vecino);
		}
	}
	return reconstruyeTrayectoria(t, (u, v) -> (v.vecino.distancia + v.peso) == u.distancia);
    }

    /**
     * Recontruye la trayectoria a partir del buscador otorgado.
     * @param destino el vértice destino.
     * @param buscador la condiciń para reconstruir el camino.
     */
    private Lista<VerticeGrafica<T>> reconstruyeTrayectoria(Vertice destino, BuscadorCamino<T> buscador){
        Lista<VerticeGrafica<T>> trayectoria = new Lista<VerticeGrafica<T>>();
	if (destino.distancia == Double.POSITIVE_INFINITY)
	    return trayectoria;
	trayectoria.agregaInicio(destino);
	Vertice t = destino;
	while(t.distancia != 0)
	    for (Vecino v : t.vecinos)
		if (buscador.seSiguen(t, v)) {
		    trayectoria.agregaInicio(v.vecino);
		    t = v.vecino;
		    break;
		}
	return trayectoria;
    }
}
