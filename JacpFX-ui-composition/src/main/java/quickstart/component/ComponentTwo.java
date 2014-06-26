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
package quickstart.component;

import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.component.View;
import org.jacpfx.api.annotations.lifecycle.PostConstruct;
import org.jacpfx.api.annotations.lifecycle.PreDestroy;
import org.jacpfx.api.message.Message;
import org.jacpfx.api.util.ToolbarPosition;
import org.jacpfx.rcp.component.FXComponent;
import org.jacpfx.rcp.componentLayout.FXComponentLayout;
import org.jacpfx.rcp.components.managedFragment.ManagedFragmentHandler;
import org.jacpfx.rcp.components.toolBar.JACPToolBar;
import org.jacpfx.rcp.context.Context;
import quickstart.configuration.BaseConfiguration;
import quickstart.fragments.FragmentOne;

import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * A simple JacpFX UI component
 *
 * @author Andy Moncsek
 */
@View(id = BaseConfiguration.COMPONENT_TWO,
        name = "SimpleView",
        active = true,
        resourceBundleLocation = "bundles.languageBundle",
        initialTargetLayoutId = BaseConfiguration.TARGET_CONTAINER_MAIN)
public class ComponentTwo implements FXComponent {
    private VBox pane;
    private Logger log = Logger.getLogger(ComponentTwo.class.getName());
    @Resource
    private Context context;

    @Override
    /**
     * The handle method always runs outside the main application thread. You can create new nodes, execute long running tasks but you are not allowed to manipulate existing nodes here.
     */
    public Node handle(final Message<Event, Object> message) {
        // runs in worker thread
        return null;
    }

    @Override
    /**
     * The postHandle method runs always in the main application thread.
     */
    public Node postHandle(final Node arg0,
                           final Message<Event, Object> message) {
        // runs in FX application thread
        return this.pane;
    }

    @PostConstruct
    /**
     * The @OnStart annotation labels methods executed when the component switch from inactive to active state
     * @param arg0
     * @param resourceBundle
     */
    public void onStartComponent(final FXComponentLayout layout,
                                 final ResourceBundle resourceBundle) {
        pane = (VBox) createUI();
        HBox lastRow = new HBox();
        ManagedFragmentHandler<FragmentOne> fragment = context.getManagedFragmentHandler(FragmentOne.class);
        lastRow.getChildren().addAll(fragment.getFragmentNode());
        pane.getChildren().add(lastRow);
        JACPToolBar toolbar = layout.getRegisteredToolBar(ToolbarPosition.NORTH);
    }

    @PreDestroy
    /**
     * The @OnTearDown annotations labels methods executed when the component is set to inactive
     * @param arg0
     */
    public void onTearDownComponent(final FXComponentLayout arg0) {
        this.log.info("run on tear down of ComponentLeft ");

    }

    /**
     * create the UI on first call
     *
     * @return
     */
    private Node createUI() {
        final VBox pane = new VBox();
        HBox.setHgrow(pane, Priority.ALWAYS);
        final HBox top = new HBox();
        top.setPrefHeight(100);
        top.setPrefWidth(200);
        Label firstName = new Label("First name:");
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
        Label lastName = new Label("Last name:");
        lastName.setFont(new Font(29.0));
        HBox.setMargin(lastName, new Insets(15, 5, 0, 5));
        TextField lastNameText = new TextField();
        lastNameText.setPrefHeight(50);
        lastNameText.setPadding(new Insets(10, 0, 0, 0));
        HBox.setHgrow(lastNameText, Priority.ALWAYS);
        HBox.setMargin(lastNameText, new Insets(10, 5, 0, 0));
        bottom.getChildren().addAll(lastName, lastNameText);


        pane.getChildren().addAll(top, bottom);
        return pane;
    }


}
