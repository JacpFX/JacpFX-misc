package org.jacpfx.gui.fragment;

import javafx.fxml.FXML;
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
@Fragment(id = BaseConfig.CONNECT_FRAGMENT,
        viewLocation = "/fxml/ConnectFragment.fxml",
        resourceBundleLocation = "bundles.languageBundle",
        localeID = "en_US",
        scope = Scope.SINGLETON)
public class VertxConnectFragment {
    @Resource
    private Context context;

    @FXML
    private TextField connectAddress;


    @FXML
    public void connectToServer() {
        final String connectValue = connectAddress.getText();
        if (connectValue == null || connectValue.isEmpty()) return;
        final String[] val = connectValue.split(":");
        if (val.length < 2) return;
        send(new ConnectionProperties("WS://",val[0], val[1]));
        context.hideModalDialog();
    }

    private void send(ConnectionProperties connectionProperties) {
        context.send(BaseConfig.WEBSOCKET_COMPONENT, connectionProperties);
        context.send(BaseConfig.CANVAS_COMPONENT, FragmentNavigation.CONNECT_VERTX);
        context.send(BaseConfig.COLOR_PICKER_COMPONENT, FragmentNavigation.CONNECT_VERTX);
    }

    @FXML
    public void back() {
        context.send(FragmentNavigation.BACK_VERTX);
    }
}