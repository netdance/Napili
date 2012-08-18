/*
 * Copyright (c) 2012. by James G Driscoll.  All rights reserved.
 */

package org.netdance.napili.samples

class Circles {

    public static final String NAME = "Circles"

    public static final PROGRAM =
"""

def circle(size) {
    for (i in 1..45) {
        forward size
        right 8
    }
}

speed 3
penup
forward 250
pendown
pencolor Color.PURPLE

for (i in 1..12) {
    circle 10
    right 30
}

home
hide

"""

}
