package quickstart.ui;

import quickstart.util.PerspectiveIds;

/**
 * Created with IntelliJ IDEA.
 * User: PETE
 * Date: 12/02/14
 * Time: 22:25
 * To change this template use File | Settings | File Templates.
 */
public enum Perspectives {

    PERSPECTIVE_1(PerspectiveIds.PERSPECTIVE_ONE, "Perspective 1"),
    PERSPECTIVE_2(PerspectiveIds.PERSPECTIVE_TWO, "Perspective 2"),
    PERSPECTIVE_3(PerspectiveIds.PERSPECTIVE_THREE, "Perspective 3"),
    PERSPECTIVE_4(PerspectiveIds.PERSPECTIVE_FOUR, "Perspective 4");

    private String perspectiveId;
    private String perspectiveName;

    private Perspectives(final String perspectiveId, final String perspectiveName) {
        this.perspectiveId = perspectiveId;
        this.perspectiveName = perspectiveName;
    }

    public String getPerspectiveId() {
        return perspectiveId;
    }

    public String getPerspectiveName() {
        return perspectiveName;
    }
}
