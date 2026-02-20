class ListaAdyacenciaGrafo<T>(): Grafo<T>{

    private val verticesLados = mutableMapOf<T, MutableList<T>>()

    override fun tamano():Int = this.verticesLados.size

    override fun contiene(v:T):Boolean = v in this.verticesLados

    override fun agregarVertice(v:T): Boolean {
        if(contiene(v)) return false
        verticesLados.put(v,mutableListOf<T>())
        return true
    }

    override fun conectar(desde:T,hasta:T): Boolean {
        if (!contiene(desde) || !contiene(hasta)) return false
        verticesLados[desde]!!.add(hasta)
        return true
    }

    override fun obtenerArcosSalida(v:T): List<T>{
        if(!contiene(v)) return listOf<T>()
        return verticesLados[v]!!.toList()       
    }

    override fun eliminarVertice(v:T): Boolean {
        if(!contiene(v)) return false
        verticesLados.remove(v)
        for (lista in verticesLados.values){
            if (v in lista) {
                lista.remove(v)
            }
        }
        return true   
    }

    override fun obtenerArcosEntrada(v:T): List<T>{
        if(!contiene(v)) return listOf<T>()
        var arcosEntrada: MutableList<T> = mutableListOf<T>()
        for ((vertice, arcos) in verticesLados){
            if (v in arcos){
                arcosEntrada.add(vertice)
            }
        }
        return arcosEntrada.toList()
    }
    
    override fun subgrafo(vertices: Collection<T>): ListaAdyacenciaGrafo<T>{
        for(v in vertices){
            if(!(contiene(v))) return ListaAdyacenciaGrafo()
        }

        val subgrafo = ListaAdyacenciaGrafo<T>()
        for(v in vertices){
            val originales = this.verticesLados[v]
            val copiaFiltrada: MutableList<T> = originales!!.filter {it in vertices}.toMutableList()
            subgrafo.verticesLados.put(v, copiaFiltrada)
        }
        return subgrafo
    }
}