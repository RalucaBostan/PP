/*import kotlinx.coroutines.*
import kotlin.system.*
import java.io.File
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock

suspend fun CoroutineScope.massiveRun(action : suspend() -> Unit) {
    File("data.txt").delete()
    val n = 100
    val k = 1000
    val random : MutableList<Int> = mutableListOf()
    val lock : Lock =  ReentrantLock()
    for (i in 0..k){
        random.add(i.toInt())
    }
    var i : Int = 0
    val time = measureTimeMillis {
        val jobs = List(n)
        {
            launch {
                lock.lock()
                File("data.txt").appendText(random[i].toString() + "\n")
                i += 1
                repeat(k) { action() }
                lock.unlock()
            }
        }
        jobs.forEach{
            it.join()
        }
    }
    println("S-au efectuat ${n * k} operatii in $time ms")
}

val mtContext = newFixedThreadPoolContext(1,"mtPool")
var counter = 0
fun main() = runBlocking<Unit>{
    CoroutineScope(mtContext).massiveRun{
        counter++
    }
    println("Numarator = $counter")
}
*/