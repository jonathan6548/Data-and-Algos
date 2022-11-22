import java.io.FileWriter;
import java.io.IOException;

public class Jonathan_Mathew_linkedList {

    Jonathan_Mathew_treeNode listHead;


    public Jonathan_Mathew_linkedList(){
        listHead = new Jonathan_Mathew_treeNode("dummy", 0, "", null, null, null);
    }

    public Jonathan_Mathew_treeNode getHead() {
        return listHead;
    }


    public Jonathan_Mathew_treeNode findSpot(Jonathan_Mathew_treeNode listHead, Jonathan_Mathew_treeNode newNode){

        Jonathan_Mathew_treeNode spot = listHead;
        while(spot.next!=null && spot.next.frequency < newNode.frequency){
            spot=spot.next;
        }

        return spot;
    }

    public void insertNewNode(Jonathan_Mathew_treeNode listHead, Jonathan_Mathew_treeNode newNode){

        Jonathan_Mathew_treeNode spot = findSpot(listHead, newNode);

        newNode.next = spot.next;
        spot.next = newNode;

    }

    public void printList(FileWriter outputFile) throws IOException {

        Jonathan_Mathew_treeNode curr = this.listHead;

        while(curr != null){
            curr.printNode(curr, outputFile);
            curr = curr.next;
            outputFile.write("---->\n");
        }

        outputFile.write(" NULL\n");

    }



}
