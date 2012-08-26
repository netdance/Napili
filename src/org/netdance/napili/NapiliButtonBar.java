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

        Button clearButton = new Button("Clear");
        clearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                napili.setCode("");
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
        getChildren().add(clearButton);
        getChildren().add(sampleChoice);
        getChildren().add(napili.getSubmitButton());
    }
}
