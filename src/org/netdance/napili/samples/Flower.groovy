/*
 * Copyright (c) 2012. by James G Driscoll.  All rights reserved.
 */

package org.netdance.napili.samples

class Flower {

        public static NAME = 'Flower'
        public static PROGRAM =
"""
// Flower

def size = 100


def random(amount) {
    def rand = new Random()
    return rand.nextInt(amount)
}

def newColor() {
    pencolor Color.rgb(random(255),random(255),random(255))
}

def leaf(length) {
    def leafAngle = 25
    left leafAngle
    forward length
    right leafAngle * 2
    forward length
    right 180 - ( leafAngle * 2)
    forward length
    right leafAngle * 2
    forward length
    right 180 - leafAngle
}

speed 2

// draw leaves
pencolor Color.GREEN
right 45
leaf(size /2)
left 90
leaf size / 2
right 45

// draw stem
forward size * 3

// draw flower
def density = 4
for (i in 0..(360/density)) {
    newColor()
    forward size back size right density
}

home
hide
"""

}
