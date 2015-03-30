Ejecución del programa

Para ejecutar por consola se deberá utilizar el comando:

java -jar gridlock.jar [STRATEGY] [PATH] [HEURISTIC]

Descripción:
STRATEGY: Estrategia a utilizar (DFS, BFS, IDDFS, GREEDY, ASTAR). Es obligatorio.

PATH: Path al archivo contenedor del tablero. Es obligatorio.

HEURISTIC: Debe ser ingresado en caso de elegir la estrategia GREEDY o ASTAR. Se debe seleccionar h1 o h2.

En caso de querer ejecutar el programa desde eclipse se deberá correr la clase GridLock.java configurando en el entorno las variables previamente mencionadas.

Formato del archivo de tableros
La primer linea hace referencia al cuadrado con la salida, contiene dos enteros separados por un espacio. El primero indica la posición "i" y el segundo la posición "j".
Las lineas siguientes contienen la información de cada bloque. Cada bloque ocupa una linea utilizando numeros enteros separados por espacios.
[Coordenada i de la cabeza] [coordenada j de la cabeza] [Coordenada i de la cola] [coordenada j de la cola] [1 si es horizontal, 0 si es vertical] [ID] [tamaño]

Notar que los ID deben ir de 1 en adelante siendo el 1 el bloque distinguido.
El tamaño corresponde a la cantidad de espacios que ocupa el bloque.
