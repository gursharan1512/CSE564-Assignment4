import java.awt.*;

public class AssociationRelationshipHandler implements UMLCLassJoiner {

    private UMLCLassJoiner umlClassJoiner;

    @Override
    public void setNextUMLClassJoiner(UMLCLassJoiner umlClassJoiner) {
        this.umlClassJoiner = umlClassJoiner;

    }

    @Override
    public void drawRelationship(int x1, int y1, int x2, int y2, String connection, Graphics g) {


        if (connection.equals("Association")) {
            g.setColor(Color.BLUE);
            g.drawLine(x2, y2, x2 - 3, y2 - 10);
            g.drawLine(x2, y2, x2 - 3, y2 + 10);

//            int xpoints1[] = {x2, x2-5,x2-10, x2-5};
//            int ypoints1[] = {y2, y2-5, y2 , y2 +5 };
//            int npoints1 = 4;
//            g.fillPolygon(xpoints1, ypoints1, npoints1);
//            g.drawPolygon(xpoints1, ypoints1, npoints1);
//            UMLClassModel destClassObj = null;
//            for (UMLClassModel classAttribute : classAttributes) {
//                if (destClass.equals(classAttribute.getClassName())) {
//                    destClassObj = classAttribute;
//                    break;
//                }
//
//            }
//
//            if (destClassObj != null) {
//                int x1 = sourceClass.getxAxis();
//                int y1 = sourceClass.getyAxis();
//
//                int x2 = destClassObj.getxAxis();
//                int y2 = destClassObj.getyAxis();
//
//                if (x1 < x2) {
//                    g.drawLine(x1 + 200, y1 + 150, x2, y2 + 150);
//                } else if (x2 > x1) {
//                    g.drawLine(x1, y1 + 150, x2 + 200, y2 + 150);
//                } else if (x1 == x2) {
//                    if (y1 < y2) {
//                        g.drawLine(x1 + 100, y1 + 300, x2 + 100, y2);
//                    } else
//                        g.drawLine(x1 + 100, y1, x2 + 100, y2 + 300);
//                }
//            }

            System.out.println("Association relationship printer");

        } else {
            umlClassJoiner.drawRelationship(x1, y1, x2, y2, connection, g);
        }

    }
}
