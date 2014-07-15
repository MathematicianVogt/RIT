import math

#Written by Ryan Vogt
#CS/MATH MAJOR AT RIT
class Bisection:
    def __init__(self,a,b,error,iterations):
        self.a=a
        self.b=b
        self.error=error
        self.iterations=iterations
        self.counter=0
    def doable(self,fa,fb):
        return fa*fb<0
        
    
   
    def evalFunction(self,x):
            #used subsition on return code to be function of interest
            # for a-d used same code for all bisection problems
            return (x+2)*((x+1)**2)*(x)*((x-1)**3)*(x-2)
        
    def Algo(self):
       
        while(self.counter < self.iterations):
                midpoint = (self.a + self.b) / 2
                self.counter = self.counter + 1
           
                if(self.evalFunction(midpoint) == 0 or (self.b - self.a) / 2 < self.error):
                    print "Found zero for F(x) which was " + str(midpoint)
                    break;
              
           
           
        
             
                if(self.evalFunction(midpoint)*self.evalFunction(self.a) > 0):
                    self.a = midpoint
                else:
                    self.b = midpoint    
        print 'done'          
               
a=raw_input("Enter lower bound")
b=raw_input("Enter upper bound")
error=raw_input("Enter error")
iterations=raw_input("Enter number of attempts")
objectOne=Bisection(float(a),float(b), float(error), float(iterations))
objectOne.Algo()
    