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

package com.trivadis.com.perspective;

import com.trivadis.com.config.BasicConfig;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.lifecycle.OnHide;
import org.jacpfx.api.annotations.lifecycle.OnShow;
import org.jacpfx.api.annotations.lifecycle.PostConstruct;
import org.jacpfx.api.annotations.lifecycle.PreDestroy;
import org.jacpfx.api.annotations.perspective.Perspective;
import org.jacpfx.api.message.Message;
import org.jacpfx.rcp.componentLayout.FXComponentLayout;
import org.jacpfx.rcp.componentLayout.PerspectiveLayout;
import org.jacpfx.rcp.context.Context;
import org.jacpfx.rcp.perspective.FXPerspective;
import org.jacpfx.rcp.util.LayoutUtil;

import java.util.ResourceBundle;
import java.util.logging.Logger;

import static javafx.scene.layout.Priority.ALWAYS;

/**
 * Created by amo on 21.08.14.
 */
@Perspective(id = BasicConfig.PERSPECTIVE_TAB,
        name = "contactPerspective",
        components = {BasicConfig.COMPONENT_DEMO1,BasicConfig.COMPONENT_DEMO2,BasicConfig.COMPONENT_DEMO3},
        viewLocation = "/fxml/perspectiveTab.fxml",
        resourceBundleLocation = "bundles.languageBundle")
public class TabPerspective implements FXPerspective {
    private Logger log = Logger.getLogger(TabPerspective.class.getName());



    @Resource
    public Context context;

    @FXML
    private VBox tab1;
    @FXML
    private VBox tab2;
    @FXML
    private VBox tab3;

    @Override
    /**
     * handle messages to perspective, be aware... perspective always runs on FXApplication thread
     *
     */
    public void handlePerspective(final Message<Event, Object> message,
                                  final PerspectiveLayout perspectiveLayout) {

    }


    @OnShow
    /**
     * This method will be executed when the perspective gets the focus and switches to foreground
     * @param layout, the component layout contains references to the toolbar and the menu
     */
    public void onShow(final FXComponentLayout layout) {
        log.info("on show of TabPerspective");
    }

    @OnHide
    /**
     * will be executed when an active perspective looses the focus and moved to the background.
     * @param layout, the component layout contains references to the toolbar and the menu
     */
    public void onHide(final FXComponentLayout layout) {
        log.info("on hide of TabPerspective");
    }

    @PostConstruct
    /**
     * @PostConstruct annotated method will be executed when component is activated.
     * @param perspectiveLayout , the perspective layout let you register target layouts
     * @param layout, the component layout contains references to the toolbar and the menu
     * @param resourceBundle
     */
    public void onStartPerspective(final PerspectiveLayout perspectiveLayout, final FXComponentLayout layout,
                                   final ResourceBundle resourceBundle) {
        // let them grow
        LayoutUtil.GridPaneUtil.setFullGrow(ALWAYS, perspectiveLayout.getRootComponent());
        // register main content
        perspectiveLayout.registerTargetLayoutComponent(BasicConfig.TARGET_CONTAINER_TAB1, tab1);
        perspectiveLayout.registerTargetLayoutComponent(BasicConfig.TARGET_CONTAINER_TAB2, tab2);
        perspectiveLayout.registerTargetLayoutComponent(BasicConfig.TARGET_CONTAINER_TAB3, tab3);
        log.info("on PostConstruct of TabPerspective");
    }

    @PreDestroy
    /**
     * @PreDestroy annotated method will be executed when component is deactivated.
     * @param layout, the component layout contains references to the toolbar and the menu
     */
    public void onTearDownPerspective(final FXComponentLayout layout) {
        log.info("on PreDestroy of TabPerspective");

    }

}
