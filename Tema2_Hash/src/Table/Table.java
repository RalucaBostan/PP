package Table;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;

public class Table {
    public String[][] hashTable;
    public final int hashDimension = 100;

    public Table(){
        hashTable = new String[hashDimension][20];
        for(int i = 0; i  < hashDimension;i++){
            for(int j = 0;j < 20; j++) {
                hashTable[i][j] = "";
            }
        }
    }

    public static int HashFunction(String token){
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();
        Value result = polyglot.eval("python", "sum(pow(ord(ch),2) + 2 * ord(ch) - 5 for ch in '" + token + "')");
        return result.asInt();
    }


    public static void InsertHash(Table hash,String exp){
        boolean found = false;
        int index = 0 ;
        int hashCode = hash.HashFunction(exp);
        for(int i = 0; i < hash.hashDimension;i++) {
            if (hash.hashTable[i][0] == ""){
                break;
            }
            if (Integer.parseInt(hash.hashTable[i][0]) == hashCode) {
                found = true;
                for(int j = 1; j < 20;j++){
                    if(hash.hashTable[i][j] == exp) {
                        break;
                    }else
                    if (hash.hashTable[i][j] == "") {
                        hash.hashTable[i][j] = exp;
                        break;
                    }
                }
                break;
            }
        }
        if(found == false){
            for(int i = 0; i < hash.hashDimension;i++){
                if (hash.hashTable[i][0] == ""){
                    hash.hashTable[i][0] = hashCode + "";
                    for(int j = 1; j < 20;j++){
                        if(hash.hashTable[i][j] == ""){
                            hash.hashTable[i][j] = exp;
                            break;
                        }
                    }
                    break;
                }
            }
        }
    }

    public void DisplayHash(Table hash){
        for(int i = 0; i < hashDimension;i++){
            if(hash.hashTable[i][0] != "") {
                System.out.print(i + 1 + ". Code : " + hash.hashTable[i][0] + " Text : ");
            }
            else{
                break;
            }
            for(int j = 1; j < 20;j++){
                if(hash.hashTable[i][j] != "0") {
                    System.out.print(hash.hashTable[i][j] + " ");
                }
                else{
                    break;
                }
            }
            System.out.print("\n");
        }
    }

    public void BubbleSort(Table hash){
        for(int i = 0;i < hash.hashDimension;i++){
            for(int j = i;j < hashDimension-1;j++){
                if(hash.hashTable[i][0] != "" && hash.hashTable[j][0] != "") {
                    if (Integer.parseInt(hash.hashTable[i][0]) > Integer.parseInt(hash.hashTable[j][0])) {
                        String[] aux = hash.hashTable[i];
                        hash.hashTable[i] = hash.hashTable[j];
                        hash.hashTable[j] = aux;
                    }
                }
                else{
                    break;
                }
            }
        }
    }

    public void DisplaySameSum(Table hash){
        for(int i = 0; i < hash.hashDimension;i++){
            if(hash.hashTable[i][0] != "0"){
                if(hash.hashTable[i][2] != "") {
                    System.out.print("Code : " + hash.hashTable[i][0] + " Text : ");
                    for (int j = 1; j < 20; j++) {
                        if (hash.hashTable[i][j] != "") {
                            System.out.print(hash.hashTable[i][j] + " ");
                        }
                        else{
                            System.out.print("\n");
                            break;
                        }
                    }
                }
            }
            else{
                break;
            }
        }
    }
}