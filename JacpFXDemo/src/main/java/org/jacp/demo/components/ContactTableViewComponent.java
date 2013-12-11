/*
 * Copyright (C) 2010 - 2012.
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
package org.jacp.demo.components;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jacp.demo.constants.GlobalConstants;
import org.jacp.demo.entity.Contact;
import org.jacp.demo.entity.ContactDTO;
import org.jacp.demo.main.Util;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.component.View;
import org.jacpfx.api.message.Message;
import org.jacpfx.controls.optionPane.JACPDialogButton;
import org.jacpfx.controls.optionPane.JACPDialogUtil;
import org.jacpfx.controls.optionPane.JACPOptionPane;
import org.jacpfx.rcp.component.FXComponent;
import org.jacpfx.rcp.components.modalDialog.JACPModalDialog;
import org.jacpfx.rcp.context.JACPContext;
import org.jacpfx.rcp.util.FXUtil.MessageUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * The ContactTableViewComponent create the table view for an category
 *
 * @author Andy Moncsek
 */
@View(id = GlobalConstants.ComponentConstants.COMPONENT_TABLE_VIEW, name = "contactDemoTableView", active = true, initialTargetLayoutId = "PmainContentTop")
public class ContactTableViewComponent implements FXComponent {
    private final static Log LOGGER = LogFactory
            .getLog(ContactTableViewComponent.class);
    private final Map<String, ContactTableView> all = Collections.synchronizedMap(new HashMap<String, ContactTableView>());
    private ContactTableView current;
    @Resource
    private JACPContext context;

    @Override
    /**
     * run handleAction in worker Thread
     */
    public Node handle(final Message<Event, Object> action) throws Exception {
        return null;
    }

    @Override
    /**
     * run postHandle in FX application Thread, use this method to update UI code
     */
    public Node postHandle(final Node node, final Message<Event, Object> message) throws Exception {
        if (message.getMessageBody() instanceof Contact) {
            // contact selected
            final Contact contact = (Contact) message.getMessageBody();
            if (contact.isEmpty()) {
                this.showDialogIfEmpty(contact);
            }
            this.current = this.getView(contact);

        } else if (message.getMessageBody() instanceof ContactDTO) {
            // contact data received
            final ContactDTO dto = (ContactDTO) message.getMessageBody();
            final ContactTableView view = this.all.get(dto.getParentName());
            // add first 1000 entries directly to table
            if (view.getContactTableView().getItems().size() < Util.PARTITION_SIZE) {
                view.getContactTableView().getItems().addAll(dto.getContacts());
            } else {
                // all other entries are added to list for paging
                this.updateContactList(view, dto.getContacts());
            }
            view.updatePositionLabel();

        } else if (message.getMessageBody().equals(MessageUtil.INIT)) {
            return this.getView(null).getTableViewLayout();
        }
        return this.current.getTableViewLayout();
    }

    private Callback<TableView<Contact>, TableRow<Contact>> createRowCallback() {
        return new Callback<TableView<Contact>, TableRow<Contact>>() {

            @Override
            public TableRow<Contact> call(final TableView<Contact> arg0) {
                final TableRow<Contact> row = new TableRow<Contact>() {
                    @Override
                    public void updateItem(final Contact contact, final boolean emty) {
                        super.updateItem(contact, emty);
                        if (contact != null) {
                            this.setOnMouseClicked(new EventHandler<Event>() {
                                @Override
                                public void handle(final Event arg0) {
                                    // send contact to TableView
                                    // component to show containing
                                    // contacts
                                    context.send(
                                            GlobalConstants.cascade(GlobalConstants.PerspectiveConstants.DEMO_PERSPECTIVE, GlobalConstants.CallbackConstants.CALLBACK_ANALYTICS), contact);
                                    context.send(
                                            GlobalConstants.cascade(GlobalConstants.PerspectiveConstants.DEMO_PERSPECTIVE, GlobalConstants.ComponentConstants.COMPONENT_DETAIL_VIEW), contact);

                                }
                            });
                        }
                    }
                };
                return row;
            }

        };
    }

    private void updateContactList(final ContactTableView view, final ObservableList<Contact> list) {
        // add chunk of contact list to contact
        view.getContact().getContacts().addAll(list);
        view.updateMaxValue();
    }

    private ContactTableView getView(final Contact contact) {
        ContactTableView view = null;
        if (contact == null) {
            view = this.createView(null);
        } else if (!this.all.containsKey(contact.getFirstName())) {
            view = this.createView(contact);
            this.all.put(contact.getFirstName(), view);
        } else if (contact != null) {
            view = this.all.get(contact.getFirstName());
        }
        return view;
    }

    private ContactTableView createView(final Contact contact) {
        final ContactTableView view = new ContactTableView();
        view.createInitialTableViewLayout(contact);
        view.getContactTableView().setRowFactory(this.createRowCallback());
        return view;
    }

    private void showDialogIfEmpty(final Contact contact) {
        // show popup to ask how many contacts to create
        final JACPOptionPane dialog = JACPDialogUtil.createOptionPane("Contact Demo Pane", "Currently are no contact in this category available. Do you want to create " + Util.MAX + " contacts?");
        dialog.setDefaultButton(JACPDialogButton.NO);
        dialog.setDefaultCloseButtonVisible(true);

        dialog.setOnYesAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent arg0) {
                contact.setAmount(Util.MAX);
                contact.setEmpty(false);
                // redirect contact to coordinator callback to create
                // contacts
                context.send("id01.id004", contact);
            }
        });

        dialog.setOnNoAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent arg0) {

            }
        });
        JACPModalDialog.getInstance().showModalDialog(dialog);

    }

}
