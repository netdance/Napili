/*
 * Copyright (c) 2012. by James G Driscoll.  All rights reserved.
 */

package org.netdance.napili.language

abstract class TurtleDelegateBaseScript extends Script {

    def methodMissing(String name, args) {
        turtle."$name"(*args)
    }
}

