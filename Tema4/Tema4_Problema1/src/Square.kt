class Square(private val v : Double) : Solids{
    override fun GetArea(): Double {
        return Math.pow(v,2.0);
    }

    override fun GetVolume(): Double {
        return Math.pow(v,3.0);
    }
}