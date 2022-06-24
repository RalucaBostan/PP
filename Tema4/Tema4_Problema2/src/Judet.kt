open class Judet(private var numeJudet : String){
    private var comune  = arrayOf<Comuna>();
    private var orase = arrayOf<Oras>();

    fun GetNumeJudet() : String{
        return numeJudet;
    }

    fun SetNumeJudet(nume : String) {
        numeJudet = nume;
    }

    fun AddComuna(comuna : Comuna){
        comune += comuna;
    }

    fun AddOras(oras: Oras){
        orase += oras;
    }

    override fun toString(): String {
        var string : String = "";
        string += " Judet : [" + numeJudet + "]";
        string += "\n\tComune : ";
        for(comuna in comune){
            string += "" + comuna + "\n";
        }
        string += "\n\tOrase componente : ";
        for(oras in orase){
            string += "" + oras + "";
        }
        return string;
    }

    open fun accept(visitor: Visitor): Boolean {
        return visitor.visit(this);
    }
}