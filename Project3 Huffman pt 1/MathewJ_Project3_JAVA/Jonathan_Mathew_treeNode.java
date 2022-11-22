import java.io.FileWriter;
import java.io.IOException;

public class Jonathan_Mathew_treeNode {

    String chStr;
    int frequency;
    String code;
    Jonathan_Mathew_treeNode left;
    Jonathan_Mathew_treeNode right;
    Jonathan_Mathew_treeNode next;

    public Jonathan_Mathew_treeNode(String chStr, int frequency, String code, Jonathan_Mathew_treeNode left, Jonathan_Mathew_treeNode right, Jonathan_Mathew_treeNode next){

        this.chStr = chStr;
        this.frequency = frequency;
        this.code = code;
        this.left = left;
        this.right = right;
        this.next = next;


    }

    public void printNode(Jonathan_Mathew_treeNode node, FileWriter output) throws IOException {
        String nextchar;
        String Rchar;
        String Lchar;

        if (node.next == null) {
            nextchar = "NULL";
        }else{
            nextchar = node.next.chStr;
        }
        if (node.right == null) {
            Rchar = "NULL";
        } else {
            Rchar = node.right.chStr;
        }

        if (node.left == null) {
            Lchar = "NULL";
        } else {
            Lchar = node.left.chStr;
        }

        output.write("(" + node.chStr + "," + node.frequency + "," + node.code +  "," + nextchar +  "," + Lchar + "," + Rchar +  ")");


    }



}
