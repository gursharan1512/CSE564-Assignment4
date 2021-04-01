import java.awt.*;

/**
 * class implemented to draw a rectangle in JPanel for all the classes in the input code.
 * @author Gursharanjit Singh Ghotra
 * @author Manthan Agrawal
 */
public class UMLBasicPlot implements Drawable {

    /**
     * draw line graph on the given panel
     * @param g - graphics in which bar needs to be drawn
     * @param umlClassModel - Model for storing information regarding a class
     */
    @Override
    public void draw(Graphics g, UMLClassModel umlClassModel) {

        g.setColor(Color.BLACK);
        int x = umlClassModel.getxAxis();
        int y = umlClassModel.getyAxis();
        g.drawString(umlClassModel.getClassName(), x + 10, y + 10);
        g.setColor(Color.BLUE);
        g.drawRect(x, y, 200, Constants.HEIGHT_OF_CLASS_UML);
    }
}
