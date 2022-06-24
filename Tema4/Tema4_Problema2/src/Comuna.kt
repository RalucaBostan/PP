class Comuna(private var nume_comuna : String){
    private var sate  = arrayOf<Sat>();

    fun GetNumeComuna() : String{
        return nume_comuna;
    }

    fun SetNumeComuna(nume : String) {
        nume_comuna = nume;
    }

    fun AddSat(sat : Sat){
        sate += sat;
    }

    override fun toString(): String {
        var string : String = "";
        string += "\n\t\tComuna : [" + nume_comuna + "]";
        string += "\n\t\tSate Componente : ";
        for(sat in sate){
            string += "\n\t\t\t" + sate.indexOf(sat).toString() + ". " + sat;
        }
        return string;
    }

    fun accept(visitor: Visitor): Boolean {
        return visitor.visit(this);
    }
}