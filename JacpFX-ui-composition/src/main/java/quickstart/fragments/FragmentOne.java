/*
 * **********************************************************************
 *
 *  Copyright (C) 2010 - 2014
 *
 *  [FragmentOne.java]
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

package quickstart.fragments;

import javafx.scene.control.Button;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.jacpfx.rcp.context.Context;
import quickstart.configuration.BaseConfiguration;

import java.io.File;
import java.util.ResourceBundle;
import java.util.stream.Stream;

/**
 * Created by Andy Moncsek on 24.04.14.
 */
@Fragment(id = BaseConfiguration.FRAGMENT_ONE,
        viewLocation = "/fxml/FragmentOne.fxml",
        resourceBundleLocation = "bundles.languageBundle",
        localeID = "en_US",
        scope = Scope.PROTOTYPE)
public class FragmentOne {
    @Resource
    private Context context;
    @Resource
    private ResourceBundle bundle;

    public void init() {
        Button button = new Button();
        button.setOnAction((event)->context.send("hello world"));
        File file= null;
        Stream.of(file.list()).forEach(f-> System.out.println(f));
    }
}
