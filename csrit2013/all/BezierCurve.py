#Ryan Vogt 
class one:
    def algo(self):
       xlist=[3,2,6,5,6.5]
       ylist=[6,2,6,2,3]
       alphalist=[3.3,2.8,5.8,5.5]
       betalist=[6.5,3.0,5.0,2.2]
       alphaprimelist=[2.5,5,4.5,6.4]
       betaprimelist=[2.5,5.8,2.5,2.8]
       for i in range(0,3):
        ao=xlist[i]
        bo=ylist[i]
        a1=3*(xlist[i]+alphalist[i]-xlist[i])
        b1=3*(ylist[i]+betalist[i]-ylist[i])
        a2=3*(xlist[i]+xlist[i+1]-alphalist[i+1] -2*(xlist[i]+alphalist[i]))
        b2=3*(ylist[i]+ylist[i+1]-betalist[i+1] -2*(ylist[i]+betalist[i]))
        a3=xlist[i+1]+alphalist[i+1]-xlist[i]+3*(xlist[i]+alphalist[i])-3*(xlist[i+1]-alphalist[i+1])
        b3=ylist[i+1]+betalist[i+1]-ylist[i]+3*(ylist[i]+betalist[i])-3*(ylist[i+1]-betalist[i+1])
        print str(i) + " " + str(ao) + " " +str(bo) + " " + str(a1) + " " + str(b1) + " " + str(a2) + " " + str(b2) + " " + str(a3) + " " + str(b3) 
        
x=one()
x.algo()

'''
output
0 3 6 9.9 19.5 -31.2 -60.0 23.1 39.5
1 2 2 8.4 9.0 -22.2 -21.0 23.6 21.0
2 6 6 17.4 15.0 -54.3 -48.6 41.4 31.8
'''
