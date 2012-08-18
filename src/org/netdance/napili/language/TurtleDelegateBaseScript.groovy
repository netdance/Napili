/*
 * Copyright (c) 2012. by James G Driscoll.  All rights reserved.
 */

package org.netdance.napili.language

/**
 * Basescript which simply looks for any unfound method calls on the Turtle object in binding
 */
abstract class TurtleDelegateBaseScript extends Script {

    // if we can't find the method, look for it on the turtle binding object
    def methodMissing(String name, args) {
        turtle."$name"(* args)
    }
}

