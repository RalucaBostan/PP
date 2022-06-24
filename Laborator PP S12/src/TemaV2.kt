import java.util.*
import kotlin.collections.HashMap

val curriedDisplay : (String) -> (MutableList<Int>) -> String = { x : String -> { y : MutableList<Int> -> "${x} : ${y.toString()}" }}

val curriedSort : (Int) -> (MutableList<Int>) -> Unit = {x : Int -> {y : MutableList<Int> -> if(x%2 == 0) Collections.sort(y) else Collections.reverse(y)}}

val curriedSum : (Int) -> (Int) -> Int = { x : Int -> { y : Int -> x + y}}

fun main(args : Array<String>){

    val hashmap : HashMap<String,MutableList<Int>> = HashMap()
    for(i in 2..9){
        val text : String = "Divizorii lui $i"
        hashmap.put(text, mutableListOf())
        for(j in 0..50){
            if(j % i == 0){
                hashmap[text]?.add(j)
            }
        }
    }
    println("Afisarea listei")
    hashmap.forEach{
        println(curriedDisplay(it.key)(it.value))
    }

    println("\n\nAfisare listei dupa sortare")
    hashmap.forEach{
        curriedSort(it.key[it.key.length - 1].toInt())(it.value)
    }

    hashmap.forEach{
        println(curriedDisplay(it.key)(it.value))
    }

    println("\n\nSuma numerelor")
    hashmap.forEach{
        var sum = 0
        it.value.forEach{
            sum = curriedSum(sum)(it)
        }
        println("${it.key} : Suma numerelor : ${sum}")
    }


}