
/*
 * Copyright (C) 2010 - 2013
 *
 * [FX2ComponentLayout.java]
 * AHCP Project (http://jacp.googlecode.com)
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package org.jacp.demo.components.util;

import javafx.scene.control.Button;
import org.jacp.javafx.rcp.componentLayout.FXComponentLayout;
import org.jacp.javafx.rcp.components.toolBar.JACPOptionButton;
import org.jacp.javafx.rcp.components.toolBar.JACPOptionButtonOrientation;

/**
 * @author Patrick Symmangk
 *
 */
public class JACPOptionButtonCreator {

    public static final int DEFAULT_OPTION_SIZE = 6;

    public static JACPOptionButton createDefaultOptionButton(final String label, final FXComponentLayout layout, final JACPOptionButtonOrientation orientation, final int optionSize){

        JACPOptionButton optionButton = new JACPOptionButton(label, layout, orientation);
        for(int i = 0; i < optionSize; i++){
            optionButton.addOption(new Button("Test Optionnnnnnnnnnnnn " + i));
        }

        return optionButton;

    }

    public static JACPOptionButton createDefaultOptionButton(final String label, final FXComponentLayout layout, final JACPOptionButtonOrientation orientation){
        return createDefaultOptionButton(label, layout, orientation, DEFAULT_OPTION_SIZE);
    }

}
