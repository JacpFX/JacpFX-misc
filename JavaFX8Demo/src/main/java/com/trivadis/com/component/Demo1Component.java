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
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.component.DeclarativeView;
import org.jacpfx.api.annotations.lifecycle.PostConstruct;
import org.jacpfx.api.annotations.lifecycle.PreDestroy;
import org.jacpfx.api.message.Message;
import org.jacpfx.rcp.component.FXComponent;
import org.jacpfx.rcp.componentLayout.FXComponentLayout;
import org.jacpfx.rcp.context.Context;
import org.jacpfx.rcp.util.FXUtil;

import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * Created by amo on 21.08.14.
 */
@DeclarativeView(id = BasicConfig.COMPONENT_DEMO1,
        name = "Demo1",
        viewLocation = "/fxml/TabComponent.fxml",
        active = true,
        resourceBundleLocation = "bundles.languageBundle",
        localeID = "en_US",
        initialTargetLayoutId = BasicConfig.TARGET_CONTAINER_TAB1)
public class Demo1Component implements FXComponent {
    private Logger log = Logger.getLogger(Demo1Component.class.getName());
    @Resource
    private Context context;
    @FXML
    private HBox tab;

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
        String family = "Helvetica";

        TextFlow textFlow = new TextFlow();
        textFlow.setLayoutX(40);
        textFlow.setLayoutY(40);

        DropShadow ds = new DropShadow();
        ds.setOffsetY(6.0f);
        ds.setColor(Color.color(0.4f, 0.4f, 0.4f));

        Text text1 = new Text();
        text1.setX(5.0f);
        text1.setY(140.0f);
        text1.setCache(true);
        text1.setText("Welcome ");
        text1.setFill(Color.GREEN);
        text1.setFont(Font.font(null, FontWeight.BOLD, 36));
        text1.setEffect(ds);
        text1.setTranslateY(10);

        Text text2 = new Text();
        text2.setEffect(new GaussianBlur());
        text2.setCache(true);
        text2.setX(10.0f);
        text2.setY(270.0f);
        text2.setFill(Color.GREEN);
        text2.setText("to the ");
        text2.setFont(Font.font(null, FontWeight.BOLD, 22));
        text2.setTranslateY(50);

        Text text3 = new Text();
        text3.setX(10.0f);
        text3.setY(50.0f);
        text3.setCache(true);
        text3.setText("Trivadis");
        text3.setFill(Color.RED);
        text3.setFont(Font.font(null, FontWeight.BOLD, 50));
        Reflection r = new Reflection();
        r.setFraction(0.7f);
        text3.setEffect(r);
        text3.setTranslateY(80);

        Text text4 = new Text(" TechEvent!");
        text4.setFill(Color.RED);
        text4.setCache(true);
        text4.setFont(Font.font(family, FontWeight.BOLD, 60));
        text4.setEffect(new Glow());
        text4.setTranslateY(120);
        textFlow.getChildren().addAll(text1, text2, text3, text4);


        Group group = new Group(textFlow);
        tab.getChildren().add(group);
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


}

