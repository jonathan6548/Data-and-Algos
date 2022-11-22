#include <iostream>
#include <algorithm>
#include <fstream>
using namespace std;


class QtNode{

    public:
        int color;
        int upperR;
        int upperC;
        int size;
        QtNode *NWkid;
        QtNode *NEkid;
        QtNode *SWkid;
        QtNode *SEkid;


        QtNode(int cl, int ur, int uc, int s){
            this->color = cl;
            this->upperR = ur;
            this->upperC = uc;
            this->size = s;
            this->NWkid = NULL;
            this->NEkid = NULL;
            this->SWkid = NULL;
            this->SEkid = NULL;
        }

        void printQtNode(ofstream *outFile){
            if (this->NWkid == NULL || this->NEkid == NULL || this->SWkid == NULL || this->SEkid == NULL){
                *outFile << "(" << this->color << "," << this->upperR << "," << this->upperC << ",NULL,NULL,NULL,NULL)" << endl;
                return;
            }else{
            *outFile << "(" << this->color << "," << this->upperR << "," << this->upperC << "," << this->NWkid->color << "," << this->NEkid->color << "," << this->SWkid->color << "," << this->SEkid->color << ")" << endl;
            }
        }

        void setColor(int c){
            this->color =c;
        }

        int getColor(){
            return this->color;
        }

};

class QuadTree{

    public:
        QtNode *QtRoot;
        int numRows;
        int numCol;
        int minVal;
        int maxVal;
        int power2Size;
        int **imgAry;
        int **newimgAry;


        QuadTree(int r, int c, int s){
            this->numRows = r;
            this->numCol = c;
            this->power2Size = s;
            this->imgAry = new int*[s];
            this->newimgAry = new int*[s];

            for (int i = 0; i < s; i++){
                this->imgAry[i] = new int[s];
                this->newimgAry[i] = new int[s];
            }

            


        }
        static int computePower2(int numr, int numc){
            int size = max(numr, numc);
            int power = 2;
            while(size > power){
                if(size>power){
                    power *=2;
                }
            }

            return power;
        }
        void zero2DArray(int **inputArray){
            for (int i = 0; i < this->power2Size; i++){
                for (int j = 0; j < this->power2Size; j++){
                    inputArray[i][j] = 0;
                }
            }
        }
        void loadImage(ifstream *inputFile, int **inputArray){
            
            int count=0;
            int size = this->numRows;
            int row = 0;
            string inputData;
            string trash;
            getline(*inputFile, trash);

            while(getline(*inputFile, inputData)){
                int col=0;
                for(int i=0; i<inputData.length(); i++){
                    
                    if(inputData[i] != ' '){
                        inputArray[row][col] = inputData[i] - 48;
                        col++;
                    }
                }
                row++;
            }

        }     
        // int addKidsColor(QtNode node){
        //     node->NWkid->getColor() + node->NEkid->getColor() + node->SWkid->getColor() + node->SEkid->getColor();
        // }
        QtNode *buildQuadTree(int **imgArray, int r, int c, int size, ofstream *outputFile){
             QtNode *newQtNode = new QtNode(-1, r, c, size);
             
             if (size ==1){
                newQtNode->setColor(imgArray[r][c]);
            }else{
                
                int halfSize = size/2;
                // cout<<"halfSize is: "<< halfSize<<endl;
                newQtNode->NWkid = buildQuadTree(imgArray, r, c, halfSize, outputFile);
                newQtNode->NEkid = buildQuadTree(imgArray, r, c+halfSize, halfSize, outputFile);
                newQtNode->SWkid = buildQuadTree(imgArray, r+halfSize, c, halfSize, outputFile);
                newQtNode->SEkid =buildQuadTree(imgArray, r+halfSize, c+halfSize, halfSize, outputFile);
                int sumColor = newQtNode->NWkid->getColor() + newQtNode->NEkid->getColor() + newQtNode->SWkid->getColor() + newQtNode->SEkid->getColor();
                // cout<<"sum color is: "<< sumColor<<endl;
                if(sumColor == 0){
                    newQtNode->setColor(0);
                    newQtNode->NWkid = NULL;
                    newQtNode->NEkid = NULL;
                    newQtNode->SWkid = NULL;
                    newQtNode->SEkid = NULL;
                }else if(sumColor == 1){
                    newQtNode->setColor(1);
                    newQtNode->NWkid = NULL;
                    newQtNode->NEkid = NULL;
                    newQtNode->SWkid = NULL;
                    newQtNode->SEkid = NULL;
                }else{
                    newQtNode->setColor(5);
                }
            }
            newQtNode->printQtNode(outputFile);

            return newQtNode;
        }
        bool isLeaf(QtNode *node){
            return node->NWkid == NULL && node->NEkid == NULL && node->SWkid == NULL && node->SEkid == NULL;
        }

        void preOrder(QtNode *node, ofstream *outputFile){
            if(isLeaf(node)){
                node->printQtNode(outputFile);
                return;
            }else{
                node->printQtNode(outputFile);
                this->preOrder(node->NWkid, outputFile);
                this->preOrder(node->NEkid, outputFile);
                this->preOrder(node->SWkid, outputFile);
                this->preOrder(node->SEkid, outputFile);
            }
        }

        void postOrder(QtNode *node, ofstream *outputFile){
            if (this->isLeaf(node)){
                node->printQtNode(outputFile);
            }else{
                this->postOrder(node->NWkid, outputFile);
                this->postOrder(node->NEkid, outputFile);
                node->printQtNode(outputFile);
                this->postOrder(node->SWkid, outputFile);
                this->postOrder(node->SEkid, outputFile);
            }
        }

        void getLeaf(QtNode *node, int **newimgAry){

            if(isLeaf(node) ){
                fillNewImgAry(node, newimgAry);
            }else{
                getLeaf(node->NWkid, newimgAry);
                getLeaf(node->NEkid, newimgAry);
                getLeaf(node->SWkid, newimgAry);
                getLeaf(node->SEkid, newimgAry);
            }
        }

        void fillNewImgAry(QtNode *node, int **newimgAry){
            int color, r, c, s;
            color = node->getColor();
            r = node->upperR;
            c = node->upperC;
            s = node->size;

            // color = node.getColor();
            // r = node.upperR;
            // c = node.color;
            // s = node.size;

            for (int i = r; i < r+s; i++){
                for(int j=c; j<c+s; j++){
                    // node->setColor(newimgAry[i][j]);
                    newimgAry[i][j] = color;
                    // cout<<"newimgAry"<< newimgAry[i][j]<<endl;

                }
            }
            
        }

};
int main(int argc, char *argv[]){

    ifstream inFile;
    ofstream outputFile1;
    ofstream outputFile2;
    ofstream outputFile3;

    inFile.open(argv[1]);
    outputFile1.open(argv[2]);
    outputFile2.open(argv[3]);
    outputFile3.open(argv[4]);

    string input;
    int row;
    int col;
    int min;
    int max;
    inFile >> row >> col >> min >> max;
    // cout<<"rows is: " << row <<endl;
    // cout<<"cols is: " << col <<endl;
    // cout<<"min is: " << min <<endl;
    // cout<<"max is: " << max <<endl;
    int pow2 = QuadTree::computePower2(row, col);
    outputFile2 << "The power2Size: " << pow2 << endl;
    QuadTree *tree = new QuadTree(row, col, pow2);
    tree->zero2DArray(tree->imgAry);
    tree->zero2DArray(tree->newimgAry);
    tree->loadImage(&inFile, tree->imgAry);
    QtNode *QtRoot = tree->buildQuadTree(tree->imgAry,0,0, pow2,&outputFile2);

    outputFile1 << "PRE order traversal: " <<endl;
    tree->preOrder(QtRoot, &outputFile1);
    outputFile1<<endl;
    outputFile1 << "POST order traversal: " <<endl;
    tree->postOrder(QtRoot, &outputFile1);

    
    outputFile3 << "The image array: " << endl;
    
    for(int i=0; i<pow2; i++){
        for(int j=0; j<pow2; j++){
            outputFile3 << tree->imgAry[i][j]<< " ";
        }
        outputFile3<<endl;
    }

    outputFile3<<endl;
    outputFile3<<"==================================================="<<endl;
    tree->zero2DArray(tree->newimgAry);
    tree->getLeaf(QtRoot, tree->newimgAry);
    outputFile3 << "The NEW image array: " << endl;
    for(int i=0; i<pow2; i++){
        for(int j=0; j<pow2; j++){
            outputFile3 << tree->newimgAry[i][j]<< " ";
        }
        outputFile3<<endl;
    }

    
    outputFile1.close();
    outputFile2.close();
    outputFile3.close();
    inFile.close();
    return 0;

};