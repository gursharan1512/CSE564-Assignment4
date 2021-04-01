import java.awt.*;

public class AssociationRelationshipHandler extends UMLCLassJoiner {


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
