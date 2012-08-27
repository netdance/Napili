# Napili - Turtle Graphics with Groovy

Napili is a simple Turtle Graphics Program with Groovy as the user facing programming language.   Users can control
the turtle using a small set of commands, along with all the libraries that Java and Groovy have to offer.

## Requirements

This project has only been tested with JDK7u6, which includes JavaFX 2.2.  It should work with other versions of the JDK,
with JavaFX 2.2 installed and JAVAFX_HOME set, but I haven't tested it.

## Build and Run

To build and run Napili, type:
> gradlew


## Turtle Commands / Methods

* home - send the turtle to it's home position, without drawing any lines
* pencolor -  change the color used by the turtle to draw lines.
  Takes `javafx.scene.paint.Color` as an argument.
* penup - puts the turtle's pen in the up position - movement won't result in drawing a line.
* pendown - puts the turtle's pen in the down position - movement
    results in drawing a line.
* left - turns the turtle left, takes a numeric argument as a number
    of degrees (360 results in turning the turtle in a full circle
    counterclockwise)
* right - turns the turtle right, takes a numeric argument as a
    number of degrees. (360 results in turning the turtle in a full
    circle clockwise)
* forward - move the turtle forward, takes an argument that is the
    number of steps to move
* back - move the turtle backward, takes an argument that is the number
    of steps to move
* show - show the turtle, if hidden. Otherwise, no change.
* hide - hide the turtle (it's still there, and will still draw, you
    just can't see it)
* speed - the speed that the turtle draws.  Takes one numeric argument,
    higher is faster

### Miscellaneous

Napili's code comes with IntelliJ IDEA and Netbeans setups, as well as the gradle setup that I use.  In IDEA, 
just use 'gradlew idea' to create the IDEA setup, then after opening, use the default configuration to build.  
In Netbeans, you may need to tweak the libraries...

I created Napili as a way to learn JavaFX (as well as a number of other technologies), and to better explain Groovy
DSLs on my blog (which can be found at http://jamesgdriscoll.wordpress.com )

Napili is named after the beautiful bay off the coast of Maui, where I once saw a turtle the size of a Volkswagen.
