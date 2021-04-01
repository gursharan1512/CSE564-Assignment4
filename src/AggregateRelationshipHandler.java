import java.awt.*;

public class AggregateRelationshipHandler implements UMLCLassJoiner {

    private UMLCLassJoiner umlClassJoiner;

    @Override
    public void setNextUMLClassJoiner(UMLCLassJoiner umlClassJoiner) {
        this.umlClassJoiner = umlClassJoiner;

    }

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
