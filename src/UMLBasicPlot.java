import java.awt.*;

public class UMLBasicPlot implements Drawable {
    @Override
    public void draw(Graphics g, UMLClassModel umlClassModel) {

        g.setColor(Color.BLACK);
        int x = umlClassModel.getxAxis();
        int y = umlClassModel.getyAxis();
        g.drawString(umlClassModel.getClassName(), x + 10, y + 10);
        g.setColor(Color.BLUE);

        int methodCount = umlClassModel.getMethodDetailsList().size();

        g.drawRect(x, y, 200, Constants.HEIGHT_OF_CLASS_UML);
    }
}
