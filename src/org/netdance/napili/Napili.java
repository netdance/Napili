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

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
import org.codehaus.groovy.runtime.InvokerHelper;
import org.netdance.napili.samples.Circles;

/**
 * Main class for the Application, responsible for setting up and running the JavaFX content.
 * Also provides methods to modify that displayed content.
 */
public class Napili extends Application {

    // Width of drawing area
    public static final double DRAWX = 500;
    // Height of drawing area
    public static final double DRAWY = 500;

    // Home location x value
    public static final double HOMEX = 250;
    // Home location y value
    public static final double HOMEY = DRAWX;
    // initial turtle direction, in degrees
    public static final double HOMEHEADING = -90;

    public static final double TURTLESPEED = 2;
    public static final double TURTLETURNSPEED = 2;

    // Height of the turtle
    public static final double TURTLEHEIGHT = 30;
    // Width of the base of the turtle
    public static final double TURTLEWIDTH = 20;

    public static final long TIMEOUT = 15L;

    protected final static TextArea output = new TextArea();
    protected Stage primaryStage;

    // The script runner class name used to execute the turtle graphic scripts
    private final String runClass = "org.netdance.napili.language.BasicRunner";

    private final Group holdingGroup = new Group();
    private final static Tab outputTab = new Tab("Output");
    private static String initialProgram = (String) Circles.PROGRAM;
    private final static TextArea code = new TextArea();


    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        BorderPane root = new BorderPane();
        BorderPane top = new BorderPane();
        Group main = new Group();
        top.setCenter(main);
        root.setTop(top);

        root.setCenter(setupTabs());
        root.getChildren().add(new NapiliMenuBar(this));
        Scene scene = new Scene(root, DRAWX + 10, DRAWY + 250);
        primaryStage.setTitle("Napili");
        primaryStage.setScene(scene);

        // create a border for the drawing area
        Rectangle rec = new Rectangle(0, 0, DRAWX, DRAWY);
        rec.setFill(Color.WHITE);
        rec.setStroke(Color.CORNFLOWERBLUE);
        rec.setStrokeType(StrokeType.OUTSIDE);
        rec.setStrokeWidth(3);
        main.getChildren().add(rec);

        // setup drawGroup  - the Group that actually holds the drawings
        // it's held by a holding group, so that we can easily erase the drawing area
        // by dropping it and creating a new one
        NapiliAnimation.clear(holdingGroup);
        main.getChildren().add(holdingGroup);

        primaryStage.show();
    }

    /**
     * Set the value of the user code area to a script
     *
     * @param script String to set the code area to
     */
    public static void setCode(String script) {
        code.setText(script);
    }

    /**
     * Get the current value of the user code area - the script the user typed in
     *
     * @return The script contained in the user code area
     */
    public static String getCode() {
        return code.getText();
    }

    /**
     * Get the primary Stage of the application - needed to open new stages
     *
     * @return The primary Stage of the application
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private TabPane setupTabs() {
        // setup output widgets
        output.setEditable(false);
        output.setWrapText(true);
        StackPane outputPane = new StackPane();
        outputPane.getChildren().add(output);
        outputTab.setContent(outputPane);
        outputTab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event e) {
                // undo bold setting, if present, when tab selected
                outputTabNormal();
            }
        });

        Tab codeTab = new Tab("Code");
        VBox codeBox = new VBox();
        codeBox.setAlignment(Pos.BOTTOM_RIGHT);
        code.setWrapText(false);
        code.setText(initialProgram);
        // set the preferred row count to something ridiculously high
        code.setPrefRowCount(100);
        codeBox.getChildren().addAll(code, new NapiliButtonBar(this));
        codeTab.setContent(codeBox);

        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPane.getTabs().addAll(codeTab, outputTab);
        return tabPane;
    }

    Button getSubmitButton() {
        Button submitButton = new Button("Run!");
        submitButton.setDefaultButton(true);
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                // reset output, wipe screen and animation queue
                resetOutput();
                // run user supplied script, adding animations into the sequence queue
                try {
                    Class clazz = Class.forName(runClass);
                    InvokerHelper.getMetaClass(clazz).invokeStaticMethod(clazz, "run", new Object[]{});
                } catch (Exception ex) {
                    NapiliOutput.println(ex.getMessage());
                    return;
                }
                // play animations in the sequence
                NapiliAnimation.play();
                NapiliOutput.flushOutput();
                String outText = output.getText();
                if (outText != null && outText.length() > 0) {
                    outputTabAlert();
                }
            }
        });
        return submitButton;
    }

    private void outputTabAlert() {
        outputTab.setStyle("-fx-font-weight: bold; -fx-background-color: FIREBRICK;");
    }

    private void outputTabNormal() {
        outputTab.setStyle("-fx-font-weight: normal;");
    }

    private void resetOutput() {
        // clear screen & animations
        NapiliAnimation.clear(holdingGroup);
        // clear output
        NapiliOutput.resetOutput();
        output.setText("");
        outputTabNormal();
    }
}