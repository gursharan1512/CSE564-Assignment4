import java.awt.*;
import java.util.ArrayList;

/**
 * Class includes method to draw triangles for the if condition inside method.
 * @author Gursharanjit Singh Ghotra
 * @author Manthan Agrawal
 */
public class UMLConditionPlot extends DecoratorPlot {

    public UMLConditionPlot(Drawable drawable) {
        super(drawable);
    }

    /**
     * draws triangle for if conditions inside method
     * @param g - graphics in which bar needs to be drawn
     * @param umlClassModel - Model for storing information regarding a class
     */
    public void draw(Graphics g, UMLClassModel umlClassModel) {
        super.draw(g, umlClassModel);
        g.setColor(Color.GREEN);
        int x = umlClassModel.getxAxis();
        int y = umlClassModel.getyAxis();
        int z = y + 60;
        x += 100;
        ArrayList<MethodDetails> methodDetails = umlClassModel.getMethodDetailsList();
        int heightOfTriangle = umlClassModel.getHeightOfComponent() - Constants.MARGIN;
        for (MethodDetails methodDetail : methodDetails) {
            if (methodDetail.isIfCheck()) {

                int[] xpoints = {x, x - heightOfTriangle / 2, x + heightOfTriangle / 2};
                int[] ypoints = {z, z + heightOfTriangle, z + heightOfTriangle};
                int npoints = 3;
                g.fillPolygon(xpoints, ypoints, npoints);
                g.drawPolygon(xpoints, ypoints, npoints);

            }
            z = z + heightOfTriangle + Constants.MARGIN;
        }
    }
}
