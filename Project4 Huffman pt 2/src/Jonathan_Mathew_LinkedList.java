import java.io.FileWriter;
import java.io.IOException;

public class Jonathan_Mathew_LinkedList {

    Jonathan_Mathew_TreeNode listHead;


    public Jonathan_Mathew_LinkedList(){
        listHead = new Jonathan_Mathew_TreeNode("dummy", 0, "", null, null, null);
    }

    public Jonathan_Mathew_TreeNode getHead() {
        return listHead;
    }


    public Jonathan_Mathew_TreeNode findSpot(Jonathan_Mathew_TreeNode listHead, Jonathan_Mathew_TreeNode newNode){

        Jonathan_Mathew_TreeNode spot = listHead;
        while(spot.next!=null && spot.next.frequency < newNode.frequency){
            spot=spot.next;
        }

        return spot;
    }

    public void insertNewNode(Jonathan_Mathew_TreeNode listHead, Jonathan_Mathew_TreeNode newNode){

        Jonathan_Mathew_TreeNode spot = findSpot(listHead, newNode);

        newNode.next = spot.next;
        spot.next = newNode;

    }

    public void printList(FileWriter outputFile) throws IOException {

        Jonathan_Mathew_TreeNode curr = this.listHead;

        while(curr != null){
            curr.printNode(curr, outputFile);
            curr = curr.next;
            outputFile.write("---->\n");
        }

        outputFile.write(" NULL\n");

    }



}
