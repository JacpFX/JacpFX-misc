package org.jacpfx.gui.fragment;

import javafx.fxml.FXML;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.fragment.Fragment;
import org.jacpfx.api.fragment.Scope;
import org.jacpfx.dto.FragmentNavigation;
import org.jacpfx.gui.configuration.BaseConfig;
import org.jacpfx.rcp.context.Context;

/**
 * Created by Andy Moncsek on 06.02.14.
 * The main selection dialog.
 */
@Fragment(id = BaseConfig.SERVERCONFIG_FRAGMENT,
        viewLocation = "/fxml/ServerCofigFragment.fxml",
        resourceBundleLocation = "bundles.languageBundle",
        localeID = "en_US",
        scope = Scope.SINGLETON)
public class ServerConfigFragment {
    @Resource
    private Context context;

    @FXML
    public void createConnection() {

        context.send(FragmentNavigation.CREATE);
    }

    @FXML
    public void connect() {
        context.send(FragmentNavigation.CONNECT);
    }
}
