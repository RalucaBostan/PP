/*import kotlinx.coroutines.*
import java.util.*
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock
import kotlin.system.*
suspend fun CoroutineScope.massiveRun(n : Int,action: suspend () -> Unit) {
    var s = 0
    var i = 0
    val lock : Lock =  ReentrantLock()
    val time = measureTimeMillis {
        val jobs = List(n)
        {
            launch {
                lock.lock()
                i += 1
                s += i
                lock.unlock()
            }
        }
        jobs.forEach { it.join() }
    }
    println("Suma este ${s}, in $time ms")
}
val mtContext = newFixedThreadPoolContext(2, "mtPool")
var counter = 0
fun main() = runBlocking<Unit> {
    var queue : Queue<Int> = ArrayDeque<Int>();
    queue.add(100);
    queue.add(1000);
    queue.add(10000);
    queue.add(50000);
    while(!queue.isEmpty()) {
        CoroutineScope(mtContext).massiveRun(queue.peek()) {}
        queue.remove()

    }
}*/