/*
 * Copyright (c) 2012. by James G Driscoll.  All rights reserved.
 */

package org.netdance.napili.samples

class SampleList {

    static def sampleList = [
            Fan.class,
            Flower.class,
            Spirograph.class,
            Circles.class
    ]
    
    
    static def ArrayList<String> sampleListNames = []
    static def ArrayList<String> sampleListPrograms = []
    
    static {
        for (sample in sampleList) {
                    sampleListNames += sample.NAME;
                    sampleListPrograms += sample.PROGRAM;
        }
        
    }

}
