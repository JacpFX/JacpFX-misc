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
package quickstart.perspectives;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.GridPane;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.lifecycle.OnShow;
import org.jacpfx.api.annotations.lifecycle.PostConstruct;
import org.jacpfx.api.annotations.lifecycle.PreDestroy;
import org.jacpfx.api.annotations.perspective.Perspective;
import org.jacpfx.api.message.Message;
import org.jacpfx.api.util.ToolbarPosition;
import org.jacpfx.controls.optionPane.JACPDialogButton;
import org.jacpfx.controls.optionPane.JACPDialogUtil;
import org.jacpfx.controls.optionPane.JACPOptionPane;
import org.jacpfx.rcp.componentLayout.FXComponentLayout;
import org.jacpfx.rcp.componentLayout.PerspectiveLayout;
import org.jacpfx.rcp.components.toolBar.JACPOptionButton;
import org.jacpfx.rcp.components.toolBar.JACPToolBar;
import org.jacpfx.rcp.context.Context;
import org.jacpfx.rcp.perspective.FXPerspective;
import org.jacpfx.rcp.util.CSSUtil;
import org.jacpfx.rcp.util.FXUtil;
import quickstart.ui.PerspectiveOptionButton;
import quickstart.ui.Perspectives;
import quickstart.util.ComponentIds;
import quickstart.util.MessageConstants;
import quickstart.util.PerspectiveIds;

import java.util.ResourceBundle;

import static javafx.scene.layout.Priority.ALWAYS;
import static org.jacpfx.rcp.util.LayoutUtil.GridPaneUtil;
import static quickstart.util.ComponentIds.COMPONENT_LEFT;
import static quickstart.util.ComponentIds.COMPONENT_RIGHT;

/**
 * A simple perspective defining a split pane
 *
 * @author: Andy Moncsek
 * @author: Patrick Symmangk (pete.jacp@gmail.com)
 */
@Perspective(id = PerspectiveIds.PERSPECTIVE_THREE, name = "contactPerspective",
        components = {COMPONENT_LEFT, COMPONENT_RIGHT},
        //viewLocation = "/fxml/perspectiveOne.fxml",
        resourceBundleLocation = "bundles.languageBundle")
public class PerspectiveThree implements FXPerspective {
    @Resource
    public Context context;

    @Override
    public void handlePerspective(final Message<Event, Object> action,
                                  final PerspectiveLayout perspectiveLayout) {
        if (action.messageBodyEquals(FXUtil.MessageUtil.INIT)) {
            SplitPane mainLayout = new SplitPane();
            mainLayout.setOrientation(Orientation.HORIZONTAL);
            mainLayout.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            mainLayout.setDividerPosition(0, 0.55f);

            // create left button menu
            GridPane leftMenu = new GridPane();
            // create main content Top
            GridPane mainContent = new GridPane();

            // let them grow
            GridPaneUtil.setFullGrow(ALWAYS, leftMenu);
            GridPaneUtil.setFullGrow(ALWAYS, mainContent);
            GridPaneUtil.setFullGrow(ALWAYS, mainLayout);

            mainLayout.getItems().addAll(leftMenu, mainContent);
            // Register root component
            perspectiveLayout.registerRootComponent(mainLayout);
            // register left menu
            perspectiveLayout.registerTargetLayoutComponent(PerspectiveIds.TARGET_CONTAINER_LEFT , leftMenu);
            // register main content
            perspectiveLayout.registerTargetLayoutComponent(PerspectiveIds.TARGET_CONTAINER_MAIN, mainContent);
        }

    }


    @OnShow
    public void onShow(final FXComponentLayout layout) {

    }

    @PostConstruct
    /**
     * @OnStart annotated method will be executed when component is activated.
     * @param layout
     * @param resourceBundle
     */
    public void onStartPerspective(final FXComponentLayout layout,
                                   final ResourceBundle resourceBundle) {
        // TODO get message from resource
        // define toolbars and menu entries
        JACPToolBar toolbar = layout.getRegisteredToolBar(ToolbarPosition.NORTH);
        Button pressMe = new Button("press me");
        JACPOptionButton options = new PerspectiveOptionButton(layout, context, "Perspectives", Perspectives.PERSPECTIVE_3);
        pressMe.setOnAction((event) -> {
            // create a modal dialog
            JACPOptionPane dialog = JACPDialogUtil.createOptionPane("modal dialog", "Add some action");
            dialog.setDefaultButton(JACPDialogButton.OK);
            dialog.setDefaultCloseButtonOrientation(Pos.CENTER_RIGHT);
            dialog.setOnOkAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent arg0) {
                    context.hideModalDialog();
                }
            });
            context.showModalDialog(dialog);

        }
        );
        toolbar.addAllOnEnd(pressMe, options);
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
