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
package org.jacp.components;

import java.util.ResourceBundle;
import java.util.logging.Logger;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import org.jacp.api.action.IAction;
import org.jacp.api.annotations.Component;
import org.jacp.api.annotations.OnStart;
import org.jacp.api.annotations.OnTearDown;
import org.jacp.javafx.rcp.component.AFXComponent;
import org.jacp.javafx.rcp.componentLayout.FXComponentLayout;
import org.jacp.javafx.rcp.util.FXUtil.MessageUtil;
import org.jacpfx.api.annotations.component.View;
import org.jacpfx.api.annotations.lifecycle.PostConstruct;
import org.jacpfx.api.annotations.lifecycle.PreDestroy;
import org.jacpfx.api.message.Message;
import org.jacpfx.rcp.component.FXComponent;
import org.jacpfx.rcp.componentLayout.FXComponentLayout;
import org.jacpfx.rcp.util.FXUtil;

/**
 * A simple JacpFX UI component
 * @author Andy Moncsek
 *
 */
@View(id = "id007", name = "SimpleView", active = true, resourceBundleLocation = "bundles.languageBundle", localeID = "en_US", initialTargetLayoutId = "content1")
public class ComponentRight implements FXComponent {
	private ScrollPane pane;
	private Label rightLabel;
	private Logger log = Logger.getLogger(ComponentRight.class.getName());

	@Override
	/**
	 * The handle method always runs outside the main application thread. You can create new nodes,
     * execute long running tasks but you are not allowed to manipulate existing nodes here.
	 */
    public Node handle(final Message<Event, Object> message) {
		// runs in worker thread
		if (message.messageBodyEquals(FXUtil.MessageUtil.INIT)) {
			return createUI();
		}
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
		}else {
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
		final VBox box = new VBox();
		final Button right = new Button("right");
		rightLabel = new Label("");
		right.setOnMouseClicked( getMessage());
		VBox.setMargin(right, new Insets(4, 2, 4, 5));
		box.getChildren().addAll(right,rightLabel);
		pane.setContent(box);
		return pane;
	}

	private EventHandler<Event> getMessage() {
		return new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				getActionListener("id01.id004", "hello stateless component").performAction(arg0);
			}
		};
	}

}

