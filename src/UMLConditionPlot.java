import java.awt.*;
import java.util.ArrayList;

public class UMLConditionPlot extends DecoratorPlot {
    public UMLConditionPlot(Drawable drawable) {
        super(drawable);
    }

    public void draw(Graphics g, UMLClassModel umlClassModel) {
        super.draw(g, umlClassModel);
        addedBehaviour(g, umlClassModel);

    }

    public void addedBehaviour(Graphics g, UMLClassModel umlClassModel) {
        super.draw(g, umlClassModel);
        g.setColor(Color.GREEN);
        int x = umlClassModel.getxAxis();
        int y = umlClassModel.getyAxis();
        int z = y;
        ArrayList<MethodDetails> methodDetails = umlClassModel.getMethodDetailsList();
        for ( MethodDetails methodDetail: methodDetails) {
            if (methodDetail.isIfCheck()) {
//				g.fillRect(x + 80, z + 60, 35, 35);
//				g.drawRect(x + 80, z + 60, 35, 35);

                int xpoints[] = { x + 100, x + 85, x + 115 };
                int ypoints[] = { z + 60, z + 95, z + 95 };
                int npoints = 3;
                g.fillPolygon(xpoints, ypoints, npoints);
                g.drawPolygon(xpoints, ypoints, npoints);
            }
            z = z + 60;
        }

    }
}
