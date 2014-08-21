package org.jacpfx.gui.workbench;

import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import org.jacpfx.api.annotations.workbench.Workbench;
import org.jacpfx.api.componentLayout.WorkbenchLayout;
import org.jacpfx.api.message.Message;
import org.jacpfx.rcp.componentLayout.FXComponentLayout;
import org.jacpfx.rcp.components.menuBar.JACPMenuBar;
import org.jacpfx.rcp.workbench.FXWorkbench;

import static org.jacpfx.api.util.ToolbarPosition.WEST;

/**
 * Created by Andy Moncsek on 13.12.13.
 * A JacpFX workbench.
 *
 * @author <a href="mailto:amo.ahcp@gmail.com"> Andy Moncsek</a>
 */
@Workbench(id = "id1", name = "workbench", perspectives = "id01")
public class DrawingWorkbench implements FXWorkbench {
    private Stage stage;

    @Override
    public void postHandle(FXComponentLayout layout) {
        final JACPMenuBar menu = layout.getMenu();
        final Menu menuFile = new Menu("File");
        menuFile.getItems().addAll(this.createExitEntry());
        menu.getMenus().add(menuFile);
    }

    @Override
    public void handleInitialLayout(Message<Event, Object> action, WorkbenchLayout<Node> layout, Stage stage) {
        this.stage = stage;
        layout.setWorkbenchXYSize(640,480);
        layout.registerToolBars(WEST);
        layout.setMenuEnabled(true);
    }

    private MenuItem createExitEntry() {
        final MenuItem itemExit = new MenuItem("Exit");
        itemExit.setOnAction((event) -> System.exit(0));
        return itemExit;
    }


}
