// Author Ryan Vogt
//An implmentation of the quickhull algorithm to find the convex hull from a set
//of points P
//to compile: to compile: gcc -Wall -std=c99 -o point points.c
#include <stdio.h>
#include <string.h>
#include "points.h"
#include <stdlib.h>
#include <ctype.h>



void displayConvexPoints(struct Point points[], int numPoints){
   
   printf("Convex Hull:\n");
   for(int i=0; i<numPoints; i++)
   {
       struct Point currentPointToShow=points[i];
       printf("%c: (%d,%d)\n",currentPointToShow.label,currentPointToShow.x,currentPointToShow.y);
       
       
   }
   
   
   
}

//take three arrays of certain pieces and combine them together
//@param bigL array of left elements
//@param bigLsize the size of bigL
//@param bigR the array of right elemeents
//@param bigRsize the size of right eleements array
//@param f the points furthest from the line
//@param P the original array of points,
//@param PSize the size of P
//@return number of slots filled
int overridePThree(struct Point bigL[],int bigLSize,struct Point bigR[], int bigRsize, struct Point f, struct Point P[])
{
   int where=0;
   for(int i=0; i<bigLSize;i++)
   {
       
       P[where++]=bigL[i];
       
   }
   P[where++]=f;
   
   for(int i= 0; i<bigRsize; i++)
   {
       
       
       P[where++]=bigR[i];
       
       
   }

   //printf("%d Number of threeP\n", where );
   return where;
   
}
//take four arrays of certain pieces and combine them together
//@param bigL array of left elements
//@param bigLsize the size of bigL
//@param bigR the array of right elemeents
//@param bigRsize the size of right eleements array
//@param r the most right point
//@param l the most left point
//@param P the original array of points,
//@param PSize the size of P
//@param the new size of P
int overridePFour(struct Point l, struct Point bigL[],int bigLSize, struct Point r, struct Point bigR[], int bigRsize, struct Point P[])
{
    int where=0;
    P[where++]=l;
   for(int i =0; i<bigLSize; i++)
   {
       
       P[where++]=bigL[i];
       
       
   }
  
   P[where++]= r;
   for(int i=0; i<bigRsize; i++)
   {
       
       
       P[where++]=bigR[i];
       
       
   }
  
   return where;
  
   
}


//A method that helps the recurisve quickHull algorithm.
//@param P, the original set of points
//@param l the left most point in P
//@param r the right most point in P
int quickHullRec(struct Point points[], struct Point l, struct Point r, int numPoints)
{
   


   if(numPoints==0)
   {

      return 0;

   }

  else
  {

    struct Point f=furthestLeftPoint(l,r,points,numPoints);
    //printf("DIPLAYFURTHEST\n" );
    //displayPoint(f);


    struct Point L[numPoints];
    int Lsize=leftPointSet(l, f, points,numPoints,L);
    //printf("\nL\n");
    //displayPoints(L,Lsize);
    struct Point R[numPoints];
    int Rsize=leftPointSet(f,r,points,numPoints,R);
    //printf("\nR\n");
    //displayPoints(R,Rsize);

    quickHullRec(L,l,f,Lsize);
    quickHullRec(R,f,r,Rsize);
    overridePThree(L,Lsize,R,Rsize,f,points);
  

      /*printf("==========dsd\n");
    displayPoints(L,Lsize);
    printf("\n");
    printf("yo");
    displayPoint(f);
    printf("\n");
    displayPoints(R,Rsize);
    printf("\n");
    printf("QUICKHULLREC");
    displayPoints(points,size);
    */

    return 2;

  }
   

   
   
}

//A method that does the quickHull algorithm. Takes a set of points and number of points
//in this set that are valid, find the points in this set and add them to convexHull
//@param points, the set of points, a subset of these points will be the convex hull
//@param numPoints, the number of points in points that are valid
//@param convexHull, this will be manipualted from empty to containing the complex hull
int quickHull(struct Point points[], struct Point convexHull[],int numPoints)
{
   
   struct Point mostLeftPoint=leftmostPoint(points,numPoints);
   //printf("MOSTLEFT\n");
   //displayPoint(mostLeftPoint);
   //printf("\n");

   struct Point mostRightPoint=rightmostPoint(points,numPoints);
   //printf("MOSTRIGHT\n");
   //displayPoint(mostRightPoint);
   //printf("\n");
   struct Point bigL[numPoints];
   

   int bigLnumber=leftPointSet(mostLeftPoint,mostRightPoint,points,numPoints,bigL);
   //printf("BIGLNUM %d \n",bigLnumber );
   //printf("BIGL\n");
   //displayPoints(bigL,bigLnumber);
   //printf("BIGLEND\n");

   struct Point bigR[numPoints];
   int bigRnumber=leftPointSet(mostRightPoint,mostLeftPoint,points,numPoints,bigR);
   //printf("BIGRNUM %d \n",bigRnumber );
   //printf("BIGR\n");
   //displayPoints(bigR,bigRnumber);
   //printf("BIGREND\n");
   
   int finalL=quickHullRec(bigL,mostLeftPoint,mostRightPoint,bigLnumber);
   int finalR=quickHullRec(bigR,mostRightPoint,mostLeftPoint,bigRnumber);
   overridePFour(mostLeftPoint,bigL,bigLnumber,mostRightPoint,bigR,bigRnumber,convexHull);

   //printf("ooverridePF\n");
   //printf("QUICKHULL");
   /*displayPoint(mostLeftPoint);
    printf("\n");
    displayPoints(bigL,bigLnumber);
    printf("\n");
    displayPoint(mostRightPoint);
    printf("\n");
    displayPoints(bigR,bigRnumber);
    printf("\n");
    displayPoints(convexHull,size); 
    */
   
   
   //printf("%d FINAL SUM\n", finalL+finalR );
   return finalR+finalL+1;
   
   
   
   
   
   
   
}


int main(void)
{


   struct Point quickHullPoints[MAX_POINTS];
   int numberOfquickHullPoints=readPoints(quickHullPoints);
   if(numberOfquickHullPoints>0)
  {
      displayPoints(quickHullPoints,numberOfquickHullPoints);
      struct Point convexHullSet[numberOfquickHullPoints];
      int numberOfConvexPoints=quickHull(quickHullPoints,convexHullSet,numberOfquickHullPoints);
      displayConvexPoints(convexHullSet,numberOfConvexPoints);
   }
   
   
   
}


