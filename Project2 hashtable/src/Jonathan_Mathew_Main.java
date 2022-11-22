import java.io.*;

public class Jonathan_Mathew_Main {

    public static void main(String[] args) throws IOException {
        File inFile = new File(args[0]);
        int bucketSize = Integer.parseInt(args[1]);
        File out1 = new File(args[2]);
        File out2 = new File(args[3]);
        FileWriter outFile1 = new FileWriter(out1);
        FileWriter outFile2 = new FileWriter(out2);

        Jonathan_Mathew_hashTable proj2 = new Jonathan_Mathew_hashTable(bucketSize);
        proj2.informationProcessing(inFile, outFile2);
        proj2.printHashTable(outFile1);
        outFile1.close();
        outFile2.close();
    }
}
