import java.io.File;
class Serializer(private val compute : ComputeData, private val index : Int){
    fun ToHTML(){
        val fisier = File("output_$index.html").writer();
        fisier.write("<HTML><HEAD><TITLE>Serializer</TITLE></HEAD>\n<BODY>\n")
        fisier.write("<p><h1>${compute.Sum()}</h1></p>\n")
        fisier.write("</BODY></HTML>");
        fisier.close();
    }
    fun ToJSON(){
        val fisier = File("output_$index.json").writer();
        fisier.write("{\"Sum\" : \"${compute.Sum()}\"}");
        fisier.close();
    }
}