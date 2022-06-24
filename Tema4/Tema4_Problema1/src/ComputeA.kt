class ComputeA(private val shapes : Array<NonSolidShape>) : ComputeData{
    override fun Sum() : Double{
        var sum : Double = 0.0;
        shapes.forEach {
            sum += it.GetArea();
        }
        return sum;
    }
}