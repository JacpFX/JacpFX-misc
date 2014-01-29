/************************************************************************
 * 
 * Copyright (C) 2010 - 2012
 *
 * [StatelessCallback.java]
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
package quickstart.callback;

import java.util.logging.Logger;

import javafx.event.Event;

import org.jacpfx.api.annotations.component.Component;
import org.jacpfx.api.annotations.component.Stateless;
import org.jacpfx.api.message.Message;
import org.jacpfx.rcp.component.CallbackComponent;

/**
 * A stateless JacpFX component
 * @author Andy Moncsek
 *
 */
//@CallbackComponent(id = "id004", name = "statelessCallback", active = false)
@Component(id = "id004", name = "statelessCallback", active = true, resourceBundleLocation = "bundles.languageBundle", localeID = "en_US")
@Stateless
public class StatelessCallback implements CallbackComponent {
	private Logger log = Logger.getLogger(StatelessCallback.class.getName());
	@Override
    public Object handle(final Message<Event, Object> message) {
		log.info(message.getMessageBody().toString());
		return "StatelessCallback - hello";
	}

}
