package mx.unam.ciencias.edd.proyecto2;

import mx.unam.ciencias.edd.proyecto2.igu.ControladorEstructura;
import mx.unam.ciencias.edd.proyecto2.fabricas.FabricaFabricas;
import mx.unam.ciencias.edd.proyecto2.fabricas.FabricaGraficadorEstructura;
import mx.unam.ciencias.edd.proyecto2.graficadores.GraficadorEstructura;

/**
 * <p>Proyecto 2: Estructuras de datos.</p>
 */
public class Proyecto2 {
    
    /* Codigo de terminacion por error de uso. */
    private static final int ERROR_USO = 1;
    
    /* Imprime en pantalla como debe usarse el programa y lo termina. */
    private static void uso() {
		System.out.println("Uso: java -jar target/proyecto2.jar [<archivo>] \n");
		System.exit(ERROR_USO);
    }

    /**
     * Metodo principal.
     * @param args argumentos de la linea de comandos.
     */
    public static void main(String[] args) {
        if (args.length > 1)
            uso();
        ControladorEstructura controlador;
        if (args.length == 1)
            controlador = new ControladorEstructura(args[0]);
        else 
            controlador = new ControladorEstructura();
        FabricaGraficadorEstructura fabrica = FabricaFabricas.creaFabricaGraficadorEstructura(controlador.getEstructura());
        GraficadorEstructura graficador = fabrica.crea(controlador.getLista());
        graficador.grafica();
    }
}
