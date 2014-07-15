#include "dlList.h"
#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <stdbool.h>
		int main(void)
		{

			DlList_T myList =dll_create();
			void *one=5;
			void *two=6;
			void *three=9;
			dll_append(myList,one);
			dll_append(myList,two);
			dll_append(myList,three);
			dll_move_to(myList,2);
			showCursor(myList);
			dll_move_to(myList,1);
			showCursor(myList);
			dll_move_to(myList,3);
			showCursor(myList);
			dll_move_to(myList,257);
			showCursor(myList);
			dll_move_to(myList,1);
			showCursor(myList);

			printList(myList);
			printf("SIZE IS %d\n",dll_size(myList));
			

			dll_clear(myList);
			dll_append(myList,one);
			dll_append(myList,two);
			dll_append(myList,three);
			dll_move_to(myList,2);
			showCursor(myList);
			dll_move_to(myList,1);
			showCursor(myList);
			dll_move_to(myList,3);
			showCursor(myList);
			dll_move_to(myList,257);
			showCursor(myList);
			dll_move_to(myList,1);
			showCursor(myList);
			printList(myList);
			printf("SIZE IS %d\n",dll_size(myList));
			


			return 0;



		}