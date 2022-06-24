/*import java.io.File
import java.io.InputStream
import java.nio.charset.Charset
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock

fun main(args : Array<String>) {
    var logger = Log(100)
    val writer1 = Producator(File("Semafor.txt"),logger)
    val writer2 = Consumator(File("Semafor.txt"),logger)
    writer1.start()
    writer2.start()
}

class Log(val maximul :  Int){
    companion object instanta{
        val instance = Log(100)
        val fname = "Semafor.txt"
    }

    fun Write(line : String){
        if(Semafor().Enter()) {
            File(fname).appendText(line + "\n")
            Thread().join()
            Semafor().Exit()
        }
    }

    fun Read() : String{
        if(Semafor().Enter()) {
            val inputStream: InputStream = File(fname).inputStream()
            val inputstring = inputStream.bufferedReader().use { it.readText() }
            if (inputstring != null) {
                Thread().join()
                Semafor().Exit()
                return inputstring
            }
        }
        return ""
    }

    fun Reset() {
        File(fname).delete()
    }
}

class Semafor{
    companion object semaphore{
        public var semafor : Semafor.semaphore? = null
    }
    fun Enter() : Boolean{
        if(semafor == null){
            semafor = Semafor.semaphore
            return true
        }
        return false
    }

    fun Exit(){
        semafor = null
    }
}

class Producator(var fname : File,var buffer: Log) : Thread(){
    override fun run() {
        for(i in 0..99){
            buffer.Write(i.toString())
            println("Producator : " + i.toString())
        }
    }
}

class Consumator(var fname : File,var buffer: Log ) : Thread(){
    override fun run() {
        for(i in 0..99){
            val read = buffer.Read()
            println("Consumator : " + read)
        }
    }
*/