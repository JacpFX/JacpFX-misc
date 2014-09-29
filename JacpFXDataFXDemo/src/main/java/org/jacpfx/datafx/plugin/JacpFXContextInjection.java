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

package org.jacpfx.datafx.plugin;

import org.datafx.controller.context.ViewContext;
import org.datafx.controller.context.resource.AnnotatedControllerResourceType;
import org.datafx.controller.flow.context.ViewFlowContext;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.rcp.context.Context;

/**
 * Created by amo on 29.09.14.
 */
public class JacpFXContextInjection implements AnnotatedControllerResourceType<Resource,Context> {
    @Override
    public Context getResource(Resource jacpFXContext, Class<Context> contextClass, ViewContext<?> viewContext) {
        // TODO context holen..........
        return (Context) viewContext.getRegisteredObject(ViewFlowContext.class).getRegisteredObject("context");
    }

    @Override
    public Class<Resource> getSupportedAnnotation() {
        return Resource.class;
    }
}
