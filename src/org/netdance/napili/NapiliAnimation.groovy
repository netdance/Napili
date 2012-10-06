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
package org.netdance.napili

import javafx.animation.Animation
import javafx.scene.Group
import javafx.animation.SequentialTransition

class NapiliAnimation {
    private static Group drawGroup = new Group()
    private static SequentialTransition play = new SequentialTransition()


    /**
     * Add a node to the drawing area.  Used primarily to add Line Objects.
     *
     * @param node - Node to add to drawing area
     */
    public static void addNode(javafx.scene.Node node) {
        drawGroup.getChildren().add(node)
    }

    /**
     * Add an Animation to the Sequential animation list for later playback
     *
     * @param animation Animation to add
     */
    public static void addAnimation(Animation animation) {
        play.getChildren().add(animation)
    }

    protected static void play() {
        play.play()
    }

    protected static clear(Group holdingGroup) {
        holdingGroup.getChildren().remove(drawGroup);
        holdingGroup.getChildren().add(drawGroup = new Group());
        play = new SequentialTransition()
    }

}
