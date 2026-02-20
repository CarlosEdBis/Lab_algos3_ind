import java.io.File
import java.io.BufferedReader

fun degreesOfSeparation (p1: String, p2: String, grafo: Grafo<String>): Int{
    if(p1 == p2) return 0
    if(!grafo.contiene(p1) || !grafo.contiene(p2)) return -1
    
    val visitados = mutableMapOf<String,Boolean>()
    val cola = mutableSetOf<String>()
    var prof = 0

    cola.add(p1)
    var u = p1
    var v = p1
    while(cola.isNotEmpty()){
        visitados.put(v,true)
        println(cola)
        for(amigo in grafo.obtenerArcosSalida(v)){
            if(amigo in visitados) continue
            if(amigo == p2) return prof + 1
            cola.add(amigo)
        }
        if(v == u){
            u = cola.last()
            prof = prof + 1
        }
        cola.remove(v)
        if(cola.isNotEmpty()) v = cola.first()
    }
    return -1
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
        linea = bufferedReader.readLine()
    }
    println(degreesOfSeparation(args[0], args[1], amigos))
}