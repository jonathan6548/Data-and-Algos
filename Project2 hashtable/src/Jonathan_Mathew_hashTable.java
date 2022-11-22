import java.io.*;

public class Jonathan_Mathew_hashTable {

    char op;
    String data;
    int bucketSize;
    Jonathan_Mathew_listNode[] hashTable;

    public Jonathan_Mathew_hashTable(int bucketSize){
        this.bucketSize = bucketSize;
        this.hashTable = new Jonathan_Mathew_listNode[bucketSize];

        for(int i=0;i<bucketSize;i++){
            Jonathan_Mathew_listNode node = new Jonathan_Mathew_listNode("dummy");
            hashTable[i] = node;
        }https://d2vlcm61l7u1fs.cloudfront.net/media%2F30d%2F30d6127e-db97-4165-8550-90a26997a5d6%2Fphp5hd0ha.png

    }

    public int Doit(String data){

            long value = 1;
//           int value = 1;
            for(int i=0; i<data.length(); i++){
                char oneCh = data.charAt(i);
                value = value * 32 + (int) oneCh;
            }

//        System.out.println("value is:" + value);
            long spot = value % bucketSize;
            return (int)spot;
    }

    public Jonathan_Mathew_listNode findSpot(int index, String data){

        Jonathan_Mathew_listNode spot = hashTable[index];

        while(spot.next != null && spot.next.data.compareTo(data)<0)
            spot=spot.next;

        return spot;
    }

    public void hashInsert(int index, String data, FileWriter outFile2) throws IOException {

        Jonathan_Mathew_listNode spot = findSpot(index, data);
        outFile2.write("*** Inside hashInsert method. Performing hashInsert \n");
        if(spot.next !=null && data.equals(spot.next.data)){
//            System.out.println("*** Warning, data is already in the database!");
            outFile2.write("*** Warning, data is already in the database! \n");
        }else{
            Jonathan_Mathew_listNode newNode = new Jonathan_Mathew_listNode(data);
            newNode.next = spot.next;
            spot.next = newNode;
            //output something from the file;
            outFile2.write("After hashInsert operation ... \n");
            printList(index, outFile2);
        }

    }



    public void hashDelete(int index, String data, FileWriter outFile2) throws IOException {

        //output message here
        Jonathan_Mathew_listNode spot = findSpot(index, data);
        outFile2.write("** Inside hashDelete method. Performing hashDelete  \n");
        if(spot.next == null || !spot.next.data.equals(data) ){
//            System.out.println("*** Warning: data is *not* in the database!");
            outFile2.write("*** Warning: data is *not* in the database! \n");
        }else{
            Jonathan_Mathew_listNode temp = spot.next;
            spot.next =temp.next;
            temp.next =null;
            outFile2.write("After hashDelete operation ... \n");
            printList(index, outFile2);
        }
    }

    public void hashRetrieval(int index, String data, FileWriter outFile2) throws IOException {

        //output file here
        Jonathan_Mathew_listNode spot = findSpot(index, data);
        outFile2.write("** Inside hashRetrieval. Performing hashRetrieval \n");
        if(spot.next == null || ! spot.next.data.equals(data)){
            //output file here
//            System.out.println("*** Warning, the record is *not* in the database!");
            outFile2.write("*** Warning, the record is *not* in the database! \n");
        }else{
            //outputfile here
//            System.out.println("Yes, the record is in the database!");
            outFile2.write("Yes, the record is in the database! \n");
        }

    }

    private void printList(int index, FileWriter outFile2) throws IOException {

        Jonathan_Mathew_listNode print = hashTable[index];
        StringBuilder pl=new StringBuilder();
        Jonathan_Mathew_listNode temp = print;
        outFile2.write("HashTable["+ index + "]: ");
        while(print.next !=null){

//            System.out.print(print.data);
//            System.out.print("-->");
            pl.append("(").append(print.data).append(",").append(print.next.data).append(")--->");
            temp=print;
            print=print.next;

        }
        if(temp.next != null)
            temp = temp.next;
        pl.append("(").append(temp.data).append(",NULL)");
        outFile2.write(String.valueOf(pl.append("\n")));

//        System.out.println("NULL");

//        System.out.println("*************************");
    }

    public void printHashTable(FileWriter outFile1) throws IOException {

        for(int i=0; i<hashTable.length;i++){


            printList(i,outFile1);

        }

    }


    public void informationProcessing(File inFile, FileWriter outFile2) throws IOException {

        char op; //get from file
        String data;//get from file

        //print index here

//        printList(index, outfile);
        FileReader fr=new FileReader(inFile);   //reads the file
        BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
        //constructs a string buffer with no characters
        String line;
        outFile2.write("Enter informationProcessing method \n");
        while((line=br.readLine())!=null){

            outFile2.write("input is:   " + line+ "\n");
//            System.out.println("line data is:========   " + line);
            op = line.charAt(0);
            data = line.substring(1).trim();
            int index = Doit(data);
//            System.out.println("data is: "+ data);
//            System.out.println(index);
            outFile2.write("index for data is:  " + index + "\n");
            outFile2.write("linked list before insertion: \n");
            printList(index, outFile2);
            if(op == '+') {
                hashInsert(index, data, outFile2);

            }else if(op == '-'){
                hashDelete(index, data, outFile2);

            }else if(op == '?'){
                hashRetrieval(index, data, outFile2);

            }else{
                outFile2.write(op + " is an unrecognizable operation!"+ "\n");
            }
            outFile2.write("\n \n");
        }

    }




}
