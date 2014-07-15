;Author Ryan Vogt
;#1 a merge function that takes two listes already ordered and combines them in such a way that the result is ordered
;carList function, takes an element and appends it to the end of a given list, element, list -> list
(define (carList carL FL) (append FL (list carL)))
;mergeFunction takes two lists, and a final list, recursively merges the lists such that the final product is sorted in asencding order list1 list2 list3 -> finalList
(define (mergeFunction L1 L2 LF) (if(and (null? L1) (null? L2) ) LF (if(null? L1) (append LF L2) (if(null? L2) (append LF L1) (if(<= (car L1) (car L2)) (mergeFunction (cdr L1) L2 (carList (car L1) LF)) (mergeFunction L1 (cdr L2) (carList (car L2) LF))  ))))) 
;merge takes two lists, and a final list, recursively merges the lists such that the final product is sorted in asencding order list1 list2-> finalList
(define (merge ordered1 ordered2)(mergeFunction ordered1 ordered2 '()))
;(merge '(5 5 5 7 9 10) '(1 2 4 5 6 6 8 9 10 11 12 13))
;#2 a merge-sort function that takes an unsorted list and uses the merge sort algorithm with also the merge function above to sort a list
;oddElements list-> list takes a list and returns the odd elements of the list
(define (oddElements L) (if(null? L) '() (if(null? (cdr L)) (list (car L)) (cons (car L) (oddElements (cdr (cdr L))))))) 
;evenElements-list-> list gives the even elements of a list
(define (evenElements L ) (if(null? L) '() (if (null? (cdr L)) '() (cons (car (cdr L ) ) (evenElements (cdr (cdr L)))))))
;merge-sort list->list takes a list that is not ordered and orders it in acending order by mergesort algorith
;takes advatage of odd and even element functions to divide lists and sort them on the fly
(define (merge-sort L ) (if(null? L ) L (if(null?(cdr L )) L ( merge (merge-sort (oddElements L )) (merge-sort (evenElements L))))))
;(merge-sort '(5 632 6 212 4 5 3 5 7 1 7 1 1 1))
;#3 furthering the deriv function in class to also accept power functions arthExp var -> list
; variable?: Any -> Bool
(define variable? symbol?)

; (eq? (variable? 'x) #t)
; (eq? (variable? 3) #f)

; variable=?: VarExp * VarExp -> Bool
(define variable=? eq?)

; (variable=? 'x 'x)
; (not (variable=? 'x 'y))

;; a sum is represented as a list with three elements: tag, e1, e2.
;; the tag is the symbol +

; sum?: Any -> Bool
(define (sum? a) (and (pair? a) (eq? (car a) '+)))

; (eq? (sum? '(+ 2 3)) #t)
; (eq? (sum? '3) #f)

; make-sum: ArithExp * ArithExp -> SumExp
(define (make-sum e1 e2) (cond ((and (number? e1) (number? e2))  (+ e1 e2)) ((and  (number? e1) (= 0 e1))  e2) ((and  (number? e2) (= 0 e2)) e1)   (else (list '+ e1 e2))))

; (equal? (make-sum 2 3) '(+ 2 3))

;; a product is represented as a list with three elements: tag, e1, e2.
;; the tag is the symbol *

; product?: Any -> Bool
(define (product? a) (and (pair? a) (eq? (car a) '*)))

; (eq? (product? '(* 2 3)) #t)   
; (eq? (product? '3) #f)

; make-sum: ArithExp * ArithExp -> ProductExp
(define (make-product e1 e2) (cond ((and (number? e1) (number? e2)) (* e1 e2)) ((and (number? e1) (= e1 0))  0) ((and (number? e2) (= e2 0)) 0) ((and (number? e1) (= e1 1)) e2) ((and (number? e2) (= e2 1)) e1)  (else (list '* e1 e2)))) 

; (equal? (make-product 2 3) '(* 2 3))

;; sums and products will use the same selectors

; arg1: SumExp or ProdExp -> ArithExp
(define (arg1 e) (car (cdr e)))

; (= (arg1 (make-sum 2 3)) 2)
; (= (arg1 (make-product 2 3)) 2)

; arg2: SumExp or ProdExp -> ArithExp
(define (arg2 e) (car (cdr (cdr e))))

; (= (arg2 (make-sum 2 3)) 3)
; (= (arg2 (make-product 2 3)) 3)




;make-expr: arithExp * natutalNumber -> arithExp
(define (make-expt e n)(cond ((= n 0) 1) ((= n 1) e) (else(cons '^ (append (list e) (list n))))))
;(make-expt '(* x 5) 2)
;expt? arthimExp-> Bool a function to see if a arthimac expression is in a correct form
(define (expt? e) (and (pair? e) (eq? (car e) '^)))
;make-expt arithExp->arithExp takes a power function and differentiates it. 
(define (make-exptDev L v )(if(or (number? (car L)) (number? (car(cdr L)))) (list 0) (if(= (arg2 L) 0) (list 0) (if(= (arg2 L) 1) (list (deriv (arg1 L) v)) (list '*  (arg2 L) (list '^ (arg1 L) (- (arg2 L) 1)))))))
;;; derivative code

;deriv: ArithExp * VarExp -> ArithExp

(define (deriv exp var)
  (cond ((number? exp) 0)
        ((variable? exp)
         (if (variable=? exp var) 1 0))
        ((expt? exp) (make-product (make-exptDev exp var) (deriv (arg1 exp) var))) 
        ((sum? exp) 
         (make-sum (deriv (arg1 exp) var)
                   (deriv (arg2 exp) var)))
        ((product? exp)
         (make-sum (make-product (arg1 exp) (deriv (arg2 exp) var))
                   (make-product (arg2 exp) (deriv (arg1 exp) var))))
        (else (error 'deriv "Unexpected Input, not an ArithExp"))))


;(deriv '(* (^ x 100) (* 5 x )) 'x)
;#4 additional changes for simplification of deriv function

;#5 integral that has inputs exp and var, where exp is an extended arithmetic expression and var is a variable expression.
; arthExp var -> arithExp

;List->bool tells if a list is empty or not, a empty list corresponds to zero polynomial and non empty is a non zero polynomial
(define (zero-poly? L) (null? L))

;constant->list, if constant is zero then returns the empty list, else gives a list with the single element constant
(define (poly<-const c)(if(= c 0) '() (list c)))
;shifts a polynomial expression to the left
(define (shift-left L)(if(null? L) '() (cons 0 L)))
;shifts a polynomial expression to the right
(define (shift-right L) (if(null? L) '() (cdr L)))
;returns the constant coefficent assosciated with a polynomial
(define (const-coeff L )(if(null? L) '() (car L) ))

(define (add-const-poly P c) (sumLists P (list c)))
; P c -> cP scales a polynomial by a factor of c
(define (scale-poly P c) (multiList P c '()))

;helper function to help aid making a polynomial be multipled by a constant
(define (multiList L c FL) (cond ((= c 0) '()) ((null? L) FL) (else(multiList (cdr L) c (append FL (list (* (car L ) c)))))))  

;code to take two lists and add them list by list l1 l2-> sumedList
(define (sumLists l1 l2) (cond ((null? l1) l2) ((null? l2) l1) (else( finalSumList l1 l2 '()))))
;helper function for sumLists, l1 l2 FL -> sumedList
(define (finalSumList l1 l2 FL) (cond ((null? l1) (append FL l2)) ((null? l2) (append FL l1)) (else ( finalSumList (cdr l1) (cdr l2) (append FL (list (+ (car l1) (car l2)))))))) 
 ;sums two lists together into one list                          
(define (add-poly p1 p2) (sumLists p1 p2))
;multiply two polynomials to get a list result
(define (mult-poly p1 p2) (first-multi p1 p2 '()))
;multiply helper function to help main multiply function
(define (first-multi p1 p2 FL) (cond ((null? p1) (sumLists FL p2)) ((null? p2) (sumLists FL p1)) (else(multipling-poly (cdr p1) p2 (scale-poly p2 (car p1)) 1 ))))
;multiply helper function to help main multiply function
(define (multipling-poly p1 p2 FL n) (cond ((null? p1) FL) (else(multipling-poly (cdr p1) p2 (sumLists FL (addZeros (scale-poly p2 (car p1)) n)) (+ n 1)))))
;adds n 0's to the front of a list
(define (addZeros L n) (cond ((= n 0 ) L ) (else(addZeros (cons 0 L ) (- n 1)))))
; returns the length of a list as a number
(define (len L n) (cond ((null? L) n) (else (len (cdr L ) (+ n 1))))) 
;(mult-poly '(1 2 1) '(1 1 1))
(define (expt-poly P n) (cond ((= n 0) '()) (else (mult-poly P (expt-poly P (- n 1)))))) 
;issues with this function
(define (poly<-exp L n FL) (cond ((null? L ) FL) (else(poly<-exp (cdr L ) (+ n 1) (list FL '+ (list '* (car L) (make-expt 'x n) ))))))  
;cant make exp<-poly without previous function and cant complete program