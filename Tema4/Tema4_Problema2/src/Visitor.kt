interface Visitor{
    fun visit(asezare : Sat) : Boolean
    fun visit(asezare : Oras) : Boolean
    fun visit(asezare : Comuna) : Boolean
    fun visit(asezare : Judet) : Boolean
    fun visit(asezare : Capitala) : Boolean
}