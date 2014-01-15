/*
 * Copyright (C) 2010 - 2014.
 * AHCP Project (http://code.google.com/p/jacp)
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
 */
package org.jacp.demo.callbacks;

import javafx.event.Event;
import org.jacp.demo.constants.GlobalConstants;
import org.jacp.demo.entity.Contact;
import org.jacp.demo.entity.ContactDTO;
import org.jacp.demo.main.Util;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.component.Component;
import org.jacpfx.api.annotations.component.Stateless;
import org.jacpfx.api.message.Message;
import org.jacpfx.rcp.component.CallbackComponent;
import org.jacpfx.rcp.context.Context;

/**
 * The coordinatorCallback splits the amount of contacts in to chunks and sends
 * it to the coordinatorCallback
 * 
 * @author Andy Moncsek
 * 
 */
@Stateless
@Component(id = GlobalConstants.CallbackConstants.CALLBACK_COORDINATOR, name = "coordinatorCallback", active = false)

public class CoordinatorCallback implements CallbackComponent {
    @Resource
    private Context context;
    @Override
    public Object handle(final Message<Event, Object> message) {
        if (message.isMessageBodyTypeOf(Contact.class)) {
            final Contact contact = (Contact) message.getMessageBody();
            if (contact.getContacts().isEmpty()) {
                if (contact.getAmount() > Util.PARTITION_SIZE) {
                    int amount = contact.getAmount();
                    while (amount > Util.PARTITION_SIZE) {
                        this.waitAmount(100);
                        this.createAmountAndSend(contact.getFirstName(), Util.PARTITION_SIZE);
                        amount -= Util.PARTITION_SIZE;
                    }
                    this.createAmountAndSend(contact.getFirstName(), amount);
                } else {
                    this.createAmountAndSend(contact.getFirstName(), contact.getAmount());
                }
            }
        }
        return null;
    }

    /**
     * for demo purposes
     */
    private void waitAmount(final int amount) {
        try {
            Thread.sleep(amount);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * create a contact dto with parent name and amount of contacts to create,
     * send dto to creator callback
     * 
     * @param name
     * @param amount
     */
    private void createAmountAndSend(final String name, final int amount) {
        final ContactDTO dto = new ContactDTO(name, amount);
        context.send(
                GlobalConstants.cascade(GlobalConstants.PerspectiveConstants.DEMO_PERSPECTIVE, GlobalConstants.CallbackConstants.CALLBACK_CREATOR), dto);
    }

}
