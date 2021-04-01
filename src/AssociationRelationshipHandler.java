import java.awt.*;

/**
 * Provides methods to draw association relation between two classes
 *
 * @author Gursharanjit Singh Ghotra
 * @author Manthan Agrawal
 */
public class AssociationRelationshipHandler extends UMLCLassJoiner {

    /**
     * Check whether given token is a While or For loop and stores the information in MethodDetails object
     * @param x1 - starting x coordinate to draw aggregation relation.
     * @param y1 - starting y coordinate to draw aggregation relation.
     * @param x2 - ending x coordinate to draw aggregation relation.
     * @param y2 - ending y coordinate to draw aggregation relation.
     * @param connection - defines the type of relation between source and destination class like aggregation or association.
     * @param g - graphics in which bar needs to be drawn
     */
    @Override
    public void drawRelationship(int x1, int y1, int x2, int y2, String connection, Graphics g) {

        if (connection.equals("Association")) {
            g.setColor(Color.BLUE);
            if (x1 < x2) {
                g.drawLine(x2, y2, x2 - 7, y2 - 15);
                g.drawLine(x2, y2, x2 - 7, y2 + 15);
            } else {
                g.drawLine(x2, y2, x2 + 7, y2 - 15);
                g.drawLine(x2, y2, x2 + 7, y2 + 15);
            }
        } else {
            umlClassJoiner.drawRelationship(x1, y1, x2, y2, connection, g);
        }
    }
}
