/*
 * Copyright (c) 2012. by James G Driscoll.  All rights reserved.
 */

package org.netdance.napili.samples

class Spirograph  {

    public static final String NAME = 'Spirograph'
    public static final PROGRAM =
"""
// Spirograph

// the increment for every turn
// set lower for a denser drawing
// and higher for one more sparse
def increment = 10

// the size of the shape
def size = 100



speed 3
penup
forward 250
pendown

def square(size) {
    for (i in 1..4) {
        forward size
        right 90
    }
}

def iterations = 360/increment

for (i in 0..iterations) {
    square(size)
    right increment
}

hide
"""
}
