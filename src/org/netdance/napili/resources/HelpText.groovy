/*
 * Copyright 2012 by the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
