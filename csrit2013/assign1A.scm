;@author Ryan Vogt.
;all functions complete
;#1
(define (2nd L) (car(cdr L)))
;(2nd '(a b c))
;#2
(define (one? L) (if(null? L) #f (if(null? (cdr L)) #t #f)))
;(one? '(a b))
;#3
(define (insertList n L) 
  (if(null? L)
     (list n) 
     (if(<= n (car L)) 
        (cons n L) 
        (cons (car L) (insertList n (cdr L))))))
(define (insertion-sort nums) (if(null? nums) '() (insertList (car nums) (insertion-sort (cdr nums)))))
;(insertion-sort '(1 5 2 6 7 65 3))
;#4
(define (Rec sym syms n)(if(eq? sym (car syms)) n (Rec sym (cdr syms) (+ n 1))))
(define (index sym syms) (Rec sym syms 0))
;(index '1 '(2 3 1 5 1))
;#5
(define (filter-by p L) (filtering p L '()))
(define (filtering p L finalList) (if(null? L ) finalList (if(p (car L) ) (filtering p (cdr L) (append finalList (list (car L)))) (filtering p (cdr L) finalList))))
;(filter-by number? '(12 3 a b '(a b c)))
;(filter-by symbol? '(12 3 a b '(a b c)))
;(filter-by list? '(12 3 a b '(a b c)))
(define (find-less n nums)
  (cond ((null? nums) '())
        ((< (car (filter-by number? nums)) n) (cons (car (filter-by number? nums)) (find-less n (cdr (filter-by number? nums)))))
        (else (find-less n (cdr (filter-by number? nums))))))
;(find-less 5 '(a b c 1 4 2 2 5 6))
(define (find-same n nums)
  (cond ((null? nums) '())
        ((= (car (filter-by number? nums)) n) (cons (car (filter-by number? nums)) (find-same n (cdr (filter-by number? nums)))))
        (else (find-same n (cdr (filter-by number? nums))))))
;(find-same 5 '(a b c 1 3 4 6 5))
(define (find-more n nums)
  (cond ((null? nums) '())
        ((> (car (filter-by number? nums)) n) (cons (car (filter-by number? nums)) (find-more n (cdr (filter-by number? nums)))))
        (else (find-more n (cdr (filter-by number? nums))))))
;(find-more 5 '(a b c 1 3 4 6 5))
(define (partition-accum pivot lst leq gt kp)
  (cond ((null? (filter-by number? lst)) (kp leq gt))
        ((> (car (filter-by number? lst)) pivot)
         (partition-accum pivot (cdr (filter-by number? lst)) leq (cons (car (filter-by number? lst)) gt) kp))
        (else
         (partition-accum pivot (cdr (filter-by number? lst)) (cons (car (filter-by number? lst)) leq) gt kp))))

(define (partition pivot lst kp)
  (partition-accum pivot (filter-by number? lst) '() '() kp))


(define (qsort-accum lst a) ; sort(lst) + a
  (if (null? (filter-by number? lst))
      a
      (partition (car (filter-by number? lst)) 
                 (cdr (filter-by number? lst))
                 (lambda (leq gt)
                   (qsort-accum leq (cons (car (filter-by number? lst)) (qsort-accum gt a)))))))

(define (qsort lst) (qsort-accum (filter-by number? lst) '()))
;(qsort '(2 5 6 1 3))