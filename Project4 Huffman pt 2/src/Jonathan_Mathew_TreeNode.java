import java.io.FileWriter;
import java.io.IOException;

public class Jonathan_Mathew_TreeNode {

    String chStr;
    int frequency;
    String code;
    Jonathan_Mathew_TreeNode left;
    Jonathan_Mathew_TreeNode right;
    Jonathan_Mathew_TreeNode next;

    public Jonathan_Mathew_TreeNode(String chStr, int frequency, String code, Jonathan_Mathew_TreeNode left, Jonathan_Mathew_TreeNode right, Jonathan_Mathew_TreeNode next){

        this.chStr = chStr;
        this.frequency = frequency;
        this.code = code;
        this.left = left;
        this.right = right;
        this.next = next;


    }

    public void printNode(Jonathan_Mathew_TreeNode node, FileWriter output) throws IOException {
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
