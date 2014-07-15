import math
class DividedDifference():
    def getInput(self):
        numList=[]
        numbs=input("Enter a list of function inputs separated by commas: ").split(",")
        numbs2=[float(i) for i in numbs]
        numList.append(numbs2)
        theList=[]
        numbs=input("Enter a list of function ouputs separated by commas: ").split(",")
        numbs2=[float(i) for i in numbs]
        theList.append(numbs2)
        eval(numList,theList)
    def eval(self,x,y):
        input=[]
        output=[]
        if(len(x)!=len(y)):
            print"bad list"
        elif(len(x)==1):
            print "Solution: Found " +  x[0]
        for t in range (0,len(x)):
           for r in range (0,t ):
               input[t]=x[t]-x[t+1]
               ouput[t]=y[t]-y[t+1]
               
        eval(input,output)
        
x=DividedDifference()
x.getInput()
