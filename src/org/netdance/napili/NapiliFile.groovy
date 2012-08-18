/*
 * Copyright (c) 2012. by James G Driscoll.  All rights reserved.
 */

package org.netdance.napili

import javafx.stage.FileChooser

import java.nio.file.Files
import java.nio.file.Path

class NapiliFile {

    static load(Napili napili) {
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

    static save(Napili napili) {
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
