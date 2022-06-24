/*import java.io.File
import java.io.InputStream


fun main(args : Array<String>) {
    val logFile1: Log = Log("Semafor.txt")
    val logFile2: Log = Log("AltSemafor.txt")
    val logFile3: Log = Log("IncaUnSemafor.txt")
    logFile1.Reset()
    logFile2.Reset()
    logFile3.Reset()
    for(i in 1..3) {
        val thread  = Producer(arrayOf(logFile1, logFile2, logFile3), i.toString())
        thread.start()
        thread.join()
    }
}

class Log(val filename : String){
    fun Write(line : String){
        File(filename).appendText(line)
    }

    fun Reset() {
        File(filename).delete()
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

class Producer(var log : Array<Log>, var text : String) : Thread() {
        override fun run() {
        for (i in 1..50) {
            if (Semafor().Enter()) {
                for(logg in log) {
                    logg.Write("Thread nr. " + text + " : " + i.toString() + "\n")
                }
                Semafor().Exit()
            }
        }
    }
}
*/