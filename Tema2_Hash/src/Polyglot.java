import Table.Table;
import org.graalvm.polyglot.*;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class Polyglot {
    private static String RToUpper(String token){
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();
        Value result = polyglot.eval("R", "toupper(\""+token+"\");");
        return result.asString();
    }


    public static void main(String[] args){
        Context polyglot = Context.create();
        Table hashTable = new Table();
        Value array = polyglot.eval("js","[\"CA\",\"AC\",\"If\",\"we\",\"run\",\"the\",\"java\",\"command\",\"included\",\"in\",\"GraalVM\",\"we\",\"will\",\"be\",\"automatically\",\"using\",\"the\",\"Graal\",\"JIT\",\"compiler\",\"no\",\"extra\",\"configuration\",\"is\",\"needed\",\"I\",\"will\",\"use\",\"the\",\"time\",\"command\", \"to\",\"get\",\"the\",\"real\",\"wall\",\"clock\",\"elapsed\",\"time\",\"it\",\"takes\",\"to\",\"run\",\"the\",\"entire\",\"program\",\"from\",\"start\",\"to\",\"finish\",\"rather\",\"than\",\"setting\",\"up\",\"a\",\"complicated\",\"micro\",\"benchmark\",\"and\",\"I\",\"will\",\"use\",\"a\",\"large\",\"input\",\"so\",\"that\",\"we\",\"arent\",\"quibbling\",\"about\",\"a\",\"few\",\"seconds\",\"here\",\"or\",\"there\",\"The\",\"large.txt\",\"file\",\"is\",\"150\",\"MB\"];");
        String[] strings = new String[(int)array.getArraySize()];
        for(int i = 0 ; i < array.getArraySize();i++){
            String element = array.getArrayElement(i).asString();
            //element = element.substring(1,element.length()-1);
            System.out.println(element);
            String upper = RToUpper(element);
            hashTable.InsertHash(hashTable,upper);
        }
        hashTable.BubbleSort(hashTable);
        hashTable.DisplayHash(hashTable);
        System.out.print("\n\n");
        System.out.println("Same Code");
        hashTable.DisplaySameSum(hashTable);
    }

}