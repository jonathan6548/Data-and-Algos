import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Jonathan_Mathew_Main {

    public static void main(String[] args) throws IOException {

        File inFile = new File(args[0]);
        File out = new File(args[1]);

        FileWriter outFile = new FileWriter(out);

        Jonathan_Mathew_HuffmanCoding proj3 = new Jonathan_Mathew_HuffmanCoding();
        proj3.computeCharCounts(inFile);

        outFile.write("******************--PRINTING COUNT ARRAY--********************\n");
        proj3.printCountAry(outFile);
        outFile.write("\n\n******************--END OF COUNT ARRAY--********************\n\n");

        outFile.write("\n\n******************--PRINTING HUFFMAN LIST--********************\n");
        proj3.constructHuffmanLList(proj3.charCountAry, outFile);
        outFile.write("\n\n******************--END OF HUFFMAN LIST--********************\n");

        outFile.write("\n\n******************--PRINTING HUFFMAN TREE--********************\n");
        proj3.constructHuffmanBinTree(proj3.LL.getHead(), outFile);
        outFile.write("\n\n******************--PRINTING HUFFMAN TREE--********************\n");

        outFile.write("\n\n******************--PRINTING LINKED LIST--********************\n");
        proj3.LL.printList(outFile);
        outFile.write("\n\n*******************--END OF LINKED LIST--********************\n\n");

        outFile.write("\n\n******************--PRINTING PRE-ORDER TRAVERSAL--********************\n");
        proj3.biTree.preOrderTraversal(proj3.biTree.getRoot(), outFile);
        outFile.write("\n\n*******************--END OF PRE-ORDER TRAVERSAL--********************\n\n");

        outFile.write("\n\n******************--PRINTING IN-ORDER TRAVERSAL--********************\n");
        proj3.biTree.inOrderTraversal(proj3.biTree.getRoot(), outFile);
        outFile.write("\n\n*******************--END OF IN-ORDER TRAVERSAL--********************\n\n");

        outFile.write("\n\n******************--PRINTING POST-ORDER TRAVERSAL--********************\n");
        proj3.biTree.postOrderTraversal(proj3.biTree.getRoot(), outFile);
        outFile.write("\n\n*******************--END OF POST-ORDER TRAVERSAL--********************\n\n");

        outFile.close();




    }
}

