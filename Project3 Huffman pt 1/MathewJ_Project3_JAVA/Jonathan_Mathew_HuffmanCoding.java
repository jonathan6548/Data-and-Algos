import java.io.*;

public class Jonathan_Mathew_HuffmanCoding {

    public int[] charCountAry;
    String[] charCode;
    public Jonathan_Mathew_linkedList LL;
    public Jonathan_Mathew_BinaryTree biTree;


    public Jonathan_Mathew_HuffmanCoding(){
        int[] charCountAry = new int[256];
//        Arrays.fill(charCountAry, 0);
        String[] charCode = new String[256];
        this.LL = new Jonathan_Mathew_linkedList();
        this.biTree = new Jonathan_Mathew_BinaryTree();
    }

    public void computeCharCounts(File inFile) throws IOException {

        int[] input = new int[256];
        FileReader fr=new FileReader(inFile);   //reads the file
        BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
        String line;

        while((line=br.readLine())!=null){
            char[] ch = line.toCharArray();
            for(char c : ch){
                input[c]++;
            }
        }

        charCountAry = input;
    }

    public void printCountAry(FileWriter outputFile) throws IOException {

        for(int i=0; i<charCountAry.length;i++){

            if(charCountAry[i]>0){
                outputFile.write((char) i + "  " + charCountAry[i] + "\n");
            }

        }
    }


    public void constructHuffmanLList(int[] charArray, FileWriter outFile) throws IOException {

        for (int index = 0; index < 256; index++) {
            if (this.charCountAry[index] > 0) {
                String chr = Character.toString((char) index);
                int freq = this.charCountAry[index];
                Jonathan_Mathew_treeNode newNode = new Jonathan_Mathew_treeNode(chr, freq, "", null, null, null);
                LL.insertNewNode(LL.getHead(), newNode);
                LL.printList(outFile);
                outFile.write("\n\n");
            }
        }

    }

    public void constructHuffmanBinTree(Jonathan_Mathew_treeNode listHead, FileWriter outputFile) throws IOException {

        while(listHead.next!=null && listHead.next.next != null){
            int freq = listHead.next.frequency + listHead.next.next.frequency;
            String chr = listHead.next.chStr + listHead.next.next.chStr;
            Jonathan_Mathew_treeNode newNode = new Jonathan_Mathew_treeNode(chr, freq, "", listHead.next, listHead.next.next, null);
            LL.insertNewNode(listHead, newNode);
            LL.printList(outputFile);
            outputFile.write("\n\n");
            listHead.next= listHead.next.next.next;
        }
        biTree.setRoot(listHead.next);
    }

}

