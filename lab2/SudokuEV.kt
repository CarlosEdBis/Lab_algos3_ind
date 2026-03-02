
/*
import kotlin.collections.mutableSetOf
data class Celda(var valor: Int, var opciones: MutableSet<Int>)

fun solSudoku(sudk: String){
    if (sudk.length != 81){
        println("NOSOLUTION")
        return
    }

    var tablero: Array<Array<Celda>> = crearTablero(sudk)

    if (tablero[0][0].valor == -1) return

    val inicio = System.nanoTime()
    solSudokuRec(tablero)
    val fin = System.nanoTime()
    println("Tiempo: ${fin - inicio} ns")

    for (i in 0 until 9){
        for (j in 0 until 9){
            print(tablero[i][j].valor)
        }
    }
    println("")
}

fun solSudokuRec(tablero: Array<Array<Celda>>): Boolean{
    val celda = encontrarCelda(tablero) ?: return true
    val (fil, col) = celda

    for (k in tablero[fil][col].opciones.toList()){
        tablero[fil][col].valor = k
        eliminarOpAdy(k, tablero, fil, col)

        if(solSudokuRec(tablero)) return true

        tablero[fil][col].valor = 0
        restaurarOpAdy(k, tablero, fil, col)
    }
    return false
}

fun crearTablero(sudk: String): Array<Array<Celda>>{
    var m: Int = 0
    var tablero = Array(9) { Array(9) { Celda(0, mutableSetOf(1,2,3,4,5,6,7,8,9)) } }

    for(i in 0 until 9){
        for(j in 0 until 9){
            val n = sudk[m].digitToInt()

            if (n != 0 && validarJugada(n, tablero, i, j)){
                tablero[i][j].valor = n
                eliminarOpAdy(n, tablero, i, j)
            } else if(n != 0){
                println("NOSOLUTION")
                return Array(1) { Array(1) { Celda(-1, mutableSetOf()) } }
            }
            m++
        }
    }
    return tablero
}

fun validarJugada(n: Int, tablero: Array<Array<Celda>>, fil: Int, col: Int): Boolean{
    for(i in 0 until 9){
        if (tablero[fil][i].valor == n) return false
    }
    for(i in 0 until 9){
        if (tablero[i][col].valor == n) return false
    }

    val inicioFil = (fil / 3) * 3
    val inicioCol = (col / 3) * 3
    for (i in 0 until 3){
        for(j in 0 until 3){
            if(tablero[inicioFil + i][inicioCol + j].valor == n) return false
        }
    }
    return true
}

fun encontrarCelda(tablero: Array<Array<Celda>>): Pair<Int, Int>?{
    var min: Pair<Int, Int>? = null
    var opc = 10

    for(i in 0 until 9){
        for(j in 0 until 9){
            if(tablero[i][j].valor == 0 && tablero[i][j].opciones.size < opc){
                min = Pair(i,j)
                opc = tablero[i][j].opciones.size
            }
        }
    }
    return min
}

fun eliminarOpAdy(n: Int, tablero: Array<Array<Celda>>, fil: Int, col: Int){
    val inicioFil = (fil / 3) * 3
    val inicioCol = (col / 3) * 3

    for (i in 0 until 3){
        for(j in 0 until 3){
            if(inicioFil + i == fil && inicioCol + j == col) continue
            tablero[inicioFil + i][inicioCol + j].opciones.remove(n)
        }
    }

    for(i in 0 until 9){
        tablero[fil][i].opciones.remove(n)
    }
    for(i in 0 until 9){
        tablero[i][col].opciones.remove(n)
    }
}

fun restaurarOpAdy(n: Int, tablero: Array<Array<Celda>>, fil: Int, col: Int){
    val inicioFil = (fil / 3) * 3
    val inicioCol = (col / 3) * 3

    for (i in 0 until 3){
        for(j in 0 until 3){
            if(inicioFil + i == fil && inicioCol + j == col) continue
            if(validarJugada(n, tablero, inicioFil + i, inicioCol + j)){
                tablero[inicioFil + i][inicioCol + j].opciones.add(n)
            }
        }
    }

    for(i in 0 until 9){
        if(validarJugada(n, tablero, fil, i)){
            tablero[fil][i].opciones.add(n)
        }
    }
    for(i in 0 until 9){
        if(validarJugada(n, tablero, i, col)){
            tablero[i][col].opciones.add(n)
        }
    }
}


fun main(args: Array<String>){
    solSudoku(args[0])
}
*/