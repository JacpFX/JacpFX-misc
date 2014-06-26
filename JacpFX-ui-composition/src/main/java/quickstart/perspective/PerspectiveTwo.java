/************************************************************************
 *
 * Copyright (C) 2010 - 2012
 *
 * [PerspectiveOne.java]
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
package quickstart.perspective;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.lifecycle.OnShow;
import org.jacpfx.api.annotations.lifecycle.PostConstruct;
import org.jacpfx.api.annotations.lifecycle.PreDestroy;
import org.jacpfx.api.annotations.perspective.Perspective;
import org.jacpfx.api.message.Message;
import org.jacpfx.api.util.ToolbarPosition;
import org.jacpfx.rcp.componentLayout.FXComponentLayout;
import org.jacpfx.rcp.componentLayout.PerspectiveLayout;
import org.jacpfx.rcp.components.toolBar.JACPOptionButton;
import org.jacpfx.rcp.components.toolBar.JACPToolBar;
import org.jacpfx.rcp.context.Context;
import org.jacpfx.rcp.perspective.FXPerspective;
import org.jacpfx.rcp.util.FXUtil;
import org.jacpfx.rcp.util.LayoutUtil;
import quickstart.configuration.BaseConfiguration;
import quickstart.ui.PerspectiveOptionButton;
import quickstart.ui.Perspectives;

import java.util.ResourceBundle;

import static javafx.scene.layout.Priority.ALWAYS;

/**
 * A simple perspective defining a split pane
 *
 * @author: Andy Moncsek
 * @author: Patrick Symmangk (pete.jacp@gmail.com)
 */
@Perspective(id = BaseConfiguration.PERSPECTIVE_TWO, name = "PerspectiveTwo",
        components = {},
        viewLocation = "/fxml/PerspectiveTwo.fxml",
        resourceBundleLocation = "bundles.languageBundle")
public class PerspectiveTwo implements FXPerspective {

    @FXML
    private HBox contentTop;
    @FXML
    private HBox contentBottom;
    @FXML
    private VBox mainPane;
    @FXML
    private Button errorButton;

    @Resource
    public Context context;

    @Override
    public void handlePerspective(final Message<Event, Object> message,
                                  final PerspectiveLayout perspectiveLayout) {

        if (message.messageBodyEquals(FXUtil.MessageUtil.INIT)) {
        }

    }


    @OnShow
    public void onShow(final FXComponentLayout layout) {

    }


    /**
     * @param layout
     * @param resourceBundle
     * @OnStart annotated method will be executed when component is activated.
     */
    @PostConstruct
    public void onStartPerspective(final PerspectiveLayout perspectiveLayout, final FXComponentLayout layout,
                                   final ResourceBundle resourceBundle) {
        LayoutUtil.GridPaneUtil.setFullGrow(ALWAYS, mainPane);
        // register left menu
        perspectiveLayout.registerTargetLayoutComponent(BaseConfiguration.TARGET_CONTAINER_TOP, contentTop);
        // register main content
        perspectiveLayout.registerTargetLayoutComponent(BaseConfiguration.TARGET_CONTAINER_MAIN, contentBottom);

        createToolbar(layout);

    }

    private void createToolbar(final FXComponentLayout layout) {
        // define toolbars and menu entries
        JACPToolBar toolbar = layout.getRegisteredToolBar(ToolbarPosition.NORTH);
        JACPOptionButton options = new PerspectiveOptionButton(layout, context, "Perspective 2", Perspectives.PERSPECTIVE_2);

        toolbar.addAllOnEnd(options);
    }

    @PreDestroy
    /**
     * @OnTearDown annotated method will be executed when component is deactivated.
     * @param arg0
     */
    public void onTearDownPerspective(final FXComponentLayout arg0) {
        // remove toolbars and menu entries when close perspective

    }

}
