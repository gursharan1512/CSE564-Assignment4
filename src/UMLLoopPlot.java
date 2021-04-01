import java.awt.*;
import java.util.ArrayList;

public class UMLLoopPlot extends DecoratorPlot {
    public UMLLoopPlot(Drawable drawable) {
        super(drawable);
    }

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
