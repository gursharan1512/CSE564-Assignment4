import java.awt.*;

public class ErrorRelationshipHandler extends UMLCLassJoiner {

    @Override
    public void drawRelationship(int x1, int y1, int x2, int y2, String connection, Graphics g) {
        System.out.println("Able to handle only Association and aggregation");
    }
}
