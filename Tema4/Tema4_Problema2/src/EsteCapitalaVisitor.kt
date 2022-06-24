class EsteCapitalaVisitor : Visitor{
    override fun visit(asezare: Capitala): Boolean {
        return true;
    }

    override fun visit(asezare: Comuna): Boolean {
        return false;
    }

    override fun visit(asezare: Judet): Boolean {
        return false;
    }

    override fun visit(asezare: Oras): Boolean {
        return false;
    }

    override fun visit(asezare: Sat): Boolean {
        return false;
    }
}