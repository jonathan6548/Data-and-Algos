#include <iostream>
#include <fstream>
using namespace std;


class uEdge{

    public:
        int Ni;
        int Nj;
        int cost;
        uEdge *next;
        

        uEdge(){
            this->Ni = 0;
            this->Nj = 0;
            this->cost = 0;
            this->next = NULL;
        }
        uEdge(int i, int j, int c){
            this->Ni = i;
            this->Nj = j;
            this->cost = c;
            this->next = NULL;
        }
        void printEdge(ofstream *outputFile){
           
           if(this->next ==NULL){
                *outputFile << "<" << this->Ni << "," << this->Nj << "," << this->cost << "," << "NULL" << ">" << endl;
                return;
           }else{
                *outputFile << "<" << this->Ni << "," << this->Nj << "," << this->cost<< ","<< this->next->Ni << "> --->";
           }

        }
};

class PrimMST{

    public:
        int numNodes,
            nodeInSetA,
            totalMSTCost;
        char *whichSet;
        uEdge *edgelistHead, *MSTlistHead; 



        PrimMST(int num){
            this->numNodes = num;
            this->nodeInSetA = -1;
            this->totalMSTCost = 0;
            this->whichSet = new char[num+1];

            for(int i=0; i<num+1;i++){
                this->whichSet[i]= 'B';
            }
            this->edgelistHead = new uEdge();
            this->MSTlistHead = new uEdge();
        
        }

        void listInsert(uEdge *edge){

            uEdge *curr = this->edgelistHead;

            while(curr->next != NULL && curr->next->cost < edge->cost){
                curr = curr->next;
            }
            edge->next = curr->next;
            curr->next = edge;
        }
        
        uEdge *removeEdge(){

            uEdge *curr = this->edgelistHead;
            while( 
                (curr->next->next != NULL) && 
                !(
                    this->whichSet[curr->next->Ni] != this->whichSet[curr->next->Nj] && 
                    (this->whichSet[curr->next->Ni] == 'A' || this->whichSet[curr->next->Nj] == 'A')
                )
            ){
                curr = curr->next;
            }
            uEdge *out = curr->next;
            curr->next = curr->next->next;
            out->next = NULL;
            return out;

        } 

        void addEdgetoMST(uEdge *edge){

            if(this->MSTlistHead->next==NULL){
                this->MSTlistHead->next = edge;
            }else{
                edge->next =  this->MSTlistHead->next;
                this->MSTlistHead->next = edge;
            }

        }

        void printSet(ofstream *outFile){
            
            *outFile << "[";
            for (int i = 1; i < this->numNodes +1; i++){
               
                *outFile << this->whichSet[i] << ",";
            }

             *outFile << "]" << endl;
            
        }

        void printEdgeList(ofstream *outFile){

            *outFile << "edgelistHead--> ";

            uEdge *curr = this->edgelistHead;

            while(curr != NULL){
                curr->printEdge(outFile);
                curr=curr->next;
            }

        }

        void printMSTList(ofstream *outFile){
            
            *outFile << "MSTlistHead--> ";

            uEdge *curr = this->MSTlistHead;

            while(curr != NULL){
                curr->printEdge(outFile);
                curr=curr->next;
            }
        }

        bool isEmpty(){

            for(int i = 0; i<this->numNodes+1; i++){

                if(this->whichSet[i]!='A')
                    return false;
            }

            return true;
        }

        void updateMST(uEdge *newEdge){

            this->addEdgetoMST(newEdge);
            this->totalMSTCost += newEdge->cost;
            if(this->whichSet[newEdge->Ni] == 'A'){
                this->whichSet[newEdge->Nj] = 'A';
            }else{
                this->whichSet[newEdge->Ni] = 'A';
            }
        }


};

int main(int argc, char *argv[]){

    ifstream inputFile;
    ofstream MSTFile;
    ofstream deBugFile;
    inputFile.open(argv[1]);
    MSTFile.open(argv[3]);
    deBugFile.open(argv[4]);

    int numNodes;
    inputFile >> numNodes;

    int node = atoi(argv[2]);

    PrimMST *proj7 = new PrimMST(numNodes);
    proj7->nodeInSetA = node;
    proj7->whichSet[0]= 'A';
    proj7->whichSet[proj7->nodeInSetA] = 'A';
    proj7->printSet(&deBugFile);


    int ni, nj, totCost;
    while (inputFile >> ni >> nj >> totCost){
       uEdge *edge = new uEdge(ni, nj, totCost);
       proj7->listInsert(edge);
       proj7->printEdgeList(&deBugFile);
    }
    



    while(!proj7->isEmpty()){

        deBugFile << "removed node: ";
        uEdge *nEdge = proj7->removeEdge();

        nEdge->printEdge(&deBugFile);
   
        proj7->updateMST(nEdge);

        proj7->printSet(&deBugFile);

        proj7->printEdgeList(&deBugFile);
        proj7->printMSTList(&deBugFile);
    }


    MSTFile << "*** Prim’s MST of the input graph, G is: ***" << endl;
    MSTFile << "NumNodes: " << numNodes<<endl;
    proj7->printMSTList(&MSTFile);
    MSTFile << " *** MST total cost = " << proj7->totalMSTCost<<endl;


    inputFile.close();
     MSTFile.close();
    deBugFile.close();
   



    return 0;
}