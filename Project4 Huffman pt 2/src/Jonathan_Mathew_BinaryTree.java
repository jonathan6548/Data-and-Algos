import java.io.FileWriter;
import java.io.IOException;

public class Jonathan_Mathew_BinaryTree {

    Jonathan_Mathew_TreeNode root;
    Jonathan_Mathew_TreeNode trueRoot;

    public Jonathan_Mathew_BinaryTree(){
        root = new Jonathan_Mathew_TreeNode(null, -1, null, null, null, null);
        trueRoot = root;
    }

    public boolean isLeaf(Jonathan_Mathew_TreeNode node){

        return node.right == null && node.left == null;
    }
    public void setRoot(Jonathan_Mathew_TreeNode node) {
        root = node;
    }

    public Jonathan_Mathew_TreeNode getRoot(){
        return root;
    }


    public void preOrderTraversal(Jonathan_Mathew_TreeNode curr, FileWriter outFile) throws IOException {
        if (isLeaf(curr)) {
            curr.printNode(curr, outFile);
            outFile.write("\n");
        } else {
            curr.printNode(curr, outFile);
            outFile.write("\n");
            preOrderTraversal(curr.left, outFile);
            preOrderTraversal(curr.right, outFile);

        }
    }

    public void inOrderTraversal(Jonathan_Mathew_TreeNode curr, FileWriter outFile) throws IOException {
        if (isLeaf(curr)){
            curr.printNode(curr,outFile);
            return;
        }
        inOrderTraversal(curr.left, outFile);
        outFile.write("\n");
        curr.printNode(curr,outFile);
        outFile.write("\n");
        inOrderTraversal(curr.right, outFile);

    }

    public void postOrderTraversal(Jonathan_Mathew_TreeNode curr, FileWriter outFile) throws IOException {
        if (isLeaf(curr)){
            curr.printNode(curr,outFile);
            return;
        }
        postOrderTraversal(curr.left, outFile);
        outFile.write("\n");
        postOrderTraversal(curr.right, outFile);
        outFile.write("\n");
        curr.printNode(curr,outFile);
    }
}
