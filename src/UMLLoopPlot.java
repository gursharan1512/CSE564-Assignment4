import java.awt.*;
import java.util.ArrayList;

public class UMLLoopPlot extends DecoratorPlot {
    public UMLLoopPlot(Drawable drawable) {
        super(drawable);
    }

    public void draw(Graphics g, UMLClassModel umlClassModel) {
        super.draw(g, umlClassModel);
        g.setColor(Color.PINK);
        int x = umlClassModel.getxAxis();
        int y = umlClassModel.getyAxis();
        int z = y;
        //int methodCount = umlClassModel.getMethodDetailsList().size();
        ArrayList<MethodDetails> methodDetails = umlClassModel.getMethodDetailsList();
        for ( MethodDetails methodDetail: methodDetails) {
            if (methodDetail.isLoopCheck()) {
                g.fillOval(x + 30, z + 60, 35, 35);
                g.drawOval(x + 30, z + 60, 35, 35);

            }
            z = z + 60;
        }

    }
}
