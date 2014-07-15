#Written by Ryan Vogt
#CS/MATH MAJOR AT RIT
import math
class RegNewton:
    
    def __init__(self,po,tol,num):
        self.po=po
        self.tol=tol
        self.num=num
        self.counter=1
        self.p=0
    
    def evalFun(self,x):
        return x**2 -6*x +9
    def evalDev(self,x):
        return 2*x-6

    def Algo(self):
    
        while(self.counter<=self.num):
            self.p=self.po -(self.evalFun(self.po)/self.evalDev(self.po))
            if((self.p-self.po)<self.tol and self.evalFun(self.p)==0):
                print "Root found at " + str(self.p) + " in " + str(self.counter) + " iterations"
                break
                
                
            self.counter=self.counter+1
            self.po=self.p
            
            
            
            
        
po=raw_input("Enter p0")
tol=raw_input("Enter error")
num=raw_input("Enter iterations")
x=RegNewton(float(po),float(tol),float(num))
x.Algo()