import java.io.File


abstract class Asezare{
    val observer : Logger = Logger(this)
    abstract fun getNumeAsezare() : String
    abstract fun getNumarLocuitori() : Int
    fun notifyObserver(source : String,message: String){observer.update(source,message)}
    fun accept(visitor: EsteCapitalaVisitor) : Boolean{
        return visitor.visit(this.javaClass.name)
    }
}

class Sat(private val nume : String, private val numar_locuitori : Int, private val numar_case : Int) : Asezare() {
    override fun getNumeAsezare(): String {
        return this.nume
    }

    override fun getNumarLocuitori(): Int {
        return this.numar_locuitori
    }

    fun getNumarCase() : Int{
        return this.numar_case
    }
}

class Oras(private val nume : String, private val numar_locuitori : Int, private val numar_blocuri : Int,private val numar_spitale : Int) : Asezare() {
    override fun getNumeAsezare(): String {
        return this.nume
    }

    override fun getNumarLocuitori(): Int {
        return this.numar_locuitori
    }

    fun getNumarBlocuri() :Int{
        return this.numar_blocuri
    }

    fun getNumarSpitale() : Int{
        return this.numar_spitale
    }
}

class Comuna(private val nume : String) : Asezare(){
    private val sate : MutableList<Sat> = mutableListOf()

    override fun getNumeAsezare(): String {
        return this.nume
    }

    override fun getNumarLocuitori(): Int {
        var nrloc = 0
        var lambda = {x : Int,y: Int -> x + y}
        sate.forEach{
            nrloc = lambda(it.getNumarLocuitori(),nrloc)
        }
        return nrloc
    }

    fun AddSat(sat: Sat){
        this.notifyObserver(this.javaClass.name,"A fost adaugat satul ${sat.getNumeAsezare()}")
        this.sate.add(sat)
    }
}

open class Judet(private val nume : String) : Asezare(){
    private val comune : MutableList<Comuna> = mutableListOf()
    private val orase : MutableList<Oras> = mutableListOf()


    override fun getNumeAsezare(): String {
        return this.nume
    }

    override fun getNumarLocuitori(): Int {
        var nrloc = 0
        var lambda = {x : Int,y: Int -> x + y}
        comune.forEach{
            nrloc = lambda(it.getNumarLocuitori(),nrloc)
        }
        orase.forEach{
            nrloc = lambda(it.getNumarLocuitori(),nrloc)
        }
        return nrloc
    }

    fun AddComuna(comuna : Comuna){
        this.notifyObserver(this.javaClass.name,"A fost adaugata comuna ${comuna.getNumeAsezare()}")
        this.comune.add(comuna)
    }

    fun AddOras(oras: Oras){
        this.notifyObserver(this.javaClass.name,"A fost adaugat orasul ${oras.getNumeAsezare()}")
        this.orase.add(oras)
    }
}

class Capitala(private val nume: String,private val an : Int) : Judet(nume){
    fun getAnAtestare() : Int{
        return this.an
    }
}

class Tara(private val nume : String,private val capitala: Capitala,private val judet: MutableList<Judet>) : Asezare(){
    override fun getNumarLocuitori(): Int {
        var nrloc = 0
        judet.forEach{
            nrloc += it.getNumarLocuitori()
        }
        return nrloc
    }

    override fun getNumeAsezare(): String {
        return this.nume
    }
}

class Logger(val asezare: Asezare){
    companion object {
        var file = File("Logger.txt")
    }
    fun update(sursa : String,mesaj: String){
        file.appendText("[$sursa]  --->   $mesaj\n")
    }

}

fun main(){
    Logger.file.writeText("")
    val sate_iasi = arrayOf<Sat>(Sat("Badeni", 1700, 321), Sat("Cucuteni", 3200, 1419), Sat("Poieni", 4210, 1771))
    val sate_neamt = arrayOf<Sat>(Sat("Cut",5200,1807), Sat("Brasauti",2900,660), Sat("Valeni",2450,1000), Sat("Izvoare",5200,1205))
    val comune_iasi = arrayOf<Comuna>(Comuna("Osoi"), Comuna("Mironeasa"))
    val comune_neamt = arrayOf(Comuna("Dumbrava Rosie"))
    val orase_iasi = arrayOf<Oras>(Oras("Pascani", 34200, 210, 2), Oras("Iasi", 800000, 3400, 7))
    val orase_neamt = arrayOf<Oras>(Oras("Piatra Neamt", 300000, 1780, 1), Oras("Roman", 150000, 630, 1))
    val judete = arrayOf<Judet>(Judet("Iasi"), Judet("Neamt"), Judet("Bucuresti"))
    val capitala = Capitala("Bucuresti Resedinta", 1459)


    comune_iasi[0].AddSat(sate_iasi[0])
    comune_iasi[0].AddSat(sate_iasi[1])

    comune_iasi[1].AddSat(sate_iasi[2])

    sate_neamt.forEach {
        comune_neamt[0].AddSat(it)
    }
    comune_iasi.forEach {
        judete[0].AddComuna(it)
    }
    orase_iasi.forEach {
        judete[0].AddOras(it)
    }
    comune_neamt.forEach {
        judete[1].AddComuna(it)
    }
    orase_neamt.forEach {
        judete[1].AddOras(it)
    }

    val sate_bucuresti = arrayOf<Sat>(Sat("Piteasca", 4700, 450), Sat("Tamasi", 2300, 650))
    val comune_bucuresti = arrayOf<Comuna>(Comuna("Cornetu" ), Comuna("Jilava"))
    val orase_bucuresti = arrayOf<Oras>(Oras("Bucuresti", 2500000, 8901, 15), Oras("Ilfov", 460000,3400,5))

    comune_bucuresti[0].AddSat(sate_bucuresti[0])
    comune_bucuresti[1].AddSat(sate_bucuresti[1])
    comune_bucuresti.forEach {
        judete[2].AddComuna(it)
    }
    orase_bucuresti.forEach {
        judete[2].AddOras(it)
    }

    val tara = Tara("Romania", capitala, judete.toMutableList())

    val capital_visitor = EsteCapitalaVisitor()
    val toate_satele = sate_bucuresti + sate_iasi + sate_neamt
    val toate_comunele = comune_bucuresti + comune_iasi + comune_neamt
    val toate_orasele = orase_bucuresti + orase_iasi + orase_neamt

    toate_satele.forEach {
        println("${it.getNumeAsezare()} este capitala : ${it.accept(capital_visitor)}")
    }
    toate_comunele.forEach {
        println("${it.getNumeAsezare()} este capitala : ${it.accept(capital_visitor)}")
    }
    toate_orasele.forEach {
        println("${it.getNumeAsezare()} este capitala : ${it.accept(capital_visitor)}")
    }
    judete.forEach {
        println("${it.getNumeAsezare()} este capitala : ${it.accept(capital_visitor)}")
    }
    println("${capitala.getNumeAsezare()} este capitala : ${capitala.accept(capital_visitor)}")
}



class EsteCapitalaVisitor{
    fun visit(string: String) : Boolean{
        return string.equals("Capitala")
    }
}

