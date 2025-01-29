// Online C++ compiler to run C++ program online
#include <bits/stdc++.h>
#include<iostream>

struct Node{
    int data;
    Node* next;
};

void insertAtBegin(Node** head, int value){
    struct Node* newNode = (struct Node*)malloc(sizeof(struct Node));
    newNode->data = value;
    newNode->next=*head;
    *head=newNode;
}

void insertAtEnd(Node** head, int value){
    struct Node *newNode = (struct Node*)malloc(sizeof(struct Node));
    newNode->data=value;
    newNode->next=NULL;
    if(*head == NULL){
        *head=newNode;
        return;
    }
    
    struct Node* tmp = *head;
    while(tmp->next != NULL){
        tmp=tmp->next;
    }
    tmp->next=newNode;
}

void deleteNode(Node** head, int value){
    struct Node *tem = *head, *prev=NULL;
    if(tem->data == value){
        *head = tem->next;
        free(tem);
        return;
    }
    
    while(tem->next != NULL && tem->data != value){
        prev=tem;
        tem=tem->next;
    }
    if(tem->data == value){
        prev->next=tem->next;
        free(tem);
    }
    
}

void display(Node* head){
    struct Node* tem = head;
    while(tem->next!=NULL){
        std::cout << tem->data << " ";
        tem=tem->next;
    }
    std::cout << tem->data << std::endl;
}



int main() {
    
    struct Node* head = NULL;
    insertAtEnd(&head, 5);
    insertAtEnd(&head, 4);
    insertAtEnd(&head, 3);
    insertAtEnd(&head, 2);
    
    display(head);
    
    insertAtBegin(&head, 1);
    
    display(head);
    
    deleteNode(&head, 1);
    
    display(head);
    
    deleteNode(&head, 3);
    
    display(head);
    
    deleteNode(&head, 2);
    
    display(head);
    
    deleteNode(&head, 1);
    
    display(head);
    

    return 0;
}