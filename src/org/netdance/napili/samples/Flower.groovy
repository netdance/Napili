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
def density = 6
for (i in 0..(360/density)) {
    newColor()
    forward size back size right density
}

home
hide
"""

}
