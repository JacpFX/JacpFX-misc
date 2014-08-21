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

package com.trivadis.com.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Amdy Moncsek on 28.01.14.
 */
@Configuration
@ComponentScan
public class BasicConfig {


    public static final String PERSPECTIVE_TAB = "idPtab";

    public static final String COMPONENT_DEMO1 = "id001";
    public static final String COMPONENT_DEMO2 = "id002";
    public static final String COMPONENT_DEMO3 = "id003";




    public static final String DIALOG_FRAGMENT = "idD1";

    public static final String TARGET_CONTAINER_MAIN = "PMain";

    public static final String TARGET_CONTAINER_TAB1 = "tab1";
    public static final String TARGET_CONTAINER_TAB2 = "tab2";
    public static final String TARGET_CONTAINER_TAB3 = "tab3";
}
