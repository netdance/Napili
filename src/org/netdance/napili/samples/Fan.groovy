/*
 * Copyright (c) 2012. by James G Driscoll.  All rights reserved.
 */

package org.netdance.napili.samples

class Fan {
    public static final String NAME = 'Fan'
    public static final PROGRAM =
"""
// Fan

pencolor Color.DARKBLUE
penup
forward 25
pendown
left 90

speed 50

def width = 200
for (i in 0..180)
  forward width back width right 1

hide
"""

}
