import java.awt.*;
import java.util.ArrayList;

/**
 * Class includes method to draw circle for the loops inside method.
 * @author Gursharanjit Singh Ghotra
 * @author Manthan Agrawal
 */
public class UMLLoopPlot extends DecoratorPlot {

    public UMLLoopPlot(Drawable drawable) {
        super(drawable);
    }

    /**
     * draws circle for loops inside method
     * @param g - graphics in which bar needs to be drawn
     * @param umlClassModel - Model for storing information regarding a class
     */
    public void draw(Graphics g, UMLClassModel umlClassModel) {
        super.draw(g, umlClassModel);
        g.setColor(Color.PINK);
        int x = umlClassModel.getxAxis() + 30;
        int y = umlClassModel.getyAxis();
        int z = y + 60;
        ArrayList<MethodDetails> methodDetails = umlClassModel.getMethodDetailsList();
        int radiusOfCircle = umlClassModel.getHeightOfComponent() - Constants.MARGIN;
        for (MethodDetails methodDetail : methodDetails) {
            if (methodDetail.isLoopCheck()) {
                g.fillOval(x, z, radiusOfCircle, radiusOfCircle);
                g.drawOval(x, z, radiusOfCircle, radiusOfCircle);

            }
            z = z + radiusOfCircle + Constants.MARGIN;
        }

    }
}
