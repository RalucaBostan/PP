import java.io.File;
import java.io.IOException
import java.nio.charset.Charset
import javax.print.DocFlavor



fun readLines(file : File) : MutableList<String>{
    var mytext = file.readLines(Charsets.UTF_8);
    var text = mutableListOf<String>();
    mytext.forEach {
        text.add(it.toString());
        text.add("\n");
    }
    return text;
}

fun writeLines(text : MutableList<String>,file : File) : String{
    file.writeText("");
    var continut : String = "";
    text.forEach {
        file.appendText(it.toString());
        continut += it.toString();
    }
    return continut;

}

fun replaceMultipleSpace(text : MutableList<String>) : MutableList<String>{
    for(i in 0..text.size-1){
        text[i] = text[i].replace(" {2,10}".toRegex()," ");
    }
    return text;
}


fun replaceMultipleNewLines(text : String) : String {
    return text.replace("\n{2,5}".toRegex(),"\n");
}

fun findAndDeleteNumPage(text : MutableList<String>) : MutableList<String>{
    for(i in 0..text.size-1){
        if(text[i].contains(" {2,10}[0-9]".toRegex())) {
            text[i] = text[i].replace(" {2,10}".toRegex(), "");
            println("Numarul de pagina " + text[i] + " a fost gasit si eliminat!");
            text[i] = text[i].replace("[0-9]".toRegex(),"\n<deleted num_page>\n");
        }
    }
    println();
    return text;
}

fun KilltheAuthor(text : MutableList<String>) : MutableList<String> {
    for(i in 0..text.size-1){
        if(text[i].contains(" {2,10}\"".toRegex()) || text[i].contains("\"")){
            println("Numele autorului a fost gasit si eliminat. ");
            text[i] = text[i].replace(" {2,10}".toRegex(),"");
            text[i] = text[i].replace("\".*\"".toRegex(),"<nume autor eliminat>");
        }
    }
    println();
    return text;
}

fun Chapter(text: MutableList<String>) : MutableList<String>{
    for(i in 0..text.size-1){
        if(text[i].contains("-C.*".toRegex())){
            println("A fost gasit capitolul " + text[i] + ".");
            text[i] = text[i].replace("-C.*".toRegex(),"<nume capitol eliminat");
        }
    }
    return text;
}

fun MapareCaractereRomanesti(text : MutableList<String>) : MutableList<String>{
    val map = hashMapOf<Char,Char>(
        'Ă' to 'A',
        'ă' to 'a',
        'Â' to 'A',
        'â' to 'a',
        'Î' to 'I',
        'î' to 'i',
        'Ș' to 'S',
        'ș' to 's',
        'Ț' to 'T',
        'ț' to 't'
    );
    println();
    for(j in 0..text.size-1){
        for(i in 0..text[j].length-1) {
            if (map.contains(text[j][i])) {
                println("Caracterul " + text[j][i] + " a fost inlocuit cu " + map.get(text[j][i]));
                text[j] = text[j].replace(text[j][i].toString(),map.get(text[j][i]).toString());
            }
        }
    }
    return text;

}
fun main(args : Array<String>){
    val inputFile = File("ebook.txt");
    val outputFile = File("ebookresult.txt");
    outputFile.writeText(inputFile.readText(Charsets.UTF_8));
    var text = readLines(outputFile);


    // 1. Inlocuire spatii multiple cu un singur spatiu.
    text = replaceMultipleSpace(text);
    var continut = writeLines(text,outputFile);



    // 2. Eliminare salturi multiple la linie noua din text.
    continut = replaceMultipleNewLines(continut);
    outputFile.writeText(continut);




    // 3. Detectare si eliminarea numarului de pagina.
    text = readLines(outputFile);
    text = findAndDeleteNumPage(text);
    writeLines(text,outputFile);




    // 4. Detectare si eliminare nume autor
    text = readLines(outputFile);
    text = KilltheAuthor(text);
    writeLines(text,outputFile);




    // 5. Detectare si eliminare nume capitol
    text = readLines(outputFile);
    text = Chapter(text);
    writeLines(text,outputFile);



    // 6. Mapare caractere romanesti
    text = readLines(outputFile);
    text = MapareCaractereRomanesti(text);
    writeLines(text,outputFile);

}