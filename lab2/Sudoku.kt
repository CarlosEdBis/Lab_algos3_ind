
fun solSudoku(sudk: String){
    if (sudk.length != 81){
        println("NOSOLUTION")
        return
    }

    var tablero: Array<Array<Int>> = crearTablero(sudk)

    if (tablero[0][0] == -1) return

    solSudokuRec(tablero)

    println("")
    
    for (i in 0 until 9){
        for (j in 0 until 9){
            print(tablero[i][j])
        }
    }
    println("")
}

fun solSudokuRec(tablero: Array<Array<Int>>){
    
}

fun crearTablero(sudk: String): Array<Array<Int>>{
    var m: Int = 0
    var tablero = Array(9) { Array(9) { 0 } }

    for(i in 0 until 9){
        for(j in 0 until 9){
            val n = sudk[m].digitToInt()

            if (n != 0 && validarJugada(n, tablero, i, j)){
                tablero[i][j] = sudk[m].digitToInt()
            } else if(n != 0){
                println("NOSOLUTION")
                return Array(1) { Array(1) { -1 } }
            }
            println(m)
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
    return true
}


fun main(args: Array<String>){
    println(args[0])
    solSudoku(args[0])
}