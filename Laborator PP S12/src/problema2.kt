interface Animal{
    fun nevoie()
}

open class Felina : Animal{
    override fun nevoie() {
        println("Sunt felina si trebuie sa fiu pieptanata")
    }
}

class Pisica() : Felina()

open class Primata(val name : String) : Animal{
    open fun vorbeste() = "$name : face uhaha uhaa"
    override fun nevoie() {
        println("Sunt primata si trebie sa fiu scarpinata")
    }
}

class MaimutaMare(name : String) : Primata(name) {
    override fun vorbeste() = "${this.name} : urlet"
}

open class Domestic(val name : String) : Animal{
    open fun vorbeste() = "$name : face cri cri"
    open fun mananca() = "$name mananca orice"
    override fun nevoie() {
        println("Sunt animal domestic si trebuie sa fiu hranit")
    }
}

class Porc(name : String) : Domestic(name){
    override fun vorbeste() = "$name : face guitz guitz"
    override fun mananca() = "$name mananca chiar orice"
}

class Magar(name : String) : Domestic(name){
    override fun vorbeste() = "$name : face niho niho"
    override fun mananca() = "$name mananca doar paie si bataie"
}

fun mesajScris(primata : Primata){
    println(primata.vorbeste())
}

open class Ingrijitor(val name : String){
    open fun Felina.react() = "HRRMR!!!"

    fun Primata.react() = "$name se joaca cu ${this@Ingrijitor.name}"

    open fun Domestic.react() = "$name este greu de ingrijit!"

    fun areGrija(felina: Felina) {
        println("Felina face: ${felina.react()}")
    }
    fun areGrija(primata: Primata){
        println("Primata spune: ${primata.react()}")
    }
    fun areGrija(domestic: Domestic){
        println("Domesticul este :  ${domestic.react()}")
    }

    fun Ingrijitor.react() = "$name${this@Ingrijitor.name}"

    fun ingrijeste(animal: Animal){
        print("${name} trebuie sa ingrijeasca : ")
        animal.nevoie()
    }
}
open class Vet(name: String): Ingrijitor(name) {
    override fun Felina.react() = "fuge de $name"
    override fun Domestic.react() = "nu ii place sa fie ingijita de $name"
}


fun MutableMap<String,MutableList<Animal>>.adaugare(animal: Animal){
    val maimute = {nou : Animal,statement : String -> if(nou.javaClass.simpleName.equals("MaimutaMare")) "maimute" else ""}
    val porci = {nou : Animal,statement : String -> if(nou.javaClass.simpleName.equals("Porc")) "porci" else ""}
    val pisici = {nou : Animal,statement : String -> if(nou.javaClass.simpleName.equals("Pisica")) "pisici" else ""}
    val magari = {nou : Animal,statement : String -> if(nou.javaClass.simpleName.equals("Magar")) "magari" else ""}
    var statement : String = ""
    val states : Array<String> = arrayOf(maimute(animal,statement),porci(animal,statement),pisici(animal,statement),magari(animal,statement))
    states.forEach {
        if(!it.equals("")){
            this[it.toString()]?.add(animal);
        }
    }
}

fun MutableList<Animal>.sacrificare(animal: Animal){

}

fun MutableList<Animal>.ingrijire(ingrijitor: Ingrijitor){
    this.forEach{
        ingrijitor.ingrijeste(it)
    }
}

fun MutableMap<String,MutableList<Animal>>.ingrijire(ingrijitor: Ingrijitor){
    this.forEach{
        it.value.ingrijire(ingrijitor)
    }
}
fun main(args : Array<String>) {
    val ingrijitor = Ingrijitor("Vasile")
    val curte : MutableMap<String,MutableList<Animal>> = mutableMapOf()
    curte.put("maimute", mutableListOf())
    curte.put("porci", mutableListOf())
    curte.put("pisici", mutableListOf())
    curte.put("magari", mutableListOf())
    curte.adaugare(MaimutaMare("maimutica"))
    curte.adaugare(Porc("porc"))
    curte.adaugare(Pisica())
    curte.adaugare(Magar("magarusu"))
    curte.adaugare(Porc("alt porc"))
    curte.adaugare(Pisica())
    curte.adaugare(Magar("alt magarus"))
    curte.forEach{
        println("Zona pentru {${it.key}} : {${it.value.toString()}}")
    }
    println()
    println("Ingrijirea zilnica")
    curte.ingrijire(ingrijitor)
}