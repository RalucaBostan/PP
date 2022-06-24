abstract class Asezare{
    private var locuitori : Int = 0;
    private var nume : String =  "";
    fun GetNumeAsezare() : String{
        return nume;
    }
    fun GetNumarLocuitori() : Int{
        return locuitori;
    }
    fun SetNumeAsezare(name : String){
        nume = name;
    }
    fun SetNumarLocuitori(number : Int){
        locuitori = number;
    }
}
