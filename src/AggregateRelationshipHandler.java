import java.awt.*;

/**
 * Provides methods to draw aggregation relation between two classes
 *
 * @author Gursharanjit Singh Ghotra
 * @author Manthan Agrawal
 */
public class AggregateRelationshipHandler extends UMLCLassJoiner {

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

        if (connection.equals("Aggregation")) {
            g.setColor(Color.BLUE);

            if (x1 < x2) {
                int[] xpoints1 = {x1, x1 + 10, x1 + 20, x1 + 10};
                int[] ypoints1 = {y1, y1 - 10, y1, y1 + 10};
                int npoints1 = 4;
                g.drawPolygon(xpoints1, ypoints1, npoints1);
            } else {
                int[] xpoints1 = {x1, x1 - 10, x1 - 20, x1 - 10};
                int[] ypoints1 = {y1, y1 - 10, y1, y1 + 10};
                int npoints1 = 4;
                g.drawPolygon(xpoints1, ypoints1, npoints1);
            }
        } else {
            umlClassJoiner.drawRelationship(x1, y2, x2, y2, connection, g);
        }

    }
}
