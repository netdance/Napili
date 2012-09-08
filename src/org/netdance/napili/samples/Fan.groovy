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

package org.netdance.napili.samples

class Fan {
    public static final String NAME = 'Fan'
    public static final PROGRAM =
"""
// Fan Sample Program
// Draw a blue fan of lines
// in a semicircle

pencolor Color.DARKBLUE
penup
forward 25
pendown
left 90

// Make the turtle go really fast
speed 50

def width = 200
180.times {
  forward width back width right 1
}
// Hide the turtle at the end of drawing
hide
"""

}
