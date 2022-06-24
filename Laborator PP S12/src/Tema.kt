val curriedDisplay : (String) -> (Carte) -> String = { x : String -> { y : Carte -> "Genul cartii : ${x} . \nDetalii : \n${y.toString()}" }}

val curriedSortby : (String) -> (HashMap<String,Carte>) -> Boolean = {x : String -> {y : HashMap<String,Carte> -> y.sortBy(x)}}

val curriedSum : (Int) -> (Carte) -> Int = {x : Int -> {y : Carte -> if(y.rating > 5) y.pret - x else 0 }}

var add = {x : Int,y : Int,z : Int -> x + y + z}.curried()
val add1 = {x : Int ->{y : Int -> {z : Int -> x + y + z}}}.uncurried()


data class Carte(val delegate : HashMap<String,Any?>) : Comparable<Carte>{
    val nume : String by delegate
    val autori : String by delegate
    val numarPagini : Int by delegate
    val dataPublicarii : String by delegate
    val editura : String by delegate
    val rating : Int by delegate
    val pret : Int by delegate
    public override fun toString(): String {
        var str : String = "\tNume : ${nume}\n";
        str += "\tAutor : ${autori}\n";
        str += "\tNumar pagini : ${numarPagini}\n";
        str += "\tData publicarii : ${dataPublicarii}\n";
        str += "\tEditura : ${editura}\n";
        str += "\tRating : ${rating}\n";
        str += "\tPret : ${pret}";
        return str;
    }

    override fun compareTo(other : Carte) : Int{
        return COMPARATOR.compare(this,other)
    }

    companion object{
        private var COMPARATOR =Comparator.comparingInt<Carte> { it.rating }
        var statement : String = ""
        fun changeStatement(string : String) {
            statement = string;
            when(statement){
                "rating" -> COMPARATOR = Comparator.comparingInt<Carte> { it.rating }
                "numarpagini" -> COMPARATOR = Comparator.comparingInt<Carte> { it.numarPagini }
                "pret" -> COMPARATOR = Comparator.comparingInt<Carte> { it.pret }
            }
        }
    }
}


fun HashMap<String,Carte>.sortBy(string : String) : Boolean{
    Carte.changeStatement(string)
    this.toList().sortedBy { (_,value) -> value}.toMap()
    return true

}

fun main(args : Array<String>){
    val mapCarte1 = hashMapOf<String,Any?>(
        "nume" to "Critica ratiunii pure",
        "autori" to "Immanuel Kant",
        "numarPagini" to 250,
        "dataPublicarii" to "12/05/1998",
        "editura" to "IRI",
        "rating" to 9,
        "pret" to 120
    )
    val mapCarte2 = hashMapOf<String,Any?>(
        "nume" to "Povesti corecte politic de adormit copii",
        "autori" to "James Finn Garner",
        "numarPagini" to 200,
        "dataPublicarii" to "01/06/2006",
        "editura" to "Humanitas",
        "rating" to 4,
        "pret" to 60
    )

    val mapCarte3 = hashMapOf<String,Any?>(
        "nume" to "Capra cu trei iezi",
        "autori" to "Ion Creanga",
        "numarPagini" to 10,
        "dataPublicarii" to "01/10/1870",
        "editura" to "Humanitas",
        "rating" to 10,
        "pret" to 10
    )

    val mapCarte4 = hashMapOf<String,Any?>(
        "nume" to "Magicianul",
        "autori" to "Zafon",
        "numarPagini" to 1243,
        "dataPublicarii" to "10/02/2010",
        "editura" to "Iris",
        "rating" to 7,
        "pret" to 120
    )


    val cartea1 = Carte(mapCarte1)
    val cartea2 = Carte(mapCarte2)
    val cartea3 = Carte(mapCarte3)
    val cartea4 = Carte(mapCarte4)

    val biblioteca : HashMap<String,Carte> = hashMapOf<String,Carte>(
        "copii" to cartea3,
        "psihologie" to cartea1,
        "beletristica" to cartea4,
        "vrajeala" to cartea2
    )

    biblioteca.forEach{
        println(curriedDisplay(it.key)(it.value))
        println()
    }


    curriedSortby("rating")(biblioteca)
    println("\n\n")
    biblioteca.forEach{
        println(curriedDisplay(it.key)(it.value))
        println()
    }

    var valoare : Int = 0
    biblioteca.forEach{
        valoare = valoare + curriedSum(2)(it.value)
    }

    println("Valoarea totala a cartilog cu rating peste 5 : ${valoare}")

}