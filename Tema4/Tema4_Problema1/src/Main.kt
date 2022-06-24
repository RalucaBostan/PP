fun main(args : Array<String>){
    val my_rectangles = arrayOf<NonSolidShape>(
        Rectangle(3.1,2.2),
        Rectangle(l=6.12,L = 8.1)
    )

    val my_solids = arrayOf<Solids>(
        Circle(5.0),
        Square(9.7),
        Circle(4.4),
        Circle(7.1),
        Square(14.7),
        Circle(23.21)
    )

    val my_compA = ComputeA(my_rectangles);
    val my_compV = ComputeV(my_solids);

    val serA = Serializer(my_compA,1);
    val serV = Serializer(my_compV,2);

    serA.ToHTML();
    serA.ToJSON();

    serV.ToHTML();
    serV.ToJSON();

}