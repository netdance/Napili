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
    4.times {
        forward size
        right 90
    }
}

def iterations = 360/increment

for (i in 1..iterations) {
    square(size)
    right increment
}

hide
"""
}
