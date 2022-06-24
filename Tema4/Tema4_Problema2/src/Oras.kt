class Oras : Asezare{
    private var numar_blocuri : Int = 0;
    private var numar_spitale : Int = 0;

    constructor(numeoras : String,locuitori : Int,numar_blocuri : Int,numar_spitale : Int){
        this.SetNumeAsezare(numeoras);
        this.SetNumarLocuitori(locuitori);
        this.numar_blocuri = numar_blocuri;
        this.numar_spitale = numar_spitale;
    }

    fun GetNumarBlocuri() : Int{
        return numar_blocuri;
    }

    fun GetNumarSpitale() : Int{
        return numar_spitale;
    }

    fun SetNumarCase(numar : Int){
        numar_blocuri = numar;
    }

    fun SetNumarSpitale(numar : Int){
        numar_spitale = numar;
    }

    override fun toString(): String {
        return "\n\t\t\tOras : [" + this.GetNumeAsezare() + "]. Locuitori : [" + this.GetNumarLocuitori() + "], Nr_Blocuri : [" + numar_blocuri + "], Spitale : [" + numar_spitale + "]";
    }

    fun accept(visitor: Visitor): Boolean {
        return visitor.visit(this);
    }
}