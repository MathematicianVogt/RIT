;#1 union: set1 set2 -> set3
;A function that unions two sets, this set will not have multiple instances of one element
(define (union set1 set2) (unionHelp set1 (append '() set2)))
;A helperfunction for union function to actually compute the union of two sets
(define (unionHelp set1 finalSet) (if(null? set1) finalSet (if(member (car set1) finalSet) (unionHelp (cdr set1) finalSet) (unionHelp (cdr set1) (append (list(car set1)) finalSet)))))
;(union '(1 2 3 4) '(2 4 5 7 6 3))
;Given code
; Terminals are quoted.
; A rule A -> X1 ... Xn is written (A (X1 ... Xn))
; A grammar is a list of rules.

(define *grammar*
  '((S (E 'eof))
    (E (T E2))
    (E2 ('+ T E2))
    (E2 ('- T E2))
    (E2 ())
    (T (F T2))
    (T2 ('* F T2))
    (T2 ('/ F T2))
    (T2 ())
    (F ('n))
    (F ('id))
    (F ('- F))
    (F ('OP E 'CP))))

; rule-lhs : Rule -> Variable
(define rule-lhs car)

; (eq? (rule-lhs '(E (T E2))) 'E)

; rule-rhs : Rule -> List(Variables or Terminals)
(define rule-rhs cadr)

; (equal? (rule-rhs '(E (T E2))) '(T E2))

; variable? : Any -> Boolean
(define variable? symbol?)

; (variable? 'E)
; (not (variable? ''+))

; terminal? : Any -> Boolean
(define (terminal? a) (and (pair? a) (eq? (car a) 'quote)))

; (terminal? ''+)
; (not (terminal? 'E))


;#2
;get rules method written to get a set of rules for a specific variable. variable name list-> list
(define (getRules rule L finalRuleList) (if(null? L) finalRuleList (if(equal? (car (car L)) rule) (getRules rule (cdr L) (append (list (car L)) finalRuleList)) (getRules rule (cdr L) finalRuleList))))
;seen? a method that takes a rule and a rulelist and sees if if the rule is in the rule list list list-> boolean
(define (seen? specificRule ruleList) (if(null? ruleList) #f (if(equal? specificRule (car ruleList)) #t (seen? specificRule (cdr ruleList)))))
;containsEpsilon? Sees if a list contains epsilon list-> boolean
(define (containsEpsilon? L) (if(null? L) #f (if(equal? (car L) '()) #t (containsEpsilon? (cdr L)))))
;get the right handed rules for a list that share the same left handed rules
(define (getRightRulesForList rule FinalList) (if(null? rule) FinalList (getRightRulesForList (cdr rule) (append (list(rule-rhs (car rule))) FinalList))))
;first 3 grammar alpha seen - > list of terminals
(define (first3 grammar alhpa seen) (first3Helper grammar alpha seen '()))
;helper function for first 3 actually goes through if a terminal is found add to the list done, if not expand variables until terminals are found for all possibilites
(define(first3Helper grammar alpha seen finalList)
  (cond
    ((null? alpha) (union finalList '())) 
    ((containsEpsilon? (getRightRulesForList (getRules (car alpha) grammar '()) '() )) (first3Helper grammar (cdr alpha) (cons (car alpha) seen) finalList))
    ((seen? (car alpha) seen) (first3Helper grammar (cdr alpha) seen finalList))
    ((terminal? (car alpha)) (union (car alpha) finalList))
    ((variable? (car alpha)) (first-var3 grammar (getRightRulesForList (car alpha) '()))
    )))

; expands varaibles grammar rules seen -> terminals from expansions
(define (first-var3 grammar rules seen) (firstvar3Helper grammar rules seen '()))
(define (firstvar3Heleper grammar rules seen finalList) (if(null? rules) finalList 
                                                           (if(terminal? (car(car rules))) (union (car(car rules)) finalList)
                                                             (firstvar3Helper grammar (getRightRulesForList (car(car rules))) (cons (car(car rules))  seen)  finalList))))
;takes a grammar and alpha returns a list of terminals in regards to first non epsilon term of alpha
(define (first-alpha grammar alpha) (first3 grammar alpha '()))
                                       

;#3
;follow var function grammar var-> list of terminals after var
(define (follow-var grammar var)


)
;helper function for follow var, grammar var rules seen-> list of terminals
(define (follow-rules4 grammar var rules seen) )


;was so confused on this assginment, didnt know how the pieces went together and couldnt think how to handle everything all at once
;terminal function didnt make sense to me and other things as well, overall lost and confused, cant even do number 3 because no idea what it means
;#1 was trivial and the others were just impossible for me.



