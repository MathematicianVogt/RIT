#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <stdbool.h>
#include <assert.h>
#include <stdio.h>

//implement queue as a link list

struct node{

	void *data;
	struct node* previous;
	struct node* next;
};

struct queueStruct{
	struct node* headOfQueue;
	int (*Compare)(const void*a,const void*b); 


};


	typedef struct queueStruct *QueueADT;
	#define _QUEUE_IMPL_
	#include "queueADT.h"

QueueADT que_create( int (*cmp)(const void*a,const void*b) )
{
	QueueADT newQueue;
	newQueue=(QueueADT) malloc(sizeof(struct queueStruct));
	newQueue->Compare=cmp;
	newQueue->headOfQueue=NULL;

	return newQueue;
}
void que_destroy( QueueADT queue )
{
		que_clear(queue);
		free(queue);
	


}
void que_clear( QueueADT queue )
{
	struct node* current;
	struct node* temp=queue->headOfQueue;
	while(temp!=NULL)
	{

		current=temp;
		temp=temp->next;
		free(current);
	}
	queue->headOfQueue=NULL;


}

void que_insert( QueueADT queue, void *data )
{	

	int (*CurrentCompare)(const void*a,const void*b)=queue->Compare;
	if(CurrentCompare==NULL)
	{

		struct node* currentNode=queue->headOfQueue;
		if(currentNode==NULL)
		{

			struct node* newNode=malloc(sizeof(struct node));
			newNode->data=data;
			newNode->next=NULL;
			newNode->previous=NULL;
			queue->headOfQueue=newNode;



		}


		else
		{
			while(currentNode->next!=NULL)
			{

				currentNode=currentNode->next;



			}
			struct node* newNode=malloc(sizeof(struct node));
			newNode->data=data;
			newNode->next=NULL;
			newNode->previous=currentNode;
			currentNode->next=newNode;
		}
	}
	else
	{	struct node* currentNode=queue->headOfQueue;
		if(currentNode==NULL)
		{

			struct node* newNode=malloc(sizeof(struct node));
			newNode->data=data;
			newNode->next=NULL;
			newNode->previous=NULL;
			queue->headOfQueue=newNode;



		}
		else
		{
			
			while((*CurrentCompare)(currentNode->next->data,data) && currentNode->next!=NULL)
			{





			}
			struct node* insertNode=malloc(sizeof(struct node));
			insertNode->data=data;
			insertNode->next=currentNode;
			insertNode->previous=currentNode->previous;
			
			if(insertNode->previous!=NULL)
			{
			insertNode->previous->next=insertNode;
			}
			
			if(insertNode->next!=NULL)
			{
			insertNode->next->previous=insertNode;
			}


		}



	}





	
}
void *que_remove( QueueADT queue )
{
	void* value;
	struct node* newQueueHead;
	struct node* currentHead;
	if(que_empty(queue))
	{

		printf("Queue is empty, can remove an element that doesnt exist");
		exit(EXIT_FAILURE);

	}
	else{
	
		value=(queue->headOfQueue)->data;
		newQueueHead=(queue->headOfQueue)->next;
		currentHead=queue->headOfQueue;
		queue->headOfQueue=newQueueHead;
		(queue->headOfQueue)->previous=NULL;
		free(currentHead);

		return value;




	}






}

bool que_empty( QueueADT queue )
{

	if(queue->headOfQueue==NULL)
	{
		return 1;
	}
	return 0;



}




