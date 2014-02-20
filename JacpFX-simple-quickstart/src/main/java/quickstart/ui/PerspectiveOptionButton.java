package quickstart.ui;

import javafx.scene.control.Button;
import org.jacpfx.rcp.componentLayout.FXComponentLayout;
import org.jacpfx.rcp.components.toolBar.JACPOptionButton;
import org.jacpfx.rcp.context.Context;
import quickstart.util.MessageConstants;
import quickstart.util.PathUtil;
import quickstart.util.PerspectiveIds;

/**
 * Created with IntelliJ IDEA.
 * User: PETE
 * Date: 12/02/14
 * Time: 21:47
 * To change this template use File | Settings | File Templates.
 */
public class PerspectiveOptionButton extends JACPOptionButton {


    public PerspectiveOptionButton(final FXComponentLayout layout, final Context context, final String label, final Perspectives excludedPerspective) {
        super(label, layout);
        this.initButtons(context, excludedPerspective);
    }

    private void initButtons(final Context context, final Perspectives excludedPerspective) {
        for (Perspectives perspectives : Perspectives.values()) {
            if (excludedPerspective != perspectives) {
                Button button = new Button(perspectives.getPerspectiveName());
                button.setOnAction((event) -> {
                    context.send(perspectives.getPerspectiveId(), MessageConstants.SWITCH_MESSAGE);
                });
                this.addOption(button);
            }
        }
    }


}
