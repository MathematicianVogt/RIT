%1 X is a sister of Y if X is a female and parents of X are Z W and parents of Y are Z W and X and Y are not the same person.
sister_of(X,Y) :- female(X), parents(X,Z,W),parents(Y,Z,W) , X \= Y.
%2 function that gets the second element of the list. 
%the second element of a list L is if there is the head, an element and then a tail.
second([First,Second | Tail], Second). 
%3 takes a list and returns yes if one element and no if not.
one([H]).
%4 insertion sort, takes a list and sorts it from smallest to greatest

insertion_sort(L,S) :- insertion_sort_help(L,[],S).
 
insertion_sort_help([],L,L).
insertion_sort_help([H|T],List1,List2) :- insert(List1,H,L2), insertion_sort_help(T,L2,List2).
 
insert([],X,[X]).
insert([H|T],X,[X,H|T]) :- X =< H, !.
insert([H|T],X,[H|T2]) :- insert(T,X,T2).             
             
%5 finding all elements where a entry can be found

index(Found,[Found| T],0).
index(Found,[H| T],Index):- index( Found,T, NewIndex),Index is NewIndex+1.

             







male(tony).
male(ethan).
male(chris).
male(ben).
male(nathan).

female(sim).
female(helen).
female(sophie).
female(olivia).

parents(helen, sim, tony).
parents(chris, sim, tony).
parents(ben, sim, tony).
parents(sophie, helen, ethan).
parents(olivia, helen, ethan).
parents(nathan, helen, ethan).




quicksort([], []).
quicksort([H | T], S) :-
   partition(T, H, Less, Same, Greater),
   quicksort(Less, SLess),
   quicksort(Greater, SGreater),
   append(SLess, [H | Same], SGreater, S).

append([], L, L).
append([H | T], L, [H | A]) :- append(T, L, A).

append(L1, L2, L3, A) :- append(L2, L3, L23), append(L1, L23, A).


d(X, X, 1).
d(C, X, 0) :- atomic(C), C \= X.
d(U + V, X, DU + DV) :- d(U, X, DU), d(V, X, DV).
d(U * V, X, U * DV + V * DU) :- d(U, X, DU), d(V, X, DV).