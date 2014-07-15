#Ryan Vogt
#MATH/CS MAJOR AT RIT
import math 
class ImprovedNewton:
    def __init__(self,po,tol,num):
        self.po=po
        self.tol=tol
        self.num=num
        self.counter=1
        self.p=0
    
    def evalFun(self,x):
       return x**2 -6*x + 9
        #return math.exp(3*x) - 27*(x**6) + 27*(x**4)*math.exp(x) -9*(x**2)*(math.exp(2*x))
    def evalDevOne(self,x):
        return 2*x-6
        #return 3*math.exp(3*x) -162*(x**5) + 27*((x**4)*math.exp(x) + math.exp(x)*4*(x**3)) -9*((x**2)*2*math.exp(2*x) + math.exp(2*x)*2*x)
    def evalDevTwo(self,x):
        return 2
        #return 9*math.exp(3*x) - 810*(x**4) + 27*((x**4)*math.exp(x) + math.exp(x)*4*(x**3) + math.exp(x)*12*(x**2) + 4*(x**3)*math.exp(x)) -9*((x**2)*4*math.exp(2*x) + math.exp(2*x)*2 + math.exp(2*x)*2 + 4*x*math.exp(2*x))
    def Algo(self):
        while(self.counter<=self.num):
            self.p=self.po -((self.evalFun(self.po)*self.evalDevOne(self.po))/(math.pow(self.evalDevOne(self.po), 2)-(self.evalDevOne(self.po)*self.evalDevTwo(self.po))))
           
            if(math.fabs((self.p-self.po))<self.tol ):
                print "Root found at " + str(self.p) + " in " + str(self.counter) + " iterations"
                break
                
                
            self.counter=self.counter+1
            self.po=self.p
            
            
            
            
        
po=raw_input("Enter p0")
tol=raw_input("Enter error")
num=raw_input("Enter iterations")
x=ImprovedNewton(float(po),float(tol),float(num))
x.Algo()