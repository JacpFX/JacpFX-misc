package org.jacp.demo.components;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jacp.demo.common.GenderType;
import org.jacp.demo.constants.GlobalConstants;
import org.jacp.demo.entity.Contact;
import org.jacpfx.api.annotations.component.DeclarativeView;
import org.jacpfx.api.annotations.lifecycle.PostConstruct;
import org.jacpfx.api.annotations.lifecycle.PreDestroy;
import org.jacpfx.api.message.Message;
import org.jacpfx.rcp.component.FXComponent;
import org.jacpfx.rcp.componentLayout.FXComponentLayout;

import java.net.URL;
import java.util.ResourceBundle;

@DeclarativeView(id = GlobalConstants.ComponentConstants.COMPONENT_DETAIL_VIEW, name = "XMlDetailView", active = true, resourceBundleLocation = "bundles.languageBundle", initialTargetLayoutId = "PdetailComponent", viewLocation = "/fxml/UserDetail.fxml")
public class XMLDetailView implements FXComponent {
    private final static Log LOGGER = LogFactory
            .getLog(XMLDetailView.class);
    @FXML
    private Label lblFirstname;
    @FXML
    private Label lblLastname;
    @FXML
    private Label lblStreet;
    @FXML
    private Label lblZip;
    @FXML
    private Label lblCountry;

    @FXML
    private Pane imagePanel;


    @FXML
    private void handleSubmit(ActionEvent event) {
    }

    @Override
    public Node handle(Message<Event, Object> message) {
        LOGGER.info("XMLDetailView handleAction message: " + message.getMessageBody() + "  " + lblCountry);
        return null;
    }

    private void fillView(Contact contact) {
        imagePanel.getStyleClass().clear();
        String styleClass = GenderType.FEMALE.getLabel().equals(
                contact.getGender()) ? "female" : "male";
        imagePanel.getStyleClass().add(styleClass);
        lblFirstname.setText(contact.getFirstName());
        lblLastname.setText(contact.getLastName());
        lblZip.setText(contact.getZip());
        lblStreet.setText(contact.getAddress());
        lblCountry.setText(contact.getCountry());
    }

    @Override
    public Node postHandle(final Node node,
                           final Message<Event, Object> message) {
        if (message.isMessageBodyTypeOf(Contact.class)) {
            // contact selected
            final Contact contact = (Contact) message.getMessageBody();
            if (contact != null) {
                fillView(contact);
            }
        }

        return null;

    }

    @PostConstruct
    public void start(FXComponentLayout layout, URL url,
                      ResourceBundle resourceBundle) {
        System.out.println("STAR XML: " + layout + "  " + url + "  " + resourceBundle);
    }

    @PreDestroy
    public void stop(FXComponentLayout layout) {
        System.out.println("STOP XML: " + layout);
    }

}
