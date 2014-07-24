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
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.component.DeclarativeView;
import org.jacpfx.api.annotations.lifecycle.OnShow;
import org.jacpfx.api.annotations.lifecycle.PostConstruct;
import org.jacpfx.api.annotations.lifecycle.PreDestroy;
import org.jacpfx.api.message.Message;
import org.jacpfx.rcp.component.FXComponent;
import org.jacpfx.rcp.componentLayout.FXComponentLayout;
import org.jacpfx.rcp.components.managedFragment.ManagedFragmentHandler;
import org.jacpfx.rcp.context.Context;
import quickstart.configuration.BaseConfiguration;
import quickstart.fragments.FragmentTwo;

import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * A simple JacpFX UI component
 *
 * @author Andy Moncsek
 */
@DeclarativeView(id = BaseConfiguration.COMPONENT_ONE,
        name = "SimpleView",
        active = true,
        resourceBundleLocation = "bundles.languageBundle",
        initialTargetLayoutId = BaseConfiguration.TARGET_CONTAINER_TOP,
        viewLocation = "/fxml/ComponentOne.fxml")
public class ComponentOne implements FXComponent {
    private Logger log = Logger.getLogger(ComponentOne.class.getName());
    @Resource
    private Context context;

    @FXML
    private VBox mainPane;

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
        return null;
    }

    @PostConstruct
    /**
     * The @OnStart annotation labels methods executed when the component switch from inactive to active state
     * @param arg0
     * @param resourceBundle
     */
    public void onStartComponent(final FXComponentLayout layout,
                                 final ResourceBundle resourceBundle) {


        HBox lastRow = new HBox();
        ManagedFragmentHandler<FragmentTwo> fragment = context.getManagedFragmentHandler(FragmentTwo.class);
        lastRow.getChildren().addAll(fragment.getFragmentNode());
        mainPane.getChildren().add(lastRow);
    }

    @PreDestroy
    /**
     * The @OnTearDown annotations labels methods executed when the component is set to inactive
     * @param arg0
     */
    public void onTearDownComponent(final FXComponentLayout arg0) {
        this.log.info("run on tear down of ComponentOne ");

    }

    @OnShow
    /**
     * The @OnTearDown annotations labels methods executed when the component is set to inactive
     * @param arg0
     */
    public void onShowComponent(final FXComponentLayout arg0) {
        this.log.info("run on tear down of ComponentOne ");
        System.out.println("FXComponentLayout: "+ arg0+" in: "+context.getId());
    }


}
