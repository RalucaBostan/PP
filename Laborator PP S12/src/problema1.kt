import java.util.Random.*;
import kotlin.random.Random

fun procesareNrPare(numar:Int,procesare:(Int)->Int):Int {
    if(numar%2==0) {
        return procesare(numar)
    } else {
        return numar
    }
}

fun main(args: Array<String>) {
    var nr1=4
    var nr2 = 5
    var lista1 : MutableList<Int> = mutableListOf(1)
    var lista2 : MutableList<MutableSet<Int>> = mutableListOf(mutableSetOf(1,2))
    for(i in 0..10) {
        lista1.add(Random.nextInt(0, 100))
        val set : MutableSet<Int> = mutableSetOf()
        for(i in 0..Random.nextInt(30,50))
            set.add(Random.nextInt(10,100))
        lista2.add(set)
    }

    val lambdaop = {number : Int, procesare:(Int) -> Int -> if(number %2 == 0) procesare(number) else number }
    val lambdasort = {lista : MutableList<Int> -> lista.sort()}
    val lambdasetop = {set : MutableSet<Int>,index : Int -> set.forEach { it * index }}
    println("Apel cu ${nr1} si operatia (it*2): ${lambdaop(nr1,{it*2})}")
    println("Apel cu ${nr2} si operatia (it*2): ${lambdaop(nr2,{it*2})}")
    println("\n\nLista nesortata : " + lista1.toString())
    lambdasort(lista1)
    println("Lista sortata : " + lista1.toString() + "\n\n")
    println("Lista de seturi inainte de lambda")
    lista2.forEach{
        println(it.toString())
    }
    lista2.forEach{
        lambdasetop(it,lista2.indexOf(it))
    }

    println("\nLista de seturi dupa lambda")
    lista2.forEach{
        println(it.toString())
    }
}