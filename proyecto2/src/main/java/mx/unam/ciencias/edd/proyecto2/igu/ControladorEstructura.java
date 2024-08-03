package mx.unam.ciencias.edd.proyecto2.igu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.FileInputStream;
import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.proyecto2.Estructura;

/**
 * <p>Clase para el controlador de la aplicacion. El controlador se encarga de obtener la estructura 
 * de datos, instancia de {@link Estructura}, y la lista de enteros ingresada por el usuario, ya sea 
 * por un nombre de archivo o a traves de la entrada estandar.</p>
 * 
 * <p>El formato de entrada debe ser el siguiente:</p>
 * 
 * <ul>
 * <li>Los espacios (incluyendo tabuladores, saltos de linea y cualquier otro caracter no imprimible) 
 * son ignorados excepto como separadores.</li>
 * <li>Si el programa encuentra una almohadilla (el simbolo #), se ignoran todos los siguientes caracteres 
 * hasta el fin de línea.</li>
 * <li>Lo primero que debe encontrar el programa es el nombre de una de las clases concretas permitidas.</li>
 * <li>Despues del nombre de clase deben venir enteros (siempre enteros) que son los elementos de la 
 * estructura.</li>
 * </ul>
 * 
 * <p>Si el formato no respeta lo anterior, el programa termina con un mensaje de error.</p>
 */
public class ControladorEstructura {

    /* Codigo de terminacion por error de uso. */
    private static final int ERROR_USO = 1;
    /* Codigo de terminacion por error de lectura. */
    private static final int ERROR_LECTURA = 2;

    /* Estructura de datos. */
    private Estructura estructura;
    /* Lista de enteros. */
    private Lista<Integer> lista;

    /**
     * Define el estado inicial del controlador.
     */
    public ControladorEstructura() {
        lista = new Lista<Integer>();
        procesa(new BufferedReader(
                    new InputStreamReader(System.in)));
    }

    /**
     * Define el estado inicial del controlador.
     * @param archivo el nombre del archivo de donde se leera la estructura de datos.
     */
    public ControladorEstructura(String archivo) {
        lista = new Lista<Integer>();
        lee(archivo);
    }

    /**
     * Lee el archivo ingresado por el usuario.
     */
    private void lee(String archivo) {
        try {
            BufferedReader in = new BufferedReader(
                                    new InputStreamReader(
                                        new FileInputStream(archivo)));
            procesa(in);
        } catch (IOException ioe) {
            System.err.println("Error de lectura.\n" + ioe.getMessage());
            System.exit(ERROR_LECTURA);
        }
    }

    /**
     * Procesa la entrada del usuario, ya sea por archivo o por la entrada estandar.
     * @param in el lector de la entrada.
     */
    private void procesa(BufferedReader in) {
        try {
            String linea;
            while ((linea = in.readLine()) != null) {
                int cont = 0;
                linea = linea.trim();
                if (linea.isEmpty() || linea.startsWith("#"))
                    continue;
                String[] partes = linea.split("\\s+");
                if (partes.length == 0)
                    continue;
                if (estructura == null)
                    setEstructura(partes[cont++]);
                for (int i = cont; i < partes.length; i++) {
                    try {
                        int n = Integer.parseInt(partes[i]);
                        lista.agrega(n);
                    } catch (NumberFormatException nfe) {
                        System.err.println("Error de casteo.\n" + nfe.getMessage());
                        System.exit(ERROR_USO);
                    }
                }
            }
            in.close();
        } catch (IOException ioe) {
            System.err.println("Error de lectura.\n" + ioe.getMessage());
            System.exit(ERROR_LECTURA);
        }
        verifica();
    }

    /**
     * Válida la lista según los requerimientos de cada estructura.
     * En caso de ser inválidos, termina el programa.
     */
    private void verifica() {
        switch (estructura) {
            case GRAFICA:
                if (lista.getElementos() % 2 != 0) {
                    System.err.println("El número de elementos debe ser par.");
                    System.exit(ERROR_USO);
                }
            default:
                return;
        }
    }

    /**
     * Define la nueva estructura de datos.
     * @param nombre el nombre de la estructura de datos.
     */
    private void setEstructura(String nombre) {
        Estructura e = Estructura.getEstructura(nombre);
        if (e == null) {
            System.err.println("Estructura no soportada.\n" + nombre);
            System.exit(ERROR_USO);
        }
        estructura = e;
    }

    /**
     * Regresa la estructura de datos.
     * @return la estructura de datos.
     */
    public Estructura getEstructura() {
        return estructura;
    }

    /**
     * Regresa la lista de enteros.
     * @return la lista de enteros.
     */
    public Lista<Integer> getLista() {
        return lista;
    }
}