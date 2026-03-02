
fun solSudoku(sudk: String){
    if (sudk.length != 81){
        println("NOSOLUTION")
        return
    }

    var tablero: Array<Array<Int>> = crearTablero(sudk)

    if (tablero[0][0] == -1) return

    val inicio = System.nanoTime()
    solSudokuRec(tablero, 0, 0)
    val fin = System.nanoTime()
    println("Tiempo: ${fin - inicio} ns")

    for (i in 0 until 9){
        for (j in 0 until 9){
            print(tablero[i][j])
        }
    }
    println("")
}

fun solSudokuRec(tablero: Array<Array<Int>>, fil: Int, col: Int): Boolean{
    if(fil == 9) return true

    val nextFil = if (col == 8) fil + 1 else fil
    val nextCol = if (col == 8) 0 else col + 1

    if(tablero[fil][col] != 0){
        if(solSudokuRec(tablero, nextFil, nextCol)) return true
        return false
    }

    for (k in 1 until 10){
        if (validarJugada(k, tablero, fil, col)){
            tablero[fil][col] = k
            if(solSudokuRec(tablero, nextFil, nextCol)) return true
            tablero[fil][col] = 0
        }
    }
    return false
}

fun crearTablero(sudk: String): Array<Array<Int>>{
    var m: Int = 0
    var tablero = Array(9) { Array(9) { 0 } }

    for(i in 0 until 9){
        for(j in 0 until 9){
            val n = sudk[m].digitToInt()

            if (n != 0 && validarJugada(n, tablero, i, j)){
                tablero[i][j] = n
            } else if(n != 0){
                println("NOSOLUTION")
                return Array(1) { Array(1) { -1 } }
            }
            m++
        }
    }
    return tablero
}

fun validarJugada(n: Int, tablero: Array<Array<Int>>, fil: Int, col: Int): Boolean{
    for(i in 0 until 9){
        if (tablero[fil][i] == n) return false
    }
    for(i in 0 until 9){
        if (tablero[i][col] == n) return false
    }

    val inicioFil = (fil / 3) * 3
    val inicioCol = (col / 3) * 3
    for (i in 0 until 3){
        for(j in 0 until 3){
            if(tablero[inicioFil + i][inicioCol + j] == n) return false
        }
    }
    return true
}


fun main(args: Array<String>){
    solSudoku(args[0])
}