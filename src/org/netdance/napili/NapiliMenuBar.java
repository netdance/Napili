/*
 * Copyright (c) 2012. by James G Driscoll.  All rights reserved.
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
import javafx.scene.web.HTMLEditor;
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
                final Stage aboutStage = new Stage();
                aboutStage.setTitle("Hey there");
                VBox aboutPane = new VBox();
                aboutPane.setAlignment(Pos.CENTER);
                Text aboutText = new Text((String)AboutText.TEXT);
                aboutPane.getChildren().add(aboutText);
                Button exitButton = new Button("Exit");
                aboutPane.getChildren().add(exitButton);
                exitButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        aboutStage.close();
                    }
                });
                Scene aboutScene = new Scene(aboutPane, Napili.DRAWX, Napili.DRAWY);
                aboutStage.setScene(aboutScene);
                aboutStage.show();
            }
        });

        helpItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                final Stage helpStage = new Stage();
                helpStage.setTitle("Here's some help");
                VBox helpPane = new VBox();
                helpPane.setAlignment(Pos.CENTER);
                Text aboutText = new Text((String) HelpText.TEXT);
                helpPane.getChildren().add(aboutText);
                Button exitButton = new Button("Exit");
                helpPane.getChildren().add(exitButton);
                exitButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        helpStage.close();
                    }
                });
                Scene helpScene = new Scene(helpPane, Napili.DRAWX, Napili.DRAWY);
                helpStage.setScene(helpScene);
                helpStage.show();
            }
        });
    }
}
