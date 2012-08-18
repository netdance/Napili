/*
 * Copyright (c) 2012. by James G Driscoll.  All rights reserved.
 */

package org.netdance.napili.resources

class HelpText {

    public static final def TEXT =
        """
The following commands are supported for using the turtle to draw
lines on the screen:

home - send the turtle to it's home position, without drawing any
    lines
pencolor - change the color used by the turtle to draw lines.
    Takes javafx.scene.paint.Color as an argument.
penup - puts the turtle's pen in the up position - movement won't
    result in drawing a line.
pendown - puts the turtle's pen in the down position - movement
    results in drawing a line.
left - turns the turtle left, takes a numeric argument as a number
    of degrees (360 results in turning the turtle in a full circle
    counterclockwise)
right - turns the turtle right, takes a numeric argument as a
    number of degrees. (360 results in turning the turtle in a full
    circle clockwise)
forward - move the turtle forward, takes an argument that is the
    number of steps to move
back - move the turtle backward, takes an argument that is the number
    of steps to move
show - show the turtle
hide - hide the turtle (it's still there, and will still draw, you
    just can't see it)
speed - the speed that the turtle draws.  Takes one numeric argument,
    higher is faster

All of Groovy's command structures are available by default - if/else,
    while, for, etc.

All of Groovy and Java's classes are also available by default - Math.PI,
    Random, etc.

"""

}
