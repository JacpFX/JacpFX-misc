package org.jacpfx.gui.fragment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.jacpfx.dto.ConnectionProperties;
import org.jacpfx.dto.FragmentNavigation;
import org.jacpfx.gui.configuration.BaseConfig;
import org.jacpfx.rcp.context.Context;

/**
 * Created by Andy Moncsek on 27.12.13.
 */
@Fragment(id = BaseConfig.CREATE_FRAGMENT,
        viewLocation = "/fxml/CreateFragment.fxml",
        resourceBundleLocation = "bundles.languageBundle",
        localeID = "en_US",
        scope = Scope.PROTOTYPE)
public class VertxCreateFragment {
    @Resource
    private Context context;

    @FXML
    private ChoiceBox ports;

    @FXML
    private TextField connectAddress;

    private ObservableList<String> values = FXCollections.observableArrayList(
            "8080", "9090", "8888");

    public void init() {
        ports.setItems(values);
        ports.getSelectionModel().selectFirst();
    }

    @FXML
    public void createServer() {
        final String port = ports.getSelectionModel().getSelectedItem().toString();
        send(new ConnectionProperties(null, null, port, ConnectionProperties.PROVIDER.VERTX));
        context.hideModalDialog();
    }

    private void send(ConnectionProperties connectionProperties) {
        context.send(BaseConfig.VERTX_COMPONENT, connectionProperties);
    }

    @FXML
    public void back() {
        context.send(FragmentNavigation.BACK_VERTX);
    }
}
