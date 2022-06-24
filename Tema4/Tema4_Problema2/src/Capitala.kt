import javax.print.DocFlavor

class Capitala(private val numeResedinta : String, private val AnDeclarare : Int) : Judet(numeResedinta.substringBefore(" Resedinta")){
    fun getNumeCapitala() : String{
        return numeResedinta;
    }

    fun getAnDeclarare() : Int{
        return AnDeclarare;
    }

    override fun toString(): String {
        return "Capitala : [" + getNumeCapitala() + " din " + getAnDeclarare() + "]\n";
    }

    override fun accept(visitor: Visitor): Boolean {
        return visitor.visit(this);
    }
}