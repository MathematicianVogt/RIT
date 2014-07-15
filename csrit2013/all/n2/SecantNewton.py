#Written by Ryan Vogt
#CS/MATH MAJOR AT RIT
import math
class SecantNewton:
    
    def __init__(self,po,p1,tol,num):
        self.po=po
        self.p1=p1
        self.tol=tol
        self.num=num
        self.counter=2
        self.q0=0
        self.q1=0
        self.p=0
    
    def evalFun(self,x):
        return x**2 -6*x +9

    def Algo(self):
        self.q0=self.evalFun(self.po)
        self.q1=self.evalFun(self.p1)
        while(self.counter<=self.num):
            self.p=self.p1 -(self.q1*(self.p1-self.po))/(self.q1-self.q0)
            if((self.p-self.p1)<self.tol and self.evalFun(self.p)==0):
                print "Root found at " + str(self.p) + " in " + str(self.counter) + " iterations"
                break
                
                
            self.counter=self.counter+1
            self.po=self.p1
            self.q0=self.q1
            self.p1=self.p
            self.q1=self.evalFun(self.p)
            
            
            
            
        
po=raw_input("Enter p0")
p1=raw_input("Enter p1")
tol=raw_input("Enter error")
num=raw_input("Enter iterations")
x=SecantNewton(float(po),float(p1),float(tol),float(num))
x.Algo()