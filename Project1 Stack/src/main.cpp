#include <iostream>
#include <fstream>

using namespace std;


class listNode{

    public:
        string data;
        listNode *next;
        listNode(string value){
            
            this->data = value;
            this->next = NULL;

        }

    void printNode(ofstream *output){
       
        *output << this->data;

    }

};


class LLStack{

    public:
        listNode *top;

        LLStack(){

            listNode *dummy = new listNode("dummy");
            this->top = dummy;

        }

        void push(listNode *newNode){
            
            newNode->next=top->next;
            top->next = newNode;

        }


        bool isEmpty(){

            if(top->next == NULL){
                return true;
            }
            return false;
        }


        listNode *pop(){
            
            listNode *popped = new listNode("hi");

            if(isEmpty())
                return NULL;
            
            //popped = top
            //top = top.next
            //popped.next = null we do this to ensure that we dont return the whole list 

            popped = top->next;
            top->next = top->next;
            top->next = popped->next;
            popped->next = NULL;
            return popped;
        
        }

        void buildStack(LLStack *stack, ifstream *input, ofstream *output){
            
            char c;
            string name;


            while(*input >> c >> name){

                *output<<"data coming in is:   " << c << name << endl;

                if(c== '+'){
                    //put data in stack
                    listNode *node = new listNode(name);
                    stack->push(node);
                    
                }else{
                    //pop data in stack but make sure its not empty

                     listNode *node = stack->pop();

                     if(node != NULL){
                        free(node);
                     }else{
                        *output<<"stack is empty" << endl;
                     }
                
                }

                 stack->printStack(output);

            }



        }

        void printStack(ofstream *output){
            listNode *curr = top;
            
            while (curr != NULL)
            {
                curr->printNode(output);
                *output << "==>";
                curr = curr->next;
            }
            *output << "NULL" << endl;
        }



};


class LLQueue{

    public:

        listNode *head;
        listNode *tail;

        LLQueue(){

            listNode *dummy = new listNode("dummy");
            this->head = dummy;
            this->tail = dummy;

        }

        void insertQ(listNode *node){
            
            tail->next = node;
            tail = node;
        }

        listNode *deleteQ(){
            
            if(this->isEmpty())
                return NULL;

            listNode *node = head->next;
            head->next = node->next;
            node->next = NULL;
            

            return node;

        }

        bool isEmpty(){
            if(head->next == tail){
                return true;
            }
            return false;
        }

        void buildQueue(LLQueue *queue, ifstream *input, ofstream *output){

            char c;
            string name;

            while(*input >> c >> name){

                *output<<"data coming in is:   " << c << name << endl;

                if(c== '+'){
                    //put data in stack
                    listNode *node = new listNode(name);
                    queue->insertQ(node);
                    
                }else{
                    //pop data in stack but make sure its not empty

                     listNode *node = queue->deleteQ();

                     if(node != NULL){
                        free(node);
                     }else{
                        *output<<"Queue is empty" << endl;
                     }
                
                }

                queue->printQueue(output);
                
            
            }
        }

        void printQueue(ofstream *output){
            listNode *curr = head;

            while(curr!=NULL){
                curr->printNode(output);
                curr = curr->next;
                *output << "=> ";
            }
            
            *output << "NULL" << endl;
        }

};




int main(int argc, const char* argv[]){

  
    ifstream inputFile;
    inputFile.open(argv[1]);

    if (!inputFile.is_open()) {
             cout << "Unable to open file" << endl;
             exit(1);
    }

    // stack
    ofstream outFile1;
    outFile1.open(argv[2]);

    
    LLStack *stack= new LLStack();
    stack->buildStack(stack, &inputFile, &outFile1);
    inputFile.close();

    // queue
    inputFile.open(argv[1]);
    ofstream outFile2;
    outFile2.open(argv[3]);

    LLQueue *queue= new LLQueue();
    queue->buildQueue(queue, &inputFile, &outFile2);

    

}