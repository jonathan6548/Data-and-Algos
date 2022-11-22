import java.io.*;
import java.util.Scanner;

public class Jonathan_Mathew_HuffmanCoding {

    public int[] charCountAry;
    String[] charCode;
    public Jonathan_Mathew_LinkedList LL;
    public Jonathan_Mathew_BinaryTree biTree;


    public Jonathan_Mathew_HuffmanCoding(){
        charCountAry = new int[256];
        charCode = new String[256];
        this.LL = new Jonathan_Mathew_LinkedList();
        this.biTree = new Jonathan_Mathew_BinaryTree();

//        Arrays.fill(charCode, "");
    }

    public void computeCharCounts(File inFile) throws IOException {

//        int[] input = new int[256];
        FileReader fr=new FileReader(inFile);   //reads the file
        BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
        String line;

        while((line=br.readLine())!=null){
            char[] ch = line.toCharArray();
            for(char c : ch){
                charCountAry[c]++;
            }
        }

    }

    public void printCountAry(FileWriter outputFile) throws IOException {

        for(int i=0; i<charCountAry.length;i++){

            if(charCountAry[i]>0){
                outputFile.write(i + "  " +  (char) i + "  " + charCountAry[i] + "\n");
            }

        }
    }


    public void constructHuffmanLList(int[] charArray, FileWriter outFile) throws IOException {

        for (int index = 0; index < 256; index++) {
            if (this.charCountAry[index] > 0) {
                String chr = Character.toString((char) index);
                int freq = this.charCountAry[index];
                Jonathan_Mathew_TreeNode newNode = new Jonathan_Mathew_TreeNode(chr, freq, "", null, null, null);
                LL.insertNewNode(LL.getHead(), newNode);
                LL.printList(outFile);
                outFile.write("\n\n");
            }
        }

    }

    public void constructHuffmanBinTree(Jonathan_Mathew_TreeNode listHead, FileWriter outputFile) throws IOException {

        while(listHead.next!=null && listHead.next.next != null){
            int freq = listHead.next.frequency + listHead.next.next.frequency;
            String chr = listHead.next.chStr + listHead.next.next.chStr;
            Jonathan_Mathew_TreeNode newNode = new Jonathan_Mathew_TreeNode(chr, freq, "", listHead.next, listHead.next.next, null);
            LL.insertNewNode(listHead, newNode);
            LL.printList(outputFile);
            outputFile.write("\n\n");
            listHead.next= listHead.next.next.next;
        }
        biTree.setRoot(listHead.next);
    }


    public void constructCharCode(Jonathan_Mathew_TreeNode t, String code){
        if(biTree.isLeaf(t)){
            t.code = code;
//            System.out.println("t.code is: " + code);
            int index = t.chStr.charAt(0);
//            System.out.println("index is: " + index);
            charCode[index] = code;
        }else{

            this.constructCharCode(t.left, code + "0");
            this.constructCharCode(t.right, code + "1");
        }

    }

    public void encode(File inFile, FileWriter compFile, FileWriter outputFile) throws IOException {

        String code;
        int index;
        FileReader fr=new FileReader(inFile);   //reads the file
        BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
        String line;

        while((line=br.readLine())!=null){
            char[] ch = line.toCharArray();
            for(char c : ch){
                index = c;
                code = this.charCode[index];
                if(code!=null){
                    outputFile.write("char: "+ c + "    index: " + index + "   code: " + code + "\n");
                    compFile.write(code);
                }


            }
        }

    }


    public void decode(FileReader compFile, FileWriter decompFile) throws IOException {

        Jonathan_Mathew_TreeNode spot = biTree.getRoot();
        int oneBit;

        BufferedReader br=new BufferedReader(compFile);  //creates a buffering character input stream

//        String line;
//        while((line=br.readLine())!=null) {
//            char[] ch = line.toCharArray();
//            for(char c : ch) {
//                oneBit = c;
//                if (biTree.isLeaf(spot)) {
//                    decompFile.write(spot.chStr);
//                    spot = biTree.getRoot();
//                }
//
//                System.out.println("OneBit is:    "+ oneBit);
//                if (oneBit == '0') {
//                    spot = spot.left;
//                } else if (oneBit == '1') {
//                    spot = spot.right;
//                } else if(oneBit == 10){
//                    decompFile.write("\n");
//                }else{
//                    System.out.println();
//                }
//            }
//
//        }


        while((oneBit = br.read()) != -1){

            if (biTree.isLeaf(spot)) {
                decompFile.write(spot.chStr);
                spot = biTree.getRoot();
            }

//            System.out.println("OneBit is:    "+ oneBit);
            if (oneBit == '0') {
                spot = spot.left;
            } else if (oneBit == '1') {
                spot = spot.right;
            } else if(oneBit == 10 ){
                decompFile.write("\n");
            } else {
                System.out.println("Error! The compress file contains invalid character!");
            }
        }

        if(!biTree.isLeaf(spot))
            System.out.println("Error: The compress file is corrupted!");


    }


    public void userInterface(Jonathan_Mathew_TreeNode root, FileWriter outFile) throws IOException {

        String nameOrg;
        String nameComp;
        String nameDecomp;

        Scanner userInput = new Scanner(System.in);
        System.out.println("Would you like to compress a file? Y/N?");

        String User= userInput.nextLine();
        if(User.equals("N")){
            return;
        }else if(!User.equals("Y")){
            while(!User.equals("Y")){
                System.out.println("Would you like to compress a file? Y/N?");
                User = userInput.nextLine();
                if(User.equals("N")){
                    return;
                }
            }

        }

        userInput = new Scanner(System.in);
        System.out.println("what is the name of the file to be compressed without extensions (.txt)");

        nameOrg = userInput.nextLine();;
        nameComp = nameOrg + "_Compressed.txt";
        nameDecomp = nameOrg + "_DeCompressed.txt";
        nameOrg = nameOrg + ".txt";

        File orgFile = new File(nameOrg);
        FileWriter compF = new FileWriter(nameComp);
        FileWriter decompFile = new FileWriter(nameDecomp);

        encode(orgFile, compF, outFile);

        compF.close();

        FileReader compFile = new FileReader(nameComp);
        decode(compFile, decompFile);

        decompFile.close();
        compFile.close();





    }
}

