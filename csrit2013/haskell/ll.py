from ll_basics import *
from functools import reduce

#Ryan Vogt
#implmentation of functions

a=LL_Empty()
b=cons(5,a)
c=cons(6,b)
d=cons(7,c)
f=cons(8,d)

'''
dummy function to
return a function that will return true, when the index passed to
the returned function is equal to the original value set at call
'''
def helper1(desiredIndex):
	def helper2(index):
		return desiredIndex==index
	return helper2

def recurse_ll_del(existing,qualifier,currentIndex):
	if(is_empty(existing)):
		return LL_Empty()
	elif(qualifier(currentIndex)):
		return  cons(tail(existing).value,tail(existing).next)
	else:
		return cons(head(existing),recurse_ll_del(existing.next,qualifier,currentIndex+1))
'''
Create and generate a function that, when used as a qualifier for
ll_insert, ll_delete, or ll_get, will return True when the element
at desiredIndex is tested.
'''

def ll_at(desiredIndex):
	
	return helper1(desiredIndex)


'''
Create and return a new list that is the same as the old one except for
one deleted value. Even if the qualifier would return true for more
than one element in the list, only the first (leftmost) one found is
removed.
 
Parameters:
existing: the given list
qualifier: a function that returns True when the element to be
           deleted has been found.
 
return: A new list with the first (leftmost) value identified by
        qualifier removed.  If qualifier never returns True,
        the existing list, or a copy thereof, is returned.
 
post condition: The existing list is unchanged.
'''

def ll_delete(existing,qualifier):
	
	
	return recurse_ll_del(existing,qualifier,0)



'''
Map a function onto the elements of a linked list.
 
Parameters:
llist: the list of values
f: the function to be applied to the values
 
return: a new list that contains values from the function f
        being applied to each element in llist (same order).
'''


def ll_map(llist, f):
	if(is_empty(llist)):
		return LL_Empty()
	else:
		return cons(f(llist.value),ll_map(llist.next,f))
'''
Create and return a new list that is the same as the old one except for
one added value.
 
Parameters:
existing: the given list
newValue: the value to be inserted into the existing list
qualifier: a function that returns True when the right location for
           the new value has been found.
 
return: A new list with newValue inserted into existing.
        Insertion happens in front of the chosen location.
        If qualifier never returns True, the new value is put
        at the end of the existing list.
 
post condition: ll_find applied to the newValue and the result
                list will return True.
                ll_basics.is_empty() applied to the result will be False.
                The existing list is unchanged.

'''
def ll_insert(existing,newValue,qualifier):
	return recurse_ll_insert(existing,newValue,qualifier,0)
'''
Return an empty linked list.
Post condition: ll_basics.is_empty() applied to the result will be true.
'''

def make_empty():
	return LL_Empty()

#helper function for ll_insert function
def recurse_ll_insert(existing,newValue,qualifier,index):
	if(is_empty(existing)):
		return LL_Empty()
	elif(qualifier(index)):
		return cons(newValue,existing)
	else:
		return cons(head(existing),recurse_ll_insert(existing.next,newValue,qualifier,index+1))

#helper function for ll_get
def recurse_ll_get(llist,qualifier,index):
	if(is_empty(llist)):
		raise IndexError 
	elif(qualifier(index)):
		return llist.value
	else:
		return recurse_ll_get(llist.next,qualifier,index+1)
'''
Search for and return a specific element of a list.
 
Parameters:
llist: the given list
qualifier: a function that returns True when the right value has
           been found.
 
return: the first (leftmost) list element for which the qualifier
        returns True.
 
exceptions: If qualifier never returns True, this
            function raises an IndexError exception.
 
post condition: The llist list is unchanged
'''
def ll_get(llist,qualifier):
	return recurse_ll_get(llist,qualifier,0)

'''
Reduce the list to a single value by applying a function to
each element of the list and the "result-so-far" (in that order).
The last (rightmost) element in the list and the start value are
the first values to which the function is applied. This is known
as a "right fold".
 
Parameters:
llist: the list of values
f: the function to be applied:
    list-element X result-so-far -> new-result
start: the second argument for the first call to f.
 
return: the result of the fold operation. Its type will
        necessarily be the same as the start parameter.
'''


def ll_fold(llist,f,start):
	if(is_empty(llist)):
		return start
	else:
		ll_fold(tail(llist),f,f(head(llist),start))
	





#print(ll_delete(f,ll_at(2)))
#print(ll_map(f,lambda x : x**2))
'''
g=ll_insert(f,55,ll_at(2))
h=ll_insert(g,25,ll_at(2))

print g
print h

j=ll_delete(h,ll_at(2))
k=ll_delete(j,ll_at(2))
print k
'''
#print(ll_insert(h,35,ll_at(2)))
'''
g=ll_delete(f,ll_at(0))
i=ll_delete(g,ll_at(0))
k=ll_delete(i,ll_at(0))
print k
'''
