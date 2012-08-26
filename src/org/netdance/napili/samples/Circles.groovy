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

class Circles {

    public static final String NAME = "Circles"

    public static final PROGRAM =
"""
// Circles
// create a series of interlocking circles

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
