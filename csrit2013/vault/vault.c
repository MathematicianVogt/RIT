// vault-stub.c starts the implementation of vault.c
#include <stdio.h>
#include <stdlib.h>

#include "vault.h"
#include "file_tools.h"
#include <string.h>
#include <fcntl.h>
#include <unistd.h>
//can only create and add things to the vault moving around, etc, butcant edit, read
//not sure why my implementation isnt working, implemented just as the .h explains. 
//this issue is that im not using the read entry correctly or my indexing from where to start
//reading from is messed up. 


/// open the vault file.
int v_open( const char * filename ) {

		

		Vault id=open(filename,O_RDWR);

		
		return id;


}

/// close the vault file.
int v_close( Vault vid ) {

	if(close(vid))
	{

		return 0;

	}
	return -1;
}

/// create an entry for the vault.
VaultEntry_T * v_create( char * title, char * artist, int value, int cost ) {

	
	if(title!=NULL && artist!=NULL && value>=0 && cost>=0)
	{
		VaultEntry_T *newEntry = (VaultEntry_T *) malloc(sizeof(struct VaultEntry_S));
		
		if(strlen(title)>36)
		{
		

			for (int i =0; i<33; i++)
			{


				newEntry->title[i]=title[i];

			}
			newEntry->title[33]='.';
			newEntry->title[34]='.';
			newEntry->title[35]='.';
			newEntry->title[36]=0;

		}
		else{
			for(int i=0; i<TITLE_LEN+1;i++)
			{

				newEntry->title[i]=title[i];

			}

		}
		if(strlen(artist)>30)
		{
			

			for (int i =0; i<27; i++)
			{


				newEntry->artist[i]=artist[i];

			}
			newEntry->artist[27]='.';
			newEntry->artist[28]='.';
			newEntry->artist[29]='.';
			newEntry->artist[30]=0;


		}
		else
		{
			for(int i=0; i< ARTIST_LEN+1;i++)
			{

				newEntry->artist[i]=artist[i];

			}


		}
		newEntry->cost=cost;
		newEntry->value=value;
		return newEntry;

	}
	return NULL;
}

/// add an entry to the vault file.
int v_add( Vault vid, VaultEntry_T * entry ) {

	 int currentOffset=move_to(vid, 0, END);
	 if(write_entry(vid ,entry,sizeof(struct VaultEntry_S))==0)
	 {


	 	return currentOffset/sizeof(struct VaultEntry_S);

	 }
	 return -1;


}

/// get the entry at the index.
VaultEntry_T * v_get( Vault vid, size_t index ) {

	
	if(index>1 || index<v_size(vid))
	{
		VaultEntry_T *entry;
		move_to(vid, index*sizeof(struct VaultEntry_S), START);
		read_entry( vid,entry,sizeof(struct VaultEntry_S));
		return entry;
	}
	return NULL;

}

/// update the entry at the index with new information.
int v_update( Vault vid, size_t index, VaultEntry_T * entry ) {
	move_to(vid,0,START);
	int newest =move_to(vid,index*sizeof(struct VaultEntry_S),START);
	if(write_entry( vid,entry,sizeof(struct VaultEntry_S) )==0)
	{
		return (newest/sizeof(struct VaultEntry_S))+1;

	}

	return -1;
	


}

/// delete the entry at the index, replacing it with the last entry's content.
int v_delete( Vault vid, size_t index ) {

	int lastIndex=move_to(vid,0,END);
	VaultEntry_T *last = v_get(vid,(lastIndex/sizeof(struct VaultEntry_S)));
	int myindex=move_to(vid,index*sizeof(struct VaultEntry_S),START);

	if(index>1 || index<v_size(vid))
	{

		return -1;

	}
	else if(write_entry(vid,last,sizeof(struct VaultEntry_S))==0)
		{
			return myindex/sizeof(struct VaultEntry_S);

		}
	return -2;
}

/// return the number of entries in the vault file.
size_t v_size( Vault vid ) {
	
	int last= move_to( vid, 0, END);
	move_to(vid,0,START);
	return last/sizeof(struct VaultEntry_S);
}

/// get the index associated with the current file offset into the vault.
size_t v_index( Vault vid ) {

	int index=move_to(vid,0,HERE);
	return index/sizeof(struct VaultEntry_S);
}

/// set the file offset so it refers to the index-th record in the vault file.
size_t v_set_index( Vault vid, size_t index ) {

	int newindex =move_to(vid,index*sizeof(struct VaultEntry_S),START);
	return newindex/sizeof(struct VaultEntry_S);
}

/// print the entry.
void v_print_entry( VaultEntry_T * entry ) {

	printf("\"%s\", by %s\n is worth $%d and costs $%d.",entry->title,entry->artist,entry->value,entry->cost );
}

// end file
