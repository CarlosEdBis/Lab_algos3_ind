# Laboratorio 2

### Carlos Bisogno 2210041

## Implementación de lógica de retroceso

Para el uso de bracktraking se creó la función **solSudokuRec**, esta recibe como entrada el tablero junto con la fila y la columna a revisar, retorna un booleano para indicar si la función acabó con éxito o falló. Lo primero es una verificación, si *fil* es nueve quiere decir que ya recorrimos todas las casillas sin fallos, por lo tanto, hallamos la solución. Sino se calcula la próxima celda a visitar.

Si la celda actual no está vacía, no es cero, se pasa a revisar la siguiente celda. En caso de estár vacía se prueban los números del 1 al 9, si alguno de estos valores es una jugada válida se guarda en la celda actual y se llama nuevamente a **solSudokuRec** para hacer pruebas en la siguiente celda, si esa llamada devuelve *true* entonces igualmente para la celda actual se devuelve *true*; pero si nos retorna *false* ese intento, la celda volverá a estar vacía, así hasta encontrar una combinación válidad o acabar todas las opciones y devolver *false*.

## Explicación del intento de poda

Para entender todas las funciones nombradas se debe ver el archivo **SudokuEV**, este es distinto al archivo Sudoku.

Los tiempos fueron medidos con reiterados cambios, pues en un principio el tiempo de ejecución era aproximadamente diez veces mayor al de **Sudoku**, luego de toda la optimización sólo se consiguió alcanzar un tiempo de dos a tres veces por encima del de **Sudoku**, es decir, menos eficiente.

En este caso se intentó realizar una poda haciendo uso de una lista mutable en la matriz. La matriz contiene el tipo de dato Celda, donde se guarda un valor de tipo Int y un lista de tipo mutableSet. Al inicio en la lista, nombrada como opciones, están los números del 1 al 9, que representan las opciones disponibles a probar en la celda en base a las celdas que ya están ocupadas.

Para intentar que funcionara de la forma más eficiente posible, al crear la matriz, inicializada con todos los valores en cero y su lista de opciones completa, del 1 al 9, cada vez que se encontraba un número distinto de cero y se añadía, también se eliminaban estos números de las opciones para las celdas correspondientes, haciendo uso de la función **eliminarOpAdy**. Luego dentro de la función **solSudokuRec**, se llama a **encontrarCelda** que busca al mejor candidato para hacer las pruebas, es decir, el que tuviera menos opciones a probar. Hasta este punto es bastante rasonable y luce más eficiente que probar a fuerza bruta, sin embargo, el costo crece considerablemente dentro del bucle **for** pues cada vez que prueba un número debe llamar a **eliminarOpAdy** para eliminar de las opciones de las otras celdas al número que se acaba de agregar, se necesita recorrer la fila y la columna actual además de la sub-cuadrícula 3x3 en la que se encuentre, y si este camino resulta ser incorrecto hay que llamar a **restaurarOpAdy** para volver a colocar el número como una opción posible, pero en este caso para cada celda se debe verificar si no existe alguna otra celda que tenga incidencia en ella y que provoque que el número no pueda ser una opción.
