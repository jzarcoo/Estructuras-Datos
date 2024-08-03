package mx.unam.ciencias.edd.proyecto2.fabricas;

import mx.unam.ciencias.edd.proyecto2.Estructura;

/**
 * <p>Una fábrica para crear fábricas concretas para diferentes estructuras de datos.</p>
 */
public class FabricaFabricas {

    /* Constructor privado para evitar instanciación. */
    private FabricaFabricas() {}

    /**
     * Crea una fábrica concreta para un tipo específico de estructura de datos.
     * @param estructura la estructura de datos para la que se desea crear la fábrica.
     * @return una instancia de la fábrica concreta correspondiente al tipo de estructura especificado.
     */
    public static FabricaGraficadorEstructura creaFabricaGraficadorEstructura(Estructura estructura) {
        switch (estructura) {
            case LISTA:
                return new FabricaGraficadorLista();
            case PILA:
                return new FabricaGraficadorPila();
            case COLA:
               return new FabricaGraficadorCola();
            case ARBOL_BINARIO_COMPLETO:
                return new FabricaGraficadorArbolBinarioCompleto();
            case ARBOL_BINARIO_ORDENADO:
                return new FabricaGraficadorArbolBinarioOrdenado();
            case ARBOL_ROJINEGRO:
                return new FabricaGraficadorArbolRojinegro();
            case ARBOL_AVL:
                return new FabricaGraficadorArbolAVL();
            case GRAFICA:
                return new FabricaGraficadorGrafica();
            case MONTICULO_MINIMO:
                return new FabricaGraficadorMonticuloMinimo();
            default:
                return null;
        }
    }
}