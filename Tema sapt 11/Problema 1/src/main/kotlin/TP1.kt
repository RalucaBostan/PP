import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors.newFixedThreadPool
import java.util.concurrent.Executors;

class Multiply(var array : IntArray,val alpha : Int) : Thread(){
    override fun run() {
        for(i in 0..array.size-1){
            array[i] *= alpha;
        }
    }
}

class Sort(var array : IntArray) : Thread(){
    override fun run() {
        array.sort();
    }
}

class Display(var array : IntArray) : Thread(){
    override fun run() {
        for(element in array){
            print(element.toString() + " ")
        }
    }
}

fun main(args : Array<String>){
    val ADT : IntArray = intArrayOf(12,54,22,6,87,4,33,51,23,11,8,65,78,5,34,987,98765,23,6676523);
    /*var threadpool : ExecutorService = Executors.newFixedThreadPool(1);
    threadpool.execute(Multiply(ADT,7))
    threadpool.execute(Sort(ADT))
    threadpool.execute(Display(ADT))
    threadpool.shutdown()*/
    val multiply = Multiply(ADT,7)
    val sort = Sort(ADT)
    val display = Display(ADT)
    multiply.start()
    multiply.join()
    sort.start()
    sort.join()
    display.start()
    display.join()
}
