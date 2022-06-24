/*import kotlinx.coroutines.*
import java.io.File
import java.util.*
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock
import kotlin.system.*

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

class Log(val filename : String){
    fun Write(line : String){
        File(filename).appendText(line)
    }

    fun Reset() {
        File(filename).delete()
    }
}

suspend fun CoroutineScope.massiveRun(logs : Array<Log>,text : String,action: suspend () -> Unit) {
    val time = measureTimeMillis {
        val jobs = List(50)
        {
            launch {
                if (Semafor().Enter()) {
                    for(log in logs) {
                        log.Write("Thread nr. " + text + " : " + it.toString() + "\n")
                    }
                    Semafor().Exit()
                }
            }
        }
        jobs.forEach { it.join() }
    }
}
val mtContext = newFixedThreadPoolContext(3, "mtPool")
var counter = 0
fun main() = runBlocking<Unit> {
    val logFile1: Log = Log("SemaforCoroutine.txt")
    val logFile2: Log = Log("AltSemaforCoroutine.txt")
    val logFile3: Log = Log("IncaUnSemaforCoroutine.txt")
    logFile1.Reset()
    logFile2.Reset()
    logFile3.Reset()
    CoroutineScope(mtContext).massiveRun(arrayOf(logFile1,logFile2,logFile3),"1") {}
    CoroutineScope(mtContext).massiveRun(arrayOf(logFile1,logFile2,logFile3),"2") {}
    CoroutineScope(mtContext).massiveRun(arrayOf(logFile1,logFile2,logFile3),"3") {}

}*/