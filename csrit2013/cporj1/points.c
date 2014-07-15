// Author Ryan Vogt
//An implmentation of the points header file
//to compile: to compile: gcc -Wall -std=c99 -o point points.c

#include <stdio.h>
#include <string.h>
#include "points.h"
#include <stdlib.h>
#include <ctype.h>
#include <math.h>



int ccw(struct Point p, struct Point q, struct Point r)
{
   return (q.x-p.x)*(r.y-p.y)-(q.y-p.y)*(r.x-p.x);


}

void displayPoint(struct Point p)
{

printf("%c: (%d,%d)",p.label,p.x,p.y);

}


void displayPoints(struct Point points[], int numPoints)
{

printf("Set of Points:\n");
for(int i=0; i<numPoints; i++)
{

printf("%c: (%d,%d)\n",points[i].label,points[i].x,points[i].y);

}


}


int equal(struct Point p1, struct Point p2)
{

if(p1.x==p2.x && p1.y==p2.y)
{

return 1;

}

return 0;

}

int indexOf(struct Point p, struct Point points[], int numPoints)
{
int indexOfMatch=0;
for(int i=0;i<numPoints; i++)
{
struct Point currentStruct=points[i];

if(currentStruct.x==p.x && currentStruct.y==p.y)
{

return indexOfMatch;

}

indexOfMatch++;

}
return -1;
}


struct Point leftmostPoint(struct Point points[], int numPoints)
{

struct Point currentSmallestStruct=points[0];
for(int i=1; i<numPoints; i++)
{

struct Point current=points[i];
if(current.x<currentSmallestStruct.x)
{

currentSmallestStruct=current;

}
}

return currentSmallestStruct;
}

struct Point rightmostPoint(struct Point points[], int numPoints)
{


struct Point currentLargestStruct=points[0];
for(int i=1; i<numPoints; i++)
{

struct Point current=points[i];
if(current.x>currentLargestStruct.x)
{

currentLargestStruct=current;



}
}


return currentLargestStruct;

}

struct Point furthestLeftPoint(struct Point a, struct Point z, struct Point points[], int numPoints)
{
  float triangleAREA;
  struct Point furthestLeft;
  int counter=0;


for(int i=0; i <numPoints; i++)
{	

if(ccw(a,z,points[i])>0)
{

if(counter==0)
{	
triangleAREA=(.5)* abs((a.x - points[i].x) * (z.y-a.y) - (a.x - z.x)* (points[i].y - a.y));
furthestLeft=points[i];
counter++;
//printf("TRIANGLEAREA:%f\n",triangleAREA );
}
else
{


if(triangleAREA<(.5)* abs((a.x - points[i].x) * (z.y-a.y) - (a.x - z.x)* (points[i].y - a.y)))
{

triangleAREA=(.5)* abs((a.x - points[i].x) * (z.y-a.y) - (a.x - z.x)* (points[i].y - a.y));
furthestLeft=points[i];

}



}


}




}

//printf("FURTHESTLEFRT\n");
//displayPoint(furthestLeft);
//printf("FURTHESTLEFRT\n");


return furthestLeft;



}



int leftPointSet(struct Point a, struct Point z, struct Point points[], int numPoints, struct Point lps[])
{
int numberOfPointsToTheLeft=0;
int currentLpsele=0;

for(int i=0; i<numPoints; i++)
{

if(ccw(a,z,points[i])>0)
{

lps[currentLpsele]=points[i];
//printf("cross product point\n");
//displayPoint(points[i]);
currentLpsele++;
numberOfPointsToTheLeft++;



}


}
return numberOfPointsToTheLeft;


}

struct Point lowestPoint(struct Point points[], int numPoints)
{	

struct Point goodY[numPoints];
int goodYEle=0;
struct Point smallestY=points[0];
for(int i=0; i<numPoints; i++)
{
struct Point currentStruc= points[i];

if(currentStruc.y<smallestY.y)
{
goodYEle=0;
memset(goodY, 0, sizeof(goodY));
smallestY=currentStruc;

}

if(currentStruc.y==smallestY.y)
{

goodY[goodYEle]=currentStruc;
goodY[goodYEle+1]=smallestY;
goodYEle=goodYEle+2;



}



}

if(goodY==NULL)
{


return smallestY;

}
else
{
struct Point smallestx=goodY[0];
for(int i =1; i<goodYEle; i++)
{
struct Point currentStruc=points[i];
if(currentStruc.x<smallestx.x)
{
smallestx=currentStruc;


}



}
return smallestx;


}










}


int readPoints(struct Point points[])
{	

char currentLabel;
int currentX;
int currentY;
int pointsEle=0;
int counter=0;
int inputCounter=1;
char buff[MAX_LINE];
int numberOfPoints;
printf("Number of points(3-100):");
if(fgets(buff, MAX_LINE, stdin))
{

numberOfPoints= atoi(buff);

if(numberOfPoints<MIN_POINTS || numberOfPoints>MAX_POINTS)
{
printf("Number of points must be between 3 and 100.\n");
return 0;

}

while(counter<numberOfPoints)
{
if(inputCounter==1)
{

printf("Enter label (character): ");
if(fgets(buff, MAX_LINE, stdin))
{

currentLabel=buff[0];
inputCounter++;

}
else
{
printf("\nError reading point label.\n");
return 0;

}


}

else if(inputCounter==2)
{

printf("Enter x (int): ");
if(fgets(buff, MAX_LINE, stdin))
{
currentX=atoi(buff);
inputCounter++;
}
else
{
printf("\nError reading x coordinate.\n");
return 0;

}





}

else
{

printf("Enter y (int): ");
if(fgets(buff, MAX_LINE, stdin))
{
currentY=atoi(buff);
points[pointsEle].label=currentLabel;
points[pointsEle].x=currentX;
points[pointsEle].y=currentY;
pointsEle++;
inputCounter=1;
counter++;
}
else
{

printf("\nError reading y coordinate.\n");
return 0;



}

}


}











}
else
{
printf("\nError reading number of points.\n");
return 0;

}




//printf("%d number of points\n",numberOfPoints );
return numberOfPoints;




}

void swap(int a, int b, struct Point points[], int numPoints)
{

if((a<0) || (b<0) || (a>numPoints-1) || (b>numPoints) || (a==b))
{




}

else
{
struct Point P1=points[a];
struct Point P2=points[b];
points[a]=P2;
points[b]=P1;	
}

}
 
/*
int main( void ) {


struct Point a;
struct Point b;
a.x=1;
a.y=1;
b.x=6;
b.y=6;

struct Point allPoints[2];
struct Point c;
struct Point d;
struct Point e;
struct Point f;
c.x=10;
c.y=5;
d.x=1;
d.y=5;
e.x=-1;
e.y=2;
f.x=-5;
f.y=2;
allPoints[0]=c;
allPoints[1]=d;
//allPoints[2]=e;
//allPoints[3]=f;
struct Point show[2];
int size=leftPointSet(a,b,allPoints,2,show);
struct Point this=furthestLeftPoint(a,b,allPoints,2);
displayPoint(this);
displayPoints(show,size);
*/
/*struct Point myPoints[5];
struct Point test;
test.label='a';
test.x=6;
test.y=1;
struct Point test1;
test1.label='b';
test1.x=10;
test1.y=3;
struct Point test2;
test2.label='c';
test2.x=0;
test2.y=0;
struct Point lps[5];
int mypoints=readPoints(myPoints);
displayPoints(myPoints,mypoints);
printf("%d\n",indexOf(test,myPoints,mypoints));
displayPoint(leftmostPoint(myPoints,mypoints));
displayPoint(rightmostPoint(myPoints,mypoints));
displayPoint(furthestLeftPoint(test,test1,myPoints,mypoints));
int here=leftPointSet(test,test1,myPoints,mypoints,lps);
displayPoints(lps,here);
printf("=====");
displayPoint(lowestPoint(myPoints,mypoints));
printf("============\n");
displayPoint(makeVector(test1,test2));
printf("%d\n",ccw(test,test2,test1));
*/
//return( 0 );
//}
