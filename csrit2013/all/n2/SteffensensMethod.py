#Ryan Vogt
#MATH/CS MAJOR AT RIT
import math 
class SteffensensMethod:
    def __init__(self,po,tol,num):
        self.po=po
        self.tol=tol
        self.num=num
        self.counter=1
        self.p=0
        self.p1=0
        self.p2=0
    
    def evalFun(self,x):
        #fixed point
        
        return math.sqrt(-6*x-9)

    def Algo(self):
        while(self.counter<=self.num):
            self.p1=self.evalFun(self.po)
            self.p2=self.evalFun(self.p1)
            self.p=self.po - (((self.p1-self.po)**2)/(self.p2-(2*self.p1)+self.po))
           
            
            if(math.fabs((self.p-self.po))<self.tol ):
                print "Root found at " + str(self.p) + " in " + str(self.counter) + " iterations"
                break
                
            #print str(math.fabs(self.p-self.po))    
            self.counter=self.counter+1
            self.po=self.p
            
            
            
            
        
po=raw_input("Enter p0")
tol=raw_input("Enter error")
num=raw_input("Enter iterations")
x=SteffensensMethod(float(po),float(tol),float(num))
x.Algo()