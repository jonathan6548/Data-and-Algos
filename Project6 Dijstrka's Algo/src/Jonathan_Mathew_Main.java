import java.io.*;

public class Jonathan_Mathew_Main {

    public static void main(String[] args) throws IOException {

        File inFile = new File(args[0]);

        int numNodes = getNumNodes(inFile);

        File SSS = new File(args[1]);
        File out = new File(args[2]);

        FileWriter SSSFile = new FileWriter(SSS);
        FileWriter outFile = new FileWriter(out);

        Jonathan_Mathew_DijktraSSS proj6 = new Jonathan_Mathew_DijktraSSS(numNodes);

        //step 1
        proj6.numNodes = numNodes;
        proj6.loadCostMatrix(inFile);
        proj6.printCostMatrix(outFile);
        proj6.sourceNode = 1;

        while(proj6.sourceNode <= proj6.numNodes) {
//            System.out.print("sourceNode is: "+ proj6.sourceNode);
            outFile.write("\n\n");
            outFile.write("++++++++++SOURCE NODE IS:" + proj6.sourceNode + "++++++++++\n");
            //step 2

            proj6.currentNode = proj6.sourceNode;
            proj6.setArrays();
            proj6.debugPrint(outFile);

            while (!proj6.doneToDo()) {
                //step 3
                proj6.minNode = proj6.findMinNode();
//                System.out.println("min node is" + proj6.minNode);
                proj6.todo[proj6.minNode] = 0;
                proj6.debugPrint(outFile);

                //step 4
                proj6.currentNode = 1;


                while (proj6.currentNode <= proj6.numNodes) {

                    //step 5
                    if (proj6.todo[proj6.currentNode] > 0) {
                        proj6.newCost = proj6.computeCost();
//                        System.out.println("new Cost is " + proj6.newCost);
//                        System.out.println("best[curr]" + proj6.best[proj6.currentNode]);
//                        System.out.println();
                        if (proj6.newCost < proj6.best[proj6.currentNode]) {
//                            System.out.println("curr node is" + proj6.currentNode);
                            proj6.best[proj6.currentNode] = proj6.newCost;
                            proj6.father[proj6.currentNode] = proj6.minNode;
                            proj6.debugPrint(outFile);
                        }
                    }

//                  step 6
                    proj6.currentNode++;
                }
            }

//            step 9
            proj6.currentNode = 1;

            SSSFile.write("\n++++++++Source Node " + proj6.sourceNode + "++++++++++\n");
            while (proj6.currentNode <= proj6.numNodes) {
                //step 10

                proj6.printShortPath(SSSFile, outFile);
                //step 11
                proj6.currentNode++;
            }

            //step 13
            proj6.sourceNode++;
        }
        //end while


        outFile.close();
        SSSFile.close();
    }

    public static int getNumNodes(File inFile) throws IOException{

        FileReader fr=new FileReader(inFile);   //reads the file
        BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
        String line = br.readLine();

        if(line!=null){
            return Integer.parseInt(line);
        }
        System.out.println("COULDNT PRINT NUM NODES");
        return 0;
    }




}
