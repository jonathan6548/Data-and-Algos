import java.io.*;
import java.util.Arrays;

public class Jonathan_Mathew_DijktraSSS {

    public int numNodes;
    public int sourceNode;
    public int minNode;
    public int currentNode;
    public int newCost;
    public int[][] costMatrix;
    public int[] father;
    public int[] todo;
    public int[] best;


//    public DijktraSSS(int n,int s, int m, int c, int ncost){
    public Jonathan_Mathew_DijktraSSS(int n){

        numNodes = n;
//        sourceNode = s;
//        minNode = m;
//        currentNode = c;
//        newCost = ncost;
        costMatrix = new int[n+1][n+1];
        for(int i=0;i<n+1;i++){
            for(int j=0;j<n+1;j++){
                if(i==j){
                    costMatrix[i][j]=0;
                }else{
                    costMatrix[i][j]=9999;
                }
            }
        }

        father = new int[n+1];
        todo = new int [n+1];
        best = new int[n+1];
    }

    public void loadCostMatrix(File inFile) throws IOException {

        FileReader fr=new FileReader(inFile);   //reads the file
        BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
        String line= br.readLine();
//        System.out.println("first print out is:"+ line);


        while((line= br.readLine()) != null){
            String[] splitArray = line.split("\\s+");
//            System.out.println(line);
            costMatrix[Integer.parseInt(splitArray[0])][Integer.parseInt(splitArray[1])]= Integer.parseInt(splitArray[2]);
        }
    }

    public void printCostMatrix(FileWriter outFile) throws IOException {

        for (int i = 1; i < costMatrix.length; i++) {
            for (int j = 1; j < costMatrix[0].length; j++) {
//                System.out.println(costMatrix[i][j]);
                outFile.write(costMatrix[i][j] + "  ");
            }
            outFile.write("\n");
        }
    }

    public void setArrays(){
        setBest();
        setFather();
        setTodo();
    }

    public void setBest(){


        for (int i = 1; i <= numNodes; i++) {
            best[i] = costMatrix[sourceNode][i];
        }

    }

    public void setFather(){
        Arrays.fill(father, sourceNode);
    }

    public void setTodo(){

        for(int i=0; i<todo.length; i++){
            if(i==sourceNode){
                todo[i]=0;
            }
            todo[i]=1;
        }
    }

    public int findMinNode(){

        int minCost  = 99999;
        int minNode = 0;
        int i=1;
        while(i<= numNodes) {
            if (todo[i] > 0) {
                if (best[i] < minCost) {
                    minCost = best[i];
                    minNode = i;
                }
            }
            i++;
        }

        return minNode;


    }

    public int computeCost(){

        return best[minNode] + costMatrix[minNode][currentNode];
    }

    public void debugPrint( FileWriter outFile) throws IOException {

        outFile.write("====================================================== \n");
        outFile.write("The Source node is:" + sourceNode + "\n");
        outFile.write("Best Array is: \n");
        printArray(best, outFile);
        outFile.write("father Array is: \n");
        printArray(father, outFile);
        outFile.write("todo Array is: \n");
        printArray(todo, outFile);
        outFile.write("====================================================== \n");


    }

    public boolean doneToDo(){

        for(int i: todo){
            if(i!=0)
                return false;
        }
        return true;
    }

    public void printShortPath(FileWriter SSS, FileWriter outFile) throws IOException {

        int curr = currentNode;
        SSS.write("The shortest path from " + sourceNode + " to " + curr +":\n\t");

        while(sourceNode != father[curr]){
            SSS.write(curr + "<-----");
            curr = father[curr];
        }
        SSS.write(curr + "<----" + sourceNode);
        SSS.write("\n\tcost: " + best[currentNode]);

        SSS.write("\n");

    }

    void printArray(int[] num, FileWriter outFile) throws IOException {

       for(int i=1; i<num.length;i++)
        outFile.write(num[i] + " ");
       outFile.write("\n");
    }


}
