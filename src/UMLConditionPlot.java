import java.awt.*;
import java.util.ArrayList;

public class UMLConditionPlot extends DecoratorPlot {
    public UMLConditionPlot(Drawable drawable) {
        super(drawable);
    }

    public void draw(Graphics g, UMLClassModel umlClassModel) {
        addedBehaviour(g, umlClassModel);

    }

    public void addedBehaviour(Graphics g, UMLClassModel umlClassModel) {
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

                int xpoints[] = {x, x - heightOfTriangle / 2, x + heightOfTriangle / 2};
                int ypoints[] = {z, z + heightOfTriangle, z + heightOfTriangle };
                int npoints = 3;
                g.fillPolygon(xpoints, ypoints, npoints);
                g.drawPolygon(xpoints, ypoints, npoints);
            }
            z = z + heightOfTriangle + Constants.MARGIN;
        }

    }
}
