
#Written by Ryan Vogt
#CS/MATH MAJOR AT RIT
import math
class FixedPoint:
    def __init__(self,po,iteration,error):
        self.po=po
        self.iteration=iteration
        self.error=error
        self.counter=1
        
    
    def evalFunction(self,x):
            #used subsition on return code to be function of interest
            # for a-d used same code for all fixed point problems
            #return x*(1+((7-(x**5))/(x**2)))**3
            #return x- (((x**5)-7)/((x**2)))
            #return x- (((x**5)-7)/(5*(x**4)))
            #return x- (((x**5)-7)/((12)))
            #return (x+1)**(.33333)
            return 2**(-x)
            #return (-x**3 +650)**(.166666)
    def algo(self):
        while (self.counter <= self.iteration):
            p=self.evalFunction(self.po)
            if(math.fabs(p-self.po)<self.error):
                print "solution found at " + str(p) + ' in ' +str(self.counter) + ' permutations.' + str(math.fabs(p-self.po))
                break
            elif (self.counter==1):
                print 'p3 is ' + str(p)
                self.counter=self.counter+1
                
           
            else:
                self.counter=self.counter+1
                self.po=p    
        
        
        
        
a=raw_input("Enter initial Po")
b=raw_input("Enter Number of Iterations")
error=raw_input("Enter error")            
biOb=FixedPoint(float(a), float(b), float(error))
biOb.algo()