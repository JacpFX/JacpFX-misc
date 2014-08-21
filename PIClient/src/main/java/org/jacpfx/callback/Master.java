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

package org.jacpfx.callback;

import javafx.event.Event;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.component.Component;
import org.jacpfx.api.annotations.lifecycle.PostConstruct;
import org.jacpfx.api.annotations.lifecycle.PreDestroy;
import org.jacpfx.api.message.Message;
import org.jacpfx.config.BasicConfig;
import org.jacpfx.entity.Result;
import org.jacpfx.entity.Work;
import org.jacpfx.rcp.component.CallbackComponent;
import org.jacpfx.rcp.context.Context;
import org.jacpfx.rcp.util.FXUtil;

import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * Created by amo on 25.07.14.
 */
@Component(id = BasicConfig.STATEFUL_CALLBACK,
        name = "statefulCallback",
        active = true,
        localeID = "en_US",
        resourceBundleLocation = "bundles.languageBundle")
public class Master implements CallbackComponent {
    private Logger log = Logger.getLogger(Master.class.getName());
    @Resource
    private Context context;

    private int nrOfMessages =1000000;
    private int nrOfElements =10000;
    private int nrOfResults =0;
    private double pi;
    private final String target = BasicConfig.PERSPECTIVE_ONE.concat(".").concat(BasicConfig.STATELESS_CALLBACK);

    @Override
    public Object handle(final Message<Event, Object> message) {
        if(message.messageBodyEquals(FXUtil.MessageUtil.INIT))     {
            for (int start = 0; start < nrOfMessages; start++) {
               context.send(target, new Work(start, nrOfElements));
            }
        } else if(message.isMessageBodyTypeOf(Result.class)) {
            Result result = message.getTypedMessageBody(Result.class);
            pi += result.getValue();
            nrOfResults += 1;
            if(nrOfResults==nrOfMessages) {
                System.out.println("value:"+pi);
            }

        }
        return null;
    }

    @PreDestroy
    /**
     * The @PreDestroy annotations labels methods executed when the component is set to inactive
     */
    public void onPreDestroyComponent() {
        this.log.info("run on tear down of StatefulCallback ");

    }

    @PostConstruct
    /**
     * The @PostConstruct annotation labels methods executed when the component switch from inactive to active state
     * @param resourceBundle
     */
    public void onPostConstructComponent(final ResourceBundle resourceBundle) {
        this.log.info("run on start of StatefulCallback ");

    }


}
