import java.awt.*;

public class UMLMethodPlot extends DecoratorPlot {

    public UMLMethodPlot(Drawable drawable) {
        super(drawable);
    }

    public void draw(Graphics g, UMLClassModel umlClassModel) {
        super.draw(g, umlClassModel);
        addedBehaviour(g, umlClassModel);

    }

    public void addedBehaviour(Graphics g, UMLClassModel umlClassModel) {
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
