class Tara(private val numeTara : String,private val capitala : Capitala, private val judete : Array<Judet>){
    override fun toString(): String {
        var string : String = "";
        string += "Tara : [" +numeTara+ "]\n";
        string += capitala;
        string += "-------Judete-------\n";
        for(judet in judete){
            string += judete.indexOf(judet).toString() + "." + judet + "\n\n";
        }
        return string;
    }
}