/************************************************************************
 *
 * Copyright (C) 2010 - 2012
 *
 * [ComponentLeft.java]
 * AHCP Project (http://jacp.googlecode.com)
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at 
 *
 *     http://www.apache.org/licenses/LICENSE-2.0 
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either 
 * express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 *
 *
 ************************************************************************/
package org.jacpfx.datafx.component;

import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.datafx.controller.flow.Flow;
import org.datafx.controller.flow.FlowException;
import org.datafx.controller.flow.FlowHandler;
import org.datafx.controller.flow.container.DefaultFlowContainer;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.component.View;
import org.jacpfx.api.annotations.lifecycle.PostConstruct;
import org.jacpfx.api.annotations.lifecycle.PreDestroy;
import org.jacpfx.api.message.Message;
import org.jacpfx.datafx.config.BasicConfig;
import org.jacpfx.datafx.controller.*;
import org.jacpfx.datafx.fragment.DialogFragment;
import org.jacpfx.rcp.component.FXComponent;
import org.jacpfx.rcp.componentLayout.FXComponentLayout;
import org.jacpfx.rcp.components.managedFragment.ManagedFragmentHandler;
import org.jacpfx.rcp.context.Context;
import org.jacpfx.rcp.util.FXUtil;

import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * A simple JacpFX UI component
 *
 * @author Andy Moncsek
 */
@View(id = BasicConfig.COMPONENT_LEFT,
        name = "SimpleView",
        active = true,
        resourceBundleLocation = "bundles.languageBundle",
        initialTargetLayoutId = BasicConfig.TARGET_CONTAINER_LEFT)
public class ComponentLeft implements FXComponent {
    private Node pane;
    private Logger log = Logger.getLogger(ComponentLeft.class.getName());
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
        if (message.messageBodyEquals(FXUtil.MessageUtil.INIT)) {

        }
        return this.pane;
    }

    @PostConstruct
    /**
     * The @PostConstruct annotation labels methods executed when the component switch from inactive to active state
     * @param arg0
     * @param resourceBundle
     */
    public void onPostConstructComponent(final FXComponentLayout arg0,
                                         final ResourceBundle resourceBundle) {
        this.pane = createUI();
        this.log.info("run on start of ComponentLeft ");
    }

    @PreDestroy
    /**
     * The @PreDestroy annotations labels methods executed when the component is set to inactive
     * @param arg0
     */
    public void onPreDestroyComponent(final FXComponentLayout arg0) {
        this.log.info("run on tear down of ComponentLeft ");

    }

    /**
     * create the UI on first call
     *
     * @return
     */
    private Node createUI() {
        final VBox main = new VBox();
        main.setSpacing(10);
        main.setPadding(new Insets(0, 20, 10, 20));

        final ManagedFragmentHandler<DialogFragment> handler = context.getManagedFragmentHandler(DialogFragment.class);
        final DialogFragment controller = handler.getController();
         controller.init();
       // main.getChildren().addAll(handler.getFragmentNode());

        Flow flow=  new Flow(WizardStartController.class).
                withLink(WizardStartController.class, "next", Wizard1Controller.class).
                withLink(Wizard1Controller.class, "next", Wizard2Controller.class).
                withLink(Wizard2Controller.class, "next", Wizard3Controller.class).
                withLink(Wizard3Controller.class, "next", WizardDoneController.class).
                withGlobalBackAction("back").
                withGlobalLink("finish", WizardDoneController.class).
                withGlobalTaskAction("help", () -> System.out.println("There is no help for the application :("));
        FlowHandler flowHandler = null;
        try {
            flowHandler = flow.createHandler();
            StackPane pane = flowHandler.start(new DefaultFlowContainer()) ;
            pane.setMinWidth(100);
            main.setMinWidth(200);
            System.out.println(pane);
            main.getChildren().addAll(pane, new Label(this.context.getParentId()));
        } catch (FlowException e) {
            e.printStackTrace();
        }

        return main;
    }

    /**
     *  Flow flow=  new Flow(WizardStartController.class).
     withLink(WizardStartController.class, "next", Wizard1Controller.class).
     withLink(Wizard1Controller.class, "next", Wizard2Controller.class).
     withLink(Wizard2Controller.class, "next", Wizard3Controller.class).
     withLink(Wizard3Controller.class, "next", WizardDoneController.class).
     withGlobalBackAction("back").
     withGlobalLink("finish", WizardDoneController.class).
     withGlobalTaskAction("help", () -> System.out.println("There is no help for the application :("));
     FlowHandler flowHandler = null;
     try {
     flowHandler = flow.createHandler();
     return flowHandler.start(new DefaultFlowContainer());
     } catch (FlowException e) {
     e.printStackTrace();
     }

     return null;
     */

}
