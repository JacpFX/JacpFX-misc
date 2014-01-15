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
import javafx.scene.chart.XYChart;
import org.jacp.demo.constants.GlobalConstants;
import org.jacp.demo.entity.Contact;
import org.jacp.demo.entity.ContactDTO;
import org.jacp.demo.main.Util;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.component.Component;
import org.jacpfx.api.message.Message;
import org.jacpfx.rcp.component.CallbackComponent;
import org.jacpfx.rcp.context.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The AnalyticsCallback components creates chart data (random data)
 * 
 * @author Andy Moncsek
 * 
 */
@Component(id = GlobalConstants.CallbackConstants.CALLBACK_ANALYTICS, name = "analyticsCallback", active = false)
public class AnalyticsCallback implements CallbackComponent {
    private final Random rnd = new Random();
    @Resource
    private Context context;

    @Override
    public Object handle(final Message<Event, Object> message) throws Exception {
        if (message.isMessageBodyTypeOf(Contact.class)) {
            final Contact contact = message.getTypedMessageBody(Contact.class);
            this.creatAndSendData(contact);
        }
        return null;
    }

    /**
     * for demo purpose send Contact with "electronic" statistic data and than
     * all other
     * 
     * @param contact
     */
    private void creatAndSendData(final Contact contact) {
        final ContactDTO dto = new ContactDTO();
        contact.setDto(dto);

        dto.setSeriesOneData(this.createChartData());
        dto.setSeriesTwoData(this.createChartData());
        dto.setSeriesThreeData(this.createChartData());
        dto.setSeriesFourData(this.createChartData());
        this.sendChartData(contact);

    }

    private void sendChartData(final Object data) {
        context.send(
                GlobalConstants.cascade(GlobalConstants.PerspectiveConstants.DEMO_PERSPECTIVE, GlobalConstants.ComponentConstants.COMPONENT_CHART_VIEW), data);
    }

    private List<XYChart.Data<String, Number>> createChartData() {
        final List<XYChart.Data<String, Number>> data = new ArrayList<XYChart.Data<String, Number>>();
        for (final String element : Util.YEARS) {
            data.add(new XYChart.Data<String, Number>(element, this.random()));
        }

        return data;
    }

    /**
     * send other data, wait after each category
     */
    private void createAndSendDTOData() {
        ContactDTO dto = new ContactDTO();

        dto.setSeriesTwoData(this.createChartData());
        this.sendChartData(dto);
        this.waitAmount(300);

        dto = new ContactDTO();
        dto.setSeriesThreeData(this.createChartData());
        this.sendChartData(dto);
        this.waitAmount(300);

        dto = new ContactDTO();
        dto.setSeriesFourData(this.createChartData());
        this.sendChartData(dto);
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

    // Common but flawed!
    private int random() {
        return Math.abs(this.rnd.nextInt()) % 9999;
    }

}
