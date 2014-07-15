// 
// $Id: vault.h,v 2.0 2014/04/13 16:55:26 csci243 Exp $ 
// File: vault.h 
// vault.h declares the interface to the vault data structure operations
// for vault files, a collection of cd/lp record information.
// 
// @author bks: ben k steele
//
// // // // // // // // // // // // // // // // // // // // // // // // 

#ifndef VAULT_H_
#define VAULT_H_

/// TITLE_LEN is the maximum length of a title field.
#define TITLE_LEN 36

/// ARTIST_LEN is the maximum length of a artist field.
#define ARTIST_LEN 30

/// VaultEntry_T determines the structure of a vault (lp) record.
///
typedef struct VaultEntry_S {       /// title of the work
    char title[TITLE_LEN+1];
                                    /// artist of the work
    char artist[ARTIST_LEN+1];
                                    /// whole dollar value
    int  value;
                                    /// whole dollar acquisition cost
    int  cost;
} VaultEntry_T;

/// alias for a vault identifier (file descriptor)
typedef int Vault;

/// open file Read+Write, initialize the internal vault id (file descriptor),
/// file size, and record index.
/// The initial index is set to 0, the index of the start of the file.
/// Uses fstat to determine file size.
///
/// @param filename to open read-write
/// @return vid, the vault identifier or -1 on error.
///
int v_open( const char * filename );

/// close the current open vault.
/// reports error using perror library call.
///
/// @param vid the current, open vault identifier
/// @return 0 or -1 if error.
/// @pre vid == the current, open vfd
///
int v_close( Vault vid );

/// return a new entry populated with information.
/// It is a fatal error if memory allocation fails.
/// If the title is too long to fit into the entry's
/// fixed field, then truncate it so that the last
/// three characters before the NUL character are '.'
/// to form an ellipsis. Do the same thing for the artist.
///
/// @param title a string representing the lp's title
/// @param artist a string representing the lp's artist
/// @param value the current dollar value of the lp
/// @param cost the dollar acquisition cost of the lp
/// @return pointer to the new entry allocated on the heap
/// @pre title must be valid, non-null string pointer.
/// @pre artist must be valid, non-null string pointer.
/// @pre value >= 0
/// @pre cost >= 0
///
VaultEntry_T * v_create( char * title, char * artist, int value, int cost );

/// append the record by writing the entry's content to the end of the vault.
///
/// @param vid the current, open vault identifier
/// @param entry the entry to add by appending
/// @return error(-1) or the new index into the vault
/// @pre entry must be valid, non-null pointer.
/// @pre vid == the current, open vfd
/// @post file size grows by 1 record upon successful addition.
/// @post file pointer location is at the end of the vault.
///
int v_add( Vault vid, VaultEntry_T * entry );

/// allocates a buffer and returns a copy of the entry at the index. 
/// The caller is responsible for memory management of the returned object.
///
/// @param vid the current, open vault identifier
/// @param index identifies the entry to get
/// @return copy of entry at the index or NULL if the index is out of range.
/// @pre 0 <= index < vsize
/// @post the pointer location is at the start of the next record in the vault.
///
VaultEntry_T * v_get( Vault vid, size_t index );

/// replaces the existing record content at index by overwriting with
/// the content of the supplied entry.
/// The caller is responsible for memory management of the entry object.
///
/// @param vid the current, open vault identifier
/// @param index identifies the entry to update
/// @param entry the new values for the update
/// @return index of next entry or -1 if the write failed.
/// @pre 0 <= index < vsize
/// @pre entry must be valid, non-null pointer.
/// @post the pointer location is at the start of the next record in the vault.
///
int v_update( Vault vid, size_t index, VaultEntry_T * entry );

/// delete the entry at the index.
/// Replace the entry at the specified index with the entry contained
/// in the last index into the vault.
/// If the index is that of the last entry, then truncate the file to
/// remove the last record and sync the file buffers out to the file system.
/// It is a fatal error if memory buffer allocation fails.
///
/// @param vid the current, open vault identifier
/// @param index identifies the entry to delete
/// @return current index on success (next index after the deleted one),
///         -1 on index out of range, or -2 if deletion failed.
/// @pre 0 <= index < vsize
/// @post on success, the file size shrinks by 1 record.
/// @post file offset is the start of the next record or the end of the vault.
///
int v_delete( Vault vid, size_t index );

/// report the size of the vault in number of records.
///
/// @param vid the current, open vault identifier
/// @return size of the vault in units of entries
/// @post start index == end index
///
size_t v_size( Vault vid );

/// Get the current 0-based index into the vault.
/// The current index in record units is simply the current
/// file pointer offset integer-divided by the record size.
///
/// @param vid the current, open vault identifier
/// @return current, 0-based index of the vault.
///
size_t v_index( Vault vid );

/// Set the current index to the specified index value.
/// Move the file pointer to the location that marks the start of the
/// record at the specified index, and make that the current 
/// file pointer location.
///
/// @param vid the current, open vault identifier
/// @param index identifies the desired, new record index
/// @return new 0-based index into the vault.
/// @pre 0 <= index < vsize
///
size_t v_set_index( Vault vid, size_t index );

/// print the entry
///
/// @param entry points to a vault entry
/// @pre entry must be valid, non-null pointer.
///
void v_print_entry( VaultEntry_T * entry );

#endif // VAULT_H_

// // // // // // // // // // // // // // // // // // // // // // // // 
// Revisions: 
// $Log: vault.h,v $
// Revision 2.0  2014/04/13 16:55:26  csci243
// documentation wording clarifications.
//
// Revision 1.2  2013/11/19 00:02:42  csci243
// fixed v_delete return value documentation
//
// Revision 1.1  2013/11/05 16:45:17  csci243
// Initial revision
// 
