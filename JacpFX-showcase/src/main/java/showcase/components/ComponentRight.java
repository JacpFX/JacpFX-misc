/************************************************************************
 *
 * Copyright (C) 2010 - 2012
 *
 * [ComponentRight.java]
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
package showcase.components;

import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.component.View;
import org.jacpfx.api.annotations.lifecycle.PostConstruct;
import org.jacpfx.api.annotations.lifecycle.PreDestroy;
import org.jacpfx.api.message.Message;
import org.jacpfx.rcp.component.FXComponent;
import org.jacpfx.rcp.componentLayout.FXComponentLayout;
import org.jacpfx.rcp.context.Context;
import org.jacpfx.rcp.util.CSSUtil;
import org.jacpfx.rcp.util.FXUtil;
import showcase.util.ComponentIds;
import showcase.util.PathUtil;
import showcase.util.PerspectiveIds;

import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * A simple JacpFX UI component
 *
 * @author Andy Moncsek
 */
@View(id = ComponentIds.COMPONENT_RIGHT, name = "SimpleView", active = true, resourceBundleLocation = "bundles.languageBundle", localeID = "en_US", initialTargetLayoutId = PerspectiveIds.TARGET_CONTAINER_MAIN)
public class ComponentRight implements FXComponent {
    private ScrollPane pane;
    private Label rightLabel;
    private Logger log = Logger.getLogger(ComponentRight.class.getName());
    @Resource
    private Context context;

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
        if (message.messageBodyEquals(FXUtil.MessageUtil.INIT)) {
            this.pane = (ScrollPane) arg0;
        } else {
            rightLabel.setText(message.getMessageBody().toString());
        }
        return this.pane;
    }


    @PostConstruct
    /**
     * The @OnStart annotation labels methods executed when the component switch from inactive to active state
     * @param arg0
     * @param resourceBundle
     */
    public void onStartComponent(final FXComponentLayout arg0,
                                 final ResourceBundle resourceBundle) {
        createUI();

    }

    @PreDestroy
    /**
     * The @OnTearDown annotations labels methods executed when the component is set to inactive
     * @param arg0
     */
    public void onTearDownComponent(final FXComponentLayout arg0) {
        this.log.info("run on tear down of ComponentRight ");

    }

    /**
     * create the UI on first call
     *
     * @return
     */
    private Node createUI() {
        ScrollPane pane = new ScrollPane();
        pane.setFitToHeight(true);
        pane.setFitToWidth(true);

        GridPane.setHgrow(pane, Priority.ALWAYS);
        GridPane.setVgrow(pane, Priority.ALWAYS);
        final BorderPane box = new BorderPane();
        final BorderPane bottomBox = new BorderPane();
        final Button right = new Button("right");

        CSSUtil.addCSSClass("showcase-component", bottomBox);
        CSSUtil.addCSSClass("showcase-component-button", right);
        rightLabel = new Label("");
        right.setOnMouseClicked((event) -> {
            context.send(PathUtil.createPath(PerspectiveIds.PERSPECTIVE_ONE, ComponentIds.STATELESS_CALLBACK), "hello stateless component");
        });
        box.setCenter(right);
        bottomBox.setCenter(rightLabel);
        box.setBottom(bottomBox);
        pane.setContent(box);
        return pane;
    }


}

