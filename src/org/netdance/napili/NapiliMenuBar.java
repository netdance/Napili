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

package org.netdance.napili;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.netdance.napili.resources.AboutText;
import org.netdance.napili.resources.HelpText;

public class NapiliMenuBar extends MenuBar {

    NapiliMenuBar(final Napili napili) {
        super();

        setUseSystemMenuBar(true);

        Menu fileMenu = new Menu("File");
        getMenus().add(fileMenu);
        MenuItem saveItem = new MenuItem("Save");
        MenuItem loadItem = new MenuItem("Load");
        fileMenu.getItems().addAll(saveItem, loadItem);

        saveItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent evt) {
                NapiliFile.save(napili);
            }
        });

        loadItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent evt) {
                NapiliFile.load(napili);
            }
        });

        Menu aboutMenu = new Menu("About");
        getMenus().add(aboutMenu);
        MenuItem aboutItem = new MenuItem("About");
        MenuItem helpItem = new MenuItem("Help");
        aboutMenu.getItems().addAll(aboutItem, helpItem);

        aboutItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent evt) {

                final double DISPLAYWIDTH = Napili.DRAWX;
                final double DISPLAYHEIGHT = Napili.DRAWY;

                final Stage aboutStage = new Stage();
                aboutStage.setTitle("Hey there");
                VBox aboutPane = new VBox();
                aboutPane.setAlignment(Pos.CENTER);
                Text aboutText = new Text((String) AboutText.TEXT);
                aboutPane.getChildren().add(aboutText);
                Button exitButton = new Button("Close");
                aboutPane.getChildren().add(exitButton);
                exitButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        aboutStage.close();
                    }
                });
                Scene aboutScene = new Scene(aboutPane, DISPLAYWIDTH, DISPLAYHEIGHT);
                aboutStage.setScene(aboutScene);
                aboutStage.show();
            }
        });

        helpItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {

                final double DISPLAYWIDTH = 500;
                final double DISPLAYHEIGHT = 700;

                final Stage helpStage = new Stage();
                helpStage.setTitle("Here's some help");
                VBox helpPane = new VBox();
                helpPane.setAlignment(Pos.CENTER);
                Text helpText = new Text((String) HelpText.TEXT);
                helpPane.getChildren().add(helpText);
                Button exitButton = new Button("Exit");
                helpPane.getChildren().add(exitButton);
                exitButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        helpStage.close();
                    }
                });
                Scene helpScene = new Scene(helpPane, DISPLAYWIDTH, DISPLAYHEIGHT);
                helpStage.setScene(helpScene);
                helpStage.show();
            }
        });
    }
}
