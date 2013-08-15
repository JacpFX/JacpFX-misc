#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.perspectives;

import javafx.event.Event;
import javafx.geometry.Orientation;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import org.jacp.api.action.IAction;
import org.jacp.javafx2.rcp.componentLayout.FX2ComponentLayout;
import org.jacp.javafx2.rcp.componentLayout.FX2PerspectiveLayout;
import org.jacp.javafx2.rcp.perspective.AFX2Perspective;
import org.jacp.javafx2.rcp.util.FX2Util.MessageUtil;
/**
 * A simple perspective defining a split pane
 * @author Andy Moncsek
 *
 */
public class PerspectiveOne extends AFX2Perspective {

	@Override
	public void handlePerspective(IAction<Event, Object> action,
			FX2PerspectiveLayout perspectiveLayout) {
		if (action.getLastMessage().equals(MessageUtil.INIT)) {
			SplitPane mainLayout = new SplitPane();
			mainLayout.setOrientation(Orientation.HORIZONTAL);
			mainLayout.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			mainLayout.setDividerPosition(0, 0.55f);

			// create left button menu
			GridPane leftMenu = new GridPane();
			GridPane.setHgrow(leftMenu, Priority.ALWAYS);
			GridPane.setVgrow(leftMenu, Priority.ALWAYS);
		
			// create main content Top
			GridPane mainContent = new GridPane();
			GridPane.setHgrow(mainContent, Priority.ALWAYS);
			GridPane.setVgrow(mainContent, Priority.ALWAYS);
	
			
			GridPane.setVgrow(mainLayout, Priority.ALWAYS);
			GridPane.setHgrow(mainLayout, Priority.ALWAYS);
			mainLayout.getItems().addAll(leftMenu, mainContent);
			// Register root component
			perspectiveLayout.registerRootComponent(mainLayout);
			// register left menu
			perspectiveLayout.registerTargetLayoutComponent("Pleft", leftMenu);
			// register main content 
			perspectiveLayout.registerTargetLayoutComponent("PMain", mainContent);
		}
		
	}

	@Override
	public void onStartPerspective(FX2ComponentLayout layout) {
		// define toolbars and menu entries
		
	}

	@Override
	public void onTearDownPerspective(FX2ComponentLayout arg0) {
		// define toolbars and menu entries when close perspective
		
	}

}
