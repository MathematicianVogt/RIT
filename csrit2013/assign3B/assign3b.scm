;; Author: Arthur Nunes-Harwitt

;; Import the parser and lexer generators.


(require (lib "yacc.ss" "parser-tools")
         (lib "lex.ss" "parser-tools")
         (prefix : (lib "lex-sre.ss" "parser-tools")))

(require (lib "pretty.ss"))

(define-tokens value-tokens (NUM ID))

(define-empty-tokens op-tokens
  (OP 
   CP
   COMMA
   EQ1
   LET 
   IN 
   + 
   - 
   * 
   /
   EOF
   PROGRAM
   CLASS
   METHOD
   NEW
   SUPERCALL
   SEQ
   PROCS
   IF
   ASSIGN
   EQUAL
   PROC
   ACCESS
   FUNCALL
   ARROW
   \
   OB
   CB
   THEN
   ELSE
   EXTENDS
   FIELD
   SEMI
   PERIOD
   ESP
   PROCEDURES
   \\
   ))

(define-lex-abbrevs
  (lower-letter (:/ "a" "z"))
  (upper-letter (:/ "A" "Z"))
  (letter (:or lower-letter upper-letter))
  (digit (:/ "0" "9"))
  (idfirst (:or letter (:or "_" "$")))
  (idrest (:or idfirst digit))
  (ident (:: idfirst (:* idrest)))
  (number (:: (:: (:+ digit) (:?(:: "." (:+ digit)))) (:? (:: (:: (:or "E" "e") (:? (:or "+" "-"))) (:+ digit)))  )))



;get-token: inputPort -> token
(define get-token
  (lexer
   ((eof) 'EOF)
   ("let" 'LET)
   ("in" 'IN)
   ("(" 'OP)
   (")" 'CP)
   ("," 'COMMA)
   ("=" 'EQ1)
   ("+" '+)
   ("-" '-)
   ("*" '*)
   ("/" '/)
   ("class" 'CLASS)
   ("if" 'IF)
   ("==" 'EQUAL)
   ("extends" 'EXTENDS)
   ("->" 'ARROW)
   ("\\" '\\)
   ("{" 'OB)
   ("}" 'CB)
   ("then" 'THEN)
   ("else" 'ELSE)
   ("field" 'FIELD)
   ("method" 'METHOD)
   ("new" 'NEW)
   ("super" 'SUPERCALL)
   ("=" 'ASSIGN)
   (";" 'SEMI)
   ("." 'PERIOD)
   ("" 'ESP)
   ("procedures" 'PROCEDURES)
   ("\\" '\\)
   
   (number (token-NUM (string->number lexeme)))
   (ident (token-ID (string->symbol lexeme)))
   (whitespace (get-token input-port))
   
   
   ))
  



;;; data definitions

;; A small language expression (SmallLangExp) is one of the following.
;; a number n
;; an identifier x
;; a sum with parts e1 and e2, 
;;   where e1 and e2 are small language expressions
;; a difference with parts e1 and e2, 
;;   where e1 and e2 are small language expressions
;; a product with parts e1 and e2,
;;   where e1 and e2 are small language expressions
;; a quotient with parts e1 and e2, 
;;   where e1 and e2 are small language expressions
;; a negation with part e,
;;   where e is an small language expression
;; a bindings with parts defs and e, 
;;   where defs is a list of identifiers * SmallLangExp
;;   and e is an small language expression

;; functions for associated with each part: predicate, constructor, selectors.

;; Number is a Scheme number

;; Identifier is a Scheme symbol

; make-sum: SmallLangExp * SmallLangExp -> SumExp
(define (make-sum exp1 exp2)
  (list 'sum exp1 exp2))

; (equal? (make-sum 2 3) '(sum 2 3))


; make-diff: SmallLangExp * SmallLangExp -> DiffExp
(define (make-diff exp1 exp2)
  (list 'diff exp1 exp2))

; (equal? (make-diff 2 3) '(diff 2 3))


; make-prod: SmallLangExp * SmallLangExp -> ProdExp
(define (make-prod exp1 exp2)
  (list 'prod exp1 exp2))

; (equal? (make-prod 2 3) '(prod 2 3))

; make-quo: SmallLangExp * SmallLangExp -> QuoExp
(define (make-quo exp1 exp2)
  (list 'quo exp1 exp2))

; (equal? (make-quo 2 3) '(quo 2 3))

; make-neg: SmallLangExp -> NegExp
(define (make-neg exp)
  (list 'neg exp))

; (equal? (make-neg 2) '(neg 2))

; make-let: Listof(Identifier*SmallLangExp) * SmallLangExp -> BindingExp
; Identifier*SmallLangExp is represented as a two element list
(define (make-let defs exp)
  (list 'with-bindings defs exp))

; (equal? (make-let (list (list 'x 1) (list 'y 2)) 3) '(with-bindings ((x 1) (y 2)) 3))

; parse-lang: (() -> token) -> LargeLangExp
(define parse-lang
  (parser
   (start exp)
   (end EOF)
   (tokens value-tokens op-tokens)
   (error (lambda (a b c) (error 'parse-small-lang "error occurred, ~v ~v ~v" a b c)))
   (grammar
    
    (program ((classdecls exp) (make-program $1 $2) ))
    (classdecls ((ESP) ('null))
                ((classdecl classdecls) (cons $1 $2)))
    (classdecl  ((CLASS ID EXTENDS ID OB fielddecls methdecls CB) (make-class $2 $4 $6 $7))
                )
    (fielddecls ((ESP) ('null))
                ((FIELD ID fielddecls) (cons $2 $3))
                )
    (methdecls ((ESP) (list 'null))
               ((METHOD ID OP formals CP exp methdecls) (cons (make-method $2 $4 $6) $7))
               )
    
               
    
                
                
                
                
                
    
    
     
    
    
    (exp ((LET let-defs IN exp) (make-let $2 $4))
         ((PROCEDURES procdefs IN exp) (make-proc $2 $4))
         ((\\ formals \\ ARROW exp) (make-proc $2 $5))
         ((OB exps CB) (make-seq $2))
         ((IF exp THEN exp ELSE exp) (make-if $2 $4 $6))
         ((NEW ID OP actuals CP) (make-new $2 $4))
         ((SUPERCALL ID OP actuals CP) (make-supercall $2 $4))
         ((ID ASSIGN exp) (make-assign $1 $3))
         ((math-exp) $1)
         ((compexp) ($1))
         
         )
    (procdefs ((procdef) (list $1))
              ((procdef COMMA let-defs) (cons $1 $3))
              )
    (procdef ((ID OP formals CP ASSIGN exp) (list $1 (make-proc $3 $6)))
             )
    (exps ((exp) (list $1))
          ((exp SEMI exps) (cons $1 $3))
          
          )
    (compexp ((math-exp EQUAL math-exp) (make-equaility $1 $3))
             ((math-exp) ($1))
             )
    
    
    (let-def ((ID EQ1 exp) (list $1 $3)))
    (let-defs ((let-def) (list $1))
              ((let-def COMMA let-defs) (cons $1 $3)))
    (math-exp ((math-exp + term) (make-sum $1 $3))
              ((math-exp - term) (make-diff $1 $3))
              ((term) $1))
    (term ((term * factor) (make-prod $1 $3))
          ((term / factor) (make-quo $1 $3))
          ((factor) $1))
    (factor ((ID) $1)
            ((NUM) $1)
            ((- factor) (make-neg $2))
            ((OP exp CP) $2)
            ((simple) $1)
            )
   (simple ((ID) ($1))
           ((simple PERIOD ID) (make-access $1 $3))
           ((simple OP actuals CP) (make-funcall $1 $3))
           ((OP exp CP) ($1))
         
    )
   (actuals ((ESP) ('null))
            ((nonemptyformals) ($1))
            )
   (nonemptyactuals ((exp) (list $1))
                    ((exp COMMA nonemptyactuals) (cons $1 $3))
                    )
   (formals ((ESP) ('null))
            ((nonemptyformals) ($1))
            )
   (nonemptyformals ((ID) (list $1))
                    ((ID COMMA nonemptyformals) (cons $1 $3)))
   )))






;Ryan Vogt
;1
;predicates
;sum function to determine if a construction has sum structure list->boolean
(define (sum? L) (if(and (pair? L) (eq? 'sum (car L))) #t #f))
;difference function to determine if a construction has difference structure list->boolean
(define (difference? L) (if(and (pair? L) (eq? 'diff (car L))) #t #f))
;product function to determine if a construction has product structure list->boolean
(define (product? L) (if(and (pair? L) (eq? 'prod (car L))) #t #f))
;quotient function to determine if a construction has quotient structure list->boolean
(define (quotient? L) (if(and (pair? L) (and (eq? 'quo (car L)) (not(eq? (car(cdr(cdr L))) '0)))) #t #f)) 
;negate function to determine if a construction has negate structure list->boolean
(define (negate? L) (if(and (pair? L) (eq? 'neg (car L))) #t #f))
;let function to determine if a construction has let structure list->boolean
(define (let? L) (if(and (pair? L) (eq? 'with-bindings (car L))) #t #f))

;selectors

;function that gets the second element in a list list->element
(define (arg1 L) (car (cdr L)))
;function that gets the third element in a list list->element
(define (arg2 L) (car (cdr ( cdr L))))
;function uses the arg1 function to get the expression of the neg list->element
(define (neg-exp L) (arg1 L))
;function uses the arg1 function to get the first expresion of the let list list->element
(define (let-defs) (arg1 L))
;function uses the arg2 function to get the second expression of the let list list->element
(define (let-exp) (arg2 L))

;2
;predicates

;program function checks if a list has the structure of a program list->boolean
(define (program? L) (if(and (pair? L) (eq? (car L) 'program)) #t #f))
;class decl function checks if something is a class decls list->boolean
(define (class-decl? L) (if(and (pair? L) (eq? (car L ) 'class)) #t #f))
;method function checks if a list has the structure of a method list->boolean
(define (method? L) (if(and (pair? L) (eq? (car L ) 'method)) #t #f))
;new function checks if a list has the structure of a new list->boolean
(define (new? L) (if(and (pair? L) (eq? (car L ) 'new)) #t #f))
;supercall function checks if a list has the structure of a supercall list->boolean
(define (supercall? L) (if(and (pair? L) (eq? (car L ) 'super)) #t #f))
;seq function checks if a list has the structure of a seq list->boolean
(define (seq? L) (if(and (pair? L) (eq? (car L ) 'sequence)) #t #f))
;procs function checks if a list has the structure of a procs list->boolean
(define (procs? L) (if(and (pair? L) (eq? (car L ) 'procedures)) #t #f))
;if function checks if a list has the structure of a if list->boolean
(define (if? L) (if(and (pair? L) (eq? (car L ) 'if)) #t #f))
;assign function checks if a list has the structure of a assign list->boolean
(define (assign? L) (if(and (pair? L) (eq? (car L ) 'assign!)) #t #f))
;equality function checks if a list has the structure of a equality list->boolean
(define (equality? L) (if(and (pair? L) (eq? (car L ) 'equality?)) #t #f))
;proc function checks if a list has the structure of a proc list->boolean
(define (proc? L) (if(and (pair? L) (eq? (car L ) 'proc)) #t #f))
;access function checks if a list has the structure of a access list->boolean
(define (access? L) (if(and (pair? L) (eq? (car L ) 'send)) #t #f))
;funcall function checks if a list has the structure of a funcall list->boolean
(define (funcall? L) (if(and (pair? L) (eq? (car L ) 'funcall)) #t #f))

;constructors 
;takes abstract syntax tree of e1 and e2 and makes them into a program e1 e2 -> list
(define (make-program e1 e2) (list 'program e1 e2))
;takes abstract syntax tree of e1,e2,e3,e4 and makes them into a class e1 e2 e3 e4 -> list
(define (make-class e1 e2 e3 e4) (list 'class e1 e2 e3 e4))
;takes abstract syntax tree e1 e2 e3 e4 and makes them into a method
(define (make-method e1 e2 e3 e4) (cons 'method(cons e1 (cons e2 (cons e3 e4)))))
;takes abstract syntax tree e1 e2 and makes them into a new structure
(define (make-new e1 e2) (cons 'new (cons e1 e2)))
;takes abstract syntax tree e1 and e2 and makes super structure
(define (make-supercall e1 e2) (list 'super e1 e2))
;takes abstract syntax tree e1 e2 to make a seq
(define (make-seq e1) (list 'sequence e1))
;takes abstract syntax tree e1 e2 to make a procs
(define (make-procs e1 e2) (list 'procedures e1 e2))
;takes syntax trees and makes a if structure
(define (make-if e1 e2 e3) (list 'if e1 e2 e3))
;takes syntax trees and make a assign structure
(define (make-assign e1 e2) (list 'assign! e1 e2))
;takes the syntax trees and makes an equal structure
(define (make-equal e1 e2) ('equality? e1 e2))
;take the syntax trees and makes a proc structure
(define (make-proc e1 e2) (list 'proc e1 e2))
;take the syntax trees and makes a acess structure
(define (make-access e1 e2) ('list 'send e1 e2))
;take the syntax trees and make a func structure
(define (make-funcall e1 e2) (cons 'funcall (cons e1 e2)))

;selectors

;function to get the decls of a program structure
(define (program-decls L ) (arg1 L))

;function to get the exp of a program structure
(define (program-exp L ) (arg2 L))

;function to get the name of a class
(define (class-name L) (arg1 L))

;function to get the parent of a class
(define (class-parent L) (arg2 L))
;function to get the fields of a class
(define (class-fields L) (car (cdr (cdr L))))
;function to get methods of a class
(define (class-methods L ) (cdr (cdr (cdr (cdr L)))))
;function to get the name of a new structure
(define (new-name L) (arg1 L))
;function to get the rands of a new structure
(define (new-rands L ) (cdr(cdr L)))
;function to find the name of a supercall struct
(define (supercall-name L) (arg1 L))
;function to find the rands of a supercall struct
(define (supercall-rands L ) (arg2 L))
;function to find exps of a seq
(define (seq-exps) (arg1 L))
;function to get the defs of a procs structure
(define (procs-defs L) (arg1 L))
;function to get the exp of a procs structure
(define (procs-exp L ) (arg2 L))
;function that gets the exp1 of if structure
(define (if-exp1 L) (arg1 L))
;function that gets the exp2 of a if structure
(define (if-exp2 L) (arg2 L))
;function that gets the exp 3 of a if structure
(define (if-exp3 L) (cdr (cdr (cdr L))))
;function to find var of a assign structure
(define (assign-var L) (arg1 L))
;function to find exp of a assign structure
(define (assign-exp L) (arg2 L))
;function that gives the formals of a proc structure
(define (proc-formals L) (arg1 L))
;function that gives the exp of a proc structure
(define (proc-exp L) (arg2 L))
;function that gets the exp of a acesss structure
(define (access-exp L) (arg1 L))
;function that gets the message of a access structure
(define (access-message L) (arg2 L))
;function to get the rator of a funcall structure
(define (funcall-rator L) (arg1 L))
;function to get the rands of a funcall structure
(define (funcall-rands L) (cdr(cdr L)))


