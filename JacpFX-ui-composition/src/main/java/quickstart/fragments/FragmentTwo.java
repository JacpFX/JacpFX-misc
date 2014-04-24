/*
 * **********************************************************************
 *
 *  Copyright (C) 2010 - 2014
 *
 *  [Component.java]
 *  JACPFX Project (https://github.com/JacpFX/JacpFX/)
 *  All rights reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an "AS IS"
 *  BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 *  express or implied. See the License for the specific language
 *  governing permissions and limitations under the License.
 *
 *
 * *********************************************************************
 */

package quickstart.fragments;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import quickstart.configuration.BaseConfiguration;

/**
 * Created by amo on 24.04.14.
 */
@Fragment(id = BaseConfiguration.FRAGMENT_TWO,
        resourceBundleLocation = "bundles.languageBundle",
        localeID = "en_US",
        scope = Scope.PROTOTYPE)
public class FragmentTwo extends VBox {

    public FragmentTwo() {
        setStyle("-fx-background-color:#f5f5f5");
        HBox.setHgrow(this, Priority.ALWAYS);
        final HBox top = new HBox();
        top.setPrefHeight(100);
        top.setPrefWidth(200);
        Label firstName = new Label("Phone:");
        firstName.setFont(new Font(29.0));
        HBox.setMargin(firstName, new Insets(15, 5, 0, 5));
        TextField firstNameText = new TextField();
        firstNameText.setPrefHeight(50);
        firstNameText.setPadding(new Insets(10, 0, 0, 0));
        HBox.setHgrow(firstNameText, Priority.ALWAYS);
        HBox.setMargin(firstNameText, new Insets(10, 5, 0, 0));
        top.getChildren().addAll(firstName, firstNameText);


        final HBox bottom = new HBox();
        bottom.setPrefHeight(100);
        bottom.setPrefWidth(200);
        Label lastName = new Label("Email:");
        lastName.setFont(new Font(29.0));
        HBox.setMargin(lastName, new Insets(15, 5, 0, 5));
        TextField lastNameText = new TextField();
        lastNameText.setPrefHeight(50);
        lastNameText.setPadding(new Insets(10, 0, 0, 0));
        HBox.setHgrow(lastNameText, Priority.ALWAYS);
        HBox.setMargin(lastNameText, new Insets(10, 5, 0, 0));
        bottom.getChildren().addAll(lastName, lastNameText);


        this.getChildren().addAll(top, bottom);
    }
}
