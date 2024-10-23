# Graficador de estructuras de datos

Este proyecto implementa un visualizador de estructuras de datos, generando gráficos en formato **SVG** (Scalable Vector Graphics) 
para representar de manera clara y visualmente agradable las siguientes estructuras


- Árbol AVL
- Árbol Binario Completo
- Árbol Binario Ordenado
- Árbol Rojinegro
- Cola
- Gráfica
- Lista
- Monticulo Mínimo
- Pila

El programa procesa el archivo de entrada en tiempo **O(n)**.

## Características
  
El programa escribe su salida en la salida estándar, y recibe su entrada a
través de un nombre de archivo o de la entrada estándar (si no se especifica
ningún nombre de archivo en la línea de comandos). El formato del archivo es el
siguiente:

- Los espacios (incluyendo tabuladores, saltos de línea y cualquier otro
carácter no imprimible) son ignorados excepto como separadores.
- Si el programa encuentra una almohadilla (el símbolo #), se ignoran todos los
siguientes caracteres hasta el fin de línea.
- Lo primero que debe encontrar el programa es el nombre de una de las clases
concretas permitidas.
- Después del nombre de clase deben venir enteros (siempre enteros) que son los
elementos de la estructura.
- En el caso de las gráficas, el número de elementos debe ser par, y cada par
de elementos es una arista. Si un par de elementos son iguales, esto
representa un vértice desconectado del resto de la gráfica.

Por ejemplo, el siguiente archivo:

```
ArbolRojinegro 1 2 3 4 5 6 7 8 9 10 11 12 13 14 5
```

describe el mismo árbol rojinegro que el siguiente archivo:

```
# Clase:
        ArbolRojinegro
    # Elementos:
    1 2 3 4
# Más elementos
5 6 7 8
                        # Todavía MÁS elementos
                        9 10 11 12
    # Los últimos elementos
    13 14 5
```

## Uso

El programa está desarrollado en Java y utiliza **Maven** para la gestión de dependencias y la compilación.

```sh
$ mvn compile # compila el código
$ mvn test    # corre las pruebas unitarias (opcional)
$ mvn install # genera el archivo proyecto1.jar en el subdirectorio target
```

Se debe correr el proyecto de la siguiente manera:

```sh
java -jar target/proyecto2.jar archivo.txt 
```

O bien,


```sh
java -jar target/proyecto2.jar archivo.txt > archivo.svg
```
