import java.io.File
import kotlin.math.truncate

typealias Dictionar = HashMap<String,String>

fun main(args : Array<String>){
    val dictionar : Dictionary = Dictionary(hashMapOf()).apply {
        Add_Mapping(Pair("Once","Odata"))
        Add_Mapping(Pair("upon","ca"))
        Add_Mapping(Pair("a",""))
        Add_Mapping(Pair("time","niciodata"))
        Add_Mapping(Pair("there","acolo"))
        Add_Mapping(Pair("was","a fost"))
        Add_Mapping(Pair("an","o"))
        Add_Mapping(Pair("old","batrana"))
        Add_Mapping(Pair("woman","femeie"))
        Add_Mapping(Pair("who","care"))
        Add_Mapping(Pair("loved","iubea"))
        Add_Mapping(Pair("baking","sa gateasca"))
        Add_Mapping(Pair("gingerbread","turta dulce"))
        Add_Mapping(Pair("She","Ea"))
        Add_Mapping(Pair("would","ar fi"))
        Add_Mapping(Pair("bake","gatit"))
        Add_Mapping(Pair("gingerbread","turta dulce"))
        Add_Mapping(Pair("cookies","biscuiti"))
        Add_Mapping(Pair("cakes","prajituri"))
        Add_Mapping(Pair("houses","case"))
        Add_Mapping(Pair("and","si"))
        Add_Mapping(Pair("people","oameni"))
        Add_Mapping(Pair("all","toti"))
        Add_Mapping(Pair("decorated","decorati"))
        Add_Mapping(Pair("with","cu"))
        Add_Mapping(Pair("chocolate","ciocolata"))
        Add_Mapping(Pair("peppermint","menta"))
        Add_Mapping(Pair("caramel","caramel"))
        Add_Mapping(Pair("candies","bomboane"))
        Add_Mapping(Pair("colored","colorate"))
        Add_Mapping(Pair("ingredients","ingrediente"))
    }

    var Poveste = "Once upon a time there was an old woman who loved baking gingerbread. She would bake gingerbread cookies, cakes, houses and gingerbread people, all decorated with chocolate and peppermint, caramel candies and colored ingredients."

    var story = Story(Poveste)

    var translator = Translator(dictionar,story)
    println(translator.traducere())

    val file = File("TranslateOutput.txt").apply {
        writeText("")
        appendText("Poveste [English] : ${Poveste}\n")
        appendText("Poveste [Romana] : ${translator.traducere()}")
    }

    val mapping : MutableList<Pair<String,String>> = mutableListOf()
    val inputFile = File("InputFile.txt")
    val string = inputFile.readLines()
    string.forEach{
        val element = it.removePrefix("(").removeSuffix(")").split(",")
        dictionar.Add_Mapping(Pair(element[0],element[1]))
    }


    Poveste = "I don't like working nonstop"

    story = Story(Poveste)

    translator = Translator(dictionar,story)
    println(translator.traducere())
}


class Dictionary(var dict : Dictionar){
    fun Add_Mapping(pereche : Pair<String,String>){
        dict.put(pereche.first,pereche.second)
    }
    fun Remove_Mapping(key : String){
        dict.remove(key)
    }
}

class Story(val story : String){
    fun prelucrare() : MutableList<String>{
        val words1 = story.split(" ")
        val words2 = mutableListOf<String>()
        for (word in words1){
            words2.add(word.trim(',','.'))
        }
        return words2
    }
}

class Translator(val dict : Dictionary,val story: Story){
    fun traducere() : String{
        var prelucrare = story.prelucrare()
        var translated = ""
        for(item in prelucrare){
            if(dict.dict.contains(item)){
                translated += dict.dict[item] + " "
            }
            else{
                translated += "[$item] "
            }
        }
        return translated
    }
}