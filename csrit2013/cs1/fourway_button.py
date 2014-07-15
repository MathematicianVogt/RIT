# John Bero IV,  homework 1,   computer science 1
import turtle
def homeposition():
# sets a position for the turtle to return to after each step.
    turtle.setx(0)
    turtle.sety(0)
def drawtriangle():
# this is the function that explains how the triangles should be drawn.
    turtle.hideturtle()
    turtle.up()
    turtle.left(90)
    turtle.forward(100)
    turtle.right(150)
    turtle.down()
    turtle.forward(50)
    turtle.right(120)
    turtle.forward(50)
    turtle.right(120)
    turtle.forward(50)
    turtle.up()
    turtle.home()
def nexttriangle1():
# this function changes from the turtle drawing the first triangle (top) to the second triangle (leftmost).
    turtle.left(90)
def nexttriangle2():
# this function changes from the turtle drawing the second triangle (leftmost) to the third traingle (bottome).
    turtle.left(180)
def nexttriangle3():
# this function changes from the turtle drawing the third triangle (bottom) to the fourth traingle (rightmost).

    turtle.right(90)
def main():
# this is the function that actually draws the traingles.
    drawtriangle()
    nexttriangle1()
    drawtriangle()
    nexttriangle2()
    drawtriangle()
    nexttriangle3()
    drawtriangle()
    turtle.home()
def drawcircle1():
# this function draws the inner circle.
    turtle.right(90)
    turtle.forward(30)
    turtle.left(90)
    turtle.down()
    turtle.circle(30)
    turtle.up()
    turtle.home()
def drawcircle2():
# this function draws the outer circle.
    turtle.right(90)
    turtle.forward(120)
    turtle.left(90)
    turtle.down()
    turtle.circle(120)
main()
drawcircle1()
drawcircle2()
input("Hit enter to close...")
