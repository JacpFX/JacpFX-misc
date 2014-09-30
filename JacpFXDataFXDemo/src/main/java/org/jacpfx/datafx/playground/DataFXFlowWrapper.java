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

package org.jacpfx.datafx.playground;

import javafx.event.Event;
import javafx.event.EventHandler;
import org.datafx.controller.ViewConfiguration;
import org.datafx.controller.flow.Flow;
import org.datafx.controller.flow.FlowException;
import org.datafx.controller.flow.FlowHandler;
import org.datafx.controller.flow.context.ViewFlowContext;
import org.jacpfx.api.component.SubComponent;
import org.jacpfx.api.util.CustomSecurityManager;
import org.jacpfx.rcp.registry.ComponentRegistry;

/**
 * Created by Andy Moncsek on 29.09.14. The DataFX Flow warpper encapsulates the DataFX Flow to allow injection of a JacpFX context into a  DataFX wizard controller.
 */
public class DataFXFlowWrapper extends Flow {

    private final static CustomSecurityManager customSecurityManager =
            new CustomSecurityManager();
    private final String parentId;
    private final SubComponent<EventHandler<Event>, Event, Object> component;

    public DataFXFlowWrapper(Class<?> startViewControllerClass, ViewConfiguration viewConfiguration, String parentId) {
        super(startViewControllerClass, viewConfiguration);
        this.parentId = parentId;
        this.component = ComponentRegistry.findComponentByQualifiedId(this.parentId);
        if (this.component == null)
            throw new IllegalStateException(" an invalid component id was provided");
    }

    public DataFXFlowWrapper(Class<?> startViewControllerClass, String parentId) {
        this(startViewControllerClass, new ViewConfiguration(), parentId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FlowHandler createHandler(ViewFlowContext flowContext) {
        final FlowHandler handler = super.createHandler(flowContext);
        handler.getFlowContext().register("jacpfxContext", this.component.getContext());
        return handler;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FlowHandler createHandler() throws FlowException {
        return createHandler(new ViewFlowContext());
    }
}
