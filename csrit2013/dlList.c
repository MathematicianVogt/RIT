#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <stdbool.h>

	
	struct cursor
	{

		struct node* currentNodeLocation;
		int currentNodeNumber;


	} ;
	struct node{

		void *data;
		struct node* previous;
		struct node* next;
	};

	struct doubleLinkedList {
		struct node* headOfQueue;
		struct cursor* myCurrentNodeLocation;


	};


	typedef struct doubleLinkedList *DlList_T;
	#define _DLL_IMPL_
	#include "dlList.h"
	DlList_T dll_create( void )
	{
		DlList_T newList;
		newList= (DlList_T) malloc(sizeof(struct doubleLinkedList));
		newList->headOfQueue=NULL;
		struct cursor* myCur=  (struct cursor*) malloc(sizeof(struct cursor));
		newList->myCurrentNodeLocation=myCur;
		(newList->myCurrentNodeLocation)->currentNodeLocation=NULL;
		(newList->myCurrentNodeLocation)->currentNodeNumber=0;


		return newList;

	}
	
	void dll_append( DlList_T lst, void *data )
	{
		

		
		if(lst->headOfQueue==NULL)
		{
			struct node* newNode= (struct node*) malloc(sizeof(struct node));
			lst->headOfQueue=newNode;
			newNode->data=data;
			newNode->previous=NULL;
			newNode->next=NULL;




		}
		else
		{	
			struct node* currentNode=lst->headOfQueue;
			while(currentNode->next!=NULL)
			{

				currentNode=currentNode->next;

			}
			struct node* newNode= (struct node*) malloc(sizeof(struct node));
			newNode->data=data;
			newNode->next=NULL;
			newNode->previous=currentNode;
			currentNode->next=newNode;




		}
		


	}



	void printList(DlList_T list)
	{
		
		struct node* currentNode = list->headOfQueue;
		if(currentNode==NULL)
		{

			printf("Nothing in list\n");

		}
		else
		{
			struct node* backwardStart=NULL;
			

			printf("FORWARD:\n");
			while(currentNode!=NULL)
			{

				if(currentNode->next==NULL)
				{
				
					backwardStart=currentNode;

				}

				printf("%d\n", (int ) currentNode->data );
				currentNode=currentNode->next;




			}
			printf("BACKWARDS\n");
			while(backwardStart!=NULL)
			{

				printf("%d\n", (int) backwardStart->data );
				backwardStart=backwardStart->previous;



			}



		}

		}
		int dll_size( DlList_T lst )
		{
			struct node* currentNode;
			int size=0;
			if(lst->headOfQueue==NULL)
			{
				return size;

			}
			else
			{
			
				currentNode=lst->headOfQueue;

				while(currentNode!=NULL)
				{


					size++;
					currentNode=currentNode->next;

				}


				return size;

			}



		}
		void dll_clear( DlList_T lst )
		{
			struct node* currentNode=lst->headOfQueue;

			while(currentNode!=NULL)
			{
				if(currentNode->next==NULL)
				{
					printf("FREED %d\n", (int )currentNode->data );
					free(currentNode);
					break;


				}

				struct node* nextNode=currentNode->next;
				printf("FREED %d\n", (int )currentNode->data );
				free(currentNode);
				currentNode=nextNode;
			}
			free(lst->myCurrentNodeLocation);




		}

		void dll_destroy( DlList_T lst )
		{
			dll_clear(lst);
			free(lst->myCurrentNodeLocation);
			free(lst);




		}
		bool dll_empty( DlList_T lst )
		{

			if(lst->headOfQueue==NULL)
			{
				return 1;

			}
			else
			{

				return 0;
			}

		}
		
		bool goingUp(DlList_T lst,int indx,int decisionNumber)
		{
			if((lst->myCurrentNodeLocation)->currentNodeLocation==NULL)
			{
				struct node* currentNodePointer=lst->headOfQueue;

				for(int i=1; i<decisionNumber; i++)
				{

					currentNodePointer=currentNodePointer->next;

				}
				lst->myCurrentNodeLocation->currentNodeLocation=currentNodePointer;
				lst->myCurrentNodeLocation->currentNodeNumber=indx;
				return 1;
			}
			else
			{

				struct node* currentNodePointer=lst->headOfQueue;

				for(int i=0; i<decisionNumber; i++)
				{

					currentNodePointer=currentNodePointer->next;

				}
				lst->myCurrentNodeLocation->currentNodeLocation=currentNodePointer;
				lst->myCurrentNodeLocation->currentNodeNumber=indx;
				return 1;


			}

		}
		bool goingDown(DlList_T lst, int indx,int decisionNumber)
		{	
			struct node* currentNodePointer=(lst->myCurrentNodeLocation)->currentNodeLocation;

			for(int i=0; i<-(decisionNumber); i++)
			{

				currentNodePointer=currentNodePointer->previous;

			}
			lst->myCurrentNodeLocation->currentNodeLocation=currentNodePointer;
			lst->myCurrentNodeLocation->currentNodeNumber=indx;
			return 1;



		}


		bool dll_move_to( DlList_T lst, int indx )
		{
			if(indx<1 || indx>dll_size(lst))
			{


				return 0;


			}
			else
			{
				int currentNodePosition=(lst->myCurrentNodeLocation)->currentNodeNumber;
				int decisionNumber=indx-currentNodePosition;
				if(decisionNumber==0)
				{

					return 0;


				}
				else if(decisionNumber>0)
				{

					goingUp(lst,indx,decisionNumber);

				}
				else
				{

					goingDown(lst,indx,decisionNumber);

				}



				return 0;




			}

		


		
		}
	
		void showCursor(DlList_T myList)
		{


			printf("DATA IN CURSOR %d\n",(int) ((myList->myCurrentNodeLocation)->currentNodeLocation)->data);
			printf("POINT SPACE %d\n",(int) (myList->myCurrentNodeLocation)->currentNodeNumber);


		}
		