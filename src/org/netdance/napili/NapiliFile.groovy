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

import javafx.stage.FileChooser

import java.nio.file.Files
import java.nio.file.Path

/**
 * Abstraction class to hold the save and load logic of Napili
 */
class NapiliFile {

    /**
     * Load the contents of user's code from a file, as specified in the FileChooser
     * @param napili Main Napili program code
     */
    static void load(Napili napili) {
        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(napili.primaryStage);
        if (file == null) return;

        System.out.println(file.getAbsolutePath());
        if (file.canRead()) {
            try {
                Path path = file.toPath();
                byte[] bytes = Files.readAllBytes(path);
                napili.code = new String(bytes);
            } catch (IOException ex) {
                napili.println(ex.getMessage());
            }
        } else {
            napili.println("Cannot read from the file " + file.getAbsolutePath());
        }
    }

    /**
     * Save the contents of the user's code to a file, as specified in the FileChooser
     * @param napili
     */
    static void save(Napili napili) {
        FileChooser chooser = new FileChooser();
        File file = chooser.showSaveDialog(napili.primaryStage);
        if (file == null) return;

        try {

            if (!file.createNewFile()) {
                String message = "Warning, file exists at " + file.getAbsolutePath() + " overwriting...";
                println(message);
            }
            if (file.canWrite()) {
                Path path = file.toPath();
                byte[] text = napili.code as byte[];
                Files.write(path, text);

            } else {
                String message = "Cannot write to the file " + file.getAbsolutePath();
                napili.println(message);
            }
        } catch (IOException ex) {
            napili.println(ex.getMessage());
        }
    }
}
