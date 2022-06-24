class Rectangle(private val L : Double,private val l : Double) : NonSolidShape{
    override fun GetArea(): Double {
        return L * l;
    }
}