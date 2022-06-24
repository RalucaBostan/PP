class Sat: Asezare{
    private var numar_case : Int = 0;

    constructor(numesat : String,locuitori : Int,numar_case : Int){
        this.SetNumeAsezare(numesat);
        this.SetNumarLocuitori(locuitori);
        this.numar_case = numar_case;
    }

    fun GetNumarCase() : Int{
        return numar_case;
    }

    fun SetNumarCase(numar : Int){
        numar_case = numar;
    }

    override fun toString(): String {
        return "Nume : [" + this.GetNumeAsezare() + "], Locuitori : [" + this.GetNumarLocuitori() + "], Nr_Case : [" + numar_case + "]";
    }

    fun accept(visitor: Visitor): Boolean {
        return visitor.visit(this);
    }
}