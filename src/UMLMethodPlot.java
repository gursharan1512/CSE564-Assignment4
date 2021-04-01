import java.awt.*;

/**
 * Class includes method to draw rectangles for all the methods.
 * @author Gursharanjit Singh Ghotra
 * @author Manthan Agrawal
 */
public class UMLMethodPlot extends DecoratorPlot {

    public UMLMethodPlot(Drawable drawable) {
        super(drawable);
    }

    /**
     * draws rectangles for methods inside class
     * @param g - graphics in which bar needs to be drawn
     * @param umlClassModel - Model for storing information regarding a class
     */
    public void draw(Graphics g, UMLClassModel umlClassModel) {
        super.draw(g, umlClassModel);
        g.setColor(Color.GREEN);
        int x = umlClassModel.getxAxis() + 20;
        int y = umlClassModel.getyAxis();

        int methodCount = umlClassModel.getMethodDetailsList().size();
        int z = y + 55;
        int heightOfMethodBlock = umlClassModel.getHeightOfComponent();
        for (int i = 0; i < methodCount; i++) {
            g.drawRect(x, z, 150, heightOfMethodBlock - 5);
            z = z + heightOfMethodBlock;
        }

    }
}
