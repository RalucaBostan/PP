typealias FunctieCuInt = (Int) -> Int
infix fun FunctieCuInt.map(g : FunctieCuInt) : FunctieCuInt{
    return {x -> this(g(x))}
}

val Aduna3SiInmultesteCu3 = {a : Int -> a + 2} map {a: Int -> a * 3}

val Aduna5SiInmultesteCu5 : (Int) -> Int = { a: Int -> a + 2 }.map { a : Int -> a * 3 }




fun main(args : Array<String>){
    println(Aduna3SiInmultesteCu3(33))
    println(Aduna5SiInmultesteCu5(33))
}