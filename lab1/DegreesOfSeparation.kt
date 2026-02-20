import java.io.File
import java.io.BufferedReader
import kotlin.collections.mutableMapOf

fun degreesOfSeparation (p1: String, p2: String, grafo: Grafo<String>): Int{
    val visitados = mutableMapOf<String,Boolean>()
    val predecesor = mutableMapOf<String,String>()
    val cola = ArrayDeque<String>()

    if(p1 == p2) return 0

    cola.addLast(p1)
    while(cola.isNotEmpty()){
        val v = cola.removeFirst()
        visitados.put(v,true)
        for(amigo in grafo.obtenerArcosSalida(v)){
            if(amigo in visitados) continue
            predecesor.put(amigo,v)
            if(amigo == p2) return contar(p2,predecesor)
            cola.addLast(amigo)
        }
    }

    return -1
}

fun contar (p2: String, predecesor: MutableMap<String,String> ): Int{
    var n = 1
    var v = predecesor[p2]

    while(v in predecesor){
        n = n + 1
        v = predecesor[v]
    }
    return n
}




fun main (args: Array<String>){
    
    val archivo: String = "input.txt"
    val file: File = File(archivo)
    val bufferedReader: BufferedReader = file.bufferedReader()
    var linea = bufferedReader.readLine()

    val amigos: Grafo<String> = ListaAdyacenciaGrafo()

    while(linea != null){
        val personas = linea.split(" ")
        amigos.agregarVertice(personas[0])
        amigos.agregarVertice(personas[1])
        amigos.conectar(personas[0],personas[1])
        amigos.conectar(personas[1],personas[0])
        
        linea = bufferedReader.readLine()
    }

    println(degreesOfSeparation(args[0], args[1], amigos))
}