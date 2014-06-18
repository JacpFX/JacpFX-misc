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
package showcase.perspectives;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
import org.jacpfx.rcp.util.FXUtil;
import org.jacpfx.rcp.util.LayoutUtil;
import showcase.ui.PerspectiveOptionButton;
import showcase.ui.Perspectives;
import showcase.util.ComponentIds;
import showcase.util.PerspectiveIds;

import java.util.ResourceBundle;

import static javafx.scene.layout.Priority.ALWAYS;

/**
 * A simple perspective defining a split pane
 *
 * @author: Andy Moncsek
 * @author: Patrick Symmangk (pete.jacp@gmail.com)
 */
@Perspective(id = PerspectiveIds.PERSPECTIVE_TWO, name = "contactPerspective",
        components = {ComponentIds.COMPONENT_LEFT},
        viewLocation = "/fxml/perspectiveOne.fxml",
        resourceBundleLocation = "bundles.languageBundle")
public class PerspectiveTwo implements FXPerspective {

    @FXML
    private HBox contentTop;
    @FXML
    private BorderPane contentBottom;
    @FXML
    private Button errorButton;

    @Resource
    public Context context;

    @Override
    public void handlePerspective(final Message<Event, Object> action,
                                  final PerspectiveLayout perspectiveLayout) {


        if (action.messageBodyEquals(FXUtil.MessageUtil.INIT)) {
            // let them grow
            LayoutUtil.GridPaneUtil.setFullGrow(ALWAYS, perspectiveLayout.getRootComponent());
            // register left menu
            perspectiveLayout.registerTargetLayoutComponent(PerspectiveIds.TARGET_CONTAINER_LEFT, contentTop);
            // register main content
            perspectiveLayout.registerTargetLayoutComponent(PerspectiveIds.TARGET_CONTAINER_MAIN, contentBottom);
            errorButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    throw new NullPointerException();
                }
            });
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

        JACPOptionButton options = new PerspectiveOptionButton(layout, context, "Switch Perspectives", Perspectives.PERSPECTIVE_2);

        Button pressMe = new Button("press me");
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
