/*
 * Copyright (c) 2012. by James G Driscoll.  All rights reserved.
 */

package org.netdance.napili;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import org.netdance.napili.samples.SampleList;

public class NapiliButtonBar extends HBox {

    NapiliButtonBar(final Napili napili) {
        super();

        Button saveButton = new Button("Save");
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                NapiliFile.save(napili);

            }
        });

        Button loadButton = new Button("Load");
        loadButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                NapiliFile.load(napili);
            }
        });

        final ChoiceBox<Object> sampleChoice = new ChoiceBox<>();
        sampleChoice.getItems().addAll("Samples", new Separator());
        sampleChoice.getItems().addAll(SampleList.getSampleListNames());
        sampleChoice.getSelectionModel().selectFirst();
        sampleChoice.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> ov, Number oldNum, Number newNum) {
                if (newNum.intValue() < 2) {
                    return;
                }
                int selectedIndex = newNum.intValue() - 2;
                napili.setCode(SampleList.getSampleListPrograms().get(selectedIndex));
            }

        });

        getChildren().add(saveButton);
        getChildren().add(loadButton);
        getChildren().add(sampleChoice);
        getChildren().add(napili.getSubmitButton());
    }
}
