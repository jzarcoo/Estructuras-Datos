#!/bin/bash

generar_archivo() {
    tipo=$1
    cantidad=$2
    numero=$3
    nombre="${tipo}${numero}.txt"
    echo "$tipo" > "$nombre"
    for ((j = 0; j < cantidad; j++)); do
        echo $((RANDOM % 100 + 1)) >> "$nombre"
    done
}

num_archivos_por_tipo=10

for tipo in "Lista" "Pila" "Cola" "ArbolBinarioCompleto" "ArbolBinarioOrdenado" "ArbolRojinegro" "ArbolAVL" "Grafica" "MonticuloMinimo"; do
    for ((i = 0; i < num_archivos_por_tipo; i++)); do
        generar_archivo "$tipo" $((RANDOM % 100 + 1)) $i
    done
done

for archivo in *.txt; do
    java -jar target/proyecto2.jar "$archivo" > "${archivo%.txt}.svg"
done
