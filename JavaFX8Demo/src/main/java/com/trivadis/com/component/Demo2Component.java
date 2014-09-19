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

package com.trivadis.com.component;

import com.trivadis.com.config.BasicConfig;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.component.DeclarativeView;
import org.jacpfx.api.annotations.lifecycle.PostConstruct;
import org.jacpfx.api.annotations.lifecycle.PreDestroy;
import org.jacpfx.api.message.Message;
import org.jacpfx.rcp.component.FXComponent;
import org.jacpfx.rcp.componentLayout.FXComponentLayout;
import org.jacpfx.rcp.context.Context;
import org.jacpfx.rcp.util.FXUtil;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * Created by amo on 21.08.14.
 */
@DeclarativeView(id = BasicConfig.COMPONENT_DEMO2,
        name = "Demo2",
        viewLocation = "/fxml/TabComponent.fxml",
        active = true,
        resourceBundleLocation = "bundles.languageBundle",
        localeID = "en_US",
        initialTargetLayoutId = BasicConfig.TARGET_CONTAINER_TAB2)
public class Demo2Component implements FXComponent {
    private Logger log = Logger.getLogger(Demo2Component.class.getName());
    @Resource
    private Context context;
    @FXML
    private HBox tab;

    List<Employee> employees = Arrays.<Employee> asList(new Employee(
            "Max Ermantraut", "max.ermantraut@example.com"), new Employee(
            "Andy Moncsek", "andy.moncsek@example.com"), new Employee(
            "Michael Brown", "michael.brown@example.com"), new Employee(
            "Anna Black", "anna.black@example.com"), new Employee(
            "Rodger York", "roger.york@example.com"), new Employee(
            "Susan Collins", "susan.collins@example.com"));

    final TreeItem<Employee> root = new TreeItem<>(new Employee("Trivadis", ""));
    final TreeItem<Employee> childNode1 = new TreeItem<>(new Employee("AD 1",
            ""));
    final TreeItem<Employee> childNode2 = new TreeItem<>(new Employee("AD 2",
            ""));
    final TreeItem<Employee> childNode3 = new TreeItem<>(new Employee("AD 3",
            ""));

    @Override
    /**
     * The handle method always runs outside the main application thread. You can create new nodes,
     * execute long running tasks but you are not allowed to manipulate existing nodes here.
     */
    public Node handle(final Message<Event, Object> message) {
        return null;
    }

    @Override
    /**
     * The postHandle method runs always in the main application thread.
     */
    public Node postHandle(final Node arg0,
                           final Message<Event, Object> message) {
        // runs in FX application thread
        if (!message.messageBodyEquals(FXUtil.MessageUtil.INIT)) {

        }
        return null;
    }


    @PostConstruct
    /**
     * The @PostConstruct annotation labels methods executed when the component switch from inactive to active state
     * @param arg0
     * @param resourceBundle
     */
    public void onPostConstructComponent(final FXComponentLayout arg0,
                                         final ResourceBundle resourceBundle) {
        root.setExpanded(true);
        root.getChildren().add(childNode1);
        root.getChildren().add(childNode2);
        root.getChildren().add(childNode3);
        childNode1.setExpanded(true);

        employees.stream().forEach((employee) -> {
            childNode1.getChildren().add(new TreeItem<>(employee));
        });

        employees.stream().forEach((employee) -> {
            childNode2.getChildren().add(new TreeItem<>(employee));
        });

        employees.stream().forEach((employee) -> {
            childNode3.getChildren().add(new TreeItem<>(employee));
        });


        tab.setStyle("-fx-background-color: lightgray");


        TreeTableColumn<Employee, String> empColumn = new TreeTableColumn<>(
                "Employee");
        empColumn.setPrefWidth(250);
        empColumn
                .setCellValueFactory((
                        TreeTableColumn.CellDataFeatures<Employee, String> param) -> new ReadOnlyStringWrapper(
                        param.getValue().getValue().getName()));

        TreeTableColumn<Employee, String> emailColumn = new TreeTableColumn<>(
                "Email");
        emailColumn.setPrefWidth(500);
        emailColumn
                .setCellValueFactory((
                        TreeTableColumn.CellDataFeatures<Employee, String> param) -> new ReadOnlyStringWrapper(
                        param.getValue().getValue().getEmail()));

        TreeTableView<Employee> treeTableView = new TreeTableView<>(root);
        treeTableView.getColumns().setAll(empColumn, emailColumn);
        HBox.setHgrow(treeTableView, Priority.ALWAYS);
        tab.getChildren().add(treeTableView);
        this.log.info("run on start of Demo1Component ");

    }

    @PreDestroy
    /**
     * The @PreDestroy annotations labels methods executed when the component is set to inactive
     * @param arg0
     */
    public void onPreDestroyComponent(final FXComponentLayout arg0) {
        this.log.info("run on tear down of Demo1Component ");

    }


    public class Employee {

        private SimpleStringProperty name;
        private SimpleStringProperty email;

        public SimpleStringProperty nameProperty() {
            if (name == null) {
                name = new SimpleStringProperty(this, "name");
            }
            return name;
        }

        public SimpleStringProperty emailProperty() {
            if (email == null) {
                email = new SimpleStringProperty(this, "email");
            }
            return email;
        }

        private Employee(String name, String email) {
            this.name = new SimpleStringProperty(name);
            this.email = new SimpleStringProperty(email);
        }

        public String getName() {
            return name.get();
        }

        public void setName(String fName) {
            name.set(fName);
        }

        public String getEmail() {
            return email.get();
        }

        public void setEmail(String fName) {
            email.set(fName);
        }
    }


}

