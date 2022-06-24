class Circle(private val v : Double) : Solids{
    override fun GetArea(): Double {
        return Math.PI * Math.pow(v,2.0);
    }

    override fun GetVolume(): Double {
        return (4.0/3.0) * Math.PI * Math.pow(v,3.0);
    }
}