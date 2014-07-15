
quicksort([], []).
quicksort([H | T], S) :-
   partition(T, H, Less, Same, Greater),
   quicksort(Less, SLess),
   quicksort(Greater, SGreater),
   append(SLess, [H | Same], SGreater, S).

append([], L, L).
append([H | T], L, [H | A]) :- append(T, L, A).

append(L1, L2, L3, A) :- append(L2, L3, L23), append(L1, L23, A).


%Ryan Vogt
%1 parition, takes a list and a pivot and breaks it into a lowerlist, samelist and upperlist.
partition([],P,A,B,C):- A = [], B = [], C = [].
partition([H1|T1],P,A1,B1,C1):- H1<P , partition(T1,P,A2,B2,C2), A1 = [H1 |A2], B1 = B2, C1 = C2.
partition([H1|T1],P,A1,B1,C1):- H1=P , partition(T1,P,A2,B2,C2), A1 = A2, B1 = [H1 |B2], C1 = C2.
partition([H1|T1],P,A1,B1,C1):- H1>P , partition(T1,P,A2,B2,C2), A1 = A2, B1 = B2, C1 = [H1 | C2].


%2 merge two lists together
merge(O1,[],O1).
merge([],O2,O2).
merge([H1|T1],[H2|T2],[H1|T]):- H1<H2,merge(T1,[H2|T2],T).
merge([H1|T1],[H2|T2],[H1|T]):- H2<H1,merge(T2,[H1|T1],T).
merge([H1|T1],[H2|T2],[H1|T]):- H2=H1,merge(T2,T1,T).

%3 merge sort of a list.
division([],[],[]).
division([H],[H],[]).
division([H1,H2|T] ,[H1|T1],[H2,T2]):-division(T,T1,T2).


merge_sort([],[]).
merge_sort([H],[H]).
merge_sort(L1,L2):-divison(L1,L11,L22), merge_sort(L11,L),merge_sort(L22,R),merge(L,R,L2).